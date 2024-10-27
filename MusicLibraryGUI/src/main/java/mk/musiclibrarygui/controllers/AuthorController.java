package mk.musiclibrarygui.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import mk.musiclibrarygui.App;

/**
 * Controller for the Author screen. Manages navigation back to the main menu.
 *
 * @author Michal Kaminski
 * @version 2.0
 */
public class AuthorController {

    /**
     * Button to navigate back to the main menu.
     */
    @FXML
    private Button goBackButton;

    /**
     * Navigates back to the main menu when the go back button is clicked.
     *
     * @param event the action event triggered by clicking the go back button
     * @throws IOException if loading the previous screen fails
     */
    @FXML
    private void goBack(ActionEvent event) throws IOException {
        App.setRoot("/mk/musiclibrarygui/views/Menu");
    }

    /**
     * Handles the keyboard shortcut (Ctrl+E) for going back to the main menu.
     * Fires the go back button action when triggered.
     *
     * @param event the key event triggered by pressing Ctrl+E
     */
    @FXML
    private void handleGoBackEvent(KeyEvent event) {
        if (event.isControlDown() && event.getCode() == KeyCode.E) {
            this.goBackButton.fire();
        }
    }
}
