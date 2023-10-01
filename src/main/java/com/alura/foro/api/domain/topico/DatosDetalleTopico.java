package com.alura.foro.api.domain.topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion,
                                 StatusTopico status, Long idAutor, Long idCurso) {
    public DatosDetalleTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getfechaCreacion(),
                topico.getStatus(), topico.getAutor().getId(), topico.getCurso().getId());
    }
}
