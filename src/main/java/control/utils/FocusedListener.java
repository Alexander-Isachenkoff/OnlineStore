package control.utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.StringConverter;

public class FocusedListener<T> implements ChangeListener<Boolean>
{
    private final Spinner<T> spinner;
    
    public FocusedListener(Spinner<T> spinner) {
        this.spinner = spinner;
    }
    
    private void updateEditor() {
        SpinnerValueFactory<T> valueFactory = spinner.getValueFactory();
        StringConverter<T> converter = valueFactory.getConverter();
        T newValue;
        try {
            newValue = converter.fromString(spinner.getEditor().getText());
        } catch (NumberFormatException ex) {
            newValue = spinner.getValue();
        }
        valueFactory.setValue(newValue);
        spinner.getEditor().setText(converter.toString(newValue));
    }
    
    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        updateEditor();
    }
}
