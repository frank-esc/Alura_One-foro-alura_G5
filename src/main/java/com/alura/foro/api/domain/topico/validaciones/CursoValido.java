package com.alura.foro.api.domain.topico.validaciones;

import com.alura.foro.api.repository.CursoRepository;
import com.alura.foro.api.domain.topico.DatosRegistrarTopico;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoValido implements ValidadorDeTopico {
    @Autowired
    private CursoRepository repository;

    public void validar(DatosRegistrarTopico datos) {
        if (datos.idCurso()==null) {
            return;
        }
        var cursoValido = repository.findActivoById(datos.idCurso());
        if (!cursoValido){
            throw new ValidationException("No se puede agregar topicos de cursos invalidos");
        }
    }
}
