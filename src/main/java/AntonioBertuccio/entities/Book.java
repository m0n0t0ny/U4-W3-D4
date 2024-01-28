package AntonioBertuccio.entities;

import AntonioBertuccio.enums.Genre;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book extends Catalog {
  private String title;
  private String author;
  @Enumerated
  private Genre genre;

  public String getAuthor() {
    return author;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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

  public Book(){}
}
