package org.example.exampractice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.exampractice.dto.request.AuthorRequestDTO;
import org.example.exampractice.dto.request.BookRequestDTO;
import org.example.exampractice.dto.response.BookResponseDTO;
import org.example.exampractice.exception.book.BookNotFoundException;
import org.example.exampractice.mapper.AuthorMapper;
import org.example.exampractice.mapper.BookMapper;
import org.example.exampractice.model.Author;
import org.example.exampractice.model.Book;
import org.example.exampractice.repository.BookRepository;
import org.example.exampractice.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorMapper authorMapper;

    @Override
    public Optional<BookResponseDTO> createBook(BookRequestDTO bookRequestDTO) {
        Book book=bookMapper.bookRequestDTOToBook(bookRequestDTO);
        return Optional.of(bookMapper.bookToBookResponseDTO(bookRepository.save(book)));
    }

    @Override
    public Optional<BookResponseDTO> getBookById(Long id) {
        Optional<Book> foundbook= bookRepository.findById(id);
        if(foundbook.isPresent()){
            return Optional.of(bookMapper.bookToBookResponseDTO(foundbook.get()));
        }
        throw new BookNotFoundException("Book ID :"+id +"is not found.");
    }

    @Override
    public List<BookResponseDTO> getAllBooks() {
        List<Book> books=bookRepository.findAll();
        List<BookResponseDTO> bookResponseDTOS= new ArrayList<>();
        for(Book book:books){
            bookResponseDTOS.add(bookMapper.bookToBookResponseDTO(book));
        }
        return bookResponseDTOS;
    }

    @Override
    public Optional<BookResponseDTO> updateBook(Long id, BookRequestDTO bookRequestDTO) {
        Optional<Book> foundbook= bookRepository.findById(id);
        if(foundbook.isPresent()){
            Book updateBook=foundbook.get();
            List<Author> authors= new ArrayList<>();
            for(AuthorRequestDTO authorRequestDTO: bookRequestDTO.authorRequestDTOs()) {
                authors.add(authorMapper.authorRequestDTOToAuthor(authorRequestDTO));
            }
            updateBook.setAuthors(authors);
            updateBook.setTitle(bookRequestDTO.title());
            updateBook.setIsbn(bookRequestDTO.isbn());
            return Optional.of(bookMapper.bookToBookResponseDTO(bookRepository.save(updateBook)));
        }
        throw new BookNotFoundException("Book ID :"+id +"is not found.");
    }

    @Override
    public void deleteBook(Long id) {
        Optional<Book> foundbook= bookRepository.findById(id);
        if(foundbook.isPresent()){
            bookRepository.deleteById(id);
        }
        throw new BookNotFoundException("Book ID :"+id +"is not found.");
    }
}
