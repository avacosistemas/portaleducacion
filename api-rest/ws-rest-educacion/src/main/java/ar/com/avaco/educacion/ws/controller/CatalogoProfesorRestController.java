package ar.com.avaco.educacion.ws.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.OrdenCatalogo;
import ar.com.avaco.educacion.ws.dto.CatalogoProfesorDTO;
import ar.com.avaco.educacion.ws.dto.ConsultaDTO;
import ar.com.avaco.educacion.ws.dto.HorarioDisponibleDTO;
import ar.com.avaco.educacion.ws.dto.PreguntaRespuestaDTO;
import ar.com.avaco.educacion.ws.service.CatalogoProfesorEPService;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class CatalogoProfesorRestController {

	@Autowired
    @Qualifier("catalogoProfesorEPService")
	private CatalogoProfesorEPService service;

	@RequestMapping(value = "/catalogoDocente", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listCatalogoProfesor(@RequestParam("orden") OrdenCatalogo filtro, 
															 @RequestParam("idMateria") Long idMateria,
															 @RequestParam("idNivel") Integer idNivel) throws BusinessException {
		
		List<CatalogoProfesorDTO> listCatalogoProfesorDto = service.listCatalogoProfesor(filtro, idMateria, idNivel);
    	JSONResponse response = new JSONResponse();
		response.setData(listCatalogoProfesorDto);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/catalogoDocente/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getCatalogoProfesor(@PathVariable("id") Long idProfesor) throws BusinessException {
		CatalogoProfesorDTO catalogoProfesorDto = service.getCatalogoProfesor(idProfesor);
    	JSONResponse response = new JSONResponse();
		response.setData(catalogoProfesorDto);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/catalogoCalificacion/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getCatalogoCalificacion(@PathVariable("id") Long idProfesor) throws BusinessException {
		double promedioCalificacion = service.getCatalogoCalificacion(idProfesor);
    	JSONResponse response = new JSONResponse();
		response.setData(promedioCalificacion);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/catalogoHorario/profesor/{id}/fecha/{fecha}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getCatalogoHorario(@PathVariable("profesor") Long idProfesor, @PathVariable("fecha") LocalDate fecha) throws BusinessException {
		List<HorarioDisponibleDTO> catalogoHorariosDto = service.getCatalogoHorarios(fecha, idProfesor);
    	JSONResponse response = new JSONResponse();
		response.setData(catalogoHorariosDto);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/catalogoConsultas/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getCatalogoConsultas(@PathVariable("id") Long idProfesor) throws BusinessException {
		List<PreguntaRespuestaDTO> catalogoConsultasDto = service.getCatalogoConsulta(idProfesor);
    	JSONResponse response = new JSONResponse();
		response.setData(catalogoConsultasDto);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/catalogoConsultas/", method = RequestMethod.POST)
	@Deprecated
	public ResponseEntity<JSONResponse> create(@RequestBody ConsultaDTO consulta) throws BusinessException {
		ConsultaDTO newConsultaDto = service.createCatalogoConsulta(consulta);
		JSONResponse response = new JSONResponse();
		response.setData(newConsultaDto);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}


}
