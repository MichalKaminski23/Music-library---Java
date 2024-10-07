/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
//project name: music library
package com.mycompany.music_library_app;
import Controller.SongController;
import Model.Song;
import View.SongView;

import Controller.SongListController;
import Model.SongList;
import View.SongListView;
import java.util.Scanner;
/**
 *
 * @author placu
 */
public class MusicLibrary 
{

    public static void main(String[] args) 
    {
        
        SongList songList = new SongList();
        SongListView songListView = new SongListView();
        SongListController songListController = new SongListController(songList, songListView);
        
        /*
        Song model1 = new Song("Sigma", "Oliwier", "Roszczyk", "unknown", "05.09.2024", 188);
        
        SongView view1 = new SongView();
        
        SongController controller1 = new SongController(model1, view1);
        
        controller1.printView();
        
        System.out.println("----------------------------------");
        
        controller1.setSongAlbum("Some text to try this function");
        
        controller1.printView();
        
        System.out.println("----------------------------------");

        String test = controller1.getSongAlbum(model1.getSongAlbum());
        
        System.out.println("After update: " + test);
        */
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter info:");
        String name = sc.next();
        String name2 = sc.next();
        String surname = sc.next();
        String album = sc.next();
        String date = sc.next();
        int time = sc.nextInt();
        
        songListController.createNewSong(name, name2, surname, album, date, time);
        
        songListController.updateView();
        
       

    }
}
