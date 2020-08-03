package ar.com.avaco.educacion.domain.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.avaco.educacion.domain.entities.cliente.Cliente;

@Entity
@Table(name = "AULA_EVENTOS")
@SequenceGenerator(name = "AULA_EVENTO_SEQ", sequenceName = "AULA_EVENTO_SEQ", allocationSize = 1)
public class AulaEventos extends ar.com.avaco.arc.core.domain.Entity<Long> {

	/** serializacion */
	private static final long serialVersionUID = 939136778257772228L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AULA_EVENTO_SEQ")
	@Column(name = "ID_AULA_EVENTO")
	private Long id;

	@Column(name = "FECHA")
	private Date fecha;

	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@JoinColumn(name = "ID_USUARIO")
	private Cliente usuario;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_USUARIO", nullable = false)
	private TipoUsuario tipoUsuario;

	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@JoinColumn(name = "ID_AULA")
	private Aula aula;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_EVENTO", nullable = false)
	private TipoEventoAula tipoEvento;

	public AulaEventos() {
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cliente getUsuario() {
		return usuario;
	}

	public void setUsuario(Cliente usuario) {
		this.usuario = usuario;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public TipoEventoAula getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEventoAula tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// TODO Agregar hashCode, equals y toString cuando se completen todos los
	// atributos

}
