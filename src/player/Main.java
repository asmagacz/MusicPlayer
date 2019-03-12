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
        //Parent root = FXMLLoader.load(getClass().getResource("musicPlayer.fxml"));
        //primaryStage.setTitle("Music Player");
       // primaryStage.setScene(new Scene(root, 500, 700));
        //primaryStage.show();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/musicPlayer.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Music Player");
        primaryStage.setScene(new Scene(root, 500, 700));
        primaryStage.show();
        ((Controller) fxmlLoader.getController()).setStage(primaryStage);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
