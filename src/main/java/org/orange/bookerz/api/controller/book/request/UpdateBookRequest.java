package org.orange.bookerz.api.controller.book.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateBookRequest {

    @NotBlank
    @Size(min = 10, max = 13)
    private String isbn;
    @NotBlank
    @Size(max = 255)
    private String title;
    @NotBlank
    @Size(max = 255)
    private String author;
    @NotBlank
    @Size(max = 255)
    private String publisher;
    @Size(max = 2000)
    private String imagePath;

    @Builder
    public UpdateBookRequest(String isbn, String title, String author, String publisher, String imagePath) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.imagePath = imagePath;
    }
}
