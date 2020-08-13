package ar.com.avaco.educacion.ws.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.ws.dto.ProfesorDTO;
import ar.com.avaco.educacion.ws.service.ProfesorEPService;
import ar.com.avaco.ws.rest.controller.AbstractDTORestController;
import ar.com.avaco.ws.rest.dto.JSONResponse;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;

@RestController
public class ProfesorRestController extends AbstractDTORestController<ProfesorDTO, Long, ProfesorEPService> {

	
	@RequestMapping(value = "/profesores", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listProfesores() throws Exception  {
		List<ProfesorDTO> listProfesores = this.service.listProfesores();
    	JSONResponse response = new JSONResponse();
		response.setData(listProfesores);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/profesores/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getProfesor(@PathVariable("id") Long id) throws Exception {
		ProfesorDTO profesor = this.service.getProfesor(id);
		JSONResponse response = new JSONResponse();
		response.setData(profesor);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/profesores/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JSONResponse> updateProfesor(@PathVariable("id") Long id, @RequestBody ProfesorDTO profesor) throws BusinessException {
		ProfesorDTO updateProfesor = service.updateProfesor(id, profesor);
		JSONResponse response = new JSONResponse();
		response.setData(updateProfesor);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/profesores/", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> create(@RequestBody ProfesorDTO profesor) throws BusinessException {
		ProfesorDTO newProfesorDto = service.createProfesor(profesor);
		JSONResponse response = new JSONResponse();
		response.setData(newProfesorDto);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}	
		
	@RequestMapping(value = "/profesores/habilitar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JSONResponse> habilitarProfesor(@PathVariable("id") Long id) throws BusinessException {
		ProfesorDTO updateProfesor = service.bloquearHabilitarProfesor(id, false);
		JSONResponse response = new JSONResponse();
		response.setData(updateProfesor);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/profesores/bloquear/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JSONResponse> bloquearProfesor(@PathVariable("id") Long id) throws BusinessException {
		ProfesorDTO updateProfesor = service.bloquearHabilitarProfesor(id, true);
		JSONResponse response = new JSONResponse();
		response.setData(updateProfesor);
		response.setStatus(JSONResponse.OK);	
        return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/profesores/uploadFotoPerfil/", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> updateFotoPerfil(@RequestParam("id") Long id, @RequestParam("foto") MultipartFile foto) throws BusinessException {
		
		JSONResponse response = new JSONResponse();
		String message = "";
	    try {
	      ProfesorDTO newProfesorDto = service.updateFotoPerfil(id, foto);
	      response.setData(newProfesorDto);
	      response.setStatus(JSONResponse.OK);	
	      return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);

	    } catch (Exception e) {
	      message = "No se puedo subir la foto de perfil: " + foto.getOriginalFilename() + "!";
	      response.setData(message);
	      response.setStatus(JSONResponse.OK);	
	
	      return new ResponseEntity<JSONResponse>(response, HttpStatus.EXPECTATION_FAILED);
	    }

	}

	@RequestMapping(value = "/profesores/downloadFotoPerfil/{id}", method = RequestMethod.GET)
	public ResponseEntity<org.springframework.core.io.Resource> downloadFotoPerfil(@PathVariable("id") Long id) throws BusinessException, IOException {
		
		byte[] foto = service.downloadFotoPerfil(id);
  
        InputStream is = new BufferedInputStream(new ByteArrayInputStream(foto));
        
        InputStreamResource isr = new InputStreamResource(is);
        
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(isr);
        
	}
	
	@RequestMapping(value = "/profesores/downloadFotoPerfil2/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> downloadFotoPerfil2(@PathVariable("id") Long id) throws BusinessException, IOException {
	    HttpHeaders headers = new HttpHeaders();
	    
	    byte[] foto = service.downloadFotoPerfil(id);
	 
	    headers.setCacheControl(CacheControl.noCache().getHeaderValue());
	    
	    ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(foto, headers, HttpStatus.OK);
	    return responseEntity;
	}
	
	
	
	
	@RequestMapping(value = "/profesores/downloadFotoPerfil3/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<org.springframework.core.io.Resource> downloadFotoPerfil3(@PathVariable("id") Long id) throws BusinessException, IOException {
		 HttpHeaders headers = new HttpHeaders();
		 byte[] foto = service.downloadFotoPerfil(id);
		
		ByteArrayResource resource = new ByteArrayResource(foto);
	    return ResponseEntity.ok()
	            .headers(headers)
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .body(resource);
	}
	
	//Service
	@Resource(name = "profesorEPService")
	public void setService(ProfesorEPService profesorEPService) {
		super.service = profesorEPService;
	}


	
}
