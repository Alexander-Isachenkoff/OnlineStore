package model.access;

import model.entities.Manufacturer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManufacturerDAOTest
{
    @BeforeEach
    void clearAll() {
        ManufacturerDAO dao = new ManufacturerDAO();
        for (Manufacturer manufacturer : dao.selectAll()) {
            dao.delete(manufacturer);
        }
    }
    
    @Test
    void saveReadTest() {
        ManufacturerDAO dao = new ManufacturerDAO();
        String name = "Rexona";
        Manufacturer saved = new Manufacturer(name);
        dao.save(saved);
        Manufacturer loaded = dao.findById(saved.getId());
        assertEquals(saved, loaded);
    }
    
    @Test
    void updateTest() {
        ManufacturerDAO dao = new ManufacturerDAO();
        
        String name = "Rexona";
        Manufacturer saved = new Manufacturer(name);
        dao.save(saved);
        
        String name2 = "Dove";
        saved.setName(name2);
        dao.update(saved);
        
        Manufacturer loaded = dao.findById(saved.getId());
        assertEquals(name2, loaded.getName());
    }
    
    @Test
    void deleteTest() {
        ManufacturerDAO dao = new ManufacturerDAO();
        Manufacturer saved = new Manufacturer("Rexona");
        assertNull(dao.findById(saved.getId()));
        dao.save(saved);
        assertNotNull(dao.findById(saved.getId()));
        dao.delete(saved);
        assertNull(dao.findById(saved.getId()));
    }
}