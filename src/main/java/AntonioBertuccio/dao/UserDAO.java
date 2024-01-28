package AntonioBertuccio.dao;

import AntonioBertuccio.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class UserDAO {
  private final EntityManagerFactory emf;
  private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

  public UserDAO(EntityManagerFactory emf) {
    this.emf = emf;
  }

  public void addUser(User user) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    try {
      em.persist(user);
      em.getTransaction().commit();
    } catch (Exception e) {
      logger.error("Errore nell'aggiungere l'utente: ", e);
      em.getTransaction().rollback();
    } finally {
      em.close();
    }
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
