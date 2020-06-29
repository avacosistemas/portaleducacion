package ar.com.avaco.educacion.ws.educacion;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.avaco.educacion.ws.controller.ClienteRestController;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration(locations = { "classpath:spring/applicationContext-test.xml" })
public class ClienteRestControllerTest {

	@Autowired
	private ClienteRestController clienteRestController;
	
	@Test
	public void getClienteCompletoTest() throws Exception {
		clienteRestController.getCompleto(101L);
	}

	@Test
	public void getClienteListadoTest() throws Exception {
		clienteRestController.listClientesListado();
	}

	public void setClienteRestController(ClienteRestController clienteRestController) {
		this.clienteRestController = clienteRestController;
	}
	
}
