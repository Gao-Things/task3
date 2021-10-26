package csci318.task3.customer.services;

import csci318.task3.customer.models.Contact;
import csci318.task3.customer.models.Customer;
import csci318.task3.customer.repositories.CustomerRepository;
import csci318.task3.customer.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService implements CustomerServiceInterface {

    @Autowired
    private CustomerRepository customerRepository;

    private Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Errors Occurred"));
    }

    private Boolean isEntered(String s) {
        return Objects.nonNull(s) && !"".equalsIgnoreCase(s);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer customer) {
        Customer newCustomerInfo = getCustomerById(customerId);

        if (isEntered(customer.getCompanyName())) {
            newCustomerInfo.setCompanyName(customer.getCompanyName());
        }

        if (isEntered(customer.getAddress())) {
            newCustomerInfo.setAddress(customer.getAddress());
        }

        if (isEntered(customer.getCountry())) {
            newCustomerInfo.setCountry(customer.getCountry());
        }

        return customerRepository.save(newCustomerInfo);
    }

    @Override
    public List<Customer> fetchCustomerList() {
        return customerRepository.findAll();
    }

    @Override
    public Customer fetchCustomerById(Long customerId) {
        return getCustomerById(customerId);
    }

    @Override
    public Customer fetchCustomerContact(Long customerId) {
        return getCustomerById(customerId);
    }

    @Override
    public Customer saveContact(Long customerId, Contact contact) {
        Customer customer = getCustomerById(customerId);
        customer.setContact(contact);
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateContact(Long customerId, Contact contact) {
        Customer customer = getCustomerById(customerId);
        Contact newContact = customer.getContact();

        if (isEntered(contact.getName())) {
            newContact.setName(contact.getName());
        }

        if (isEntered(contact.getEmail())) {
            newContact.setEmail(contact.getEmail());
        }

        if (isEntered(contact.getPhone())) {
            newContact.setPhone(contact.getPhone());
        }

        if (isEntered(contact.getPosition())) {
            newContact.setPosition(contact.getPosition());
        }

        customer.setContact(newContact);
        return customerRepository.save(customer);
    }

    @Override
    public Map<String, String> validate(Long customerId) {
        HashMap<String, String> map = new HashMap<>();
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (!customerOpt.isPresent())
            return null;
        Customer customer = customerOpt.get();
        String address = customer.getAddress();
        String phone = customer.getContact().getPhone();
        map.put("address", address);
        map.put("phone", phone);
        return map;
    }
}