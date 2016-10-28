package br.com.papa.horizon.vo;


public class EquipamentoVO {
		
	private Long id_equipamento;

	private String tipoEquipamento;

	private String marca;

	private String modelo;

	private String numeroSerie;


	public Long getId_equipamento() {
		return id_equipamento;
	}

	public void setId_equipamento(Long id_equipamento) {
		this.id_equipamento = id_equipamento;
	}

	public String getTipoEquipamento() {
		return tipoEquipamento;
	}

	public void setTipoEquipamento(String tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	
	

}
