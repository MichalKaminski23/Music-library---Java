/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.music_library_app;
import Controller.SongListController;
import Model.SongList;
import View.SongListView;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author placu
 */
//TODO komentarze, args(?), try-catch i ogólnie zabezpieczyć się przed tworzeniem np. dwóch takich samych nazw piosenek, złymi parametrami itp
public class MusicLibrary 
{
    public static void main(String[] args) 
    {
        System.out.println("Your music library - enjoy!");
        System.out.println("");
        
        Scanner scanner = new Scanner(System.in);
        
        SongList songList = new SongList();
        SongListView songListView = new SongListView();
        SongListController songListController = new SongListController(songList, songListView);
        
        String songTitle;
        String authorName;
        String authorSurname;
        String songAlbum;
        int songTime;
        LocalDate songRelease = null;

            
        while(true)
        {   
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
            

            switch(choice)
            {
                case "a":
                    System.out.println("Your music library: ");
                    songListController.updateView();
                    break;
                    
                case "b":
                    try
                    {
                    System.out.println("");
                    System.out.println("Write your song paremeters:");
                    
                    System.out.print("Title of the song: ");
                    songTitle = scanner.nextLine();
                    if (songTitle.isBlank() || songTitle.matches("\\d+")) 
                    {
                        throw new IllegalArgumentException("Title of the song cannot be empty or includes only numbers.");
                    }
                    
                    System.out.print("Name of the author: ");
                    authorName = scanner.nextLine();
                    if(authorName.isBlank() || authorName.matches("\\d+"))
                    {
                        throw new IllegalArgumentException("Name of the author cannot be empty or includes only numbers.");
                    }
                    
                    System.out.print("Surname of the author: ");
                    authorSurname = scanner.nextLine();
                    if(authorSurname.isBlank() || authorSurname.matches("\\d+"))
                    {
                        throw new IllegalArgumentException("Surname of the author cannot be empty or includes only numbers.");
                    }
                    
                    System.out.print("Album of the song (or no): ");
                    songAlbum = scanner.nextLine();
                    if(songAlbum.isBlank() || songTitle.matches("\\d+"))
                    {
                        throw new IllegalArgumentException("Surname of the author cannot be empty or includes only numbers.");
                    }
                    
                    System.out.print("Release date (YYYY-MM-DD): ");
                    String dateInput = scanner.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    try 
                    {
                        songRelease = LocalDate.parse(dateInput, formatter);
                    } 
                    catch (DateTimeParseException e) 
                    {
                        throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD.");
                    }
                    
                    System.out.print("Time of the song [s]: ");
                    try 
                    {
                       songTime = scanner.nextInt();
                       if(songTime <= 0)
                       {
                           throw new IllegalArgumentException("Song time must be a number and be bigger than 0");
                       }
                    }
                    catch(InputMismatchException e)
                    {
                       throw new InputMismatchException("Invalid input for song time. Please enter a valid number.");
                       
                    }
                    
                    songListController.createNewSong(songTitle, authorName, authorSurname, songAlbum, songRelease, songTime);
                    scanner.nextLine();
                    break;
                    
                    }
                    catch (IllegalArgumentException e) 
                    {
                        System.out.println("---------------------");
                        System.out.println("Error: " + e.getMessage());
                        System.out.println("---------------------");
                        break;
                    }
                    
                case "c":
                    System.out.println("");
                    songListController.updateView();
                    System.out.print("Choose method to remove the song: n = by name, i = by id: ");
                    String removeChoice = scanner.next();
                    scanner.nextLine();
                    
                    if(removeChoice.equals("n"))
                    {
                        System.out.print("Enter the name: ");
                        songTitle = scanner.nextLine();
                        songListController.removeSong(songTitle);
                    }
                    else if(removeChoice.equals("i"))
                    {
                        System.out.print("Enter the ID: ");
                        int songID = scanner.nextInt();
                        songListController.removeSong(songID);
                    }
                    else
                    {
                        System.out.println("---------------------");
                        System.out.println("Bad paremeter of method.");
                        System.out.println("---------------------");
                        break;
                    }
                    break;
                    
                case "d":
                    System.out.println("");
                    songListController.updateView();
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
                    String dateInput = scanner.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    try 
                    {
                        songRelease = LocalDate.parse(dateInput, formatter);
                    } 
                    catch (DateTimeParseException e) 
                    {
                        throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD.");
                    }
                    
                    System.out.print("Enter the new song's duration time: ");
                    songTime = scanner.nextInt();
                    
                    songListController.editSong(chooseID, songTitle, authorName, authorSurname, songAlbum, songRelease, songTime);
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
