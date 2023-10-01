package com.alura.foro.api.domain.topico;

import com.alura.foro.api.domain.curso.Curso;
import com.alura.foro.api.repository.CursoRepository;
import com.alura.foro.api.domain.topico.validaciones.ValidadorCancelarTopico;
import com.alura.foro.api.domain.topico.validaciones.ValidadorDeTopico;
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
public class AgendaDeTopicoService {

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRepository topicoRepository;

    private List<ValidadorDeTopico> validadores = new ArrayList<>();

    private List<ValidadorCancelarTopico> validadoresCancelar = new ArrayList<>();

    public DatosDetalleTopico registrar(DatosRegistrarTopico datos) {
        if (!usuarioRepository.findById(datos.idAutor()).isPresent()) {
            throw new ValidacionDeIntegridad("Este id del usuario no fue encontrado");
        }
        if (datos.idCurso()!=null && !cursoRepository.existsById(datos.idCurso())) {
            throw new ValidacionDeIntegridad("Este id del curso no fue encontrado");
        }
        validadores.forEach(v-> v.validar(datos));

        var autor = usuarioRepository.findById(datos.idAutor()).get();
        var curso = cursoRepository.findById(datos.idCurso()).get();

        if (curso==null){
            throw new ValidacionDeIntegridad("No existen topicos de este curso");
        }

        var topico = new Topico(datos.titulo(), datos.mensaje(), autor, curso);

        topicoRepository.save(topico);

        return new DatosDetalleTopico(topico);

    }

    public void cancelar(DatosCancelarTopico datos) {
        if (!topicoRepository.existsById(datos.idTopico())) {
            throw new ValidacionDeIntegridad("Id del topico introducido no existe");
        }

        validadoresCancelar.forEach(v-> v.validar(datos));

        var topico = topicoRepository.getReferenceById(datos.idTopico());
        topico.cancelar(datos.motivo());
    }
    private Curso escogerCurso(DatosRegistrarTopico datos) {
        if (datos.idCurso() != null) {
            return cursoRepository.getReferenceById(datos.idCurso());
        }
        if (datos.categoria() == null) {
            throw new ValidacionDeIntegridad("Debe escoger categoria para curso");
        }
        return cursoRepository.escogerCursoConCategoria(datos.categoria());
    }
    public Page<DatosDetalleTopico> consultar(Pageable paginacion){
        return topicoRepository.findAll(paginacion).map(DatosDetalleTopico::new);
    }

}
