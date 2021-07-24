package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InventoryEditorTest {

    InventoryEditor inventoryEditorTest = new InventoryEditor();
    InventoryList inventoryListTest = new InventoryList();

    @Test
    void editItem() {
    }

    @Test
    void addItem() {
        inventoryEditorTest.addItem(inventoryListTest, "test name", "test serial", "test value"); // call the function with details of a test item

        Item testItem = new Item("test name", "test serial", "test value"); // create an item to add to an array list to test
        ArrayList<Item> expected = new ArrayList<Item>();
        expected.add(testItem); // add the test item to the comparison arraylist

        assertEquals(expected.get(0).outputLine, inventoryListTest.items.get(0).outputLine); // output line should be the same for the test item and the function call item
    }
}