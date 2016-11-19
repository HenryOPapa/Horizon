package br.com.papa.horizon.util;

import java.util.List;

import br.com.papa.horizon.dao.FuncionarioDao;
import br.com.papa.horizon.entity.Funcionario;
import br.com.papa.horizon.entity.OrdemDeServico;

public class CalculoFibonacci {
	
	FuncionarioDao funcionarioDao;
	
	public OrdemDeServico vincularOS(OrdemDeServico OS){
		funcionarioDao = new FuncionarioDao();
		
		List<Funcionario> listFuncionarios = funcionarioDao.findAll();
		long pontuacaoTotalUnitaria = 0;
		long pontuacaoMaisBaixa = 0;
		long idFuncionario = 0;
		Funcionario funcionarioLivre = new Funcionario();
		
		for(Funcionario funcionario : listFuncionarios){//Aqui iremos percorrer por todos os funcionários
			pontuacaoTotalUnitaria = 0;//Zeramos o valor da pontuaçao acumulada do funcionario

			/**
			 * Se a especialidade do funcionário for a mesma da Ordem de Servico
			 * Entramos na condição
			 */
			if(funcionario.getEspecialidade().equals(OS.getEspecialidade().getDescricao())){ 		
				//Caso funcionario nao possua OS, será atribuido a ele esta nova OS.
				if(funcionario.getOrdensDeServico().size() == 0){
					idFuncionario = funcionario.getId();
					
				}else{
					/**
					 * Apos entrar na condição, iremos varrer todas 
					 * as OS vinculadas ao funcionário para calcular o 
					 * total de seus pontos de demanda.
					 */					
					
					for(OrdemDeServico os : funcionario.getOrdensDeServico()){
						pontuacaoTotalUnitaria += os.getPontos();
					}
					
					/*
					 * Após recuperarmos o total de pontuacao do funcionário
					 * validamos se o mesmo possui a pontuacao mais baixa
					 */
					if(idFuncionario == 0){
						if(pontuacaoMaisBaixa == 0){
							idFuncionario = funcionario.getId();
							pontuacaoMaisBaixa = pontuacaoTotalUnitaria;
							
						}
						
					}
					
					if(pontuacaoTotalUnitaria < pontuacaoMaisBaixa){
						pontuacaoMaisBaixa = pontuacaoTotalUnitaria;
						//Caso não haja func. sem demanda, atribuimos a OS para o com menor pontuacao
						idFuncionario = funcionario.getId();
					}
				}
				
			}//FIM do primeiro IF

		}
		
		funcionarioLivre = funcionarioDao.findById(idFuncionario);
		funcionarioLivre.addOrdemServico(OS);
		
		
		return OS;
	}

}
