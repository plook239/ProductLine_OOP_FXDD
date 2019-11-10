import java.util.Date;

class ProductionRecord {
  private int productionNum;
  private int productID;
  private String serialNum;
  private Date date;

  /**
   * Receives int, int, string, date for params.
   *
   * @param productionNum Production Number
   * @param productID Product ID
   * @param serialNum Serial Number
   * @param date Date
   */
  public ProductionRecord(int productionNum, int productID, String serialNum, Date date) {
    this.productionNum = productionNum;
    this.productID = productID;
    this.serialNum = serialNum;
    this.date = date;
  }

  /** Constructor for creating Production Record from button @param productID Product ID. */
  public ProductionRecord(int productID) {
    productionNum = 0;
    serialNum = "0";
    date = new Date();
    this.productID = productID;
  }

  /**
   * Constructor for setting serial number and date.
   *
   * @param productProduced Product Object
   * @param itemCount Integer to increment by number of items
   */
  public ProductionRecord(Product productProduced, int itemCount) {
    String inc = String.format("%05d", itemCount);
    productionNum = 0;
    serialNum = productProduced.getManufacturer().substring(0, 3) + ItemType.AUDIO.code + inc;
    date = new Date();
  }

  /** Creates a string with production record data. */
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

  /** Getter and Setters. */
  public void setProductionNum(int productionNum) {
    this.productionNum = productionNum;
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

  public String getSerialNum() {
    return serialNum;
  }

  public int getProductID() {
    return productID;
  }

  public int getProductionNum() {
    return productionNum;
  }
}
