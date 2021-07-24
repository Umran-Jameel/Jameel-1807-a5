package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SorterTest {
    Sorter sorterTest = new Sorter();
    InventoryList inventoryListTest = new InventoryList();

    @Test
    void sortByValue() {
        // make new items with different values to sort
        Item item2 = new Item("", "", "2");
        Item item4 = new Item("", "", "4");
        Item item3 = new Item("", "", "3");
        Item item1= new Item("", "", "1");

        // add the items to the list in a messed up order
        inventoryListTest.items.add(item2);
        inventoryListTest.items.add(item4);
        inventoryListTest.items.add(item3);
        inventoryListTest.items.add(item1);

        sorterTest.sortByValue(inventoryListTest); // call the sort function
        assertEquals(0, inventoryListTest.items.indexOf(item1)); // item 1 should have index 0 after sorting
    }

    @Test
    void sortBySerialNumber() {
        // make new items with different serial number values to sort
        Item item2 = new Item("", "2345678901", "");
        Item item4 = new Item("", "4567890123", "");
        Item item3 = new Item("", "1234567890", "");
        Item item1= new Item("", "3456789012", "");

        // add the items to the list in a messed up order
        inventoryListTest.items.add(item2);
        inventoryListTest.items.add(item4);
        inventoryListTest.items.add(item3);
        inventoryListTest.items.add(item1);

        sorterTest.sortBySerialNumber(inventoryListTest); // call the sort function
        assertEquals(2, inventoryListTest.items.indexOf(item1)); // item 1 should have index 2 after sorting
    }

    @Test
    void sortByName() {
        // make new items with different names to sort
        Item item2 = new Item("ddddd", "", "");
        Item item4 = new Item("aaaa", "", "");
        Item item3 = new Item("ccccc", "", "");
        Item item1= new Item("bbbbb", "", "");

        // add the items to the list in a messed up order
        inventoryListTest.items.add(item2);
        inventoryListTest.items.add(item4);
        inventoryListTest.items.add(item3);
        inventoryListTest.items.add(item1);

        sorterTest.sortByName(inventoryListTest); // call the sort function
        assertEquals(1, inventoryListTest.items.indexOf(item1)); // item 1 should have index 1 after sorting
    }
}