package br.com.shm.model;

public class ProdutosVenda {
	
	private Integer idProdutosVenda;
	private Integer idVenda;
	private Produto produto;
	private Integer quantidade;
	private Double valor;
	
	public ProdutosVenda() {
		
	}
	
	public ProdutosVenda(Integer idVenda, Produto produto, Integer quantidade) {
		this.idVenda = idVenda;
		this.produto = produto;
		this.quantidade = quantidade;
		this.valor = produto.getPreco() * quantidade;
	}
	
	public Integer getIdProdutosVenda() {
		return idProdutosVenda;
	}
	
	public void setIdProdutosVenda(Integer idProdutosVenda) {
		this.idProdutosVenda = idProdutosVenda;
	}
	
	public Integer getIdVenda() {
		return idVenda;
	}
	
	public void setIdVenda(Integer idVenda) {
		this.idVenda = idVenda;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}

}
