package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import player.Songs;


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
    private MediaPlayer mediaPlayer;

    private int index = 0;

    public Controller() {

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    //TODO podzielić projekt na odpowiednie pliki zgodnie z MVC

    public void playMusic() {
        //TODO wczytywanie poprawnej ścieżki
        hit = new Media(songs.listOfFiles[index].toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
    }

    public void stopMusic() {
        mediaPlayer.stop();
    }

    public void pauseMusic() {
        MediaPlayer.Status currentStatus = mediaPlayer.getStatus();
        if (currentStatus == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.play();
        }

    }

    public void nextSong() {
        index += 1;
        mediaPlayer.stop();
        hit = new Media(songs.listOfFiles[index].toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();

    }

    public void prevSong() {
        index -= 1;
        mediaPlayer.stop();
        hit = new Media(songs.listOfFiles[index].toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();

    }

    public void readPath() {
        songs.readPath();
        showPlaylist();
    }

    private void showPlaylist() {
        songsList.getChildren().clear();
        songsList.getChildren().addAll(songs.getButtonlist());
    }

    //TODO naprawic ustawianie glosnosci
    public void setVolume() {
        // mediaPlayer.setVolume(volume.getValue());
    }
}
