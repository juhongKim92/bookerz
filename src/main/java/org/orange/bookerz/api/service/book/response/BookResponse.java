package org.orange.bookerz.api.service.book.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.orange.bookerz.domain.book.Book;

@Getter
@NoArgsConstructor
public class BookResponse {

    private Long id;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private String imagePath;

    @Builder
    public BookResponse(Long id, String isbn, String title, String author, String publisher, String imagePath) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.imagePath = imagePath;
    }

    public static BookResponse of(Book book) {

        return BookResponse.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .imagePath(book.getImagePath())
                .build();
    }
}
