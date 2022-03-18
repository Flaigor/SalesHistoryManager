package br.com.shm.model;

public class ProdutoVenda {
	
	private Integer id;
	private Venda venda;
	private Integer idVenda;
	private Produto produto;
	private Integer idProduto;
	private Integer quantidade;
	private Double valor;
	
	public ProdutoVenda() {
		
	}
	
	public ProdutoVenda(Venda venda, Produto produto, Integer quantidade) {
		this.venda = venda;
		this.idVenda = venda.getId();
		this.produto = produto;
		this.idProduto = produto.getId();
		this.quantidade = quantidade;
		this.valor = produto.getPreco() * quantidade;
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
	
	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
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
