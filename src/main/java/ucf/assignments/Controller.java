package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Controller {
    static InventoryList inventoryList = new InventoryList();
    InventoryEditor inventoryEditor = new InventoryEditor();
    SceneMaker sceneMaker = new SceneMaker();
    Sorter sorter = new Sorter();
    Searcher searcher = new Searcher();
    SaveAndLoader saveAndLoader = new SaveAndLoader();

    @FXML
    TableView tableView;

    public Controller() {
        // constructor for the class
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
            else if (inventoryEditor.validator.isValidSerial(serial.toUpperCase(), "", Controller.inventoryList) == 0) { // if the user-provided serial number is not valid, let the user know
                addItemStage.setTitle("Serial # must be in format XXXXXXXXXX (letters and/or digits)!");
            }
            else if (inventoryEditor.validator.isValidSerial(serial.toUpperCase(), "", Controller.inventoryList) == 1) { // if the serial number already exists
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

    public void removeItemClicked(ActionEvent actionEvent) {
        int index = tableView.getSelectionModel().getSelectedIndex(); // get the selected item
        inventoryEditor.removeItem(Controller.inventoryList, index); // remove the item
        updateTable(); // update the table
    }



    public void editItemClicked(ActionEvent actionEvent) {
        int index = tableView.getSelectionModel().getSelectedIndex();

        // Elements of the edit item stage
        Button updateButton = new Button("Update Item");
        TextField textFieldName = new TextField();
        TextField textFieldSerial = new TextField();
        TextField textFieldValue = new TextField();

        // set the textfields to have the existing attributes of the item
        textFieldName.setText(Controller.inventoryList.items.get(index).name);
        textFieldSerial.setText(Controller.inventoryList.items.get(index).serialNumber);
        textFieldValue.setText(Controller.inventoryList.items.get(index).value);

        // create the stage and add the elements to that stage
        Stage editItemStage = sceneMaker.makeScene(600, 170, textFieldName, textFieldSerial, textFieldValue, updateButton);
        editItemStage.setTitle("Edit Item");
        editItemStage.show();

        updateButton.setOnAction(e -> {
            // get the user input of the item's attributes
            String name = textFieldName.getText();
            String serial = textFieldSerial.getText();
            String value = textFieldValue.getText();

            if (!inventoryEditor.validator.isValidName(name)) { // if the item's name is not valid, let the user know and don't edit
                editItemStage.setTitle("Item name must be between 2 and 256 characters in length (inclusive)!");
            } else if (inventoryEditor.validator.isValidSerial(serial.toUpperCase(), Controller.inventoryList.items.get(index).serialNumber, Controller.inventoryList) == 0) { // if the user-provided serial number is not valid, let the user know
                editItemStage.setTitle("Serial # must be in format XXXXXXXXXX (letters and/or digits)!");
            } else if (inventoryEditor.validator.isValidSerial(serial.toUpperCase(), Controller.inventoryList.items.get(index).serialNumber, Controller.inventoryList) == 1) { // if duplicate serial number
                editItemStage.setTitle("There is already an item with that serial number!");
            } else if (!inventoryEditor.validator.isValidMoney(value)) { // if the user-provided value is not valid, let the user know
                editItemStage.setTitle("Value must represent a valid monetary value in US Dollars!");
            } else { // if everything is valid, edit the item and close the window
                inventoryEditor.editItem(Controller.inventoryList, name, serial.toUpperCase(), value, index); // add the new item to the list
                editItemStage.close();
                updateTable();
            }
        });

    }

    public void sortByValueClicked(ActionEvent actionEvent) {
        sorter.sortByValue(Controller.inventoryList); // sort the list by value
        updateTable(); // update the table
    }

    public void sortBySerialClicked(ActionEvent actionEvent) {
        sorter.sortBySerialNumber(Controller.inventoryList); // sort the list by serial number
        updateTable(); // update the table
    }

    public void sortByNameClicked(ActionEvent actionEvent) {
        sorter.sortByName(Controller.inventoryList); // sort the list by name
        updateTable(); // update the table
    }

    public void searchByNameClicked(ActionEvent actionEvent) {
        // elements of the search by name dialogue
        Button search = new Button("Search");
        TextField textFieldByName = new TextField();
        textFieldByName.setPromptText("Item Name...");

        // create the stage
        Stage searchByNameStage = sceneMaker.makeScene(500, 100, textFieldByName, search);
        searchByNameStage.setTitle("Search By Name");
        searchByNameStage.show();

        search.setOnAction(e -> {
            int index = searcher.searchByName(Controller.inventoryList, textFieldByName.getText()); // get index of the item being searched
            // if the item doesn't exist, let the user know
            if (index == -1) {
                searchByNameStage.setTitle("That item does not exist!");
            }
            else {
                searchByNameStage.close();
                tableView.getSelectionModel().select(index); // highlight the index of the item
            }
        });
    }

    public void searchBySerialClicked(ActionEvent actionEvent) {
        // elements of the search by name dialogue
        Button search = new Button("Search");
        TextField textFieldByName = new TextField();
        textFieldByName.setPromptText("Serial Number...");

        // create the stage
        Stage searchBySerialStage = sceneMaker.makeScene(500, 100, textFieldByName, search);
        searchBySerialStage.setTitle("Search By Serial Number");
        searchBySerialStage.show();

        search.setOnAction(e -> {
            int index = searcher.searchBySerial(Controller.inventoryList, textFieldByName.getText()); // get index of the item being searched
            // if the item doesn't exist, let the user know
            if (index == -1) {
                searchBySerialStage.setTitle("There is no item with that serial number!");
            }
            else {
                searchBySerialStage.close();
                tableView.getSelectionModel().select(index); // highlight the index of the item
            }
        });
    }

    public void saveTSVClicked(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage(); // new stage for the filechooser dialogue

        // file chooser for getting the file name and directory from the user
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as TSV");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Tab-Separated Value", "*.txt")); // TSV can be saved only in .txt format

        File tsvFile = fileChooser.showSaveDialog(stage); // getting the file pointer
        saveAndLoader.saveTSV(Controller.inventoryList, tsvFile); // save the contents of the inventory list to a tsv file
    }

    public void saveHTMLClicked(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage(); // new stage for the filechooser dialogue

        // file chooser for getting the file name and directory from the user
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as HTML");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML", "*.html")); // HTML saved in html format only

        File htmlFile = fileChooser.showSaveDialog(stage); // getting the file pointer
        saveAndLoader.saveHTML(Controller.inventoryList, htmlFile); // save the contents of the inventory list to an html file
    }

    public void loadTSVClicked(ActionEvent actionEvent) throws FileNotFoundException {
        Stage stage = new Stage(); // stage for the filechooser

        // file chooser for the user to provide the file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load TSV file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Tab-Separated Value", "*.txt"));
        File tsvFile = fileChooser.showOpenDialog(stage);

        saveAndLoader.loadTSV(Controller.inventoryList, tsvFile); // load the tsv file
        updateTable();
    }

    public void loadHTMLClicked(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage(); // stage for the filechooser dialogue

        // file chooser for the user to provide the file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load HTML file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML", "*.html"));
        File htmlFile = fileChooser.showOpenDialog(stage);

        saveAndLoader.loadHTML(Controller.inventoryList, htmlFile); // load the content on the html file
        updateTable(); // update the table
    }

    public void updateTable() {
        // clear the current content of the table
        tableView.getItems().clear();
        tableView.getColumns().clear();

        // adding value, serial number, and name columns to the tableview
        TableColumn value = new TableColumn("Value");
        TableColumn serialNumber = new TableColumn("Serial #");
        TableColumn name = new TableColumn("Name");
        tableView.getColumns().addAll(value, serialNumber, name);

        // observable list to add to the table
        ObservableList<Item> content = FXCollections.observableArrayList();
        content.addAll(Controller.inventoryList.items);

        // associating the columns with the content of the list
        value.setCellValueFactory(new PropertyValueFactory<Item, String>("value"));
        serialNumber.setCellValueFactory(new PropertyValueFactory<Item, String>("serialNumber"));
        name.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));

        tableView.setItems(content);
    }

}
