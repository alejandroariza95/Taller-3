package com.fRemache.taller3.service;

import com.fRemache.taller3.model.Libro;
import com.fRemache.taller3.model.Prestamo;
import com.fRemache.taller3.model.Usuario;
import com.fRemache.taller3.repository.ILibroRepository;
import com.fRemache.taller3.repository.IPrestamoRepository;
import com.fRemache.taller3.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {
    @Autowired
    private IPrestamoRepository prestamoRepository;

    @Autowired
    private ILibroRepository libroRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    public Prestamo guardarPrestamo(Prestamo prestamo) {

        Libro libro = libroRepository.findById(prestamo.getLibro().getId_libro())
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        Usuario usuario = usuarioRepository.findById(prestamo.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        prestamo.setLibro(libro);
        prestamo.setUsuario(usuario);

        return prestamoRepository.save(prestamo);
    }

    public List<Prestamo> getAllPrestamos() {
        return prestamoRepository.findAll();
    }

    public Optional<Prestamo> getPrestamoById(Long id) {
        return prestamoRepository.findById(id);
    }

    public void deletePrestamo(Long id) {
        prestamoRepository.deleteById(id);
    }

    public ResponseEntity<Prestamo> actualizarPrestamo(Long id, Prestamo prestamoActualizado) {
        Optional<Prestamo> prestamo = prestamoRepository.findById(id);

        if (prestamo.isPresent()) {
            Prestamo prestamoExistente = prestamo.get();

            Libro libro = libroRepository.findById(prestamoActualizado.getLibro().getId())
                    .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

            Usuario usuario = usuarioRepository.findById(prestamoActualizado.getUsuario().getId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            prestamoExistente.setFechaPrestamo(prestamoActualizado.getFechaPrestamo());
            prestamoExistente.setFechaDevolucion(prestamoActualizado.getFechaDevolucion());
            prestamoExistente.setLibro(libro);
            prestamoExistente.setUsuario(usuario);

            prestamoRepository.save(prestamoExistente);
            return ResponseEntity.ok(prestamoExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
