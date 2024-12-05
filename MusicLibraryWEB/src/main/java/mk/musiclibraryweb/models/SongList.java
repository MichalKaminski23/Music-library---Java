package mk.musiclibraryweb.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Data;

/**
 * The SongList class manages a collection of Song objects, providing methods to
 * add, delete, and retrieve songs.
 *
 * @author Michal Kaminski
 * @version 5.0
 */
@Data
public class SongList {

    /**
     * A list that holds all the Song objects.
     */
    private final ArrayList<Song> allSongs = new ArrayList<Song>();

    /**
     * A SongTitleChecker instance that checks if the existing song title is the
     * same as the new song title, ignoring case differences. This lambda
     * expression provides a convenient way to enforce title uniqueness when
     * adding or editing songs in the music library.
     *
     * The comparison is case-insensitive, meaning that titles such as "My Song"
     * and "my song" will be considered identical.
     */
    private static final SongTitleChecker titleChecker = (existingTitle, newTitle)
            -> existingTitle.equalsIgnoreCase(newTitle);

    /**
     * Adds a new song to the list after validating the input.
     *
     * @param songTitle The title of the song
     * @param authorName The composer's first name
     * @param authorSurname The composer's surname
     * @param songAlbum The album name the song belongs to
     * @param songRelease The release date of the song
     * @param songTime The duration of the song in seconds
     * @throws WrongInputException if any input is invalid
     */
    public void addSong(String songTitle, String authorName, String authorSurname, String songAlbum, String songRelease, String songTime) throws WrongInputException {
        if (songTitle == null || songTitle.isBlank() || authorName == null || authorName.isBlank()
                || authorSurname == null || authorSurname.isBlank() || songAlbum == null || songAlbum.isBlank()
                || songRelease == null || songRelease.isBlank() || songTime == null || songTime.isBlank()) {
            throw new WrongInputException("Text fields can't be empty!");
        } else if (!songRelease.matches("(0[1-9]|[1-2][0-9]|3[0-1])\\.(0[1-9]|1[0-2])\\.(19|20)\\d{2}")) {
            throw new WrongInputException("Invalid date format! Use dd.MM.yyyy!");
        } else if (!songTime.matches("\\d+") || Integer.parseInt(songTime) <= 0) {
            throw new WrongInputException("Invalid time format! Use only positive numbers!");
        }
        compareSongTitles(songTitle, titleChecker);
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
     */
    public Song getOneByIndex(int index) {
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

        for (int i = 0; i < allSongs.size(); i++) {
            allSongs.get(i).setSongID(i);
        }
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
    
    /**
 * Updates the details of a song at the specified index in the list.
 *
 * @param index          The index of the song to update
 * @param newSongTitle   The new title of the song
 * @param newAuthorName  The new author's first name
 * @param newAuthorSurname The new author's surname
 * @param newSongAlbum   The new album name
 * @param newSongRelease The new release date in the format 'dd.MM.yyyy'
 * @param newSongTime    The new duration of the song in seconds
 * @throws WrongInputException if the index is invalid or the new input data is invalid
 */
public void updateSong(int index, String newSongTitle, String newAuthorName, String newAuthorSurname, 
                       String newSongAlbum, String newSongRelease, String newSongTime) throws WrongInputException {
    if (index < 0 || index >= allSongs.size()) {
        throw new WrongInputException("Index out of bounds: " + index);
    }
    if (newSongTitle == null || newSongTitle.isBlank() || newAuthorName == null || newAuthorName.isBlank()
            || newAuthorSurname == null || newAuthorSurname.isBlank() || newSongAlbum == null || newSongAlbum.isBlank()
            || newSongRelease == null || newSongRelease.isBlank() || newSongTime == null || newSongTime.isBlank()) {
        throw new WrongInputException("Text fields can't be empty!");
    } else if (!newSongRelease.matches("(0[1-9]|[1-2][0-9]|3[0-1])\\.(0[1-9]|1[0-2])\\.(19|20)\\d{2}")) {
        throw new WrongInputException("Invalid date format! Use dd.MM.yyyy!");
    } else if (!newSongTime.matches("\\d+") || Integer.parseInt(newSongTime) <= 0) {
        throw new WrongInputException("Invalid time format! Use only positive numbers!");
    }

    // Exclude the current song during title uniqueness check
    for (int i = 0; i < allSongs.size(); i++) {
        if (i != index && titleChecker.checkTitle(allSongs.get(i).getSongTitle(), newSongTitle)) {
            throw new WrongInputException("A song with this title already exists: " + newSongTitle);
        }
    }

    // Update song details
    Song songToUpdate = allSongs.get(index);
    songToUpdate.setSongTitle(newSongTitle);
    songToUpdate.setAuthorName(newAuthorName);
    songToUpdate.setAuthorSurname(newAuthorSurname);
    songToUpdate.setSongAlbum(newSongAlbum);
    songToUpdate.setSongRelease(newSongRelease);
    songToUpdate.setSongTime(newSongTime);
}

}
