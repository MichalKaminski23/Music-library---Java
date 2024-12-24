package mk.musiclibraryweb.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.Data;

/**
 * The Song class represents a musical track with information about its ID,
 * title, composer, album, release date, and duration.
 *
 * @author Michal Kaminski
 * @version 6.0
 */
@Data
@Entity
public class Song implements Serializable {

    /**
     * The unique ID of the song.
     */
    @Id
    @Column(name = "songID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int songID;

    /**
     * The title of the song.
     */
    @Column(name = "songTitle")
    private String songTitle;

    /**
     * The first name of the composer.
     */
    @Column(name = "authorName")
    private String authorName;

    /**
     * The surname of the composer.
     */
    @Column(name = "authorSurname")
    private String authorSurname;

    /**
     * The album the song belongs to.
     */
    @Column(name = "songAlbum")
    private String songAlbum;

    /**
     * The release date of the song in the format 'dd.MM.yyyy'.
     */
    @Column(name = "songRelease")
    private String songRelease;

    /**
     * The duration of the song in seconds.
     */
    @Column(name = "songTime")
    private String songTime;

    /**
     * Constructs a new Song object with the given details.
     *
     * @param songID The ID of the song
     * @param songTitle The title of the song
     * @param authorName The author's first name
     * @param authorSurname The author's surname
     * @param songAlbum The album name the song belongs to
     * @param songRelease The release date of the song
     * @param songTime The duration of the song in seconds
     */
    public Song(int songID, String songTitle, String authorName, String authorSurname, String songAlbum, String songRelease, String songTime) {
        this.songID = songID;
        this.songTitle = songTitle;
        this.authorName = authorName;
        this.authorSurname = authorSurname;
        this.songAlbum = songAlbum;
        this.songRelease = songRelease;
        this.songTime = songTime;
    }

    public Song() {
    }
}
