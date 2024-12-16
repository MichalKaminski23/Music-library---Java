package mk.musiclibraryweb.models;

import java.util.List;

public interface DataSource {

    public List<Song> getAllSongs();

    public void persistObject(Object object);

    public boolean update(Song song);

    public boolean delete(int songID);

}
