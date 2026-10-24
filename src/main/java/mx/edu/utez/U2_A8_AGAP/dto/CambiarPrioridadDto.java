package mx.edu.utez.U2_A8_AGAP.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import mx.edu.utez.U2_A8_AGAP.enums.PrioridadTicket;

@Data
public class CambiarPrioridadDto {

    @NotNull(message = "La nueva prioridad es obligatoria")
    private PrioridadTicket prioridad;
}