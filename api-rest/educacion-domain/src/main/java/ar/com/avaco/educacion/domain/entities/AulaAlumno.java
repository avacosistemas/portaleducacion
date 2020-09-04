package ar.com.avaco.educacion.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AULA_ALUMNO")
@SequenceGenerator(name = "AULA_ALUMNO_SEQ", sequenceName = "AULA_ALUMNO_SEQ", allocationSize = 1)
public class AulaAlumno extends ar.com.avaco.arc.core.domain.Entity<Long> {

	private static final long serialVersionUID = -16480283449455363L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AULA_ALUMNO_SEQ")
	@Column(name = "ID_AULA_ALUMNO")
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_AULA")
	private Aula aula;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_ALUMNO")
	private Alumno alumno;

	@Column(nullable = true, name = "CALIFICACION")
	private Double calificacion;

	@Column(nullable = true, name = "COMENTARIO")
	private String comentario;

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Double calificacion) {
		this.calificacion = calificacion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
