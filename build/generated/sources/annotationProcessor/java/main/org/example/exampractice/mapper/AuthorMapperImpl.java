package org.example.exampractice.mapper;

import javax.annotation.processing.Generated;
import org.example.exampractice.dto.request.AuthorRequestDTO;
import org.example.exampractice.dto.response.AuthorResponseDTO;
import org.example.exampractice.model.Author;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-23T20:30:28-0600",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.11.1.jar, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public Author authorRequestDTOToAuthor(AuthorRequestDTO authorRequestDTO) {
        if ( authorRequestDTO == null ) {
            return null;
        }

        Author author = new Author();

        author.setName( authorRequestDTO.name() );

        return author;
    }

    @Override
    public AuthorResponseDTO authorToAuthorResponseDTO(Author author) {
        if ( author == null ) {
            return null;
        }

        String name = null;

        name = author.getName();

        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO( name );

        return authorResponseDTO;
    }
}
