package model.access;

import model.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SupplyDAOTest
{
    private final ManufacturerDAO manufacturerDAO = new ManufacturerDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final ProductDAO productDAO = new ProductDAO();
    private final SupplyDAO supplyDAO = new SupplyDAO();
    private final ProductSupplyDAO productSupplyDAO = new ProductSupplyDAO();
    private final ProviderDAO providerDAO = new ProviderDAO();
    
    @BeforeEach
    void clearAll() {
        for (Product product : productDAO.selectAll()) {
            productDAO.delete(product);
        }
        for (Category category : categoryDAO.selectAll()) {
            categoryDAO.delete(category);
        }
        for (Manufacturer manufacturer : manufacturerDAO.selectAll()) {
            manufacturerDAO.delete(manufacturer);
        }
        for (Supply supply : supplyDAO.selectAll()) {
            supplyDAO.delete(supply);
        }
        for (ProductSupply supply : productSupplyDAO.selectAll()) {
            productSupplyDAO.delete(supply);
        }
        for (Provider provider : providerDAO.selectAll()) {
            providerDAO.delete(provider);
        }
    }
    
    private Product createExampleProduct(String name) {
        Category category = new Category("Гигиена");
        categoryDAO.save(category);
        Manufacturer manufacturer = new Manufacturer("Dove");
        manufacturerDAO.save(manufacturer);
        return new Product(name, category, manufacturer, 125.00, "Описание");
    }
    
    private Provider createExampleProvider() {
        String INN = "1234567890";
        String name = "Политех";
        String email = "example@mail.ru";
        String phone = "65-65-65";
        String OGRN = "1234567891234";
        String address = "ул. Лермонтова, 86";
        return new Provider(INN, name, email, phone, OGRN, address);
    }
    
    @Test
    void save() {
        Product product = createExampleProduct("Мыло");
        productDAO.save(product);
        Provider provider = createExampleProvider();
        providerDAO.save(provider);
        
        Supply supply = new Supply("13.05.2020", provider);
        ProductSupply productSupply = new ProductSupply(supply, product, 10, 110.0);
        supply.getProductSupplies().add(productSupply);
        supplyDAO.save(supply);
        
        Supply loaded = supplyDAO.findById(supply.getId());
        
        System.out.println(loaded.getProductSupplies().size());
        for (ProductSupply supplyProductSupply : loaded.getProductSupplies()) {
            System.out.println(supplyProductSupply);
        }
    }
}