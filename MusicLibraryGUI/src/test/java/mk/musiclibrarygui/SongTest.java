package mk.musiclibrarygui;

import mk.musiclibrarygui.models.Song;
import mk.musiclibrarygui.models.WrongInputException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author Michal Kaminski
 * @version 4.0
 */
public class SongTest {

    private Song testSong;

    @BeforeEach
    public void setUp() {
        testSong = new Song("Test title", "Test name", "Test surname", "Test album", "Test release", "Test duration");
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    void testSetSongTitleNullEmptyBlank(String text) {
        try {
            testSong.setSongTitle(text);
            fail("Song title can't be empty");
        } catch (WrongInputException e) {
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"Nice song", "Very nice song"})
    void testSetSongTitleProperlyValues(String text) {
        try {
            testSong.setSongTitle(text);
        } catch (WrongInputException e) {
            fail("Bad values");
        }
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    public void testSetAuthorNameNullEmptyBlank(String text) {
        try {
            testSong.setAuthorName(text);
            fail("Author name can't be empty");
        } catch (WrongInputException e) {
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"Nice name", "Very nice name"})
    public void testSetAuthorNameProperlyValues(String text) {
        try {
            testSong.setAuthorName(text);
        } catch (WrongInputException e) {
            fail("Bad values");
        }
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    public void testSetAuthorSurnameNullEmptyBlank(String text) {
        try {
            testSong.setAuthorSurname(text);
            fail("Author surname can't be empty");
        } catch (WrongInputException e) {
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"Nice surname", "Very nice surname"})
    public void testSetAuthorSurnameProperlyValues(String text) {
        try {
            testSong.setAuthorSurname(text);
        } catch (WrongInputException e) {
            fail("Bad values");
        }
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    public void testSetSongALbumNullEmptyBlank(String text) {
        try {
            testSong.setSongAlbum(text);
            fail("Song album can't be empty");
        } catch (WrongInputException e) {
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"Nice album", "Very nice album"})
    public void testSetSongALbumProperlyValues(String text) {
        try {
            testSong.setSongAlbum(text);
        } catch (WrongInputException e) {
            fail("Bad values");
        }
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    public void testSetSongReleaseNullEmptyBlank(String text) {
        try {
            testSong.setSongRelease(text);
            fail("Song release can't be empty");
        } catch (WrongInputException e) {
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"12.12.2003", "04.02.2006"})
    public void testSetSongReleaseProperlyValues(String text) {
        try {
            testSong.setSongRelease(text);
        } catch (WrongInputException e) {
            fail("Bad values");
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"00.00.0000", "55.13.2000", "12.12.0000", "01.07.3333"})
    public void testSetSongReleaseNotProperlyValues(String text) {
        try {
            testSong.setSongTime(text);
            assertTrue(!text.matches("(0[1-9]|[1-2][0-9]|3[0-1])\\.(0[1-9]|1[0-2])\\.(19|20)\\d{2}"), "Not properly date (dd.MM.yyyy)");
        } catch (WrongInputException e) {
        }
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    public void testSetSongTimeNullEmptyBlank(String text) {
        try {
            testSong.setSongTime(text);
            fail("Song time can't be empty");
        } catch (WrongInputException e) {
        }
    }

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

    @ParameterizedTest
    @ValueSource(strings = {"x", "yy", "-1", "0"})
    public void testSetSongTimeNotProperlyValues(String text) {
        try {
            testSong.setSongTime(text);
            assertTrue(!text.matches("\\d+"), "Song time should only contain digits");
        } catch (WrongInputException e) {
        }
    }
}
