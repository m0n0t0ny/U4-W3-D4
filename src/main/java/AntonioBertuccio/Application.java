package AntonioBertuccio;

import AntonioBertuccio.dao.*;
import AntonioBertuccio.entities.Book;
import AntonioBertuccio.entities.Magazine;
import AntonioBertuccio.entities.User;
import AntonioBertuccio.enums.Periodicity;
import com.github.javafaker.Faker;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Random;

public class Application {
  public static void main(String[] args) {
    System.out.println("Creazione dell'Entity Manager Factory (emf) per l'accesso al database.");
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4w3d4");

    System.out.println("Creazione DAO...");
    Faker faker = new Faker();
    CatalogDAO catalogDAO = new CatalogDAO(emf);
    BookDAO bookDAO = new BookDAO(emf);
    MagazineDAO magazineDAO = new MagazineDAO(emf);
    UserDAO userDAO = new UserDAO(emf);
    LoanDAO loanDAO = new LoanDAO(emf);

    // 👤 Aggiungi utente
    User user = new User(faker.name().firstName(), faker.name().lastName(), faker.date().birthday(18, 99));
    userDAO.save(user);

    // 📚 Aggiungi libro
    Book book = new Book(faker.book().title(), faker.number().numberBetween(1900, 2024), faker.number().numberBetween(20, 2000), faker.book().author(), faker.book().genre());
    bookDAO.addBook(book);

    // 📰 Aggiungi rivista
    Magazine magazine = new Magazine(faker.book().title(), faker.number().numberBetween(2010, 2024), faker.number().numberBetween(10, 100), getRandomPeriodicity());
    magazineDAO.addMagazine(magazine);

    // 🔎 Ricerca libri per ISBN
    String searchIsbn = "9791828958550";
    Book foundBook = bookDAO.findBookByIsbn(searchIsbn);
    if (foundBook != null) {
      System.out.println("🟢 L'ISBN corrisponde al libro: " + foundBook.getTitle());
    } else {
      System.out.println("❌ Nessun libro trovato con ISBN: " + searchIsbn);
    }

    System.out.println("🔴 Chiusura dell EntityManagerFactory alla fine dell'applicazione");
    emf.close();
  }

  private static Periodicity getRandomPeriodicity() {
    Periodicity[] values = Periodicity.values();
    int randomIndex = new Random().nextInt(values.length);
    return values[randomIndex];
  }
}
