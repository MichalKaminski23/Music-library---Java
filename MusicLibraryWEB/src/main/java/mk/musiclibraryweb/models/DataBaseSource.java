package mk.musiclibraryweb.models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link DataSource} interface that interacts with the
 * database using JPA. This class provides methods for adding, retrieving,
 * updating, deleting, and checking the existence of {@link Song} objects.
 *
 * @author Michal Kaminski
 * @version 6.0
 */
public class DataBaseSource implements DataSource {

    private final EntityManagerFactory emf;

    /**
     * Constructs a new {@link DataBaseSource} instance and initializes the
     * EntityManagerFactory. The factory is created using the persistence unit
     * defined in the {@code persistence.xml} file.
     */
    public DataBaseSource() {
        emf = Persistence.createEntityManagerFactory("mk.musiclibraryweb.MusicLibraryWEB");
    }

    /**
     * Adds a new object to the database.
     *
     * @param object the object to be persisted in the database
     */
    @Override
    public void insert(Object object) {
        if (object == null) {
            throw new PersistenceException("Object can't be empty to insert!");
        }
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    /**
     * Retrieves all songs from the database.
     *
     * @return a list of all {@link Song} objects in the database
     */
    @Override
    public List<Song> getAllSongs() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT s FROM Song s");
            return query.getResultList();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return new ArrayList<>();
    }

    /**
     * Updates the details of an existing {@link Song} in the database.
     *
     * @param song the {@link Song} object with updated details
     * @return {@code true} if the update was successful, {@code false}
     * otherwise
     */
    @Override
    public boolean update(Song song) {
        if (song == null) {
            throw new PersistenceException("Song can't be empty to update!");
        }
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(song);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
        return true;
    }

    /**
     * Deletes a song from the database by its ID.
     *
     * @param songID the ID of the song to be deleted
     * @return {@code true} if the song was deleted successfully, {@code false}
     * otherwise
     */
    @Override
    public boolean delete(int songID) {
        if (songID <= 0) {
            throw new PersistenceException("Song ID can't be empty to delete!");
        }
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            var result = em.createQuery("DELETE FROM Song s WHERE s.songID=:songID")
                    .setParameter("songID", songID)
                    .executeUpdate() != 0;
            em.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return true;
    }

    /**
     * Finds a song by its ID.
     *
     * @param songID the ID of the song to find
     * @return the {@link Song} with the specified ID, or {@code null} if no
     * song was found
     */
    @Override
    public Song findById(int songID) {
        if (songID <= 0) {
            throw new IllegalArgumentException("The song ID must be positive and not null.");
        }
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Song.class, songID);
        } catch (Exception ex) {
            System.err.println("Error while finding song by ID: " + ex.getMessage());
            return null;
        }
    }

    /**
     * Checks if a song with the given title already exists in the database.
     *
     * @param songTitle the title of the song to check
     * @return {@code true} if a song with the specified title exists,
     * {@code false} otherwise
     */
    @Override
    public boolean isSongTitleTaken(String songTitle) {
        if (songTitle == null || songTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Song title cannot be null, empty, or blank.");
        }
        try (EntityManager em = emf.createEntityManager()) {
            Long count = em.createQuery("SELECT COUNT(s) FROM Song s WHERE s.songTitle = :songTitle", Long.class)
                    .setParameter("songTitle", songTitle)
                    .getSingleResult();
            return count > 0;
        } catch (Exception ex) {
            System.err.println("Error while checking title uniqueness: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Checks if a song with the given ID already exists in the database.
     *
     * @param songID the ID of the song to check
     * @return {@code true} if a song with the specified ID exists,
     * {@code false} otherwise
     */
    @Override
    public boolean isSongIDTaken(int songID) {
        if (songID <= 0) {
            throw new IllegalArgumentException("Song ID must be greater than 0.");
        }
        try (EntityManager em = emf.createEntityManager()) {
            Long count = em.createQuery("SELECT COUNT(s) FROM Song s WHERE s.songID = :songID", Long.class)
                    .setParameter("songID", songID)
                    .getSingleResult();
            return count > 0;
        } catch (Exception ex) {
            System.err.println("Error while checking song ID uniqueness: " + ex.getMessage());
            return false;
        }
    }
}
