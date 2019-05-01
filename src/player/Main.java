package player;

import view.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/musicPlayer.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Music Player");
        primaryStage.setScene(new Scene(root, 495, 700));
        primaryStage.getScene().getStylesheets().add("styles.css");
        primaryStage.show();
        ((Controller) fxmlLoader.getController()).setStage(primaryStage);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
