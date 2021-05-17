package model.service;

import interfaces.DataChangedListener;
import model.access.CategoryDAO;
import model.entities.Category;

import java.util.List;

public class CategoryService
{
    private final CategoryDAO dao = new CategoryDAO();
    
    private DataChangedListener dataChangedListener = () -> {};
    
    public void setDataChangedListener(DataChangedListener dataChangedListener) {
        this.dataChangedListener = dataChangedListener;
    }
    
    public Category findCategoryById(int id) {
        return dao.findById(id);
    }
    
    public void saveCategory(Category category) {
        dao.save(category);
        dataChangedListener.onDataChanged();
    }
    
    public void saveOrUpdate(Category category) {
        dao.saveOrUpdate(category);
        dataChangedListener.onDataChanged();
    }
    
    public void updateCategory(Category category) {
        dao.update(category);
        dataChangedListener.onDataChanged();
    }
    
    public void deleteCategory(Category category) {
        dao.delete(category);
        dataChangedListener.onDataChanged();
    }
    
    public List<Category> findAll() {
        return dao.selectAll();
    }
}
