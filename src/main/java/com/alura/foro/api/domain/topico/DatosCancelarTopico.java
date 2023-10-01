package com.alura.foro.api.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DatosCancelarTopico(@NotNull Long idTopico, MotivoCancelarTopico motivo) {
}
