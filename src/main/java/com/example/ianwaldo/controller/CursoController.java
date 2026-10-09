package com.example.ianwaldo.controller;

import com.example.ianwaldo.model.Curso;
import com.example.ianwaldo.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<Curso> getAll() {
        return cursoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getById(@PathVariable Long id) {
        return cursoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Curso> create(@RequestBody Curso curso) {
        Curso nuevo = cursoService.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable Long id, @RequestBody Curso cursoDetails) {
        return cursoService.findById(id).map(curso -> {
            curso.setNombre(cursoDetails.getNombre());
            curso.setDescripcion(cursoDetails.getDescripcion());
            curso.setHoras(cursoDetails.getHoras());
            Curso actualizado = cursoService.save(curso);
            return ResponseEntity.ok(actualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (cursoService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}