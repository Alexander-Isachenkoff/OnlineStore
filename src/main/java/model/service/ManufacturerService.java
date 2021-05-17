package model.service;

import interfaces.DataChangedListener;
import model.access.ManufacturerDAO;
import model.entities.Manufacturer;

import java.util.List;

public class ManufacturerService
{
    private final ManufacturerDAO dao = new ManufacturerDAO();
    private DataChangedListener dataChangedListener = () -> {};
    
    public void setDataChangedListener(DataChangedListener dataChangedListener) {
        this.dataChangedListener = dataChangedListener;
    }
    
    public Manufacturer findManufacturerById(int id) {
        return dao.findById(id);
    }
    
    public void saveManufacturer(Manufacturer manufacturer) {
        dao.save(manufacturer);
        dataChangedListener.onDataChanged();
    }
    
    public void updateManufacturer(Manufacturer manufacturer) {
        dao.update(manufacturer);
        dataChangedListener.onDataChanged();
    }
    
    public void saveOrUpdate(Manufacturer manufacturer) {
        dao.saveOrUpdate(manufacturer);
        dataChangedListener.onDataChanged();
    }
    
    public void deleteManufacturer(Manufacturer manufacturer) {
        dao.delete(manufacturer);
        dataChangedListener.onDataChanged();
    }
    
    public List<Manufacturer> findAll() {
        return dao.selectAll();
    }
}
