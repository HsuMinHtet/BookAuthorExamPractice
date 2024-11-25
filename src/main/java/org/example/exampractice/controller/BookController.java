package org.example.exampractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.exampractice.dto.request.BookRequestDTO;
import org.example.exampractice.dto.response.BookResponseDTO;
import org.example.exampractice.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        List<BookResponseDTO> bookResponseDTOS = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDTOS);
    }

    @GetMapping("/{id}")
    ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id) {
        Optional<BookResponseDTO> bookResponseDTO=bookService.getBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDTO.get());
    }

    @PostMapping
    ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRequestDTO bookRequestDTO) {
        Optional<BookResponseDTO> bookResponseDTO=bookService.createBook(bookRequestDTO);
        if(bookResponseDTO.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(bookResponseDTO.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PutMapping("/{id}")
    ResponseEntity<BookResponseDTO> updateBookById(@PathVariable Long id, @RequestBody BookRequestDTO bookRequestDTO) {
        Optional<BookResponseDTO> bookResponseDTO = bookService.updateBook(id, bookRequestDTO);
        return bookResponseDTO
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)); // Fallback, although exception should handle this
    }


    @DeleteMapping("/{id}")
    ResponseEntity<BookResponseDTO> deleteBookById(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
