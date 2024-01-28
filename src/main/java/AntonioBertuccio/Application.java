package AntonioBertuccio;

import AntonioBertuccio.dao.UserDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {
  private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4w3d4");

  public static void main(String[] args) {
    EntityManager em = emf.createEntityManager();
    LibraryDAO db = new LibraryDAO(em);
    UserDAO pd = new UserDAO(em);

    try {

//      ---------- Save on DB 💾 ----------
//      Book capodanno = new Book("Capodanno", LocalDate.of(2023, 12, 31), "Festeggiamo insieme l'ultimo giorno del 2023 🎉", EventType.PRIVATE, 255);
//      db.save(capodanno);
//      User chiaraPuleio = new User("Chiara", "Puleio", LocalDate.of(1994, 10, 29));
//      pd.save((chiaraPuleio));

//      ---------- Search from DB 🔎 ----------
//      UUID id = UUID.fromString("6a5b66fc-3561-4954-adcf-8cdfae0da3e5");
//      Event capodannoFromDB = db.findById(id);
//      if (capodannoFromDB != null) {
//        System.out.println(capodannoFromDB);
//      }
//      else {
//        System.out.println("🔴 L'oggetto con uuid " + id + " non é stato trovato.");
//      }

//      ---------- Find and Delete ❌ ----------
//      db.findByIdAndDelete(UUID.fromString("86644ea7-74dc-427f-b0e7-da55e1f72564"));

    } catch (Exception ex) {
      System.err.println(ex.getMessage());

    } finally {
      System.out.println("🟢 Entity manager (em) closed ❌.");
      em.close();
      emf.close();
      System.out.println("🟢 Entity manager factory (emf) closed ❌.");
      System.out.println("🟢 End of all processes from main.");
    }

  }
}
