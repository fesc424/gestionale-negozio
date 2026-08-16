package com.francescoquarra.gestionale_negozio.controller;

import tools.jackson.databind.ObjectMapper;
import com.francescoquarra.gestionale_negozio.dto.CategoriaRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CategoriaControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void creaCategoria_restituisce201ELaCategoriaCreata() throws Exception {
        CategoriaRequest request = new CategoriaRequest();
        request.setNome("Categoria di test " + System.currentTimeMillis()); // nome unico per evitare conflitti tra run

        mockMvc.perform(post("/api/categorie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value(request.getNome()));
    }

    @Test
    void creaCategoria_restituisce400SeNomeVuoto() throws Exception {
        CategoriaRequest request = new CategoriaRequest();
        request.setNome("");

        mockMvc.perform(post("/api/categorie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors.nome").exists());
    }

    @Test
    void getCategoriaInesistente_restituisce404() throws Exception {
        mockMvc.perform(get("/api/categorie/999999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void getTutteLeCategorie_restituisce200EUnaLista() throws Exception {
        mockMvc.perform(get("/api/categorie"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}