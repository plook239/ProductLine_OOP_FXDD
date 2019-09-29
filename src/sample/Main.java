/********************************************
 * Author: Peter Look II
 * File: Main.java
 * Project: Production_Line_OOP_FXDD
 * Date created: 9/16/2019
 *********************************************/

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/************************
 * Creates primary stage
 ************************/

public class Main extends Application
{

  @Override
  public void start(Stage primaryStage) throws Exception
  {
    Parent root = FXMLLoader.load(getClass().getResource("productionManager.fxml"));
    primaryStage.setTitle("Production Manager");
    primaryStage.setScene(new Scene(root, 590, 525));
    primaryStage.show();
  }

  /***************
   * Main method
   ***************/

  public static void main(String[] args)
  {
    launch(args);


  }
}


