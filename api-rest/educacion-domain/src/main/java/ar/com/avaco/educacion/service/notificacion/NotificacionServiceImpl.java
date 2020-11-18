package ar.com.avaco.educacion.service.notificacion;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.ListUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.service.MailSenderSMTPService;
import ar.com.avaco.educacion.domain.entities.Alumno;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.AulaAlumno;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.domain.entities.aulaVirtual.Clase;
import ar.com.avaco.educacion.domain.entities.cliente.Cliente;
import ar.com.avaco.utils.DateUtils;

@Service("notificacionService")
public class NotificacionServiceImpl implements NotificacionService {

	private static final String CLASSPATH_RESOURCE_LOADER_CLASS = "classpath.resource.loader.class";
	private static final String CLASSPATH = "classpath";
	private static final String ISO_8859_1 = "ISO-8859-1";

	private VelocityEngine ve;

	private MailSenderSMTPService mailSenderSMTPService;

	@Value("teachonline@teachonline.com.ar")
	private String from;

	@Value("TeachOnline - Restablecimiento de contraseña")
	private String subjectResetoPasswordCliente;

	@Value("template/reseteo-password-cliente.html")
	private String bodyResetoPasswordCliente;

	@Value("TechOnline - Bienvenido")
	private String subjectRegistroClienteNuevoPassword;

	@Value("template/registro-cliente-nuevo-password.html")
	private String bodyRegistroClienteNuevoPassword;	
	
	@Value("template/registro-alumno-nuevo.html")
	private String bodyRegistroAlumnoNuevo;
	
	@Value("TechOnline - Usuario Habiliatdo!")
	private String subjectHabilitacionExistosa;
	
	@Value("template/notificacion-habilitacion-existosa.html")
	private String bodyHabilitacionExistosa;
	
	@Value("TechOnline - Docente aprobado!")
	private String subjectAprobacionDocenteCategoria;
	
	@Value("template/notificacion-aprobacion-docente-categoria.html")
	private String bodyAprobacionDocenteCategoria;
	
	@Value("TechOnline - Nueva pregunta!")
	private String subjectNuevaPregunta;
	
	@Value("template/notificacion-nueva-pregunta.html")
	private String bodyNuevaPregunta;
	
	@Value("TechOnline - Has comprado una clase!")
	private String subjectCompraClase;
	
	@Value("template/notificacion-compra-clase.html")
	private String bodyCompraClase;
	
	@Value("TechOnline - Pago aprobado!")
	private String subjectPagoClase;
	
	@Value("template/notificacion-pago-clase.html")
	private String bodyPagoClase;
	
	@Value("TechOnline - Nueva calificacion!")
	private String subjectNuevaCalificacion;
	
	@Value("template/notificacion-nueva-calificacion.html")
	private String bodyNuevaCalificacion;
	
	@Value("TechOnline - Asignación de aula!")
	private String subjectAsignacionProfesorAula;
	
	@Value("template/notificacion-asignacion-aula-profesor.html")
	private String bodyAsignacionProfesorAula;
	
	@Value("TechOnline - Asignación de aula!")
	private String subjectAsignacionAlumnoAula;
	
	@Value("template/notificacion-asignacion-aula-aulumno.html")
	private String bodyAsignacionAlumnoAula;
	
	
	@Value("template/header-general.html")
	private String headerGeneral;

	@Value("template/footer-general.html")
	private String footerGeneral;
	
	@Value("TechOnline - Actualización Datos del Aula")
	private String subjectActualizarAula;
	
	@Value("template/bodyActualizarAula.html")
	private String bodyActualizarAula;
	
	@Value("TeachOnline - Baja de Asignación de Aula")
	private String subjectCambioProfesorAnterior;

	@Value("template/bodyCambioProfesorAnterior.html")
	private String bodyCambioProfesorAnterior;
	
	@Value("TeachOnline - Asignación de Aula")
	private String subjectCambioProfesorNuevo;
	
	@Value("template/bodyCambioProfesorNuevo.html")
	private String bodyCambioProfesorNuevo;	

	@Value("avacosistemas@gmail.com")
	private String supportMail;
	
	public NotificacionServiceImpl() {
		ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, CLASSPATH);
		ve.setProperty(CLASSPATH_RESOURCE_LOADER_CLASS, ClasspathResourceLoader.class.getName());
		ve.init();
	}

	public void notificarInicioServer() {
		mailSenderSMTPService.sendMail(from, supportMail, "Inicio Server Tomcat",
				"Se inicio el server. " + DateUtils.toString(new Date(), DateUtils.PATTERN_FULL_24_HS), null);
		
	}

	private String getHeader() {
		VelocityContext context = new VelocityContext();
		Template template = ve.getTemplate(headerGeneral, ISO_8859_1);
		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		return writer.toString();
	}

	private String getFooter() {
		VelocityContext context = new VelocityContext();
		Template template = ve.getTemplate(footerGeneral, ISO_8859_1);
		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		return writer.toString();
	}

	protected String getBody(Map<String, String> parametros, String pathBody) {
		VelocityContext context = new VelocityContext();
		parametros.forEach((clave, valor) -> context.put(clave, valor));
		Template template = ve.getTemplate(pathBody, ISO_8859_1);
		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		StringBuilder sb = new StringBuilder();
		sb.append(getHeader());
		sb.append(writer.toString());
		sb.append(getFooter());
		return sb.toString();
	}

	@Async
	@Override
	public void notificarResetoPassword(Cliente cliente, String tmppass) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("cliente", cliente.getNombreApellido());
		params.put("usuario", cliente.getUsername());
		params.put("password", tmppass);
		mailSenderSMTPService.sendMail(from, cliente.getEmail(), subjectResetoPasswordCliente,
				getBody(params, bodyResetoPasswordCliente), null);
	}

	@Async
	@Override
	public void notificarRegistroClienteNuevoPassword(Cliente cliente, String tmpass) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("cliente", cliente.getNombreApellido());
		params.put("usuario", cliente.getUsername());
		params.put("password", tmpass);
		mailSenderSMTPService.sendMail(from, cliente.getEmail(), subjectRegistroClienteNuevoPassword,
				getBody(params, bodyRegistroClienteNuevoPassword), null);
	}
	
	@Override
	public void notificarAlumnoNuevo(Cliente cliente) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("cliente", cliente.getNombreApellido());
		params.put("usuario", cliente.getUsername());
		mailSenderSMTPService.sendMail(from, cliente.getEmail(), subjectRegistroClienteNuevoPassword,
				getBody(params, bodyRegistroAlumnoNuevo), null);
		
	}
	
	@Override
	public void notificarHabilitacionExitosa(Cliente cliente) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("cliente", cliente.getNombreApellido());		
		mailSenderSMTPService.sendMail(from, cliente.getEmail(), subjectHabilitacionExistosa,
				getBody(params, bodyHabilitacionExistosa), null);
		
	}
	
	@Override
	public void notificarAprobacionDocenteCategoria(Cliente cliente, String categoria ) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("cliente", cliente.getNombreApellido());	
		params.put("categoria", categoria );	
		mailSenderSMTPService.sendMail(from, cliente.getEmail(), subjectAprobacionDocenteCategoria,
				getBody(params, bodyAprobacionDocenteCategoria), null);
		
	}
	
	@Override
	public void notificarNuevaPregunta(Cliente cliente, String url ) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("cliente", cliente.getNombreApellido());	
		params.put("url", url );
		mailSenderSMTPService.sendMail(from, cliente.getEmail(), subjectNuevaPregunta,
				getBody(params, bodyNuevaPregunta), null);		
	}
	
	@Override
	public void notificarCompraClase(Cliente cliente, Clase clase, Aula aula ) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("cliente", cliente.getNombreApellido());	
		params.put("claseId", clase.getIdClase());
		params.put("claseLink", clase.getUrl());		
		params.put("dia", new SimpleDateFormat("dd/MM/yyyy").format(aula.getDia()));
		params.put("hora", aula.getHora().toString());
		params.put("aulaId", aula.getId().toString());
		params.put("materia", aula.getMateria().getDescripcion() + "("+ aula.getMateria().getNivel().getDescripcion() +")" );
		mailSenderSMTPService.sendMail(from, cliente.getEmail(), subjectCompraClase,
				getBody(params, bodyCompraClase), null);		
	}
	
	@Override
	public void notificarPagoClase(Cliente cliente, Clase clase, Aula aula ) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("cliente", cliente.getNombreApellido());	
		params.put("claseId", clase.getIdClase());
		params.put("claseLink", clase.getUrl());		
		params.put("dia", new SimpleDateFormat("dd/MM/yyyy").format(aula.getDia()));
		params.put("hora", aula.getHora().toString());
		params.put("aulaId", aula.getId().toString());
		params.put("materia", aula.getMateria().getDescripcion() + "("+ aula.getMateria().getNivel().getDescripcion() +")" );
		mailSenderSMTPService.sendMail(from, cliente.getEmail(), subjectPagoClase,
				getBody(params, bodyPagoClase), null);		
	}
	
	@Override
	public void notificarNuevaCalificacion(Cliente profesor, Cliente alumno, String calificacion, String comentario) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("profesor", profesor.getNombreApellido());
		params.put("alumno", alumno.getNombreApellido());
		params.put("calificacion", calificacion );
		params.put("comentario", comentario );
		mailSenderSMTPService.sendMail(from, profesor.getEmail(), subjectNuevaCalificacion,
				getBody(params, bodyNuevaCalificacion), null);		
	}
	
	@Override
	public void notificarAsignacionProfesorAula(Cliente profesor, Aula aula) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("profesor", profesor.getNombreApellido());
		params.put("dia", new SimpleDateFormat("dd/MM/yyyy").format(aula.getDia()));
		params.put("hora", aula.getHora().toString());
		params.put("aulaId", aula.getId().toString());
		params.put("materia", aula.getMateria().getDescripcion() + "("+ aula.getMateria().getNivel().getDescripcion() +")" );
		
		mailSenderSMTPService.sendMail(from, profesor.getEmail(), subjectAsignacionProfesorAula,
				getBody(params, bodyAsignacionProfesorAula), null);		
	}
	
	@Override
	public void notificarAsignacionAlumnoAula(Cliente alumno, Aula aula) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("profesor", alumno.getNombreApellido());
		params.put("dia", new SimpleDateFormat("dd/MM/yyyy").format(aula.getDia()));
		params.put("hora", aula.getHora().toString());
		params.put("aulaId", aula.getId().toString());
		params.put("materia", aula.getMateria().getDescripcion() + "("+ aula.getMateria().getNivel().getDescripcion() +")" );
		
		mailSenderSMTPService.sendMail(from, alumno.getEmail(), subjectAsignacionAlumnoAula,
				getBody(params, bodyAsignacionAlumnoAula), null);		
	}
	
	@Override
	public void notificarActualizacionAula(Aula aula) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("idAula", aula.getIdString());
		params.put("fecha", DateUtils.toString(aula.getDia()));
		params.put("hora", aula.getHora().toString() + " Hs.");
		params.put("materia", aula.getMateria().getDescripcion() + aula.getMateria().getNivel().getDescripcion());
		
		List<String> bcc = aula.getAlumnos().stream().map(aa -> aa.getAlumno().getEmail()).collect(Collectors.toList());
		bcc.add(aula.getProfesor().getEmail());
		mailSenderSMTPService.sendMail(from, new String[0], bcc.toArray(new String[0]), subjectActualizarAula, getBody(params, bodyActualizarAula), null);
				
	}
	
	@Override
	public void notificarCambioProfesor(Profesor profesorAnterior, Aula aula) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("idAula", aula.getIdString());
		params.put("fecha", DateUtils.toString(aula.getDia()));
		params.put("hora", aula.getHora().toString() + " Hs.");
		params.put("materia", aula.getMateria().getDescripcion() + aula.getMateria().getNivel().getDescripcion());
		params.put("profesor", profesorAnterior.getNombreApellido());
		mailSenderSMTPService.sendMail(from, profesorAnterior.getEmail(), subjectCambioProfesorAnterior, getBody(params, bodyCambioProfesorAnterior), null);

		params.put("profesor", aula.getProfesor().getNombreApellido());
		mailSenderSMTPService.sendMail(from, aula.getProfesor().getEmail(), subjectCambioProfesorNuevo, getBody(params, bodyCambioProfesorNuevo), null);
	}
	
	@Resource(name = "mailSenderSMTPService")
	public void setMailSenderSMTPService(MailSenderSMTPService mailSenderSMTPService) {
		this.mailSenderSMTPService = mailSenderSMTPService;
	}

	
	
	public void setFrom(String from) {
		this.from = from;
	}

	

}
