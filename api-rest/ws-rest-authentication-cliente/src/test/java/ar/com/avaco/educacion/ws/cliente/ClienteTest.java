package ar.com.avaco.educacion.ws.cliente;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.avaco.educacion.service.cliente.ClienteService;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration(locations = { "classpath:spring/applicationContext-test.xml" })
public class ClienteTest {

	private ClienteService clienteService;

	private AuthenticationManager authenticationManager;
	
	@Test
	public void resetPasswordTest() {
//		long idCliente = 100L;
//		Cliente cliente = clienteService.get(idCliente);
//		assertTrue(cliente.isRequiereCambioPassword());
//		clienteService.resetPassword(idCliente);
//		BufferedReader br = null;
//		FileReader fr = null;
//		try {
//			fr = new FileReader("passfile.txt");
//			br = new BufferedReader(fr);
//			String password = br.readLine();
//			cliente = clienteService.get(idCliente);
//			clienteService.updatePassword(cliente, password, "temppass");
//			cliente = clienteService.get(idCliente);
//			assertFalse(cliente.isRequiereCambioPassword());
//			File file = new File("passfile.txt");
//			file.delete();
//
//			Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(cliente.getUsername(), "temppass"));
//			assertNotNull(authenticate);
//			
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (br != null)
//					br.close();
//				if (fr != null)
//					fr.close();
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//
//		}
	}

	@Resource(name = "clienteServiceTest")
	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@Resource(name = "authenticationManager")
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
}
