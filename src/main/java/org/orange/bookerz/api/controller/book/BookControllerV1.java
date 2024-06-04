package org.orange.bookerz.api.controller.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.orange.bookerz.api.controller.book.request.AddBookRequest;
import org.orange.bookerz.api.controller.book.request.UpdateBookRequest;
import org.orange.bookerz.api.service.book.BookService;
import org.orange.bookerz.api.service.book.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class BookControllerV1 {

    private final BookService bookService;
    private final ObjectMapper objectMapper;

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
    public ResponseEntity<BookResponse> getBook(@PathVariable Long id) {
        BookResponse response = bookService.findBook(id);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/book/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody @Valid UpdateBookRequest request) {
        String title = bookService.updateBook(id, request);
        return ResponseEntity.ok().body(title + " is updated.");
    }

    @DeleteMapping("/book/{id}")
    ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().body("deleteSuccess");
    }
}
