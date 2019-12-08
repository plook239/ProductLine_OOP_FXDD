/**
 * @author Peter Look
 */

import java.sql.Timestamp;
import java.util.Date;

class ProductionRecord {
    private int productionNum;
    private int productID;
    private String serialNum;
    private Date date;
    private Timestamp stamp = new Timestamp(System.currentTimeMillis());

    /**
     * Receives int, int, string, date for params.
     *
     * @param productionNum Production Number
     * @param productID     Product ID
     * @param serialNum     Serial Number
     * @param date          Date
     */
    ProductionRecord(int productionNum, int productID, String serialNum, Date date) {
        this.productionNum = productionNum;
        this.productID = productID;
        this.serialNum = serialNum;
        this.date = date;
    }

  /*public ProductionRecord(int productID) {
    productionNum = 0;
    serialNum = "0";
    date = new Date(stamp.getTime());
    this.productID = productID;
  }*/

    /**
     * Constructor for setting serial number.
     *
     * @param productProduced Product Object
     * @param itemCount       Integer to increment by number of items
     */
    ProductionRecord(Product productProduced, int itemCount) {
        int serialTracker = 0;
        String inc = String.format("%05d", itemCount + serialTracker);
        serialNum = productProduced.getManufacturer().substring(0, 3) + productProduced.getTypeCode() + inc;
        serialTracker++;
        date = new Date(stamp.getTime());
    }

    /**
     * Creates a string with production record data.
     */
    public String toString() {
        return "Prod. Num: "
                + productionNum
                + " Product ID: "
                + productID
                + " Serial Num: "
                + serialNum
                + " Date: "
                + date;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public void setProdDate(Date date) {
        this.date = date;
    }

    public Date getProdDate() {
        return date;
    }

    String getSerialNum() {
        return serialNum;
    }

    int getProductID() {
        return productID;
    }

    public int getProductionNum() {
        return productionNum;
    }
}
