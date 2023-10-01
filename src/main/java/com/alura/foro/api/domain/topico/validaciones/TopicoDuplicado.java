package com.alura.foro.api.domain.topico.validaciones;

import com.alura.foro.api.domain.topico.DatosRegistrarTopico;
import com.alura.foro.api.repository.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class TopicoDuplicado {
    private TopicoRepository repository;

    public void validar(DatosRegistrarTopico datos) {
        if (datos.idCurso()==null)
            return;

        var topicoDuplicado = repository.existsByTitulo(datos.titulo());
        if (topicoDuplicado) {
            throw new ValidationException("Ya existe un topico con ese titulo");
        }
    }
}
