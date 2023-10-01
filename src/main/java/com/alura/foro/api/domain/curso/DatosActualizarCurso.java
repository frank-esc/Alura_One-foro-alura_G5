package com.alura.foro.api.domain.curso;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarCurso(@NotNull Long id, String nombre) {
}
