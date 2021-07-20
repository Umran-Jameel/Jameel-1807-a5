package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Search {
    @FXML
    TextField textField;
    @FXML
    Button byNameButton;
    @FXML
    Button bySerialButton;

    public void searchByNameClicked(ActionEvent actionEvent) {
        // get the stage/window from the button byNameButton
        // close the window
        // get the text from the textfield
        // loop through the inventory and find the index of the item using the item name string
        // highlight the row of that index in the inventory list
    }

    public void searchBySerialClicked(ActionEvent actionEvent) {
        // get the stage/window from the button bySerialButton
        // close the window
        // get the text from the textfield
        // loop through the inventory and find the index of the item using the item serial number string
        // highlight the row of that index in the inventory list
    }
}
