package ar.com.avaco.educacion.ws.dto;

import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class AulaAbiertaInstitucionDTO extends DTOEntity<Long> {

	private Long id;

	private String institucion;

	private String materia;

	private String profesor;

	private String dia;

	private String hora;

	private String estado;

	public AulaAbiertaInstitucionDTO(Aula aula) {
		createDTO(aula);
	}

	private void createDTO(Aula aula) {
		this.id = aula.getId();
		this.materia = aula.getMateria().getDescripcion();
		this.dia = DateUtils.toString(aula.getDia());
		this.hora = StringUtils.leftPad(aula.getHora().toString(), 2, "0");
		this.profesor = aula.getProfesor().getNombreApellido();
		this.institucion = aula.getInstitucion() != null ? aula.getInstitucion().getNombre() : "";
		Calendar ahora = Calendar.getInstance();
		ahora.setTime(DateUtils.getFechaYHoraActual());

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

	public String getProfesor() {
		return profesor;
	}

	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}

}
