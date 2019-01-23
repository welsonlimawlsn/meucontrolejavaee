package br.com.welson.meucontrole.ejb;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.core.MediaType;

@Stateless
public class EmailEJB {

	@Resource(name = "java:/jboss/mail/gmail")
	private Session session;

	@Asynchronous
	public void enviar(String para, String assunto, String html) {
		MimeMessage mensagem = new MimeMessage(session);
		try {
			mensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse(para));
			mensagem.setSubject(assunto);
			mensagem.setContent(html, MediaType.TEXT_HTML);
			Transport.send(mensagem);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
