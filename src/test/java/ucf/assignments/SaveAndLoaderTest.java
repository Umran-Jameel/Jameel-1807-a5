package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class SaveAndLoaderTest {

    SaveAndLoader saveAndLoader = new SaveAndLoader();

    @Test
    void saveTSV() throws IOException {
        // create a new test file in the project directory to test the save tsv method
        File testFile = new File("/Users/umranrazack02/IdeaProjects/Application Assignment 2/src/test/resources/ucf/assignments/tsvSaveTest.txt");
        Scanner read = new Scanner(testFile);

        // create new items to add to the list
        InventoryList inventoryList = new InventoryList();
        Item test1 = new Item("name1","serial1","value1");
        Item test2 = new Item("name2","serial2","value2");
        Item test3 = new Item("name3","serial3","value3");
        Item test4 = new Item("name4","serial4","value4");
        inventoryList.items.add(test1);
        inventoryList.items.add(test2);
        inventoryList.items.add(test3);
        inventoryList.items.add(test4);

        saveAndLoader.saveTSV(inventoryList, testFile); // save the tsv file

        // going to the third line to verify the expected result
        String thirdLine = read.nextLine();
        thirdLine = read.nextLine();
        thirdLine = read.nextLine();

        String expected = String.format("$%s\t%s\t%s", test2.value, test2.serialNumber, test2.name); // expected string for comparison

        assertEquals(expected, thirdLine); // third line of saved file should be the same as the results
    }

    @Test
    void saveHTML() throws IOException {
        // create a new test file in the project directory to test the save html method
        File testFile = new File("/Users/umranrazack02/IdeaProjects/Application Assignment 2/src/test/resources/ucf/assignments/htmlSaveTest.html");
        FileInputStream fileInputStream = new FileInputStream(testFile.getAbsolutePath());

        // make new test items and add the to list
        InventoryList inventoryList = new InventoryList();
        Item test1 = new Item("name1","serial1","value1");
        Item test2 = new Item("name2","serial2","value2");
        Item test3 = new Item("name3","serial3","value3");
        Item test4 = new Item("name4","serial4","value4");
        inventoryList.items.add(test1);
        inventoryList.items.add(test2);
        inventoryList.items.add(test3);
        inventoryList.items.add(test4);


        saveAndLoader.saveHTML(inventoryList, testFile); // save the html file and contents

        StringBuilder testStringBuilder = new StringBuilder(); // string builder to test find the first item
        fileInputStream.skip(304); // skip 304 chars to get to the expected first item value

        // the first value is 6 chars long, so we make a string with the next 6 chars in the file and test the result
        testStringBuilder.append((char)fileInputStream.read());
        testStringBuilder.append((char)fileInputStream.read());
        testStringBuilder.append((char)fileInputStream.read());
        testStringBuilder.append((char)fileInputStream.read());
        testStringBuilder.append((char)fileInputStream.read());
        testStringBuilder.append((char)fileInputStream.read());

        assertEquals("value1", testStringBuilder.toString());
    }

    // make sure to run the save tests above before running these load tests, as we use those testfiles to load
    @Test
    void loadTSV() throws IOException {
        File testTSV = new File("/Users/umranrazack02/IdeaProjects/Application Assignment 2/src/test/resources/ucf/assignments/tsvSaveTest.txt");
        InventoryList inventoryListTest = new InventoryList(); // new inventory list, we'll test the size of it after loading the file. Initial size is 0;
        saveAndLoader.loadTSV(inventoryListTest, testTSV);

        assertEquals(4, inventoryListTest.items.size()); // the file has 4 items in it, so the size of the list should now be 4
        assertEquals("name1", inventoryListTest.items.get(0).name); // the first item's name should be "name1"
        assertEquals("name2", inventoryListTest.items.get(1).name); // the second item's name should be "name2"
        assertEquals("name3", inventoryListTest.items.get(2).name); // the third item's name should be "name3"
        assertEquals("name4", inventoryListTest.items.get(3).name); // the fourth item's name should be "name4"
    }

    @Test
    void loadHTML() throws IOException {
        File testHtml = new File("/Users/umranrazack02/IdeaProjects/Application Assignment 2/src/test/resources/ucf/assignments/htmlSaveTest.html");
        InventoryList inventoryListTest = new InventoryList(); // new inventory list, we'll test the size of it after loading the file. Initial size is 0;
        saveAndLoader.loadHTML(inventoryListTest, testHtml);

        assertEquals(4, inventoryListTest.items.size()); // the file has 4 items in it, so the size of the list should now be 4
        assertEquals("name1", inventoryListTest.items.get(0).name); // the first item's name should be "name1"
        assertEquals("name2", inventoryListTest.items.get(1).name); // the second item's name should be "name2"
        assertEquals("name3", inventoryListTest.items.get(2).name); // the third item's name should be "name3"
        assertEquals("name4", inventoryListTest.items.get(3).name); // the fourth item's name should be "name4"
    }
}