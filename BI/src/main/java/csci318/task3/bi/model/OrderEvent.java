package csci318.task3.bi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderEvent {

    private Long customerId;
    private String productName;
    private Long quantity;
    private double price;


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public OrderEvent(Long customerId, String productName, Long quantity, double price) {
        this.customerId = customerId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderEvent() { }

    @Override
    public String toString() {
        return "OrderEvent{" +
                "customerId=" + customerId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
