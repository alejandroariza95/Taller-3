package com.fRemache.taller3.service;

import com.fRemache.taller3.model.Persona;
import com.fRemache.taller3.repository.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private IPersonaRepository ipersonaRepository;

    public Persona createPersona(Persona persona) {
        return ipersonaRepository.save(persona);
    }

    public Optional<Persona> getPersonById(Long id) {
        return ipersonaRepository.findById(id);
    }

    public List<Persona> getAllPersonas() {
        return ipersonaRepository.findAll();
    }

    public ResponseEntity<Persona> actualizarPersona(Long id, Persona personaActualizada) {
        Optional<Persona> persona = ipersonaRepository.findById(id);

        if (persona.isPresent()) {
            Persona personaExistente = persona.get();
            personaExistente.setNombre(personaActualizada.getNombre());
            personaExistente.setApellido(personaActualizada.getApellido());
            personaExistente.setTipo(personaActualizada.getTipo());
            ipersonaRepository.save(personaExistente);
            return ResponseEntity.ok(personaExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public void deletePersona(Long id) {
        ipersonaRepository.deleteById(id);
    }


}
