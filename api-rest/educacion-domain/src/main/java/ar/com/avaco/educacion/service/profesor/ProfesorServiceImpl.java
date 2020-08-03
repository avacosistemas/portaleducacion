package ar.com.avaco.educacion.service.profesor;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.educacion.domain.entities.Materia;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.repository.profesor.ProfesorRepository;
import ar.com.avaco.educacion.service.cliente.ClienteService;
import ar.com.avaco.educacion.service.materia.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Transactional
@Service("profesorService")
public class ProfesorServiceImpl extends NJBaseService<Long, Profesor, ProfesorRepository> implements ProfesorService {

	private MateriaService materiaService;
	private ClienteService clienteService;
	
	@Autowired
	public ProfesorServiceImpl(MateriaService materiaService, ClienteService clienteService) {
		this.materiaService = materiaService;
		this.clienteService = clienteService;
	}

	/**
	 * @see ProfesorService#getProfesor(Long)
	 */
	@Override
	public Profesor getProfesor(Long id) {
		return getRepository().getProfesor(id);
	}
	
	/**
	 * @see ProfesorService#getMateriaProfesor(Long)
	 */
	@Override
	public Profesor getMateriaProfesor(Long id) {
		return getRepository().findProfesorConMaterias(id);
	}
	
	/**
	 * @see ProfesorService#listProfesores()
	 */
	@Override
	public List<Profesor> listProfesores() {
		return getRepository().listProfesores();
	}
	
	/**
	 * @see ProfesorService#createMateriaProfesor(Long, Long)
	 */
	@Override
	public Profesor createMateriaProfesor(Long idMateria, Long idProfesor) throws BusinessException {

		Profesor profesor = this.getRepository().getOne(idProfesor);
		Materia materia = materiaService.get(idMateria);
		
		if(!profesor.getMaterias().contains(materia)) {
			
			profesor.addMateria(materia);
			materia.addProfesor(profesor);
			
			profesor = this.getRepository().save(profesor);

		} else {
			throw new BusinessException("El profesor ya tiene asociada la materia");
		}
		
		
		
		return profesor;
	}
	
	/**
	 * @see ProfesorService#removeMateriaProfesor(Long, Long)
	 */
	@Override
	public void removeMateriaProfesor(Long idMateria, Long idProfesor) {

		Profesor profesor = this.getRepository().getOne(idProfesor);
		Materia materia = materiaService.get(idMateria);
		profesor.removeMateria(materia);
		materia.removeProfesor(profesor);
		
		profesor = this.getRepository().save(profesor);

	}
	
	/**
	 * @see ProfesorService#createProfesor(Profesor)
	 */
	@Override
	public Profesor createProfesor(Profesor profesor) throws BusinessException {
		Profesor newProfesor = (Profesor) clienteService.registrarClientePersona(profesor);
		return newProfesor;
	}
	
	/**
	 * @see ProfesorService#updateProfesor(Profesor)
	 */
	@Override
	public Profesor updateProfesor(Profesor entity) throws BusinessException {

		Profesor profesor = (Profesor) this.clienteService.validaUpdateProfesorAlumno(entity);

		return this.getRepository().save(profesor);
	}

	/**
	 * @see ProfesorService#bloquearHabilitarProfesor(Profesor, boolean)
	 */
	@Override
	public Profesor bloquearHabilitarProfesor(Profesor entity, boolean bloquear) {
		Profesor profesor = this.get(entity.getId());
		profesor.setId(entity.getId());
		profesor.setBloqueado(bloquear);
		
		profesor = this.getRepository().save(profesor);

		return profesor;
	}
	
	@Override
	public Profesor uploadFotoPerfil(Long id, MultipartFile foto) {
		
		Profesor profesor = this.get(id);
		try {
			
		
			byte [] byteArr=foto.getBytes();
			//byte[] bFile = new byte[(int) foto.length()];
			//byte[] imageData = new byte[(int) foto.length()];
			//InputStream inputStream = new ByteArrayInputStream(byteArr);
	
			/*
			byte[] byteObjects = new byte[foto.getBytes().length];
		    int i = 0;

		    for (byte b : foto.getBytes()){
		        byteObjects[i++] = b;
		    }
			 */
	    	//profesor.setImagenPerfil(byteArr);

	    	profesor.setFoto(byteArr);
	    	
		} catch (IOException  e) {
		    //todo handle better
			//log.error("Error occurred", e);
	
		    e.printStackTrace();
		}
		
		

		//CatalogImage img = new CatalogImage();
	    /*byte[] imageData = new byte[(int) ((CharSequence) foto).length()];
	    try {
		    FileInputStream fileInputStream = new FileInputStream(foto);
		    fileInputStream.read(imageData);
		    fileInputStream.close();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    profesor.setFoto(imageData);*/
	  
	    profesor = this.getRepository().save(profesor);

		return profesor;
	}

	
	@Override
	public byte[] downloadFotoPerfil(Long id) {
		return repository.getFotoPerfil(id);
	}

	@Resource(name = "profesorRepository")
	void setProfesorRepository(ProfesorRepository profesorRepository) {
		this.repository = profesorRepository;
	}
	
}
