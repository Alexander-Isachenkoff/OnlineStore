package model.access;

import model.entities.Category;

public class CategoryDAO extends DAO<Category>
{
    protected Class<Category> getLoadingClass() {
        return Category.class;
    }
}
