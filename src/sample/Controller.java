/************************************************
 * Author: Peter Look II
 * File: Controller.java
 * Project: Production_Line_OOP_FXDD
 * Date created: 9/16/2019 , finished 9/28/2019
 ************************************************/

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

/***********************************
 * Controller
 * H2 driver URL
 * initializes a connection
 *  and Prepared statement variables
 ***********************************/

public class Controller {

  private static final String JDBC_DRIVER = "org.h2.Driver";
  private static final String DB_URL = "jdbc:h2:./res/ProductionDB";
  private Connection connect = null;
  private PreparedStatement prepStmt = null;

  /***************************
   * Calls initializeDataBase
   **************************/

  public Controller() {
    initializeDataBase();
  }

  /*******************************************************
   * Initialize choiceBox and comboBox
   * choiceBox ~ Audio, Communication, and hardware types
   * comboBox ~ 0-10 for choosing quantities
   *******************************************************/

  public void initialize()
  {
    choiceBox_1.getItems().addAll("AUDIO", "COMMUNICATION","HARDWARE");
    for(int i = 1; i < 11; i++)
    {
      comboBox_produce.getItems().addAll(i);
    }
    comboBox_produce.setEditable(true);
    comboBox_produce.getSelectionModel().selectFirst();
  }

  /********************************************************
   * Initialize DB
   * variables for USER and PASSWORD
   * initializes addProductToDataBase for insertion query
   ********************************************************/

  private void initializeDataBase() {
    final String USER = "";
    final String PASS = "";

    String addProductToDataBase = "insert into PRODUCT(type,name, manufacturer) values " + "('audio','ipad','apple');";

    try
    {
      Class.forName(JDBC_DRIVER);
      connect = DriverManager.getConnection(DB_URL, USER, PASS);
      prepStmt = connect.prepareStatement(addProductToDataBase);
    }
    catch (ClassNotFoundException | SQLException e)
    {
      e.printStackTrace();
    }
  }

  /*************************************************
   * Prepared statement variable to get user input
   *  from Type, product name, and manufacturer
   *************************************************/

  @FXML
  void addProductButtonClicked(MouseEvent event) throws SQLException
  {
    prepStmt.setString(1, choiceBox_1.getValue());
    prepStmt.setString(2, textField_productName_1.getText());
    prepStmt.setString(3, textField_manufacturer_1.getText());
    prepStmt.execute();
  }

  /****************************************************
   * These are the FXID for elements of the application
   * most are not in use yet, I just added the id's for
   * future needs.
   ****************************************************/

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