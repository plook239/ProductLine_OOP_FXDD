/**
 * @author Peter Look
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;


/**
 * Controller for Production Manager tabs.
 */
public class Controller {

    /**
     * FXID list.
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
    @FXML
    private Tab employeeTab;
    @FXML
    private TextField EmpFullNmTextField;
    @FXML
    private TextField EmpPwTextField;
    @FXML
    private Button CreateEmployeeButton;
    @FXML
    private TextArea CreateEmpResultTextField;
    @FXML
    private ListView<String> AllEmpListView;
    @FXML
    private Label productNameLabel;
    @FXML
    private Label manufacturerNameLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label fullNameLabel;
    @FXML
    private Label statusLabelTab1;
    @FXML
    private Label statusLabelTab3;
    @FXML
    private Label statusLabelTab2;
    @FXML
    private Label statusLabelTab4;

    private static ObservableList<Product> productList = FXCollections.observableArrayList();
    static ObservableList<ProductionRecord> productionLog = FXCollections.observableArrayList();
    private static ObservableList<String> employeeList = FXCollections.observableArrayList();
    private int itemCount = 0;

    public Controller() {
    }

    /**
     * Initialize
     */
    public void initialize() throws SQLException {
        setupTypeChoice();
        setupProductCombo();
        loadProductLine();
        loadProductionLog();
        showEmployeeList();
    }

    /**
     * Populates the choiceBox on the Product Line Tab with values from ItemType.java
     * "AUDIO","AUDIO_MOBILE", "VISUAL", and "VISUAL_MOBILE"
     */
    private void setupTypeChoice() {
        choiceBox_1
                .getItems()
                .addAll(
                        ItemType.AUDIO.toString(),
                        ItemType.AUDIO_MOBILE.toString(),
                        ItemType.VISUAL.toString(),
                        ItemType.VISUAL_MOBILE.toString());
    }

    /**
     * Populates the comboBox on the Produce tab with values 1-10 for choosing a quantity for Record
     * Production button.
     */
    private void setupProductCombo() {
        for (int i = 1; i < 11; i++) {
            comboBox_produce.getItems().addAll(i);
        }
        comboBox_produce.setEditable(true);
        comboBox_produce.getSelectionModel().selectFirst();
    }

    /**
     * Populates ObservableList<Product> productList with values from the 'Add Product' button on the
     * Production Line tab when 'Add Product' button is clicked
     *
     * @param productName      from textField_productName_1.getText() and is String
     * @param manufacturerName from textField_manufacturer_1.getText() and is String
     * @param itemType         from choiceBox_1.getValue() and is String
     */
    static void setupProductList(String productName, String manufacturerName, String itemType) {
        productList.addAll(new Widget(productName, manufacturerName, itemType));
    }

    /**
     * Set tableview columns up, load productList into TableView and ListView
     */
    public void loadProductLine() throws SQLException {
        Database.dataBaseGetProductList();
        productLineTableView_column_3.setCellValueFactory(new PropertyValueFactory<>("Manufacturer"));
        productLineTableView_column_2.setCellValueFactory(new PropertyValueFactory<>("Name"));
        productLineTableView_column_1.setCellValueFactory(new PropertyValueFactory<>("Type"));
        productLineTableView.setItems(productList);
        produceListView.setItems(productList);
    }

    /**
     * @param fullName users first and last name
     */
    static void loadEmployeeList(String fullName) {
        employeeList.addAll(fullName);
    }

    /**
     * Calls db and gets all employee first and last names.
     */
    private void showEmployeeList() throws SQLException {
        Database.dataBaseGetEmployee();
        AllEmpListView.setItems(employeeList);
    }

    /**
     * Populates the ObservableList<ProductionRecord> productLog with values from the database
     * PRODUCTIONRECORD table
     */
    public void loadProductionLog() throws SQLException {
        Database.dataBaseLoadProductLog();

        for (ProductionRecord ignored : productionLog) {
            productionLogTextArea.appendText(productionLog.iterator().next().toString() + "\n");
        }
    }

    /**
     * @param pL ObservableList<ProductionRecord> pl </ProductionRecord>
     *           clears textArea and writes new products to be logged.
     */
    private void showProductionLog(ObservableList<ProductionRecord> pL) {
        productionLogTextArea.clear();
        for (ProductionRecord pr : pL) {
            productionLogTextArea.appendText(pr.toString() + "\n");
        }

    }

    /**
     * When 'Add Product' button is clicked, calls addProduct() to send product name, product
     * manufacturer, and item type from the user on Production Line tab to the database PRODUCT table,
     * then calls setupProductionLineTable() with the same product name, product manufacturer, and
     * item type in order to create a Product Widget to add to the ObservableList<Product>productList.
     */
    @FXML
    void addProductButtonClicked(MouseEvent event) {
        if (textField_productName_1.getText().trim().isEmpty() || textField_manufacturer_1.getText().trim().isEmpty() || choiceBox_1.getValue() == null) {
            productNameLabel.setStyle("-fx-text-fill: red");
            manufacturerNameLabel.setStyle("-fx-text-fill: red");
            typeLabel.setStyle("-fx-text-fill: red");
            statusLabelTab1.setStyle("-fx-text-fill: red");
            statusLabelTab1.setText("All fields required.");
        } else {
            Database.dataBaseAddProduct(
                    textField_productName_1.getText(),
                    textField_manufacturer_1.getText(),
                    choiceBox_1.getValue());

            setupProductList(
                    textField_productName_1.getText(),
                    textField_manufacturer_1.getText(),
                    choiceBox_1.getValue());

            statusLabelTab1.setText("Product Added.");
        }
    }

    /**
     * When 'Record Production' button is clicked, calls addProductionRecord() to send a ProductID
     * values from the comboBox on the Produce tab and sets global variable Quantity to that selection
     * for other use. Calls setupProductionLog() to populate Production Log with data from selection
     */
    @FXML
    void recordProduceButtonClicked(MouseEvent event) {
        statusLabelTab3.setText("New Production");
        int qt = comboBox_produce.getSelectionModel().getSelectedIndex() + 1;
        Product p = produceListView.getSelectionModel().getSelectedItem();
        if (comboBox_produce.getSelectionModel().isEmpty() || produceListView.getSelectionModel().isEmpty()) {
            statusLabelTab2.setText("Select a product and quantity");
            statusLabelTab2.setStyle("-fx-text-fill: red");
        } else {
            statusLabelTab2.setText("Successfully added production");
            ObservableList<ProductionRecord> productionRun = FXCollections.observableArrayList();
            for (int q = 1; q <= qt; q++) {
                productionRun.add(new ProductionRecord(p, itemCount++));
            }

            Database.dataBaseAddProductionRecord(productionRun);
            showProductionLog(productionRun);
        }
    }

    /**
     * When 'createEmployeeButton' is clicked, sends the data to employee table in db.
     */
    @FXML
    void createEmployeeButtonClicked(MouseEvent event) throws SQLException {
        if (EmpFullNmTextField.getText().trim().isEmpty() || EmpPwTextField.getText().trim().isEmpty()) {
            fullNameLabel.setStyle("-fx-text-fill: red");
            passwordLabel.setStyle("-fx-text-fill: red");
            statusLabelTab4.setText("First Last and Password required.");
        } else {
            Employee e = new Employee(EmpFullNmTextField.getText(), EmpPwTextField.getText());
            Database.dataBaseSetEmployee(e);
            CreateEmpResultTextField.clear();
            AllEmpListView.getItems().clear();
            CreateEmpResultTextField.appendText(e.toString());
            showEmployeeList();
        }
    }

}
