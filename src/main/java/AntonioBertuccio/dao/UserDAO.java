package AntonioBertuccio.dao;

import AntonioBertuccio.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class UserDAO {
  private final EntityManagerFactory emf;
  private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

  public UserDAO(EntityManagerFactory emf) {
    this.emf = emf;
  }

  public void save(User user) {
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();
    System.out.println("⚪ Initializing transaction.");
    transaction.begin();
    System.out.println("⚪ Adding new object to Persistence Context.");
    em.persist(user);
    System.out.println("⚪ Writing to the database.");
    transaction.commit();
    System.out.println("🟢 New data added.");
    em.close();
  }

  public User findUserByCardNumber(String cardNumber) {
    EntityManager em = emf.createEntityManager();
    try {
      return em.find(User.class, cardNumber);
    } finally {
      em.close();
    }
  }
}