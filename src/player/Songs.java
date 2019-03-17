package player;

import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Songs {
    private List<Button> buttonlist = new ArrayList<>();
    private String songsDataFile = "songsDataFile.txt";

    //TODO rodzielić na osobne pliki ListOfFiles i songs
    public List<File> listOfFiles = new ArrayList<>();
    //TODO zmienić wczytywanie plików żeby zapisywało

    public Songs() {
        //playList();
        //readSongs();
    }

    public void readPath() {
        //TODO dodac wczytywanie danych pliku do TXT
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);
        listOfFiles.addAll(Arrays.asList(selectedDirectory.listFiles()));
        //playList();
        writeIntoFile();
    }

    private void writeIntoFile() {
        try {
            PrintWriter writer = new PrintWriter(songsDataFile, "UTF-8");
            for (File file : listOfFiles) {
                writer.println(file);
            }
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException fne) {
            fne.printStackTrace();
        }
        fromSongsDataFileToListOfFiles();
    }

    private void fromSongsDataFileToListOfFiles(){

    }

    public void playList() {
        if (listOfFiles.isEmpty()) {
            try (BufferedReader br = new BufferedReader(new FileReader(songsDataFile))) {
                String line = br.readLine();
                while (line != null) {
                    listOfFiles.add(new File(line));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (File file : listOfFiles) {
            if (file.isFile()) {
                //TODO fix list of songs
                buttonlist.add(new Button(file.getName()));
                System.out.println(file.getAbsolutePath());
                System.out.println(file.getName());
            }

        }
        for (int elem = 0; elem < buttonlist.size(); elem++) {
            buttonlist.get(elem).setMinSize(460, 30);

        }
    }

    public List<Button> getButtonlist() {
        return buttonlist;
    }

}
