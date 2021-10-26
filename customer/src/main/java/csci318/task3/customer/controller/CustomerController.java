package csci318.task3.customer.controller;

import csci318.task3.customer.models.Contact;
import csci318.task3.customer.models.Customer;
import csci318.task3.customer.services.CustomerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    @Autowired
    private CustomerServiceInterface customerService;

    @PostMapping("/customers")
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @PutMapping("/customers/{id}")
    public Customer saveCustomer(@PathVariable("id") Long customerId, @RequestBody Customer customer) {
        return customerService.updateCustomer(customerId, customer);
    }

    @GetMapping("/customers")
    public List<Customer> fetchCustomerList() {
        return customerService.fetchCustomerList();
    }

    @GetMapping("/customers/{id}")
    public Customer fetchCustomerById(@PathVariable("id") Long customerId) {
        return customerService.fetchCustomerById(customerId);
    }

    @GetMapping("/customers/validate/{id}")
    public Map<String, String> validate(@PathVariable("id") Long customerId) {
        return customerService.validate(customerId);
    }

    @PostMapping("/customers/{id}/contact")
    public Customer saveContact(@PathVariable("id") Long customerId, @RequestBody Contact contact) {
        return customerService.saveContact(customerId, contact);
    }

    @PutMapping("/customers/{id}/contact")
    public Customer updateContact(@PathVariable("id") Long customerId, @RequestBody Contact contact) {
        return customerService.updateContact(customerId, contact);
    }
}
