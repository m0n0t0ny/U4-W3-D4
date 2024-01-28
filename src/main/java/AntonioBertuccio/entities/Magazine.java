package AntonioBertuccio.entities;

import AntonioBertuccio.enums.Periodicity;

import javax.persistence.*;

@Entity
@Table(name = "magazines")
public class Magazine extends Catalog {
  @Enumerated(EnumType.STRING)
  private Periodicity periodicity;

  public Periodicity getPeriodicity() {
    return periodicity;
  }

  public void setPeriodicity(Periodicity periodicity) {
    this.periodicity = periodicity;
  }

  public Magazine(){}

  public Magazine(Periodicity periodicity) {
    this.periodicity = periodicity;
  }
}
