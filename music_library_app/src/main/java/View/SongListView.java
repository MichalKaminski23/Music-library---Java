/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;
import Model.Song;
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
        if(songs.size() == 0)
        {
            System.out.println("");
            System.out.println("There is no song in the list. Add a new song first.");
            System.out.println("");
        }
        else
        {
           for(int i = 0; i < songs.size(); i++)
           {
                System.out.println("");
                songView.printSongInformation(songs.get(i));
                System.out.println("");
            }
           
        }
    }
}
