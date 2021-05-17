package model.access;

import model.entities.Provider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProviderDAOTest
{
    @BeforeEach
    void clearAll() {
        ProviderDAO dao = new ProviderDAO();
        for (Provider provider : dao.selectAll()) {
            dao.delete(provider);
        }
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
        ProviderDAO dao = new ProviderDAO();
        Provider saved = createExampleProvider();
        dao.save(saved);
        Provider loaded = dao.findById(saved.getId());
        assertEquals(saved, loaded);
    }
    
    @Test
    void update() {
        ProviderDAO dao = new ProviderDAO();
        Provider saved = createExampleProvider();
        dao.save(saved);
        saved.setINN("ИРГУПС");
        saved.setName("0000000");
        saved.setAddress("ул. Ярославского, д.258");
        saved.setEmail("sample@yandex.ru");
        saved.setOGRN("123879546");
        saved.setPhoneNumber("84-98-14");
        dao.update(saved);
        Provider loaded = dao.findById(saved.getId());
        assertEquals(saved, loaded);
    }
    
    @Test
    void delete() {
        ProviderDAO dao = new ProviderDAO();
        Provider saved = createExampleProvider();
        assertNull(dao.findById(saved.getId()));
        dao.save(saved);
        assertNotNull(dao.findById(saved.getId()));
        dao.delete(saved);
        assertNull(dao.findById(saved.getId()));
    }
}