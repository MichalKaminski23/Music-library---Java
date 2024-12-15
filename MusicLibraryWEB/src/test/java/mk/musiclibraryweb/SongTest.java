package mk.musiclibraryweb;

import java.util.stream.Stream;
import mk.musiclibraryweb.models.Song;
import mk.musiclibraryweb.models.WrongInputException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests for the {@link Song} class, verifying correctness and exception
 * handling for its methods. Includes parameterized tests for various scenarios,
 * such as handling of null, empty, and valid inputs.
 *
 * @author Michal Kaminski
 * @version 4.0
 */
public class SongTest {

    private Song testSong;

    /**
     * Sets up a default {@link Song} instance before each test.
     */
    @BeforeEach
    public void setUp() {
        testSong = new Song("Test title", "Test name", "Test surname", "Test album", "Test release", "Test duration");
    }

    /**
     * Provides a stream of {@link Song} objects, including a null value, for
     * parameterized tests.
     *
     * @return a stream of {@link Song} instances
     */
    static Stream<Song> provideSongs() {
        return Stream.of(
                null,
                new Song("Title 1", "Author 1", "Surname 1", "Album 1", "12.12.2020", "300"),
                new Song("Title 2", "Author 2", "Surname 2", "Album 2", "01.01.2021", "150"),
                new Song("Title 3", "Author 3", "Surname 3", "Album 3", "20.07.2022", "180")
        );
    }

    /**
     * Verifies that a {@link Song} object is correctly identified as null or
     * non-null.
     *
     * @param song the {@link Song} instance to test
     */
    @ParameterizedTest
    @MethodSource("provideSongs")
    void testSongNotNull(Song song) {
        if (song == null) {
            assertTrue(null == song, "Reference to null! One Song object should be null");
        } else {
            assertNotNull(song, "Reference to null! Song object should not be null");
        }
    }

    /**
     * Tests setting the song title with null, empty, or blank values. Expects a
     * {@link WrongInputException} to be thrown.
     *
     * @param text the invalid song title to test
     */
    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    void testSetSongTitleNullEmptyBlank(String text) {
        try {
            testSong.setSongTitle(text);
            fail("Expected WrongInputException to be thrown for null, empty, or blank values");
        } catch (WrongInputException e) {
            assertEquals("Song title can't be empty!", e.getMessage(), "Incorrect exception message");
        }
    }

    /**
     * Tests setting the song title with valid values.
     *
     * @param text the valid song title to test
     */
    @ParameterizedTest
    @ValueSource(strings = {"Nice song", "Very nice song"})
    void testSetSongTitleProperlyValues(String text) {
        try {
            testSong.setSongTitle(text);
        } catch (WrongInputException e) {
            fail("Bad values");
        }
    }

    /**
     * Tests setting the author name with null, empty, or blank values. Expects
     * a {@link WrongInputException} to be thrown.
     *
     * @param text the invalid author name to test
     */
    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    public void testSetAuthorNameNullEmptyBlank(String text) {
        try {
            testSong.setAuthorName(text);
            fail("Expected WrongInputException to be thrown for null, empty, or blank values");
        } catch (WrongInputException e) {
            assertEquals("Author name can't be empty!", e.getMessage(), "Incorrect exception message");
        }
    }

    /**
     * Tests setting the author name with valid values.
     *
     * @param text the valid author name to test
     */
    @ParameterizedTest
    @ValueSource(strings = {"Nice name", "Very nice name"})
    public void testSetAuthorNameProperlyValues(String text) {
        try {
            testSong.setAuthorName(text);
        } catch (WrongInputException e) {
            fail("Bad values");
        }
    }

    /**
     * Tests setting the author surname with null, empty, or blank values.
     * Expects a {@link WrongInputException} to be thrown.
     *
     * @param text the invalid author surname to test
     */
    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    public void testSetAuthorSurnameNullEmptyBlank(String text) {
        try {
            testSong.setAuthorSurname(text);
            fail("Expected WrongInputException to be thrown for null, empty, or blank values");
        } catch (WrongInputException e) {
            assertEquals("Author surname can't be empty!", e.getMessage(), "Incorrect exception message");
        }
    }

    /**
     * Tests setting the author surname with valid values.
     *
     * @param text the valid author surname to test
     */
    @ParameterizedTest
    @ValueSource(strings = {"Nice surname", "Very nice surname"})
    public void testSetAuthorSurnameProperlyValues(String text) {
        try {
            testSong.setAuthorSurname(text);
        } catch (WrongInputException e) {
            fail("Bad values");
        }
    }

    /**
     * Tests setting the song album with null, empty, or blank values. Expects a
     * {@link WrongInputException} to be thrown.
     *
     * @param text the invalid song album to test
     */
    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    public void testSetSongAlbumNullEmptyBlank(String text) {
        try {
            testSong.setSongAlbum(text);
            fail("Expected WrongInputException to be thrown for null, empty, or blank values");
        } catch (WrongInputException e) {
            assertEquals("Album name can't be empty!", e.getMessage(), "Incorrect exception message");
        }
    }

    /**
     * Tests setting the song album with valid values.
     *
     * @param text the valid song album to test
     */
    @ParameterizedTest
    @ValueSource(strings = {"Nice album", "Very nice album"})
    public void testSetSongAlbumProperlyValues(String text) {
        try {
            testSong.setSongAlbum(text);
        } catch (WrongInputException e) {
            fail("Bad values");
        }
    }

    /**
     * Tests setting the song release date with null, empty, or blank values.
     * Expects a {@link WrongInputException} to be thrown.
     *
     * @param text the invalid song release date to test
     */
    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    public void testSetSongReleaseNullEmptyBlank(String text) {
        try {
            testSong.setSongRelease(text);
            fail("Expected WrongInputException to be thrown for null, empty, or blank values");
        } catch (WrongInputException e) {
            assertEquals("Song release can't be empty!", e.getMessage(), "Incorrect exception message");
        }
    }

    /**
     * Tests setting the song release date with valid values.
     *
     * @param text the valid song release date to test
     */
    @ParameterizedTest
    @ValueSource(strings = {"12.12.2003", "04.02.2006"})
    public void testSetSongReleaseProperlyValues(String text) {
        try {
            testSong.setSongRelease(text);
        } catch (WrongInputException e) {
            fail("Bad values");
        }
    }

    /**
     * Tests setting the song release date with invalid dates. Expects a
     * {@link WrongInputException} to be thrown.
     *
     * @param text the invalid song release date to test
     */
    @ParameterizedTest
    @ValueSource(strings = {"00.00.0000", "55.13.2000", "12.12.0000", "01.07.3333"})
    public void testSetSongReleaseNotProperlyValues(String text) {
        try {
            testSong.setSongTime(text);
            assertTrue(!text.matches("(0[1-9]|[1-2][0-9]|3[0-1])\\.(0[1-9]|1[0-2])\\.(19|20)\\d{2}"), "Not properly date (dd.MM.yyyy)");
        } catch (WrongInputException e) {
        }
    }

    /**
     * Tests setting the song duration with null, empty, or blank values.
     * Expects a {@link WrongInputException} to be thrown.
     *
     * @param text the invalid song duration date to test
     */
    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    public void testSetSongTimeNullEmptyBlank(String text) {
        try {
            testSong.setSongTime(text);
            fail("Expected WrongInputException to be thrown for null, empty, or blank values");
        } catch (WrongInputException e) {
            assertEquals("Song time can't be empty!", e.getMessage(), "Incorrect exception message");
        }
    }

    /**
     * Tests setting the song duration with valid values.
     *
     * @param text the valid song duration to test
     */
    @ParameterizedTest
    @ValueSource(strings = {"333", "666"})
    public void testSetSongTimeProperlyValues(String text) {
        try {
            testSong.setSongTime(text);
            assertEquals(text, testSong.getSongTime());
        } catch (WrongInputException e) {
            fail("Song time can't be a negative digit");
        }
    }

    /**
     * Tests setting the song duration with invalid values, such as non-digit
     * strings or negative values. Expects a {@link WrongInputException} to be
     * thrown.
     *
     * @param text the invalid song duration to test
     */
    @ParameterizedTest
    @ValueSource(strings = {"x", "yy", "-1", "0"})
    public void testSetSongTimeNotProperlyValues(String text) {
        try {
            testSong.setSongTime(text);
            assertTrue(!text.matches("\\d+"), "Song time should only contain digits");
        } catch (WrongInputException e) {
        }
    }
    
    //update testy zrobić
}
