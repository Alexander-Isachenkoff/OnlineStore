package model.service;

import model.access.ProviderDAO;
import model.entities.Provider;

public class ProviderService
{
    private final ProviderDAO dao = new ProviderDAO();
    
    public Provider findProviderById(int id) {
        return dao.findById(id);
    }
    
    public void saveProvider(Provider provider) {
        dao.save(provider);
    }
    
    public void updateProvider(Provider provider) {
        dao.update(provider);
    }
    
    public void deleteProvider(Provider provider) {
        dao.delete(provider);
    }
}
