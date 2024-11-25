package org.example.exampractice.service;

import org.example.exampractice.dto.request.AuthorRequestDTO;
import org.example.exampractice.dto.response.AuthorResponseDTO;
import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<AuthorResponseDTO> createAuthor(AuthorRequestDTO authorRequestDTO);
    Optional<AuthorResponseDTO> getAuthorById(Long id);
    List<AuthorResponseDTO> getAllAuthors();
    Optional<AuthorResponseDTO> updateAuthor(Long id, AuthorRequestDTO authorRequestDTO);
    void deleteAuthor(Long id);
}
