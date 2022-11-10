package br.com.shm.jdbc;

import java.util.ArrayList;
import java.util.List;

import br.com.shm.dao.ClientesDAO;
import br.com.shm.dao.ProdutoVendaDAO;
import br.com.shm.dao.ProdutosDAO;
import br.com.shm.dao.VendasDAO;
import br.com.shm.model.Cliente;
import br.com.shm.model.Produto;
import br.com.shm.model.ProdutoVenda;
import br.com.shm.model.Venda;

public class GeraMassa {
	
	public static void main(String[] args) {
		
		String mes = "11";
		String ano = "2022";
		
		ClientesDAO dao = new ClientesDAO();
		List<Cliente> listaCli = dao.listarClientes();
		
		ProdutosDAO daoProd = new ProdutosDAO();
		List<Produto> listaProd = daoProd.listarProdutos();
		
		List<Produto> produtos = new ArrayList<Produto>();
		List<Integer> qtd = new ArrayList<Integer>();
		List<Double> preco = new ArrayList<Double>();
		
		GeraMassa gm = new GeraMassa();
		
		
		produtos.add(listaProd.get(1));
		
		qtd.add(5);
		
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("01/" + mes + "/" + ano, "", listaCli.get(0), true, produtos, qtd, preco); // 1
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		
		produtos.add(listaProd.get(1));
		
		qtd.add(10);
		
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("02/" + mes + "/" + ano, "", listaCli.get(1), true, produtos, qtd, preco); // 2
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		produtos.add(listaProd.get(0));
		produtos.add(listaProd.get(1));
		
		qtd.add(30);
		qtd.add(10);
		
		preco.add(listaProd.get(0).getPreco());
		preco.add(listaProd.get(1).getPreco());
		
		
		gm.cadastrarVenda("03/" + mes + "/" + ano, "", listaCli.get(2), true, produtos, qtd, preco); // 3
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		produtos.add(listaProd.get(0));
		produtos.add(listaProd.get(1));
		
		qtd.add(30);
		qtd.add(10);
		
		preco.add(listaProd.get(0).getPreco());
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("04/" + mes + "/" + ano, "", listaCli.get(3), true, produtos, qtd, preco); // 4
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		
		produtos.add(listaProd.get(0));
		produtos.add(listaProd.get(1));
		
		qtd.add(30);
		qtd.add(10);
		
		preco.add(listaProd.get(0).getPreco());
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("05/" + mes + "/" + ano, "", listaCli.get(4), true, produtos, qtd, preco); // 5
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		

		produtos.add(listaProd.get(0));
		produtos.add(listaProd.get(1));
		
		qtd.add(30);
		qtd.add(10);
		
		preco.add(listaProd.get(0).getPreco());
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("06/" + mes + "/" + ano, "", listaCli.get(5), true, produtos, qtd, preco); // 6
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		produtos.add(listaProd.get(0));
		produtos.add(listaProd.get(1));
		
		qtd.add(30);
		qtd.add(10);
		
		preco.add(listaProd.get(0).getPreco());
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("07/" + mes + "/" + ano, "", listaCli.get(6), true, produtos, qtd, preco); // 7
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		
		produtos.add(listaProd.get(0));
		produtos.add(listaProd.get(1));
		
		qtd.add(50);
		qtd.add(5);
		
		preco.add(listaProd.get(0).getPreco());
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("08/" + mes + "/" + ano, "", listaCli.get(7), true, produtos, qtd, preco); // 8
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		
		produtos.add(listaProd.get(11));
		
		qtd.add(15);
		
		preco.add(listaProd.get(11).getPreco());
		
		gm.cadastrarVenda("09/" + mes + "/" + ano, "", listaCli.get(8), true, produtos, qtd, preco); // 9
		
		
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		
		produtos.add(listaProd.get(1));
		
		qtd.add(5);
		
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("10/" + mes + "/" + ano, "", listaCli.get(0), true, produtos, qtd, preco); // 10
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		
		produtos.add(listaProd.get(1));
		
		qtd.add(10);
		
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("11/" + mes + "/" + ano, "", listaCli.get(1), true, produtos, qtd, preco); // 11
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		
		produtos.add(listaProd.get(0));
		produtos.add(listaProd.get(1));
		
		qtd.add(30);
		qtd.add(10);
		
		preco.add(listaProd.get(0).getPreco());
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("12/" + mes + "/" + ano, "", listaCli.get(2), true, produtos, qtd, preco); // 12
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		produtos.add(listaProd.get(0));
		produtos.add(listaProd.get(1));
		
		qtd.add(30);
		qtd.add(10);
		
		preco.add(listaProd.get(0).getPreco());
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("13/" + mes + "/" + ano, "", listaCli.get(3), true, produtos, qtd, preco); // 13
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		produtos.add(listaProd.get(0));
		produtos.add(listaProd.get(1));
		
		qtd.add(30);
		qtd.add(10);
		
		preco.add(listaProd.get(0).getPreco());
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("14/" + mes + "/" + ano, "", listaCli.get(4), true, produtos, qtd, preco); // 14
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		
		produtos.add(listaProd.get(0));
		produtos.add(listaProd.get(1));
		
		qtd.add(30);
		qtd.add(10);
		
		preco.add(listaProd.get(0).getPreco());
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("15/" + mes + "/" + ano, "", listaCli.get(5), true, produtos, qtd, preco); // 15
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		
		produtos.add(listaProd.get(0));
		produtos.add(listaProd.get(1));
		
		qtd.add(30);
		qtd.add(10);
		
		preco.add(listaProd.get(0).getPreco());
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("16/" + mes + "/" + ano, "", listaCli.get(6), true, produtos, qtd, preco); // 16
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		
		produtos.add(listaProd.get(0));
		produtos.add(listaProd.get(1));
		
		qtd.add(50);
		qtd.add(5);
		
		preco.add(listaProd.get(0).getPreco());
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("17/" + mes + "/" + ano, "", listaCli.get(7), true, produtos, qtd, preco); // 17
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		
		produtos.add(listaProd.get(11));
		
		qtd.add(15);
		
		preco.add(listaProd.get(11).getPreco());
		
		gm.cadastrarVenda("18/" + mes + "/" + ano, "", listaCli.get(8), true, produtos, qtd, preco); // 18
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		
		produtos.add(listaProd.get(1));
		
		qtd.add(1);
		
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("19/" + mes + "/" + ano, "", listaCli.get(0), true, produtos, qtd, preco); // 19
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		
		produtos.add(listaProd.get(1));
		
		qtd.add(10);
		
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("20/" + mes + "/" + ano, "", listaCli.get(1), true, produtos, qtd, preco); // 20
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		produtos.add(listaProd.get(0));
		produtos.add(listaProd.get(1));
		
		qtd.add(30);
		qtd.add(10);
		
		preco.add(listaProd.get(0).getPreco());
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("21/" + mes + "/" + ano, "", listaCli.get(2), true, produtos, qtd, preco); // 21
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		produtos.add(listaProd.get(0));
		produtos.add(listaProd.get(1));
		
		qtd.add(30);
		qtd.add(10);
		
		preco.add(listaProd.get(0).getPreco());
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("22/" + mes + "/" + ano, "", listaCli.get(3), true, produtos, qtd, preco); // 22
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		produtos.add(listaProd.get(0));
		produtos.add(listaProd.get(1));
		
		qtd.add(30);
		qtd.add(10);
		
		preco.add(listaProd.get(0).getPreco());
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("23/" + mes + "/" + ano, "", listaCli.get(4), true, produtos, qtd, preco); // 23
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		
		produtos.add(listaProd.get(0));
		produtos.add(listaProd.get(1));
		
		qtd.add(30);
		qtd.add(10);
		
		preco.add(listaProd.get(0).getPreco());
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("24/" + mes + "/" + ano, "", listaCli.get(5), true, produtos, qtd, preco); // 24
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		produtos.add(listaProd.get(0));
		produtos.add(listaProd.get(1));
		
		qtd.add(30);
		qtd.add(10);
		
		preco.add(listaProd.get(0).getPreco());
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("25/" + mes + "/" + ano, "", listaCli.get(6), true, produtos, qtd, preco); // 25
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		
		produtos.add(listaProd.get(0));
		produtos.add(listaProd.get(1));
		
		qtd.add(50);
		qtd.add(5);
		
		preco.add(listaProd.get(0).getPreco());
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("26/" + mes + "/" + ano, "", listaCli.get(7), true, produtos, qtd, preco); // 27
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		produtos.add(listaProd.get(11));
		
		qtd.add(15);
		
		preco.add(listaProd.get(11).getPreco());
		
		gm.cadastrarVenda("28/" + mes + "/" + ano, "", listaCli.get(8), true, produtos, qtd, preco); // 28
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		/*
		
		produtos.add(listaProd.get(1));
		
		qtd.add(1);
		
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("29/" + mes + "/" + ano, "", listaCli.get(0), true, produtos, qtd, preco); // 29
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		produtos.add(listaProd.get(1));
		
		qtd.add(10);
		
		preco.add(listaProd.get(1).getPreco());
		
		gm.cadastrarVenda("30/" + mes + "/" + ano, "", listaCli.get(1), true, produtos, qtd, preco); // 30
		
		produtos.clear();
		qtd.clear();
		preco.clear();
		
		/**/
	}
	
	public void cadastrarVenda(String dataVenda, String descricao, Cliente comprador, boolean pago, 
			List<Produto> produtos, List<Integer> qtd, List<Double> preco)
	{	
		VendasDAO vendaDao = new VendasDAO();
		Venda venda = new Venda(dataVenda, descricao, comprador, pago);
		vendaDao.cadastrarVenda(venda);
		venda.setId(vendaDao.getMaiorId());
		
		for(int i = 0; i < produtos.size(); i++)
		{
			ProdutoVenda prodVenda = new ProdutoVenda(venda, produtos.get(i), qtd.get(i), preco.get(i));
			ProdutoVendaDAO prodVendaDao = new ProdutoVendaDAO();
			prodVendaDao.cadastrarProdutoVenda(prodVenda);
		}
	}
	
}
