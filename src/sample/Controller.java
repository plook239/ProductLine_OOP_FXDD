/**
 * Author: Peter Look II File: Controller.java
 * Project: Production_Line_OOP_FXDD Date
 * created:
 * 9/16/2019 , finished 9/28/2019
 */
package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javafx.scene.input.MouseEvent;

/** Controller H2 driver URL initializes a connection and Prepared statement variables */
public class Controller {

  private static final String JDBC_DRIVER = "org.h2.Driver";
  private static final String DB_URL = "jdbc:h2:./res/ProductionDB";

  /** Controller class empty */
  public Controller() {}

  /**
   * Initialize choiceBox and comboBox choiceBox ~ Audio, Communication, and hardware types comboBox
   * ~ 0-10 for choosing quantities
   */
  public void initialize() {
    choiceBox_1.getItems().addAll("AUDIO", "COMMUNICATION", "HARDWARE");
    for (int i = 1; i < 11; i++) {
      comboBox_produce.getItems().addAll(i);
    }
    comboBox_produce.setEditable(true);
    comboBox_produce.getSelectionModel().selectFirst();
  }

  /**
   * Button to get user input from choice_box_1, textField_productName_1, and
   * textField_manufacturer_1 from Type, product name, and manufacturer Calls addProduct with text
   * and choice box field data
   */
  @FXML
  void addProductButtonClicked(MouseEvent event) {
    addProduct(
        textField_productName_1.getText(),
        textField_manufacturer_1.getText(),
        choiceBox_1.getValue());
  }

  /** Initialize DB variables for USER and PASSWORD addProductButton insertion query to DB */
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
   * These are the FXID for elements of the application most are not in use yet, I just added the
   * id's for future needs.
   */
  @FXML private Tab productLineTab;

  @FXML private Button addProductButton;
  @FXML private TextField textField_productName_1;
  @FXML private TextField textField_manufacturer_1;
  @FXML private ChoiceBox<String> choiceBox_1;
  @FXML private TableView<?> productLineTableView;
  @FXML private TableColumn<?, ?> productLineTableView_column_1;
  @FXML private TableColumn<?, ?> productLineTableView_column_2;
  @FXML private TableColumn<?, ?> productLineTableView_column_3;
  @FXML private Tab produceTab;
  @FXML private ListView<?> produceListView;
  @FXML private ComboBox<Integer> comboBox_produce;
  @FXML private Button recordProduceButton;
  @FXML private Tab productionLogTab;
  @FXML private TextArea productionLogTextArea;
}
