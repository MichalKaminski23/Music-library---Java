package mk.musiclibrarygui;

import java.io.IOException;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import mk.models.Song;
import mk.models.SongList;
import mk.models.WrongInputException;

//dorobić: obsługiwanie - dat oraz czasu trwania (żeby lepiej działała edycja piosenek), 
//wyjątki (chyba - może z error dialog?), udogodnienia dla niepełnosprytnych, żeby nie było dwóch takich samych tytułów oraz podział na MVC
public class MusicTableController {

    @FXML
    private TableView<Song> table;
    @FXML
    private TableColumn<Song, Integer> songID;
    @FXML
    private TableColumn<Song, String> songTitle;
    @FXML
    private TableColumn<Song, String> authorName;
    @FXML
    private TableColumn<Song, String> authorSurname;
    @FXML
    private TableColumn<Song, String> songAlbum;
    @FXML
    private TableColumn<Song, LocalDate> songRelease;
    @FXML
    private TableColumn<Song, Integer> songTime;
    @FXML
    private Label errorLabel;

    private final SongList songList;

    public MusicTableController(SongList songList) {
        this.songList = songList;
    }

    @FXML
    public void initialize() {
        songID.setCellValueFactory(new PropertyValueFactory<>("songID"));
        songTitle.setCellValueFactory(new PropertyValueFactory<>("songTitle"));
        authorName.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        authorSurname.setCellValueFactory(new PropertyValueFactory<>("authorSurname"));
        songAlbum.setCellValueFactory(new PropertyValueFactory<>("songAlbum"));
        songRelease.setCellValueFactory(new PropertyValueFactory<>("songRelease"));
        songTime.setCellValueFactory(new PropertyValueFactory<>("songTime"));
        table.setItems(songList.getAllSongsObservable());

        table.setEditable(true);
        makeColumnsEditable();
    }

    private void makeColumnsEditable() {
        songTitle.setCellFactory(TextFieldTableCell.forTableColumn());
        songTitle.setOnEditCommit(event -> {
            Song song = event.getRowValue();
            String newTitle = event.getNewValue();
            if (newTitle == null || newTitle.trim().isEmpty()) {
                showErrorDialog("Song title can't be empty!");
                table.refresh();
            } else {
                song.setSongTitle(newTitle);
            }
        });

        authorName.setCellFactory(TextFieldTableCell.forTableColumn());
        authorName.setOnEditCommit(event -> {
            Song song = event.getRowValue();
            String newAuthorName = event.getNewValue();
            if (newAuthorName == null || newAuthorName.trim().isEmpty()) {
                showErrorDialog("Author name can't be empty!");
                table.refresh();
            } else {
                song.setAuthorName(newAuthorName);
            }
        });

        authorSurname.setCellFactory(TextFieldTableCell.forTableColumn());
        authorSurname.setOnEditCommit(event -> {
            Song song = event.getRowValue();
            String newAuthorSurname = event.getNewValue();
            if (newAuthorSurname == null || newAuthorSurname.trim().isEmpty()) {
                showErrorDialog("Author surname can't be empty!");
                table.refresh();
            } else {
                song.setAuthorSurname(newAuthorSurname);
            }
        });

        songAlbum.setCellFactory(TextFieldTableCell.forTableColumn());
        songAlbum.setOnEditCommit(event -> {
            Song song = event.getRowValue();
            String newSongAlbum = event.getNewValue();
            if (newSongAlbum == null || newSongAlbum.trim().isEmpty()) {
                showErrorDialog("Album name can't be empty!");
                table.refresh();
            } else {
                song.setSongAlbum(newSongAlbum);
            }
        });

        songTime.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        songTime.setOnEditCommit(event -> {
            Song song = event.getRowValue();
            Integer newSongTime = event.getNewValue();

            if (newSongTime == null) {
                showErrorDialog("Song time can't be empty!");
                table.refresh();
            }
            else if (newSongTime <= 0)
            {
                showErrorDialog("Song time can't be lower than 0!");
                table.refresh();
            }
            else if(!newSongTime.toString().matches("\\d+")) // nie działa xd
            {
                showErrorDialog("Song time can't be a string!");
                table.refresh();
            }
            else
            {
                song.setSongTime(newSongTime);
            }

           
        });

        songRelease.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        songRelease.setOnEditCommit(event -> {
            Song song = event.getRowValue();
            LocalDate newSongRelease = event.getNewValue();
            if (newSongRelease == null) {
                showErrorDialog("Release date can't be empty or invalid!");
                table.refresh();
            } else {
                song.setSongRelease(newSongRelease);
            }
        });
    }

    
    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void addNewSong(ActionEvent event) throws IOException {
        App.setRoot("AddSongScreen");
    }

    @FXML
    private void removeSong(ActionEvent event) {
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
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        App.setRoot("Menu");
    }

}
