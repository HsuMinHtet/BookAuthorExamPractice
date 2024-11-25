package org.example.exampractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.exampractice.dto.request.AuthorRequestDTO;
import org.example.exampractice.dto.response.AuthorResponseDTO;
import org.example.exampractice.dto.response.BookResponseDTO;
import org.example.exampractice.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    ResponseEntity<List<AuthorResponseDTO>> getAllAuthors() {
        List<AuthorResponseDTO> authorResponseDTOS = authorService.getAllAuthors();
        return ResponseEntity.status(HttpStatus.OK).body(authorResponseDTOS);
    }

    @GetMapping("/{id}")
    ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable Long id) {
        Optional<AuthorResponseDTO> authorResponseDTO=authorService.getAuthorById(id);
        return ResponseEntity.status(HttpStatus.OK).body(authorResponseDTO.get());
    }

    @PostMapping
    ResponseEntity<AuthorResponseDTO> createAuthor(@RequestBody AuthorRequestDTO authorRequestDTO) {
        Optional<AuthorResponseDTO> authorResponseDTO=authorService.createAuthor(authorRequestDTO);
        if(authorResponseDTO.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(authorResponseDTO.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PutMapping("/{id}")
    ResponseEntity<AuthorResponseDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorRequestDTO authorRequestDTO) {
        Optional<AuthorResponseDTO> authorResponseDTO= authorService.updateAuthor(id, authorRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(authorResponseDTO.get());
    }
    @DeleteMapping("/{id}")
    ResponseEntity<BookResponseDTO> deleteBookById(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
