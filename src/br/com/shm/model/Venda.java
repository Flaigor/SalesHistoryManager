package br.com.shm.model;

import javax.xml.crypto.Data;

public class Venda {
	
	private Integer id;
	private Data dataVenda;
	private String descricao;
	private Cliente comprador;
	private Integer Idcliente;
	private boolean pago;
	
	public Venda()
	{
		
	}
	
	public Venda(Data dataVenda, String descricao, Cliente comprador, boolean pago)
	{
		this.dataVenda = dataVenda;
		this.descricao = descricao;
		this.comprador = comprador;
		this.Idcliente = comprador.getId();
		this.pago = pago;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Data getDataVenda() 
	{
		return dataVenda;
	}
	
	public void setDataVenda(Data dataVenda) 
	{
		this.dataVenda = dataVenda;
	}
	
	public String getDescricao() 
	{
		return descricao;
	}

	public void setDescricao(String descricao) 
	{
		this.descricao = descricao;
	}
	
	public Cliente getComprador() 
	{
		return comprador;
	}
	
	public void setComprador(Cliente comprador) 
	{
		this.comprador = comprador;
	}

	public Integer getIdcliente() {
		return Idcliente;
	}

	public void setIdcliente(Integer idcliente) {
		Idcliente = idcliente;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}
	
}
