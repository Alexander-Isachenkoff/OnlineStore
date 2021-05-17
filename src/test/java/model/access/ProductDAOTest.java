package model.access;

import model.entities.Category;
import model.entities.Manufacturer;
import model.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDAOTest
{
    private final ManufacturerDAO manufacturerDAO = new ManufacturerDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final ProductDAO productDAO = new ProductDAO();
    
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
    }
    
    private Product createExampleProduct() {
        Category category = new Category("Гигиена");
        categoryDAO.save(category);
        Manufacturer manufacturer = new Manufacturer("Dove");
        manufacturerDAO.save(manufacturer);
        return new Product("Мыло", category, manufacturer, 125.00, "Описание мыла");
    }
    
    @Test
    void save() {
        Product product = createExampleProduct();
        productDAO.save(product);
        Product loaded = productDAO.findById(product.getId());
        assertEquals(product.toString(), loaded.toString());
    }
    
    @Test
    void update() {
        Product product = createExampleProduct();
        productDAO.save(product);
        
        Category newCategory = new Category("Техника для дома");
        Manufacturer newManufacturer = new Manufacturer("Scarlett");
        categoryDAO.save(newCategory);
        manufacturerDAO.save(newManufacturer);
        
        product.setCategory(newCategory);
        product.setManufacturer(newManufacturer);
        product.setCost(1111.0);
        product.setName("Вентилятор");
        product.setDescription("Описание вентилятора");
        
        productDAO.update(product);
        
        Product newProduct = productDAO.findById(product.getId());
        assertEquals(product.toString(), newProduct.toString());
    }
    
    @Test
    void delete() {
        Product saved = createExampleProduct();
        assertNull(productDAO.findById(saved.getId()));
        productDAO.save(saved);
        assertNotNull(productDAO.findById(saved.getId()));
        productDAO.delete(saved);
        assertNull(productDAO.findById(saved.getId()));
    }
}