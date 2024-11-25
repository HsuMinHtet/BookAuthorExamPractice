package org.example.exampractice.controller;

import com.google.gson.Gson;
import org.example.exampractice.dto.request.AuthorRequestDTO;
import org.example.exampractice.dto.response.AuthorResponseDTO;
import org.example.exampractice.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @Test
    void getAllAuthors() throws Exception {
        //prepare data
        List<AuthorResponseDTO> authorResponseDTOS = new ArrayList<>();
        authorResponseDTOS.add( new AuthorResponseDTO("Author A"));
        authorResponseDTOS.add( new AuthorResponseDTO("Author B"));

        // Mock the service method
        Mockito.when(authorService.getAllAuthors()).thenReturn(authorResponseDTOS);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/author")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(MockMvcResultMatchers.content().json(new Gson().toJson(authorResponseDTOS))
                );
    }

    @Test
    void getAuthorById() throws Exception {
        Long id= 1L;
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO("Author A");
        Mockito.when(authorService.getAuthorById(id)).thenReturn(Optional.of(authorResponseDTO));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/author/"+ id)
                ).andDo(MockMvcResultHandlers.print())
                .andExpectAll(MockMvcResultMatchers.content().json(new Gson().toJson(authorResponseDTO)));
    }

    @Test
    void createAuthor() throws Exception {
        AuthorRequestDTO authorRequestDTO = new AuthorRequestDTO("Author A");
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO("Author A");

        Mockito.when(authorService.createAuthor(authorRequestDTO)).thenReturn(Optional.of(authorResponseDTO));
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/author")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new Gson().toJson(authorRequestDTO))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void updateAuthor() throws Exception {
        AuthorRequestDTO authorRequestDTO= new AuthorRequestDTO("Author A");
        Long id= 1L;
        AuthorResponseDTO authorResponseDTO= new AuthorResponseDTO("Author A");

        Mockito.when(authorService.updateAuthor(id,authorRequestDTO)).thenReturn(Optional.of(authorResponseDTO));

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/author/"+ id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new Gson().toJson(authorRequestDTO))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(MockMvcResultMatchers.content().json(new Gson().toJson(authorResponseDTO))
                );
    }

    @Test
    void deleteBookById() throws Exception {
        Long id= 1L;
        Mockito.doNothing().when(authorService).deleteAuthor(id);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/author/"+ id)
        ).andDo(MockMvcResultHandlers.print())
                .andExpectAll(MockMvcResultMatchers.status().isNoContent());
    }
}