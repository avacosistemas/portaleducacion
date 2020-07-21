package ar.com.avaco.educacion.domain.entities;

import java.time.LocalDate;
import java.time.LocalTime;
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
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
	  name = "AULA_ALUMNO", 
	  joinColumns = @JoinColumn(name = "ID_AULA"), 
	  inverseJoinColumns = @JoinColumn(name = "ID_ALUMNO"))
	Set<Alumno> alumnos;
	
	@ManyToMany
	@JoinTable(
	  name = "AULA_PROFESOR", 
	  joinColumns = @JoinColumn(name = "ID_AULA"), 
	  inverseJoinColumns = @JoinColumn(name = "ID_PROFESOR"))
	Set<Profesor> profesores;
	
	@OneToMany(targetEntity= Comentario.class, mappedBy="aula", cascade=CascadeType.MERGE)
    private Set<Comentario> comentarios = new HashSet<>();
	
	@ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.MERGE)
    @JoinColumn(name = "ID_MATERIA", insertable = false, updatable = false)
	private Materia materia;	
	
	@ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.MERGE)
    @JoinColumn(name = "ID_INSTITUCION", insertable = false, updatable = false)
	private Institucion institucion;
	
	@Column(name = "DIA", nullable = false)
	private LocalDate dia;
	
	@Column(name = "HORA", nullable = false)
	private LocalTime hora;

	@Column(name = "CALIFICACION", nullable = false)
	private Integer calificacion;

	public Aula() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(Set<Alumno> alumnos) {
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

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void LocalTime(LocalTime hora) {
		this.hora = hora;
	}
	
	

	public Integer getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public void addProfesor(Profesor profesor) {
		this.getProfesores().add(profesor);
		profesor.getAulas().add(this);
		
	}

	public void removeProfesor(Profesor profesor) {
		this.getProfesores().remove(profesor);
		profesor.getAulas().remove(this);
		
	}

	public void removeAlumno(Alumno alumno) {
		this.getAlumnos().remove(alumno);
		alumno.getAulas().remove(this);
		
	}

	public void addAlumno(Alumno alumno) {
		this.getAlumnos().add(alumno);
		alumno.getAulas().add(this);		
	}

	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}

	
	
	//TODO Agregar hashCode, equals y toString cuando se completen todos los atributos
	
}
