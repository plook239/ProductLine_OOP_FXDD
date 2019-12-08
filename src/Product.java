public abstract class Product implements Item {

    private int Id;
    private String Type;
    String Manufacturer;
    String Name;

    /**
     * Receives String values for Product data.
     *
     * @param name         Product Name
     * @param manufacturer Manufacturer Name
     * @param type         Item Type
     */
    Product(String name, String manufacturer, String type) {
        this.Name = name;
        this.Manufacturer = manufacturer;
        this.Type = type;
    }

    /**
     * Receives String values for Product data.
     *
     * @param name         Product Name
     * @param manufacturer Manufacturer Name
     */
    Product(String name, String manufacturer) {
        this.Name = name;
        this.Manufacturer = manufacturer;
    }

    /**
     * Creates a string with product data with new line after each data point.
     */
    public String toString() {
        return "Name: " + Name + "\n" + "Manufacturer: " + Manufacturer + "\n" + "Type: " + Type;
    }

    /**
     * Getter and Setters.
     */
    public int getId() {
        return Id;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    String getTypeCode() {
        return ItemType.valueOf(Type).code;
    }

}

class Widget extends Product {

    /**
     * Widget for creating a Product.
     *
     * @param name         Product Name
     * @param manufacturer Manufacturer Name
     * @param type         Item Type
     */
    Widget(String name, String manufacturer, String type) {
        super(name, manufacturer, type);
    }
}
