package AntonioBertuccio;

import AntonioBertuccio.dao.UserDAO;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import AntonioBertuccio.dao.*;
import AntonioBertuccio.entities.*;

import java.time.LocalDate;

public class Application {
  public static void main(String[] args) {
    System.out.println("Creazione dell'Entity Manager Factory (emf) per l'accesso al database.");
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4w3d4");

    System.out.println("Creazione DAO...");
    CatalogDAO catalogDAO = new CatalogDAO(emf);
    BookDAO bookDAO = new BookDAO(emf);
    MagazineDAO magazineDAO = new MagazineDAO(emf);
    UserDAO userDAO = new UserDAO(emf);
    LoanDAO loanDAO = new LoanDAO(emf);

    User user = new User("Chiara", "Puleio", LocalDate.of(1994, 10, 29));
    userDAO.save(user);

    // 📚 Aggiunta nuovi elementi
//    Book book = new Book();
//    book.setIsbn("1234567890123");
//    book.setYear(2022);
//    book.setPages(200);
//    book.setAuthor("John Doe");
//    book.setGenre(Genre.FICTION);
//    bookDAO.addBook(book);

    // 🔎 Ricerca libri per ISBN
//    String isbnToSearch = "1234567890123";
//    Book foundBook = bookDAO.findBookByIsbn(isbnToSearch);
//    if (foundBook != null) {
//      System.out.println("Libro trovato: " + foundBook.getTitle());
//    } else {
//      System.out.println("Nessun libro trovato con ISBN: " + isbnToSearch);
//    }

    System.out.println("Chiusura dell EntityManagerFactory alla fine dell'applicazione");
    emf.close();
  }
}
