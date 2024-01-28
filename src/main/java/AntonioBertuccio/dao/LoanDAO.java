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

  public void addLoan(Loan loan) {
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      em.persist(loan);
      em.getTransaction().commit();
      logger.info("🟢 Prestito creato e salvato con successo.");
    } catch (Exception e) {
      em.getTransaction().rollback();
      logger.error("🔴 Errore durante la creazione del prestito: ", e);
    } finally {
      em.close();
    }
  }

  public List<Loan> searchLoanByUserId(Long userId) {
    EntityManager em = emf.createEntityManager();
    try {
      TypedQuery<Loan> query = em.createQuery(
              "SELECT l FROM Loan l WHERE l.user.id = :userId", Loan.class);
      query.setParameter("userId", userId);
      return query.getResultList();
    } catch (Exception e) {
      logger.error("🔴 Errore nella ricerca dei prestiti per l'utente: ", e);
      return null;
    } finally {
      em.close();
    }
  }

  public List<Loan> searchByOutdatedLoans() {
    EntityManager em = emf.createEntityManager();
    try {
      Date currentDate = new Date();
      TypedQuery<Loan> query = em.createQuery(
              "SELECT l FROM Loan l WHERE l.dueDate < :currentDate AND l.returnDate IS NULL", Loan.class);
      query.setParameter("currentDate", currentDate);
      return query.getResultList();
    } catch (Exception e) {
      logger.error("🔴 Errore nella ricerca dei prestiti scaduti: ", e);
      return null;
    } finally {
      em.close();
    }
  }
}

