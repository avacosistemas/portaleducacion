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
import ar.com.avaco.educacion.ws.dto.AlumnoPerfilDTO;
import ar.com.avaco.educacion.ws.dto.AulaAlumnoPortalDTO;
import ar.com.avaco.educacion.ws.dto.ComentarioDTO;
import ar.com.avaco.educacion.ws.dto.PreguntaDTO;
import ar.com.avaco.educacion.ws.dto.PuntuacionDTO;
import ar.com.avaco.educacion.ws.service.AlumnoPerfilEPService;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class AlumnoPerfilRestController extends AbstractDTORestController<AlumnoPerfilDTO, Long, AlumnoPerfilEPService> {
	
	@RequestMapping(value = "/alumno/miperfil/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getAlumno(@PathVariable("id") Long id) throws Exception {
		Cliente cliente = (Cliente)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONResponse response = new JSONResponse();
		if (cliente.getId().equals(id) && cliente.getTipoCliente().equals(TipoCliente.ALUMNO)) {
			AlumnoPerfilDTO alumno = this.service.getAlumno(cliente.getId());
			response.setData(alumno);
			response.setStatus(JSONResponse.OK);
		} else {
			response.setData("No es alumno o no coinciden los parametros");
			response.setStatus(JSONResponse.ERROR);
		}
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/alumno/miperfil/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> updateAlumno(@PathVariable("id") Long id, @RequestBody(required = true) AlumnoPerfilDTO perfil) throws Exception {
		Cliente cliente = (Cliente)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONResponse response = new JSONResponse();
		if (cliente.getId().equals(id) && cliente.getTipoCliente().equals(TipoCliente.ALUMNO)) {
			this.service.updateAlumno(id, perfil);
			response.setData(perfil);
			response.setStatus(JSONResponse.OK);
		} else {
			response.setData("No es alumno o no coinciden los parametros");
			response.setStatus(JSONResponse.ERROR);
		}
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/alumno/preguntar/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> preguntar(@PathVariable("id") Long id, @RequestBody(required = true) PreguntaDTO preguntaDTO) throws Exception {
		Cliente cliente = (Cliente)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONResponse response = new JSONResponse();
		if (cliente.getId().equals(id) && cliente.getTipoCliente().equals(TipoCliente.ALUMNO)) {
			this.service.preguntar(preguntaDTO, id);
			response.setStatus(JSONResponse.OK);
		} else {
			response.setData("No es alumno o no coinciden los parametros");
			response.setStatus(JSONResponse.ERROR);
		}
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/alumno/misclases/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listarMisClases(@PathVariable("id") Long id) throws Exception {
		Cliente cliente = (Cliente)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONResponse response = new JSONResponse();
		if (cliente.getId().equals(id) && cliente.getTipoCliente().equals(TipoCliente.ALUMNO)) {
			List<AulaAlumnoPortalDTO> aulas =  this.service.listarMisAulas(id);
			response.setData(aulas);
			response.setStatus(JSONResponse.OK);
		} else {
			response.setData("No es alumno o no coinciden los parametros");
			response.setStatus(JSONResponse.ERROR);
		}
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/alumno/{id}/detalleclase/{idClase}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getMiClases(@PathVariable("id") Long id, @PathVariable("idClase") Long idClase) throws Exception {
		Cliente cliente = (Cliente)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONResponse response = new JSONResponse();
		if (cliente.getId().equals(id) && cliente.getTipoCliente().equals(TipoCliente.ALUMNO)) {
			AulaAlumnoPortalDTO aulaAlumnoDTO = this.service.getAula(idClase);
			response.setData(aulaAlumnoDTO);
			response.setStatus(JSONResponse.OK);
		} else {
			response.setData("No es alumno o no coinciden los parametros");
			response.setStatus(JSONResponse.ERROR);
		}
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/alumno/{id}/anotaciones/{idClase}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getComentarios(@PathVariable("id") Long id, @PathVariable("idClase") Long idClase) throws Exception {
		Cliente cliente = (Cliente)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONResponse response = new JSONResponse();
		if (cliente.getId().equals(id) && cliente.getTipoCliente().equals(TipoCliente.ALUMNO)) {
			List<ComentarioDTO> anotaciones = this.service.getComentariosAula(idClase);
			response.setData(anotaciones);
			response.setStatus(JSONResponse.OK);
		} else {
			response.setData("No es alumno o no coinciden los parametros");
			response.setStatus(JSONResponse.ERROR);
		}
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/alumno/{id}/anotaciones/{idClase}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> agregarComentario(@PathVariable("id") Long id, @PathVariable("idClase") Long idClase, @RequestBody ComentarioDTO comentario) throws Exception {
		Cliente cliente = (Cliente)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONResponse response = new JSONResponse();
		if (cliente.getId().equals(id) && cliente.getTipoCliente().equals(TipoCliente.ALUMNO)) {
			this.service.agregarComentarioAula(comentario, idClase, cliente.getNombreApellido());
			response.setStatus(JSONResponse.OK);
		} else {
			response.setData("No es alumno o no coinciden los parametros");
			response.setStatus(JSONResponse.ERROR);
		}
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/alumno/{id}/puntuacion/{idAula}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> calificarClase(@PathVariable("id") Long id, @PathVariable("idAula") Long idAula, 
			@RequestBody PuntuacionDTO puntuacionDTO) throws Exception {
		Cliente cliente = (Cliente)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONResponse response = new JSONResponse();
		if (cliente.getId().equals(id) && cliente.getTipoCliente().equals(TipoCliente.ALUMNO)) {
			this.service.calificarAula(idAula, id, puntuacionDTO);
			response.setStatus(JSONResponse.OK);
		} else {
			response.setData("No es alumno o no coinciden los parametros");
			response.setStatus(JSONResponse.ERROR);
		}
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	//Service
	@Resource(name = "alumnoPerfilEPService")
	public void setService(AlumnoPerfilEPService alumnoEPService) {
		super.service = alumnoEPService;
	}

}
