package org.example.exampractice.mapper;

import org.example.exampractice.dto.request.BookRequestDTO;
import org.example.exampractice.dto.response.BookResponseDTO;
import org.example.exampractice.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class})
public interface BookMapper {
    @Mapping(source = "bookRequestDTO.authorRequestDTOs",target = "authors")
    Book bookRequestDTOToBook(BookRequestDTO bookRequestDTO);
    @Mapping(source = "book.authors", target = "authorResponseDTOs")
    BookResponseDTO bookToBookResponseDTO(Book book);
}
