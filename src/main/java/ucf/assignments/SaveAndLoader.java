package ucf.assignments;

import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class SaveAndLoader {
    public void saveTSV(InventoryList inventoryList, File tsvFile) throws IOException {
        FileWriter fileWriter = new FileWriter(tsvFile);

        // write header in the file
        String header = String.format("%s\t%s\t%s\n", "Value", "Serial Number", "Name");
        fileWriter.write(header);

        // write each inventory item to the file separating each field by a tabulation character
        for (int i = 0; i < inventoryList.items.size(); i++) {
            fileWriter.write("$" + inventoryList.items.get(i).value + "\t");
            fileWriter.write(inventoryList.items.get(i).serialNumber + "\t");
            fileWriter.write(inventoryList.items.get(i).name + "\n");
        }

        fileWriter.close(); // close file
    }

    public void saveHTML(InventoryList inventoryList, File htmlFile) throws IOException {
        FileWriter fileWriter = new FileWriter(htmlFile);

        // create table and write header
        String tableAndHeader = String.format("<html>\n<table style=\"border-collapse: collapse; width: 50%%;\" border=\"1\">\n<tbody>\n<tr>\n<td style=\"width: 33.3333%%;\"><strong>Value</strong></td>\n<td style=\"width: 33.3333%%;\"><strong>Serial Number</strong></td>\n<td style=\"width: 33.3333%%;\"><strong>Name</strong></td>\n</tr>\n");
        fileWriter.write(tableAndHeader);

        for (int i = 0; i < inventoryList.items.size(); i++) {
            // formatting the strings in html and writing them to the file
            String value = String.format("<tr>\n<td style=\"width: 33.3333%%;\">%s</td>", inventoryList.items.get(i).value);
            String serial = String.format("<td style=\"width: 33.3333%%;\">%s</td>", inventoryList.items.get(i).serialNumber);
            String name = String.format("<td style=\"width: 33.3333%%;\">%s</td>\n</tr>", inventoryList.items.get(i).name);
            fileWriter.write(value);
            fileWriter.write(serial);
            fileWriter.write(name);
        }
        fileWriter.write("</tbody>\n</table>\n</html>"); // closing the html

        fileWriter.close();
    }

    public void loadTSV(InventoryList inventoryList, File tsvFile) throws FileNotFoundException {
        Scanner read = new Scanner(tsvFile); // scanner to read the file

        read.nextLine(); // skip the first line (table header)

        while (read.hasNext()) {
            String value = read.next().substring(1); // the value string comes with $, which is not stored in the item field
            String serialNumber = read.next(); // next thing to read is serial number
            String name = read.next(); // next thing to read is name

            Item newItem = new Item(name, serialNumber, value); // create the new item
            inventoryList.items.add(newItem); // add it to the list
        }
    }

    // i parsed the html myself. Since we're loading html files previously saved by the application, this works
    public void loadHTML(InventoryList inventoryList, File htmlFile) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(htmlFile.getAbsolutePath()); // getting the path of the file

        fileInputStream.skip(304); // skipping the html syntax and the table header

        char i = 0; // char to store what is to be read

        // string builders to construct the attributes of the items
        StringBuilder value = new StringBuilder();
        StringBuilder serialNumber = new StringBuilder();
        StringBuilder name = new StringBuilder();

        // loop through the characters in the html file
        while (true) {
            // we start at the value, loop until the value ends
            while (true) {
                i = (char)fileInputStream.read();
                if (i == '<') {
                    break;
                }
                value.append(i); // append the char to create the value string
            }

            fileInputStream.skip(33); // skip 33 more chars to get to the serial number

            // loop from start of serial number till end
            while (true) {
                i = (char)fileInputStream.read();
                if (i == '<') {
                    break;
                }
                serialNumber.append(i); // append the char to create the serial number string
            }

            fileInputStream.skip(33); // skip 33 more chars to get to the item name

            // loop from start of name till end
            while (true) {
                i = (char)fileInputStream.read();
                if (i == '<') {
                    break;
                }
                name.append(i); // append the char to create the name string
            }

            // instantiate the item using the constructed strings for the attributes
            Item readItem = new Item(name.toString(), serialNumber.toString().toUpperCase(), value.toString());
            inventoryList.items.add(readItem); // add the item to the list

            // clear the string builders to get more items
            value.setLength(0);
            serialNumber.setLength(0);
            name.setLength(0);

            fileInputStream.skip(43); // skip 43 chars to get to the next value

            // if the next char is not >, that means the items have ended
            if ((char)fileInputStream.read() != '>') {
                break;
            }
            else {
                continue;
            }
        }

    }
}
