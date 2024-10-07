/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;
import Model.Song;
import Model.SongList;
import java.util.ArrayList;
/**
 *
 * @author placu
 */
public class SongListView 
{
    SongView songView = new SongView();
    
    public void printSongList(ArrayList<Song> songs)
    {
        for(int i = 0; i < songs.size(); i++)
        {
            songView.printSongInformation(songs.get(i));
        }
    }
}
