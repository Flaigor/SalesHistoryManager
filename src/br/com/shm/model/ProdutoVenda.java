package br.com.shm.model;

public class ProdutoVenda {
	
	private Integer id;
	private Venda venda;
	private Produto produto;
	private Integer quantidade;
	private Double valor;
	
	public ProdutoVenda() {
		
	}
	
	public ProdutoVenda(Venda venda, Produto produto, Integer quantidade) {
		this.venda = venda;
		this.produto = produto;
		this.quantidade = quantidade;
		this.valor = produto.getPreco() * quantidade;
	}
	
	public Integer getIdProdutosVenda() {
		return id;
	}
	
	public void setIdProdutosVenda(Integer id) {
		this.id = id;
	}
	
	public Venda getIdVenda() {
		return venda;
	}
	
	public void setIdVenda(Venda venda) {
		this.venda = venda;
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
