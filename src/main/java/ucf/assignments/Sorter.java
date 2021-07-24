package ucf.assignments;

import java.util.Collections;
import java.util.Comparator;

public class Sorter {
    public void sortByValue(InventoryList inventoryList) {
        Collections.sort(inventoryList.items, Comparator.comparing(Item::getValue)); // sort the list by value
    }

    public void sortBySerialNumber(InventoryList inventoryList) {
        Collections.sort(inventoryList.items, Comparator.comparing(Item::getSerialNumber)); // sort the list by serial number
    }

    public void sortByName(InventoryList inventoryList) {
        Collections.sort(inventoryList.items, Comparator.comparing(Item::getName)); // sort the list by name
    }
}
