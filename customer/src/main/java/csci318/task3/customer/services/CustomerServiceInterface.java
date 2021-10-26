package csci318.task3.customer.services;

import csci318.task3.customer.models.*;
import csci318.task3.customer.models.Contact;
import csci318.task3.customer.models.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerServiceInterface {

    public Customer saveCustomer(Customer customer);

    public List<Customer> fetchCustomerList();

    public Customer fetchCustomerById(Long customerId);

    Customer fetchCustomerContact(Long customerId);

    public Customer saveContact(Long customerId, Contact contact);

    public Customer updateCustomer(Long customerId, Customer customer);

    public Customer updateContact(Long customerId, Contact contact);

    public Map<String, String> validate(Long customerId);
}
