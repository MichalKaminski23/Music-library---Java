package mk.musiclibraryweb;

import jakarta.persistence.PersistenceException;
import java.util.stream.Stream;
import mk.musiclibraryweb.models.Album;
import mk.musiclibraryweb.models.DataBaseSource;
import mk.musiclibraryweb.models.Song;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit test class for testing the methods of {@link DataBaseSource}. This class
 * includes tests for insert, update, delete, findById, and other methods that
 * interact with the database.
 *
 * @author Michal Kaminski
 * @version 6.0
 */
public class DataBaseSourceTest {

    private DataBaseSource dataBaseSourceTest = new DataBaseSource();
    private static Album testAlbum = new Album(101, "Testing album");

    /**
     * Sets up the test environment before each test method is executed. It
     * ensures that no residual data exists in the database for testing.
     */
    @BeforeEach
    public void setUp() {
        dataBaseSourceTest.deleteAlbum(101);
        dataBaseSourceTest.deleteAlbum(102);
        dataBaseSourceTest.deleteAlbum(201);
        dataBaseSourceTest.deleteAlbum(301);
        dataBaseSourceTest.deleteAlbum(401);
        dataBaseSourceTest.deleteAlbum(501);
        dataBaseSourceTest.delete(1001);
        dataBaseSourceTest.delete(2001);
        dataBaseSourceTest.delete(3001);
        dataBaseSourceTest.delete(4001);
        dataBaseSourceTest.delete(5001);
    }

    /**
     * Provides a stream of valid songs for testing purposes.
     *
     * @return a stream of {@link Song} objects
     */
    static Stream<Song> provideValidSongs() {
        return Stream.of(
                new Song(1001, "Haha", "Ptysiek", "Koks", testAlbum, "12.12.2020", "300"),
                new Song(2001, "Hihi", "Rysiek", "Kozak", testAlbum, "01.01.2021", "150"),
                new Song(3001, "Hehe", "Zdzisiek", "Kozaczek", testAlbum, "20.07.2022", "180"),
                new Song(4001, "Uhuh", "Maniek", "Jackowski", testAlbum, "12.12.2003", "400"),
                new Song(5001, "Hura", "Lechu", "Polak", testAlbum, "04.02.2006", "555")
        );
    }

    /**
     * Provides a stream of null song objects for testing purposes.
     *
     * @return a stream of {@link Song} objects
     */
    static Stream<Song> provideNullSong() {
        return Stream.of(
                null,
                null
        );
    }

    /**
     * Provides a stream of valid song IDs for testing purposes.
     *
     * @return a stream of song IDs
     */
    static Stream<Integer> provideValidIDs() {
        return Stream.of(102, 201, 301, 401, 501);
    }

    /**
     * Provides a stream of invalid song IDs for testing purposes.
     *
     * @return a stream of invalid song IDs
     */
    static Stream<Integer> provideInvalidIDs() {
        return Stream.of(-1, 0, -100);
    }

    /**
     * Provides a stream of valid song titles for testing purposes.
     *
     * @return a stream of valid song titles
     */
    static Stream<String> provideValidTitles() {
        return Stream.of("Title 1", "Title 2", "Title 3");
    }

    /**
     * Provides a stream of valid album names for testing purposes.
     *
     * @return a stream of valid album names
     */
    static Stream<String> provideValidNames() {
        return Stream.of("Testing Album 1", "Testing Album 2", "Testing Album 3");
    }

    /**
     * Provides a stream of invalid song titles for testing purposes.
     *
     * @return a stream of invalid song titles
     */
    static Stream<String> provideInvalidTitles() {
        return Stream.of(null, "", "   ");
    }

    /**
     * Test for the insert method of the {@link DataBaseSource} class. It checks
     * whether a valid song can be inserted into the database.
     *
     * @param song the song to insert into the database
     */
    @ParameterizedTest
    @MethodSource("provideValidSongs")
    void testInsert(Song song) {
        try {
            dataBaseSourceTest.insert(testAlbum);
            dataBaseSourceTest.insert(song);
            dataBaseSourceTest.deleteAlbum(101);
        } catch (PersistenceException e) {
            assertEquals("Object can't be empty to insert!", e.getMessage(), "Incorrect exception message");
        }
    }

    /**
     * Test for the insert method when trying to insert a null song. It ensures
     * that a {@link PersistenceException} is thrown when trying to insert a
     * null song.
     *
     * @param song the null song to insert
     */
    @ParameterizedTest
    @MethodSource("provideNullSong")
    void testInsertNull(Song song) {
        try {
            dataBaseSourceTest.insert(song);
            fail("Object can't be null!");
        } catch (PersistenceException e) {
        }
    }

    /**
     * Test for the update method of the {@link DataBaseSource} class. It checks
     * whether a song can be updated in the database.
     *
     * @param song the song to update in the database
     */
    @ParameterizedTest
    @MethodSource("provideValidSongs")
    void testUpdate(Song song) {
        dataBaseSourceTest.insert(testAlbum);
        dataBaseSourceTest.insert(song);

        song.setSongTitle("Updated Title");
        assertDoesNotThrow(() -> dataBaseSourceTest.update(song), "Update failed for a valid song.");

        Song updatedSong = dataBaseSourceTest.findById(song.getSongID());
        dataBaseSourceTest.deleteAlbum(101);

        assertNotNull(updatedSong, "Updated song not found in the database.");
        assertEquals("Updated Title", updatedSong.getSongTitle(), "Song title was not updated correctly.");
    }

    /**
     * Test for the update method when trying to update a null song. It ensures
     * that a {@link PersistenceException} is thrown when trying to update a
     * null song.
     *
     * @param song the null song to update
     */
    @ParameterizedTest
    @MethodSource("provideNullSong")
    void testUpdateNull(Song song) {
        PersistenceException exception = assertThrows(PersistenceException.class,
                () -> dataBaseSourceTest.update(song));
        assertEquals("Song can't be empty to update!", exception.getMessage(),
                "Incorrect exception message for null object.");
    }

    /**
     * Test for the delete method of the {@link DataBaseSource} class. It checks
     * whether a song can be deleted by its ID.
     *
     * @param songID the song ID to delete
     */
    @ParameterizedTest
    @MethodSource("provideValidIDs")
    void testDeleteValidID(int songID) {
        dataBaseSourceTest.insert(testAlbum);
        Song song = new Song(songID, "Test Song", "Test Author", "Test Surname", testAlbum, "01.01.2023", "300");
        dataBaseSourceTest.insert(song);

        boolean result = dataBaseSourceTest.delete(songID);
        dataBaseSourceTest.deleteAlbum(101);
        assertTrue(result, "Song should be successfully deleted.");
        assertNull(dataBaseSourceTest.findById(songID), "Deleted song should not exist in the database.");
    }

    /**
     * Test for the delete method when trying to delete a song with an invalid
     * ID. It ensures that a {@link PersistenceException} is thrown when trying
     * to delete a song with an invalid ID.
     *
     * @param songID the invalid song ID
     */
    @ParameterizedTest
    @MethodSource("provideInvalidIDs")
    void testDeleteInvalidID(int songID) {
        PersistenceException exception = assertThrows(PersistenceException.class, () -> {
            dataBaseSourceTest.delete(songID);
        });

        assertEquals("Song ID can't be empty to delete!", exception.getMessage(), "Incorrect exception message.");
    }

    /**
     * Test for the findById method of the {@link DataBaseSource} class. It
     * checks whether a song can be found by its ID.
     *
     * @param songID the song ID to find
     */
    @ParameterizedTest
    @MethodSource("provideValidIDs")
    void testFindByIdValidID(int songID) {
        dataBaseSourceTest.insert(testAlbum);
        Song song = new Song(songID, "Test Song", "Test Author", "Test Surname", testAlbum, "01.01.2023", "300");
        dataBaseSourceTest.insert(song);

        Song foundSong = dataBaseSourceTest.findById(songID);
        dataBaseSourceTest.deleteAlbum(101);
        assertNotNull(foundSong, "Song should be found in the database.");
        assertEquals(songID, foundSong.getSongID(), "Found song ID does not match.");
    }

    /**
     * Test for the findById method when trying to find a song with an invalid
     * ID. It ensures that an {@link IllegalArgumentException} is thrown when
     * trying to find a song with an invalid ID.
     *
     * @param songID the invalid song ID
     */
    @ParameterizedTest
    @MethodSource("provideInvalidIDs")
    void testFindByIdInvalidID(int songID) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dataBaseSourceTest.findById(songID);
        });

        assertEquals("The song ID must be positive and not null.", exception.getMessage(), "Incorrect exception message.");
    }

    /**
     * Test for checking if a song title is already taken in the database. It
     * verifies whether the title exists in the database.
     *
     * @param songTitle the title to check
     */
    @ParameterizedTest
    @MethodSource("provideValidTitles")
    void testIsSongTitleTakenExistingTitle(String songTitle) {
        dataBaseSourceTest.insert(testAlbum);
        dataBaseSourceTest.insert(new Song(1001, "Title 1", "Author 1", "Surname 1", testAlbum, "12.12.2020", "300"));
        dataBaseSourceTest.insert(new Song(2001, "Title 2", "Author 2", "Surname 2", testAlbum, "01.01.2021", "150"));
        dataBaseSourceTest.insert(new Song(3001, "Title 3", "Author 3", "Surname 3", testAlbum, "20.07.2022", "180"));

        boolean isTaken = dataBaseSourceTest.isSongTitleTaken(songTitle);
        dataBaseSourceTest.deleteAlbum(101);
        assertTrue(isTaken, "Title should be marked as taken.");
    }

    /**
     * Test for checking if a song title is valid (not null, empty, or blank).
     * It ensures that an exception is thrown if the title is invalid.
     *
     * @param songTitle the title to check
     */
    @ParameterizedTest
    @MethodSource("provideInvalidTitles")
    void testIsSongTitleTakenInvalidTitle(String songTitle) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dataBaseSourceTest.isSongTitleTaken(songTitle);
        });

        assertEquals("Song title cannot be null, empty, or blank.", exception.getMessage(), "Incorrect exception message.");
    }

    /**
     * Test for checking if a song ID is already taken in the database. It
     * verifies whether the ID exists in the database.
     *
     * @param songID the song ID to check
     */
    @ParameterizedTest
    @MethodSource("provideValidIDs")
    void testIsSongIDTakenExistingID(int songID) {
        dataBaseSourceTest.insert(testAlbum);
        dataBaseSourceTest.insert(new Song(songID, "Title " + songID, "Author", "Surname", testAlbum, "12.12.2020", "300"));

        boolean isTaken = dataBaseSourceTest.isSongIDTaken(songID);
        dataBaseSourceTest.deleteAlbum(101);
        assertTrue(isTaken, "Song ID should be marked as taken.");
    }

    /**
     * Test for checking if a song ID is valid (greater than 0). It ensures that
     * an exception is thrown if the ID is invalid.
     *
     * @param songID the song ID to check
     */
    @ParameterizedTest
    @MethodSource("provideInvalidIDs")
    void testIsSongIDTakenInvalidID(int songID) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dataBaseSourceTest.isSongIDTaken(songID);
        });

        assertEquals("Song ID must be greater than 0.", exception.getMessage(), "Incorrect exception message.");
    }

    /**
     * Verifies that the method {@code findAlbumByID} correctly retrieves an
     * {@link Album} when given valid album IDs. The test inserts an album with
     * a specific ID, then queries the database and ensures the returned album
     * is not null and has the expected ID.
     *
     * @param albumID a valid album ID used to insert and retrieve the album
     */
    @ParameterizedTest
    @MethodSource("provideValidIDs")
    void testFindByAlbumIdValidID(int albumID) {
        Album album = new Album(albumID, "Test album");
        dataBaseSourceTest.insert(album);

        Album foundAlbum = dataBaseSourceTest.findAlbumByID(albumID);
        assertNotNull(foundAlbum, "Album should be found in the database.");
        assertEquals(albumID, foundAlbum.getAlbumID(), "Found album ID does not match.");
    }

    /**
     * Checks whether the method {@code findAlbumByID} throws an
     * {@link IllegalArgumentException} when an invalid album ID is provided.
     * The invalid IDs are supplied by the method source
     * {@code provideInvalidIDs}.
     *
     * @param albumID an invalid album ID used to invoke the method
     * {@code findAlbumByID}
     */
    @ParameterizedTest
    @MethodSource("provideInvalidIDs")
    void testFindByAlbumIdInvalidID(int albumID) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dataBaseSourceTest.findAlbumByID(albumID);
        });

        assertEquals("The album ID must be positive and not null.", exception.getMessage(), "Incorrect exception message.");
    }

    /**
     * Tests the {@code deleteAlbum} method using valid album IDs. Inserts a
     * test album, attempts to delete it, and verifies that the deletion was
     * successful by asserting the method's return value is {@code true} and
     * checking that the album no longer exists in the database.
     *
     * @param albumID a valid album ID used to insert and then delete the album
     */
    @ParameterizedTest
    @MethodSource("provideValidIDs")
    void testDeleteAlbumValidID(int albumID) {
        dataBaseSourceTest.insert(testAlbum);
        Album album = new Album(albumID, "Test album");

        boolean result = dataBaseSourceTest.deleteAlbum(albumID);

        assertTrue(result, "Album should be successfully deleted.");
        assertNull(dataBaseSourceTest.findAlbumByID(albumID), "Deleted album should not exist in the database.");
    }

    /**
     * Tests the {@code deleteAlbum} method using invalid album IDs. Expects a
     * {@link PersistenceException} to be thrown with a specific error message
     * indicating that the album ID cannot be empty.
     *
     * @param albumID an invalid album ID used to invoke the {@code deleteAlbum}
     * method
     */
    @ParameterizedTest
    @MethodSource("provideInvalidIDs")
    void testDeleteAlbumInvalidID(int albumID) {
        PersistenceException exception = assertThrows(PersistenceException.class, () -> {
            dataBaseSourceTest.deleteAlbum(albumID);
        });

        assertEquals("Album ID can't be empty to delete!", exception.getMessage(), "Incorrect exception message.");
    }

    /**
     * Verifies that the method {@code isAlbumNameTaken} returns {@code true}
     * for album names that already exist in the database. Three albums with
     * known names are inserted, and the method is tested with valid names that
     * should match the existing entries.
     *
     * @param albumName a valid album name expected to already be present in the
     * database
     */
    @ParameterizedTest
    @MethodSource("provideValidNames")
    void testIsAlbumTitleTakenExistingName(String albumName) {
        dataBaseSourceTest.insert(new Album(101, "Testing Album 1"));
        dataBaseSourceTest.insert(new Album(201, "Testing Album 2"));
        dataBaseSourceTest.insert(new Album(301, "Testing Album 3"));

        boolean isTaken = dataBaseSourceTest.isAlbumNameTaken(albumName);
        assertTrue(isTaken, "Name should be marked as taken.");
    }

    /**
     * Ensures that the method {@code isAlbumNameTaken} throws an
     * {@link IllegalArgumentException} when provided with invalid album names,
     * such as {@code null}, empty, or blank strings.
     *
     * @param albumName an invalid album name that should trigger an exception
     */
    @ParameterizedTest
    @MethodSource("provideInvalidTitles")
    void testIsAlbumTitleTakenInvalidName(String albumName) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dataBaseSourceTest.isAlbumNameTaken(albumName);
        });

        assertEquals("Album name cannot be null, empty, or blank.", exception.getMessage(), "Incorrect exception message.");
    }

    /**
     * Verifies that the method {@code isAlbumIDTaken} returns {@code true} for
     * album IDs that already exist in the database. An {@link Album} is first
     * inserted with the provided valid ID, and then the method is tested to
     * ensure it detects that the ID is already in use.
     *
     * @param albumID a valid album ID that is inserted into the database
     */
    @ParameterizedTest
    @MethodSource("provideValidIDs")
    void testIsAlbumIDTakenExistingID(int albumID) {
        dataBaseSourceTest.insert(new Album(albumID, "Title " + albumID));

        boolean isTaken = dataBaseSourceTest.isAlbumIDTaken(albumID);
        assertTrue(isTaken, "Album ID should be marked as taken.");
    }

    /**
     * Ensures that the method {@code isAlbumIDTaken} throws an
     * {@link IllegalArgumentException} when provided with invalid album IDs
     * (e.g., zero or negative values). The method is expected to detect these
     * invalid inputs and throw the appropriate exception.
     *
     * @param albumID an invalid album ID that should trigger an exception
     */
    @ParameterizedTest
    @MethodSource("provideInvalidIDs")
    void testIsAlbumIDTakenInvalidID(int albumID) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dataBaseSourceTest.isAlbumIDTaken(albumID);
        });

        assertEquals("Album ID must be greater than 0.", exception.getMessage(), "Incorrect exception message.");
    }
}
