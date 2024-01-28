package AntonioBertuccio.dao;

import AntonioBertuccio.entities.Magazine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class MagazineDAO {
  private final EntityManagerFactory emf;
  private static final Logger logger = LoggerFactory.getLogger(MagazineDAO.class);

  public MagazineDAO() {
    emf = Persistence.createEntityManagerFactory("u4w3d4");
  }

  public void addMagazine(Magazine magazine) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    try {
      em.persist(magazine);
      em.getTransaction().commit();
    } catch (Exception e) {
      logger.error("Errore nell'aggiungere la rivista: ", e);
      em.getTransaction().rollback();
    } finally {
      em.close();
    }
  }

  public Magazine findMagazineByIsbn(String isbn) {
    EntityManager em = emf.createEntityManager();
    try {
      return em.find(Magazine.class, isbn);
    } finally {
      em.close();
    }
  }

  public List<Magazine> findAllMagazines() {
    EntityManager em = emf.createEntityManager();
    try {
      TypedQuery<Magazine> query = em.createQuery("SELECT m FROM Magazine m", Magazine.class);
      return query.getResultList();
    } finally {
      em.close();
    }
  }

  public void updateMagazine(Magazine magazine) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    try {
      em.merge(magazine);
      em.getTransaction().commit();
    } catch (Exception exception) {
      logger.error("Errore nell'aggiornare la rivista: ", exception);
      em.getTransaction().rollback();
    } finally {
      em.close();
    }
  }

  public void deleteMagazine(Magazine magazine) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    try {
      em.remove(em.contains(magazine) ? magazine : em.merge(magazine));
      em.getTransaction().commit();
    } catch (Exception exception) {
      logger.error("Errore nell'eliminare la rivista: ", exception);
      em.getTransaction().rollback();
    } finally {
      em.close();
    }
  }
}
