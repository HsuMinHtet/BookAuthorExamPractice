package org.example.exampractice;

import lombok.RequiredArgsConstructor;
import org.example.exampractice.dto.request.AuthorRequestDTO;
import org.example.exampractice.dto.request.BookRequestDTO;
import org.example.exampractice.service.AuthorService;
import org.example.exampractice.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class ExamPracticeApplication {

    private final AuthorService authorService;
    private final BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(ExamPracticeApplication.class, args);
    }
    @Bean
    CommandLineRunner runner(){
        return args -> {
            AuthorRequestDTO authorRequestDTO = new AuthorRequestDTO("James Gosling"); // Create the Author
            authorService.createAuthor(authorRequestDTO); // Save the Author

            BookRequestDTO bookRequestDTO = new BookRequestDTO("Java Programming", "9780134685991", List.of(authorRequestDTO)); // Create the Book
            bookService.createBook(bookRequestDTO); // Save the Book

        };
    }

}
