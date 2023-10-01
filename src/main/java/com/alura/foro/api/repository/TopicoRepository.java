package com.alura.foro.api.repository;

import com.alura.foro.api.domain.curso.Curso;
import com.alura.foro.api.domain.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.time.LocalDateTime;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Boolean existsByCursoAndFechaCreacion(Curso curso, LocalDateTime fechaCreacion);

    Boolean existsByTitulo(String titulo);
}
