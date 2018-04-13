package br.ucb.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
	
	private static Properties props;
	private static Session session;
	
	private static void construirProperties(){
		props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
	}

	private static void criarSessao(){
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("an123dre16@gmail.com", "Ags16#$oi");
			}
		});
		session.setDebug(true);
	}
	
    public static void enviarEmail(String emails, String assunto, String mensagem) {
    	construirProperties();
    	criarSessao();
        try {
              Message message = new MimeMessage(session);
              message.setFrom(new InternetAddress("an123dre16@gmail.com"));
              Address[] toUser = InternetAddress.parse(emails);  
              message.setRecipients(Message.RecipientType.TO, toUser);
              message.setSubject(assunto);
              message.setText(mensagem);
              Transport.send(message);
         } catch (MessagingException e) {
              throw new RuntimeException(e);
        }
  }
}
