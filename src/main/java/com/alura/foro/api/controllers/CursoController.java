package com.alura.foro.api.controllers;

import com.alura.foro.api.domain.curso.*;
import com.alura.foro.api.repository.CursoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra nuevo curso en base de datos")
    public ResponseEntity<DatosRespuestaCurso> registrarCurso(@RequestBody @Valid DatosRegistrarCurso datosRegistrarCurso, UriComponentsBuilder uriComponentsBuilder) {
        Curso curso = cursoRepository.save(new Curso(datosRegistrarCurso));
        DatosRespuestaCurso datosRespuestaCurso = new DatosRespuestaCurso(curso.getId(), curso.getNombre(), curso.getCategoria().toString());

        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaCurso);
    }

    @GetMapping
    @Operation(summary = "Obtiene el listado de cursos")
    public ResponseEntity<Page<DatosDetalleCurso>> listaCursos(@PageableDefault(size = 2) Pageable paginacion) {
        return ResponseEntity.ok(cursoRepository.findByActivoTrue(paginacion).map(DatosDetalleCurso::new));
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Actualiza dtos de curso existente")
    public ResponseEntity actualizarCurso(@RequestBody @Valid DatosActualizarCurso datosActualizarCurso) {
        Curso curso = cursoRepository.getReferenceById(datosActualizarCurso.id());
        curso.actualizarDatos(datosActualizarCurso);
        return ResponseEntity.ok(new DatosRespuestaCurso(curso.getId(), curso.getNombre(), curso.getCategoria().toString()));
    }

    @DeleteMapping("/{id}") //DELETE LOGICO
    @Transactional
    @Operation(summary = "Elimina curso registrado a inactivo")
    public ResponseEntity desactivarCurso(@PathVariable Long id) {
        Curso curso = cursoRepository.getReferenceById(id);
        curso.desactivarCurso();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene registro de curso con ID")
    public ResponseEntity<DatosRespuestaCurso> muestraDatosCurso(@PathVariable Long id) {
        Curso curso = cursoRepository.getReferenceById(id);
        var datosCurso = new DatosRespuestaCurso(curso.getId(), curso.getNombre(), curso.getCategoria().toString());
        var cursoActivo=cursoRepository.findActivoById(datosCurso.id());
        if (!cursoActivo) {
            throw new ValidationException("No se puede mostrar cursos inactivos");
        }
        return ResponseEntity.ok(datosCurso);
    }

}
