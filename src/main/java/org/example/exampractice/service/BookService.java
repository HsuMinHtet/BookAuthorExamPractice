package org.example.exampractice.service;

import org.example.exampractice.dto.request.BookRequestDTO;
import org.example.exampractice.dto.response.BookResponseDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<BookResponseDTO> createBook(BookRequestDTO bookRequestDTO);
    Optional<BookResponseDTO> getBookById(Long id);
    List<BookResponseDTO> getAllBooks();
    Optional<BookResponseDTO> updateBook(Long id, BookRequestDTO bookRequestDTO);
    void deleteBook(Long id);
}
