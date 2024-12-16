package mk.musiclibraryweb.models;

/**
 * SingletonModel is a class that implements the Singleton design pattern for managing a single instance of SongList.
 * It ensures that there is only one instance of SongList throughout the application.
 * The instance is initialized with some sample songs if it doesn't already exist.
 * 
 * @author Michal Kaminski
 * @version 5.0
 */
public class SingletonModel {

//    // The single instance of SongList.
//    private static SongList instance;
//
//    // Private constructor to prevent instantiation from outside.
//    private SingletonModel() {
//    }
//
//    /**
//     * Retrieves the single instance of the SongList. If the instance doesn't exist yet,
//     * it will be created and initialized with sample songs.
//     *
//     * @return the instance of SongList.
//     */
//    public static SongList getInstance() {
//
//        if (instance == null) {
//            instance = new SongList();
//            try {
//                instance.addSong("Example title 1", "Example name 1", "Example surname 1", "Example album 1", "01.12.2003", "420");
//                instance.addSong("Example title 2", "Example name 2", "Example surname 2", "Example album 2", "02.12.2003", "421");
//                instance.addSong("Example title 3", "Example name 3", "Example surname 3", "Example album 3", "03.12.2003", "422");
//                instance.addSong("Example title 4", "Example name 4", "Example surname 4", "Example album 4", "04.12.2003", "423");
//                instance.addSong("Example title 5", "Example name 5", "Example surname 5", "Example album 5", "05.12.2003", "424");
//
//            } catch (WrongInputException ex) {
//                //throw new WrongInputException("Error while loading sample songs");
//            }
//        }
//        return instance;
//    }
}
