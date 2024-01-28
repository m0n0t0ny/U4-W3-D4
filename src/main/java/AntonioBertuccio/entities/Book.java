package AntonioBertuccio.entities;

import AntonioBertuccio.enums.Genre;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book extends Catalog {
  private String title;
  private String author;
  @Enumerated(EnumType.STRING)
  private String genre;

  public Book() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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

  public Book(String isbn, int year, int pages, String title, String author, Genre genre) {
    super(isbn, year, pages);
    this.title = title;
    this.author = author;
    this.genre = genre.toString();
  }
}
