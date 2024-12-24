package mk.musiclibraryweb.models;

import java.util.List;

/**
 * Interface defining the methods for interacting with the data source (e.g., database).
 * It provides basic CRUD operations for the {@link Song} entity, such as retrieving,
 * adding, updating, deleting songs, as well as checking the uniqueness of song titles and IDs.
 * 
 * @author Michal Kaminski
 * @version 6.0
 */
public interface DataSource {

    /**
     * Retrieves all the songs stored in the data source.
     * 
     * @return a list of all {@link Song} objects
     */
    public List<Song> getAllSongs();

    /**
     * Adds a new object to the data source.
     * 
     * @param object the object to be added (usually a {@link Song} instance)
     */
    public void insert(Object object);

    /**
     * Updates an existing {@link Song} in the data source.
     * 
     * @param song the {@link Song} object with updated information
     * @return {@code true} if the update was successful, {@code false} otherwise
     */
    public boolean update(Song song);

    /**
     * Deletes a song from the data source by its ID.
     * 
     * @param songID the ID of the song to be deleted
     * @return {@code true} if the song was deleted successfully, {@code false} otherwise
     */
    public boolean delete(int songID);

    /**
     * Finds a song by its ID.
     * 
     * @param songID the ID of the song to be found
     * @return the {@link Song} object with the specified ID, or {@code null} if not found
     */
    public Song findById(int songID);

    /**
     * Checks whether a song with the given title already exists in the data source.
     * 
     * @param songTitle the title of the song to check
     * @return {@code true} if a song with the specified title exists, {@code false} otherwise
     */
    public boolean isSongTitleTaken(String songTitle);

    /**
     * Checks whether a song with the given ID already exists in the data source.
     * 
     * @param songID the ID of the song to check
     * @return {@code true} if a song with the specified ID exists, {@code false} otherwise
     */
    public boolean isSongIDTaken(int songID);
}
