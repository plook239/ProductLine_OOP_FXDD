/*
 * Author: Peter Look II
 * File: Controller.java
 * Project: Production_Line_OOP_FXDD Date
 * created:
 * 9/16/2019
 * updated:
 * 11/09/2019
 */

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.Date;

/**
 * Controller for Production Manager tabs
 * */
public class Controller {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:./res/ProductionDB";
    private int Quantity;
    private String serialNumber;
    private Date dateAsOf;
    private ObservableList<Product> productList = FXCollections.observableArrayList();
    private ObservableList<ProductionRecord> productLog = FXCollections.observableArrayList();

    public Controller() {
    }

    public void initialize() throws SQLException {
        setupTypeChoice();
        setupProductCombo();
        loadProductLine();
        loadProductionLog();
    }

    /**
     * Populates the choiceBox on the Product Line Tab with values from ItemType.java
     * "AUDIO","AUDIO_MOBILE", "VISUAL", and "VISUAL_MOBILE"
     * */
    public void setupTypeChoice() {
        choiceBox_1.getItems().addAll(ItemType.AUDIO.toString(), ItemType.AUDIO_MOBILE.toString(), ItemType.VISUAL.toString(), ItemType.VISUAL_MOBILE.toString());
    }

    /**
     * Populates the comboBox on the Produce tab with values 1-10 for choosing a quantity for Record Production button.
     * */
    public void setupProductCombo() {
        for (int i = 1; i < 11; i++) {
            comboBox_produce.getItems().addAll(i);
        }
        comboBox_produce.setEditable(true);
        comboBox_produce.getSelectionModel().selectFirst();
    }

    /**
     * Populates ObservableList<Product> productList with values from the 'Add Product' button on the Production Line tab when 'Add Product' button is clicked
     * @param productName from textField_productName_1.getText() and is String
     * @param manufacturerName from textField_manufacturer_1.getText() and is String
     * @param itemType from choiceBox_1.getValue() and is String
     * */
    public void setupProductLineTable(String productName, String manufacturerName, String itemType) {

        productList.addAll(new Widget(productName, manufacturerName, itemType));
    }

    /**
     * Populates ObservableList<Product> productList with values from the database PRODUCT table and populates TableView columns with data
     * */
    public void loadProductLine() throws SQLException {
        Statement stmt = null;
        String query = "SELECT NAME, TYPE, MANUFACTURER FROM PRODUCT";

        try {
            final String USER = "";
            final String PASS = "";
            Class.forName(JDBC_DRIVER);
            Connection connect = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String prodName = rs.getString(1);
                String manName = rs.getString(3);
                String typeName = rs.getString(2);
                setupProductLineTable(prodName, manName, typeName);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

      productLineTableView_column_3.setCellValueFactory(new PropertyValueFactory<>("Manufacturer"));
      productLineTableView_column_2.setCellValueFactory(new PropertyValueFactory<>("Name"));
      productLineTableView_column_1.setCellValueFactory(new PropertyValueFactory<>("Type"));
      productLineTableView.setItems(productList);
      produceListView.setItems(productList);
    }

    /**
     * Populates the ObservableList<ProductionRecord> productLog with values from the database PRODUCTIONRECORD table
     * */
    public void loadProductionLog() throws SQLException {
        Statement stmt = null;
        String query = "SELECT * FROM PRODUCTIONRECORD";

        try {
            final String USER = "";
            final String PASS = "";
            Class.forName(JDBC_DRIVER);
            Connection connect = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                productLog.addAll(new ProductionRecord(rs.getInt("PRODUCT_ID"), rs.getInt("PRODUCTION_NUM"), serialNumber, dateAsOf));

            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    /**
     * Populates the textArea on the Production Log tab with RecordProduction objects toString method for all Products in productList.
     * */
    public void setupProductionLog() {

      for (Product p : productList) {
        ProductionRecord r = new ProductionRecord(p, Quantity++);
        serialNumber = r.getSerialNum();
        java.util.Date dateAsOf = r.getProdDate();
        java.sql.Date dateSQL = new java.sql.Date(dateAsOf.getTime());
        productionLogTextArea.appendText(r.toString() + "\n");

        try {
          final String USER = "";
          final String PASS = "";
          Class.forName(JDBC_DRIVER);
          Connection connect = DriverManager.getConnection(DB_URL, USER, PASS);

          if (connect != null) {
            PreparedStatement prepStmt =
                    connect.prepareStatement(
                            "INSERT INTO PRODUCTIONRECORD (SERIAL_NUM,DATE_PRODUCED) VALUES (?,?)",
                            PreparedStatement.RETURN_GENERATED_KEYS);

              prepStmt.setString(1, serialNumber);
              prepStmt.setDate(2, dateSQL);
              prepStmt.executeUpdate();


              prepStmt.close();
              connect.close();

          } else {
            throw new Exception("Could not establish connection.");
          }
        } catch (Exception ex) {
          System.out.println(ex.toString());
        }
      }
    }

  /**
     * When 'Add Product' button is clicked, calls addProduct() to send product name, product manufacturer, and item type
     * from the user on Production Line tab to the database PRODUCT table, then calls setupProductionLineTable() with the same product name, product manufacturer,
     * and item type in order to create a Product Widget to add to the ObservableList<Product>productList.
     */
    @FXML
    void addProductButtonClicked(MouseEvent event){
        addProduct(
                textField_productName_1.getText(),
                textField_manufacturer_1.getText(),
                choiceBox_1.getValue());

        setupProductLineTable(
                textField_productName_1.getText(),
                textField_manufacturer_1.getText(),
                choiceBox_1.getValue());
    }

    /**
     * When 'Record Production' button is clicked, calls addProductionRecord() to send a ProductID values from the comboBox on the Produce tab and sets global variable Quantity to that selection for other use.
     * Calls setupProductionLog() to populate Production Log with data from selection
     * */
    @FXML
    void recordProduceButtonClicked(MouseEvent event) {
        addProductionRecord(
                comboBox_produce.getSelectionModel().getSelectedIndex() + 1);
        Quantity = comboBox_produce.getSelectionModel().getSelectedIndex() + 1;
        setupProductionLog();

    }

    /**
     * Receives values product name, manufacturer name, and item type from 'Add Product' button being clicked
     * and stores those to the database PRODUCT table.
     * */
    public static void addProduct(String productName, String manufacturerName, String itemType) {
        try {
            final String USER = "";
            final String PASS = "";
            Class.forName(JDBC_DRIVER);
            Connection connect = DriverManager.getConnection(DB_URL, USER, PASS);

            if (connect != null) {
                PreparedStatement prepStmt =
                        connect.prepareStatement(
                                "INSERT INTO PRODUCT (NAME, TYPE, MANUFACTURER) VALUES (?,?,?)",
                                PreparedStatement.RETURN_GENERATED_KEYS);
                prepStmt.setString(1, productName);
                prepStmt.setString(2, itemType);
                prepStmt.setString(3, manufacturerName);
                prepStmt.executeUpdate();
                prepStmt.close();
                connect.close();
            } else {
                throw new Exception("Could not establish connection.");
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     * Receives the comboBox value that the user selected on the Produce tab and stores that many ProductID's in the database PRODUCTIONRECORD table.
     * */
    public static void addProductionRecord(int cB){
        try {
            final String USER = "";
            final String PASS = "";
            Class.forName(JDBC_DRIVER);
            Connection connect = DriverManager.getConnection(DB_URL, USER, PASS);

            if (connect != null) {
                PreparedStatement prepStmt =
                        connect.prepareStatement(
                                "INSERT INTO PRODUCTIONRECORD (PRODUCT_ID) VALUES (?)",
                                PreparedStatement.RETURN_GENERATED_KEYS);
                for (int rP = 1; rP <= cB; rP++) {
                    prepStmt.setInt(1, rP);
                    prepStmt.executeUpdate();
                }

                prepStmt.close();
                connect.close();
            } else {
                throw new Exception("Could not establish connection.");
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     * FXID list
     */
    @FXML
    private Tab productLineTab;
    @FXML
    private Button addProductButton;
    @FXML
    private TextField textField_productName_1;
    @FXML
    private TextField textField_manufacturer_1;
    @FXML
    private ChoiceBox<String> choiceBox_1;
    @FXML
    private TableView<Product> productLineTableView;
    @FXML
    private TableColumn<Product, ItemType> productLineTableView_column_1;
    @FXML
    private TableColumn<Product, String> productLineTableView_column_2;
    @FXML
    private TableColumn<Product, String> productLineTableView_column_3;
    @FXML
    private Tab produceTab;
    @FXML
    private ListView<Product> produceListView;
    @FXML
    private ComboBox<Integer> comboBox_produce;
    @FXML
    private Button recordProduceButton;
    @FXML
    private Tab productionLogTab;
    @FXML
    private TextArea productionLogTextArea;
}
