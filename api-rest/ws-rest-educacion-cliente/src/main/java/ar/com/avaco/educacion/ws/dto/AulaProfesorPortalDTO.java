package ar.com.avaco.educacion.ws.dto;

import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class AulaProfesorPortalDTO extends DTOEntity<Long> {

	private Long id;

	private String materia;

	private String institucion;

	private String dia;

	private String hora;

	private Integer calificacion;

	private String estado;

	private String idString;

	public AulaProfesorPortalDTO(Aula aula) {
		this.id = aula.getId();
		this.materia = aula.getMateria().getDescripcion();
		this.dia = DateUtils.toString(aula.getDia());
		this.hora = StringUtils.leftPad(aula.getHora().toString(), 2, "0");
		this.calificacion = aula.getCalificacion();
		this.institucion = aula.getInstitucion() != null ? aula.getInstitucion().getNombre() : "";
		Calendar ahora = Calendar.getInstance();
		ahora.setTime(DateUtils.getFechaYHoraActual());

		this.idString = "Aula" + " #" + StringUtils.leftPad(id.toString(), 5, "0");
		
		Calendar instance = Calendar.getInstance();
		instance.setTime(aula.getDia());
		instance.set(Calendar.HOUR, aula.getHora());
		instance.set(Calendar.MINUTE, 0);
		instance.set(Calendar.SECOND, 0);

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

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
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

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Integer getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}

	public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getIdString() {
		return idString;
	}

	public void setIdString(String idString) {
		this.idString = idString;
	}

}
