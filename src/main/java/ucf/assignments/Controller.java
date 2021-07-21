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


    InventoryList inventoryList = new InventoryList();
    InventoryEditor inventoryEditor= new InventoryEditor();

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
        // load the search by serial fxml and show it
        System.out.println(inventoryList.items.get(0).name);
    }

    public void addButtonCLicked(ActionEvent actionEvent) {
    }

    public void editButtonClicked(ActionEvent actionEvent) {
    }

    public void byNameButtonCLicked(ActionEvent actionEvent) {
    }

    public void bySerialButtonClicked(ActionEvent actionEvent) {
    }
}
