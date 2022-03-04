package br.com.shm.model;

import java.util.ArrayList;

public class Venda {
	
	private double preco;
	private String dataVenda;
	private String descricao;
	private Cliente comprador;
	private ArrayList<Produto> produtos;
	
	public Venda()
	{
		
	}
	
	public Venda(double preco, String dataVenda, String descricao, Cliente comprador, ArrayList<Produto> produtos)
	{
		this.preco = preco;
		this.dataVenda = dataVenda;
		this.descricao = descricao;
		this.comprador = comprador;
		this.produtos = produtos;
	}
	
	public double getPreco() 
	{
		return preco;
	}
	
	public void setPreco(double preco) 
	{
		this.preco = preco;
	}
	
	public String getDataVenda() 
	{
		return dataVenda;
	}
	
	public void setDataVenda(String dataVenda) 
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
	
	public ArrayList<Produto> getProdutos() 
	{
		return produtos;
	}
	
	public void setProdutos(ArrayList<Produto> produtos) 
	{
		this.produtos = produtos;
	}
	
}
