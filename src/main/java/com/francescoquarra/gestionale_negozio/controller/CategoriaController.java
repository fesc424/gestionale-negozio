package com.francescoquarra.gestionale_negozio.controller;

import com.francescoquarra.gestionale_negozio.dto.CategoriaRequest;
import com.francescoquarra.gestionale_negozio.dto.CategoriaResponse;
import com.francescoquarra.gestionale_negozio.entity.Categoria;
import com.francescoquarra.gestionale_negozio.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorie")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<CategoriaResponse> getAll() {
        return categoriaService.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> getById(@PathVariable Long id) {
        Categoria categoria = categoriaService.findById(id);
        return ResponseEntity.ok(toResponse(categoria));
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> create(@RequestBody CategoriaRequest request) {
        Categoria categoria = new Categoria();
        categoria.setNome(request.getNome());

        Categoria salvata = categoriaService.create(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(salvata));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private CategoriaResponse toResponse(Categoria categoria) {
        return new CategoriaResponse(categoria.getId(), categoria.getNome());
    }
}