package model.service;

import model.access.SupplyDAO;
import model.entities.Product;
import model.entities.ProductSupply;
import model.entities.Supply;

public class SupplyService
{
    private final SupplyDAO dao = new SupplyDAO();
    private final ProductService productService = new ProductService();
    
    public Supply findSupplyById(int id) {
        return dao.findById(id);
    }
    
    public void saveSupply(Supply supply) {
        dao.save(supply);
        performSupply(supply);
    }
    
    public void updateSupply(Supply supply) {
        Supply oldSupply = dao.findById(supply.getId());
        rollBackSupply(oldSupply);
        performSupply(supply);
        dao.update(supply);
    }
    
    public void deleteSupply(Supply supply) {
        rollBackSupply(supply);
        dao.delete(supply);
    }
    
    private void rollBackSupply(Supply supply) {
        for (ProductSupply productSupply : supply.getProductSupplies()) {
            Product product = productSupply.getProduct();
            int quantity = productSupply.getQuantity();
            productService.removeProductsFromStorage(product, quantity);
        }
    }
    
    private void performSupply(Supply supply) {
        for (ProductSupply productSupply : supply.getProductSupplies()) {
            Product product = productSupply.getProduct();
            int quantity = productSupply.getQuantity();
            productService.addProductsToStorage(product, quantity);
        }
    }
}
