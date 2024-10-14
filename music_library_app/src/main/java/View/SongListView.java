package View;

import Model.Song;
import java.util.ArrayList;

/**
 * This class is responsible for presenting the song list to the user in a
 * readable format. It interacts with the SongView class to display individual
 * song details.
 *
 * @author Michal Kaminski
 * @version 1.0
 */
public class SongListView {

    /**
     * Empty constructor for the SongListView class.
     */
    @SuppressWarnings("empty-statement")
    public SongListView() {
        ;
    }

    /**
     * Instance of SongView to utilize its method for printing song details
     */
    SongView songView = new SongView();

    /**
     * Prints the list of songs to the console.
     *
     * @param songs The list of songs to be printed.
     */
    public void printSongList(ArrayList<Song> songs) {

        for (int i = 0; i < songs.size(); i++) {
            System.out.println("");
            songView.printSongInformation(songs.get(i));
            System.out.println("");
        }

    }
}
