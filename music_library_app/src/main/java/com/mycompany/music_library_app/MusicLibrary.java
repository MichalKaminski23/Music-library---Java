package com.mycompany.music_library_app;

import Controller.SongListController;
import Model.SongList;
import View.SongListView;
import java.util.Scanner;

/**
 * The main class of the project with simple menu in which user can call
 * functions from other classes.
 *
 * @author Michal Kaminski
 * @version 1.0
 */
public class MusicLibrary {

    /**
     * Empty constructor for the MusicLibrary class.
     */
    @SuppressWarnings("empty-statement")
    public MusicLibrary() {
        ;
    }

    /**
     * Main method of application uses the classes defined in different
     * packages.
     *
     * @param args command line parameters
     */
    public static void main(String[] args) {

        System.out.println("Your music library - enjoy!");
        System.out.println("");

        Scanner scanner = new Scanner(System.in);

        SongList songList = new SongList();
        SongListView songListView = new SongListView();
        SongListController songListController = new SongListController(songList, songListView);

        while (true) {
            System.out.println("-------------------------");
            System.out.println("");
            System.out.println("          Menu");
            System.out.println("a. Show your music library");
            System.out.println("b. Create new song.");
            System.out.println("c. Delete song.");
            System.out.println("d. Update song.");
            System.out.println("e. Exit.");
            System.out.println("");
            System.out.println("-------------------------");
            System.out.print("Enter the character (a, b, c, d, e) to do amazing things: ");

            String choice = scanner.next();
            scanner.nextLine();

            switch (choice) {
                case "a":
                    System.out.println("-------------------------");
                    songListController.updateView();
                    break;

                case "b":
                    System.out.println("-------------------------");
                    songListController.createNewSong();
                    break;

                case "c":
                    System.out.println("-------------------------");
                    songListController.updateView();
                    songListController.removeSong();
                    break;

                case "d":
                    System.out.println("-------------------------");
                    songListController.updateView();
                    songListController.editSong();
                    break;

                case "e":
                    System.out.println("---------------------");
                    System.out.println("See you next time!");
                    System.out.println("---------------------");
                    return;

                default:
                    System.out.println("---------------------");
                    System.out.println("Bad parameter.");
                    System.out.println("---------------------");
                    break;
            }
        }
    }
}
