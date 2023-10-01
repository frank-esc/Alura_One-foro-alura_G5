package com.alura.foro.api.domain.topico;

import com.alura.foro.api.domain.curso.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistrarTopico(@NotBlank String titulo, @NotBlank String mensaje, @NotNull Long idAutor, Long idCurso, Categoria categoria) {
}
