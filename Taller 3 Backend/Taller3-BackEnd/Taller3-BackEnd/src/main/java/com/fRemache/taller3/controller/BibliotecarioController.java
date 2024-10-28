package com.fRemache.taller3.controller;

import com.fRemache.taller3.model.Bibliotecario;
import com.fRemache.taller3.service.BibliotecarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bibliotecarios")
public class BibliotecarioController {
    @Autowired
    private BibliotecarioService bibliotecarioService;

    @PostMapping
    public Bibliotecario newBibliotecario(@RequestBody Bibliotecario bibliotecario) {
        return bibliotecarioService.saveBibliotecario(bibliotecario);
    }

    @GetMapping
    public List<Bibliotecario> getBibliotecarios() {
        return bibliotecarioService.getAllBibliotecarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bibliotecario> getBibliotecarioPorId(@PathVariable Long id) {
        Optional<Bibliotecario> bibliotecario = bibliotecarioService.getBibliotecarioById(id);
        return bibliotecario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteBibliotecario(@PathVariable Long id) {
        bibliotecarioService.deleteBibliotecario(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bibliotecario> updateBibliotecario(@PathVariable Long id, @RequestBody Bibliotecario bibliotecarioActualizado) {
        return bibliotecarioService.updateBibliotecario(id, bibliotecarioActualizado);
    }
}