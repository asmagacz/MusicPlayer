package view;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import player.ListOfFiles;
import player.SongTimer;
import player.Songs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
    public Button songsFolderPath;
    @FXML
    public VBox songsList;
    @FXML
    public Button prev;
    @FXML
    private Label songTitle;
    @FXML
    private Button favourite;
    @FXML
    private Label songTimer;
    @FXML
    private MenuButton menuButton;
    @FXML
    private MenuItem playList;
    @FXML
    private MenuItem favList;
    @FXML
    private ScrollPane scrollPane;

    private Media hit;
    private MediaPlayer mediaPlayer;
    private ListOfFiles readListOfFiles = new ListOfFiles();
    private SongTimer timer;
    private List<File> favouriteList = new ArrayList<>();

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
        //TODO fix song timer
        //showTimer();

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
            }
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

    }

    public void loopSong() {
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
            }
        });
    }

    public void addToFavourite() {
        favouriteList.add(songs.listOfFiles.get(index));
        readListOfFiles.writeIntoFile(favouriteList, "favListFile.txt");
    }

    public void readPath() {
        readListOfFiles.readPath();
    }

    public void onPlaylistSelected() {
        songs.readPath("songsDataFile.txt");
        showPlaylist();
    }

    public void onFavlistSelected() {
        //TODO naprawić poprawne wyświetlanie i wczytywanie listy oraz odtwarzanie
        clearPlaylist();
        songs.getButtonlist().clear();
        System.out.println(favouriteList.get(0));
        songs.readPath("favListFile.txt");
        showPlaylist();
    }

    private void clearPlaylist() {
        songsList.getChildren().clear();
        //TODO naprawić usuwanie i wczytywanie przycisków do listy
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
