package AntonioBertuccio;

import AntonioBertuccio.dao.*;
import AntonioBertuccio.entities.*;
import AntonioBertuccio.enums.Periodicity;
import com.github.javafaker.Faker;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Application {
  public static void main(String[] args) {
    System.out.println("⚪ Creazione dell'Entity Manager Factory (emf) per l'accesso al database.");
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4w3d4");

    System.out.println("⚪ Creazione DAO...");
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

    // 🗑 Rimuovi elemento per ISBN
    String isbnToRemove = "9791828958550";
    catalogDAO.deleteByIsbn(isbnToRemove);

    // 🔎 Ricerca per ISBN
    String searchIsbn = "9791828958550";
    Book foundItem = bookDAO.findBookByIsbn(searchIsbn);
    if (foundItem != null) {
      System.out.println("🟢 L'ISBN corrisponde al libro: " + foundItem.getTitle());
    } else {
      System.out.println("❌ Nessun libro trovato con ISBN: " + searchIsbn);
    }

    // 🔎 Ricerca per anno
    int searchYear = 2014;
    List<Catalog> foundItems = catalogDAO.searchByYear(searchYear);

    if (foundItems != null && !foundItems.isEmpty()) {
      System.out.println("🟢 Elementi trovati nel 2014:");

      for (Catalog item : foundItems) {
        if (item instanceof Book bookItem) {
          System.out.println("⚪ Titolo libro: " + bookItem.getTitle() + ", Autore: " + bookItem.getAuthor());
        } else if (item instanceof Magazine magazineItem) {
          System.out.println("⚪ Titolo rivista: " + magazineItem.getTitle() + ", Periodicità: " + magazineItem.getPeriodicity());
        }
      }
    } else {
      System.out.println("🔴 Nessun elemento trovato con anno di pubblicazione " + searchYear);
    }

    // 🔎 Ricerca per autore (libro)
    String searchAuthor = "Willie Nader";
    List<Book> booksByAuthor = bookDAO.searchByAuthor(searchAuthor);

    if (booksByAuthor != null && !booksByAuthor.isEmpty()) {
      System.out.println("🟢 Libri dell'autore " + searchAuthor + " trovati:");

      for (Book bookItem : booksByAuthor) {
        System.out.println("⚪ Titolo: " + bookItem.getTitle() + ", Autore: " + bookItem.getAuthor());
      }
    } else {
      System.out.println("🔴 Nessun libro trovato dell'autore " + searchAuthor);
    }

    // 🔎 Ricerca per titolo
    String searchKeyword = "the";
    List<Catalog> foundItemsWithWord = catalogDAO.searchByTitle(searchKeyword);

    if (foundItemsWithWord != null && !foundItemsWithWord.isEmpty()) {
      System.out.println("🟢 Sono stati trovati elementi contenenti la parola '" + searchKeyword + "' nel titolo:");

      for (Catalog item : foundItemsWithWord) {
        if (item instanceof Book) {
          System.out.println("⚪ Titolo del libro: " + item.getTitle() + ", Autore: " + ((Book) item).getAuthor());
        } else if (item instanceof Magazine) {
          System.out.println("⚪ Titolo della rivista: " + item.getTitle() + ", Periodicità: " + ((Magazine) item).getPeriodicity());
        }
      }
    } else {
      System.out.println("🔴 Nessun elemento trovato che contenga la parola '" + searchKeyword + "' all'interno del proprio titolo.");
    }

    long cardNumberToSearch = 36;
    long isbnToSearch = 2147483647;

    User userId = userDAO.searchUserByCardNumber(String.valueOf(cardNumberToSearch));
    Catalog catalogId = catalogDAO.searchByIsbn(String.valueOf(isbnToSearch));

    Catalog catalog = null;
    if (user != null && catalog != null) {
      Loan loan = new Loan();
      loan.setUser(user);
      loan.setCatalog(catalog);
      loan.setStartDate(new Date());
      loan.setDueDate(calculateDueDate());
      loan.setReturnDate(null);

      loanDAO.addLoan(loan);

      System.out.println("Loan created and saved successfully:");
      System.out.println("Loan ID: " + loan.getUser());
      System.out.println("User: " + loan.getUser().getFirstName() + loan.getUser().getLastName());
      System.out.println("Catalog Title: " + loan.getCatalog().getTitle());
      System.out.println("Start Date: " + loan.getStartDate());
      System.out.println("Due Date: " + loan.getDueDate());
    } else {
      System.out.println("User or Catalog not found in the database.");
    }

    System.out.println("🔴 Chiusura dell'EntityManagerFactory alla fine dell'applicazione");
    emf.close();
  }

  private static Periodicity getRandomPeriodicity() {
    Periodicity[] values = Periodicity.values();
    int randomIndex = new Random().nextInt(values.length);
    return values[randomIndex];
  }

  private static Date calculateDueDate() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    calendar.add(Calendar.DAY_OF_MONTH, 30);
    return calendar.getTime();
  }
}