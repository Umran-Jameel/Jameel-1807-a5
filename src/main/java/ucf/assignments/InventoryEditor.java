package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class InventoryEditor {

    public Validator validator = new Validator();

    public void editItem() {
        // change the info in the array list index
        // get the window/stage from the button
        // close the dialog box
    }

    public void addItem(InventoryList inventoryList, String name, String serial, String money) {
        Item newItem = new Item(name, serial, money); // instantiate the new item
        inventoryList.items.add(newItem); // add the new item
    }

}
