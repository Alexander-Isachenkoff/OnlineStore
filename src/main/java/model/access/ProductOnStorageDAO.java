package model.access;

import model.entities.ProductOnStorage;

public class ProductOnStorageDAO extends DAO<ProductOnStorage>
{
    @Override
    protected Class<ProductOnStorage> getLoadingClass() {
        return ProductOnStorage.class;
    }
}
