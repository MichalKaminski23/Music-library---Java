/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.time.LocalDate;

/**
 *
 * @author placu
 */
public class SongList {

    public ArrayList<Song> songs = new ArrayList<Song>();

    public void addSongToList(String songTitle, String composerName, String composerSurname, String songAlbum, LocalDate songRelease, int songTime) throws StringIsEmptyException, InvalidIntException {
        for (Song song : songs) {
            if (song.getSongTitle().equalsIgnoreCase(songTitle)) {
                System.out.println("---------------------");
                System.out.println("A song with this title already exists: " + songTitle + "edit this song or create another title");
                System.out.println("---------------------");
                return;
            }
        }

        if (songTitle.isBlank()) {
            throw new StringIsEmptyException("Song title can't be empty!");
        }

        if (composerName.isBlank()) {
            throw new StringIsEmptyException("Composer name can't be empty!");
        }

        if (composerSurname.isBlank()) {
            throw new StringIsEmptyException("Composer surname can't be empty!");
        }

        if (songAlbum.isBlank()) {
            throw new StringIsEmptyException("Song album can't be empty!");
        }

        // if(songRelease.) do zrobienia
        if (songTime <= 0) {
            throw new InvalidIntException("Song time can't be lower than 0 or be float!");
        }
        Song newSong = new Song(songTitle, composerName, composerSurname, songAlbum, songRelease, songTime);
        songs.add(newSong);
    }

    public void removeSongFromListByName(String songTitle) throws StringIsEmptyException, InvalidIntException, ArraySizeIsZeroException {
        Song songToRemove = null;
        int indexToRemove = -1;

        if(songs.size() == 0)
        {
            throw new ArraySizeIsZeroException("Your library size is 0, you can't remove song.");
        }
        
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getSongTitle().equals(songTitle)) {
                songToRemove = songs.get(i);
                indexToRemove = i;
                break;
            }
        }

        if (songToRemove != null) {
            songs.remove(songToRemove);
            Song.songCounter--;

            for (int i = indexToRemove; i < songs.size(); i++) {
                Song currentSong = songs.get(i);
                currentSong.setSongID(currentSong.getSongID() - 1);
            }
            System.out.println("Song is removed.");
        } else {
            System.out.println("Bad song's name.");
        }
    }

    public void removeSongFromListByID(int songID) throws StringIsEmptyException, InvalidIntException, ArraySizeIsZeroException  {
        Song songToRemove = null;
        int indexToRemove = -1;

        if(songs.size() == 0)
        {
            throw new ArraySizeIsZeroException("Your library size is 0, you can't remove song.");
        }
        
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getSongID() == songID) {
                songToRemove = songs.get(i);
                indexToRemove = i;
                break;
            }
        }

        if (songToRemove != null) {
            songs.remove(songToRemove);
            Song.songCounter--;

            for (int i = indexToRemove; i < songs.size(); i++) {
                Song currentSong = songs.get(i);
                currentSong.setSongID(currentSong.getSongID() - 1);
            }
            System.out.println("Song is removed.");
        } else {
            System.out.println("Bad song's ID.");
        }
    }

    public void updateSong(int songID, String newSongTitle, String newComposerName, String newComposerSurname, String newSongAlbum, LocalDate newSongRelease, int newSongTime) throws StringIsEmptyException, InvalidIntException, ArraySizeIsZeroException {
        LocalDate newTempReleaseDate = LocalDate.parse("2000-01-01");

        if (songs.size() == 0) {
            throw new ArraySizeIsZeroException("Your library size is 0, you can't remove song.");
        } else {
            songs.get(songID - 1).setSongTitle("");
            songs.get(songID - 1).setComposerName("");
            songs.get(songID - 1).setComposerSurname("");
            songs.get(songID - 1).setSongAlbum("");
            songs.get(songID - 1).setReleaseDate(newTempReleaseDate);
            songs.get(songID - 1).setSongTime(0);
            songs.get(songID - 1).setSongTitle(newSongTitle);
            songs.get(songID - 1).setComposerName(newComposerName);
            songs.get(songID - 1).setComposerSurname(newComposerSurname);
            songs.get(songID - 1).setSongAlbum(newSongAlbum);
            songs.get(songID - 1).setReleaseDate(newSongRelease);
            songs.get(songID - 1).setSongTime(newSongTime);
        }

    }
}
