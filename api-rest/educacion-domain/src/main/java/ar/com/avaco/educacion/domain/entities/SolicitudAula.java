package ar.com.avaco.educacion.domain.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AULA_SOLICITUD")
@SequenceGenerator(name = "AULA_SOLICITUD_SEQ", sequenceName = "AULA_SOLICITUD_SEQ", allocationSize = 1)
public class SolicitudAula extends ar.com.avaco.arc.core.domain.Entity<Long> {

	private static final long serialVersionUID = 6961361798933535455L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AULA_SOLICITUD_SEQ")
	@Column(name = "ID_AULA_SOLICITUD")
	private Long id;

	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@JoinColumn(name = "ID_ALUMNO")
	private Alumno alumno;

	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@JoinColumn(name = "ID_AULA")
	private Aula aula;

	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO", nullable = false)
	private EstadoSolicitudAula estado;

	@Column(name = "FECHA_SOLICITUD", nullable = false)
	private Date fechaSolicitud;

	@Column(name = "FECHA_ACCION")
	private Date fechaAccion;

	@Column(name = "COMENTARIOS")
	private String comentarios;

	public SolicitudAula() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public EstadoSolicitudAula getEstado() {
		return estado;
	}

	public void setEstado(EstadoSolicitudAula estado) {
		this.estado = estado;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public Date getFechaAccion() {
		return fechaAccion;
	}

	public void setFechaAccion(Date fechaAccion) {
		this.fechaAccion = fechaAccion;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

}
