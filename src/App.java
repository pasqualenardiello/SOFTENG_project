package unisa.group1.test_scalc;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX main class.
 *
 * @author Group 1
 * 
 */
public class App extends Application {
    
    /**
     * Start method.
     * 
     * @param primaryStage  the primary stage.
     * @throws IOException  with class mismatch.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Calc_fx.fxml"));
        Scene scene = new Scene(root, 1082, 487);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        primaryStage.setTitle("calc");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     * 
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}