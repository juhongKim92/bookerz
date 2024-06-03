package org.orange.bookerz.domain.book;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.orange.bookerz.api.controller.book.request.AddBookRequest;
import org.orange.bookerz.domain.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private String imagePath;

    @Builder
    public Book(String isbn, String title, String author, String publisher,String imagePath) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.imagePath = imagePath;
    }

    public static Book of(AddBookRequest request) {
        return Book.builder()
                .isbn(request.getIsbn())
                .author(request.getAuthor())
                .publisher(request.getPublisher())
                .title(request.getTitle())
                .imagePath(request.getImagePath())
                .build();
    }
}
