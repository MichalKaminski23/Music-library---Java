package View;

import Model.Song;

/**
 * This class is responsible for displaying information about individual songs
 * in a readable format.
 *
 * @author Michal Kaminski
 * @version 1.0
 */
public class SongView {

    /**
     * Empty constructor for the SongView class.
     */
    @SuppressWarnings("empty-statement")
    public SongView() {
        ;
    }

    /**
     * Prints the details of a song using individual parameters.
     *
     * @param songID The unique identifier of the song.
     * @param songTitle The title of the song.
     * @param composerName The name of the song's composer.
     * @param composerSurname The surname of the song's composer.
     * @param songAlbum The album where the song is featured.
     * @param releaseDate The release date of the song.
     * @param songTime The duration of the song in seconds.
     */
    public void printSongInformation(int songID, String songTitle, String composerName, String composerSurname, String songAlbum, String releaseDate, int songTime) {
        System.out.println("Song ID: " + songID);
        System.out.println("Song: " + songTitle);
        System.out.println("Writer: " + composerName + " " + composerSurname);
        System.out.println("Album: " + songAlbum);
        System.out.println("Release date: " + releaseDate);
        System.out.println("Time: " + songTime + "s");
    }

    /**
     * Prints the details of a song using a Song object.
     *
     * @param newSong The Song object containing song details.
     */
    public void printSongInformation(Song newSong) {
        System.out.println("Song ID: " + newSong.getSongID());
        System.out.println("Song: " + newSong.getSongTitle());
        System.out.println("Writer: " + newSong.getComposerName() + " " + newSong.getComposerSurname());
        System.out.println("Album: " + newSong.getSongAlbum());
        System.out.println("Release date: " + newSong.getReleaseDate());
        System.out.println("Time: " + newSong.getSongTime() + "s");
    }
}
