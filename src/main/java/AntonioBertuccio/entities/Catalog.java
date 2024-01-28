package AntonioBertuccio.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Catalog {
  @Id
  private String isbn;
  private int year;
  private int pages;

  public Catalog() {
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
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

  public Catalog(String isbn, int year, int pages) {
    this.isbn = isbn;
    this.year = year;
    this.pages = pages;
  }
}
