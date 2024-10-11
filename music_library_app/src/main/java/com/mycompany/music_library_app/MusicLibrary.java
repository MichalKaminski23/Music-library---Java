/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.music_library_app;

import Controller.SongListController;
import Model.SongList;
import View.SongListView;
import java.util.Scanner;

/**
 *
 * @author placu
 */
//TODO komentarze, args, dodać lepsze działanie menu - np. tablicy rozmiar = 0 no to wróć do menu totalnie
public class MusicLibrary {

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
                    System.out.println("Your music library: ");
                    songListController.updateView();
                    break;

                case "b":
                    System.out.println("");
                    songListController.createNewSong();
                    break;

                case "c":
                    System.out.println("");
                    songListController.updateView();
                    songListController.removeSong();
                    break;

                case "d":
                    System.out.println("");
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
