package mk.musiclibrarygui.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import mk.musiclibrarygui.App;

/**
 * Controller for the main menu of the music library application. Manages
 * navigation to different screens and application exit.
 *
 * @author Michal Kaminski
 * @version 2.0
 */
public class MenuController {

    /**
     * Button to navigate to the credits screen.
     */
    @FXML
    private Button creditsButton;

    /**
     * Button to navigate to the music library screen.
     */
    @FXML
    private Button libraryButton;

    /**
     * Button to exit the application.
     */
    @FXML
    private Button exitButton;

    /**
     * Navigates to the author screen when the author button is clicked.
     *
     * @param event the action event triggered by clicking the author button
     * @throws IOException if loading the author screen fails
     */
    @FXML
    private void showAuthor(ActionEvent event) throws IOException {
        App.setRoot("/mk/musiclibrarygui/views/Author");
    }

    /**
     * Navigates to the music library screen when the library button is clicked.
     *
     * @param event the action event triggered by clicking the library button
     * @throws IOException if loading the music library screen fails
     */
    @FXML
    private void showLibrary(ActionEvent event) throws IOException {
        App.setRoot("/mk/musiclibrarygui/views/MusicTable");
    }

    /**
     * Exits the application when the exit button is clicked.
     *
     * @param event the action event triggered by clicking the exit button
     */
    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Handles the keyboard shortcut (Ctrl+C) to navigate to the credits screen.
     * Fires the credits button action when triggered.
     *
     * @param event the key event triggered by pressing Ctrl+C
     */
    @FXML
    private void handleAuthorEvent(KeyEvent event) {
        if (event.isControlDown() && event.getCode() == KeyCode.C) {
            this.creditsButton.fire();
        }
    }

    /**
     * Handles the keyboard shortcut (Ctrl+L) to navigate to the music library
     * screen. Fires the library button action when triggered.
     *
     * @param event the key event triggered by pressing Ctrl+L
     */
    @FXML
    private void handleLibraryEvent(KeyEvent event) {
        if (event.isControlDown() && event.getCode() == KeyCode.L) {
            this.libraryButton.fire();
        }
    }

    /**
     * Handles the keyboard shortcut (Ctrl+E) to exit the application. Fires the
     * exit button action when triggered.
     *
     * @param event the key event triggered by pressing Ctrl+E
     */
    @FXML
    private void handleExitEvent(KeyEvent event) {
        if (event.isControlDown() && event.getCode() == KeyCode.E) {
            this.exitButton.fire();
        }
    }

}
