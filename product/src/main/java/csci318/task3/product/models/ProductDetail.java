package csci318.task3.product.models;

import javax.persistence.*;

@Entity
public class ProductDetail {
    @Id
    private String name;

    @OneToOne(mappedBy = "productDetail")
    private Product product;

    private String description;
    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ProductDetail(String name, String description, String comment) {
        this.name = name;
        this.description = description;
        this.comment = comment;
    }

    public ProductDetail() { }

    @Override
    public String toString() {
        return "ProductDetail{" +
                "productName='" + name + '\'' +
                ", description='" + description + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
