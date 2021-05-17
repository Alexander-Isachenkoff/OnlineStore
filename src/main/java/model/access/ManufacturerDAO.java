package model.access;

import model.entities.Manufacturer;

public class ManufacturerDAO extends DAO<Manufacturer>
{
    protected Class<Manufacturer> getLoadingClass() {
        return Manufacturer.class;
    }
}
