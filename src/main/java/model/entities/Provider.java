package model.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Поставщики")
public class Provider
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Код")
    private int id;
    
    @Column(name = "ИНН")
    private String INN;
    
    @Column(name = "Наименование")
    private String name;
    
    @Column(name = "Email")
    private String email;
    
    @Column(name = "Телефон")
    private String phoneNumber;
    
    @Column(name = "ОГРН")
    private String OGRN;
    
    @Column(name = "Адрес")
    private String address;
    
    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Supply> supplies = new ArrayList<>();
    
    public Provider() {};
    
    public Provider(String INN, String name, String email, String phoneNumber, String OGRN, String address) {
        this.INN = INN;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.OGRN = OGRN;
        this.address = address;
    }
    
    public int getId() {
        return id;
    }
    
    public void setINN(String INN) {
        this.INN = INN;
    }
    
    public String getINN() {
        return INN;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getOGRN() {
        return OGRN;
    }
    
    public void setOGRN(String OGRN) {
        this.OGRN = OGRN;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Override
    public String toString() {
        return "Provider{" +
                "INN='" + INN + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", OGRN='" + OGRN + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider provider = (Provider) o;
        return id == provider.id &&
                Objects.equals(INN, provider.INN) &&
                Objects.equals(name, provider.name) &&
                Objects.equals(email, provider.email) &&
                Objects.equals(phoneNumber, provider.phoneNumber) &&
                Objects.equals(OGRN, provider.OGRN) &&
                Objects.equals(address, provider.address);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, INN, name, email, phoneNumber, OGRN, address);
    }
}
