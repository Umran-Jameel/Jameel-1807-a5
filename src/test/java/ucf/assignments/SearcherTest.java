package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearcherTest {
    Searcher searcher = new Searcher();
    InventoryList inventoryListTest = new InventoryList();

    @Test
    void searchByName() {
        // test items to add to test arraylist
        Item test1 = new Item("","","");
        Item test2 = new Item("","","");
        Item test3 = new Item("findMe","","");
        Item test4 = new Item("","","");
        inventoryListTest.items.add(test1);
        inventoryListTest.items.add(test2);
        inventoryListTest.items.add(test3);
        inventoryListTest.items.add(test4);

        // index of "findMe" should be 2
        assertEquals(2, searcher.searchByName(inventoryListTest, "findMe"));
    }

    @Test
    void searchBySerial() {
        // test items to add to test arraylist
        Item test1 = new Item("","","");
        Item test2 = new Item("","","");
        Item test3 = new Item("","","");
        Item test4 = new Item("","findMe","");
        inventoryListTest.items.add(test1);
        inventoryListTest.items.add(test2);
        inventoryListTest.items.add(test3);
        inventoryListTest.items.add(test4);

        // index of "findMe" should be 3
        assertEquals(3, searcher.searchBySerial(inventoryListTest, "findMe"));
    }
}