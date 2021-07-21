package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InventoryEditor {

    public void editItem() {
        // change the info in the array list index
        // get the window/stage from the button
        // close the dialog box
    }

    public void addItem() {
        // add the info to the array list
        // get the window/stage from the button
        // close the dialog box

        /*
        Stage stage = (Stage) addButton.getScene().getWindow();

        String name = textFieldName.getText();
        String serial = textFieldSerial.getText();
        String value = textFieldValue.getText();

        if (name.length() < 2 || name.length() > 256) {
            stage.setTitle("Item name must be between 2 and 256 characters in length (inclusive)!");
        }
        else if (!isValidSerial(serial)) {
            stage.setTitle("Serial # must be in format XXXXXXXXXX (letters and/or digits)!");
        }
        else if (!isValidMoney(value)) {
            stage.setTitle("Value must represent a valid monetary value in US Dollars!");
        }
        else {
            stage.close();
        }
*/
    }

    private boolean isValidSerial(String serial) {
        if (serial.length() != 10) {
            return false;
        }

        char[] arr = serial.toCharArray();

        for (int i = 0; i < serial.length(); i++) {
            if (Character.isLetter(arr[i]) || Character.isDigit(arr[i])) {
                continue;
            }
            else {
                return false;
            }
        }

        return true;
    }

    private boolean isValidMoney(String money) {
        try {
            Double value = Double.parseDouble(money);
        } catch (NumberFormatException e){
            return false;
        }

        char[] arr = money.toCharArray();

        if (arr[money.length() - 3] != '.') {
            return false;
        }

        return true;
    }
}
