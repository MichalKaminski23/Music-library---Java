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
 * updating, deleting, and checking the existence of {@link Song} and
 * {@link Album} objects.
 *
 * It manages the persistence operations by utilizing an
 * {@link EntityManagerFactory} to create {@link EntityManager} instances for
 * interacting with the database.
 *
 * This class ensures that all database transactions are properly handled,
 * including committing successful transactions and rolling back in case of
 * exceptions.
 *
 * @author Michal Kaminski
 * @version 6.0
 */
public class DataBaseSource implements DataSource {

    /**
     * The factory for creating {@link EntityManager} instances. It is
     * initialized using the specified persistence unit.
     */
    private final EntityManagerFactory emf;

    /**
     * Constructs a new {@link DataBaseSource} instance and initializes the
     * {@link EntityManagerFactory}. The factory is created using the
     * persistence unit defined in the {@code persistence.xml} file.
     *
     * The persistence unit name {@code mk.musiclibraryweb.MusicLibraryWEB}
     * should correspond to a valid configuration in your
     * {@code persistence.xml}.
     */
    public DataBaseSource() {
        emf = Persistence.createEntityManagerFactory("mk.musiclibraryweb.MusicLibraryWEB");
    }

    /**
     * Adds a new object to the database.
     *
     * This method persists the provided object within a transaction. If the
     * object is {@code null}, a {@link PersistenceException} is thrown. In case
     * of any persistence errors during the transaction, it is rolled back to
     * maintain data integrity.
     *
     * @param object the object to be persisted in the database
     * @throws PersistenceException if the object is {@code null} or if a
     * persistence error occurs
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
     * This method executes a JPQL query to select all instances of {@link Song}
     * from the database. If an error occurs during the transaction, it is
     * rolled back, and an empty list is returned.
     *
     * @return a list of all {@link Song} objects in the database, or an empty
     * list if none are found
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
     * This method merges the state of the given {@link Song} into the current
     * persistence context. If the {@code song} is {@code null}, a
     * {@link PersistenceException} is thrown. In case of any exceptions during
     * the transaction, it is rolled back.
     *
     * @param song the {@link Song} object with updated details
     * @return {@code true} if the update was successful, {@code false}
     * otherwise
     * @throws PersistenceException if the {@code song} is {@code null} or if a
     * persistence error occurs
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
     * This method executes a JPQL delete query to remove the {@link Song} with
     * the specified ID. If the song does not exist, it logs a message and
     * returns {@code false}. In case of any exceptions during the transaction,
     * it is rolled back.
     *
     * @param songID the ID of the song to be deleted
     * @return {@code true} if the song was deleted successfully, {@code false}
     * otherwise
     * @throws PersistenceException if the {@code songID} is invalid (e.g.,
     * non-positive)
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
     * This method retrieves a {@link Song} entity based on the provided ID. If
     * the {@code songID} is invalid (e.g., non-positive), an
     * {@link IllegalArgumentException} is thrown.
     *
     * @param songID the ID of the song to find
     * @return the {@link Song} with the specified ID, or {@code null} if no
     * song was found
     * @throws IllegalArgumentException if the {@code songID} is not positive
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
     * This method executes a JPQL query to count the number of {@link Song}
     * entities with the specified title. It returns {@code true} if at least
     * one such song exists.
     *
     * @param songTitle the title of the song to check
     * @return {@code true} if a song with the specified title exists,
     * {@code false} otherwise
     * @throws IllegalArgumentException if the {@code songTitle} is
     * {@code null}, empty, or blank
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
     * This method executes a JPQL query to count the number of {@link Song}
     * entities with the specified ID. It returns {@code true} if at least one
     * such song exists.
     *
     * @param songID the ID of the song to check
     * @return {@code true} if a song with the specified ID exists,
     * {@code false} otherwise
     * @throws IllegalArgumentException if the {@code songID} is not positive
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

    /**
     * Finds an album by its ID.
     *
     * This method retrieves an {@link Album} entity based on the provided ID.
     * If the album does not exist, it returns {@code null}.
     *
     * @param albumID the ID of the album to find
     * @return the {@link Album} with the specified ID, or {@code null} if no
     * album was found
     * @throws IllegalArgumentException if the {@code albumID} is not positive
     */
    @Override
    public Album findAlbumByID(int albumID) {
        if (albumID <= 0) {
            throw new IllegalArgumentException("The album ID must be positive and not null.");
        }
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Album.class, albumID);
        } finally {
            em.close();
        }
    }

    /**
     * Retrieves all albums from the database.
     *
     * This method executes a JPQL query to select all instances of
     * {@link Album} from the database. If an error occurs during the
     * transaction, it is rolled back, and an empty list is returned.
     *
     * @return a list of all {@link Album} objects in the database, or an empty
     * list if none are found
     */
    @Override
    public List<Album> getAllAlbums() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT a FROM Album a");
            return query.getResultList();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return new ArrayList<>();
    }

    /**
     * Removes the album with the specified ID from the database, along with any
     * associated songs. This method uses a JPA transaction to execute both
     * deletions atomically. If the album exists, all songs linked to that album
     * are deleted first, followed by the album itself. In case of an error, the
     * transaction is rolled back.
     *
     * @param albumID the unique identifier of the album to remove
     * @return {@code true} if the album (and its related songs) were
     * successfully deleted; {@code false} otherwise
     */
    @Override
    public boolean deleteAlbum(int albumID) {
        EntityManager em = emf.createEntityManager();
        if (albumID <= 0) {
            throw new PersistenceException("Album ID can't be empty to delete!");
        }
        try {
            em.getTransaction().begin();
            Album album = em.find(Album.class, albumID);

            if (album != null) {
                Query query = em.createQuery("DELETE FROM Song s WHERE s.songAlbum = :album");
                query.setParameter("album", album);
                query.executeUpdate();

                em.remove(album);
            }

            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    /**
     * Checks if an album with the given name already exists in the database.
     *
     * This method executes a JPQL query to count the number of {@link Album}
     * entities with the specified name. It returns {@code true} if at least one
     * such album exists.
     *
     * @param albumName the name of the album to check
     * @return {@code true} if an album with the specified name exists,
     * {@code false} otherwise
     * @throws IllegalArgumentException if the {@code albumName} is
     * {@code null}, empty, or blank
     */
    @Override
    public boolean isAlbumNameTaken(String albumName) {
        if (albumName == null || albumName.trim().isEmpty()) {
            throw new IllegalArgumentException("Album name cannot be null, empty, or blank.");
        }
        try (EntityManager em = emf.createEntityManager()) {
            Long count = em.createQuery("SELECT COUNT(a) FROM Album a WHERE a.albumName = :albumName", Long.class)
                    .setParameter("albumName", albumName)
                    .getSingleResult();
            return count > 0;
        } catch (Exception ex) {
            System.err.println("Error while checking names uniqueness: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Checks if an album with the given ID already exists in the database.
     *
     * This method executes a JPQL query to count the number of {@link Album}
     * entities with the specified ID. It returns {@code true} if at least one
     * such album exists.
     *
     * @param albumID the ID of the album to check
     * @return {@code true} if an album with the specified ID exists,
     * {@code false} otherwise
     * @throws IllegalArgumentException if the {@code albumID} is not positive
     */
    @Override
    public boolean isAlbumIDTaken(int albumID) {
        if (albumID <= 0) {
            throw new IllegalArgumentException("Album ID must be greater than 0.");
        }
        try (EntityManager em = emf.createEntityManager()) {
            Long count = em.createQuery("SELECT COUNT(a) FROM Album a WHERE a.albumID = :albumID", Long.class)
                    .setParameter("albumID", albumID)
                    .getSingleResult();
            return count > 0;
        } catch (Exception ex) {
            System.err.println("Error while checking album ID uniqueness: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Finds all song titles associated with a specific album ID.
     *
     * This method executes a JPQL query to retrieve the titles of all
     * {@link Song} entities that belong to the album with the specified ID.
     *
     * @param albumID the ID of the album whose song titles are to be retrieved
     * @return a list of song titles associated with the specified album, or an
     * empty list if none are found
     * @throws IllegalArgumentException if the {@code albumID} is not positive
     */
    @Override
    public List<String> findSongTitlesByAlbumID(int albumID) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery(
                    "SELECT s.songTitle FROM Song s WHERE s.songAlbum.albumID = :albumID", String.class);
            query.setParameter("albumID", albumID);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
