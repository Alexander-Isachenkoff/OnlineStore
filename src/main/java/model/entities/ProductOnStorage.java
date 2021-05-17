package model.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Склад")
public class ProductOnStorage implements Serializable
{
    @Id
    @OneToOne
    @JoinColumn(name = "Код_товара", referencedColumnName = "Код")
    private Product product;
    
    @Column(name = "Количество")
    private int quantity;
    
    public ProductOnStorage() {}
    
    public ProductOnStorage(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    
    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
