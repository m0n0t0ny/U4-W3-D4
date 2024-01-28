package AntonioBertuccio.dao;

import AntonioBertuccio.entities.Book;
import AntonioBertuccio.entities.Catalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class BookDAO {
  private final CatalogDAO catalogDAO;
  private final EntityManagerFactory emf;
  private static final Logger logger = LoggerFactory.getLogger(BookDAO.class);

  public BookDAO(EntityManagerFactory emf) {
    this.emf = emf;
    this.catalogDAO = new CatalogDAO(this.emf);
  }

  public void addBook(Book book) {
    catalogDAO.addItem(book);
  }

  public Book findBookByIsbn(String isbn) {
    Catalog item = catalogDAO.searchByIsbn(isbn);
    if (item instanceof Book) {
      return (Book) item;
    } else {
      return null;
    }
  }

  public void deleteByIsbn(String isbn) {
    catalogDAO.deleteByIsbn(isbn);
  }

  public List<Book> findByAuthor(String author) {
    EntityManager em = emf.createEntityManager();
    try {
      TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.author = :author", Book.class);
      query.setParameter("author", author);
      return query.getResultList();
    } catch (Exception e) {
      logger.error("🔴 Errore nella ricerca per autore: ", e);
      return null;
    } finally {
      em.close();
    }
  }

  public List<Book> findByGenre(String genre) {
    EntityManager em = emf.createEntityManager();
    try {
      TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.genre = :genre", Book.class);
      query.setParameter("genre", genre);
      return query.getResultList();
    } catch (Exception e) {
      logger.error("🔴 Errore nella ricerca per genere: ", e);
      return null;
    } finally {
      em.close();
    }
  }

  public List<Book> searchByTitle(String title) {
    EntityManager em = emf.createEntityManager();
    try {
      TypedQuery<Book> query = em.createQuery(
              "SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(:title)", Book.class);
      query.setParameter("title", "%" + title + "%");
      return query.getResultList();
    } catch (Exception e) {
      logger.error("🔴 Errore nella ricerca per titolo: ", e);
      return null;
    } finally {
      em.close();
    }
  }
}
