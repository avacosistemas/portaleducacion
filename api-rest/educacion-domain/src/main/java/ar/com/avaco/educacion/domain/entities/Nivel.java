package ar.com.avaco.educacion.domain.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "NIVEL")
@SequenceGenerator(name = "NIVEL_SEQ", sequenceName = "NIVEL_SEQ", allocationSize = 1)
public class Nivel extends ar.com.avaco.arc.core.domain.Entity<Integer> {
	
	/** serializacion */
	private static final long serialVersionUID = -6390813521335354714L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NIVEL_SEQ")
	@Column(name = "ID_NIVEL")
    private Integer id;
	
	@Column(name="DESC_NIVEL", nullable=false)
	private String descripcion;
	
    @OneToMany(targetEntity= Materia.class, mappedBy="nivel", cascade=CascadeType.MERGE)
    private Set<Materia> materias = new HashSet<>();
    
    public Nivel() {}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(Set<Materia> materias) {
		this.materias = materias;
	}

	//TODO Agregar hashCode, equals y toString cuando se completen todos los atributos
	
}
