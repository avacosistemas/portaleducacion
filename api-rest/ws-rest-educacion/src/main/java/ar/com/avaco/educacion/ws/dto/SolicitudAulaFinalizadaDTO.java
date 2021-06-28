package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.educacion.domain.entities.SolicitudAula;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class SolicitudAulaFinalizadaDTO extends DTOEntity<Long> {

	private Long id;

	private String idAulaString;

	private String nombreAlumno;

	private String nombreProfesor;

	private String fechaHora;

	private String institucion;

	private String documentoAlumno;

	private String materia;

	private String comentarios;

	private String estado;

	public SolicitudAulaFinalizadaDTO() {
	}

	public SolicitudAulaFinalizadaDTO(SolicitudAula sa) {
		this.comentarios = sa.getComentarios();
		this.documentoAlumno = sa.getAlumno().getIdentificacion().getTipo().toString() + "  " + sa.getAlumno().getIdentificacion().getNumero();
		this.estado = sa.getEstado().name();
		this.fechaHora = DateUtils.toString(sa.getAula().getDia()) + " - " + sa.getAula().getHora().toString() + " Hs.";
		this.idAulaString = sa.getAula().getIdString();
		this.institucion = sa.getAula().getInstitucion().getNombre();
		this.materia = sa.getAula().getMateria().getDescripcion();
		this.nombreAlumno = sa.getAlumno().getNombreApellido();
		this.nombreProfesor = sa.getAula().getProfesor().getNombreApellido();
		this.id = sa.getId();
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdAulaString() {
		return idAulaString;
	}

	public void setIdAulaString(String idAulaString) {
		this.idAulaString = idAulaString;
	}

	public String getNombreAlumno() {
		return nombreAlumno;
	}

	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}

	public String getNombreProfesor() {
		return nombreProfesor;
	}

	public void setNombreProfesor(String nombreProfesor) {
		this.nombreProfesor = nombreProfesor;
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public String getDocumentoAlumno() {
		return documentoAlumno;
	}

	public void setDocumentoAlumno(String documentoAlumno) {
		this.documentoAlumno = documentoAlumno;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

}
