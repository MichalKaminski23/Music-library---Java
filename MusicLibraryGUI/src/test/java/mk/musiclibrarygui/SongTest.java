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

    private Song song;

    @BeforeEach
    public void setUp() {
        song = new Song("Test title", "Test name", "Test surname", "Test album", "Test release", "Test duration");
    }

    @Test
    public void testSetSongTitle() {
        try {
            song.setSongTitle("Kruci");
            assertEquals(song.getSongTitle(), "Kruci", "Names are not the same");
        } catch (WrongInputException e) {
            fail("Comparing titles Kruci fails");
        }

    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    void testSetSongTitle(String text) {
        try {
            song.setSongTitle(text);
            fail("Song title can't be empty");
        } catch (WrongInputException e) {
        }
    }

    @Test
    public void testSetAuthorName() {
        try {
            song.setAuthorName("");
            fail("Author name can't be empty");
        } catch (WrongInputException e) {
        }

    }

    @Test
    public void testSetAuthorSurname() {
        try {
            song.setAuthorSurname("");
            fail("Author surname can't be empty");
        } catch (WrongInputException e) {
        }

    }

    @Test
    public void testSetSongALbum() {
        try {
            song.setSongAlbum("");
            fail("Song album can't be empty");
        } catch (WrongInputException e) {
        }

    }

    @Test
    public void testSetSongRelease() {
        try {
            song.setSongRelease("");
            fail("Song release can't be empty");
        } catch (WrongInputException e) {
        }

    }

    @Test
    public void testSetSongTime() {
        try {
            song.setSongTime("");
            fail("Song time can't be empty");
        } catch (WrongInputException e) {
        }

    }
}
