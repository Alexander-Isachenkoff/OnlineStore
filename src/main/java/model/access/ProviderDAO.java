package model.access;

import model.entities.Provider;

public class ProviderDAO extends DAO<Provider>
{
    protected Class<Provider> getLoadingClass() {
        return Provider.class;
    }
}
