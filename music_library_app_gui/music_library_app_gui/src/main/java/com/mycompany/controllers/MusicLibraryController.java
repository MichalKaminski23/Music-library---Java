package com.mycompany.controllers;

import com.mycompany.music_library_app_gui.App;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.mycompany.models.Song;
import com.mycompany.models.SongList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.ObservableList;

import javafx.scene.control.TableColumn;
/**
 * FXML Controller class
 *
 * @author Michal Kaminski
 * @version 1.0
 */
public class MusicLibraryController  {

    @FXML
    public TableColumn songTitle;
    @FXML
    public TableColumn authorName;
    @FXML
    public TableColumn authorSurname;
    @FXML
    public TableColumn songAlbum;
    @FXML
    public TableColumn releaseDate;
    @FXML
    public TableColumn durationTime;
    @FXML
    public TableColumn songID;
    
    public final ObservableList<Song> data = null;  
    
    @FXML
    public void addNewSong(ActionEvent event) {
        String songReleaseInput = "12-12-2000";
        LocalDate songRelease;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        songRelease = LocalDate.parse(songReleaseInput, formatter);
        data.add(new Song("a", "b", "c", "d", songRelease, 322));
    }

    @FXML
    public void removeSong(ActionEvent event) {
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        App.setRoot("/com/mycompany/views/MainMenu");
    }
}
