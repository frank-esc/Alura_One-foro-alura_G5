package com.alura.foro.api.domain.respuesta;

import com.alura.foro.api.repository.CursoRepository;
import com.alura.foro.api.repository.RespuestaRepository;
import com.alura.foro.api.repository.TopicoRepository;
import com.alura.foro.api.repository.UsuarioRepository;
import com.alura.foro.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@SuppressWarnings("all")
public class AgendaDeRespuestaService {
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private RespuestaRepository respuestaRepository;

    private List<ValidadorDeRespuesta> validadores = new ArrayList<>();
    private List<ValidadorCancelarRespuesta> validadoresCancelar = new ArrayList<>();
    public DatosDetalleRespuesta agregar(DatosRegistrarRespuesta datos){
        if (!usuarioRepository.findById(datos.idAutor()).isPresent()) {
            throw new ValidacionDeIntegridad("Este id del autor no fue encontrado");
        }
        if (datos.idTopico()!=null && !topicoRepository.existsById(datos.idTopico())) {
            throw new ValidacionDeIntegridad("Este id del topico no fue encontrado");
        }
        validadores.forEach(v-> v.validar(datos));
        var topico = topicoRepository.findById(datos.idTopico()).get();
        var autor = usuarioRepository.findById(datos.idAutor()).get();

        if (topico==null){
            throw new ValidacionDeIntegridad("No existen respuesta del topico");
        }
        var respuesta = new Respuesta(datos.mensaje(),topico,autor);
        respuestaRepository.save(respuesta);
        return new DatosDetalleRespuesta(respuesta);
    }

    public void eliminar(DatosCancelarRespuesta datos) {
        if (!respuestaRepository.existsById(datos.idRespuesta())) {
            throw new ValidacionDeIntegridad("Id de la respuesta introducido no existe");
        }
        validadoresCancelar.forEach(v-> v.validar(datos));

        var respuesta = respuestaRepository.getReferenceById(datos.idRespuesta());
        respuesta.eliminar(datos.motivo());
    }
    public Page<DatosDetalleRespuesta> consultar(Pageable paginacion){
        return respuestaRepository.findAll(paginacion).map(DatosDetalleRespuesta::new);
    }

}
