package mk.musiclibrarygui.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import mk.musiclibrarygui.App;
import mk.musiclibrarygui.models.Song;
import mk.musiclibrarygui.models.SongList;
import mk.musiclibrarygui.models.SongTitleChecker;
import mk.musiclibrarygui.models.WrongInputException;

/**
 * Controller class for managing the music table, allowing users to add, edit,
 * and remove songs from the music library.
 *
 * @author Michal Kaminski
 * @version 3.0
 */
public class MusicTableController {

    /**
     * A SongTitleChecker instance that checks if the existing song title is the
     * same as the new song title, ignoring case differences. This lambda
     * expression provides a convenient way to enforce title uniqueness when
     * adding or editing songs in the music library.
     *
     * The comparison is case-insensitive, meaning that titles such as "My Song"
     * and "my song" will be considered identical.
     */
    private final SongTitleChecker titleChecker = (existingTitle, newTitle)
            -> existingTitle.equalsIgnoreCase(newTitle);

    /**
     * Reference to the main application instance, used for managing scene
     * transitions. This allows the controller to request navigation to
     * different screens within the application.
     */
    private final App app;

    /**
     * The table view displaying the list of songs.
     */
    @FXML
    private TableView<Song> table;

    /**
     * The column displaying the song ID.
     */
    @FXML
    private TableColumn<Song, Integer> songID;

    /**
     * The column displaying the song title.
     */
    @FXML
    private TableColumn<Song, String> songTitle;

    /**
     * The column displaying the author's name.
     */
    @FXML
    private TableColumn<Song, String> authorName;

    /**
     * The column displaying the author's surname.
     */
    @FXML
    private TableColumn<Song, String> authorSurname;

    /**
     * The column displaying the song album.
     */
    @FXML
    private TableColumn<Song, String> songAlbum;

    /**
     * The column displaying the song release date.
     */
    @FXML
    private TableColumn<Song, String> songRelease;

    /**
     * The column displaying the song duration.
     */
    @FXML
    private TableColumn<Song, String> songTime;

    /**
     * Label for displaying error messages.
     */
    @FXML
    private Label errorLabel;

    /**
     * Button for adding a new song.
     */
    @FXML
    private Button addSongButton;

    /**
     * Button for removing the selected song.
     */
    @FXML
    private Button removeSongButton;

    /**
     * Button for showing some info.
     */
    @FXML
    private Button someStatsButton;

    /**
     * Button for navigating back to the previous menu.
     */
    @FXML
    private Button goBackButton;

    /**
     * The list of songs managed by this controller.
     */
    private final SongList songList;

    /**
     * Constructs a MusicTableController with the specified song list and
     * application instance. This controller manages the music table view,
     * displaying the list of songs in a structured format and allowing user
     * interaction with individual song entries.
     *
     * @param songList The SongList instance containing all songs to be
     * displayed in the table view
     * @param app The main App instance that controls the application's
     * navigation and lifecycle
     */
    public MusicTableController(SongList songList, App app) {
        this.songList = songList;
        this.app = app;
    }

    /**
     * Initializes the controller after its root element has been processed.
     * This method sets up the table, makes columns editable, and binds key
     * events.
     *
     * @throws WrongInputException if there are issues with input data
     */
    public void initialize() throws WrongInputException {

        table.getSelectionModel().setCellSelectionEnabled(true);

        songID.setCellValueFactory(new PropertyValueFactory<>("songID"));
        songTitle.setCellValueFactory(new PropertyValueFactory<>("songTitle"));
        authorName.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        authorSurname.setCellValueFactory(new PropertyValueFactory<>("authorSurname"));
        songAlbum.setCellValueFactory(new PropertyValueFactory<>("songAlbum"));
        songRelease.setCellValueFactory(new PropertyValueFactory<>("songRelease"));
        songTime.setCellValueFactory(new PropertyValueFactory<>("songTime"));
        table.setItems(songList.getAllSongsObservable());

        table.setEditable(true);

        table.setOnKeyPressed(event -> {
            handleRemoveSongEvent(event);
            handleAddSongEvent(event);
            handleGoBackEvent(event);
            handleShowStatsEvent(event);
            handleTableExitEvent(event);
        });

        makeColumnsEditable();
    }

    /**
     * Makes the columns in the table editable and defines the behavior for
     * committing edits.
     *
     * @throws WrongInputException if there are issues with input data
     */
    private void makeColumnsEditable() throws WrongInputException {
        songTitle.setCellFactory(TextFieldTableCell.forTableColumn());
        songTitle.setOnEditCommit(event -> {
            try {
                Song song = event.getRowValue();
                String newTitle = event.getNewValue();
                songList.compareSongTitles(newTitle, titleChecker);
                song.setSongTitle(newTitle);
            } catch (WrongInputException e) {
                showErrorDialog(e.getMessage());
                table.refresh();
            }
        });

        authorName.setCellFactory(TextFieldTableCell.forTableColumn());
        authorName.setOnEditCommit(event -> {
            try {
                Song song = event.getRowValue();
                String newAuthorName = event.getNewValue();
                song.setAuthorName(newAuthorName);
            } catch (WrongInputException e) {
                showErrorDialog(e.getMessage());
                table.refresh();
            }
        });

        authorSurname.setCellFactory(TextFieldTableCell.forTableColumn());
        authorSurname.setOnEditCommit(event -> {
            try {
                Song song = event.getRowValue();
                String newAuthorSurname = event.getNewValue();
                song.setAuthorSurname(newAuthorSurname);
            } catch (WrongInputException e) {
                showErrorDialog(e.getMessage());
                table.refresh();
            }
        });

        songAlbum.setCellFactory(TextFieldTableCell.forTableColumn());
        songAlbum.setOnEditCommit(event -> {
            try {
                Song song = event.getRowValue();
                String newSongAlbum = event.getNewValue();
                song.setSongAlbum(newSongAlbum);
            } catch (WrongInputException e) {
                showErrorDialog(e.getMessage());
                table.refresh();
            }
        });

        songTime.setCellFactory(TextFieldTableCell.forTableColumn());
        songTime.setOnEditCommit(event -> {
            try {
                Song song = event.getRowValue();
                String newSongTime = event.getNewValue();
                song.setSongTime(newSongTime);
            } catch (WrongInputException e) {
                showErrorDialog(e.getMessage());
                table.refresh();
            }

        });

        songRelease.setCellFactory(TextFieldTableCell.forTableColumn());
        songRelease.setOnEditCommit(event -> {
            try {
                Song song = event.getRowValue();
                String newSongRelease = event.getNewValue();
                song.setSongRelease(newSongRelease);
            } catch (WrongInputException e) {
                showErrorDialog(e.getMessage());
                table.refresh();
            }
        });
    }

    /**
     * Displays an error dialog with the specified message.
     *
     * @param message the message to display in the error dialog
     */
    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    /**
     * Displays an info dialog with the specified label and message, where each
     * line is displayed separately.
     *
     * @param label the header label of the dialog
     * @param message the message to display in the info dialog
     */
    private void showInfoAlert(String label, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Some stats");
        alert.setHeaderText(label);

        TextArea textArea = new TextArea(message);
        textArea.setWrapText(true);
        textArea.setEditable(false);

        textArea.setPrefWidth(400);
        textArea.setPrefHeight(Region.USE_COMPUTED_SIZE);

        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }

    /**
     * Handles the action of adding a new song when the corresponding button is
     * pressed.
     *
     * @param event the action event triggered by the button
     * @throws IOException if there is an issue loading the AddSongScreen
     */
    @FXML
    private void addNewSong(ActionEvent event) throws IOException {
        app.setRoot("/mk/musiclibrarygui/views/AddSongScreen");
    }

    /**
     * Removes the selected song from the table.
     *
     * @throws WrongInputException if there is an issue with the input
     */
    @FXML
    private void removeSelectedSong() throws WrongInputException {
        removeSong();
    }

    /**
     * Removes a song from the song list based on the selected index in the
     * table.
     *
     * @throws WrongInputException if there is an issue with the input
     */
    private void removeSong() throws WrongInputException {
        try {
            int indexToRemove = table.getSelectionModel().getSelectedIndex();

            if (indexToRemove >= 0) {
                this.errorLabel.setText("");
                this.songList.deleteOneByIndex(indexToRemove);
                table.setItems(songList.getAllSongsObservable());

                Song.songCounter--;

                for (int i = indexToRemove; i < songList.getSongsArraySize(); i++) {
                    Song currentSong = songList.getOneByIndex(i);
                    currentSong.setSongID(currentSong.getSongID() - 1);
                    table.setItems(songList.getAllSongsObservable());
                }
            } else {
                this.errorLabel.setText("Select song to remove!");
                errorLabel.setVisible(true);
            }
        } catch (WrongInputException e) {
            showErrorDialog(e.getMessage());
        }
    }

    /**
     * Handles the action of displaying statistics when the corresponding event
     * is triggered. This method retrieves and shows information about unique
     * authors, the number of songs in each album, the shortest song, and the
     * longest song in the library using information from the song list.
     *
     * @param event the action event triggered when this method is called
     */
    @FXML
    void showStats(ActionEvent event) {
        showInfoAlert("Unique authors: ", songList.getUniqueAuthors());
        showInfoAlert("Songs in albums: ", songList.getSongCountPerAlbum());
        showInfoAlert("The shortest song in the library: ", songList.getTheShortestSong());
        showInfoAlert("The longest song in the library: ", songList.getTheLongestSong());
    }

    /**
     * Handles the action of going back to the previous screen when the button
     * is pressed.
     *
     * @param event the action event triggered by the button
     * @throws IOException if there is an issue loading the Menu screen
     */
    @FXML
    private void goBack(ActionEvent event) throws IOException {
        app.setRoot("/mk/musiclibrarygui/views/Menu");
    }

    /**
     * Handles keyboard events to add a new song when Ctrl+A is pressed.
     *
     * @param event the key event triggered by the keyboard
     */
    @FXML
    private void handleAddSongEvent(KeyEvent event) {
        if (event.isControlDown() && event.getCode() == KeyCode.A) {
            this.addSongButton.fire();
        }
    }

    /**
     * Handles keyboard events to go back when Ctrl+E is pressed.
     *
     * @param event the key event triggered by the keyboard
     */
    @FXML
    private void handleGoBackEvent(KeyEvent event) {
        if (event.isControlDown() && event.getCode() == KeyCode.E) {
            this.goBackButton.fire();
        }
    }

    /**
     * Handles keyboard events to remove a selected song when the Delete key is
     * pressed.
     *
     * @param event the key event triggered by the keyboard
     */
    @FXML
    private void handleRemoveSongEvent(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) {
            this.removeSongButton.fire();
        }
    }

    /**
     * Handles keyboard events to show the window with some stats when Ctrl+S is
     * pressed.
     *
     * @param event the key event triggered by the keyboard
     */
    @FXML
    void handleShowStatsEvent(KeyEvent event) {
        if (event.isControlDown() && event.getCode() == KeyCode.S) {
            this.someStatsButton.fire();
        }
    }

    /**
     * Handles keyboard events to exit the table when the Escape key is pressed.
     *
     * @param event the key event triggered by the keyboard
     */
    @FXML
    private void handleTableExitEvent(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            goBackButton.requestFocus();
            event.consume();
        }
    }
}
