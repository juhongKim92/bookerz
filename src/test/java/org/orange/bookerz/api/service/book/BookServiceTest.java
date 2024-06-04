package org.orange.bookerz.api.service.book;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.orange.bookerz.api.controller.book.request.AddBookRequest;
import org.orange.bookerz.api.controller.book.request.UpdateBookRequest;
import org.orange.bookerz.api.exception.AlreadyExistException;
import org.orange.bookerz.api.service.book.response.BookResponse;
import org.orange.bookerz.domain.book.Book;
import org.orange.bookerz.domain.book.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.rmi.AlreadyBoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("중복된 isbn 입력시 에러 발생")
    void duplicateError() throws Exception{
        // given
        AddBookRequest request = AddBookRequest.builder()
                .title("title")
                .author("author")
                .isbn("isbn")
                .publisher("publisher")
                .imagePath("path")
                .build();

        when(bookRepository.findByIsbn("isbn")).thenReturn(Optional.of(new Book()));
        // when
        AlreadyExistException exception = assertThrows(AlreadyExistException.class, () -> {
            bookService.addBook(request);
        });

        assertThat(exception.getMessage()).isEqualTo("Book is already exist.");
    }

    @Test
    @DisplayName("도서 등록 성공 시 도서 제목을 반환 한다.")
    void bookAddSuccess() throws Exception{
        // given
        AddBookRequest request = AddBookRequest.builder()
                .title("title")
                .author("author")
                .isbn("isbn")
                .publisher("publisher")
                .imagePath("path")
                .build();

        Book book = Book.of(request);

        // when
        when(bookRepository.findByIsbn(request.getIsbn())).thenReturn(Optional.empty());
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        String title = bookService.addBook(request);

        // then
        assertThat(title).isEqualTo(request.getTitle());
        verify(bookRepository).save(any(Book.class));
    }

    @Test
    @DisplayName("도서 목록 페이징 조회")
    void getBooksByPaging() throws Exception{
        // given
        int page = 0;
        int offset = 4;
        Pageable pageable = PageRequest.of(page, offset);

        Book book1 = new Book("title1", "author1", "isbn1", "publisher1", "path1");
        Book book2 = new Book("title2", "author2", "isbn2", "publisher2", "path2");

        List<Book> books = Arrays.asList(book1, book2);
        Page<Book> bookPage = new PageImpl<>(books, pageable, books.size());
        // when
        when(bookRepository.findAll(any(Pageable.class))).thenReturn(bookPage);
        Page<BookResponse> bookResponses = bookService.getBooks(page, offset);

        // then
        assertThat(bookResponses.getContent()).hasSameSizeAs(books);
        assertThat(bookResponses.getContent().get(0).getTitle()).isEqualTo(books.get(0).getTitle());
        assertThat(bookResponses.getContent().get(1).getTitle()).isEqualTo(books.get(1).getTitle());
        verify(bookRepository).findAll(any(Pageable.class));
    }

    @Test
    @DisplayName("책의 id 를 통해 책을 조회 할 수 있다.")
    void findBookById() throws Exception{
        // given
        Book savedBook = new Book("title1", "author1", "isbn1", "publisher1", "path1");
        long bookId = 1L;

        // when
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(savedBook));
        BookResponse bookResponses = bookService.findBook(bookId);

        // then
        assertThat(bookResponses.getTitle()).isEqualTo(savedBook.getTitle());
        assertThat(bookResponses.getIsbn()).isEqualTo(savedBook.getIsbn());
        verify(bookRepository).findById(bookId);
    }

    @Test
    @DisplayName("책의 정보들을 업데이트 할 수 있다.")
    void updateBookInfo() throws Exception{
        // given
        Book savedBook = new Book("title1", "author1", "isbn1", "publisher1", "path1");
        long bookId = 1L;
        UpdateBookRequest updateRequest = UpdateBookRequest.builder()
                .title("newTitle")
                .publisher("newPublisher")
                .build();

        // when
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(savedBook));
        bookService.updateBook(bookId, updateRequest);
        // then
        verify(bookRepository).findById(bookId);
        verify(bookRepository).save(any(Book.class));
        assertThat(savedBook.getTitle()).isEqualTo(updateRequest.getTitle());
        assertThat(savedBook.getPublisher()).isEqualTo(updateRequest.getPublisher());
    }

    @Test
    @DisplayName("아이디를 통해 책정보를 삭제할 수 있다.")
    void deleteById() throws Exception{
        // given
        long bookId = 1L;
        // when
        bookService.deleteBook(bookId);
        // then
        verify(bookRepository, times(1)).deleteById(bookId);
    }
}