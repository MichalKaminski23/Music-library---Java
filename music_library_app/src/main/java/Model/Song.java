package Model;

import java.time.LocalDate;

/**
 *
 * @author Michal Kaminski
 */
public class Song {

    public Song(String songTitle, String composerName, String composerSurname, String songAlbum, LocalDate songRelease, int songTime) {
        this.songID = songCounter++;
        this.songTitle = songTitle;
        this.composerName = composerName;
        this.composerSurname = composerSurname;
        this.songAlbum = songAlbum;
        this.songRelease = songRelease;
        this.songTime = songTime;
    }

    private String songTitle;

    private String composerName;
    private String composerSurname;

    private String songAlbum;
    private LocalDate songRelease;

    private int songTime; //in seconds

    static int songCounter = 1;
    private int songID;

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getComposerName() {
        return composerName;
    }

    public void setComposerName(String composerName) {
        this.composerName = composerName;
    }

    public String getComposerSurname() {
        return composerSurname;
    }

    public void setComposerSurname(String composerSurname) {
        this.composerSurname = composerSurname;
    }

    public String getSongAlbum() {
        return songAlbum;
    }

    public void setSongAlbum(String songAlbum) {
        this.songAlbum = songAlbum;
    }

    public LocalDate getReleaseDate() {
        return songRelease;
    }

    public void setReleaseDate(LocalDate songRelease) {
        this.songRelease = songRelease;
    }

    public float getSongTime() {
        return songTime;
    }

    public void setSongTime(int songTime) {
        this.songTime = songTime;
    }
}
