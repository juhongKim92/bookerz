package org.orange.bookerz.api.controller.book.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateBookRequest {

    @Size(min = 10, max = 13)
    private String isbn;
    @Size(min = 1,max = 255)
    private String title;
    @Size(min = 1,max = 255)
    private String author;
    @Size(min = 1,max = 255)
    private String publisher;
    @Size(min = 1,max = 2000)
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
