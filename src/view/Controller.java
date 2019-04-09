package view;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import player.ListOfFiles;
import player.SongTimer;
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
    private SongTimer timer;

    private int index = 0;

    private boolean isPlaying = false;

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
        System.out.println(mediaPlayer.getTotalDuration());
        changeTitle();
        checkStatus();
        /*MediaPlayer.Status currentStatus = mediaPlayer.getStatus();
        //TODO fix song timer
        //showTimer();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                while (status) {
                    songTimer.setText(timer.showTimer());
                }
            }
        });*/
    }

    public void stopMusic() {
        mediaPlayer.stop();
    }

    public void pauseMusic() {
        MediaPlayer.Status currentStatus = mediaPlayer.getStatus();
        if (currentStatus == MediaPlayer.Status.PLAYING) {
            System.out.println(mediaPlayer.getStatus());
            mediaPlayer.pause();
        } else {
            System.out.println(mediaPlayer.getStatus());
            mediaPlayer.play();
        }

    }

    public void nextSong() {
        index += 1;
        mediaPlayer.stop();

        if (index > songs.listOfFiles.size() - 1) {
            index = 0;
        }
        hit = new Media(songs.listOfFiles.get(index).toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
        changeTitle();
        checkStatus();
    }

    public void prevSong() {
        index -= 1;
        mediaPlayer.stop();
        if (index < 0) {
            index = songs.listOfFiles.size() - 1;
        }
        hit = new Media(songs.listOfFiles.get(index).toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
        changeTitle();
        checkStatus();
    }

    private void checkStatus() {
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.stop();
                nextSong();
                return;
            }
        });
    }

    public void readPath() {
        readListOfFiles.readPath();
    }

    public void onMenuSelected() {
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

    //TODO naprawic ustawianie glosnosci
    public void setVolume() {
        volume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (volume.isPressed()) {
                    mediaPlayer.setVolume(volume.getValue() / 100); // It would set the volume
                    // as specified by user by pressing
                }
            }
        });

    }
}
