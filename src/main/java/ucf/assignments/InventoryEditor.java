package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class InventoryEditor {
    public Validator validator = new Validator();

    public void addItem(InventoryList inventoryList, String name, String serial, String money) {
        Item newItem = new Item(name, serial, money); // instantiate the new item
        inventoryList.items.add(newItem); // add the new item
    }

    public void removeItem(InventoryList inventoryList, int index) {
        inventoryList.items.remove(index); // remove the item at that index
    }

    public void editItem(InventoryList inventoryList, String name, String serial, String value, int index) {
        inventoryList.items.remove(index); // remove the item so it can be readded with new attributes

        Item editedItem = new Item(name, serial, value);
        inventoryList.items.add(index, editedItem);
    }


}
