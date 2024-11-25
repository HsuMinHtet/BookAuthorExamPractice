package org.example.exampractice.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.example.exampractice.dto.request.AuthorRequestDTO;
import org.example.exampractice.dto.request.BookRequestDTO;
import org.example.exampractice.dto.response.AuthorResponseDTO;
import org.example.exampractice.dto.response.BookResponseDTO;
import org.example.exampractice.model.Author;
import org.example.exampractice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-23T20:30:28-0600",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.11.1.jar, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public Book bookRequestDTOToBook(BookRequestDTO bookRequestDTO) {
        if ( bookRequestDTO == null ) {
            return null;
        }

        Book book = new Book();

        book.setAuthors( authorRequestDTOListToAuthorList( bookRequestDTO.authorRequestDTOs() ) );
        book.setTitle( bookRequestDTO.title() );
        book.setIsbn( bookRequestDTO.isbn() );

        return book;
    }

    @Override
    public BookResponseDTO bookToBookResponseDTO(Book book) {
        if ( book == null ) {
            return null;
        }

        List<AuthorResponseDTO> authorResponseDTOs = null;
        Long id = null;
        String title = null;
        String isbn = null;

        authorResponseDTOs = authorListToAuthorResponseDTOList( book.getAuthors() );
        id = book.getId();
        title = book.getTitle();
        isbn = book.getIsbn();

        BookResponseDTO bookResponseDTO = new BookResponseDTO( id, title, isbn, authorResponseDTOs );

        return bookResponseDTO;
    }

    protected List<Author> authorRequestDTOListToAuthorList(List<AuthorRequestDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Author> list1 = new ArrayList<Author>( list.size() );
        for ( AuthorRequestDTO authorRequestDTO : list ) {
            list1.add( authorMapper.authorRequestDTOToAuthor( authorRequestDTO ) );
        }

        return list1;
    }

    protected List<AuthorResponseDTO> authorListToAuthorResponseDTOList(List<Author> list) {
        if ( list == null ) {
            return null;
        }

        List<AuthorResponseDTO> list1 = new ArrayList<AuthorResponseDTO>( list.size() );
        for ( Author author : list ) {
            list1.add( authorMapper.authorToAuthorResponseDTO( author ) );
        }

        return list1;
    }
}
