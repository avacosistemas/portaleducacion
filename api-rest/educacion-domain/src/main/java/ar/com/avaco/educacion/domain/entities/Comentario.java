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
@Table(name = "COMENTARIO")
@SequenceGenerator(name = "COMENTARIO_SEQ", sequenceName = "COMENTARIO_SEQ", allocationSize = 1)
public class Comentario extends ar.com.avaco.arc.core.domain.Entity<Long> {
	
	/** serializacion */
	private static final long serialVersionUID = -2622456330960514017L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMENTARIO_SEQ")
	@Column(name = "ID_COMENTARIO")
    private Long id;
	
	@Column(name = "DESC_COMENTARIO", length = 280)
	private String comentario;
	
	@ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.MERGE)
    @JoinColumn(name = "ID_AULA", insertable = false, updatable = false)
	private Aula aula;
	
	public Comentario() {}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}
	
	
	//TODO Agregar hashCode, equals y toString cuando se completen todos los atributos
	
}
