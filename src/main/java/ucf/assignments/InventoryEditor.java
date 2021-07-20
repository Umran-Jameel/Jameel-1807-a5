package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InventoryEditor {
    @FXML
    Button addButton;
    @FXML
    Button editButton;
    @FXML
    TextField textFieldName;
    @FXML
    TextField textFieldSerial;
    @FXML
    TextField textFieldValue;

    public void editItemClicked(ActionEvent actionEvent) {
        // change the info in the array list index
        // get the window/stage from the button
        // close the dialog box
    }

    public void addItemCLicked(ActionEvent actionEvent) {
        // add the info to the array list
        // get the window/stage from the button
        // close the dialog box
    }
}
