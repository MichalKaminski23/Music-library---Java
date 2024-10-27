package mk.musiclibrarygui.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import mk.musiclibrarygui.App;
import mk.musiclibrarygui.models.SongList;
import mk.musiclibrarygui.models.WrongInputException;

/**
 * Controller for the AddSongScreen. Manages song details input fields and
 * facilitates adding new songs to the SongList.
 *
 * @author Michal Kaminski
 * @version 2.0
 */
public class AddSongScreenController {

    /**
     * Text field for entering the song title.
     */
    @FXML
    private TextField titleField;

    /**
     * Text field for entering the author's name.
     */
    @FXML
    private TextField nameField;

    /**
     * Text field for entering the author's surname.
     */
    @FXML
    private TextField surnameField;

    /**
     * Text field for entering the album name.
     */
    @FXML
    private TextField albumField;

    /**
     * Text field for entering the release date.
     */
    @FXML
    private TextField releaseField;

    /**
     * Text field for entering the song duration.
     */
    @FXML
    private TextField timeField;

    /**
     * Label for displaying error messages if input is invalid.
     */
    @FXML
    private Label errorLabel;

    /**
     * Button to submit and add a new song.
     */
    @FXML
    private Button submitButton;

    /**
     * Button to go back to the previous screen.
     */
    @FXML
    private Button goBackButton;

    /**
     * The list of songs to which the new song will be added.
     */
    private SongList songList;

    /**
     * Constructor that accepts a SongList instance for adding new songs.
     *
     * @param songList the song list to which new songs will be added
     */
    public AddSongScreenController(SongList songList) {
        this.songList = songList;
    }

    /**
     * Handles the submit action, attempting to add a new song with the entered
     * details. If the input is invalid, an error message is displayed.
     *
     * @param event the action event triggered by clicking the submit button
     * @throws IOException if loading the next screen fails
     * @throws WrongInputException if the input fields contain invalid data
     */
    @FXML
    private void submit(ActionEvent event) throws IOException, WrongInputException {
        try {
            songList.addSong(
                    titleField.getText(),
                    nameField.getText(),
                    surnameField.getText(),
                    albumField.getText(),
                    releaseField.getText(),
                    timeField.getText()
            );
            App.setRoot("/mk/musiclibrarygui/views/MusicTable");

        } catch (WrongInputException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    /**
     * Navigates back to the music table view when the go back button is
     * clicked.
     *
     * @param event the action event triggered by clicking the go back button
     * @throws IOException if loading the previous screen fails
     */
    @FXML
    private void goBack(ActionEvent event) throws IOException {
        App.setRoot("/mk/musiclibrarygui/views/MusicTable");
    }

    /**
     * Handles the keyboard shortcut (Ctrl+S) for submitting the new song. Fires
     * the submit button action when triggered.
     *
     * @param event the key event triggered by pressing Ctrl+S
     */
    @FXML
    private void handleSubmitEvent(KeyEvent event) {
        if (event.isControlDown() && event.getCode() == KeyCode.S) {
            this.submitButton.fire();
        }
    }

    /**
     * Handles the keyboard shortcut (Ctrl+E) for going back to the previous
     * screen. Fires the go back button action when triggered.
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
