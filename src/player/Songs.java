package player;

import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Songs {
    private List<Button> buttonlist = new ArrayList<>();

    //TODO rodzielić na osobne pliki ListOfFiles i songs
    public File[] listOfFiles;

    public Songs() {
        //readSongs();
    }

    public void readPath() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);
        listOfFiles = selectedDirectory.listFiles();
        playList();
    }

    private void playList() {
        for (File file : listOfFiles) {
            if (file.isFile()) {
                //TODO fix list of songs
                buttonlist.add(new Button(file.getName()));
                System.out.println(file.getName());
            }

        }
        for (int elem = 0; elem < buttonlist.size(); elem++) {
            buttonlist.get(elem).setMinSize(480, 20);
        }
    }

    public List<Button> getButtonlist() {
        return buttonlist;
    }

}
