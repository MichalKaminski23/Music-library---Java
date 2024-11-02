package mk.musiclibrarygui.models;

/**
 * The Song class represents a musical track with information about its ID,
 * title, composer, album, release date, and duration.
 *
 * @author Michal Kaminski
 * @version 2.0
 */
public class Song {

    /**
     * The title of the song.
     */
    private String songTitle;

    /**
     * The first name of the composer.
     */
    private String authorName;

    /**
     * The surname of the composer.
     */
    private String authorSurname;

    /**
     * The album the song belongs to.
     */
    private String songAlbum;

    /**
     * The release date of the song in the format 'dd.MM.yyyy'.
     */
    private String songRelease;

    /**
     * The duration of the song in seconds.
     */
    private String songTime;

    /**
     * The unique ID of the song, generated using the songCounter.
     */
    private int songID;

    /**
     * A static counter used to assign unique IDs to each song.
     */
    public static int songCounter = 1;

    /**
     * Constructs a new Song object with the given details.
     *
     * @param songTitle The title of the song
     * @param authorName The author's first name
     * @param authorSurname The author's surname
     * @param songAlbum The album name the song belongs to
     * @param songRelease The release date of the song
     * @param songTime The duration of the song in seconds
     */
    public Song(String songTitle, String authorName, String authorSurname, String songAlbum, String songRelease, String songTime) {
        this.songID = songCounter++;
        this.songTitle = songTitle;
        this.authorName = authorName;
        this.authorSurname = authorSurname;
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
     * @throws WrongInputException if the title is blank
     */
    public void setSongTitle(String songTitle) throws WrongInputException {
        if (songTitle.isBlank()) {
            throw new WrongInputException("Song title can't be empty!");
        }
        this.songTitle = songTitle;
    }

    /**
     * Gets the composer's first name.
     *
     * @return The composer's first name
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * Sets the composer's first name.
     *
     * @param authorName The new first name of the composer
     * @throws WrongInputException if the name is blank
     */
    public void setAuthorName(String authorName) throws WrongInputException {
        if (authorName.isBlank()) {
            throw new WrongInputException("Author name can't be empty!");
        }
        this.authorName = authorName;
    }

    /**
     * Gets the composer's surname.
     *
     * @return The composer's surname
     */
    public String getAuthorSurname() {
        return authorSurname;
    }

    /**
     * Sets the composer's surname.
     *
     * @param authorSurname The new surname of the composer
     * @throws WrongInputException if the surname is blank
     */
    public void setAuthorSurname(String authorSurname) throws WrongInputException {
        if (authorSurname.isBlank()) {
            throw new WrongInputException("Author surname can't be empty!");
        }
        this.authorSurname = authorSurname;
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
     * @throws WrongInputException if the album name is blank
     */
    public void setSongAlbum(String songAlbum) throws WrongInputException {
        if (songAlbum.isBlank()) {
            throw new WrongInputException("Album name can't be empty!");
        }
        this.songAlbum = songAlbum;
    }

    /**
     * Gets the release date of the song.
     *
     * @return The release date of the song
     */
    public String getSongRelease() {
        return songRelease;
    }

    /**
     * Sets the release date of the song.
     *
     * @param songRelease The new release date of the song
     * @throws WrongInputException if the date format is invalid
     */
    public void setSongRelease(String songRelease) throws WrongInputException {
        if (!songRelease.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new WrongInputException("Invalid date format! Please use dd.MM.yyyy.");
        }
        this.songRelease = songRelease;
    }

    /**
     * Gets the duration of the song in seconds.
     *
     * @return The song's duration in seconds
     */
    public String getSongTime() {
        return songTime;
    }

    /**
     * Sets the duration of the song.
     *
     * @param songTime The new duration of the song in seconds
     * @throws WrongInputException if the duration is blank or not a positive
     * integer
     */
    public void setSongTime(String songTime) throws WrongInputException {
        if (songTime.isBlank()) {
            throw new WrongInputException("Song time can't be empty!");
        } else if (!songTime.matches("\\d+")) {
            throw new WrongInputException("Song time must be a positive integer!");
        } else if (Integer.parseInt(songTime) <= 0) {
            throw new WrongInputException("Song time must be greater than 0!");
        }
        this.songTime = songTime;
    }

}