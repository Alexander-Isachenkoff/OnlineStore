package model.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Поставки")
public class Supply
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Код")
    private int id;
    
    @Column(name = "Дата")
    private String date;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Код_поставщика")
    private Provider provider;
    
    @OneToMany(mappedBy = "supply", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductSupply> productSupplies = new ArrayList<>();
    
    public Supply() {}
    
    public Supply(String date, Provider provider) {
        this.date = date;
        this.provider = provider;
    }
    
    public int getId() {
        return id;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public Provider getProvider() {
        return provider;
    }
    
    public void setProvider(Provider provider) {
        this.provider = provider;
    }
    
    public List<ProductSupply> getProductSupplies() {
        return productSupplies;
    }
    
    @Override
    public String toString() {
        return "Supply{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", provider=" + provider +
                ", productSupplies=" + productSupplies +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supply supply = (Supply) o;
        return id == supply.id &&
                Objects.equals(date, supply.date) &&
                Objects.equals(provider, supply.provider);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, date, provider, productSupplies);
    }
}
