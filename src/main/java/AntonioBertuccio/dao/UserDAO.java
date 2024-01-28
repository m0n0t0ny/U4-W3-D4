package AntonioBertuccio.dao;

import AntonioBertuccio.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.UUID;

public class UserDAO {
  private final EntityManager em;

  public UserDAO(EntityManager em) {
    this.em = em;
  }

  public void save(User user) {
    EntityTransaction transaction = em.getTransaction();
    System.out.println("⚪ Initialising transaction.");
    transaction.begin();
    System.out.println("⚪ Adding new object to Persistence Context.");
    em.persist(user);
    System.out.println("⚪ Writing on database.");
    transaction.commit();
    System.out.println("🟢 New data added.");
  }

  public User findById(UUID id) {
    return em.find(User.class, id);
  }

  public void findByIdAndDelete(UUID id) {
    User found = em.find(User.class, id);
    System.out.println("⚪ Searching object by id 🔎.");
    if (found != null) {
      EntityTransaction transaction = em.getTransaction();
      System.out.println("⚪ Initialising transaction 🔀.");
      transaction.begin();
      System.out.println("⚪ Adding new object to Persistence Context.");
      em.remove(found);
      System.out.println("⚪ Deleting object from database.");
      transaction.commit();
      System.out.println("🟢 Object deleted ❌.");
    } else {
      System.out.println("🔴 L'oggetto con uuid" + id + "non é stato trovato.");
    }
  }
}
