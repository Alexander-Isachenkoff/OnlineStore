package model.access;

import model.entities.Product;

public class ProductDAO extends DAO<Product>
{
    protected Class<Product> getLoadingClass() {
        return Product.class;
    }
}
