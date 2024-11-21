package mk.musiclibrarygui;

import java.util.stream.Stream;
import mk.musiclibrarygui.models.Song;
import mk.musiclibrarygui.models.SongList;
import mk.musiclibrarygui.models.SongTitleChecker;
import mk.musiclibrarygui.models.WrongInputException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests for the {@link SongList} class, verifying correctness and exception
 * handling for its methods. Includes parameterized tests for various scenarios,
 * such as handling of null, empty, and valid inputs.
 *
 * @author Michal Kaminski
 * @version 4.0
 */
public class SongListTest {

    public SongList songList;

    private final SongTitleChecker titleChecker = (existingTitle, newTitle)
            -> existingTitle.equalsIgnoreCase(newTitle);

    /**
     * Sets up a new instance of SongList before each test.
     */
    @BeforeEach
    public void setUp() {
        songList = new SongList();
    }

    /**
     * Provides a stream of SongList instances, including a null value, for
     * parameterized tests.
     *
     * @return a stream of SongList instances.
     */
    static Stream<SongList> provideSongLists() {
        return Stream.of(
                null,
                new SongList(),
                new SongList()
        );
    }

    /**
     * Tests if the SongList instance is correctly initialized or null where
     * expected.
     *
     * @param songList the SongList instance being tested.
     */
    @ParameterizedTest
    @MethodSource("provideSongLists")
    void testSongListNotNull(SongList songList) {
        if (songList == null) {
            assertTrue(null == songList, "Reference to null! One SongList object should be null");
        } else {
            assertNotNull(songList, "Reference to null! SongList object should not be null");
        }
    }

    /**
     * Tests that attempting to add a song with null, empty, or blank fields
     * throws an exception.
     *
     * @param text the string input for the test.
     */
    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    void testAddSongNullEmptyBlank(String text) {
        try {
            songList.addSong(text, text, text, text, text, text, titleChecker);
            fail("Expected WrongInputException to be thrown for null, empty, or blank values");
        } catch (WrongInputException e) {
            assertEquals("Text fields can't be empty!", e.getMessage(), "Incorrect exception message");
        }
    }

    /**
     * Tests adding songs with proper values to the SongList.
     *
     * @param title the title of the song.
     * @param name the author's name.
     * @param surname the author's surname.
     * @param album the album name.
     * @param release the release date in dd.MM.yyyy format.
     * @param time the duration of the song in seconds.
     */
    @ParameterizedTest
    @CsvSource({
        "Super Title, 333, Byczek, OMG ALBUM, 12.12.2020, 300",
        "Another Song, Mikolaj, Tefciu, @@@, 01.01.2000, 150",
        "777, Michal, Kamyk3222, pop album, 25.12.1999, 180"
    })
    void testAddSongProperlyValues(String title, String name, String surname, String album, String release, String time) {
        try {
            songList.addSong(title, name, surname, album, release, time, titleChecker);
            Song lastAddedSong = songList.getAllSongs().get(songList.getAllSongs().size() - 1);

            assertEquals(title, lastAddedSong.getSongTitle(), "Title does not match");
            assertEquals(name, lastAddedSong.getAuthorName(), "Author name does not match");
            assertEquals(surname, lastAddedSong.getAuthorSurname(), "Author surname does not match");
            assertEquals(album, lastAddedSong.getSongAlbum(), "Album name does not match");
            assertEquals(release, lastAddedSong.getSongRelease(), "Release date does not match");
            assertEquals(time, lastAddedSong.getSongTime(), "Song time does not match");
        } catch (WrongInputException e) {
            fail("Exception should not be thrown for valid input values");
        }
    }

    /**
     * Tests adding songs with invalid values to the SongList.
     *
     * @param title the title of the song.
     * @param name the author's name.
     * @param surname the author's surname.
     * @param album the album name.
     * @param release an invalid release date.
     * @param time an invalid duration of the song.
     */
    @ParameterizedTest
    @CsvSource({
        "Super Title, 333, Byczek, OMG ALBUM, 00.00.0000, x",
        "Another Song, Mikolaj, Tefciu, @@@, 55.13.2000, yy",
        "777, Michal, Kamyk3222, pop album, 12.12.0000, -1"
    })
    void testAddSongNotProperlyValues(String title, String name, String surname, String album, String release, String time) {
        try {
            songList.addSong(title, name, surname, album, release, time, titleChecker);
            Song lastAddedSong = songList.getAllSongs().get(songList.getAllSongs().size() - 1);

            assertEquals(title, lastAddedSong.getSongTitle(), "Title does not match");
            assertEquals(name, lastAddedSong.getAuthorName(), "Author name does not match");
            assertEquals(surname, lastAddedSong.getAuthorSurname(), "Author surname does not match");
            assertEquals(album, lastAddedSong.getSongAlbum(), "Album name does not match");
            assertTrue(!release.matches("(0[1-9]|[1-2][0-9]|3[0-1])\\.(0[1-9]|1[0-2])\\.(19|20)\\d{2}"), "Not properly date (dd.MM.yyyy)");
            assertTrue(!time.matches("\\d+"), "Song time should only contain digits");
        } catch (WrongInputException e) {
        }
    }

    /**
     * Tests comparing song titles with proper values.
     *
     * @param text the song title to compare.
     */
    @ParameterizedTest
    @ValueSource(strings = {"Example title 1", "Example title 2"})
    public void testCompareSongTitlesProperlyValues(String text) {
        try {
            songList.compareSongTitles(text, titleChecker);
            assertEquals(songList.getOneByIndex(0).getSongTitle(), text);
            assertEquals(songList.getOneByIndex(1).getSongTitle(), text);
            fail("Exception should not be thrown for valid input values");
        } catch (WrongInputException e) {
        }
    }

    /**
     * Tests comparing song titles with not proper values.
     *
     * @param text the song title to compare.
     */
    @ParameterizedTest
    @ValueSource(strings = {"Not title 1", "Not title 2"})
    public void testCompareSongTitlesNotProperlyValues(String text) {
        try {
            songList.compareSongTitles(text, titleChecker);
            assertTrue(!text.matches(songList.getOneByIndex(0).getSongTitle()));
            assertTrue(!text.matches(songList.getOneByIndex(1).getSongTitle()));
        } catch (WrongInputException e) {
            fail("Exception should be thrown for invalid input values");
        }
    }

    /**
     * Tests deleting a song by valid index.
     *
     * @param testIndex the index to delete.
     */
    @ParameterizedTest
    @ValueSource(ints = {0, 2, 4})
    public void testDeleteOneByIndexProperlyValues(int testIndex) {
        try {
            songList.deleteOneByIndex(testIndex);
        } catch (WrongInputException e) {
            fail("Exception shouldn't be thrown for valid input values");
        }
    }

    /**
     * Tests deleting a song by an invalid index.
     *
     * @param testIndex the index to delete.
     */
    @ParameterizedTest
    @ValueSource(ints = {-1, 1000000})
    public void testDeleteOneByIndexNotProperlyValues(int testIndex) {
        int arraySize = songList.getSongsArraySize();
        try {
            songList.deleteOneByIndex(testIndex);
            fail("It should passed");
        } catch (WrongInputException e) {
            assertEquals(songList.getSongsArraySize(), arraySize, 0.01, "Bad size of the array");
        }
    }
}
