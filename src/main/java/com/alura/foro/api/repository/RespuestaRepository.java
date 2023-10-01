package com.alura.foro.api.repository;

import com.alura.foro.api.domain.respuesta.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {

    @Query("""
            select r from Respuesta r
            join r.topico t
            where
            t.id=:idTopico
            """)
    List<Respuesta> findAllByTopicoId(Long idTopico);
}
