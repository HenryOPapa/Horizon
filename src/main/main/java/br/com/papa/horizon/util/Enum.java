package br.com.papa.horizon.util;

/**
 * 
 * @author Henry O' Papa
 *
 */

public class Enum {
	
	public enum TipoEnderecos{
		COMERCIAL, RESIDENCIAL
	}
	/*NIVEIS DE ACESSO
	 * BAIXO --> CADASTRO CLIENTE, ABRIR ORCAMENTO
	 * MEDIO --> ACESSOS DO NIVEL BAIXO + ACESSO A FILA DE SERVIÇOS
	 * TOTAL --> ACESSO TOTAL AO SISTEMA
	 * */
	
	public enum StatusOrcamento{
		EM_ABERTO, CONCLUIDO, APROVADO, REPROVADO
	}
	
	public enum StatusOrdemDeServico{
		BACKLOG, TODO, WIP, IMPEDIMENT, DONE
	}
	
}
