package com.example.demo.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.Usuario;
import com.example.demo.repositorio.UsuarioRepository;

@RestController
@RequestMapping("/usuarios") // Ruta base para las operaciones relacionadas con usuarios
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Crear un nuevo usuario
    @PostMapping("/")
    public void crearUsuario(@RequestBody Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    // Actualizar un usuario por su ID
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        usuario.setId(id);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }

    // Borrar un usuario por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarUsuario(@PathVariable Integer id) {
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Consultar todos los usuarios
    @GetMapping("/")
    public List<Usuario> darUsuarios() {
        return usuarioRepository.findAll();
    }

    // Consultar un usuario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> darUsuario(@PathVariable Integer id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Consultar usuarios por tipo
    @GetMapping("/consultarPorTipo")
    public Collection<Usuario> consultarUsuariosPorTipo(@RequestParam("tipo") String tipo) {
        return usuarioRepository.consultarUsuariosPorTipo(tipo);
    }
}
