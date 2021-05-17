package model.service;

import model.access.*;
import model.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplyServiceTest
{
    private final ProviderService providerService = new ProviderService();
    private final CategoryService categoryService = new CategoryService();
    private final ManufacturerService manufacturerService = new ManufacturerService();
    private final ProductService productService = new ProductService();
    private final SupplyService supplyService = new SupplyService();
    
    private final ManufacturerDAO manufacturerDAO = new ManufacturerDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final ProductDAO productDAO = new ProductDAO();
    private final SupplyDAO supplyDAO = new SupplyDAO();
    private final ProductSupplyDAO productSupplyDAO = new ProductSupplyDAO();
    private final ProviderDAO providerDAO = new ProviderDAO();
    private final ProductOnStorageDAO productOnStorageDAO = new ProductOnStorageDAO();
    
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
        for (ProductOnStorage productOnStorage : productOnStorageDAO.selectAll()) {
            productOnStorageDAO.delete(productOnStorage);
        }
    }
    
    @Test
    void supplyTest() {
        Category cat1 = new Category("Гигиена");
        categoryService.saveCategory(cat1);
        Manufacturer man1 = new Manufacturer("Dove");
        manufacturerService.saveManufacturer(man1);
        Provider prov1 = new Provider("4654", "Поставщик 1", "example@mail.ru", "65-65-65",
                "5646871", "Адрес 1");
        providerService.saveProvider(prov1);
        Product product1 = new Product("Мыло", cat1, man1, 100, "Описание мыла");
        productService.saveProduct(product1);
        
        Product loadedProduct = productService.findProductById(product1.getId());
        assertEquals(product1.toString(), loadedProduct.toString());
    
        assertEquals(0, productService.getProductQuantity(loadedProduct));
        
        Supply supply = new Supply("13.05.2020", prov1);
        ProductSupply productSupply = new ProductSupply(supply, product1, 15, 115);
        supply.getProductSupplies().add(productSupply);
        supplyService.saveSupply(supply);
    
        assertEquals(15, productService.getProductQuantity(loadedProduct));
        
        Supply loadedSupply = supplyService.findSupplyById(supply.getId());
        loadedSupply.getProductSupplies().get(0).setQuantity(5);
        supplyService.updateSupply(loadedSupply);
    
        assertEquals(5, productService.getProductQuantity(loadedProduct));
    }
}