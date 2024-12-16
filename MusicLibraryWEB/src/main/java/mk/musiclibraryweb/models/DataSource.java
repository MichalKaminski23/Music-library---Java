package mk.musiclibraryweb.models;

import java.util.List;

public interface DataSource {

    public List<Song> getAllSongs();

    public void persistObject(Object object) throws WrongInputException;

    public boolean update(Song song) throws WrongInputException;

    public boolean delete(int songID);

}
