package mk.musiclibraryweb.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import lombok.Data;

/**
 * The {@code Song} class represents a musical track within the Music Library
 * Web application. It encapsulates information about the song's unique
 * identifier, title, composer details, associated album, release date, and
 * duration.
 *
 * @author Michal Kaminski
 * @version 6.0
 */
@Data
@Entity
public class Song implements Serializable {

    /**
     * The unique identifier for the song. Must be a positive integer and unique
     * across all songs.
     */
    @Id
    @Column(name = "songID")
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
     * The album to which the song belongs. Establishes a many-to-one
     * relationship with the {@link Album} class.
     */
    @ManyToOne
    @JoinColumn(name = "albumID")
    private Album songAlbum;

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
     * Constructs a new {@code Song} object with the specified details.
     *
     * @param songID The unique ID of the song.
     * @param songTitle The title of the song.
     * @param authorName The first name of the composer.
     * @param authorSurname The surname of the composer.
     * @param songAlbum The album to which the song belongs.
     * @param songRelease The release date of the song in {@code dd.MM.yyyy}
     * format.
     * @param songTime The duration of the song in seconds.
     */
    public Song(int songID, String songTitle, String authorName, String authorSurname, Album songAlbum, String songRelease, String songTime) {
        this.songID = songID;
        this.songTitle = songTitle;
        this.authorName = authorName;
        this.authorSurname = authorSurname;
        this.songAlbum = songAlbum;
        this.songRelease = songRelease;
        this.songTime = songTime;
    }

    /**
     * Constructs an empty Song object.
     */
    public Song() {
    }
}
