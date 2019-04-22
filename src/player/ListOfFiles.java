package player;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.List;

public class ListOfFiles {
    //TODO poprawic i przenieść z klasy Songs
    private List<File> listOfFiles;
    private String songsDataFile = "songsDataFile.txt";

    public ListOfFiles() {
    }

    public void readPath() {
        //TODO dodac filtr dla plików .mp3
        /*DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);
        listOfFiles = selectedDirectory.listFiles();*/
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(
                "MP3 Files", "*.mp3"
        ));
        List<File> files = fileChooser.showOpenMultipleDialog(null);
        listOfFiles = files;
        try (PrintWriter writer = new PrintWriter(songsDataFile, "UTF-8")) {
            for (File file : listOfFiles) {
                writer.println(file);
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void writeIntoFile(List<File> favSongsList, String fileName) {

        try (FileWriter writer = new FileWriter(fileName, true)) {
            for (File file : favSongsList) {
                writer.append(file.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
