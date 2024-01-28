package AntonioBertuccio.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "loans")
public class Loan {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "transaction_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "catalog_id")
  private Catalog catalog;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "start_date")
  private Date startDate;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "due_date")
  private Date dueDate;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "return_date")
  private Date returnDate;

  @ManyToOne
  @JoinColumn(name = "library_id")
  private Library library;

  public Loan() {
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Catalog getCatalog() {
    return catalog;
  }

  public void setCatalog(Catalog catalog) {
    this.catalog = catalog;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public Date getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(Date returnDate) {
    this.returnDate = returnDate;
  }

  public Loan(User user, Catalog catalog, Date startDate, Date dueDate, Date returnDate) {
    this.user = user;
    this.catalog = catalog;
    this.startDate = startDate;
    this.dueDate = dueDate;
    this.returnDate = returnDate;
  }
}