package csci318.task3.order.services;

import csci318.task3.order.models.*;
import csci318.task3.order.repositories.*;
import csci318.task3.order.models.Order;
import csci318.task3.order.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;

@Service
public class OrderService implements OrderServiceInterface {
    static RestTemplate restTemplate = new RestTemplate();
    @Autowired
    StreamBridge streamBridge;
    static String customerUrl = "http://localhost:3000/";
    static String productUrl = "http://localhost:3002/";

    @Autowired
    private OrderRepository orderRepository;

    private ApplicationEventPublisher publisher;

    @Autowired
    public void QuoteService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    private Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Errors Occurred"));
    }

    private Boolean isEntered(String s) {
        return Objects.nonNull(s) && !"".equalsIgnoreCase(s);
    }

    @Override
    public Order saveOrder(Order order) {
        Map<String, String> customerValidate = restTemplate.getForObject(customerUrl + "customers/validate/{id}", Map.class, order.getCustomerId());
        double price = 0;
        if (Objects.nonNull(customerValidate)) {
            Double productValidate = restTemplate.getForObject(productUrl + "/products/validate/{name}/{quantity}", Double.class, order.getProductName(), order.getQuantity());
            if (Objects.nonNull(productValidate)) {
                Map<String, Object> requestBody = new HashMap<>();
                requestBody.put("name", order.getProductName());
                requestBody.put("quantity", order.getQuantity());
                restTemplate.put(productUrl + "/products/update-quantity/", requestBody);
                price = restTemplate.getForObject(productUrl + "/products/" + order.getProductName() + "/price", Double.class);
            }
        }
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setCustomerId(order.getCustomerId());
        orderEvent.setProductName(order.getProductName());
        orderEvent.setQuantity(order.getQuantity());
        orderEvent.setPrice(price);
        streamBridge.send("test-port", orderEvent);
        return orderRepository.save(order);
    }

    @Override
    public Map<String, Object> fetchOrderCustomerById(Long orderId) {
        Order order = getOrderById(orderId);
        Map<String, Object> customer = restTemplate.getForObject(customerUrl + "customers/{id}", Map.class, order.getCustomerId());
        return customer;
    }

    @Override
    public Map<String, Object> fetchOrderProductById(Long orderId) {
        Order order = getOrderById(orderId);
        Map<String, Object> product = restTemplate.getForObject(productUrl + "products/{name}", Map.class, order.getProductName());
        return product;
    }
}