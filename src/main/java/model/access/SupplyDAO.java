package model.access;

import model.entities.Supply;

public class SupplyDAO extends DAO<Supply>
{
    @Override
    protected Class<Supply> getLoadingClass() {
        return Supply.class;
    }
}
