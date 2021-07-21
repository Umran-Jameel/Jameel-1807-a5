package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    static InventoryList inventoryList = new InventoryList();

    @FXML
    TableView tableView;

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

    @FXML
    TextField textField;
    @FXML
    Button byNameButton;
    @FXML
    Button bySerialButton;

    InventoryEditor inventoryEditor = new InventoryEditor();

    public Controller() {

    }

    public void saveTSVClicked(ActionEvent actionEvent) {
        // File chooser to get the file name and path from the user
        // Write the info into the tsv file using /t and save
    }

    public void saveHTMLClicked(ActionEvent actionEvent) {

    }

    public void loadTSVClicked(ActionEvent actionEvent) {
        // read all the info and put it into the array list
    }

    public void loadHTMLClicked(ActionEvent actionEvent) {
    }

    public void addItemClicked(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/ucf/assignments/AddItem.fxml")); // load fxml file to add item

        // set the scene and show it
        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Add Item");
        primaryStage.show();
    }

    public void addButtonCLicked(ActionEvent actionEvent) {
        Stage stage = (Stage) addButton.getScene().getWindow(); // getting access to the stage via the button

        String name = textFieldName.getText(); // get the item name stringfrom the textfield for the name
        String serial = textFieldSerial.getText(); // get the serial number string from the textfield for the serial number
        String value = textFieldValue.getText(); // get the value string from the textfield for the value

        // if the user-provided name is not valid, let the user know
        if (!inventoryEditor.isValidName(name)) {
            stage.setTitle("Item name must be between 2 and 256 characters in length (inclusive)!");
        }
        else if (!inventoryEditor.isValidSerial(serial)) { // if the user-provided serial number is not valid, let the user know
            stage.setTitle("Serial # must be in format XXXXXXXXXX (letters and/or digits)!");
        }
        else if (!inventoryEditor.isValidMoney(value)) { // if the user-provided value is not valid, let the user know
            stage.setTitle("Value must represent a valid monetary value in US Dollars!");
        }
        else { // if everything is valid
            stage.close(); // close the dialog for user input
            inventoryEditor.addItem(inventoryList, name, serial, value); // add the new item to the list
        }
    }

    public void removeItemClicked(ActionEvent actionEvent) {
        // remove the selected item on the tableview
    }

    public void editItemClicked(ActionEvent actionEvent) {
        // load the edit item fxml page and show the stage
        // put the current info on the dialog box
    }

    public void searchByNameClicked(ActionEvent actionEvent) {
        // load the search by name fxml and show it
    }

    public void searchBySerialClicked(ActionEvent actionEvent) {

    }

    public void editButtonClicked(ActionEvent actionEvent) {

    }

    public void byNameButtonCLicked(ActionEvent actionEvent) {
    }

    public void bySerialButtonClicked(ActionEvent actionEvent) {
    }
}
