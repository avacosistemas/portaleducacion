package ar.com.avaco.educacion.domain.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.avaco.educacion.domain.entities.cliente.Cliente;

@Entity
@Table(name = "PROFESOR")
@AttributeOverride(name = "id", column = @Column(name = "ID_PROFESOR"))
public class Profesor extends Cliente {
	
	/** serializacion */
	private static final long serialVersionUID = 1206370224660296393L;
	
	/*
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFESOR_SEQ")
	@Column(name = "ID_PROFESOR")
    private Long id;*/

	@OneToMany(targetEntity= Horario.class, mappedBy="profesor", cascade=CascadeType.MERGE)
    private Set<Horario> horarios = new HashSet<>();
	
	@OneToMany(targetEntity= Disponibilidad.class, mappedBy="profesor", cascade=CascadeType.MERGE)
    private Set<Disponibilidad> disponibilidad = new HashSet<>();
	
	@OneToMany(targetEntity= Compra.class, mappedBy="profesor", cascade=CascadeType.MERGE)
    private Set<Compra> compras = new HashSet<>();
	
	@OneToMany(targetEntity= PreguntaRespuesta.class, mappedBy="profesor", cascade=CascadeType.MERGE)
    private Set<PreguntaRespuesta> preguntasRespuestas = new HashSet<>();
	
	@ManyToMany
	@JoinTable(
	  name = "PROFESOR_MATERIA", 
	  joinColumns = @JoinColumn(name = "ID_PROFESOR"), 
	  inverseJoinColumns = @JoinColumn(name = "ID_MATERIA"))
	Set<Materia> materias;
	
	@ManyToMany(mappedBy = "profesores")
	Set<Aula> aulas;
	
	public Profesor() {}

	public Set<Horario> getHorarios() {
		return horarios;
	}


	public void setHorarios(Set<Horario> horarios) {
		this.horarios = horarios;
	}


	public Set<Disponibilidad> getDisponibilidad() {
		return disponibilidad;
	}


	public void setDisponibilidad(Set<Disponibilidad> disponibilidad) {
		this.disponibilidad = disponibilidad;
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


	public Set<Materia> getMaterias() {
		return materias;
	}


	public void setMaterias(Set<Materia> materias) {
		this.materias = materias;
	}

	
	//TODO Agregar hashCode, equals y toString cuando se completen todos los atributos
	
}
