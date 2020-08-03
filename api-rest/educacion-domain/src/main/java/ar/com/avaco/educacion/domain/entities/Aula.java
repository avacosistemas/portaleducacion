package ar.com.avaco.educacion.domain.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AULA")
@SequenceGenerator(name = "AULA_SEQ", sequenceName = "AULA_SEQ", allocationSize = 1)
public class Aula extends ar.com.avaco.arc.core.domain.Entity<Long> {

	/** serializacion */
	private static final long serialVersionUID = 939136778257772228L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AULA_SEQ")
	@Column(name = "ID_AULA")
	private Long id;

	@OneToMany(targetEntity = AulaAlumno.class, mappedBy = "aula", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	Set<AulaAlumno> alumnos = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "AULA_PROFESOR", joinColumns = @JoinColumn(name = "ID_AULA"), inverseJoinColumns = @JoinColumn(name = "ID_PROFESOR"))
	Set<Profesor> profesores = new HashSet<>();
	
	@OneToMany(targetEntity = Comentario.class, mappedBy = "aula", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Set<Comentario> comentarios = new HashSet<>();
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_MATERIA")
	private Materia materia;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_INSTITUCION")
	private Institucion institucion;

	@Column(name = "DIA", nullable = false)
	private Date dia;

	@Column(name = "HORA", nullable = false)
	private Integer hora;

	@Column(name = "CALIFICACION", nullable = false)
	private Integer calificacion = 0;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PROFESOR")
	private Profesor profesor;

	public Aula() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<AulaAlumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(Set<AulaAlumno> alumnos) {
		this.alumnos = alumnos;
	}

	public Set<Profesor> getProfesores() {
		return profesores;
	}

	public void setProfesores(Set<Profesor> profesores) {
		this.profesores = profesores;
	}

	public Set<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Integer getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}

	public void addProfesor(Profesor profesor) {
		this.getProfesores().add(profesor);
		profesor.getAulas().add(this);

	}

	public void removeProfesor(Profesor profesor) {
		this.getProfesores().remove(profesor);
		profesor.getAulas().remove(this);

	}

	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}
	
	// TODO Agregar hashCode, equals y toString cuando se completen todos los
	// atributos

}
