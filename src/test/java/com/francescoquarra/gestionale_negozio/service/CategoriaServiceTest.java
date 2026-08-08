package com.francescoquarra.gestionale_negozio.service;

import com.francescoquarra.gestionale_negozio.entity.Categoria;
import com.francescoquarra.gestionale_negozio.exception.DuplicateResourceException;
import com.francescoquarra.gestionale_negozio.exception.ResourceNotFoundException;
import com.francescoquarra.gestionale_negozio.repository.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    @Test
    void findById_restituisceLaCategoriaSeEsiste() {
        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNome("Alimentari");

        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));

        Categoria risultato = categoriaService.findById(1L);

        assertEquals("Alimentari", risultato.getNome());
    }

    @Test
    void findById_lanciaEccezioneSeNonTrovata() {
        when(categoriaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> categoriaService.findById(99L));
    }

    @Test
    void create_lanciaEccezioneSeNomeGiaEsistente() {
        Categoria nuova = new Categoria();
        nuova.setNome("Alimentari");

        when(categoriaRepository.existsByNome("Alimentari")).thenReturn(true);

        assertThrows(DuplicateResourceException.class, () -> categoriaService.create(nuova));

        // Verifica che, in caso di errore, save() non venga MAI chiamato
        verify(categoriaRepository, never()).save(any(Categoria.class));
    }

    @Test
    void create_salvaLaCategoriaSeNomeNonEsistente() {
        Categoria nuova = new Categoria();
        nuova.setNome("Igiene personale");

        when(categoriaRepository.existsByNome("Igiene personale")).thenReturn(false);
        when(categoriaRepository.save(nuova)).thenReturn(nuova);

        Categoria risultato = categoriaService.create(nuova);

        assertEquals("Igiene personale", risultato.getNome());
        verify(categoriaRepository).save(nuova);
    }
}
