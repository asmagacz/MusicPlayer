package player;

import javafx.stage.DirectoryChooser;

import java.io.File;

public class ListOfFiles {
//TODO poprawic i przenieść z klasy Songs
    private File[] listOfFiles;

    public ListOfFiles() {
    }

    public void readPath() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);
        listOfFiles = selectedDirectory.listFiles();
    }

    public File[] getListOfFiles() {
        return listOfFiles;
    }
}
