package control;

import javafx.beans.property.SimpleStringProperty;
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
import model.entities.Product;
import model.service.ProductService;

import java.io.IOException;

public class ProductsSceneController
{
    // region Services
    private final ProductService productService = new ProductService();
    // endregion
    
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Button addButton;
    @FXML
    private TableView<Product> table;
    private Stage productForm;
    private ProductFormController controller;
    
    public void initialize() {
        initTable();
        update();
        productService.setDataChangedListener(this::update);
        addButton.setOnAction(e -> {
            showCreateProductForm();
            update();
        });
        editButton.setOnAction(event -> {
            Product selectedProduct = table.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                showEditProductForm(selectedProduct);
                update();
            }
        });
        deleteButton.setOnAction(event -> {
            Product selectedProduct = table.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                productService.deleteProduct(selectedProduct);
            }
        });
        table.getParent().visibleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                update();
            }
        });
    }
    
    private void update() {
        table.getItems().setAll(productService.findAll());
    }

    private void initTable() {
        TableColumn<Product, String> name = new TableColumn<>("Наименование");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Product, String> category = new TableColumn<>("Категория");
        category.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getCategory().getName()));
        TableColumn<Product, String> manufacturer = new TableColumn<>("Производитель");
        manufacturer.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getManufacturer().getName()));
        TableColumn<Product, Double> cost = new TableColumn<>("Стоимость");
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        TableColumn<Product, String> description = new TableColumn<>("Описание");
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        table.getColumns().add(name);
        table.getColumns().add(category);
        table.getColumns().add(manufacturer);
        table.getColumns().add(cost);
        table.getColumns().add(description);
    }
    
    private void lazyInitManufacturerForm() {
        if (productForm != null && controller != null) {
            return;
        }
        productForm = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/product_form.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        productForm.setScene(new Scene(root));
        productForm.initModality(Modality.WINDOW_MODAL);
        productForm.initOwner(getStage());
        productForm.setResizable(false);
        controller = loader.getController();
        productForm.setOnShown(event -> controller.updateData());
    }
    
    private void showCreateProductForm() {
        lazyInitManufacturerForm();
        controller.setInitialProduct(new Product());
        controller.setHeadText("Создание (Товары)");
        productForm.setTitle("Создание (Товары)");
        productForm.showAndWait();
    }
    
    private void showEditProductForm(Product product) {
        lazyInitManufacturerForm();
        controller.setInitialProduct(product);
        controller.setHeadText(product.getName());
        productForm.setTitle(product.getName() + " (Категории): Изменение");
        productForm.showAndWait();
    }
    
    private Stage getStage() {
        return ((Stage) table.getScene().getWindow());
    }
}
