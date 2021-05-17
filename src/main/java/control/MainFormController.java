package control;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainFormController
{
    public VBox productsScene;
    public VBox categoriesScene;
    public VBox manufacturersScene;
    
    // region FXML
    @FXML
    private ToggleButton productsButton;
    @FXML
    private ToggleButton storageButton;
    @FXML
    private ToggleButton categoriesButton;
    // endregion
    @FXML
    private ToggleButton manufacturersButton;
    @FXML
    private ToggleButton suppliesButton;
    @FXML
    private ToggleButton providersButton;
    @FXML
    private VBox tableBox;
    
    public void initialize() {
        productsButton.selectedProperty().addListener(event -> switchScene(productsScene));
        categoriesButton.selectedProperty().addListener(event -> switchScene(categoriesScene));
        manufacturersButton.selectedProperty().addListener(event -> switchScene(manufacturersScene));
        ToggleGroup toggleGroup = new ToggleGroup();
        productsButton.setToggleGroup(toggleGroup);
        storageButton.setToggleGroup(toggleGroup);
        categoriesButton.setToggleGroup(toggleGroup);
        manufacturersButton.setToggleGroup(toggleGroup);
        suppliesButton.setToggleGroup(toggleGroup);
        providersButton.setToggleGroup(toggleGroup);
        productsButton.setSelected(true);
    }
    
    private void switchScene(Pane scene) {
        for (Node child : tableBox.getChildren()) {
            child.setVisible(false);
            child.setManaged(false);
        }
        scene.setVisible(true);
        scene.setManaged(true);
    }
    
    private Stage getStage() {
        return ((Stage) tableBox.getScene().getWindow());
    }
}
