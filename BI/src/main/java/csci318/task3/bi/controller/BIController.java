package csci318.task3.bi.controller;

import csci318.task3.bi.service.OrderInteractiveQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BIController {
    @Autowired
    private OrderInteractiveQuery orderInteractiveQuery;

    @GetMapping("/bi/{id}/order-value")
    private Double getOrderValueByCustomerId(@PathVariable("id") Long customerId) {
        return orderInteractiveQuery.getOrderValueByCustomerId(customerId);
    }

    @GetMapping("/bi/{id}/products")
    private List<String> getProductByCustomerId(@PathVariable("id") Long customerId) {
        return orderInteractiveQuery.getProductByCustomerId(customerId);
    }
}
