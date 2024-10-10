/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.SongList;
import View.SongListView;
import java.time.LocalDate;
/**
 *
 * @author placu
 */
public class SongListController 
{
    public SongList model;
    public SongListView view;
    
    public SongListController(SongList model, SongListView view)
    {
        this.model = model;
        this.view = view;
    }
    
    public void createNewSong(String songName, String composerName, String composerSurname, String songAlbum, LocalDate releaseDate, int songTime)
    {
        model.addSongToList(songName, composerName, composerSurname, songAlbum, releaseDate, songTime);
    }
    
    public void removeSong(String songName)
    {
        model.removeSongFromListByName(songName);
    }
    
    public void removeSong(int songID)
    {
        model.removeSongFromListByID(songID);
    }
    
    public void updateView()
    {
        view.printSongList(model.songs);
    }
    
    public void editSong(int songID, String newSongTitle, String newComposerName, String newComposerSurname, String newSongAlbum, LocalDate newReleaseDate, int newSongTime)
    {
        model.updateSong(songID, newSongTitle, newComposerName, newComposerSurname, newSongAlbum, newReleaseDate, newSongTime);
    }
    
}
