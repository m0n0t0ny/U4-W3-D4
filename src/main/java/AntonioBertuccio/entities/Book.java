package AntonioBertuccio.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book extends Catalog {
  private String author;
  private String genre;

  public Book() {
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public Book(String title, int year, int pages, String author, String genre) {
    super(title, year, pages);
    this.author = author;
    this.genre = genre;
  }
}
