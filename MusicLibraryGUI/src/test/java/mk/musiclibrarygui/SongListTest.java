package mk.musiclibrarygui;

import mk.musiclibrarygui.models.SongList;
import mk.musiclibrarygui.models.SongTitleChecker;
import mk.musiclibrarygui.models.WrongInputException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author Michal Kaminski
 * @version 4.0
 */
public class SongListTest {

    public SongList songList;

    private final SongTitleChecker titleChecker = (existingTitle, newTitle)
            -> existingTitle.equalsIgnoreCase(newTitle);

    @BeforeEach
    public void setUp() {
        songList = new SongList();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    public void testDeleteOneByIndex1(int testIndex) {
        try {
            songList.deleteOneByIndex(testIndex);
        } catch (WrongInputException e) {
            fail("It should passed");
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {20000, -1})
    public void testDeleteOneByIndex2(int testIndex) {
        int size = songList.getSongsArraySize();
        try {
            songList.deleteOneByIndex(testIndex);
            fail("It should passed");
        } catch (WrongInputException e) {
            assertEquals(songList.getSongsArraySize(), size, 0.01, "bad size of the array");
        }
    }
}
