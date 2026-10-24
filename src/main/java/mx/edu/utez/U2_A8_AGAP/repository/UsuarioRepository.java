package mx.edu.utez.U2_A8_AGAP.repository;

import mx.edu.utez.U2_A8_AGAP.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}