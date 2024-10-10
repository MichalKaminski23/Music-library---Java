/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;
import Model.Song;
/**
 *
 * @author placu
 */
public class SongView 
{ 
    
    public void printSongInformation(int songID, String songTitle, String composerName, String composerSurname, String songAlbum, String releaseDate, int songTime)
    {
        System.out.println("Song ID: " + songID);
        System.out.println("Song: " + songTitle);
        System.out.println("Writer: " + composerName + " " + composerSurname);
        System.out.println("Album: " + songAlbum);
        System.out.println("Release date: " + releaseDate);
        System.out.println("Time: " + songTime + "s");
    }
    
    public void printSongInformation(Song newSong)
    {
        System.out.println("Song ID: " + newSong.getSongID());
        System.out.println("Song: " + newSong.getSongTitle());
        System.out.println("Writer: " + newSong.getComposerName() + " " + newSong.getComposerSurname());
        System.out.println("Album: " + newSong.getSongAlbum());
        System.out.println("Release date: " + newSong.getReleaseDate());
        System.out.println("Time: " + newSong.getSongTime() + "s");
    }
}
