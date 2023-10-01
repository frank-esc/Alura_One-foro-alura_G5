package com.alura.foro.api.domain.curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Table(name = "cursos")
@Entity(name = "Curso")
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	private Boolean activo = true;

	public Curso(DatosRegistrarCurso datosRegistrarCurso) {
		this.nombre = datosRegistrarCurso.nombre();
		this.categoria = datosRegistrarCurso.categoria();
		this.activo = true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void actualizarDatos(DatosActualizarCurso datosActualizarCurso) {
		if (datosActualizarCurso.nombre() != null) {
			this.nombre = datosActualizarCurso.nombre();
		}
	}

	public void desactivarCurso() {
		this.activo = false;
	}

}
