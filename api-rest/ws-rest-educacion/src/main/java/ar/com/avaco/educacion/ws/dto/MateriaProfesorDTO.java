package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.educacion.domain.entities.Materia;
import ar.com.avaco.educacion.domain.entities.Nivel;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class MateriaProfesorDTO extends DTOEntity<Long> {
	
	private Long id;
	private Long idMateria;
	private String descMateria;
	private Integer idNivel;
	private String descNivel;
	
	public MateriaProfesorDTO() {}

	public MateriaProfesorDTO(Materia materia) {
		this.setDTO(materia);
	}

	//Getters and Setters
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

	public String getDescMateria() {
		return descMateria;
	}

	public void setDescMateria(String descMateria) {
		this.descMateria = descMateria;
	}

	public Integer getIdNivel() {
		return idNivel;
	}

	public void setIdNivel(Integer idNivel) {
		this.idNivel = idNivel;
	}

	public String getDescNivel() {
		return descNivel;
	}

	public void setDescNivel(String descNivel) {
		this.descNivel = descNivel;
	}

	//Converter
	public Materia toEntity() {
		Materia materia = new Materia();
		materia.setId(this.getId());
		
		Nivel nivel = new Nivel();
		nivel.setId(this.idNivel);
	
		materia.setNivel(nivel);
	
		return materia;
	}
	
	public void setDTO(Materia materia) {
		this.setId(materia.getId());

	}


	

}
