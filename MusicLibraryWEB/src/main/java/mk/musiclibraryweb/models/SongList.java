package mk.musiclibraryweb.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * The SongList class manages a collection of Song objects, providing methods to
 * add, delete, and retrieve songs.
 *
 * @author Michal Kaminski
 * @version 6.0
 */
@Data
public class SongList implements DataSource {

    /**
     * A list that holds all the Song objects.
     */
    private final ArrayList<Song> allSongs = new ArrayList<Song>();
    private boolean ok = false;
    
    public SongList()
    {
        allSongs.add(new Song("Title 1", "Name 1", "Surname 1", "Album 1", "12.01.2024", "111"));
        allSongs.add(new Song("Title 2", "Name 2", "Surname 2", "Album 2", "12.02.2024", "222"));
        allSongs.add(new Song("Title 3", "Name 3", "Surname 3", "Album 3", "12.03.2024", "333"));
    }
    
    @Override
    public List<Song> getAllSongs() {
        return allSongs;
    }
    
    @Override
    public boolean delete(int songID) {
        return allSongs.removeIf(i -> i.getSongID()== songID);
    }

    @Override
    public void persistObject(Object object) {
        if(object instanceof Song song) allSongs.add(song);
    }

    @Override
    public boolean update(Song song) {
        for(int i = 0; i < allSongs.size(); ++i) {
            if(allSongs.get(i).getSongID() == song.getSongID()) {
                allSongs.set(i, song);
                return true;
            }
        }        
        return false;
    }

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
            ok = false;
            throw new WrongInputException("Text fields can't be empty!");
        } else if (!songRelease.matches("(0[1-9]|[1-2][0-9]|3[0-1])\\.(0[1-9]|1[0-2])\\.(19|20)\\d{2}")) {
            ok = false;
            throw new WrongInputException("Invalid date format! Use dd.MM.yyyy!");
        } else if (!songTime.matches("\\d+") || Integer.parseInt(songTime) <= 0) {
            ok = false;
            throw new WrongInputException("Invalid time format! Use only positive numbers!");
        } else {
            ok = true;
        }

        if (ok == true) {
            compareSongTitles(songTitle, titleChecker);
            this.allSongs.add(new Song(songTitle, authorName, authorSurname, songAlbum, songRelease, songTime));
        }
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
     * @param songID The index of the song to delete
     */
    public boolean deleteOneByIndex(int songID) {
        return allSongs.removeIf(i -> i.getSongID() == songID);
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
     * Updates the song with the specified ID using the details of the given
     * Song object.
     *
     * Validates the songID and the provided Song object to ensure all fields
     * meet the required format and constraints. Throws exceptions for invalid
     * inputs.
     *
     * @param songID the unique identifier of the song to update
     * @param song the Song object containing the new details
     * @return true if the song with the specified ID exists and was updated,
     * false otherwise
     * @throws WrongInputException if: - songID is out of bounds, - any text
     * field in the Song object is empty, - the release date is not in the
     * format "dd.MM.yyyy", - the song time is not a positive number, - a song
     * with the same title already exists (excluding the current song).
     */
    public boolean updateSong(int songID, Song song) throws WrongInputException {
        var s = allSongs.stream().filter(i -> i.getSongID() == songID).findFirst();
        if (song.getSongTitle() == null || song.getSongTitle().isBlank() || song.getAuthorName() == null || song.getAuthorName().isBlank()
                || song.getAuthorSurname() == null || song.getAuthorSurname().isBlank() || song.getSongAlbum() == null || song.getSongAlbum().isBlank()
                || song.getSongRelease() == null || song.getSongRelease().isBlank() || song.getSongTime() == null || song.getSongTime().isBlank()) {
            throw new WrongInputException("Text fields can't be empty!");
        } else if (!song.getSongRelease().matches("(0[1-9]|[1-2][0-9]|3[0-1])\\.(0[1-9]|1[0-2])\\.(19|20)\\d{2}")) {
            throw new WrongInputException("Invalid date format! Use dd.MM.yyyy!");
        } else if (!song.getSongTime().matches("\\d+") || Integer.parseInt(song.getSongTime()) <= 0) {
            throw new WrongInputException("Invalid time format! Use only positive numbers!");
        }

        for (int i = 0; i < allSongs.size(); i++) {
            if (i != songID && titleChecker.checkTitle(allSongs.get(i).getSongTitle(), song.getSongTitle())) {
                throw new WrongInputException("A song with this title already exists: " + song.getSongTitle());
            }
        }

        if (s.isPresent()) {
            s.get().update(song);
        }

        return s.isPresent();
    }
}
