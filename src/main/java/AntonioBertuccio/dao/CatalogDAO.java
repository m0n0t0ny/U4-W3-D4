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

  public void addCatalogItem(Catalog item) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    try {
      em.persist(item);
      em.getTransaction().commit();
    } catch (Exception e) {
      logger.error("Errore nell'aggiungere l'elemento: ", e);
      em.getTransaction().rollback();
    } finally {
      em.close();
    }
  }

  public Catalog findCatalogItemByIsbn(String isbn) {
    EntityManager em = emf.createEntityManager();
    try {
      return em.find(Catalog.class, isbn);
    } finally {
      em.close();
    }
  }

  public void deleteCatalogItemByIsbn(String isbn) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    try {
      Catalog item = em.find(Catalog.class, isbn);
      if (item != null) {
        em.remove(item);
      }
      em.getTransaction().commit();
    } catch (Exception e) {
      logger.error("Errore nella rimozione dell'elemento con ISBN: " + isbn, e);
      em.getTransaction().rollback();
    } finally {
      em.close();
    }
  }

  public List<Catalog> searchByTitle(String title) {
    EntityManager em = emf.createEntityManager();
    try {
      TypedQuery<Catalog> query = em.createQuery(
              "SELECT c FROM Catalog c WHERE LOWER(c.title) LIKE LOWER(:title)", Catalog.class);
      query.setParameter("title", "%" + title + "%");
      return query.getResultList();
    } catch (Exception e) {
      logger.error("Errore nella ricerca per titolo: ", e);
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
