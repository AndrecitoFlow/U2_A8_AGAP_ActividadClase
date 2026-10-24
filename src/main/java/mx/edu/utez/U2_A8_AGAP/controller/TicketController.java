package mx.edu.utez.U2_A8_AGAP.controller;

import jakarta.validation.Valid;
import mx.edu.utez.U2_A8_AGAP.dto.CambiarPrioridadDto;
import mx.edu.utez.U2_A8_AGAP.dto.TicketDto;
import mx.edu.utez.U2_A8_AGAP.model.Ticket;
import mx.edu.utez.U2_A8_AGAP.service.TicketService;
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
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<Ticket> crearTicket(@Valid @RequestBody TicketDto dto) {
        return new ResponseEntity<>(ticketService.crearTicket(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/siguiente-estado")
    public ResponseEntity<Ticket> actualizarEstado(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.actualizarEstado(id));
    }

    @PatchMapping("/{id}/prioridad")
    public ResponseEntity<Ticket> reasignarPrioridad(@PathVariable Long id, @Valid @RequestBody CambiarPrioridadDto dto) {
        return ResponseEntity.ok(ticketService.reasignarPrioridad(id, dto.getPrioridad()));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Ticket>> obtenerTicketsUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(ticketService.obtenerTicketsUsuario(usuarioId));
    }
}