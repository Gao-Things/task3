package csci318.task3.product.models;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productDetail_name")
    private ProductDetail productDetail;

    private String productCategory;
    private Double price;
    private Long stockQuantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Long stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    public Product(String name, String productCategory, Double price, Long stockQuantity) {
        this.name = name;
        this.productCategory = productCategory;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public Product() { }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", productDetail=" + productDetail.toString() +
                ", productCategory='" + productCategory + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}

