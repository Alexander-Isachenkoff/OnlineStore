package model.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Поставка_товара")
public class ProductSupply implements Serializable
{
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Код_поставки")
    private Supply supply;
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Код_товара")
    private Product product;
    
    @Column(name = "Количество")
    private int quantity;
    
    @Column(name = "Цена_за_единицу")
    private double cost;
    
    public ProductSupply() {}
    
    public ProductSupply(Supply supply, Product product, int quantity, double cost) {
        this.supply = supply;
        this.product = product;
        this.quantity = quantity;
        this.cost = cost;
    }
    
    public Supply getSupply() {
        return supply;
    }
    
    public void setSupply(Supply supply) {
        this.supply = supply;
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
    
    public double getCost() {
        return cost;
    }
    
    public void setCost(double cost) {
        this.cost = cost;
    }
    
    @Override
    public String toString() {
        return "ProductSupply{" +
                ", product=" + product +
                ", quantity=" + quantity +
                ", cost=" + cost +
                '}';
    }
}
