package mk.musiclibrarygui.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import mk.musiclibrarygui.App;
import mk.musiclibrarygui.models.SongList;

/**
 * Controller for the Author screen. Manages navigation back to the main menu.
 *
 * @author Michal Kaminski
 * @version 2.0
 */
public class AuthorController {

    /**
     * The list of songs to which the new song will be added.
     */
    private SongList songList;

    /**
     * Reference to the main application instance, used for managing scene
     * transitions. This allows the controller to request navigation to
     * different screens within the application.
     */
    private App app;

    /**
     * Button to navigate back to the main menu.
     */
    @FXML
    private Button goBackButton;

    /**
     * Constructs an AuthorController with the specified song list and
     * application instance. This controller manages actions related to the
     * author within the application.
     *
     * @param songList The SongList instance containing all songs managed by the
     * application
     * @param app The main App instance that manages the application's lifecycle
     * and navigation
     */
    public AuthorController(SongList songList, App app) {
        this.songList = songList;
        this.app = app;
    }

    /**
     * Navigates back to the main menu when the go back button is clicked.
     *
     * @param event the action event triggered by clicking the go back button
     * @throws IOException if loading the previous screen fails
     */
    @FXML
    private void goBack(ActionEvent event) throws IOException {
        app.setRoot("/mk/musiclibrarygui/views/Menu");
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
