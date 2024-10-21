package com.mycompany.controllers;

import com.mycompany.music_library_app_gui.App;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * FXML Controller class
 *
 * @author Michal Kaminski
 * @version 1.0
 */
public class MainMenuController {


     
    @FXML
    public void showMusicLibrary(ActionEvent event) throws IOException {
        App.setRoot("/com/mycompany/views/MusicLibrary");
    }


}
