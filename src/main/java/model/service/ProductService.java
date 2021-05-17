package model.service;

import interfaces.DataChangedListener;
import model.access.ProductDAO;
import model.access.ProductOnStorageDAO;
import model.entities.Product;
import model.entities.ProductOnStorage;

import java.util.List;

public class ProductService
{
    private final ProductDAO dao = new ProductDAO();
    private final ProductOnStorageDAO storageDAO = new ProductOnStorageDAO();
    private DataChangedListener dataChangedListener = () -> {};
    
    public void setDataChangedListener(DataChangedListener dataChangedListener) {
        this.dataChangedListener = dataChangedListener;
    }
    
    public Product findProductById(int id) {
        return dao.findById(id);
    }
    
    public void saveProduct(Product product) {
        dao.save(product);
        storageDAO.save(new ProductOnStorage(product, 0));
        dataChangedListener.onDataChanged();
    }
    
    public void updateProduct(Product product) {
        dao.update(product);
        dataChangedListener.onDataChanged();
    }
    
    public void saveOrUpdate(Product product) {
        dao.saveOrUpdate(product);
        dataChangedListener.onDataChanged();
    }
    
    public void deleteProduct(Product product) {
        dao.delete(product);
        dataChangedListener.onDataChanged();
    }
    
    public void addProductsToStorage(Product product, int quantity) {
        ProductOnStorage productOnStorage = storageDAO.findById(product.getId());
        productOnStorage.setQuantity(productOnStorage.getQuantity() + quantity);
        storageDAO.update(productOnStorage);
    }
    
    public void removeProductsFromStorage(Product product, int quantity) {
        ProductOnStorage productOnStorage = storageDAO.findById(product.getId());
        productOnStorage.setQuantity(productOnStorage.getQuantity() - quantity);
        storageDAO.update(productOnStorage);
    }
    
    public int getProductQuantity(Product product) {
        return storageDAO.findById(product.getId()).getQuantity();
    }
    
    public List<Product> findAll() {
        return dao.selectAll();
    }
}
