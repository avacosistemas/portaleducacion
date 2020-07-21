package ar.com.avaco.educacion.service.notificacion;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import ar.com.avaco.arc.core.service.MailSenderSMTPService;
import ar.com.avaco.educacion.domain.entities.cliente.Cliente;

@Service("notificacionService")
public class NotificacionServiceImpl implements NotificacionService {

	private static final String CLASSPATH_RESOURCE_LOADER_CLASS = "classpath.resource.loader.class";
	private static final String CLASSPATH = "classpath";
	private static final String ISO_8859_1 = "ISO-8859-1";

	private VelocityEngine ve;

	private MailSenderSMTPService mailSenderSMTPService;

//	@Value("${prop.notificaciones.from}")
	private String from;

//	@Value("${prop.subject.oferta.inversion}")
	private String subjectOfertaInversion;

//	@Value("${prop.body.oferta.inversion}")
	private String bodyOfertaInverion;

//	@Value("${prop.subject.subasta.aceptada}")
	private String subjectSubastaAceptada;

//	@Value("${prop.body.subasta.aceptada}")
	private String bodySubastaAceptada;

//	@Value("${prop.subject.reseteo.password.cliente}")
	private String subjectResetoPasswordCliente;

//	@Value("${prop.body.reseteo.password.cliente}")
	private String bodyResetoPasswordCliente;

//	@Value("${prop.subject.registro.cliente.nuevo.password}")
	private String subjectRegistroClienteNuevoPassword;

//	@Value("${prop.body.registro.cliente.nuevo.password}")
	private String bodyRegistroClienteNuevoPassword;

//	@Value("${prop.header.general}")
	private String headerGeneral;

//	@Value("${prop.footer.general}")
	private String footerGeneral;

//	@Value("${prop.subject.cobro.mora.cuota.prestamo}")
	private String subjectCobroMoraCuotaPrestamo;

//	@Value("${prop.body.cobro.mora.cuota.prestamo}")
	private String bodyCobroMoraCuotaPrestamo;

//	@Value("${prop.subject.cobro.mora.parcial.cuota.prestamo}")
	private String subjectCobroMoraParcialCuotaPrestamo;

//	@Value("${prop.body.cobro.mora.parcial.cuota.prestamo}")
	private String bodyCobroMoraParcialCuotaPrestamo;

//	@Value("${prop.subject.aviso.cuota.mora}")
	private String subjectAvisoCuotaMora;

//	@Value("${prop.body.aviso.cuota.mora}")
	private String bodyAvisoCuotaMora;

//	@Value("${prop.subject.aviso.cuota.mora.cada.5.dias}")
	private String subjectAvisoCuotaMoraCada5Dias;

//	@Value("${prop.body.aviso.cuota.mora.cada.5.dias}")
	private String bodyAvisoCuotaMoraCada5Dias;

//	@Value("${prop.subject.pago.cuota.mora.inversor.prestamo}")
	private String subjectPagoCuotaMoraInversorPretamo;

//	@Value("${prop.body.pago.cuota.mora.inversor.prestamo}")
	private String bodyPagoCuotaMoraInversorPretamo;

	private String subjectPagoCuotaMoraParcialInversorPretamo;

//	@Value("${prop.body.pago.cuota.mora.parcial.inversor.prestamo}")
	private String bodyPagoCuotaMoraParcialInversorPretamo;

//	@Value("${prop.subject.aviso.cuota.vencida.sin.pago}")
	private String subjectAvisoCuotaVencidaSinPago;

//	@Value("${prop.body.aviso.cuota.vencida.sin.pago}")
	private String bodyAvisoCuotaVencidaSinPago;

//	@Value("${prop.subject.cobro.cuota.prestamo}")
	private String subjectCobroCuotaPrestamo;

//	@Value("${prop.body.cobro.cuota.prestamo}")
	private String bodyCobroCuotaPrestamo;

//	@Value("${prop.subject.cobro.parcial.cuota.prestamo}")
	private String subjectCobroParcialCuotaPrestamo;

//	@Value("${prop.body.cobro.parcial.cuota.prestamo}")
	private String bodyCobroParcialCuotaPrestamo;

//	@Value("${prop.subject.pago.cuota.inversor.prestamo}")
	private String subjectPagoCuotaInversorPrestamo;

//	@Value("${prop.body.pago.cuota.inversor.prestamo}")
	private String bodyPagoCuotaInversorPrestamo;

//	@Value("${prop.subject.pago.parcial.cuota.inversor.prestamo}")
	private String subjectPagoParcialCuotaInversorPrestamo;

//	@Value("${prop.body.pago.parcial.cuota.inversor.prestamo}")
	private String bodyPagoParcialCuotaInversorPrestamo;

//	@Value("${prop.subject.prestamo.finalziado}")
	private String subjectPrestamoFinalizado;

//	@Value("${prop.body.prestamo.finalziado}")
	private String bodyPrestamoFinalizado;

//	@Value("${prop.subject.ajuste.oferta.prestamo}")
	private String subjectAjusteDeOfertaPrestamo;

//	@Value("${prop.body.ajuste.oferta.prestamo}")
	private String bodyAjusteDeOfertaPrestamo;

//	@Value("${prop.subject.oferta.superada}")
	private String subjectOfertaSuperada;

//	@Value("${prop.body.oferta.superada}")
	private String bodyOfertaSuperada;

//	@Value("${prop.subject.solicitante.fin.subasta.monto.suficiente}")
	private String subjectSolicitanteFinSubastaMontoSuficiente;

//	@Value("${prop.body.solicitante.fin.subasta.monto.suficiente}")
	private String bodySolicitanteFinSubastaMontoSuficiente;

//	@Value("${prop.subject.inversor.fin.subasta.monto.suficiente}")
	private String subjectInversorFinSubastaMontoSuficiente;

//	@Value("${prop.body.inversor.fin.subasta.monto.suficiente}")
	private String bodyInversorFinSubastaMontoSuficiente;

//	@Value("${prop.subject.solicitante.fin.subasta.monto.insuficiente}")
	private String subjectSolicitanteFinSubastaMontoInsuficiente;

//	@Value("${prop.body.solicitante.fin.subasta.monto.insuficiente}")
	private String bodySolicitanteFinSubastaMontoInsuficiente;

//	@Value("${prop.subject.inversor.fin.subasta.monto.insuficiente}")
	private String subjectInversorFinSubastaMontoInsuficiente;

//	@Value("${prop.body.inversor.fin.subasta.monto.insuficiente}")
	private String bodyInversorFinSubastaMontoInsuficiente;

//	@Value("${prop.subject.oferta.compra.cartera}")
	private String subjectOfertaCompraCartera;

//	@Value("${prop.body.oferta.compra.cartera}")
	private String bodyOfertaCompraCartera;

//	@Value("${prop.subject.oferta.compra.cartera.superada}")
	private String subjectOfertaCompraCarteraSuperada;

//	@Value("${prop.body.oferta.compra.cartera.superada}")
	private String bodyOfertaCompraCarteraSuperada;

//	@Value("${prop.subject.pago.venta.cartera.cobro.comision}")
	private String subjectPagoVentaCarteraCobroComision;

//	@Value("${prop.body.pago.venta.cartera.cobro.comision}")
	private String bodyPagoVentaCarteraCobroComision;

//	@Value("${prop.subject.solicitud.venta.cartera}")
	private String subjectSolicitudVentaCartera;

//	@Value("${prop.body.solicitud.venta.cartera}")
	private String bodySolicitudVentaCartera;

//	@Value("${prop.subject.venta.cartera}")
	private String subjectVentaCartera;

//	@Value("${prop.body.venta.cartera}")
	private String bodyVentaCartera;

//	@Value("${prop.subject.venta.cartera.sin.ofertas}")
	private String subjectVentaCarteraSinOfertas;

//	@Value("${prop.body.venta.cartera.sin.ofertas}")
	private String bodyVentaCarteraSinOfertas;

	public NotificacionServiceImpl() {
		ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, CLASSPATH);
		ve.setProperty(CLASSPATH_RESOURCE_LOADER_CLASS, ClasspathResourceLoader.class.getName());
		ve.init();
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
		params.put("cliente", cliente.getRazonSocialNombreApellido());
		params.put("usuario", cliente.getUsername());
		params.put("password", tmppass);
		mailSenderSMTPService.sendMail(from, cliente.getEmail(), subjectResetoPasswordCliente,
				getBody(params, bodyResetoPasswordCliente), null);
	}

	@Async
	@Override
	public void notificarRegistroClienteNuevoPassword(Cliente cliente, String tmpass) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("cliente", cliente.getRazonSocialNombreApellido());
		params.put("usuario", cliente.getUsername());
		params.put("password", tmpass);
		mailSenderSMTPService.sendMail(from, cliente.getEmail(), subjectRegistroClienteNuevoPassword,
				getBody(params, bodyRegistroClienteNuevoPassword), null);
	}

	
	@Resource(name = "mailSenderSMTPService")
	public void setMailSenderSMTPService(MailSenderSMTPService mailSenderSMTPService) {
		this.mailSenderSMTPService = mailSenderSMTPService;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}
