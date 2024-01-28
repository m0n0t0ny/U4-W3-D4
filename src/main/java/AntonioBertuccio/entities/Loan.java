package AntonioBertuccio.entities;

import AntonioBertuccio.enums.Periodicity;

import javax.persistence.*;

@Entity
@Table(name = "loans")
public class Loan {
  @Id
  @Column(name = "transaction_id")
  private long id;
  @ManyToOne
  @JoinColumn(name = "person_id")
  private User user;
  @ManyToOne
  @JoinColumn(name = "event")
  private Library library;
  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private Periodicity attendance;

  public Loan(){}
}
