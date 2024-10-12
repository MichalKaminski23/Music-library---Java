package View;

import Model.Song;
import java.util.ArrayList;

/**
 *
 * @author Michal Kaminski
 */
public class SongListView {

    SongView songView = new SongView();

    public void printSongList(ArrayList<Song> songs) {

        for (int i = 0; i < songs.size(); i++) {
            System.out.println("");
            songView.printSongInformation(songs.get(i));
            System.out.println("");
        }

    }
}
