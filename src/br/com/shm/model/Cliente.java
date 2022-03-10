package br.com.shm.model;

public class Cliente {
	
	private Integer id;
	private String nome;
	private String endereco;
	private String telefone;
	
	public Cliente() 
	{
		
	}
	
	public Cliente(String nome) 
	{
		this.nome = nome;
	}
	
	public Cliente(String endereco, String telefone) 
	{
		this.endereco = endereco;
		this.telefone = telefone;
	}
	
	public Cliente(String nome, String endereco, String telefone) 
	{
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
	}
	
	public Integer getId() {
		return id;
	}

	public void setIdCliente(Integer id) {
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
	
	public String getEndereco() 
	{
		return endereco;
	}
	
	public void setEndereco(String endereco) 
	{
		this.endereco = endereco;
	}
	
	public String getTelefone() 
	{
		return telefone;
	}
	
	public void setTelefone(String telefone) 
	{
		this.telefone = telefone;
	}
	
}
