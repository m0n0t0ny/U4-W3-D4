package AntonioBertuccio.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "library")
public class Library {

  @Id
  @GeneratedValue
  private long id;
  @Column(name = "title")
  private String title;
  @Column(name = "event_date")
  private LocalDate eventDate;
  @Column(name = "description")
  private String description;
  @Column(name = "max_attendants")
  private int maxAttendants;
  @OneToMany(mappedBy = "library", cascade = CascadeType.REMOVE)
  private List<Loan> loanList;

  public Library() {
  }

  public Library(String title, LocalDate eventDate, String description, int maxAttendants) {
    this.title = title;
    this.eventDate = eventDate;
    this.description = description;
    this.maxAttendants = maxAttendants;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDate getEventDate() {
    return eventDate;
  }

  public void setEventDate(LocalDate eventDate) {
    this.eventDate = eventDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getMaxAttendants() {
    return maxAttendants;
  }

  public void setMaxAttendants(int maxAttendants) {
    this.maxAttendants = maxAttendants;
  }

  @Override
  public String toString() {
    return "Event{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", eventDate=" + eventDate +
            ", description='" + description + '\'' +
            ", maxAttendants=" + maxAttendants +
            '}';
  }
}
