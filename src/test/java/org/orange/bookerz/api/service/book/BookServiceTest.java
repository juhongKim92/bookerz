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
import org.orange.bookerz.api.exception.AlreadyExistException;
import org.orange.bookerz.domain.book.Book;
import org.orange.bookerz.domain.book.BookRepository;

import java.rmi.AlreadyBoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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


}