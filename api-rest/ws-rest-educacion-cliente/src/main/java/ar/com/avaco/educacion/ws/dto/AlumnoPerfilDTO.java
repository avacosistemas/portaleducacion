package ar.com.avaco.educacion.ws.dto;

import ar.com.avaco.educacion.domain.entities.Alumno;
import ar.com.avaco.ws.rest.dto.DTOEntity;

public class AlumnoPerfilDTO extends DTOEntity<Long> {

	private Long id;
	private String nombre;
	private String apellido;
	private String tipoIdentificacion;
	private String numeroIdentificacion;
	private String username;
	private String email;
	private String telefonoMovil;
	private String telefonoFijo;
	private String descripcion;
	private String institucion;
	private byte[] foto;

	public AlumnoPerfilDTO() {
	}

	public AlumnoPerfilDTO(Alumno alumno) {
		this.setDTO(alumno);
	}

	public String getNombreApellido() {
		return this.nombre + " " + this.apellido;
	}

	public void setDTO(Alumno alumno) {
		this.setId(alumno.getId());
		this.setNombre(alumno.getNombre());
		this.setApellido(alumno.getApellido());
		this.setTipoIdentificacion(alumno.getIdentificacion().getTipo().name());
		this.setNumeroIdentificacion(alumno.getIdentificacion().getNumero());
		this.setUsername(alumno.getUsername());
		this.setEmail(alumno.getEmail());
		this.setTelefonoMovil(alumno.getContacto().getTelefonoMovil());
		this.setTelefonoFijo(alumno.getContacto().getTelefonoFijo());
		this.setInstitucion(alumno.getInstitucion() != null ? alumno.getInstitucion().getNombre() : null);
		this.setFoto(alumno.getFoto());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefonoMovil() {
		return telefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	public String getTelefonoFijo() {
		return telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

}
