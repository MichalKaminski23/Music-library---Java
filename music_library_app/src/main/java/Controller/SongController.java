package Controller;

import Model.Song;
import View.SongView;
import java.time.LocalDate;

/**
 * Controller class for managing song data between the model and view. It allows
 * setting and retrieving song information and updating the view.
 *
 * @author Michal Kaminski
 * @version 1.0
 */
public class SongController {

    private final Song model;
    private final SongView view;

    /**
     * Constructor for SongController. Initializes the model and view objects.
     *
     * @param model The song model object
     * @param view The song view object
     */
    public SongController(Song model, SongView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Sets the title of the song in the model.
     *
     * @param songTitle The title of the song
     */
    public void setSongTitle(String songTitle) {
        model.setSongTitle(songTitle);
    }

    /**
     * Gets the title of the song from the model.
     *
     * @return The title of the song
     */
    public String getSongTitle() {
        return model.getSongTitle();
    }

    /**
     * Sets the composer's name of the song in the model.
     *
     * @param composerName The composer's first name
     */
    public void setSongComposerName(String composerName) {
        model.setComposerName(composerName);
    }

    /**
     * Retrieves the composer's first name of the song from the model.
     *
     * @return The composer's first name of the song
     */
    public String getSongComposerName() {
        return model.getComposerName();
    }

    /**
     * Sets the composer's surname of the song in the model.
     *
     * @param composerSurname The composer's surname
     */
    public void setSongComposerSurname(String composerSurname) {
        model.setComposerSurname(composerSurname);
    }

    /**
     * Retrieves the composer's surname of the song from the model.
     *
     * @return The composer's surname of the song
     */
    public String getSongComposerSurname() {
        return model.getComposerSurname();
    }

    /**
     * Sets the album of the song in the model.
     *
     * @param songAlbum The album of the song
     */
    public void setSongAlbum(String songAlbum) {
        model.setSongAlbum(songAlbum);
    }

    /**
     * Retrieves the album of the song from the model.
     *
     * @return The album of the song
     */
    public String getSongAlbum() {
        return model.getSongAlbum();
    }

    /**
     * Sets the release date of the song in the model.
     *
     * @param songRelease The release date of the song (LocalDate)
     */
    public void setSongReleaseDate(LocalDate songRelease) {
        model.setReleaseDate(songRelease);
    }

    /**
     * Retrieves the release date of the song from the model.
     *
     * @return The release date of the song (LocalDate)
     */
    public LocalDate getSongReleaseDate() {
        return model.getReleaseDate();
    }

    /**
     * Sets the duration time of the song in the model.
     *
     * @param songTime The duration of the song in seconds
     */
    public void setSongTime(int songTime) {
        model.setSongTime(songTime);
    }

    /**
     * Retrieves the duration time of the song from the model.
     *
     * @return The duration of the song in seconds as an int
     */
    public int getSongTime() {
        return model.getSongTime();
    }

    /**
     * Displays the song information using the view. Retrieves song information
     * from the model and sends it to the view for display.
     */
    public void printView() {
        view.printSongInformation(model.getSongID(), model.getSongTitle(), model.getComposerName(), model.getComposerSurname(), model.getSongAlbum(), model.getReleaseDate().toString(), model.getSongTime());
    }
}
