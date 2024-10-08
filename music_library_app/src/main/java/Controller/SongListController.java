/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.SongList;
//import Model.Song;
import View.SongListView;
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
    
    public void createNewSong(String songName, String composerName, String composerSurname, String songAlbum, String releaseDate, int songTime)
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
    
}
