package mx.edu.utez.U2_A8_AGAP.controller;

import jakarta.validation.Valid;
import mx.edu.utez.U2_A8_AGAP.dto.UsuarioDto;
import mx.edu.utez.U2_A8_AGAP.model.Usuario;
import mx.edu.utez.U2_A8_AGAP.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@Valid @RequestBody UsuarioDto dto) {
        return new ResponseEntity<>(usuarioService.save(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Usuario> desactivarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.desactivarUsuario(id));
    }
}