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
import org.springframework.web.bind.annotation.RequestParam;
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
	
	//Service
	@Resource(name = "horarioDisponibleEPService")
	public void setService(HorarioDisponibleEPService horarioDisponibleEPService) {
		super.service = horarioDisponibleEPService;
	}

}
