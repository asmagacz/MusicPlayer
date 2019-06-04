package player;

import javafx.stage.FileChooser;

import java.io.*;
import java.util.List;

public class ListOfFiles {
    private List<File> listOfFiles;
    private String songsDataFile = "songsDataFile.txt";

    public ListOfFiles() {
    }

    public void readPath() {
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

    public void writeIntoFile(String song, String fileName) {

        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(song + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
