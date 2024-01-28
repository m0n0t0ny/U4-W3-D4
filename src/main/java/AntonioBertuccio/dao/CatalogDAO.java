package AntonioBertuccio.dao;

import AntonioBertuccio.entities.Catalog;
import AntonioBertuccio.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.List;

public class CatalogDAO {
  private final EntityManagerFactory emf;
  private static final Logger logger = LoggerFactory.getLogger(CatalogDAO.class);

  public CatalogDAO(EntityManagerFactory emf) {
    this.emf = emf;
  }

  public void addItem(Catalog item) {
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      em.persist(item);
      em.getTransaction().commit();
    } catch (Exception e) {
      logger.error("🔴 Errore nell'aggiungere l'elemento: ", e);
      if (em.getTransaction().isActive()) {
        em.getTransaction().rollback();
      }
    } finally {
      em.close();
    }
  }

  public Catalog searchByIsbn(String isbn) {
    EntityManager em = emf.createEntityManager();
    try {
      return em.find(Catalog.class, isbn);
    } finally {
      em.close();
    }
  }

  public void deleteByIsbn(String isbn) {
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      Catalog item = em.find(Catalog.class, isbn);
      if (item != null) {
        em.remove(item);
      } else {
        logger.info("🔴 ISBN '" + isbn + "' non trovato.");
      }
      em.getTransaction().commit();
    } catch (Exception e) {
      logger.error("🔴 Errore nella rimozione dell'elemento con ISBN: " + isbn, e);
      if (em.getTransaction().isActive()) {
        em.getTransaction().rollback();
      }
    } finally {
      em.close();
    }
  }

  public List<Catalog> searchByYear(int year) {
    EntityManager em = emf.createEntityManager();
    try {
      TypedQuery<Catalog> query = em.createQuery(
              "SELECT c FROM Catalog c WHERE c.year = :year", Catalog.class);
      query.setParameter("year", year);
      return query.getResultList();
    } catch (Exception e) {
      logger.error("🔴 Errore nella ricerca per anno: " + year, e);
      return null;
    } finally {
      em.close();
    }
  }

  public List<Catalog> searchByTitle(String titleKeyword) {
    EntityManager em = emf.createEntityManager();
    try {
      TypedQuery<Catalog> query = em.createQuery(
              "SELECT c FROM Catalog c WHERE c.title LIKE :keyword", Catalog.class);
      query.setParameter("keyword", "%" + titleKeyword + "%");
      return query.getResultList();
    } catch (Exception e) {
      logger.error("🔴 Errore nella ricerca per titolo: ", e);
      return null;
    } finally {
      em.close();
    }
  }

  public void closeEntityManagerFactory() {
    if (emf != null && emf.isOpen()) {
      emf.close();
    }
  }
}
