package ar.com.avaco.educacion.domain.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;

import ar.com.avaco.educacion.domain.entities.cliente.Cliente;
import ar.com.avaco.educacion.domain.entities.cliente.TipoCliente;

@Entity
@Table(name = "PROFESOR")
@AttributeOverride(name = "id", column = @Column(name = "ID_PROFESOR"))
public class Profesor extends Cliente implements Serializable {

	/** serializacion */
	private static final long serialVersionUID = 1206370224660296393L;

	@OneToMany(targetEntity = HorarioDisponible.class, mappedBy = "profesor", cascade = CascadeType.MERGE)
	private Set<HorarioDisponible> horariosDisp = new HashSet<>();

	@OneToMany(targetEntity = HorasAlumno.class, mappedBy = "profesor", cascade = CascadeType.MERGE)
	private Set<HorasAlumno> horasDispAlumo = new HashSet<>();

	@OneToMany(targetEntity = Compra.class, mappedBy = "profesor", cascade = CascadeType.MERGE)
	private Set<Compra> compras = new HashSet<>();

	@OneToMany(targetEntity = PreguntaRespuesta.class, mappedBy = "profesor", cascade = CascadeType.MERGE)
	private Set<PreguntaRespuesta> preguntasRespuestas = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "PROFESOR_MATERIA", joinColumns = @JoinColumn(name = "ID_PROFESOR"), inverseJoinColumns = @JoinColumn(name = "ID_MATERIA"))
	Set<Materia> materias;

	@ManyToMany(mappedBy = "profesores")
	Set<Aula> aulas;

	@Column(name = "VALOR_HORA", nullable = true)
	private Double valorHora;

	@Column(name = "CALIFICACION", nullable = true)
	private Double calificacion;

	@Column(name = "FOTO", nullable = true)
	@Type(type = "org.hibernate.type.BinaryType")
	private byte foto[];

	@Column(name = "MATERIAS_STRING", nullable = true)
	private String materiasString;

	@Column(name = "DESCRIPCION", nullable = true)
	private String descripcion;

	public Profesor() {
		this.setTipoCliente(TipoCliente.PROFESOR);
	}

	private void generarMateriasString() {
		List<String> collect = this.materias.stream().map(Materia::getDescripcion).collect(Collectors.toList());
		this.materiasString = StringUtils.join(collect, ", ");
	}

	public Set<HorarioDisponible> getHorariosDisp() {
		return horariosDisp;
	}

	public void setHorariosDisp(Set<HorarioDisponible> horariosDisp) {
		this.horariosDisp = horariosDisp;
	}

	public Set<HorasAlumno> getHorasDispAlumo() {
		return horasDispAlumo;
	}

	public void setHorasDispAlumo(Set<HorasAlumno> horasDispAlumo) {
		this.horasDispAlumo = horasDispAlumo;
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

	public Set<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(Set<Materia> materias) {
		this.materias = materias;
	}

	public Set<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(Set<Aula> aulas) {
		this.aulas = aulas;
	}

	public void addMateria(Materia materia) {
		this.materias.add(materia);
		materia.getProfesores().add(this);
		generarMateriasString();
	}

	public void removeMateria(Materia materia) {
		this.materias.remove(materia);
		materia.getProfesores().remove(this);
		generarMateriasString();
	}

	public void addAula(Aula aula) {
		this.aulas.add(aula);
		aula.getProfesores().add(this);
	}

	public void removeAula(Aula aula) {
		this.aulas.remove(aula);
		aula.getProfesores().remove(this);

	}

	public Double getValorHora() {
		return valorHora;
	}

	public void setValorHora(Double valorHora) {
		this.valorHora = valorHora;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Double calificacion) {
		this.calificacion = calificacion;
	}

	public String getMateriasString() {
		return materiasString;
	}

	public void setMateriasString(String materiasString) {
		this.materiasString = materiasString;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	// TODO Agregar hashCode, equals y toString cuando se completen todos los
	// atributos

}
