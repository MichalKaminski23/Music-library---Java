package Model;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Michal Kaminski
 */
public class SongList {

    public ArrayList<Song> songs = new ArrayList<Song>();

    public void addSongToList(String songTitle, String composerName, String composerSurname, String songAlbum, String songReleaseInput, String songTimeInput) throws StringIsEmptyException, InvalidIntException, InvalidDateFormatException {

        LocalDate songRelease = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        int songTime;

        for (Song song : songs) {
            if (song.getSongTitle().equalsIgnoreCase(songTitle)) {
                System.out.println("");
                System.out.println("A song with this title already exists: " + songTitle + "edit this song or create another title");
                return;
            }
        }

        if (songTitle.isBlank()) {
            System.out.println("");
            throw new StringIsEmptyException("Song title can't be empty!");
        }

        if (composerName.isBlank()) {
            System.out.println("");
            throw new StringIsEmptyException("Composer name can't be empty!");
        }

        if (composerSurname.isBlank()) {
            System.out.println("");
            throw new StringIsEmptyException("Composer surname can't be empty!");
        }

        if (songAlbum.isBlank()) {
            System.out.println("");
            throw new StringIsEmptyException("Song album can't be empty!");
        }

        try {
            songRelease = LocalDate.parse(songReleaseInput, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Invalid date format. Please use DD-MM-YYYY.");
        }

        songTime = Integer.parseInt(songTimeInput);
        if (songTime <= 0 || songTimeInput.isBlank()) {
            System.out.println("");
            throw new InvalidIntException("Song time can't be lower than 0 or be a float!");
        }

        Song newSong = new Song(songTitle, composerName, composerSurname, songAlbum, songRelease, songTime);
        songs.add(newSong);
    }

    public void removeSongFromListByName(String songTitle) throws StringIsEmptyException {

        if (songTitle.isBlank()) {
            System.out.println("");
            throw new StringIsEmptyException("Song title can't be null!");
        }

        Song songToRemove = null;
        int indexToRemove = -1;
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

    public void removeSongFromListByID(String songIDInput) throws StringIsEmptyException, InvalidIntException {

        int songID;

        if (songIDInput.isBlank()) {
            System.out.println("");
            throw new StringIsEmptyException("Song ID can't be a string!");
        }

        try {
            songID = Integer.parseInt(songIDInput);
        } catch (NumberFormatException e) {
            throw new InvalidIntException("Invalid input: Song ID must be a number.");
        }

        Song songToRemove = null;
        int indexToRemove = -1;
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

    public void updateSong(String songIDInput, String newSongTitle, String newComposerName, String newComposerSurname, String newSongAlbum, String newSongReleaseInput, String newSongTimeInput) throws StringIsEmptyException, InvalidIntException, InvalidDateFormatException {

        LocalDate newTempReleaseDate = LocalDate.parse("2000-01-01");
        LocalDate songRelease = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        int songTime;
        int songID;

        if (songIDInput.isBlank()) {
            System.out.println("");
            throw new StringIsEmptyException("Song ID can't be string!");
        }

        try {
            songID = Integer.parseInt(songIDInput);
        } catch (NumberFormatException e) {
            throw new InvalidIntException("Invalid input: Song ID must be a number.");
        }

        if (newSongTitle.isBlank()) {
            System.out.println("");
            throw new StringIsEmptyException("Song title can't be empty!");
        } else {
            songs.get(songID - 1).setSongTitle(newSongTitle);
        }

        if (newComposerName.isBlank()) {
            System.out.println("");
            throw new StringIsEmptyException("Composer name can't be empty!");
        } else {
            songs.get(songID - 1).setComposerName(newComposerName);
        }

        if (newComposerSurname.isBlank()) {
            System.out.println("");
            throw new StringIsEmptyException("Composer surname can't be empty!");
        } else {
            songs.get(songID - 1).setComposerSurname(newComposerSurname);
        }

        if (newSongAlbum.isBlank()) {
            System.out.println("");
            throw new StringIsEmptyException("Song album can't be empty!");
        } else {
            songs.get(songID - 1).setSongAlbum(newSongAlbum);
        }

        try {
            songRelease = LocalDate.parse(newSongReleaseInput, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Invalid date format. Please use DD-MM-YYYY.");
        }
        songs.get(songID - 1).setReleaseDate(songRelease);

        songTime = Integer.parseInt(newSongTimeInput);
        if (songTime <= 0 || newSongTimeInput.isBlank()) {
            System.out.println("");
            throw new InvalidIntException("Song time can't be lower than 0 or be a float!");
        } else {
            songs.get(songID - 1).setSongTime(songTime);
        }
    }

}
