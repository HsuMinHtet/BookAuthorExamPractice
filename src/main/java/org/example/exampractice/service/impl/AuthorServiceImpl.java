package org.example.exampractice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.exampractice.dto.request.AuthorRequestDTO;
import org.example.exampractice.dto.response.AuthorResponseDTO;
import org.example.exampractice.exception.author.AuthorNotFoundException;
import org.example.exampractice.mapper.AuthorMapper;
import org.example.exampractice.model.Author;
import org.example.exampractice.repository.AuthorRepository;
import org.example.exampractice.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public Optional<AuthorResponseDTO> createAuthor(AuthorRequestDTO authorRequestDTO) {
        Author saveAuthor= authorMapper.authorRequestDTOToAuthor(authorRequestDTO);
        return Optional.of(authorMapper.authorToAuthorResponseDTO(authorRepository.save(saveAuthor)));
    }

    @Override
    public Optional<AuthorResponseDTO> getAuthorById(Long id) {
        Optional<Author> foundAuthor = authorRepository.findById(id);
        if(foundAuthor.isPresent()){
            return Optional.of(authorMapper.authorToAuthorResponseDTO(foundAuthor.get()));
        }
        throw new AuthorNotFoundException("Author: "+id +"is not found.");
    }

    @Override
    public List<AuthorResponseDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorResponseDTO> authorResponseDTOS = new ArrayList<>();
        for(Author author : authors){
            authorResponseDTOS.add(authorMapper.authorToAuthorResponseDTO(author));
        }
        return authorResponseDTOS;
    }

    @Override
    public Optional<AuthorResponseDTO> updateAuthor(Long id, AuthorRequestDTO authorRequestDTO) {
        Optional<Author> foundAuthor = authorRepository.findById(id);
        if(foundAuthor.isPresent()){
            Author updateAuthor= foundAuthor.get();
            updateAuthor.setName(authorRequestDTO.name());
            return Optional.of(authorMapper.authorToAuthorResponseDTO(authorRepository.save(updateAuthor)));
        }
        throw new AuthorNotFoundException("Author: "+id +"is not found.");
    }

    @Override
    public void deleteAuthor(Long id) {
        Optional<Author> foundAuthor = authorRepository.findById(id);
        if(foundAuthor.isPresent()){
            authorRepository.deleteById(id);
        }
        throw new AuthorNotFoundException("Author: "+id +"is not found.");
    }
}
