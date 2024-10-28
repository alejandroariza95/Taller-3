package com.fRemache.taller3.service;

import com.fRemache.taller3.model.Usuario;
import com.fRemache.taller3.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository iusuarioRepository;

    public Usuario createUsuario(Usuario usuario) {
        return iusuarioRepository.save(usuario);
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return iusuarioRepository.findById(id);
    }

    public List<Usuario> getAllUsuario() {
        return iusuarioRepository.findAll();
    }

    public ResponseEntity<Usuario> updateUsuario(Long id, Usuario usuarioActualizado) {
        Optional<Usuario> usuario = iusuarioRepository.findById(id);

        if (usuario.isPresent()) {
            Usuario usuarioExistente = usuario.get();
            usuarioExistente.setNombre(usuarioActualizado.getNombre());
            usuarioExistente.setApellido(usuarioActualizado.getApellido());
            usuarioExistente.setPrestamos(usuarioActualizado.getPrestamos());
            iusuarioRepository.save(usuarioExistente);
            return ResponseEntity.ok(usuarioExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public void deleteUsuario(Long id) {
        iusuarioRepository.deleteById(id);
    }
}
