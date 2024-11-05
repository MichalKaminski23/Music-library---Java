package mk.musiclibrarygui.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import lombok.Data;

/**
 * The SongList class manages a collection of Song objects, providing methods to
 * add, delete, and retrieve songs.
 *
 * @author Michal Kaminski
 * @version 3.0
 */
@Data
public class SongList {

    /**
     * A list that holds all the Song objects.
     */
    private final ArrayList<Song> allSongs = new ArrayList<Song>();

    /**
     * Constructs a new SongList object and initializes it with some example
     * songs.
     */
    public SongList() {
        allSongs.add(new Song("Example title 1", "Example name 1", "Example surname 1", "Example album 1", "01.12.2003", "420"));
        allSongs.add(new Song("Example title 2", "Example name 2", "Example surname 2", "Example album 2", "02.12.2003", "421"));
        allSongs.add(new Song("Example title 3", "Example name 3", "Example surname 3", "Example album 3", "03.12.2003", "422"));
        allSongs.add(new Song("Example title 4", "Example name 4", "Example surname 4", "Example album 4", "04.12.2003", "423"));
        allSongs.add(new Song("Example title 5", "Example name 5", "Example surname 5", "Example album 5", "05.12.2003", "424"));
    }

    /**
     * Adds a new song to the list after validating the input.
     *
     * @param songTitle The title of the song
     * @param authorName The composer's first name
     * @param authorSurname The composer's surname
     * @param songAlbum The album name the song belongs to
     * @param songRelease The release date of the song
     * @param songTime The duration of the song in seconds
     * @param titleChecker A functional interface to define the title comparison
     * logic
     * @throws WrongInputException if any input is invalid
     */
    public void addSong(String songTitle, String authorName, String authorSurname, String songAlbum, String songRelease, String songTime, SongTitleChecker titleChecker) throws WrongInputException {
        for (Song song : allSongs) {
            if (titleChecker.checkTitle(song.getSongTitle(), songTitle)) {
                throw new WrongInputException("A song with this title already exists: " + songTitle);
            }
        }
        if (!songRelease.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new WrongInputException("Invalid date format! Use dd.MM.yyyy!");
        }
        if (!songTime.matches("\\d+") || Integer.parseInt(songTime) <= 0) {
            throw new WrongInputException("Invalid time format! Use only positive numbers!");
        }
        if (songTitle.isBlank() || authorName.isBlank()
                || authorSurname.isBlank() || songAlbum.isBlank()
                || songRelease.isBlank() || songTime.isBlank()) {
            throw new WrongInputException("Text fields can't be empty!");
        }
        this.allSongs.add(new Song(songTitle, authorName, authorSurname, songAlbum, songRelease, songTime));
    }

    /**
     * Checks if the specified title already exists among the current song
     * titles in the list to ensure each song has a unique title. Utilizes a
     * {@link SongTitleChecker} functional interface to compare titles. If a
     * song with the same title is found, an exception is thrown.
     *
     * @param songNewTitle The title of the song to be compared for uniqueness
     * @param titleChecker A functional interface to define the title comparison
     * logic
     * @throws WrongInputException if a song with the specified title already
     * exists
     */
    public void compareSongTitles(String songNewTitle, SongTitleChecker titleChecker) throws WrongInputException {
        for (Song song : allSongs) {
            if (titleChecker.checkTitle(song.getSongTitle(), songNewTitle)) {
                throw new WrongInputException("A song with this title already exists: " + songNewTitle);
            }
        }
    }

    /**
     * Retrieves a song by its index in the list.
     *
     * @param index The index of the song to retrieve
     * @return The Song object at the specified index
     * @throws WrongInputException if the index is out of bounds
     */
    public Song getOneByIndex(int index) throws WrongInputException {
        if (index < 0 || index >= allSongs.size()) {
            throw new WrongInputException("Index out of bounds: " + index);
        }
        return allSongs.get(index);
    }

    /**
     * Deletes a song by its index in the list.
     *
     * @param index The index of the song to delete
     * @throws WrongInputException if the index is out of bounds
     */
    public void deleteOneByIndex(int index) throws WrongInputException {
        if (index < 0 || index >= allSongs.size()) {
            throw new WrongInputException("Index out of bounds: " + index);
        }
        this.allSongs.remove(index);
    }

    /**
     * Retrieves the number of songs in the list.
     *
     * @return The size of the songs list
     */
    public int getSongsArraySize() {
        return allSongs.size();
    }

    /**
     * Converts the list of songs to an ObservableList for use in JavaFX.
     *
     * @return An ObservableList containing all songs
     */
    public ObservableList<Song> getAllSongsObservable() {
        return FXCollections.observableArrayList(allSongs);
    }

    /**
     * Retrieves a set of unique authors from the song list and formats the
     * result as a string where each author's name is displayed on a separate
     * line.
     *
     * @return a formatted string containing unique authors
     */
    public String getUniqueAuthors() {
        if (allSongs.isEmpty()) {
            return "No songs available";
        }

        Set<String> uniqueAuthors = allSongs.stream()
                .map(song -> song.getAuthorName() + " " + song.getAuthorSurname())
                .collect(Collectors.toSet());

        StringBuilder result = new StringBuilder();
        uniqueAuthors.forEach(author
                -> result.append(author).append("\n")
        );

        return result.toString();
    }

    /**
     * Retrieves the count of songs per album and formats the result as a string
     * where each album and its corresponding count are displayed on separate
     * lines.
     *
     * @return a formatted string containing album names and their respective
     * song counts
     */
    public String getSongCountPerAlbum() {
        if (allSongs.isEmpty()) {
            return "No songs available";
        }
        Map<String, Long> songCountMap = allSongs.stream()
                .collect(Collectors.groupingBy(Song::getSongAlbum, Collectors.counting()));

        StringBuilder result = new StringBuilder();
        songCountMap.forEach((album, count)
                -> result.append(album).append(": ").append(count).append("\n")
        );

        return result.toString();
    }

    /**
     * Retrieves the title of the shortest song from the song list.
     *
     * @return the title of the shortest song, or null if the list is empty
     */
    public String getTheShortestSong() {
        return allSongs.stream()
                .min(Comparator.comparingInt(song -> Integer.valueOf(song.getSongTime())))
                .map(song -> song.getSongTitle() + " (duration: " + song.getSongTime() + " seconds)")
                .orElse("No songs available");
    }

    /**
     * Retrieves the title of the longest song from the song list.
     *
     * @return the title of the longest song, or null if the list is empty
     */
    public String getTheLongestSong() {
        return allSongs.stream()
                .max(Comparator.comparingInt(song -> Integer.valueOf(song.getSongTime())))
                .map(song -> song.getSongTitle() + " (duration: " + song.getSongTime() + " seconds)")
                .orElse("No songs available");
    }
}
