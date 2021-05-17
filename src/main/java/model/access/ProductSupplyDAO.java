package model.access;

import model.entities.ProductSupply;

public class ProductSupplyDAO extends DAO<ProductSupply>
{
    @Override
    protected Class<ProductSupply> getLoadingClass() {
        return ProductSupply.class;
    }
}
