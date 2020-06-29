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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.avaco.educacion.domain.entities.cliente.Cliente;

import javax.persistence.JoinColumn;

@Entity
@Table(name = "ALUMNO")
@AttributeOverride(name = "id", column = @Column(name = "ID_ALUMNO"))
public class Alumno extends Cliente {
	
	/** serializacion */
	private static final long serialVersionUID = 939136778257772228L;
	
	/*
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALUMNO_SEQ")
	@Column(name = "ID_ALUMNO")
    private Long id;*/
	
	@OneToMany(targetEntity= Disponibilidad.class, mappedBy="alumno", cascade=CascadeType.MERGE)
    private Set<Disponibilidad> disponibilidad = new HashSet<>();
	
	@OneToMany(targetEntity= Compra.class, mappedBy="alumno", cascade=CascadeType.MERGE)
    private Set<Compra> compras = new HashSet<>();
	
	@OneToMany(targetEntity= PreguntaRespuesta.class, mappedBy="alumno", cascade=CascadeType.MERGE)
    private Set<PreguntaRespuesta> preguntasRespuestas = new HashSet<>();
	
	@ManyToMany
	@JoinTable(
	  name = "ALUMNO_INSTITUCION", 
	  joinColumns = @JoinColumn(name = "ID_ALUMNO"), 
	  inverseJoinColumns = @JoinColumn(name = "ID_INSTITUCION"))
	Set<Institucion> instituciones;
	
	@ManyToMany(mappedBy = "alumnos")
	Set<Aula> aulas;

	public Alumno() {}


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

	
	//TODO Agregar hashCode, equals y toString cuando se completen todos los atributos
	
}
