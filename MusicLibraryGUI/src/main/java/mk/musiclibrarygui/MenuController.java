package mk.musiclibrarygui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController {

    @FXML
    private void showAuthor(ActionEvent event) throws IOException {
        App.setRoot("Author");
    }

    @FXML
    private void showLibrary(ActionEvent event) throws IOException {
        App.setRoot("MusicTable");
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

}
