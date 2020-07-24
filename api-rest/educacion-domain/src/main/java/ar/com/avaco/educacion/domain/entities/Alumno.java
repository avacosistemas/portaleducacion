package ar.com.avaco.educacion.domain.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import ar.com.avaco.educacion.domain.entities.cliente.Cliente;

@Entity
@Table(name = "ALUMNO")
@AttributeOverride(name = "id", column = @Column(name = "ID_ALUMNO"))
public class Alumno extends Cliente {
	
	/** serializacion */
	private static final long serialVersionUID = 939136778257772228L;

	@OneToMany(targetEntity= HorasAlumno.class, mappedBy="alumno", cascade=CascadeType.MERGE)
    private Set<HorasAlumno> horasDispAlumo = new HashSet<>();
	
	@OneToMany(targetEntity= Compra.class, mappedBy="alumno", cascade=CascadeType.MERGE)
    private Set<Compra> compras = new HashSet<>();
	
	@OneToMany(targetEntity= PreguntaRespuesta.class, mappedBy="alumno", cascade=CascadeType.MERGE)
    private Set<PreguntaRespuesta> preguntasRespuestas = new HashSet<>();

	@ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @JoinColumn(name = "ID_INSTITUCION")
	private Institucion institucion;

	@ManyToMany(mappedBy = "alumnos")
	Set<Aula> aulas;

	public Alumno() {}

	public Set<HorasAlumno> getHorasDispAlumo() {
		return horasDispAlumo;
	}

	public void setHorasDispAlumo(Set<HorasAlumno> horasDispAlumo) {
		this.horasDispAlumo = horasDispAlumo;
	}

	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}

	public Set<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(Set<Aula> aulas) {
		this.aulas = aulas;
	}

	public Set<Compra> getCompras() {
		return compras;
	}

	public void setCompras(Set<Compra> compras) {
		this.compras = compras;
	}

	public Set<PreguntaRespuesta> getPreguntasRespuestas() {
		return preguntasRespuestas;
	}

	public void setPreguntasRespuestas(Set<PreguntaRespuesta> preguntasRespuestas) {
		this.preguntasRespuestas = preguntasRespuestas;
	}

	public void removeAula(Aula aula) {
		this.getAulas().remove(aula);
		aula.getAlumnos().remove(this);		
	}

	public void addAula(Aula aula) {
		this.getAulas().add(aula);
		aula.getAlumnos().add(this);
		
	}

	
	//TODO Agregar hashCode, equals y toString cuando se completen todos los atributos
	
}
