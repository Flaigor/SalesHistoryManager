package br.com.shm.model;

public class Produto {
	
	private Integer id;
	private String nome;
	private String descricao;
	private Double preco;
	
	public Produto() 
	{
		
	}
	
	public Produto(String nome, String descricao, double preco) 
	{
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	public Integer getIdProduto() 
	{
		return id;
	}

	public void setIdProduto(Integer id) 
	{
		this.id = id;
	}

	public String getNome() 
	{
		return nome;
	}
	
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	
	public String getDescricao() 
	{
		return descricao;
	}
	
	public void setDescricao(String descricao) 
	{
		this.descricao = descricao;
	}
	
	public double getPreco() 
	{
		return preco;
	}
	
	public void setPreco(Double preco) 
	{
		this.preco = preco;
	}
	
}
