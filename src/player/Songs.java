package player;

import javafx.scene.control.Button;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Songs {
    private List<Button> buttonlist = new ArrayList<>();
    public List<File> listOfFiles = new ArrayList<>();
    private String songsDataFile = "songsDataFile.txt";

    public Songs() {
    }

    // TODO: 03.04.2019 dodac 2 metody : jedna oczytuje z pliku i zamienia na liste druga po

    public void readPath() {
        //TODO dodac wczytywanie danych z  pliku do listyi generowanie listy buttonów
        try (BufferedReader br = new BufferedReader(new FileReader(songsDataFile))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                listOfFiles.add(new File(line));
                line = br.readLine();
            }
            System.out.println(listOfFiles.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        generateButtonList();
    }

    public void generateButtonList() {

        for (File file : listOfFiles) {

            if (file.isFile()) {
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
