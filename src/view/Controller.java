package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import player.ListOfFiles;
import player.Songs;

import java.util.Random;

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
    public Button random;
    @FXML
    public Button loop;
    @FXML
    public Slider volume = new Slider(0, 100, 0);
    @FXML
    public VBox songsList;
    @FXML
    public Button prev;
    @FXML
    private Label songTitle;
    @FXML
    private Slider timeSlider = new Slider();

    private Media hit;
    private MediaPlayer mediaPlayer;
    private ListOfFiles readListOfFiles = new ListOfFiles();

    private int index = 0;


    public Controller() {
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void playMusic() {
        hit = new Media(songs.listOfFiles.get(index).toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
        System.out.println(mediaPlayer.getTotalDuration());
        changeTitle();
        checkStatus();
        seeker();
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
            seeker();
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
        seeker();
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
        seeker();
    }

    private void checkStatus() {
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.stop();
            nextSong();
        });
    }

    public void randomSong() {
        Random rand = new Random();
        mediaPlayer.stop();
        int indexInRandom = rand.nextInt(songs.listOfFiles.size());
        hit = new Media(songs.listOfFiles.get(indexInRandom).toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
        changeTitle();
        checkStatus();
        seeker();

    }

    public void loopSong() {
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        });
    }

    public void addToFavourite() {
        readListOfFiles.writeIntoFile(songs.listOfFiles.get(index).toString(), "favListFile.txt");
    }

    public void readPath() {
        readListOfFiles.readPath();
    }

    public void onPlaylistSelected() {
        clearPlaylist();
        songs.readPath("songsDataFile.txt");
        showPlaylist();
    }

    public void onFavlistSelected() {
        clearPlaylist();
        songs.readPath("favListFile.txt");
        showPlaylist();
    }

    private void clearPlaylist() {
        songs.getButtonlist().clear();
        songs.listOfFiles.clear();
        songsList.getChildren().clear();
    }

    private void showPlaylist() {
        songsList.getChildren().clear();
        songsList.getChildren().addAll(songs.getButtonlist());
    }

    private void changeTitle() {
        songTitle.setText(songs.listOfFiles.get(index).getName());
    }

    public void setVolume() {
        volume.valueProperty().addListener(observable -> {
            if (volume.isPressed()) {
                mediaPlayer.setVolume(volume.getValue() / 100);
            }
        });

    }

    private void seeker() {
        mediaPlayer.totalDurationProperty().addListener((obs, oldDuration, newDuration) ->
                timeSlider.setMax(newDuration.toSeconds()));
        mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (!timeSlider.isValueChanging()) {
                timeSlider.setValue(newTime.toSeconds());
            }
        });
        timeSlider.valueProperty().addListener(observable -> {
            if (timeSlider.isPressed()) {
                mediaPlayer.seek(Duration.seconds(timeSlider.getValue()));
            }
        });
    }
}
