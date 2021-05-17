package control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.Manufacturer;
import model.service.ManufacturerService;

public class ManufacturersFormController
{
    private final ManufacturerService manufacturerService = new ManufacturerService();
    private Manufacturer initialManufacturer = new Manufacturer();
    
    // region FXML
    @FXML
    private Label headLabel;
    @FXML
    private TextField nameField;
    // endregion
    
    public void initialize() {
        
    }
    
    public void setInitialManufacturer(Manufacturer manufacturer) {
        initialManufacturer = manufacturer;
        nameField.setText(manufacturer.getName());
        headLabel.setText(manufacturer.getName());
    }
    
    public void setHeadText(String text) {
        headLabel.setText(text);
    }
    
    @FXML
    private void onSave() {
        initialManufacturer.setName(nameField.getText());
        manufacturerService.saveOrUpdate(initialManufacturer);
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
