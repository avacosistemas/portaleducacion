package ar.com.avaco.educacion.domain.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DECIDIR")
@SequenceGenerator(name = "DECIDIR_SEQ", sequenceName = "DECIDIR_SEQ", allocationSize = 1)
public class Decidir extends ar.com.avaco.arc.core.domain.Entity<Long> {
	
	/** serializacion */
	private static final long serialVersionUID = -8991448551162650942L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DECIDIR_SEQ")
	@Column(name = "ID_DECIDIR")
	private Long id;
	
	@OneToOne(mappedBy = "decidir", cascade = CascadeType.ALL)
	private Compra compra;
	
	
	public Decidir() {}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	

	//TODO Agregar hashCode, equals y toString cuando se completen todos los atributos

}
