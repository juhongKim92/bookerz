package org.orange.bookerz.api.controller.book;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.orange.bookerz.api.controller.book.request.AddBookRequest;
import org.orange.bookerz.api.controller.book.request.UpdateBookRequest;
import org.orange.bookerz.api.service.book.BookService;
import org.orange.bookerz.api.service.book.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class BookControllerV1 {

    private final BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<String> addBook(@RequestBody @Valid AddBookRequest request) {
        String title = bookService.addBook(request);
        return ResponseEntity.ok().body(title + " is registered.");
    }

    @GetMapping("/books")
    public ResponseEntity<Page<BookResponse>> getBooks(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {

        Page<BookResponse> response = bookService.getBooks(page, size);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable long id) {
        BookResponse response = bookService.findBook(id);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/book/{id}")
    public void updateBook(@RequestBody @Valid UpdateBookRequest request) {

    }
}
