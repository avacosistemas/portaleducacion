package ar.com.avaco.educacion.ws.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
import ar.com.avaco.educacion.ws.dto.MateriaDTO;
import ar.com.avaco.educacion.ws.service.MateriaEPService;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class MateriaRestController extends AbstractDTORestController<MateriaDTO, Long, MateriaEPService> {

	@RequestMapping(value = "/materias/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> list(@RequestParam(value= "descripcion", required= false) String descripcion) {
		if(!StringUtils.isBlank(descripcion)) {			
			ResponseEntity<JSONResponse>resp = super.listFiltered(materia-> materia.getDescripcion()
					.toUpperCase().contains(descripcion.toUpperCase()));
			return resp;
		}		
		return super.list();		
	}
	

	@RequestMapping(value = "/materias/nivel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listByNivel(@PathVariable("id") Integer idNivel) throws BusinessException {
		List<MateriaDTO> listMaterias = this.service.listByNivel(idNivel);
    	JSONResponse response = new JSONResponse();
		response.setData(listMaterias);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	//Service
	@Resource(name = "materiaEPService")
	public void setService(MateriaEPService materiaEPService) {
		super.service = materiaEPService;
	}

}
