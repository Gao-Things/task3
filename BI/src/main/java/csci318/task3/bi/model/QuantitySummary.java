package csci318.task3.bi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class QuantitySummary {

    private String productName;
    private long quantity;

    public QuantitySummary(String productName, long quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public QuantitySummary() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductQuantity{" +
                "productName='" + productName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
