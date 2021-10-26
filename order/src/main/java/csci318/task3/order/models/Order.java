package csci318.task3.order.models;

import javax.persistence.*;

@Entity
@Table(name="Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long orderId;

    private String supplier;
    private Long quantity;
    private String productName;
    private Long customerId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
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

    public Order(String supplier, Long quantity, String productName, Long customerId) {
        this.supplier = supplier;
        this.quantity = quantity;
        this.productName = productName;
        this.customerId = customerId;
    }

    public Order() { }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", supplier='" + supplier + '\'' +
                ", quantity=" + quantity +
                ", productName='" + productName + '\'' +
                ", customerId=" + customerId +
                '}';
    }
}
