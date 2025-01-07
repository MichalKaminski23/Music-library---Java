package mk.musiclibraryweb.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * Represents an Album entity in the music library system. An album contains
 * multiple songs and is identified by a unique album ID and name.
 *
 * @author Michal Kaminski
 * @version 6.0
 */
@Data
@Entity
public class Album implements Serializable {

    /**
     * The unique identifier for the album.
     */
    @Id
    @Column(name = "albumID")
    private int albumID;

    /**
     * The name of the album. This field is mandatory and cannot be null.
     */
    @Column(name = "albumName")
    private String albumName;

    /**
     * The list of songs associated with this album. This establishes a
     * one-to-many relationship with the Song entity. CascadeType.ALL ensures
     * that all related song operations (persist, merge, remove, etc.) are
     * cascaded. OrphanRemoval=true ensures that removing a song from the
     * album's song list will also remove it from the database.
     */
    @OneToMany(mappedBy = "songAlbum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Song> songs = new ArrayList<>();

    /**
     * Default constructor required by JPA.
     */
    public Album() {
    }

    /**
     * Constructs an Album with the specified ID and name.
     *
     * @param albumID The unique identifier for the album.
     * @param albumName The name of the album.
     */
    public Album(int albumID, String albumName) {
        this.albumID = albumID;
        this.albumName = albumName;
    }

    /**
     * Returns the string representation of the album, which is its name.
     *
     * @return The name of the album.
     */
    @Override
    public String toString() {
        return albumName;
    }
}
