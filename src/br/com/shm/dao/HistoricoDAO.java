package br.com.shm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.shm.jdbc.ConnectionFactory;
import br.com.shm.model.Cliente;
import br.com.shm.model.Produto;
import br.com.shm.model.ProdutoVenda;
import br.com.shm.model.Venda;

public class HistoricoDAO {

	private Connection con;
	private String[] mes = { "Janerio", "Fevereiro", "Março", "Abril", "Maio", "Junho", 
			"Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
	
	public HistoricoDAO(){
		this.con = new ConnectionFactory().getConnection();
	}
	
	public List<ProdutoVenda> listar(int tipo, int pesquisa, int ordem, String ano)
	{
		try
		{
			List<ProdutoVenda> lista = new ArrayList<>();
			String sql = montarQuery(tipo, pesquisa, ordem, ano);
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			if(tipo == 0)
			{
				while(rs.next())
				{
					ProdutoVenda pv = new ProdutoVenda();
					Venda ven = new Venda();
					Cliente cli = new Cliente();
					
					cli.setNome(rs.getString("cliente"));
					pv.setQuantidade(rs.getInt("compras"));
					ven.setComprador(cli);
					pv.setVenda(ven);
					
					lista.add(pv);
				}
			}
			else if(tipo == 1)
			{
				while(rs.next())
				{
					ProdutoVenda pv = new ProdutoVenda();
					Venda v = new Venda();
					
					if(pesquisa == 0)
					{
						v.setDataVenda(mes[rs.getInt("mes") - 1]);
					}
					else if(pesquisa == 1)
					{
						v.setDataVenda(rs.getString("mes"));
					}
					
					pv.setQuantidade(rs.getInt("vendas"));
					pv.setVenda(v);
					
					lista.add(pv);
				}
			}
			else if(tipo == 2)
			{
				while(rs.next())
				{
					ProdutoVenda pv = new ProdutoVenda();
					Produto p = new Produto();
					
					p.setNome(rs.getString("produto"));
					pv.setQuantidade(rs.getInt("quantidade"));
					pv.setProduto(p);
					
					lista.add(pv);
				}
			}
			
			return lista;

		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha no retorno do histórico, erro: " + erro);
			return null;
		}

	}
	
	public String montarQuery(int tipo, int pesquisa, int ordem, String ano)
	{
		String sql = "";
		
		if(ano.compareTo("Todos os anos") == 0)
		{
			ano = "";
		}
		else {
			ano = "where substring(v.DataVenda, 7, 4) = '" + ano + "' ";
		}
		
		if(tipo == 0)
		{
			if(pesquisa == 0)
			{
				sql = "Select c.NomeCliente cliente, count(*) compras from shmdb.clientes c "
						+ "inner join shmdb.vendas v on c.IdCliente = v.IdCliente " + ano
						+ "group by c.NomeCliente order by count(*) ";
			}
			else if(pesquisa == 1)
			{
				sql = "Select c.NomeCliente cliente, count(*) compras from shmdb.clientes c "
						+ "inner join shmdb.vendas v on c.IdCliente = v.IdCliente and v.PagoVenda = 0 " + ano
						+ "group by c.NomeCliente order by count(*) ";
			}
		}
		else if(tipo == 1)
		{
			if(pesquisa == 0)
			{
				sql = "Select month(str_to_date(v.DataVenda, '%d/%m/%Y')) mes, count(v.IdVenda) vendas "
					+ "from shmdb.vendas v " + ano
					+ "group by month(str_to_date(v.DataVenda, '%d/%m/%Y')) " 
					+ "order by count(v.IdVenda) ";
			}
			else if(pesquisa == 1)
			{
				sql = "Select year(str_to_date(v.DataVenda, '%d/%m/%Y')) mes, count(v.IdVenda) vendas "
						+ "from shmdb.vendas v " + ano
						+ "group by year(str_to_date(v.DataVenda, '%d/%m/%Y')) " 
						+ "order by count(v.IdVenda) ";
			}
		}
		else if(tipo == 2)
		{
			if(pesquisa == 0)
			{
				sql += "SELECT p.NomeProduto produto, sum(pv.QuantidadeProdutoVenda) quantidade FROM shmdb.produtos p "
						+ "inner join shmdb.produtovenda pv on p.IdProduto = pv.IdProduto "
						+ "inner join shmdb.vendas v on v.IdVenda = pv.IdVenda " + ano
						+ "group by p.NomeProduto order by sum(pv.QuantidadeProdutoVenda) ";
			}
		}
		
		if(ordem == 0)
		{
			sql += "asc;";
			
		}
		else if(ordem == 1)
		{
			sql += "desc;";
		}
		
		return sql;
	}
	
	public List<String> listarAnos()
	{
		List<String> anos = new ArrayList<String>();
		
		try
		{
			String sql = "select distinct substring(DataVenda, 7, 4) ano from shmdb.vendas order by DataVenda asc;";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			anos.add("Todos os anos");
			
			while(rs.next())
			{
				anos.add(rs.getString("ano"));
			}
			
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha no retorno dos Anos, erro: " + erro);
			return null;
		}
		
		return anos;
	}
	
	public List<Integer> getListPrevisao(int ano)
	{
		List<Integer> listaPrevisao = new ArrayList<Integer>();
		
		try
		{
			String sql = "Select month(str_to_date(v.DataVenda, '%d/%m/%Y')) mes, count(v.IdVenda) vendas "
					+ "from shmdb.vendas v where substring(v.DataVenda, 7, 4) = '" + ano
					+ "' group by month(str_to_date(v.DataVenda, '%d/%m/%Y')) "
					+ "order by month(str_to_date(v.DataVenda, '%d/%m/%Y'));";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				listaPrevisao.add(rs.getInt(2));
			}
			
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha no retorno da Lista para previsão, erro: " + erro);
			return null;
		}
		
		return listaPrevisao;
	}
	
	public List<String> getMeses()
	{
		List<String> lst = new ArrayList<String>();
		for(int i = 0; i < this.mes.length; i++)
		{
			lst.add(this.mes[i]);
		}
		return lst;
	}
	
}
