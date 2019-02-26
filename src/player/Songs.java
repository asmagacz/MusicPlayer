package player;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;

public class Songs {
    //public String selectedPath = "C:/Projekty/JavaProjects/MusicPlayer/src/songslist";
    //TODO wczytywanie listy i odczytywanie
    public File[] listOfFiles;



    public Songs(){
        //readSongs();
    }

    public void readPath(){
         DirectoryChooser directoryChooser = new DirectoryChooser();
         File selectedDirectory = directoryChooser.showDialog(null);
         listOfFiles = selectedDirectory.listFiles();
    }

}
