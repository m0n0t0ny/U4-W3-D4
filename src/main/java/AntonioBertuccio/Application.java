package AntonioBertuccio;

import AntonioBertuccio.dao.UserDAO;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import AntonioBertuccio.dao.*;
import AntonioBertuccio.entities.*;
import AntonioBertuccio.enums.Genre;
import AntonioBertuccio.enums.Periodicity;

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

    // 👤 Aggiungi utente
    User user = new User("Antonio", "Bertuccio", LocalDate.of(1992, 4, 4));
    userDAO.save(user);

    // 📚 Aggiungi libro
    Book book = new Book("1984",1948,999,"George Orwell", Genre.SCIENCE_FICTION);
    bookDAO.addBook(book);

    // 📰 Aggiungi rivista
    Magazine magazine = new Magazine("0987654321098",2023,45, Periodicity.MONTHLY);
    magazineDAO.addMagazine(magazine);

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
