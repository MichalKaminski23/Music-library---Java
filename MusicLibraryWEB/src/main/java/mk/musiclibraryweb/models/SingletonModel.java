package mk.musiclibraryweb.models;

public class SingletonModel {

    private static SongList instance;

    private SingletonModel() {
    }

    public static SongList getInstance() {

        if (instance == null) {
            instance = new SongList();
            try {
                instance.addSong("Example title 1", "Example name 1", "Example surname 1", "Example album 1", "01.12.2003", "420");
                instance.addSong("Example title 2", "Example name 2", "Example surname 2", "Example album 2", "02.12.2003", "421");
                instance.addSong("Example title 3", "Example name 3", "Example surname 3", "Example album 3", "03.12.2003", "422");
                instance.addSong("Example title 4", "Example name 4", "Example surname 4", "Example album 4", "04.12.2003", "423");
                instance.addSong("Example title 5", "Example name 5", "Example surname 5", "Example album 5", "05.12.2003", "424");

            } catch (WrongInputException ex) {
                //throw new WrongInputException("Error while loading sample songs");
            }
        }
        return instance;
    }
}
