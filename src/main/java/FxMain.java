import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class FxMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/GUI.fxml"));
            Scene scene = new Scene(root);
            primaryStage.getIcons().add(new Image("http://files.softicons.com/download/toolbar-icons/fatcow-hosting-extra-icons-2-by-fatcow/png/32x32/pizza.png"));
            primaryStage.setTitle("Pizza - konkurs");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void main(String[] args) {
        launch(args);
    }

}
