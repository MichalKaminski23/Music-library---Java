package mk.musiclibrarygui.models;

/**
 * A functional interface for checking the uniqueness of song titles.
 * This interface defines a single abstract method to compare an existing
 * song title with a new song title to determine if they are the same.
 *
 * @author Michal Kaminski
 * @version 3.0
 */
@FunctionalInterface
public interface SongTitleChecker {

    /**
     * Compares an existing song title with a new song title.
     *
     * @param existingTitle The title of the existing song
     * @param newTitle The title of the new song to check
     * @return true if the titles are considered the same, false otherwise
     */
    boolean checkTitle(String existingTitle, String newTitle);
}
