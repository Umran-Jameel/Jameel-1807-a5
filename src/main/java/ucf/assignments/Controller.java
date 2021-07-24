package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class Controller {
    SceneMaker sceneMaker = new SceneMaker();
    static InventoryList inventoryList = new InventoryList();
    InventoryEditor inventoryEditor = new InventoryEditor();

    @FXML
    ListView<String> listView = new ListView<String>();

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

    public void addItemClicked(ActionEvent actionEvent) {
        // Elements of the add item stage
        Button addButton = new Button("Add");
        TextField textFieldName = new TextField();
        TextField textFieldSerial = new TextField();
        TextField textFieldValue = new TextField();

        // prompt the user to enter the item name, serial number, and value
        textFieldName.setPromptText("Item Name...");
        textFieldSerial.setPromptText("Serial Number...");
        textFieldValue.setPromptText("Value (USD)...");

        // create the stage and add the elements to that stage
        Stage addItemStage = sceneMaker.makeScene(600, 170, textFieldName, textFieldSerial, textFieldValue, addButton);
        addItemStage.setTitle("Add Item");
        addItemStage.show();

        addButton.setOnAction(e -> {
            // get the user input of the item's attributes
            String name = textFieldName.getText();
            String serial = textFieldSerial.getText();
            String value = textFieldValue.getText();

            if (!inventoryEditor.validator.isValidName(name)) { // if the item's name is not valid, let the user know and don't add
                addItemStage.setTitle("Item name must be between 2 and 256 characters in length (inclusive)!");
            }
            else if (inventoryEditor.validator.isValidSerial(serial.toUpperCase(), Controller.inventoryList) == 0) { // if the user-provided serial number is not valid, let the user know
                addItemStage.setTitle("Serial # must be in format XXXXXXXXXX (letters and/or digits)!");
            }
            else if (inventoryEditor.validator.isValidSerial(serial.toUpperCase(), Controller.inventoryList) == 1) { // if the serial number already exists
                addItemStage.setTitle("There is already an item with that serial number!");
            }
            else if (!inventoryEditor.validator.isValidMoney(value)) { // if the user-provided value is not valid, let the user know
                addItemStage.setTitle("Value must represent a valid monetary value in US Dollars!");
            }
            else { // if everything is valid, add to the list and close the window
                inventoryEditor.addItem(Controller.inventoryList, name, serial.toUpperCase(), value); // add the new item to the list
                addItemStage.close();
                updateTable();
            }
        });
    }

    public void editItemClicked(ActionEvent actionEvent) {
    }


    public void searchByNameClicked(ActionEvent actionEvent) {
        // load the search by name fxml and show it
    }

    public void searchBySerialClicked(ActionEvent actionEvent) {
    }


    public void sortByValueClicked(ActionEvent actionEvent) {
    }

    public void sortBySerialClicked(ActionEvent actionEvent) {
    }

    public void sortByNameClicked(ActionEvent actionEvent) {
    }

    public void updateTable() {
        listView.getItems().clear(); // clear the list view to add again

        // add all the elements to the listview
        for (int i = 0; i < Controller.inventoryList.items.size(); i++) {
            listView.getItems().add(Controller.inventoryList.items.get(i).outputLine);
        }
    }


}
