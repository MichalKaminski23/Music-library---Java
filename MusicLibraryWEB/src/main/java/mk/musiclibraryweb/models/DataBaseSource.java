package mk.musiclibraryweb.models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class DataBaseSource implements DataSource {

    private final EntityManagerFactory emf;

    public DataBaseSource() {
        emf = Persistence.createEntityManagerFactory("mk.musiclibraryweb.MusicLibraryWEB");
    }

    @Override
    public void persistObject(Object object) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Song> getAllSongs() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("SELECT s FROM Song s");
            return query.getResultList();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return new ArrayList<>();
    }

    @Override
    public boolean update(Song song) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(song);
            em.getTransaction().commit();
        } catch (Exception ex) {
        }
        return true;
    }

    @Override
    public boolean delete(int songID) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            var result = em.createQuery("DELETE FROM Song s WHERE s.songID=:songID").setParameter("songID", songID).executeUpdate() != 0;
            em.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return true;
    }

}
