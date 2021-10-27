package csci318.task3.order.controller;

import csci318.task3.order.models.*;
import csci318.task3.order.models.Order;
import csci318.task3.order.services.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private OrderServiceInterface orderService;

    @PostMapping("/orders")
    public Order saveOrder(@RequestBody Map<String, Object> req) throws Exception {
        Long quantity = Long.parseLong(String.valueOf(req.get("quantity")));
        String productName = String.valueOf(req.get("productName"));
        Long customerId = Long.parseLong(String.valueOf(req.get("customerId")));
        Order order = new Order(quantity, productName, customerId);
        return orderService.saveOrder(order);
    }

    @GetMapping("/orders/{id}/customer")
    public Map<String, Object> fetchOrderCustomerById(@PathVariable("id") Long orderId) {
        return orderService.fetchOrderCustomerById(orderId);
    }

    @GetMapping("/orders/{id}/product")
    public Map<String, Object> fetchOrderProductById(@PathVariable("id") Long orderId) {
        return orderService.fetchOrderProductById(orderId);
    }
}
