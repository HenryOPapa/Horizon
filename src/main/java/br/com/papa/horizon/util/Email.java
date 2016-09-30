package br.com.papa.horizon.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Email {
	
	public void enviaEmailCadastroCliente(String emailCliente) throws EmailException {
	    
		   SimpleEmail email = new SimpleEmail();
		   email.setHostName("smtp.gmail.com");
		   email.setSmtpPort(465);
		   //destinatários
		   email.addTo(emailCliente);
		   //email do qual enviará
		   email.setFrom("henrynpsii@gmail.com", "Horizon");
		   //assunto
		   email.setSubject("Bem Vindo!!!");
		   //Adicione a mensagem do email
		   email.setMsg("Seu cadastro foi efetuado com Sucesso!!!");
		   //Para autenticar no servidor é necessário chamar os dois métodos abaixo
		   System.out.println("autenticando...");
		   email.setSSL(true);
		   email.setAuthentication("henrynpsii@gmail.com", "xxxx");
		   System.out.println("enviando...");
		   email.send();
		   System.out.println("Email enviado!");
		}

}
