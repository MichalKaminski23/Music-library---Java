package mk.musiclibraryweb.models;

import java.util.List;

/**
 * Interface defining the methods for interacting with the data source (e.g.,
 * database). It provides basic CRUD operations for the {@link Song} and
 * {@link Album} entities, such as retrieving, adding, updating, deleting songs
 * and albums, as well as checking the uniqueness of song titles, song IDs,
 * album names, and album IDs.
 *
 * Implementations of this interface are responsible for managing the
 * persistence of {@link Song} and {@link Album} objects, ensuring data
 * integrity and consistency within the underlying data storage mechanism.
 *
 * Additional methods are provided to facilitate the association between songs
 * and albums, such as retrieving song titles by album ID.
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
     * Typically, this object is an instance of {@link Song} or {@link Album}.
     * The method persists the object in the underlying data storage.
     *
     * @param object the object to be added (usually a {@link Song} or
     * {@link Album} instance)
     */
    public void insert(Object object);

    /**
     * Updates an existing {@link Song} in the data source.
     *
     * This method merges the state of the given {@link Song} object into the
     * current persistence context, effectively updating the corresponding
     * record in the database.
     *
     * @param song the {@link Song} object with updated information
     * @return {@code true} if the update was successful, {@code false}
     * otherwise
     */
    public boolean update(Song song);

    /**
     * Deletes a song from the data source by its ID.
     *
     * This method removes the {@link Song} entity corresponding to the provided
     * {@code songID} from the data source.
     *
     * @param songID the ID of the song to be deleted
     * @return {@code true} if the song was deleted successfully, {@code false}
     * otherwise
     */
    public boolean delete(int songID);

    /**
     * Finds a song by its ID.
     *
     * Retrieves the {@link Song} entity that matches the specified
     * {@code songID}. If no such song exists, the method returns {@code null}.
     *
     * @param songID the ID of the song to be found
     * @return the {@link Song} object with the specified ID, or {@code null} if
     * not found
     */
    public Song findById(int songID);

    /**
     * Checks whether a song with the given title already exists in the data
     * source.
     *
     * This method is useful for enforcing uniqueness constraints on song
     * titles.
     *
     * @param songTitle the title of the song to check
     * @return {@code true} if a song with the specified title exists,
     * {@code false} otherwise
     */
    public boolean isSongTitleTaken(String songTitle);

    /**
     * Checks whether a song with the given ID already exists in the data
     * source.
     *
     * This method ensures that song IDs are unique within the data source.
     *
     * @param songID the ID of the song to check
     * @return {@code true} if a song with the specified ID exists,
     * {@code false} otherwise
     */
    public boolean isSongIDTaken(int songID);

    /**
     * Finds an album by its ID.
     *
     * Retrieves the {@link Album} entity that matches the specified
     * {@code albumID}. If no such album exists, the method returns
     * {@code null}.
     *
     * @param albumID the ID of the album to be found
     * @return the {@link Album} object with the specified ID, or {@code null}
     * if not found
     */
    public Album findAlbumByID(int albumID);

    /**
     * Retrieves all the albums stored in the data source.
     *
     * @return a list of all {@link Album} objects
     */
    public List<Album> getAllAlbums();

    /**
     * Deletes an album from the data source by its ID.
     *
     * This method removes the {@link Album} entity corresponding to the
     * provided {@code albumID} from the data source.
     *
     * @param albumID the ID of the album to be deleted
     * @return {@code true} if the album was deleted successfully, {@code false}
     * otherwise
     */
    public boolean deleteAlbum(int albumID);

    /**
     * Checks whether an album with the given name already exists in the data
     * source.
     *
     * This method is useful for enforcing uniqueness constraints on album
     * names.
     *
     * @param albumName the name of the album to check
     * @return {@code true} if an album with the specified name exists,
     * {@code false} otherwise
     */
    public boolean isAlbumNameTaken(String albumName);

    /**
     * Checks whether an album with the given ID already exists in the data
     * source.
     *
     * This method ensures that album IDs are unique within the data source.
     *
     * @param albumID the ID of the album to check
     * @return {@code true} if an album with the specified ID exists,
     * {@code false} otherwise
     */
    public boolean isAlbumIDTaken(int albumID);

    /**
     * Finds all song titles associated with a specific album ID.
     *
     * This method retrieves the titles of all {@link Song} entities that belong
     * to the {@link Album} with the specified {@code albumID}.
     *
     * @param albumID the ID of the album whose song titles are to be retrieved
     * @return a list of song titles associated with the specified album, or an
     * empty list if none are found
     */
    public List<String> findSongTitlesByAlbumID(int albumID);
}