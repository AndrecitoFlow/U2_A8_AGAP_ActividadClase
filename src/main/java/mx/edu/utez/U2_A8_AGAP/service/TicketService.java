package mx.edu.utez.U2_A8_AGAP.service;

import mx.edu.utez.U2_A8_AGAP.dto.TicketDto;
import mx.edu.utez.U2_A8_AGAP.enums.EstadoTicket;
import mx.edu.utez.U2_A8_AGAP.enums.PrioridadTicket;
import mx.edu.utez.U2_A8_AGAP.exception.ResourceNotFoundException;
import mx.edu.utez.U2_A8_AGAP.model.Ticket;
import mx.edu.utez.U2_A8_AGAP.model.Usuario;
import mx.edu.utez.U2_A8_AGAP.repository.TicketRepository;
import mx.edu.utez.U2_A8_AGAP.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Ticket crearTicket(TicketDto dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con ID " + dto.getUsuarioId() + " no existe"));

        if (!Boolean.TRUE.equals(usuario.getActivo())) {
            throw new IllegalArgumentException("No se puede crear el ticket: El usuario está inactivo");
        }

        Ticket ticket = new Ticket();
        ticket.setDescripcion(dto.getDescripcion());
        ticket.setEstado(EstadoTicket.ABIERTO);
        ticket.setPrioridad(dto.getPrioridad());
        ticket.setUsuario(usuario);

        return ticketRepository.save(ticket);
    }

    public Ticket actualizarEstado(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El ticket con ID " + id + " no existe"));

        if (ticket.getEstado() == EstadoTicket.ABIERTO) {
            ticket.setEstado(EstadoTicket.EN_PROCESO);
        } else if (ticket.getEstado() == EstadoTicket.EN_PROCESO) {
            ticket.setEstado(EstadoTicket.CERRADO);
        } else if (ticket.getEstado() == EstadoTicket.CERRADO) {
            throw new IllegalArgumentException("El ticket ya está CERRADO y no se puede modificar");
        }

        return ticketRepository.save(ticket);
    }

    public Ticket reasignarPrioridad(Long id, PrioridadTicket nuevaPrioridad) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El ticket con ID " + id + " no existe"));

        if (ticket.getEstado() == EstadoTicket.CERRADO) {
            throw new IllegalArgumentException("No se puede reasignar la prioridad: El ticket está CERRADO");
        }

        ticket.setPrioridad(nuevaPrioridad);
        return ticketRepository.save(ticket);
    }

    public List<Ticket> obtenerTicketsUsuario(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ResourceNotFoundException("El usuario con ID " + usuarioId + " no existe");
        }
        return ticketRepository.findByUsuarioId(usuarioId);
    }
}