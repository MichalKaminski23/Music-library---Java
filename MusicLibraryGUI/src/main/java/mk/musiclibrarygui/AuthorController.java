package mk.musiclibrarygui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AuthorController {

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        App.setRoot("Menu");
    }
}
