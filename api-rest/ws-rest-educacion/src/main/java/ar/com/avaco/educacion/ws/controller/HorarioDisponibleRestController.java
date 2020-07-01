package ar.com.avaco.educacion.ws.controller;

import java.util.List;
import java.util.Map;

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
import ar.com.avaco.educacion.ws.dto.HorarioDisponibleDTO;
import ar.com.avaco.educacion.ws.dto.HorarioDisponibleFullDTO;
import ar.com.avaco.educacion.ws.service.HorarioDisponibleEPService;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class HorarioDisponibleRestController extends AbstractDTORestController<HorarioDisponibleDTO, Long, HorarioDisponibleEPService> {

	@RequestMapping(value = "/horariosdisponibles/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getProfesor(@PathVariable("id") Long idProfesor) throws Exception {
		List<HorarioDisponibleDTO> horariosDisponibles = this.service.listHorariosDispProfesor(idProfesor);
		JSONResponse response = new JSONResponse();
		response.setData(horariosDisponibles);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/horariosdisponibles/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listHorariosDisponibles() {
		return super.list();
	}
	
	@RequestMapping(value = "/horariosdisponibles/", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> addHorariosDisponibles(@RequestBody HorarioDisponibleFullDTO horariosDisp) throws BusinessException {
		List<HorarioDisponibleDTO> horariosDisponibles = service.addHorarioDisponible(horariosDisp);
		JSONResponse response = new JSONResponse();
		response.setData(horariosDisponibles);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/horariosdisponibles/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JSONResponse> update(@PathVariable("id") Long id, @RequestBody HorarioDisponibleDTO horariosDisp) throws BusinessException {
		return null;
	}
	
	@RequestMapping(value = "/horariosdisponibles/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<JSONResponse> delete(@PathVariable("id") Long id) throws BusinessException {
		return super.delete(id);
	}

	//Service
	@Resource(name = "horarioDisponibleEPService")
	public void setService(HorarioDisponibleEPService horarioDisponibleEPService) {
		super.service = horarioDisponibleEPService;
	}

}
