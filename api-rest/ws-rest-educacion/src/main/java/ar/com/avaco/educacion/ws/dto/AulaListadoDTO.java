package ar.com.avaco.educacion.ws.dto;

import java.util.Calendar;
import org.apache.commons.lang3.StringUtils;

import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class AulaListadoDTO extends DTOEntity<Long> {

	protected String idString;

	protected Long id;

	protected Long idMateria;

	private String nombreMateria;

	protected Long idInstitucion;

	private String nombreInstitucion;

	protected Long idProfesor;

	private String nombreProfesor;

	protected String dia;

	protected String hora;

	protected String estado;

	private Double calificacion;

	public AulaListadoDTO() {

	}

	public AulaListadoDTO(Aula aula) {
		setDTO(aula);
	}

	public void setDTO(Aula aula) {
		this.setId(aula.getId());
		this.setIdMateria(aula.getMateria().getId());
		this.setNombreMateria(aula.getMateria().getDescripcion());
		this.setIdInstitucion(aula.getInstitucion() != null ? aula.getInstitucion().getId() : null);
		this.setNombreInstitucion(aula.getInstitucion() != null ? aula.getInstitucion().getNombre() : null);
		this.setIdProfesor(aula.getProfesor() != null ? aula.getProfesor().getId() : null);
		this.setNombreProfesor(aula.getProfesor() != null ? aula.getProfesor().getNombreApellido() : null);
		this.setDia(DateUtils.toString(aula.getDia()));
		this.setHora(aula.getHora().toString());
		this.setCalificacion(aula.getCalificacion() > 0D ? aula.getCalificacion() : null);
		
		Calendar instance = Calendar.getInstance();
		instance.setTime(aula.getDia());
		instance.set(Calendar.HOUR, aula.getHora());
		instance.set(Calendar.MINUTE, 0);
		instance.set(Calendar.SECOND, 0);

		Calendar ahora = Calendar.getInstance();
		ahora.setTime(DateUtils.getFechaYHoraActual());

		if (instance.after(ahora)) {
			this.setEstado("Pendiente");
		} else {
			instance.set(Calendar.HOUR, aula.getHora() + 1);
			if (instance.before(ahora)) {
				this.setEstado("Finalizada");
			} else {
				this.setEstado("En Curso");
			}
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHora() {
		return hora;
	}

	public String getHoraString() {
		return StringUtils.leftPad(this.hora, 2, "0") + ":00 Hs";
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Long getIdInstitucion() {
		return idInstitucion;
	}

	public void setIdInstitucion(Long idInstitucion) {
		this.idInstitucion = idInstitucion;
	}

	public Long getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(Long idProfesor) {
		this.idProfesor = idProfesor;
	}

	public String getNombreMateria() {
		return nombreMateria;
	}

	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}

	public String getNombreInstitucion() {
		return nombreInstitucion;
	}

	public void setNombreInstitucion(String nombreInstitucion) {
		this.nombreInstitucion = nombreInstitucion;
	}

	public String getNombreProfesor() {
		return nombreProfesor;
	}

	public void setNombreProfesor(String nombreProfesor) {
		this.nombreProfesor = nombreProfesor;
	}

	public String getIdString() {
		return "Aula" + " #" + StringUtils.leftPad(id.toString(), 5, "0");
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Double calificacion) {
		this.calificacion = calificacion;
	}

	public void setIdString(String idString) {
		this.idString = idString;
	}

}
