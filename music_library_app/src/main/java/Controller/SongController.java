/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.Song;
import View.SongView;
import java.time.LocalDate;
/**
 *
 * @author placu
 */
public class SongController 
{
    private Song model;
    private SongView view;
    
    public SongController(Song model, SongView view)
    {
        this.model = model;
        this.view = view;
    }

    public void setSongName(String songTitle)
    {
        model.setSongTitle(songTitle);
    }

    public String getSongName() 
    {
        return model.getSongTitle();
    }

    public void setSongComposerName(String name)
    {
        model.setComposerName(name);
    }

    public String getSongComposerName(String composerName)
    {
        return model.getComposerName();
    }
    
    public void setSongComposerSurname(String composerSurname)
    {
        model.setComposerName(composerSurname);
    }
    
    public String getSongComposerSurname(String composerSurname)
    {
        return model.getComposerSurname();
    }
    
    public void setSongAlbum(String songAlbum)
    {
        model.setSongAlbum(songAlbum);
    }
    
    public String getSongAlbum(String songAlbum)
    {
        return model.getSongAlbum();
    }
            
    public void setSongReleaseDate(LocalDate songRelease)
    {
        model.setReleaseDate(songRelease);
    }
    
    public LocalDate getSongReleaseDate(LocalDate songRelease)
    {
        return model.getReleaseDate();
    }
    
    public void setSongTime(int songTime)
    {
        model.setSongTime(songTime);
    }
    
    public float getSongTime(int songTime)
    {
        return model.getSongTime();
    }
    
    public void printView() 
    {
        view.printSongInformation(model.getSongID(), model.getSongTitle(), model.getComposerName(), model.getComposerSurname(), model.getSongAlbum(), model.getReleaseDate().toString(), (int) model.getSongTime());
    }
}
