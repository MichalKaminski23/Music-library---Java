package mk.musiclibrarygui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mk.models.SongList;
import mk.models.WrongInputException;

public class AddSongScreenController {

    @FXML
    private TextField titleField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField albumField;
    @FXML
    private TextField releaseField;
    @FXML
    private TextField timeField;
    @FXML
    private Label errorLabel;

    private SongList songList;

    public AddSongScreenController(SongList songList) {
        this.songList = songList;
    }

    @FXML
    private void submit(ActionEvent event) throws IOException, WrongInputException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        if (titleField.getText().isBlank()
                || nameField.getText().isBlank()
                || surnameField.getText().isBlank()
                || albumField.getText().isBlank()
                || releaseField.getText().isBlank()
                || timeField.getText().isBlank()) {
            this.errorLabel.setText("    Text fields can't be null!");
        } else {
            
            
        if (!isValidDateFormatInput(releaseField.getText(), formatter)) {
            errorLabel.setText("Invalid date format! Use DD-MM-YYYY.");
            return;
        }
            LocalDate releaseDate = LocalDate.parse(releaseField.getText(), formatter);

        try {
        if (!isValidIntegerInput(timeField.getText())) {
            errorLabel.setText("Invalid song time! Must be a positive integer.");
            return;
        }
    } catch (WrongInputException e) {
        errorLabel.setText(e.getMessage());
        return;
    }
    
    int songTime = Integer.parseInt(timeField.getText());
        
            this.songList.addSong(
                    titleField.getText(),
                    nameField.getText(),
                    surnameField.getText(),
                    albumField.getText(),
                    releaseDate,
                    songTime
            );

            App.setRoot("MusicTable");
        }
    }

    private boolean isValidIntegerInput(String songTimeInput) throws WrongInputException {

        if (!songTimeInput.matches("\\d+") ) {
            throw new WrongInputException("Please enter a valid integer for song time!");
        }
            int songTime = Integer.parseInt(songTimeInput);
            
            if(songTime <= 0)
            {
               throw new WrongInputException("Song time must be a positive integer!");
            }
            
        return true;
    }
    
    private boolean isValidDateFormatInput(String dateInput, DateTimeFormatter formatter) {
        try {
            LocalDate.parse(dateInput, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    @FXML
    private void goBack(ActionEvent event) throws IOException {
        App.setRoot("MusicTable");
    }

}
