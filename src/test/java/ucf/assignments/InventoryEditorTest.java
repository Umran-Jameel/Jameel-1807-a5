package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InventoryEditorTest {

    InventoryEditor inventoryEditorTest = new InventoryEditor();
    InventoryList inventoryListTest = new InventoryList();

    @Test
    void addItem() {
        inventoryEditorTest.addItem(inventoryListTest, "test name", "test serial", "test value"); // call the function with details of a test item

        Item testItem = new Item("test name", "test serial", "test value"); // create an item to add to an array list to test
        ArrayList<Item> expected = new ArrayList<Item>();
        expected.add(testItem); // add the test item to the comparison arraylist

        assertEquals(expected.get(0).outputLine, inventoryListTest.items.get(0).outputLine); // output line should be the same for the test item and the function call item
    }

    @Test
    void removeItem() {
        Item testItem = new Item("test name", "test serial", "test value"); // test item to be removed
        inventoryListTest.items.add(testItem); // add test item

        inventoryEditorTest.removeItem(inventoryListTest, 0);

        assertEquals(0, inventoryListTest.items.size()); // expected size should be 0 after removing
    }

    @Test
    void editItem() {
        Item testItem = new Item("test name", "test serial", "test value"); // test item to be edited
        inventoryListTest.items.add(testItem); // add test item

        String editedName = "edited name";
        String editedSerial = "edited serial";
        String editedValue = "edited value";

        inventoryEditorTest.editItem(inventoryListTest, editedName, editedSerial, editedValue, 0); // call edit function with edited attributes and index as parameters

        assertEquals("edited name", inventoryListTest.items.get(0).name); // expected edited name vs actual name after function call
        assertEquals("edited serial", inventoryListTest.items.get(0).serialNumber); // expected edited serial vs actual serial after function call
        assertEquals("edited value", inventoryListTest.items.get(0).value); // expected edited value vs actual value after function call
    }


}