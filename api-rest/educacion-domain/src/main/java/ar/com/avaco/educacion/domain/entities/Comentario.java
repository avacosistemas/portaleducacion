package ar.com.avaco.educacion.domain.entities;

import java.util.Date;

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

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "FECHA")
	private Date fecha;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_AULA")
	private Aula aula;

	public Comentario() {
	}

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	// TODO Agregar hashCode, equals y toString cuando se completen todos los
	// atributos

}
