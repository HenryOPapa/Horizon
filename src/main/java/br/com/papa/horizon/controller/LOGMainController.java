package br.com.papa.horizon.controller;

import java.util.Date;

import br.com.papa.horizon.entity.Estoque;
import br.com.papa.horizon.entity.log.LOGNotaFiscal;
import br.com.papa.horizon.log.LOGNotaFiscalDao;

public class LOGMainController {
	
	LOGNotaFiscalDao logNotaFiscalDao;
	
	public void salvarLogNotaFiscal(Estoque estoque){
		LOGNotaFiscal notaFiscal = new LOGNotaFiscal();
		notaFiscal.setDataInclusao(new Date());
		notaFiscal.setDescricaoPeca(estoque.getDescricaoPeca());
		notaFiscal.setQuantidadePeca(estoque.getQuantidade());
		notaFiscal.setNumeroNotaFiscal(estoque.getNotaFiscal());
		logNotaFiscalDao = new LOGNotaFiscalDao();
		logNotaFiscalDao.save(notaFiscal);
		
	}

}
