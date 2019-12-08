/**
 * @author Peter Look
 */

import javafx.collections.ObservableList;

import java.sql.*;


public class Database {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:./res/ProductionDB";
    private static final String USER = "";
    private static final String PASS = "";
    private static Statement stmt;
    public static Connection connect;

    /**
     * @param itemType
     * @param manufacturerName
     * @param productName
     */
    static void dataBaseAddProduct(String productName, String manufacturerName, String itemType) {
        String query = "INSERT INTO PRODUCT (NAME, TYPE, MANUFACTURER) VALUES (?,?,?)";
        try {
            Class.forName(JDBC_DRIVER);
            connect = DriverManager.getConnection(DB_URL, USER, PASS);

            if (connect != null) {
                PreparedStatement prepStmt =
                        connect.prepareStatement(
                                query,
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
     * @param productionRun insert into PRODUCTIONRECORD table product id, serial num, date produced.
     */
    public static void dataBaseAddProductionRecord(ObservableList<ProductionRecord> productionRun) {
        String query = "INSERT INTO PRODUCTIONRECORD (PRODUCT_ID,SERIAL_NUM,DATE_PRODUCED) VALUES (?,?,?)";
        try {
            Class.forName(JDBC_DRIVER);
            connect = DriverManager.getConnection(DB_URL, USER, PASS);

            if (connect != null) {
                PreparedStatement prepStmt =
                        connect.prepareStatement(
                                query,
                                PreparedStatement.RETURN_GENERATED_KEYS);
                for (ProductionRecord recProd : productionRun) {
                    Timestamp ts = new Timestamp(System.currentTimeMillis());
                    prepStmt.setInt(1, recProd.getProductID());
                    prepStmt.setString(2, recProd.getSerialNum());
                    prepStmt.setTimestamp(3, ts);
                    prepStmt.executeUpdate();
                }
                prepStmt.close();
                connect.close();
            } else throw new Exception("Could not establish connection.");
        } catch (Exception ex) {
            System.out.println(ex.toString() + "A01");
        }
    }

    /**
     * Get all from PRODUCT table
     */
    static void dataBaseGetProductList() throws SQLException {
        String query = "SELECT * FROM PRODUCT";
        try {
            Class.forName(JDBC_DRIVER);
            connect = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String prodName = rs.getString(2);
                String manName = rs.getString(4);
                String typeName = rs.getString(3);
                Controller.setupProductList(prodName, manName, typeName);
            }
            connect.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e + "B02");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    /**
     * Get all from EMPLOYEE table
     */
    static void dataBaseGetEmployee() throws SQLException {
        String query = "SELECT * FROM EMPLOYEE";
        try {
            Class.forName(JDBC_DRIVER);
            connect = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                //String usrName = rs.getString(1);
                String passWord = rs.getString(2);
                //String eMail = rs.getString(3);
                String fullName = rs.getString(4);
                Employee.reverseString(passWord);
                Controller.loadEmployeeList(fullName);
            }
            connect.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e + "B99");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    /**
     * @param emp Insert into EMPLOYEE table username, password, email, fullname.
     */
    static void dataBaseSetEmployee(Employee emp) {
        String query = "INSERT INTO EMPLOYEE (USERNAME, PASSWORD, EMAIL, FULLNAME) VALUES (?,?,?,?)";
        try {
            Class.forName(JDBC_DRIVER);
            connect = DriverManager.getConnection(DB_URL, USER, PASS);

            if (connect != null) {
                PreparedStatement prepStmt =
                        connect.prepareStatement(
                                query,
                                PreparedStatement.RETURN_GENERATED_KEYS);

                prepStmt.setString(1, emp.username);
                prepStmt.setString(2, Employee.reverseString(emp.password));
                prepStmt.setString(3, emp.email);
                prepStmt.setString(4, emp.fullName);
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
     * Get all from PRODUCTIONRECORD table
     */
    public static void dataBaseLoadProductLog() throws SQLException {
        String query = "SELECT * FROM PRODUCTIONRECORD";
        try {
            Class.forName(JDBC_DRIVER);
            connect = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int pn = rs.getInt("PRODUCTION_NUM");
                int pid = rs.getInt("PRODUCT_ID");
                String sn = rs.getString("SERIAL_NUM");
                Timestamp dt = rs.getTimestamp("DATE_PRODUCED");
                Controller.productionLog.addAll(new ProductionRecord(pn, pid, sn, dt));
            }
            stmt.close();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e + "C03");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}





