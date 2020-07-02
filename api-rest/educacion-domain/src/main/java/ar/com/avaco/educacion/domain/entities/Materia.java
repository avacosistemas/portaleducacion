package ar.com.avaco.educacion.domain.entities;

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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MATERIA")
@SequenceGenerator(name = "MATERIA_SEQ", sequenceName = "MATERIA_SEQ", allocationSize = 1)
public class Materia extends ar.com.avaco.arc.core.domain.Entity<Long> {
	
	/** serializacion */
	private static final long serialVersionUID = 8077314210615963115L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MATERIA_SEQ")
	@Column(name = "ID_MATERIA")
    private Long id;
	
	@Column(name="DESC_MATERIA", nullable=false)
	private String descripcion;
	
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "ID_NIVEL")
	private Nivel nivel;
    
	@ManyToMany(mappedBy = "materias")
	private Set<Profesor> profesores;
	
	@OneToMany(targetEntity= Aula.class, mappedBy="materia", cascade=CascadeType.MERGE)
    private Set<Aula> aulas = new HashSet<>();
    
	public Materia() {}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public Set<Profesor> getProfesores() {
		return profesores;
	}

	public void setProfesores(Set<Profesor> profesores) {
		this.profesores = profesores;
	}

	public Set<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(Set<Aula> aulas) {
		this.aulas = aulas;
	}
	
	public void addProfesor(Profesor profesor) {
        this.profesores.add(profesor);
        profesor.getMaterias().add(this);
    }
 
    public void removeProfesor(Profesor profesor) {
        this.profesores.remove(profesor);
        profesor.getMaterias().remove(this);
    }

	//TODO Agregar hashCode, equals y toString cuando se completen todos los atributos
	
}
