package com.alura.foro.api.domain.topico.validaciones;

import com.alura.foro.api.domain.topico.DatosRegistrarTopico;

public interface ValidadorDeTopico {
    void validar(DatosRegistrarTopico datos);
}
