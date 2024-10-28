package com.fRemache.taller3.service;

import com.fRemache.taller3.model.Libro;
import com.fRemache.taller3.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;



@Service
public class LibroService {

    @Autowired
    private ILibroRepository ilibroRepository;

    public Libro createLibro(Libro libro) {
        return ilibroRepository.save(libro);
    }

    public Optional<Libro> getLibroById(Long id) {
        return ilibroRepository.findById(id);
    }

    public List<Libro> getAllLibros() {
        return ilibroRepository.findAll();
    }

    public ResponseEntity<Libro> actualizarLibro(Long id, Libro libroActualizado) {
        Optional<Libro> libro = ilibroRepository.findById(id);

        if (libro.isPresent()) {
            Libro libroExistente = libro.get();
            libroExistente.setTitulo(libroActualizado.getTitulo());
            libroExistente.setAutor(libroActualizado.getAutor());
            libroExistente.setIsbn(libroActualizado.getIsbn());
            libroExistente.setDisponible(libroActualizado.isDisponible());
            ilibroRepository.save(libroExistente);
            return ResponseEntity.ok(libroExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public void deleteLibro(Long id) {
        ilibroRepository.deleteById(id);
    }
}
