package AntonioBertuccio.dao;


import AntonioBertuccio.entities.Loan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class LoanDAO {
  private final EntityManagerFactory emf;
  private static final Logger logger = LoggerFactory.getLogger(LoanDAO.class);

  public LoanDAO() {
    emf = Persistence.createEntityManagerFactory("u4w3d4");
  }

  public void addLoan(Loan loan) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    try {
      em.persist(loan);
      em.getTransaction().commit();
    } catch (Exception exception) {
      logger.error("Errore nell'aggiungere il prestito: ", exception);
      em.getTransaction().rollback();
    } finally {
      em.close();
    }
  }

  public Loan findLoanById(Long id) {
    EntityManager em = emf.createEntityManager();
    try {
      return em.find(Loan.class, id);
    } finally {
      em.close();
    }
  }

  public List<Loan> findAllLoans() {
    EntityManager em = emf.createEntityManager();
    try {
      TypedQuery<Loan> query = em.createQuery("SELECT l FROM Loan l", Loan.class);
      return query.getResultList();
    } finally {
      em.close();
    }
  }

  public void updateLoan(Loan loan) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    try {
      em.merge(loan);
      em.getTransaction().commit();
    } catch (Exception exception) {
      logger.error("Errore nell'aggiornare il prestito: ", exception);
      em.getTransaction().rollback();
    } finally {
      em.close();
    }
  }

  public void deleteLoan(Loan loan) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    try {
      em.remove(em.contains(loan) ? loan : em.merge(loan));
      em.getTransaction().commit();
    } catch (Exception exception) {
      logger.error("Errore nell'eliminare il prestito: ", exception);
      em.getTransaction().rollback();
    } finally {
      em.close();
    }
  }
}
