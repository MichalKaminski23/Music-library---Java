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
}
