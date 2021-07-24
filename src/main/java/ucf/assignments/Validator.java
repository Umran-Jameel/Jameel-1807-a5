package ucf.assignments;

import java.util.Locale;

public class Validator {
    public boolean isValidName(String name) {
        // criteria for name is that it's between 2 and 256 chars (inclusive)
        if (name.length() < 2 || name.length() > 256) {
            return false;
        }

        return true;
    }

    public int isValidSerial(String serial, InventoryList inventoryList) {
        // serial number should be 10 chars
        if (serial.length() != 10) {
            return 0;
        }

        char[] arr = serial.toCharArray(); // make serial string to char array for for loop char by char
        for (int i = 0; i < serial.length(); i++) {
            // if char is not a letter or digit, then it's not a valid serial number
            if (Character.isLetter(arr[i]) || Character.isDigit(arr[i])) {
                continue;
            }
            else {
                return 0;
            }
        }

        // if it is a duplicate serial number, it's invalid
        for (int i = 0; i < inventoryList.items.size(); i++) {
            if (serial.equals(inventoryList.items.get(i).serialNumber)) {
                return 1; // we return 1 to display a different error message
            }
        }

        return 2; // 2 means it's valid
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
