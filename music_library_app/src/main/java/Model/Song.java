package Model;

import java.time.LocalDate;

/**
 * The Song class represents a musical track with information about its title,
 * composer, album, release date, and duration.
 *
 * @author Michal Kaminski
 * @version 1.0
 */
public class Song {

    /**
     * The title of the song.
     */
    private String songTitle;

    /**
     * The first name of the composer.
     */
    private String composerName;

    /**
     * The surname of the composer.
     */
    private String composerSurname;

    /**
     * The album the song belongs to.
     */
    private String songAlbum;

    /**
     * The release date of the song (as a LocalDate object).
     */
    private LocalDate songRelease;

    /**
     * The duration of the song in seconds.
     */
    private int songTime;

    /**
     * The unique ID of the song, generated using the songCounter.
     */
    private int songID;

    /**
     * A static counter used to assign unique IDs to each song.
     */
    static int songCounter = 1;

    /**
     * Constructs a new Song object with the given details.
     *
     * @param songTitle The title of the song
     * @param composerName The composer's first name
     * @param composerSurname The composer's surname
     * @param songAlbum The album name the song belongs to
     * @param songRelease The release date of the song (LocalDate)
     * @param songTime The duration of the song in seconds
     */
    public Song(String songTitle, String composerName, String composerSurname, String songAlbum, LocalDate songRelease, int songTime) {
        this.songID = songCounter++;
        this.songTitle = songTitle;
        this.composerName = composerName;
        this.composerSurname = composerSurname;
        this.songAlbum = songAlbum;
        this.songRelease = songRelease;
        this.songTime = songTime;
    }

    /**
     * Gets the unique ID of the song.
     *
     * @return The song's ID
     */
    public int getSongID() {
        return songID;
    }

    /**
     * Sets the unique ID for the song.
     *
     * @param songID The song's new ID
     */
    public void setSongID(int songID) {
        this.songID = songID;
    }

    /**
     * Gets the title of the song.
     *
     * @return The song's title
     */
    public String getSongTitle() {
        return songTitle;
    }

    /**
     * Sets the title of the song.
     *
     * @param songTitle The new title of the song
     */
    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    /**
     * Gets the composer's first name.
     *
     * @return The composer's first name
     */
    public String getComposerName() {
        return composerName;
    }

    /**
     * Sets the composer's first name.
     *
     * @param composerName The new first name of the composer
     */
    public void setComposerName(String composerName) {
        this.composerName = composerName;
    }

    /**
     * Gets the composer's surname.
     *
     * @return The composer's surname
     */
    public String getComposerSurname() {
        return composerSurname;
    }

    /**
     * Sets the composer's surname.
     *
     * @param composerSurname The new surname of the composer
     */
    public void setComposerSurname(String composerSurname) {
        this.composerSurname = composerSurname;
    }

    /**
     * Gets the album name of the song.
     *
     * @return The song's album name
     */
    public String getSongAlbum() {
        return songAlbum;
    }

    /**
     * Sets the album name of the song.
     *
     * @param songAlbum The new album name of the song
     */
    public void setSongAlbum(String songAlbum) {
        this.songAlbum = songAlbum;
    }

    /**
     * Gets the release date of the song.
     *
     * @return The release date (LocalDate) of the song
     */
    public LocalDate getReleaseDate() {
        return songRelease;
    }

    /**
     * Sets the release date of the song.
     *
     * @param songRelease The new release date of the song (LocalDate)
     */
    public void setReleaseDate(LocalDate songRelease) {
        this.songRelease = songRelease;
    }

    /**
     * Gets the duration time of the song in seconds.
     *
     * @return The song's duration in seconds
     */
    public int getSongTime() {
        return songTime;
    }

    /**
     * Sets the duration time of the song in seconds.
     *
     * @param songTime The new duration of the song in seconds
     */
    public void setSongTime(int songTime) {
        this.songTime = songTime;
    }
}
