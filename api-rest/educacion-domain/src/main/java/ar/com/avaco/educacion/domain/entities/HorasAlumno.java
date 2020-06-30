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
@Table(name = "HORAS_DISP")
@SequenceGenerator(name = "HORAS_DISP_SEQ", sequenceName = "HORAS_DISP_SEQ", allocationSize = 1)
public class HorasAlumno extends ar.com.avaco.arc.core.domain.Entity<Long> {
	
	/** serializacion */
	private static final long serialVersionUID = -6943378044790435321L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HORAS_DISP_SEQ")
	@Column(name = "ID_HORAS_DISP")
    private Long id;
	
	@ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.MERGE)
    @JoinColumn(name = "ID_PROFESOR", insertable = false, updatable = false)
	private Profesor profesor;
	
	@ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.MERGE)
    @JoinColumn(name = "ID_ALUMNO", insertable = false, updatable = false)
	private Alumno alumno;
	
	@Column(name = "CANT_HORAS_DISP")
	private Integer horasDisponibles;
	
	public HorasAlumno() {}

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

	public Integer getHorasDisponibles() {
		return horasDisponibles;
	}

	public void setHorasDisponibles(Integer horasDisponibles) {
		this.horasDisponibles = horasDisponibles;
	}
	
	//TODO Agregar hashCode, equals y toString cuando se completen todos los atributos

}
