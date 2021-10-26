package csci318.task3.product.controller;

import csci318.task3.product.models.*;
import csci318.task3.product.services.ProductServiceInterface;
import csci318.task3.product.models.Product;
import csci318.task3.product.models.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductServiceInterface productService;

    @PostMapping("/products")
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/products/{name}")
    public Product saveProduct(@PathVariable("name") String name, @RequestBody Product product) {
        return productService.updateProduct(name, product);
    }

    @GetMapping("/products")
    public List<Product> fetchProductList() {
        return productService.fetchProductList();
    }

    @GetMapping("/products/{name}")
    public Product fetchProductByName(@PathVariable("name") String name) {
        return productService.fetchProductByName(name);
    }

    @GetMapping("/products/{name}/price")
    public double fetchPriceByProductName(@PathVariable("name") String name) {
        return productService.fetchPriceByProductName(name);
    }

    @GetMapping("/products/validate/{name}/{quantity}")
    public Double checkInventory(@PathVariable("name") String name, @PathVariable("quantity") Long quantity) {
        return productService.checkInventory(name, quantity);
    }

    @PostMapping("/products/{name}/product-detail")
    public Product saveProductDetail(@PathVariable("name") String name, @RequestBody ProductDetail productDetail) {
        return productService.saveProductDetail(name, productDetail);
    }

    @PutMapping("/products/update-quantity/")
    public void updateProductQuantity(@RequestBody Map<String, Object> req) {
        productService.updateProductQuantity(req);
    }
}
