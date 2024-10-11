/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.SongList;
import View.SongListView;
import java.time.LocalDate;
import Model.StringIsEmptyException;
import Model.InvalidIntException;
import Model.ArraySizeIsZeroException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author placu
 */
public class SongListController {

    public SongList model;
    public SongListView view;

    public SongListController(SongList model, SongListView view) {
        this.model = model;
        this.view = view;
    }

    public void createNewSong() {
        Scanner scanner = new Scanner(System.in);
        String songTitle;
        String authorName;
        String authorSurname;
        String songAlbum;
        int songTime;
        LocalDate songRelease = null;
        String dateInput;

        try {
            System.out.println("Write your song paremeters:");

            System.out.print("Title of the song: ");
            songTitle = scanner.nextLine();

            System.out.print("Name of the author: ");
            authorName = scanner.nextLine();

            System.out.print("Surname of the author: ");
            authorSurname = scanner.nextLine();

            System.out.print("Album of the song (or no): ");
            songAlbum = scanner.nextLine();

            System.out.print("Release date (YYYY-MM-DD): ");
            dateInput = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            songRelease = LocalDate.parse(dateInput, formatter);

            System.out.print("Time of the song [s]: ");
            songTime = scanner.nextInt();

            model.addSongToList(songTitle, authorName, authorSurname, songAlbum, songRelease, songTime);
        } catch (StringIsEmptyException e) {
            System.err.println(e.getMessage());
        } catch (InvalidIntException e) {
            System.err.println(e.getMessage());
        }
    }

    public void removeSong() {
        Scanner scanner = new Scanner(System.in);
        String songTitle;
        try {
            System.out.print("Choose method to remove the song: n = by name, i = by id: ");
            String removeChoice = scanner.next();
            scanner.nextLine();

            if (removeChoice.equals("n")) {
                System.out.print("Enter the name: ");
                songTitle = scanner.nextLine();

                model.removeSongFromListByName(songTitle);

            } else if (removeChoice.equals("i")) {
                System.out.print("Enter the ID: ");
                int songID = scanner.nextInt();
                model.removeSongFromListByID(songID);
            } else {
                System.out.println("---------------------");
                System.out.println("Bad paremeter of method.");
                System.out.println("---------------------");

            }
        } catch (ArraySizeIsZeroException e) {
            System.err.println(e.getMessage());
        } catch (InvalidIntException e) {
            System.err.println(e.getMessage());
        } catch (StringIsEmptyException e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateView() {
        try {
            view.printSongList(model.songs);
        } catch (ArraySizeIsZeroException e) {
            System.err.println(e.getMessage());
        }
    }

    public void editSong() {
        Scanner scanner = new Scanner(System.in);
        String songTitle;
        String authorName;
        String authorSurname;
        String songAlbum;
        int songTime;
        LocalDate songRelease = null;
        String dateInput;

        try {
            System.out.print("Choose song ID to edit this song: ");
            int chooseID = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter the new title: ");
            songTitle = scanner.nextLine();

            System.out.print("Enter the new author's name: ");
            authorName = scanner.nextLine();

            System.out.print("Enter the new author's surname: ");
            authorSurname = scanner.nextLine();

            System.out.print("Enter the new song's album: ");
            songAlbum = scanner.nextLine();

            System.out.print("Release date (YYYY-MM-DD): ");
            dateInput = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            songRelease = LocalDate.parse(dateInput, formatter);

            System.out.print("Enter the new song's duration time: ");
            songTime = scanner.nextInt();

            model.updateSong(chooseID, songTitle, authorName, authorSurname, songAlbum, songRelease, songTime);

        } catch (StringIsEmptyException e) {
            System.err.println(e.getMessage());
        } catch (InvalidIntException e) {
            System.err.println(e.getMessage());
        } catch (ArraySizeIsZeroException e) {
            System.err.println(e.getMessage());
        }

    }

}
