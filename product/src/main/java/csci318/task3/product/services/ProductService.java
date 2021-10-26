package csci318.task3.product.services;

import csci318.task3.product.models.*;
import csci318.task3.product.repositories.ProductRepository;
import csci318.task3.product.models.Product;
import csci318.task3.product.models.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService implements ProductServiceInterface {
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ProductRepository productRepository;

    private Product getProductById(String name) {
        return productRepository.findById(name).orElseThrow(() -> new RuntimeException("Errors Occurred"));
    }

    private Boolean isEntered(String s) {
        return Objects.nonNull(s) && !"".equalsIgnoreCase(s);
    }
    private Boolean isEntered(Double s) {
        return Objects.nonNull(s);
    }
    private Boolean isEntered(Long s) {
        return Objects.nonNull(s);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(String name, Product product) {
        Product newProductInfo = getProductById(name);

        if (isEntered(product.getProductCategory())) {
            newProductInfo.setProductCategory(product.getProductCategory());
        }

        if (isEntered(product.getPrice())) {
            newProductInfo.setPrice(product.getPrice());
        }

        if (isEntered(product.getStockQuantity())) {
            newProductInfo.setStockQuantity(product.getStockQuantity());
        }

        return productRepository.save(newProductInfo);
    }

    @Override
    public List<Product> fetchProductList() {
        return productRepository.findAll();
    }

    @Override
    public Product fetchProductByName(String name) {
        return getProductById(name);
    }

    @Override
    public double fetchPriceByProductName(String name)
    {
        Product product = getProductById(name);
        return product.getPrice();
    }

    @Override
    public Product fetchProductContact(String name) {
        return getProductById(name);
    }

    @Override
    public Product saveProductDetail(String name, ProductDetail productDetail) {
        Product product = getProductById(name);
        productDetail.setName(name);
        product.setProductDetail(productDetail);
        return saveProduct(product);
    }

    @Override
    public Product updateProductDetail(String name, ProductDetail productDetail) {
        Product product = getProductById(name);
        ProductDetail newProductDetail = product.getProductDetail();

        if (isEntered(productDetail.getDescription())) {
            newProductDetail.setDescription(productDetail.getDescription());
        }

        if (isEntered(productDetail.getComment())) {
            newProductDetail.setComment(productDetail.getComment());
        }

        product.setProductDetail(newProductDetail);
        return saveProduct(product);
    }

    @Override
    public Double checkInventory(String productName, Long quantity) {
        Optional< Product > productOpt = productRepository.findById(productName);
        if (!productOpt.isPresent())
            return null;
        Product product = productOpt.get();
        if (product.getStockQuantity() < quantity)
            return null;
        return product.getPrice();
    }

    @Override
    public void updateProductQuantity(Map<String, Object> req) {
        String productName = String.valueOf(req.get("name"));
        Long quantity = Long.parseLong(String.valueOf(req.get("quantity")));
        Product product = getProductById(productName);
        product.setStockQuantity(product.getStockQuantity() - quantity);
        publisher.publishEvent(product);
    }
}