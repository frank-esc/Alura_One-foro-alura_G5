package com.alura.foro.api.domain.respuesta;

import jakarta.validation.constraints.NotNull;

public record DatosCancelarRespuesta(@NotNull Long idRespuesta, MotivoCancelarRespuesta motivo) {
}
