package csci318.task3.product.services;

import csci318.task3.product.models.Product;
import csci318.task3.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ProductEventHandler {
    @Autowired
    private ProductRepository productRepository;

    private Product getProductById(String name) {
        return productRepository.findById(name).orElseThrow(() -> new RuntimeException("Errors Occurred"));
    }

    @EventListener
    public void eventHandler1(Product product) {
        productRepository.save(product);
    }
}
