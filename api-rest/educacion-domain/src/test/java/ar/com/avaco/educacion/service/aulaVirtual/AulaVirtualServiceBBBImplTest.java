package ar.com.avaco.educacion.service.aulaVirtual;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import ar.com.avaco.educacion.domain.entities.Alumno;
import ar.com.avaco.educacion.domain.entities.Aula;
import ar.com.avaco.educacion.domain.entities.Materia;
import ar.com.avaco.educacion.domain.entities.Profesor;
import ar.com.avaco.educacion.domain.entities.aulaVirtual.Clase;
import ar.com.avaco.educacion.exception.AulaVirtualException;

public class AulaVirtualServiceBBBImplTest {

	AulaVirtualServiceImpl aulaVirtualServiceImpl;
	Clase clase;
	
	@Before
	public void setUp() throws Exception {
		aulaVirtualServiceImpl=new AulaVirtualServiceImpl();
		aulaVirtualServiceImpl.setUrl("https://aulavirtual.teachonline.com.ar/bigbluebutton/");
		aulaVirtualServiceImpl.setSalt("f4JzhgiFV4vW2KNutq5Oxxz630uvwvw2jY5ARsDLY8Q");
		clase=null;
	}

	@Test
	public void A_testCrearSala() {
				
		Materia materia=new Materia();
		materia.setDescripcion("Amasado de la masa madre");
		
		Profesor profesor=new Profesor();
		profesor.setId(new Long(543));
		profesor.setNombre("Gluten");
		profesor.setApellido("Morgen");
		profesor.setUsername("profesor");
		
		Aula aula=new Aula();
		aula.setMateria(materia);
		HashSet<Profesor> profesors=new HashSet<>();
		profesors.add(profesor);
		aula.setProfesores(profesors);

		try {
			clase=aulaVirtualServiceImpl.crearClase(profesor, aula);
			assertTrue(clase!=null);
			System.out.println("Url acceso Profesor: "+clase.getUrl());
		} catch (AulaVirtualException e) {
			fail ("No se pudo crear la sala");
			e.printStackTrace();
		}		
		
	}

	@Test
	public void B_testCrearSalaYUnirAlumno() {
		
		if (clase==null) {
			A_testCrearSala();
		}
		
		Alumno alumno=new Alumno();
		alumno.setId(new Long(123));
		alumno.setNombre("Alberto");
		alumno.setApellido("Magno");
		
		try {
			String url=aulaVirtualServiceImpl.unirseAlumnoClase(clase.getIdClase(), alumno);
			System.out.println("Url acceso Alumno: "+url);
			assertTrue(url!=null);
		} catch (AulaVirtualException e) {
			fail ("No se pudo crear unir el alumno a la sala");
			e.printStackTrace();
		}		
	}
	
}
