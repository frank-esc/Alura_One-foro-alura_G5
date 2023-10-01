package com.alura.foro.api.domain.respuesta;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarRespuesta(@NotNull Long id, String mensaje) {
}
