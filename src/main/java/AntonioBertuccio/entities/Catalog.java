package AntonioBertuccio.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Catalog {
  @Id
  @GeneratedValue
  private String isbn;
  private String title;
  private int year;
  private int pages;

  public Catalog() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getPages() {
    return pages;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }

  public Catalog(String title, int year, int pages) {
    this.title = title;
    this.year = year;
    this.pages = pages;
  }
}
