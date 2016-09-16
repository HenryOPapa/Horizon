
package br.com.papa.horizon.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.papa.horizon.util.GsonExclude;

/**
 * 
 * @author Henry O' Papa
 *
 */

@Entity
@Table(name="equipamento")
public class Equipamento implements Serializable{
	
	private static final long serialVersionUID = -4573440496209503089L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_EQUIPAMENTO")
	private Long id_equipamento;
	
	@Column(name = "TIPO_EQUIPAMENTO")
	private String tipoEquipamento;
	
	@Column(name = "MARCA")
	private String marca;
	
	@Column(name = "MODELO")
	private String modelo;
	
	@Column(name = "NUMERO_SERIE")
	private String numeroSerie;
	
//	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.ALL})
//	private Cliente cliente;

	@GsonExclude
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente cliente;
	

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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	

}
