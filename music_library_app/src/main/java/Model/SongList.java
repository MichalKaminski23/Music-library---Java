/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.ArrayList;
/**
 *
 * @author placu
 */
public class SongList 
{
    public ArrayList<Song> songs = new ArrayList<Song>();
    
    public void addSongToList(String songName, String composerName, String composerSurname, String songAlbum, String releaseDate, int songTime)
    {
        Song newSong = new Song(songName, composerName, composerSurname, songAlbum, releaseDate, songTime);
        songs.add(newSong);
    }
    
    public void removeSongFromListByName(String songName)
    {
        Song songToRemove = null;
        int indexToRemove = -1;
        
        for (int i = 0; i < songs.size(); i++) 
        {
            if (songs.get(i).getSongName().equals(songName)) 
            {
                songToRemove = songs.get(i);
                indexToRemove = i;
                break;
            }
        }
        
        if (songToRemove != null) 
        {
            songs.remove(songToRemove);
            Song.songCounter--;

            for (int i = indexToRemove; i < songs.size(); i++) 
            {
                Song currentSong = songs.get(i);
                currentSong.setSongID(currentSong.getSongID() - 1); 
            }
            System.out.println("Song is removed.");
        } 
        else 
        {
            System.out.println("Bad song's name.");
        }
    }
    
    public void removeSongFromListByID(int songID)
    {
        Song songToRemove = null;
        int indexToRemove = -1;
        
        for (int i = 0; i < songs.size(); i++) 
        {
            if (songs.get(i).getSongID() == songID) 
            {
                songToRemove = songs.get(i);
                indexToRemove = i;
                break;
            }
        }

        
        if (songToRemove != null) 
        {
            songs.remove(songToRemove);
            Song.songCounter--;
            
            for (int i = indexToRemove; i < songs.size(); i++) 
            {
                Song currentSong = songs.get(i);
                currentSong.setSongID(currentSong.getSongID() - 1); 
            }
            System.out.println("Song is removed.");
        } 
        else 
        {
            System.out.println("Bad song's ID.");
        }
    }
}
