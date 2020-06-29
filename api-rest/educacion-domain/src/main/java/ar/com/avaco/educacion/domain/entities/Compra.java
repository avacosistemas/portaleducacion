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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "HORAS_COMPRA")
@SequenceGenerator(name = "COMPRA_SEQ", sequenceName = "COMPRA_SEQ", allocationSize = 1)
public class Compra extends ar.com.avaco.arc.core.domain.Entity<Long> {

	/** serializacion */
	private static final long serialVersionUID = -8760441611443270296L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPRA_SEQ")
	@Column(name = "ID_COMPRA")
    private Long id;
	
	@OneToOne
    @JoinColumn(name = "FK_DECIDIR", updatable = false, nullable = false)
    private Decidir decidir;

	@ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.MERGE)
    @JoinColumn(name = "ID_PROFESOR", insertable = false, updatable = false)
	private Profesor profesor;
	
	@ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.MERGE)
    @JoinColumn(name = "ID_ALUMNO", insertable = false, updatable = false)
	private Alumno alumno;
	
	@Column(name = "CANT_HORAS_COMPRA")
	private Integer horasCompradas;
	
	public Compra() {}

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

	public Integer getHorasCompradas() {
		return horasCompradas;
	}

	public void setHorasCompradas(Integer horasCompradas) {
		this.horasCompradas = horasCompradas;
	}

	//TODO Agregar hashCode, equals y toString cuando se completen todos los atributos
	
}
