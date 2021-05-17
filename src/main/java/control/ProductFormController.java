package control;

import control.utils.FocusedListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.entities.Category;
import model.entities.Manufacturer;
import model.entities.Product;
import model.service.CategoryService;
import model.service.ManufacturerService;
import model.service.ProductService;

public class ProductFormController
{
    private final ProductService productService = new ProductService();
    private final CategoryService categoryService = new CategoryService();
    private final ManufacturerService manufacturerService = new ManufacturerService();
    private Product initialProduct = new Product();
    
    // region FXML
    @FXML
    private Label headLabel;
    @FXML
    private TextField nameField;
    @FXML
    private ComboBox<Category> categoryCmb;
    @FXML
    private ComboBox<Manufacturer> manufacturerCmb;
    @FXML
    private Spinner<Double> costSpinner;
    @FXML
    private TextArea descriptionArea;
    // endregion
    
    public void initialize() {
        costSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0,
                Double.MAX_VALUE, 0, 1));
        costSpinner.focusedProperty().addListener(new FocusedListener<>(costSpinner));
        categoryCmb.setConverter(new StringConverter<Category>()
        {
            @Override
            public String toString(Category object) {
                return object.getName();
            }
            
            @Override
            public Category fromString(String string) {
                return null;
            }
        });
        manufacturerCmb.setConverter(new StringConverter<Manufacturer>()
        {
            @Override
            public String toString(Manufacturer object) {
                return object.getName();
            }
            
            @Override
            public Manufacturer fromString(String string) {
                return null;
            }
        });
    }
    
    public void updateData() {
        categoryCmb.getItems().setAll(categoryService.findAll());
        manufacturerCmb.getItems().setAll(manufacturerService.findAll());
    }
    
    private void initProduct() {
        initialProduct.setName(nameField.getText());
        initialProduct.setCategory(categoryCmb.getValue());
        initialProduct.setManufacturer(manufacturerCmb.getValue());
        initialProduct.setCost(costSpinner.getValue());
        initialProduct.setDescription(descriptionArea.getText());
    }
    
    public void setInitialProduct(Product product) {
        initialProduct = product;
        nameField.setText(product.getName());
        categoryCmb.setValue(product.getCategory());
        manufacturerCmb.setValue(product.getManufacturer());
        costSpinner.getValueFactory().setValue(product.getCost());
        descriptionArea.setText(product.getDescription());
        headLabel.setText(product.getName());
    }
    
    public void setHeadText(String text) {
        headLabel.setText(text);
    }
    
    @FXML
    private void onSave() {
        initProduct();
        productService.saveOrUpdate(initialProduct);
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
