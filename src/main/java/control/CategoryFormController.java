package control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.Category;
import model.service.CategoryService;

public class CategoryFormController
{
    private final CategoryService categoryService = new CategoryService();
    private Category initialCategory = new Category();
    
    // region FXML
    @FXML
    private Label headLabel;
    @FXML
    private TextField nameField;
    // endregion
    
    public void initialize() {
    
    }
    
    public void setInitialCategory(Category category) {
        initialCategory = category;
        nameField.setText(category.getName());
        headLabel.setText(category.getName());
    }
    
    public void setHeadText(String text) {
        headLabel.setText(text);
    }
    
    @FXML
    private void onSave() {
        initialCategory.setName(nameField.getText());
        categoryService.saveOrUpdate(initialCategory);
        close();
    }
    
    @FXML
    private void onCancel() {
        close();
    }
    
    private void close() {
        getStage().close();
    }
    
    private Stage getStage() {
        return ((Stage) nameField.getScene().getWindow());
    }
}
