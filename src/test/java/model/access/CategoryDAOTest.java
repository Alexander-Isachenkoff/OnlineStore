package model.access;

import model.entities.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryDAOTest
{
    @BeforeEach
    void clearAll() {
        CategoryDAO dao = new CategoryDAO();
        for (Category category : dao.selectAll()) {
            dao.delete(category);
        }
    }
    
    @Test
    void saveReadTest() {
        CategoryDAO dao = new CategoryDAO();
        String name = "Техника для дома";
        Category savedCategory = new Category(name);
        dao.save(savedCategory);
        Category loadedCategory = dao.findById(savedCategory.getId());
        assertEquals(savedCategory, loadedCategory);
    }
    
    @Test
    void updateTest() {
        CategoryDAO dao = new CategoryDAO();
        
        String name = "Техника для дома";
        Category savedCategory = new Category(name);
        dao.save(savedCategory);
        
        String name2 = "Детские товары";
        savedCategory.setName(name2);
        dao.update(savedCategory);
        
        Category loadedCategory = dao.findById(savedCategory.getId());
        assertEquals(name2, loadedCategory.getName());
    }
    
    @Test
    void deleteTest() {
        CategoryDAO dao = new CategoryDAO();
        Category saved = new Category("Детские товары");
        dao.save(saved);
        assertNotEquals(dao.findById(saved.getId()), null);
        dao.delete(saved);
        assertNull(dao.findById(saved.getId()));
    }
}