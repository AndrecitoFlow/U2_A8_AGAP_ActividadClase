package mx.edu.utez.U2_A8_AGAP.service;

import mx.edu.utez.U2_A8_AGAP.dto.UsuarioDto;
import mx.edu.utez.U2_A8_AGAP.exception.ResourceNotFoundException;
import mx.edu.utez.U2_A8_AGAP.model.Usuario;
import mx.edu.utez.U2_A8_AGAP.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con ID " + id + " no existe"));
    }

    public Usuario save(UsuarioDto dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setActivo(true);
        return usuarioRepository.save(usuario);
    }

    public Usuario desactivarUsuario(Long id) {
        Usuario usuario = findById(id);
        usuario.setActivo(false);
        return usuarioRepository.save(usuario);
    }
}