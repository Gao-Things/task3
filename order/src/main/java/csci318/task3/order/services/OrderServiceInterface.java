package csci318.task3.order.services;

import csci318.task3.order.models.Order;

import java.util.Map;

public interface OrderServiceInterface {
    public Order saveOrder(Order order);
    public Map<String, Object> fetchOrderCustomerById(Long orderId);
    public Map<String, Object> fetchOrderProductById(Long orderId);

}
