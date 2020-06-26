package ar.com.avaco.educacion.ws.mock;

import java.io.File;
import java.util.List;

import ar.com.avaco.arc.core.service.MailSenderSMTPService;

public class MailSenderMock implements MailSenderSMTPService {

	@Override
	public void sendMail(String from, String to, String subject, String msg, List<File> archivos) {
		// Do nothing
	}

	@Override
	public void sendMail(String from, String[] to, String subject, List<String> msg, List<File> archivos) {
		// Do nothing
	}

}
