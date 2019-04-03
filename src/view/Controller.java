package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import player.ListOfFiles;
import player.Songs;

import java.util.concurrent.TimeUnit;

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
    public Slider volume = new Slider(0, 100, 0);
    @FXML
    public Button songsFolderPath;
    @FXML
    public VBox songsList;
    @FXML
    public Button prev;
    @FXML
    private Label songTitle;
    @FXML
    private Label songTimer;
    @FXML
    private MenuButton menuButton;
    @FXML
    private MenuItem playList;

    private Media hit;
    private MediaPlayer mediaPlayer;
    private ListOfFiles readListOfFiles = new ListOfFiles();

    private int index = 0;

    public Controller() {
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    //TODO podzielić projekt na odpowiednie pliki zgodnie z MVC

    public void playMusic() {
        //TODO wczytywanie poprawnej ścieżki
        hit = new Media(songs.listOfFiles.get(index).toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
        changeTitle();
        if(mediaPlayer.getStatus() == MediaPlayer.Status.STOPPED){
            nextSong();
        }
        //TODO fix song timer
        //showTimer();
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
        /// TODO: 03.04.2019 dodac if sprawdzajacego index
        if(index > songs.listOfFiles.size() - 1){
            index = 0;
        }
        hit = new Media(songs.listOfFiles.get(index).toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
        changeTitle();

        MediaPlayer.Status currentStatus = mediaPlayer.getStatus();

        if(currentStatus == MediaPlayer.Status.STOPPED){
            nextSong();
        }
    }

    public void prevSong() {
        index -= 1;
        mediaPlayer.stop();
        if(index < 0){
            index = songs.listOfFiles.size() - 1;
        }
        hit = new Media(songs.listOfFiles.get(index).toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
        changeTitle();
        if(mediaPlayer.getStatus() == MediaPlayer.Status.STOPPED){
            nextSong();
        }
    }

    public void readPath() {
        readListOfFiles.readPath();
    }

    public void onMenuSelected(){
        songs.readPath();
        showPlaylist();
    }

    private void clearPlaylist() {
        songsList.getChildren().removeAll(songs.getButtonlist());
    }

    private void showPlaylist() {
        songsList.getChildren().clear();
        songsList.getChildren().addAll(songs.getButtonlist());
    }

    private void changeTitle() {
        songTitle.setText(songs.listOfFiles.get(index).getName());
    }

    private void showTimer() {
        long displayMinutes = 0;
        long starttime = System.currentTimeMillis();
        System.out.println("Timer:");
        while (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long timepassed = System.currentTimeMillis() - starttime;
            long secondspassed = timepassed / 1000;
            if (secondspassed == 60) {
                secondspassed = 0;
                starttime = System.currentTimeMillis();
            }
            if ((secondspassed % 60) == 0)
                displayMinutes++;

            System.out.println(displayMinutes + "::" + secondspassed);
            songTimer.setText(displayMinutes + ":" + secondspassed);
        }
    }


    //TODO naprawic ustawianie glosnosci
    public void setVolume() {
        // mediaPlayer.volumeProperty().bindBidirectional(volume.valueProperty());
        //System.out.println(volume.getValue());
    }
}
