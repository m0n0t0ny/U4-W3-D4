package AntonioBertuccio.entities;

import AntonioBertuccio.enums.Genre;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book extends Catalog {
  private String author;
  @Enumerated(EnumType.STRING)
  private Genre genre;

  public Book() {
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public Book(String title, int year, int pages, String author, Genre genre) {
    super(title, year, pages);
    this.author = author;
    this.genre = genre;
  }
}
