package mx.edu.utez.U2_A8_AGAP.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioDto {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
}