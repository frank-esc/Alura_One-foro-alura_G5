package com.alura.foro.api.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistrarCurso(@NotBlank String nombre, @NotNull Categoria categoria) {
}
