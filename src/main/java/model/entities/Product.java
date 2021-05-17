package model.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Товары")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Код")
    private int id;
    
    @Column(name = "Наименование")
    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Код_категории")
    private Category category;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Код_производителя")
    private Manufacturer manufacturer;
    
    @Column(name = "Стоимость")
    private double cost;
    
    @Column(name = "Описание")
    private String description;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductSupply> productSupplies = new ArrayList<>();
    
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductOnStorage productOnStorage;
    
    public Product() {}
    
    public Product(String name, Category category, Manufacturer manufacturer, double cost, String description) {
        this.name = name;
        this.category = category;
        this.manufacturer = manufacturer;
        this.cost = cost;
        this.description = description;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Category getCategory() {
        return category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    
    public Manufacturer getManufacturer() {
        return manufacturer;
    }
    
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
    
    public double getCost() {
        return cost;
    }
    
    public void setCost(double cost) {
        this.cost = cost;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", manufacturer=" + manufacturer +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Double.compare(product.cost, cost) == 0 &&
                name.equals(product.name) &&
                category.equals(product.category) &&
                manufacturer.equals(product.manufacturer) &&
                description.equals(product.description);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, manufacturer, cost, description);
    }
}
