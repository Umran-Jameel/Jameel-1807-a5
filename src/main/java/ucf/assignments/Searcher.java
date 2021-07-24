package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Searcher {

    public int searchByName(InventoryList inventoryList, String name) {
        // loop through each element to find the index of that item name and return the index
        for (int i = 0; i < inventoryList.items.size(); i++) {
            if (name.equals(inventoryList.items.get(i).name)) {
                return i;
            }
        }

        return -1; // return -1 if it doesn't exist
    }

    public int searchBySerial(InventoryList inventoryList, String serial) {
        // loop through each element to find the index of that item name and return the index
        for (int i = 0; i < inventoryList.items.size(); i++) {
            if (serial.equals(inventoryList.items.get(i).serialNumber)) {
                return i;
            }
        }

        return -1; // return -1 if it doesn't exist
    }
}
