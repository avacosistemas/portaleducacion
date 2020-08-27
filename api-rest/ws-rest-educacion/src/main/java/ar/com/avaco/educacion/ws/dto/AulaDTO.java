package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.Institucion;
import ar.com.avaco.educacion.domain.entities.Materia;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class AulaDTO extends DTOEntity<Long> {

	protected Long id;

	protected Long idMateria;

	protected Long idInstitucion;

	protected String dia;

	protected String hora;

	protected Long idProfesor;

	protected String nombreProfesor;

	protected String urlJoin;

	public AulaDTO() {

	}

	public AulaDTO(Aula aula) {
		setDTO(aula);
	}

	public Aula toEntity() {
		Aula aula = new Aula();
		aula.setId(this.getId());
		aula.setDia(DateUtils.toDate(getDia(), "dd/MM/yyyy"));
		aula.setHora(Integer.parseInt(getHora()));
		Materia materia = new Materia();
		materia.setId(idMateria);
		aula.setMateria(materia);

		if (this.getIdInstitucion() != null && this.getIdInstitucion() > 0) {
			Institucion institucion = new Institucion();
			institucion.setId(this.getIdInstitucion());
			aula.setInstitucion(institucion);
		}

		Profesor profesor = new Profesor();
		profesor.setId(this.getIdProfesor());
		aula.setProfesor(profesor);

		return aula;
	}

	public void setDTO(Aula aula) {
		this.setId(aula.getId());
		this.setIdMateria(aula.getMateria().getId());
		this.setIdInstitucion(aula.getInstitucion() != null ? aula.getInstitucion().getId() : null);
		this.setDia(DateUtils.toString(aula.getDia()));
		this.setHora(aula.getHora().toString());
		this.setIdProfesor(aula.getProfesor().getId());
		this.setNombreProfesor(aula.getProfesor().getNombreApellido());
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

	public Long getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(Long idProfesor) {
		this.idProfesor = idProfesor;
	}

	public String getNombreProfesor() {
		return nombreProfesor;
	}

	public void setNombreProfesor(String nombreProfesor) {
		this.nombreProfesor = nombreProfesor;
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

	public Long getIdInstitucion() {
		return idInstitucion;
	}

	public void setIdInstitucion(Long idInstitucion) {
		this.idInstitucion = idInstitucion;
	}

	public String getUrlJoin() {
		return urlJoin;
	}

	public void setUrlJoin(String urlJoin) {
		this.urlJoin = urlJoin;
	}

}
