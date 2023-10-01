package com.alura.foro.api.domain.respuesta;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(Long id, String mensaje, Long idTopico, LocalDateTime fechaCreacion, Long idAutor) {
    public DatosDetalleRespuesta(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getTopico().getId(), respuesta.getfechaCreacion(), respuesta.getAutor().getId());
    }
}
