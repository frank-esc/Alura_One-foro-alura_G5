package com.alura.foro.api.repository;

import com.alura.foro.api.domain.curso.Categoria;
import com.alura.foro.api.domain.curso.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Page<Curso> findByActivoTrue(Pageable paginacion);

    @Query("""
            select c from Curso c
            where
            c.categoria=:categoria
            order by rand()
            limit 1
            """)
    Curso escogerCursoConCategoria(Categoria categoria);

    @Query("""
            select c.activo
            from Curso c
            where c.id=:idCurso
            """)
    Boolean findActivoById(Long idCurso);

}
