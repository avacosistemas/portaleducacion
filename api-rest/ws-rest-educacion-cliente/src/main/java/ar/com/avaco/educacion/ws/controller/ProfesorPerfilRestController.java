package ar.com.avaco.educacion.ws.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.educacion.domain.entities.cliente.Cliente;
import ar.com.avaco.educacion.domain.entities.cliente.TipoCliente;
import ar.com.avaco.educacion.ws.dto.ComentarioDTO;
import ar.com.avaco.educacion.ws.dto.AulaProfesorPortalDTO;
import ar.com.avaco.educacion.ws.dto.CalificacionDTO;
import ar.com.avaco.educacion.ws.dto.PreguntaRespuestaDTO;
import ar.com.avaco.educacion.ws.dto.ProfesorPerfilDTO;
import ar.com.avaco.educacion.ws.dto.RespuestaDTO;
import ar.com.avaco.educacion.ws.service.ProfesorPerfilEPService;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class ProfesorPerfilRestController extends AbstractDTORestController<ProfesorPerfilDTO, Long, ProfesorPerfilEPService> {
	
	@RequestMapping(value = "/profesor/miperfil/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getProfesor(@PathVariable("id") Long id) throws Exception {
		Cliente cliente = (Cliente)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONResponse response = new JSONResponse();
		if (cliente.getId().equals(id) && cliente.getTipoCliente().equals(TipoCliente.PROFESOR)) {
			ProfesorPerfilDTO profesor = this.service.getProfesor(cliente.getId());
			response.setData(profesor);
			response.setStatus(JSONResponse.OK);
		} else {
			response.setData("No es profesor o no coinciden los parametros");
			response.setStatus(JSONResponse.ERROR);
		}
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/profesor/miperfil/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> updateProfesor(@PathVariable("id") Long id, @RequestBody(required = true) ProfesorPerfilDTO perfil) throws Exception {
		Cliente cliente = (Cliente)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONResponse response = new JSONResponse();
		if (cliente.getId().equals(id) && cliente.getTipoCliente().equals(TipoCliente.PROFESOR)) {
			this.service.updateProfesor(id, perfil);
			response.setData(perfil);
			response.setStatus(JSONResponse.OK);
		} else {
			response.setData("No es profesor o no coinciden los parametros");
			response.setStatus(JSONResponse.ERROR);
		}
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/profesor/preguntas/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getProfesorPreguntas(@PathVariable("id") Long id) throws Exception {
		Cliente cliente = (Cliente)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONResponse response = new JSONResponse();
		if (cliente.getId().equals(id) && cliente.getTipoCliente().equals(TipoCliente.PROFESOR)) {
			List<PreguntaRespuestaDTO> pr = this.service.listPreguntaRespuestas(id);
			response.setData(pr);
			response.setStatus(JSONResponse.OK);
		} else {
			response.setData("No es profesor o no coinciden los parametros");
			response.setStatus(JSONResponse.ERROR);
		}
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/profesor/responder/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> responderPregunta(@PathVariable("id") Long id, @RequestBody(required = true) RespuestaDTO respuestaDTO) throws Exception {
		Cliente cliente = (Cliente)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONResponse response = new JSONResponse();
		if (cliente.getId().equals(id) && cliente.getTipoCliente().equals(TipoCliente.PROFESOR)) {
			this.service.responderPregunta(respuestaDTO);
			response.setStatus(JSONResponse.OK);
		} else {
			response.setData("No es profesor o no coinciden los parametros");
			response.setStatus(JSONResponse.ERROR);
		}
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/profesor/misclases/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listarMisClases(@PathVariable("id") Long id) throws Exception {
		Cliente cliente = (Cliente)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONResponse response = new JSONResponse();
		if (cliente.getId().equals(id) && cliente.getTipoCliente().equals(TipoCliente.PROFESOR)) {
			List<AulaProfesorPortalDTO> aulas =  this.service.listarMisAulas(id);
			response.setData(aulas);
			response.setStatus(JSONResponse.OK);
		} else {
			response.setData("No es profesor o no coinciden los parametros");
			response.setStatus(JSONResponse.ERROR);
		}
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/profesor/{id}/detalleclase/{idClase}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getMiClases(@PathVariable("id") Long id, @PathVariable("idClase") Long idClase) throws Exception {
		Cliente cliente = (Cliente)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONResponse response = new JSONResponse();
		if (cliente.getId().equals(id) && cliente.getTipoCliente().equals(TipoCliente.PROFESOR)) {
			AulaProfesorPortalDTO aulaProfesorDTO = this.service.getAula(idClase);
			response.setData(aulaProfesorDTO);
			response.setStatus(JSONResponse.OK);
		} else {
			response.setData("No es profesor o no coinciden los parametros");
			response.setStatus(JSONResponse.ERROR);
		}
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/profesor/{id}/anotaciones/{idClase}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getComentarios(@PathVariable("id") Long id, @PathVariable("idClase") Long idClase) throws Exception {
		Cliente cliente = (Cliente)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONResponse response = new JSONResponse();
		if (cliente.getId().equals(id) && cliente.getTipoCliente().equals(TipoCliente.PROFESOR)) {
			List<ComentarioDTO> anotaciones = this.service.getComentariosAula(idClase);
			response.setData(anotaciones);
			response.setStatus(JSONResponse.OK);
		} else {
			response.setData("No es profesor o no coinciden los parametros");
			response.setStatus(JSONResponse.ERROR);
		}
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/profesor/{id}/anotaciones/{idClase}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> agregarComentario(@PathVariable("id") Long id, @PathVariable("idClase") Long idClase, @RequestBody ComentarioDTO comentario) throws Exception {
		Cliente cliente = (Cliente)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONResponse response = new JSONResponse();
		if (cliente.getId().equals(id) && cliente.getTipoCliente().equals(TipoCliente.PROFESOR)) {
			this.service.agregarComentarioAula(comentario, idClase, cliente.getNombreApellido());
			response.setStatus(JSONResponse.OK);
		} else {
			response.setData("No es profesor o no coinciden los parametros");
			response.setStatus(JSONResponse.ERROR);
		}
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/profesor/calificaciones/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getCalificaciones(@PathVariable("id") Long id) throws Exception {
		Cliente cliente = (Cliente)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONResponse response = new JSONResponse();
		if (cliente.getId().equals(id) && cliente.getTipoCliente().equals(TipoCliente.PROFESOR)) {
			List<CalificacionDTO> calificaciones = this.service.getCalificaciones(id);
			response.setData(calificaciones);
			response.setStatus(JSONResponse.OK);
		} else {
			response.setData("No es profesor o no coinciden los parametros");
			response.setStatus(JSONResponse.ERROR);
		}
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/profesor/{id}/alumnos/{idClase}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getAlumnosAula(@PathVariable("id") Long id, @PathVariable("idClase") Long idClase) throws Exception {
		Cliente cliente = (Cliente)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONResponse response = new JSONResponse();
		if (cliente.getId().equals(id) && cliente.getTipoCliente().equals(TipoCliente.PROFESOR)) {
			List<CalificacionDTO> calificaciones = this.service.getAlumnos(idClase);
			response.setData(calificaciones);
			response.setStatus(JSONResponse.OK);
		} else {
			response.setData("No es profesor o no coinciden los parametros");
			response.setStatus(JSONResponse.ERROR);
		}
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	
	
	//Service
	@Resource(name = "profesorPerfilEPService")
	public void setService(ProfesorPerfilEPService profesorEPService) {
		super.service = profesorEPService;
	}

}
