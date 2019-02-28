package player;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
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
    public Button next;
    @FXML
    public Slider volume;
    @FXML
    public Button songsFolderPath;
    @FXML
    public VBox songsList;
    @FXML
    public Button prev;

    private Media hit;
    private MediaPlayer  mediaPlayer;

    private List<Button> buttonlist = new ArrayList<>();

    private int index = 0;

    //TODO wczytywanie listy utworów i przewijanie
    //private Media hit = new Media(new File(toString(songs.getFolder() " " songs.getListOfFiles(0))));
   // private MediaPlayer mediaPlayer = new MediaPlayer(hit);

    public Controller(){

    }

    public void setStage(Stage stage){
        this.stage=stage;
    }


    public void playMusic(){
        //TODO wczytywanie poprawnej ścieżki
        hit = new Media(songs.listOfFiles[index].toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
    }

    public void stopMusic(){
        mediaPlayer.stop();
    }

    public void pauseMusic(){
        mediaPlayer.pause();
    }

    public void nextSong(){
        index += 1;
        mediaPlayer.stop();
        hit = new Media(songs.listOfFiles[index].toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();

    }
    public void prevSong(){
        index -= 1;
        mediaPlayer.stop();
        hit = new Media(songs.listOfFiles[index].toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();

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
