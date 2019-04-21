package player;

import javafx.stage.DirectoryChooser;

import java.io.*;
import java.util.List;

public class ListOfFiles {
    //TODO poprawic i przenieść z klasy Songs
    private File[] listOfFiles;
    private String songsDataFile = "songsDataFile.txt";

    public ListOfFiles() {
    }

    public void readPath() {
        //TODO dodac wczytywanie danych pliku do TXT
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);
        listOfFiles = selectedDirectory.listFiles();

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
