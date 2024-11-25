package org.example.exampractice.dto.response;

import org.example.exampractice.dto.request.AuthorRequestDTO;

import java.util.List;

public record BookResponseDTO(
        Long id,

        String title,

        String isbn,

        List<AuthorResponseDTO> authorResponseDTOs
) {
}
