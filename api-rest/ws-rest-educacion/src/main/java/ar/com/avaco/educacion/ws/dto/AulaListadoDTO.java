package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class AulaListadoDTO extends DTOEntity<Long> {

	protected Long id;

	protected Long idMateria;

	private String nombreMateria;

	protected Long idInstitucion;

	private String nombreInstitucion;

	protected String dia;

	protected String hora;

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
		this.setDia(DateUtils.toString(aula.getDia()));
		this.setHora(aula.getHora().toString());
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

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Long getIdInstitucion() {
		return idInstitucion;
	}

	public void setIdInstitucion(Long idInstitucion) {
		this.idInstitucion = idInstitucion;
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
	
}
