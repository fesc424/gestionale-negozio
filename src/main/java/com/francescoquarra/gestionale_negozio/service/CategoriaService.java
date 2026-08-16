package com.francescoquarra.gestionale_negozio.service;

import com.francescoquarra.gestionale_negozio.entity.Categoria;
import com.francescoquarra.gestionale_negozio.exception.DuplicateResourceException;
import com.francescoquarra.gestionale_negozio.exception.ResourceNotFoundException;
import com.francescoquarra.gestionale_negozio.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria non trovata con id: " + id));
    }

    public Categoria create(Categoria categoria) {
        if (categoriaRepository.existsByNome(categoria.getNome())) {
            throw new DuplicateResourceException("Esiste già una categoria con questo nome: " + categoria.getNome());
        }
        return categoriaRepository.save(categoria);
    }

    public void deleteById(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria non trovata con id: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}