package AntonioBertuccio.dao;

import AntonioBertuccio.entities.Loan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class LoanDAO {
  private final EntityManagerFactory emf;
  private static final Logger logger = LoggerFactory.getLogger(LoanDAO.class);

  public LoanDAO(EntityManagerFactory emf) {
    this.emf = emf;
  }

  public List<Loan> findLoansByUserCardNumber(String cardNumber) {
    EntityManager em = emf.createEntityManager();
    try {
      TypedQuery<Loan> query = em.createQuery(
              "SELECT l FROM Loan l WHERE l.user.cardNumber = :cardNumber AND l.actualReturnDate IS NULL", Loan.class);
      query.setParameter("cardNumber", cardNumber);
      return query.getResultList();
    } catch (Exception e) {
      logger.error("Errore nella ricerca dei prestiti per l'utente: ", e);
      return null;
    } finally {
      em.close();
    }
  }

  public List<Loan> findOverdueLoans() {
    EntityManager em = emf.createEntityManager();
    try {
      Date currentDate = new Date();
      TypedQuery<Loan> query = em.createQuery(
              "SELECT l FROM Loan l WHERE l.dueDate < :currentDate AND l.actualReturnDate IS NULL", Loan.class);
      query.setParameter("currentDate", currentDate);
      return query.getResultList();
    } catch (Exception e) {
      logger.error("Errore nella ricerca dei prestiti scaduti: ", e);
      return null;
    } finally {
      em.close();
    }
  }
}

