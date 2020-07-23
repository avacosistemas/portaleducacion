package ar.com.avaco.educacion.domain.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.com.avaco.educacion.domain.entities.cliente.Cliente;

import javax.persistence.JoinColumn;

@Entity
@Table(name = "ALUMNO")
@AttributeOverride(name = "id", column = @Column(name = "ID_ALUMNO"))
public class Alumno extends Cliente {
	
	/** serializacion */
	private static final long serialVersionUID = 939136778257772228L;
	
	@OneToMany(targetEntity= HorasAlumno.class, mappedBy="alumno", cascade=CascadeType.MERGE)
    private Set<HorasAlumno> horasDispAlumo = new HashSet<>();
	
	@OneToMany(targetEntity= Compra.class, mappedBy="alumno", cascade=CascadeType.MERGE)
    private Set<Compra> compras = new HashSet<>();
	
	@OneToMany(targetEntity= PreguntaRespuesta.class, mappedBy="alumno", cascade=CascadeType.MERGE)
    private Set<PreguntaRespuesta> preguntasRespuestas = new HashSet<>();
	
	@ManyToMany
	@JoinTable(
	  name = "ALUMNO_INSTITUCION", 
	  joinColumns = @JoinColumn(name = "ID_ALUMNO"), 
	  inverseJoinColumns = @JoinColumn(name = "ID_INSTITUCION"))
	Set<Institucion> instituciones = new HashSet<>();
	
	@ManyToMany(mappedBy = "alumnos")
	Set<Aula> aulas = new HashSet<>();

	public Alumno() {}

	public Set<HorasAlumno> getHorasDispAlumo() {
		return horasDispAlumo;
	}

	public void setHorasDispAlumo(Set<HorasAlumno> horasDispAlumo) {
		this.horasDispAlumo = horasDispAlumo;
	}

	public Set<Institucion> getInstituciones() {
		return instituciones;
	}

	public void setInstituciones(Set<Institucion> instituciones) {
		this.instituciones = instituciones;
	}

	public Set<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(Set<Aula> aulas) {
		this.aulas = aulas;
	}

	public Set<Compra> getCompras() {
		return compras;
	}

	public void setCompras(Set<Compra> compras) {
		this.compras = compras;
	}

	public Set<PreguntaRespuesta> getPreguntasRespuestas() {
		return preguntasRespuestas;
	}

	public void setPreguntasRespuestas(Set<PreguntaRespuesta> preguntasRespuestas) {
		this.preguntasRespuestas = preguntasRespuestas;
	}

	public void removeAula(Aula aula) {
		this.getAulas().remove(aula);
		aula.getAlumnos().remove(this);		
	}

	public void addAula(Aula aula) {
		this.getAulas().add(aula);
		aula.getAlumnos().add(this);
		
	}

	
	//TODO Agregar hashCode, equals y toString cuando se completen todos los atributos
	
}
