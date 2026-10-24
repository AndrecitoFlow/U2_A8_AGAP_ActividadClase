package mx.edu.utez.U2_A8_AGAP.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import mx.edu.utez.U2_A8_AGAP.enums.PrioridadTicket;

@Data
public class TicketDto {

    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;

    @NotNull(message = "La prioridad es obligatoria (BAJA, MEDIA, ALTA)")
    private PrioridadTicket prioridad;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;
}