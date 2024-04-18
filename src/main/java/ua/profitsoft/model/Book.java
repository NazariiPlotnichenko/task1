package ua.profitsoft.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Book {
    @JsonProperty("title")
    private String title;
    @JsonProperty("author")
    private String author;
    @JsonProperty("year_published")
    private int year_published;
    @JsonProperty("genre")
    private String genre;

    public Book() {
    }

    public Book(String title, String author, int year_published, String genre) {
        this.title = title;
        this.author = author;
        this.year_published = year_published;
        this.genre = genre;
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

    public int getYearPublished() {
        return year_published;
    }

    public void setYearPublished(int year_published) {
        this.year_published = year_published;
    }

    public List<String> getGenre() {
        return Arrays.stream(genre.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void setGenres(String genres) {
        this.genre = genres;
    }
}
