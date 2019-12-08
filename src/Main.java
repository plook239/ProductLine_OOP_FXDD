/**
 * @author Peter Look
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("productionManager.fxml"));
        primaryStage.setTitle("Production Manager");
        primaryStage.setScene(new Scene(root, 590, 525));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
