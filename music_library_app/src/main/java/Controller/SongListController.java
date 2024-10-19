package Controller;

import Model.SongList;
import View.SongListView;
import View.MessageErrorPrinter;
import Model.WrongInputException;
import java.util.Scanner;

/**
 * Controller class that manages the interaction between the SongList model and
 * the SongListView. It handles user input for creating, removing, editing, and
 * displaying songs in the music library.
 *
 * @author Michal Kaminski
 * @version 1.0
 */
public class SongListController {

    /**
     * Object from MessageErrorPrinter class to print error messages caught in
     * exceptions.
     */
    MessageErrorPrinter messageErrorPrinter = new MessageErrorPrinter();

    /**
     * The model representing the list of songs.
     */
    private final SongList model;

    /**
     * The view responsible for displaying the list of songs.
     */
    private final SongListView view;

    /**
     * Constructs the controller with the provided model and view.
     *
     * @param model The SongList model
     * @param view The SongListView view
     */
    public SongListController(SongList model, SongListView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Prompts the user for song details and creates a new song to add to the
     * song list. Catches exceptions for invalid input formats (e.g., empty
     * strings, invalid integers, or date format issues).
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

        } catch (WrongInputException e) {
            messageErrorPrinter.printMessage(e.getMessage());
        }
    }

    /**
     * Removes a song from the song list by either name or ID. Prompts the user
     * to choose the method of removal and handles exceptions for invalid input.
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
        } catch (WrongInputException e) {
            messageErrorPrinter.printMessage(e.getMessage());
        }
    }

    /**
     * Updates the song view by displaying all the songs in the library. If the
     * song list is empty, it informs the user that there are no songs.
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
     * Allows the user to edit the details of a song in the list. Prompts the
     * user for new song information and updates the model. Handles exceptions
     * for invalid inputs such as empty strings or incorrect data formats.
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

                int chooseIDInt = Integer.parseInt(chooseID);

                if (chooseIDInt <= 0) {
                    System.out.println("The song ID can't be <= 0.");
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

                System.out.println("Song updated successfuly.");
            }
        } catch (WrongInputException e) {
            messageErrorPrinter.printMessage(e.getMessage());
        }

    }

}
