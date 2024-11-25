package org.example.exampractice.dto.request;

import java.util.List;

public record BookRequestDTO(
         String title,

         String isbn,

         List<AuthorRequestDTO> authorRequestDTOs
) {
}
