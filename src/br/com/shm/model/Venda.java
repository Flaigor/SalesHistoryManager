package br.com.shm.model;

public class Venda {
	
	private Integer id;
	private String dataVenda;
	private String descricao;
	private Cliente comprador;
	private Double valor;
	private boolean pago;
	
	public Venda()
	{
		
	}
	
	public Venda(String dataVenda, String descricao, Cliente comprador, boolean pago)
	{
		this.dataVenda = dataVenda;
		this.descricao = descricao;
		this.comprador = comprador;
		this.pago = pago;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public boolean getPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}
	
}
