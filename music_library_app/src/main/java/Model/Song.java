/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
/**
 *
 * @author placu
 */
public class Song 
{
    public Song(String songName, String composerName, String composerSurname, String songAlbum, String releaseDate, int songTime) 
    {
        this.songID = songCounter++;
        this.songName = songName;
        this.composerName = composerName;
        this.composerSurname = composerSurname;
        this.songAlbum = songAlbum;
        this.releaseDate = releaseDate;
        this.songTime = songTime;
    }
    
    private String songName;
    
    private String composerName;
    private String composerSurname;
    
    private String songAlbum;
    private String releaseDate;
    
    private int songTime; //in seconds
    
    static int songCounter = 1;
    private int songID;

    public int getSongID() 
    {
        return songID;
    }

    public void setSongID(int songID) 
    {
        this.songID = songID;
    }

    public String getSongName() 
    {
        return songName;
    }

    public void setSongName(String songName) 
    {
        this.songName = songName;
    }

    public String getComposerName() 
    {
        return composerName;
    }

    public void setComposerName(String composerName) 
    {
        this.composerName = composerName;
    }

    public String getComposerSurname() 
    {
        return composerSurname;
    }

    public void setComposerSurname(String composerSurname) 
    {
        this.composerSurname = composerSurname;
    }

    public String getSongAlbum() 
    {
        return songAlbum;
    }

    public void setSongAlbum(String songAlbum) 
    {
        this.songAlbum = songAlbum;
    }

    public String getReleaseDate() 
    {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) 
    {
        this.releaseDate = releaseDate;
    }

    public float getSongTime() 
    {
        return songTime;
    }

    public void setSongTime(int songTime) 
    {
        this.songTime = songTime;
    }
}
