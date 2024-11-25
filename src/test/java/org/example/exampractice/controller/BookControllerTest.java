package org.example.exampractice.controller;

import org.example.exampractice.dto.request.AuthorRequestDTO;
import org.example.exampractice.dto.request.BookRequestDTO;
import org.example.exampractice.dto.response.AuthorResponseDTO;
import org.example.exampractice.dto.response.BookResponseDTO;
import org.example.exampractice.exception.book.BookNotFoundException;
import org.example.exampractice.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @InjectMocks
    private BookController bookController;
    @Mock
    private BookService bookService;

    //<<<<<<<Valid(Happy Tests)>>>>>>>
    @Test
    void getAllBooks_success_returnsBookList() {
        //prepare data
        List<BookResponseDTO> bookResponseDTOS = new ArrayList<>();
        List<AuthorResponseDTO> authorsForBook1 = List.of(new AuthorResponseDTO("Author A"));
        List<AuthorResponseDTO> authorsForBook2 = List.of(new AuthorResponseDTO("Author B"));
        bookResponseDTOS.add(new BookResponseDTO(1L,"Science Title", "BB122e", authorsForBook1));
        bookResponseDTOS.add(new BookResponseDTO(2L,"Math Title", "BB123f", authorsForBook2));

        // Mock the service method
        Mockito.when(bookService.getAllBooks()).thenReturn(bookResponseDTOS);

        // Inject mock to controller
        ResponseEntity<List<BookResponseDTO>> responseEntity = bookController.getAllBooks();

        // Assert: Verify the response
        assert responseEntity.getStatusCode() == HttpStatus.OK;
        assert responseEntity.getBody() != null;
        assert responseEntity.getBody().size() == bookResponseDTOS.size();
        assert responseEntity.getBody().equals(bookResponseDTOS);
    }


    @Test
    void getBookById_success_returnsBook() {
        //prepare data
        List<AuthorResponseDTO> authorsForBook1 = List.of(
                new AuthorResponseDTO("Author A"),
                new AuthorResponseDTO("Author B")
        );
        BookResponseDTO bookResponseDTO= new BookResponseDTO(1L,"Science Title", "BB122e", authorsForBook1);
        Long id = 1L;

        // Mock the service method
        Mockito.when(bookService.getBookById(id)).thenReturn(Optional.of(bookResponseDTO));

        // Inject mock to controller
        ResponseEntity<BookResponseDTO> responseEntity = bookController.getBookById(id);

        // Assert: Verify the response
        assert responseEntity.getStatusCode() == HttpStatus.OK;
        assert responseEntity.getBody() != null;
        assert responseEntity.getBody().equals(bookResponseDTO);
    }


    @Test
    void createBook_success_returnsBook() {
        //prepare data
        List<AuthorResponseDTO> authorsForBook1 = List.of(
                new AuthorResponseDTO("Author A"),
                new AuthorResponseDTO("Author B")
        );
        List<AuthorRequestDTO> authorRequestDTOS = List.of(
                new AuthorRequestDTO("Author A"),
                new AuthorRequestDTO("Author B")
        );
        BookResponseDTO bookResponseDTO= new BookResponseDTO(1L,"Science Title", "BB122e", authorsForBook1);
        BookRequestDTO bookRequestDTO= new BookRequestDTO("Science Title", "BB122e", authorRequestDTOS);

        // Mock the service method
        Mockito.when(bookService.createBook(bookRequestDTO)).thenReturn(Optional.of(bookResponseDTO));

        // Inject mock to controller
        ResponseEntity<BookResponseDTO> responseEntity = bookController.createBook(bookRequestDTO);

        // Assert: Verify the response
        assert responseEntity.getStatusCode() == HttpStatus.CREATED;
        assert responseEntity.getBody() != null;
        assert responseEntity.getBody().equals(bookResponseDTO);
    }

    @Test
    void updateBook_success_returnsBook() {
        //prepare data
        List<AuthorResponseDTO> authorsForBook1 = List.of(
                new AuthorResponseDTO("Author A"),
                new AuthorResponseDTO("Author B")
        );
        List<AuthorRequestDTO> authorRequestDTOS = List.of(
                new AuthorRequestDTO("Author A"),
                new AuthorRequestDTO("Author B")
        );
        BookResponseDTO bookResponseDTO= new BookResponseDTO(1L,"Science Title", "BB122e", authorsForBook1);
        BookRequestDTO bookRequestDTO= new BookRequestDTO("Science Title", "BB122e", authorRequestDTOS);
        Long id = 4L;

        // Mock the service method
        Mockito.when(bookService.updateBook(id, bookRequestDTO)).thenReturn(Optional.of(bookResponseDTO));

        // Inject mock to controller
       ResponseEntity<BookResponseDTO> responseEntity=bookController.updateBookById(id, bookRequestDTO);

        // Assert: Verify the response
        assert responseEntity.getStatusCode() == HttpStatus.OK;
        assert responseEntity.getBody() != null;
        assert responseEntity.getBody().equals(bookResponseDTO);
    }

    @Test
    void deleteBookById() {
        //prepare data
        Long id = 1L;

        // Mock the service method
        Mockito.doNothing().when(bookService).deleteBook(id);

        // Inject mock to controller
        ResponseEntity<BookResponseDTO> responseEntity = bookController.deleteBookById(id);

        // Assert: Verify the response
        assert responseEntity.getStatusCode() == HttpStatus.NO_CONTENT;
        assert responseEntity.getBody() == null;
    }


    //<<<<<<<Invalid(UnHappy Tests)>>>>>>>

    @Test
    void updateBookById_invalidInput_returnsBook() {
        //prepare data
        List<AuthorRequestDTO> authorRequestDTOS = List.of(
                new AuthorRequestDTO("Author A"),
                new AuthorRequestDTO("Author B")
        );
        Long id = 4L;
        BookRequestDTO bookRequestDTO = new BookRequestDTO("Science Title", "BB122e", authorRequestDTOS);

        // Mock the service method
        Mockito.when(bookService.updateBook(id, bookRequestDTO))
                .thenThrow(new BookNotFoundException("Book ID: " + id + " is not found."));

        // Inject mock to controller
        BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> {
            bookController.updateBookById(id, bookRequestDTO);
        });
    }
}