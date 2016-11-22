package br.com.papa.horizon.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.papa.horizon.entity.Funcionario;
import br.com.papa.horizon.entity.Orcamento;

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
		   email.setAuthentication("henrynpsii@gmail.com", "xxxxxxx");
		   System.out.println("enviando...");
		   email.send();
		   System.out.println("Email enviado!");
		}
	
	public void enviaEmailCadastroFuncionario(String senha, String login, Funcionario funcionario) throws EmailException {
	    
		   SimpleEmail email = new SimpleEmail();
		   email.setHostName("smtp.gmail.com");
		   email.setSmtpPort(465);
		   //destinatários
		   email.addTo(funcionario.getEmail());
		   //email do qual enviará
		   email.setFrom("henrynpsii@gmail.com", "Horizon");
		   //assunto
		   email.setSubject("Bem Vindo!!!");
		   //Adicione a mensagem do email 
		   email.setMsg("Olá " + funcionario.getNome() 
				   + "\n" 
				   +"Seu cadastro foi efetuado com Sucesso!!! \n"
				   + "Seu login é: " + login
				   + " \n Sua senha é: " + senha
				   +"! \n  Favor Altera-la no primeiro login");
		   //Para autenticar no servidor é necessário chamar os dois métodos abaixo
		   System.out.println("autenticando...");
		   email.setSSL(true);
		   email.setAuthentication("henrynpsii@gmail.com", "xxxxxxx");
		   System.out.println("enviando...");
		   email.send();
		   System.out.println("Email enviado!");
		}
	
	public void enviaEmailOrcamento(String emailCliente, Orcamento orcamento) throws EmailException {
	    
		   SimpleEmail email = new SimpleEmail();
		   email.setHostName("smtp.gmail.com");
		   email.setSmtpPort(465);
		   //destinatários
		   email.addTo(emailCliente);
		   //email do qual enviará
		   email.setFrom("henrynpsii@gmail.com", "Horizon");
		   //assunto
		   email.setSubject("Pedido Orcamento");
		   //Adicione a mensagem do email
		   email.setMsg("Segue os dados de orcamento:"
		   		+ "\n Descrição do problema: " + orcamento.getObservacao()
		   		+ "\n Valor Total: " + orcamento.getValorTotal()); 
		   //Para autenticar no servidor é necessário chamar os dois métodos abaixo
		   System.out.println("autenticando...");
		   email.setSSL(true);
		   email.setAuthentication("henrynpsii@gmail.com", "xxxxxxx");
		   System.out.println("enviando...");
		   email.send();
		   System.out.println("Email enviado!");
		}
	
	public void enviaEmailOSConcluida(String emailCliente) throws EmailException {
	    
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
		   email.setAuthentication("henrynpsii@gmail.com", "xxxxx");
		   System.out.println("enviando...");
		   email.send();
		   System.out.println("Email enviado!");
		}

}
