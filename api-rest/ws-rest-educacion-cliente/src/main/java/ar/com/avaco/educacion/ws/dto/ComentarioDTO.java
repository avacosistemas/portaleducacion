package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.educacion.domain.entities.Comentario;
import ar.com.avaco.utils.DateUtils;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class ComentarioDTO extends DTOEntity<Long> {

	private Long id;

	private String nombre;

	private String comentario;

	private String fechaHora;

	public ComentarioDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public ComentarioDTO(Comentario comentario) {
		this.id = comentario.getId();
		this.nombre = comentario.getNombre();
		this.fechaHora = DateUtils.toString(comentario.getFecha(), DateUtils.PATTERN_FULL_24_HS);
		this.comentario = comentario.getComentario();
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
