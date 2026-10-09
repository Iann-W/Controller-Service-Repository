package com.example.ianwaldo.service;

import com.example.ianwaldo.model.Curso;
import com.example.ianwaldo.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> findById(Long id) {
        return cursoRepository.findById(id);
    }

    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    public boolean delete(Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}