package AntonioBertuccio.dao;

import AntonioBertuccio.entities.Catalog;
import AntonioBertuccio.entities.Magazine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class MagazineDAO {
  private final CatalogDAO catalogDAO;
  private final EntityManagerFactory emf;  // Aggiungi questa linea
  private static final Logger logger = LoggerFactory.getLogger(MagazineDAO.class);

  public MagazineDAO(EntityManagerFactory emf) {
    this.emf = emf;
    this.catalogDAO = new CatalogDAO(emf);
  }

  public void addMagazine(Magazine magazine) {
    catalogDAO.addCatalogItem(magazine);
  }

  public Magazine findMagazineByIsbn(String isbn) {
    Catalog item = catalogDAO.findCatalogItemByIsbn(isbn);
    if (item instanceof Magazine) {
      return (Magazine) item;
    } else {
      logger.info("Nessuna rivista trovata con ISBN: " + isbn);
      return null;
    }
  }

  public void deleteByIsbn(String isbn) {
    catalogDAO.deleteCatalogItemByIsbn(isbn);
  }
  public List<Magazine> searchByTitle(String title) {
    EntityManager em = emf.createEntityManager();
    try {
      TypedQuery<Magazine> query = em.createQuery(
              "SELECT m FROM Magazine m WHERE LOWER(m.title) LIKE LOWER(:title)", Magazine.class);
      query.setParameter("title", "%" + title + "%");
      return query.getResultList();
    } catch (Exception e) {
      logger.error("Errore nella ricerca per titolo: ", e);
      return null;
    } finally {
      em.close();
    }
  }
}
