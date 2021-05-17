package control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Category;
import model.service.CategoryService;

import java.io.IOException;

public class CategoriesSceneController
{
    // region Services
    private final CategoryService categoryService = new CategoryService();
    // endregion
    
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Button addButton;
    @FXML
    private TableView<Category> table;
    
    private Stage categoryForm;
    private CategoryFormController controller;

    public void initialize() {
        TableColumn<Category, String> name = new TableColumn<>("Наименование");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.getColumns().add(name);
        
        addButton.setOnAction(event -> {
            showCreateCategoryForm();
            update();
        });
        editButton.setOnAction(event -> {
            Category selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                showEditCategoryForm(selected);
                update();
            }
        });
        deleteButton.setOnAction(event -> {
            Category selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                categoryService.deleteCategory(selected);
            }
        });
        update();
        categoryService.setDataChangedListener(this::update);
        table.getParent().visibleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                update();
            }
        });
    }
    
    private void update() {
        table.getItems().setAll(categoryService.findAll());
    }
    
    private void lazyInitCategoryForm() {
        if (categoryForm != null && controller != null) {
            return;
        }
        categoryForm = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/category_form.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        categoryForm.setScene(new Scene(root));
        categoryForm.initModality(Modality.WINDOW_MODAL);
        categoryForm.initOwner(getStage());
        categoryForm.setResizable(false);
        controller = loader.getController();
    }
    
    public void showCreateCategoryForm() {
        lazyInitCategoryForm();
        controller.setInitialCategory(new Category());
        controller.setHeadText("Создание (Категории)");
        categoryForm.setTitle("Создание (Категории)");
        categoryForm.showAndWait();
    }
    
    public void showEditCategoryForm(Category category) {
        lazyInitCategoryForm();
        controller.setInitialCategory(category);
        controller.setHeadText(category.getName());
        categoryForm.setTitle(category.getName() + " (Категории): Изменение");
        categoryForm.showAndWait();
    }
    
    private Stage getStage() {
        return ((Stage) table.getScene().getWindow());
    }
}
