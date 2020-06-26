package ar.com.avaco.educacion.ws.cliente;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ar.com.avaco.educacion.domain.cliente.Cliente;
import ar.com.avaco.educacion.service.cliente.ClienteServiceImpl;

public class ClienteServiceImplTest extends ClienteServiceImpl {

	@Override
	protected void notificarResetoPassword(Cliente cliente, String tmpass) {
		File logFile = new File("passfile.txt");
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(logFile));
			writer.write(tmpass);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
