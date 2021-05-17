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
import model.entities.Manufacturer;
import model.service.ManufacturerService;

import java.io.IOException;

public class ManufacturersSceneController
{
    // region Services
    private final ManufacturerService service = new ManufacturerService();
    // endregion
    
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Button addButton;
    @FXML
    private TableView<Manufacturer> table;
    
    private Stage manufacturerForm;
    private ManufacturersFormController controller;
    
    public void initialize() {
        TableColumn<Manufacturer, String> name = new TableColumn<>("Наименование");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.getColumns().add(name);
        
        addButton.setOnAction(event -> {
            showCreateManufacturerForm();
            update();
        });
        
        editButton.setOnAction(event -> {
            Manufacturer selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                showEditManufacturerForm(selected);
                update();
            }
        });
        
        deleteButton.setOnAction(event -> {
            Manufacturer selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                service.deleteManufacturer(selected);
            }
        });
        update();
        service.setDataChangedListener(this::update);
        table.getParent().visibleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                update();
            }
        });
    }
    
    private void update() {
        table.getItems().setAll(service.findAll());
    }
    
    private void lazyInitManufacturerForm() {
        if (manufacturerForm != null && controller != null) {
            return;
        }
        manufacturerForm = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/manufacturer_form.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        manufacturerForm.setScene(new Scene(root));
        manufacturerForm.initModality(Modality.WINDOW_MODAL);
        manufacturerForm.initOwner(getStage());
        manufacturerForm.setResizable(false);
        controller = loader.getController();
    }
    
    public void showCreateManufacturerForm() {
        lazyInitManufacturerForm();
        controller.setInitialManufacturer(new Manufacturer());
        controller.setHeadText("Создание (Производители)");
        manufacturerForm.setTitle("Создание (Производители)");
        manufacturerForm.showAndWait();
    }
    
    public void showEditManufacturerForm(Manufacturer manufacturer) {
        lazyInitManufacturerForm();
        controller.setInitialManufacturer(manufacturer);
        controller.setHeadText(manufacturer.getName());
        manufacturerForm.setTitle(manufacturer.getName() + " (Производители): Изменение");
        manufacturerForm.showAndWait();
    }
    
    private Stage getStage() {
        return ((Stage) table.getScene().getWindow());
    }
}
