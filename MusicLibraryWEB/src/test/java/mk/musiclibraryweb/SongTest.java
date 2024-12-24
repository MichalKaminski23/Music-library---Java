package mk.musiclibraryweb;

import java.util.stream.Stream;
import mk.musiclibraryweb.models.Song;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Test class for the {@link Song} class.
 *
 * This class contains tests for verifying the behavior of the class, including
 * tests for null and non-null Song instances.
 *
 * @author Michal Kaminski
 * @version 6.0
 */
public class SongTest {

    private Song testSong;

    /**
     * Sets up a default {@link Song} instance before each test.
     *
     * This method creates a {@link Song} object with predefined values which
     * will be used in the test methods.
     */
    @BeforeEach
    public void setUp() {
        testSong = new Song(420, "Test title", "Test name", "Test surname", "Test album", "Test release", "Test duration");
    }

    /**
     * Provides a stream of {@link Song} objects, including a null value, for
     * parameterized tests.
     *
     * This method returns a stream of {@link Song} instances to be used as
     * input for parameterized tests. The stream includes a null value, as well
     * as valid {@link Song} objects for testing.
     *
     * @return a stream of {@link Song} instances
     */
    static Stream<Song> provideSongs() {
        return Stream.of(
                null,
                new Song(1, "Title 1", "Author 1", "Surname 1", "Album 1", "12.12.2020", "300"),
                new Song(2, "Title 2", "Author 2", "Surname 2", "Album 2", "01.01.2021", "150"),
                new Song(3, "Title 3", "Author 3", "Surname 3", "Album 3", "20.07.2022", "180")
        );
    }

    /**
     * Verifies that a {@link Song} object is correctly identified as null or
     * non-null.
     *
     * This method tests whether the provided {@link Song} object is correctly
     * identified as null or not. It uses the parameterized input provided by
     * the {@link #provideSongs} method and asserts whether the reference is
     * null or not null.
     *
     * @param song the {@link Song} instance to test, can be null or non-null
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
}
