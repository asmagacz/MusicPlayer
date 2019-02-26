package player;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Controller {
    static Stage stage;
    private Songs songs = new Songs();

    @FXML
    public Button play;
    @FXML
    public Button stop;
    @FXML
    public Button pause;
    @FXML
    public Slider volume;
    @FXML
    public Button songsFolderPath;
    @FXML
    public VBox songsList;

    List<Button> buttonlist = new ArrayList<>();

    //TODO wczytywanie listy utworów i przewijanie
    //private Media hit = new Media(new File(toString(songs.getFolder() " " songs.getListOfFiles(0))));
   // private MediaPlayer mediaPlayer = new MediaPlayer(hit);

    public Controller(){

    }

    public void setStage(Stage stage){
        this.stage=stage;
    }


    public void playMusic(){
      //  mediaPlayer.play();
    }

    public void stopMusic(){
      //  mediaPlayer.stop();
    }

    public void pauseMusic(){
      //  mediaPlayer.pause();
    }

    public void readPath(){
        songs.readPath();
        showPlaylist();
    }

    private void showPlaylist(){
        //songsList.appendText(songs.listOfFiles.toString());
        for (File file : songs.listOfFiles) {
            if (file.isFile()) {
                //TODO fix list of songs
                buttonlist.add(new Button(file.getName()));
                System.out.println(file.getName());
            }

        }
        for (int elem = 0; elem < buttonlist.size(); elem++){
            buttonlist.get(elem).setMinSize(songsList.getPrefWidth(),20);
        }
        songsList.getChildren().clear();
        songsList.getChildren().addAll(buttonlist);
    }

    //TODO naprawic ustawianie glosnosci
    public void setVolume(){
       // mediaPlayer.setVolume(volume.getValue());
    }
}
