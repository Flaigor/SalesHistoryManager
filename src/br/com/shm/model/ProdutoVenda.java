package br.com.shm.model;

public class ProdutoVenda {
	
	private Integer id;
	private Venda venda;
	private Produto produto;
	private Integer quantidade;
	private Double valor;
	
	public ProdutoVenda() {
		
	}
	
	public ProdutoVenda(Venda venda, Produto produto, Integer quantidade, Double preco) {
		this.venda = venda;
		this.produto = produto;
		this.quantidade = quantidade;
		this.valor = preco;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Venda getVenda() {
		return venda;
	}
	
	public void setVenda(Venda venda) {
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
