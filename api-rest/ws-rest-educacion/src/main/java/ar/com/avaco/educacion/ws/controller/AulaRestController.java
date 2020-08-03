package ar.com.avaco.educacion.ws.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.AulaDTO;
import ar.com.avaco.educacion.ws.dto.AulaListadoDTO;
import ar.com.avaco.educacion.ws.dto.AulaProfesorDTO;
import ar.com.avaco.educacion.ws.dto.CompraAulaDTO;
import ar.com.avaco.educacion.ws.dto.EventoClaseDTO;
import ar.com.avaco.educacion.ws.service.AulaEPService;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class AulaRestController extends AbstractDTORestController<AulaDTO, Long, AulaEPService> {

	
	@RequestMapping(value = "/aulas/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listAulas() throws Exception  {
		List<AulaListadoDTO> listAulas = this.service.listAulas();
    	JSONResponse response = new JSONResponse();
		response.setData(listAulas);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/aulas/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> get(@PathVariable("id") Long id) throws BusinessException {
		AulaDTO aulaDTO=service.getAula(id);
		JSONResponse response = new JSONResponse();
		response.setData(aulaDTO);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/aulas/", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> create(@RequestBody AulaDTO aula) throws BusinessException {
		AulaDTO newAulaDto = service.crearAula(aula);
		JSONResponse response = new JSONResponse();
		response.setData(newAulaDto);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/aulas/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<JSONResponse> delete(@PathVariable("id") Long id) throws BusinessException {
		return super.delete(id);
	}
	
	@RequestMapping(value = "/aulas/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JSONResponse> update(@PathVariable("id") Long id, @RequestBody AulaDTO aulaDTO) throws BusinessException {
		AulaDTO aulaDtoToUpdate = service.updateAula(id, aulaDTO);
		JSONResponse response = new JSONResponse();
		response.setData(aulaDtoToUpdate);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/comprarAula/", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> comprarClase(@RequestBody CompraAulaDTO compraAulaDTO) throws BusinessException {
		AulaDTO newAulaDto = service.comprarClase(compraAulaDTO);
		JSONResponse response = new JSONResponse();
		response.setData(newAulaDto);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/event/aula/registroEventos/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody ResponseEntity<JSONResponse> registrarEventoClase(@PathVariable("id") Long id,EventoClaseDTO eventoClaseDTO, HttpServletRequest request) throws BusinessException {
		eventoClaseDTO.setFromIP(request.getRemoteAddr());
		System.out.println(eventoClaseDTO.getEvent());
		service.registrarEventoClase(id, eventoClaseDTO);
		JSONResponse response = new JSONResponse();
		response.setData("ACK");
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);	
	}
	
	/**
	 * TODO MOVER A WS-EDUCACION-CLIENTE
	 * @param aulaProfesorDTO
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/aula/abrirClase/", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> abrirClase(@RequestBody AulaProfesorDTO aulaProfesorDTO) throws BusinessException {
		AulaDTO newAulaDto = service.abrirClase(aulaProfesorDTO);
		JSONResponse response = new JSONResponse();
		response.setData(newAulaDto);
		response.setData("ACK");
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);	
	}
	
	
	//Service
	@Resource(name = "aulaEPService")
	public void setService(AulaEPService aulaEPService) {
		super.service = aulaEPService;
	}

}
