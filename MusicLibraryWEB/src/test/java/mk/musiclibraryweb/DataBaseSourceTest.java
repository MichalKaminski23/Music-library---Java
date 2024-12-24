package mk.musiclibraryweb;

import jakarta.persistence.PersistenceException;
import java.util.stream.Stream;
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

    private DataBaseSource dataBaseSourceTest;

    /**
     * Sets up the test environment before each test method is executed. It
     * ensures that no residual data exists in the database for testing.
     */
    @BeforeEach
    public void setUp() {
        dataBaseSourceTest = new DataBaseSource();
        dataBaseSourceTest.delete(1);
        dataBaseSourceTest.delete(2);
        dataBaseSourceTest.delete(3);
        dataBaseSourceTest.delete(4);
        dataBaseSourceTest.delete(5);
    }

    /**
     * Provides a stream of valid songs for testing purposes.
     *
     * @return a stream of {@link Song} objects
     */
    static Stream<Song> provideValidSongs() {
        return Stream.of(
                new Song(1, "Haha", "Ptysiek", "Koks", "Muza", "12.12.2020", "300"),
                new Song(2, "Hihi", "Rysiek", "Kozak", "Jazz", "01.01.2021", "150"),
                new Song(3, "Hehe", "Zdzisiek", "Kozaczek", "Pop", "20.07.2022", "180"),
                new Song(4, "Uhuh", "Maniek", "Jackowski", "Rap", "12.12.2003", "400"),
                new Song(5, "Hura", "Lechu", "Polak", "WOW", "04.02.2006", "555")
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
    static Stream<Integer> provideValidSongIDs() {
        return Stream.of(1, 2, 3, 4, 5);
    }

    /**
     * Provides a stream of invalid song IDs for testing purposes.
     *
     * @return a stream of invalid song IDs
     */
    static Stream<Integer> provideInvalidSongIDs() {
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
            dataBaseSourceTest.insert(song);
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
        dataBaseSourceTest.insert(song);

        song.setSongTitle("Updated Title");
        assertDoesNotThrow(() -> dataBaseSourceTest.update(song), "Update failed for a valid song.");

        Song updatedSong = dataBaseSourceTest.findById(song.getSongID());
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
    @MethodSource("provideValidSongIDs")
    void testDeleteValidID(int songID) {
        Song song = new Song(songID, "Test Song", "Test Author", "Test Surname", "Test Album", "01.01.2023", "300");
        dataBaseSourceTest.insert(song);

        boolean result = dataBaseSourceTest.delete(songID);

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
    @MethodSource("provideInvalidSongIDs")
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
    @MethodSource("provideValidSongIDs")
    void testFindByIdValidID(int songID) {
        Song song = new Song(songID, "Test Song", "Test Author", "Test Surname", "Test Album", "01.01.2023", "300");
        dataBaseSourceTest.insert(song);

        Song foundSong = dataBaseSourceTest.findById(songID);

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
    @MethodSource("provideInvalidSongIDs")
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
        dataBaseSourceTest.insert(new Song(1, "Title 1", "Author 1", "Surname 1", "Album 1", "12.12.2020", "300"));
        dataBaseSourceTest.insert(new Song(2, "Title 2", "Author 2", "Surname 2", "Album 2", "01.01.2021", "150"));
        dataBaseSourceTest.insert(new Song(3, "Title 3", "Author 3", "Surname 3", "Album 3", "20.07.2022", "180"));

        boolean isTaken = dataBaseSourceTest.isSongTitleTaken(songTitle);
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
    @MethodSource("provideValidSongIDs")
    void testIsSongIDTakenExistingID(int songID) {
        dataBaseSourceTest.insert(new Song(songID, "Title " + songID, "Author", "Surname", "Album", "12.12.2020", "300"));

        boolean isTaken = dataBaseSourceTest.isSongIDTaken(songID);
        assertTrue(isTaken, "Song ID should be marked as taken.");
    }

    /**
     * Test for checking if a song ID is valid (greater than 0). It ensures that
     * an exception is thrown if the ID is invalid.
     *
     * @param songID the song ID to check
     */
    @ParameterizedTest
    @MethodSource("provideInvalidSongIDs")
    void testIsSongIDTakenInvalidID(int songID) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dataBaseSourceTest.isSongIDTaken(songID);
        });

        assertEquals("Song ID must be greater than 0.", exception.getMessage(), "Incorrect exception message.");
    }
}
