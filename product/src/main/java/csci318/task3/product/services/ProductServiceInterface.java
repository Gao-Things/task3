package csci318.task3.product.services;

import csci318.task3.product.models.*;
import csci318.task3.product.models.Product;
import csci318.task3.product.models.ProductDetail;

import java.util.List;
import java.util.Map;

public interface ProductServiceInterface {
    public Product saveProduct(Product product);
    public Product updateProduct(String name, Product product);
    public List<Product> fetchProductList();
    public Product fetchProductByName(String name);
    public Product fetchProductContact(String name);
    public Product saveProductDetail(String name, ProductDetail productDetail);
    public Product updateProductDetail(String name, ProductDetail productDetail);
    public Double checkInventory(String productName, Long quantity);
    public void updateProductQuantity(Map<String, Object> event);

    public double fetchPriceByProductName(String name);
}
