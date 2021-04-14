package ar.com.avaco.educacion.ws.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.AlumnoPerfilDTO;
import ar.com.avaco.educacion.ws.dto.AulaAbiertaInstitucionDTO;
import ar.com.avaco.educacion.ws.dto.AulaAlumnoPortalDTO;
import ar.com.avaco.educacion.ws.dto.ComentarioDTO;
import ar.com.avaco.educacion.ws.dto.PreguntaDTO;
import ar.com.avaco.educacion.ws.dto.PuntuacionDTO;
import ar.com.avaco.educacion.ws.service.AlumnoPerfilEPService;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;
import ar.com.avaco.ws.rest.security.util.ClienteUtils;

@RestController
public class AlumnoPerfilRestController extends AbstractDTORestController<AlumnoPerfilDTO, Long, AlumnoPerfilEPService> {
	
	@RequestMapping(value = "/alumno/miperfil/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getAlumno(@PathVariable("id") Long id) throws Exception {
		Long idCliente = ClienteUtils.getClienteLogueadoId();
		JSONResponse response = new JSONResponse();
		if (idCliente.equals(id)) {
			AlumnoPerfilDTO alumno = this.service.getAlumno(idCliente);
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
		Long idCliente = ClienteUtils.getClienteLogueadoId();
		JSONResponse response = new JSONResponse();
		if (idCliente.equals(id)) {
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
		Long idCliente = ClienteUtils.getClienteLogueadoId();
		JSONResponse response = new JSONResponse();
		if (idCliente.equals(id)) {
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
		Long idCliente = ClienteUtils.getClienteLogueadoId();
		JSONResponse response = new JSONResponse();
		if (idCliente.equals(id)) {
			List<AulaAlumnoPortalDTO> aulas =  this.service.listarMisAulas(id);
			response.setData(aulas);
			response.setStatus(JSONResponse.OK);
		} else {
			response.setData("No es alumno o no coinciden los parametros");
			response.setStatus(JSONResponse.ERROR);
		}
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/alumno/aulasabiertas/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listarAulasAbiertas(@PathVariable("id") Long id) throws Exception {
		Long idCliente = ClienteUtils.getClienteLogueadoId();
		JSONResponse response = new JSONResponse();
		if (idCliente.equals(id)) {
			List<AulaAlumnoPortalDTO> aulas =  this.service.listarMisAulas(id);
			response.setData(aulas);
			response.setStatus(JSONResponse.OK);
		} else {
			response.setData("No es alumno o no coinciden los parametros");
			response.setStatus(JSONResponse.ERROR);
		}
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/alumno/aulasabiertasinstitucion/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listarAulasAbiertasInstitucion(@PathVariable("id") Long id) throws Exception {
		Long idCliente = ClienteUtils.getClienteLogueadoId();
		JSONResponse response = new JSONResponse();
		if (idCliente.equals(id)) {
			List<AulaAbiertaInstitucionDTO> aulas =  this.service.listarAulasAbiertesMiInstitucion(id);
			response.setData(aulas);
			response.setStatus(JSONResponse.OK);
		} else {
			response.setData("No es alumno o no coinciden los parametros");
			response.setStatus(JSONResponse.ERROR);
		}
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/alumno/{idAlumno}/detalleclase/{idClase}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getMiClases(@PathVariable("idAlumno") Long idAlumno, @PathVariable("idClase") Long idClase) throws Exception {
		Long idCliente = ClienteUtils.getClienteLogueadoId();
		JSONResponse response = new JSONResponse();
		if (idCliente.equals(idAlumno)) {
			AulaAlumnoPortalDTO aulaAlumnoDTO = this.service.getAula(idClase, idAlumno);
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
		Long idCliente = ClienteUtils.getClienteLogueadoId();
		JSONResponse response = new JSONResponse();
		if (idCliente.equals(id)) {
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
		Long idCliente = ClienteUtils.getClienteLogueadoId();
		AlumnoPerfilDTO alumnoPerfilDTO = this.service.getAlumno(idCliente);
		JSONResponse response = new JSONResponse();
		if (idCliente.equals(id)) {
			this.service.agregarComentarioAula(comentario, idClase, alumnoPerfilDTO.getNombreApellido());
			response.setStatus(JSONResponse.OK);
		} else {
			response.setData("No es alumno o no coinciden los parametros");
			response.setStatus(JSONResponse.ERROR);
		}
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/alumno/{id}/puntuacion/{idAula}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> calificarClase(@PathVariable("id") Long id, @PathVariable("idAula") Long idAula, 
			@RequestBody PuntuacionDTO puntuacionDTO) throws BusinessException  {
		Long idCliente = ClienteUtils.getClienteLogueadoId();
		JSONResponse response = new JSONResponse();
		if (idCliente.equals(id)) {
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
