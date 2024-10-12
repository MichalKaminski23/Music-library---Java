package Controller;

import Model.SongList;
import View.SongListView;
import Model.StringIsEmptyException;
import Model.InvalidIntException;
import Model.InvalidDateFormatException;
import java.util.Scanner;

/**
 *
 * @author Michal Kaminski
 */
public class SongListController {

    /**
     *
     */
    public SongList model;

    /**
     *
     */
    public SongListView view;

    /**
     *
     * @param model
     * @param view
     */
    public SongListController(SongList model, SongListView view) {
        this.model = model;
        this.view = view;
    }

    /**
     *
     */
    public void createNewSong() {
        try {
            Scanner scanner = new Scanner(System.in);
            String songTitle;
            String authorName;
            String authorSurname;
            String songAlbum;
            String songTimeInput;
            String songRelease;

            System.out.println("Write your song paremeters:");

            System.out.print("Title of the song: ");
            songTitle = scanner.nextLine();

            System.out.print("Name of the author: ");
            authorName = scanner.nextLine();

            System.out.print("Surname of the author: ");
            authorSurname = scanner.nextLine();

            System.out.print("Album of the song (or no): ");
            songAlbum = scanner.nextLine();

            System.out.print("Release date (DD-MM-YYYY): ");
            songRelease = scanner.nextLine();

            System.out.print("Time of the song [s]: ");
            songTimeInput = scanner.nextLine();

            model.addSongToList(songTitle, authorName, authorSurname, songAlbum, songRelease, songTimeInput);
            System.out.println("Song added successfully.");

        } catch (StringIsEmptyException | InvalidIntException | InvalidDateFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     *
     */
    public void removeSong() {
        try {
            if (model.songs.isEmpty()) {
                System.out.println("The song list is empty. No songs to remove.");
                return;
            } else {
                Scanner scanner = new Scanner(System.in);
                String songTitle;
                System.out.print("Choose method to remove the song: n = by name, i = by id: ");
                String removeChoice = scanner.nextLine();

                switch (removeChoice) {
                    case "n":
                        System.out.print("Enter the name: ");
                        songTitle = scanner.nextLine();
                        model.removeSongFromListByName(songTitle);
                        break;
                    case "i":
                        System.out.print("Enter the ID: ");
                        String songID = scanner.nextLine();
                        model.removeSongFromListByID(songID);
                        break;
                    default:
                        System.out.println("---------------------");
                        System.out.println("Bad paremeter of method.");
                        System.out.println("---------------------");
                        break;
                }
            }
        } catch (InvalidIntException | StringIsEmptyException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     *
     */
    public void updateView() {
        if (model.songs.isEmpty()) {
            System.out.println("Your library size is 0.");
            return;
        } else {
            System.out.println("Your music library: ");
            view.printSongList(model.songs);
        }
    }

    /**
     *
     */
    public void editSong() {
        try {
            if (model.songs.isEmpty()) {
                System.out.println("The song list is empty. No songs to edit.");
                return;
            } else {
                Scanner scanner = new Scanner(System.in);

                String chooseID;
                String songTitle;
                String authorName;
                String authorSurname;
                String songAlbum;
                String songTimeInput;
                String songRelease;

                System.out.print("Choose song ID to edit this song: ");
                chooseID = scanner.nextLine();

                if (chooseID.isBlank()) {
                    System.out.println("The song ID can't be empty.");
                    return;
                }

                System.out.print("Enter the new title: ");
                songTitle = scanner.nextLine();

                System.out.print("Enter the new author's name: ");
                authorName = scanner.nextLine();

                System.out.print("Enter the new author's surname: ");
                authorSurname = scanner.nextLine();

                System.out.print("Enter the new song's album: ");
                songAlbum = scanner.nextLine();

                System.out.print("Enter the new song's release date (DD-MM-YYYY): ");
                songRelease = scanner.nextLine();

                System.out.print("Enter the new song's duration time: ");
                songTimeInput = scanner.nextLine();

                model.updateSong(chooseID, songTitle, authorName, authorSurname, songAlbum, songRelease, songTimeInput);

                System.out.println("Song updated successfuly");
            }
        } catch (StringIsEmptyException | InvalidIntException | InvalidDateFormatException e) {
            System.err.println(e.getMessage());
        }

    }

}
