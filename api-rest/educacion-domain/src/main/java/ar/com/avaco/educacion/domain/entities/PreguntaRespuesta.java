package ar.com.avaco.educacion.domain.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PREG_RESP")
@SequenceGenerator(name = "PREG_RESP_SEQ", sequenceName = "PREG_RESP_SEQ", allocationSize = 1)
public class PreguntaRespuesta extends ar.com.avaco.arc.core.domain.Entity<Long> {
	

	/** serializacion */
	private static final long serialVersionUID = 3040662695842031809L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PREG_RESP_SEQ")
	@Column(name = "ID_PREG_RESP")
    private Long id;
	
	@ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.MERGE)
    @JoinColumn(name = "ID_ALUMNO", insertable = false, updatable = false)
	private Alumno alumno;
	
	@ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.MERGE)
    @JoinColumn(name = "ID_PROFESOR", insertable = false, updatable = false)
	private Profesor profesor;
	
	@ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.MERGE)
    @JoinColumn(name = "ID_MATERIA", insertable = false, updatable = false)
	private Materia materia;
	
	@Column(name = "PREGUNTA")
	private String pregunta;
	
	@Column(name = "RESPUESTA")
	private String respuesta;
	
	public PreguntaRespuesta() {}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}


	//TODO Agregar hashCode, equals y toString cuando se completen todos los atributos

}
