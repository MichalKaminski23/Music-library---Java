package Model;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The SongList class manages a collection of songs. It provides methods to add,
 * remove, and update songs in the list.
 *
 * @author Michal Kaminski
 * @version 1.0
 */
public class SongList {

    /**
     * Empty constructor for the SongList class.
     */
    @SuppressWarnings("empty-statement")
    public SongList() {
        ;
    }

    /**
     * List to store Song objects
     */
    public ArrayList<Song> songs = new ArrayList<Song>();

    /**
     * Adds a new song to the list.
     *
     * @param songTitle The title of the song
     * @param composerName The name of the composer
     * @param composerSurname The surname of the composer
     * @param songAlbum The album of the song
     * @param songReleaseInput The release date in string format (DD-MM-YYYY)
     * @param songTimeInput The duration of the song in string format
     * @throws WrongInputException If any string input is empty or bad
     */
    public void addSongToList(String songTitle, String composerName, String composerSurname, String songAlbum, String songReleaseInput, String songTimeInput) throws WrongInputException {

        LocalDate songRelease = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        int songTime;

        for (Song song : songs) {
            if (song.getSongTitle().equalsIgnoreCase(songTitle)) {
                System.out.println("");
                System.out.println("A song with this title already exists: " + songTitle + " edit this song or create another title");
                return;
            }
        }

        if (songTitle.isBlank()) {
            System.out.println("");
            throw new WrongInputException("Song title can't be empty!");
        }

        if (composerName.isBlank()) {
            System.out.println("");
            throw new WrongInputException("Composer name can't be empty!");
        }

        if (composerSurname.isBlank()) {
            System.out.println("");
            throw new WrongInputException("Composer surname can't be empty!");
        }

        if (songAlbum.isBlank()) {
            System.out.println("");
            throw new WrongInputException("Song album can't be empty!");
        }

        if (isValidDateFormat(songReleaseInput, formatter)) {
            songRelease = LocalDate.parse(songReleaseInput, formatter);
        } else {
            throw new WrongInputException("Invalid date format. Please use DD-MM-YYYY.");
        }

        songTime = Integer.parseInt(songTimeInput);
        if (songTime <= 0 || songTimeInput.isBlank()) {
            System.out.println("");
            throw new WrongInputException("Song time can't be lower than 0 or be a float!");
        }

        Song newSong = new Song(songTitle, composerName, composerSurname, songAlbum, songRelease, songTime);
        songs.add(newSong);
        System.out.println("Song added successfully.");
    }

    /**
     * Checks if the given date string matches the expected date format.
     *
     * This method attempts to parse the input date string using the provided if
     * the parsing succeeds, the date format is considered valid; otherwise, it
     * is invalid.
     *
     * @param dateInput The date string to be checked.
     * @param formatter The formatter used to parse the date string.
     * @return {@code true} if the date string is valid according to the
     * specified formatter; {@code false} otherwise.
     */
    private boolean isValidDateFormat(String dateInput, DateTimeFormatter formatter) {
        try {
            LocalDate.parse(dateInput, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Removes a song from the list by title.
     *
     * @param songTitle The title of the song to be removed
     * @throws WrongInputException If the song title is empty
     */
    public void removeSongFromListByName(String songTitle) throws WrongInputException {

        if (songTitle.isBlank()) {
            System.out.println("");
            throw new WrongInputException("Song title can't be null!");
        }

        Song songToRemove = null;
        int indexToRemove = -1;

        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getSongTitle().equals(songTitle)) {
                songToRemove = songs.get(i);
                indexToRemove = i;
                break;
            }
        }

        if (songToRemove != null) {
            songs.remove(songToRemove);
            Song.songCounter--;

            for (int i = indexToRemove; i < songs.size(); i++) {
                Song currentSong = songs.get(i);
                currentSong.setSongID(currentSong.getSongID() - 1);
            }
            System.out.println("Song is removed.");
        } else {
            System.out.println("Bad song's name.");
        }

    }

    /**
     * Removes a song from the list by ID.
     *
     * @param songIDInput The ID of the song to be removed
     * @throws WrongInputException If the songID input is empty
     */
    public void removeSongFromListByID(String songIDInput) throws WrongInputException {

        int songID;

        if (songIDInput.isBlank()) {
            System.out.println("");
            throw new WrongInputException("Song ID can't be a string!");
        }

        if (!songIDInput.matches("\\d+")) {
            throw new WrongInputException("Invalid input: Song ID must be a number.");
        }

        songID = Integer.parseInt(songIDInput);

        Song songToRemove = null;
        int indexToRemove = -1;
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getSongID() == songID) {
                songToRemove = songs.get(i);
                indexToRemove = i;
                break;
            }
        }

        if (songToRemove != null) {
            songs.remove(songToRemove);
            Song.songCounter--;

            for (int i = indexToRemove; i < songs.size(); i++) {
                Song currentSong = songs.get(i);
                currentSong.setSongID(currentSong.getSongID() - 1);
            }
            System.out.println("Song is removed.");
        } else {
            System.out.println("Bad song's ID.");
        }

    }

    /**
     * Updates the details of a song in the list.
     *
     * @param songIDInput The ID of the song to be updated
     * @param newSongTitle The new title of the song
     * @param newComposerName The new name of the composer
     * @param newComposerSurname The new surname of the composer
     * @param newSongAlbum The new album of the song
     * @param newSongReleaseInput The new release date in string format
     * (DD-MM-YYYY)
     * @param newSongTimeInput The new duration of the song in string format
     * @throws WrongInputException If any string input is empty or bad
     */
    public void updateSong(String songIDInput, String newSongTitle, String newComposerName, String newComposerSurname, String newSongAlbum, String newSongReleaseInput, String newSongTimeInput) throws WrongInputException {

        LocalDate newTempReleaseDate = LocalDate.parse("2000-01-01");
        LocalDate songRelease = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        int songTime;
        int songID;

        if (songIDInput.isBlank()) {
            System.out.println("");
            throw new WrongInputException("Song ID can't be string!");
        }

        if (!songIDInput.matches("\\d+")) {
            throw new WrongInputException("Invalid input: Song ID must be a number.");
        }

        songID = Integer.parseInt(songIDInput);

        if (newSongTitle.isBlank()) {
            System.out.println("");
            throw new WrongInputException("Song title can't be empty!");
        } else {
            songs.get(songID - 1).setSongTitle(newSongTitle);
        }

        if (newComposerName.isBlank()) {
            System.out.println("");
            throw new WrongInputException("Composer name can't be empty!");
        } else {
            songs.get(songID - 1).setComposerName(newComposerName);
        }

        if (newComposerSurname.isBlank()) {
            System.out.println("");
            throw new WrongInputException("Composer surname can't be empty!");
        } else {
            songs.get(songID - 1).setComposerSurname(newComposerSurname);
        }

        if (newSongAlbum.isBlank()) {
            System.out.println("");
            throw new WrongInputException("Song album can't be empty!");
        } else {
            songs.get(songID - 1).setSongAlbum(newSongAlbum);
        }

        if (isValidDateFormat(newSongReleaseInput, formatter)) {
            songRelease = LocalDate.parse(newSongReleaseInput, formatter);
            songs.get(songID - 1).setReleaseDate(songRelease);
        } else {
            throw new WrongInputException("Invalid date format. Please use DD-MM-YYYY.");
        }

        songTime = Integer.parseInt(newSongTimeInput);
        if (songTime <= 0 || newSongTimeInput.isBlank()) {
            System.out.println("");
            throw new WrongInputException("Song time can't be lower than 0 or be a float!");
        } else {
            songs.get(songID - 1).setSongTime(songTime);
        }
    }

}
