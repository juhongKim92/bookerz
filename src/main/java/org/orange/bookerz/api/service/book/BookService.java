package org.orange.bookerz.api.service.book;

import lombok.RequiredArgsConstructor;
import org.orange.bookerz.api.controller.book.request.AddBookRequest;
import org.orange.bookerz.api.controller.book.request.UpdateBookRequest;
import org.orange.bookerz.api.exception.AlreadyExistException;
import org.orange.bookerz.api.exception.InvalidRequestException;
import org.orange.bookerz.api.service.book.response.BookResponse;
import org.orange.bookerz.domain.book.Book;
import org.orange.bookerz.domain.book.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public String addBook(AddBookRequest request) {

        Optional<Book> bookByIsbn = findBookByIsbn(request.getIsbn());
        if (bookByIsbn.isPresent()) {
            throw new AlreadyExistException("Book is already exist.");
        }

        Book book = Book.of(request);
        bookRepository.save(book);
        return book.getTitle();
    }

    public Page<BookResponse> getBooks(int page, int offset) {
        Pageable pageable = PageRequest.of(page, offset);

        return bookRepository.findAll(pageable).map(BookResponse::of);
    }

    public BookResponse findBook(long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new InvalidRequestException("id", "check book id"));
        return BookResponse.of(book);
    }

    public String updateBook(Long bookId,UpdateBookRequest request) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new InvalidRequestException("id", "check book id"));
        book.updateBookInfo(request);
        bookRepository.save(book);
        return book.getTitle();
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    private Optional<Book> findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

}
