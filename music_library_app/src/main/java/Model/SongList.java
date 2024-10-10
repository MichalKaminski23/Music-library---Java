/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.ArrayList;
import java.time.LocalDate;
/**
 *
 * @author placu
 */
public class SongList 
{
    public ArrayList<Song> songs = new ArrayList<Song>();
    
    public void addSongToList(String songTitle, String composerName, String composerSurname, String songAlbum, LocalDate songRelease, int songTime)
    {
        for(Song song : songs)
        {
            if (song.getSongTitle().equalsIgnoreCase(songTitle)) 
            {
                System.out.println("---------------------");
                System.out.println("A song with this title already exists: " + songTitle + "edit this song or create another title");
                System.out.println("---------------------");
                return;
            }
        }
        
        Song newSong = new Song(songTitle, composerName, composerSurname, songAlbum, songRelease, songTime);
        songs.add(newSong);
    }
    
    public void removeSongFromListByName(String songTitle)
    {
        Song songToRemove = null;
        int indexToRemove = -1;
        
        for (int i = 0; i < songs.size(); i++) 
        {
            if (songs.get(i).getSongTitle().equals(songTitle)) 
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
    
    public void updateSong(int songID, String newSongTitle, String newComposerName, String newComposerSurname, String newSongAlbum, LocalDate newSongRelease, int newSongTime)
    {
        LocalDate newTempReleaseDate = LocalDate.parse("2000-01-01");
        
        songs.get(songID-1).setSongTitle("");
        songs.get(songID-1).setComposerName("");
        songs.get(songID-1).setComposerSurname("");
        songs.get(songID-1).setSongAlbum("");
        songs.get(songID-1).setReleaseDate(newTempReleaseDate);
        songs.get(songID-1).setSongTime(0);
        songs.get(songID-1).setSongTitle(newSongTitle);
        songs.get(songID-1).setComposerName(newComposerName);
        songs.get(songID-1).setComposerSurname(newComposerSurname);
        songs.get(songID-1).setSongAlbum(newSongAlbum);
        songs.get(songID-1).setReleaseDate(newSongRelease);
        songs.get(songID-1).setSongTime(newSongTime);
    }
}
