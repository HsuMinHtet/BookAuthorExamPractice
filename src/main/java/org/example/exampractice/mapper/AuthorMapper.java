package org.example.exampractice.mapper;

import org.example.exampractice.dto.request.AuthorRequestDTO;
import org.example.exampractice.dto.response.AuthorResponseDTO;
import org.example.exampractice.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author authorRequestDTOToAuthor(AuthorRequestDTO authorRequestDTO);
    AuthorResponseDTO authorToAuthorResponseDTO(Author author);
}
