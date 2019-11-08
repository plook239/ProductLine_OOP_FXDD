package sample;

import java.util.Arrays;
import java.util.Date;

public class ProductionRecord {
  private int productionRecord;
  private int productionNum;
  private int productID;
  private String serialNum;
  private Date date;

  public ProductionRecord(int productionNum, int productID, String serialNum, Date date) {
    this.productionNum = productionNum;
    this.productID = productID;
    this.serialNum = serialNum;
    this.date = date;
  }

  public ProductionRecord(int productID) {
    productionNum = 0;
    serialNum = "0";
    date = new Date();
    this.productID = productID;
  }

  public ProductionRecord(Product productProduced, int itemCount) {
    String inc = String.format("%05d",itemCount);
    productionNum = 0;
    serialNum = productProduced.getManufacturer().substring(0,3) + ItemType.AUDIO.code + inc;
    date = new Date();
    this.productID = productID;
  }

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

  public String toString(){
    return "Prod. Num: " + productionNum + " Product ID: " + productID + " Serial Num: " + serialNum + " Date: " + date;
  }

}


