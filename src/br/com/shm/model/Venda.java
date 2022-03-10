package br.com.shm.model;

import java.util.ArrayList;

import javax.xml.crypto.Data;

public class Venda {
	
	private Integer id;
	private Data dataVenda;
	private String descricao;
	private Cliente comprador;
	
	public Venda()
	{
		
	}
	
	public Venda(Data dataVenda, String descricao, Cliente comprador)
	{
		this.dataVenda = dataVenda;
		this.descricao = descricao;
		this.comprador = comprador;
	}
	
	public Integer getIdVenda() {
		return id;
	}

	public void setIdVenda(Integer id) {
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
	
}
