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

    public Songs() {
    }

    public void readPath(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                listOfFiles.add(new File(line));
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        generateButtonList();
    }

    public void generateButtonList() {
        for (File file : listOfFiles) {

            if (file.isFile()) {
                buttonlist.add(new Button(file.getName()));
            }

        }

        for (int elem = 0; elem < buttonlist.size(); elem++) {
            buttonlist.get(elem).setMinSize(465, 30);
            buttonlist.get(elem).setStyle("-fx-background-color: #7b98f7; -fx-border-with: 2; -fx-border-color: black;");
        }

    }

    public List<Button> getButtonlist() {
        return buttonlist;
    }
}
