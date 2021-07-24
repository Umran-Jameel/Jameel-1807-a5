package ucf.assignments;


public class Item {
    String name;
    String serialNumber;
    String value;
    String outputLine;

    // constructor for arguments to be passed into the item instantiation
    public Item(String name, String serial, String value) {
        this.name = name;
        this.serialNumber = serial;
        this.value = value;
        outputLine = String.format("$%s\t\t\t\t     %s\t\t\t       %s", this.value, this.serialNumber, this.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
