package csci318.task3.bi.controller;

import csci318.task3.bi.service.OrderInteractiveQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BIController {
    @Autowired
    private OrderInteractiveQuery orderInteractiveQuery;
}
