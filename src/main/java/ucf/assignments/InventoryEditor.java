package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class InventoryEditor {

    public void editItem() {
        // change the info in the array list index
        // get the window/stage from the button
        // close the dialog box
    }

    public void addItem(InventoryList inventoryList, String name, String serial, String money) {
        Item newItem = new Item(name, serial, money); // instantiate the new item
        inventoryList.items.add(newItem); // add the new item
    }

    public boolean isValidName(String name) {
        // criteria for name is that it's between 2 and 256 chars (inclusive)
        if (name.length() < 2 || name.length() > 256) {
            return false;
        }

        return true;
    }

    public boolean isValidSerial(String serial) {
        // serial number should be 10 chars
        if (serial.length() != 10) {
            return false;
        }

        char[] arr = serial.toCharArray(); // make serial string to char array for for loop char by char
        for (int i = 0; i < serial.length(); i++) {
            // if char is not a letter or digit, then it's not a valid serial number
            if (Character.isLetter(arr[i]) || Character.isDigit(arr[i])) {
                continue;
            }
            else {
                return false;
            }
        }

        return true;
    }

    public boolean isValidMoney(String money) {
        try {
            Double value = Double.parseDouble(money); // try to convert the money to double
        } catch (NumberFormatException e){
            return false; // if can't be converted, it's not a valid monetary value
        }

        char[] arr = money.toCharArray(); // money string to char array to check decimal point
        if (arr[money.length() - 3] != '.') { // if there is no decimal point two spaces before end of string, it's not valid
            return false;
        }

        return true;
    }
}
