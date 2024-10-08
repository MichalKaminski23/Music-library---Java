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
//TODO komentarze, args(?), metoda update, try-catch, i ogólnie zabezpieczyć się przed tworzeniem np. dwóch takich samych nazw piosenek, złymi parametrami itp
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
        
        while(true)
        {   
            System.out.println("-------------------------");
            System.out.println("");
            System.out.println("          Menu");
            System.out.println("1. Show your music library");
            System.out.println("2. Create new song.");
            System.out.println("3. Delete song.");
            System.out.println("4. Update song.");
            System.out.println("5. Exit.");     
            System.out.println("");
            System.out.println("-------------------------");
            System.out.print("Enter the number (1-5) to do amazing things: ");
        
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            String songName;

            switch(choice)
            {
                case 1:
                    System.out.println("Your music library: ");
                    songListController.updateView();
                    break;
                    
                case 2:
                    System.out.println("");
                    System.out.println("Write your song paremeters:");
                    System.out.print("Name of the song: ");
                    songName = scanner.nextLine();
                    
                    System.out.print("Name of the author: ");
                    String authorName = scanner.nextLine();
                    
                    System.out.print("Surname of the author: ");
                    String authorSurname = scanner.nextLine();
                    
                    System.out.print("Album of the song (or no): ");
                    String songAlbum = scanner.nextLine();
                    
                    System.out.print("Release date: ");
                    String songRelease = scanner.nextLine();
                    
                    System.out.print("Time of the song [s]: ");
                    int songTime = scanner.nextInt();
                    
                    songListController.createNewSong(songName, authorName, authorSurname, songAlbum, songRelease, songTime);
                    break;
                    
                case 3:
                    System.out.println("");
                    songListController.updateView();
                    System.out.print("Choose method to remove the song: 1 = by name, 2 = by id: ");
                    int removeChoice = scanner.nextInt();
                    scanner.nextLine();
                    
                    if(removeChoice == 1)
                    {
                        System.out.print("Enter the name: ");
                        songName = scanner.nextLine();
                        songListController.removeSong(songName);
                    }
                    else if(removeChoice == 2)
                    {
                        System.out.print("Enter the ID: ");
                        int songID = scanner.nextInt();
                        songListController.removeSong(songID);
                    }
                    else
                    {
                        System.out.println("Bad paremeter of method.");
                        break;
                    }
                    break;
                case 4:
                    System.out.println("");
                    break;
                    
                case 5:
                    System.out.println("");
                    return;
                    
                default:
                    System.out.println("");
                    System.out.println("Bad parameter.");
                    break;
            }
            
        }
    }
}
