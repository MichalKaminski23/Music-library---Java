/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.Song;
import java.util.ArrayList;
import Model.ArraySizeIsZeroException;

/**
 *
 * @author placu
 */
public class SongListView {

    SongView songView = new SongView();

    public void printSongList(ArrayList<Song> songs) throws ArraySizeIsZeroException {
        if (songs.size() == 0) {
            throw new ArraySizeIsZeroException("Your library size is 0, you can't do anything on it.");
        } else {
            for (int i = 0; i < songs.size(); i++) {
                System.out.println("");
                songView.printSongInformation(songs.get(i));
                System.out.println("");
            }

        }
    }
}
