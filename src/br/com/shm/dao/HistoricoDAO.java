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
	
	public HistoricoDAO(){
		this.con = new ConnectionFactory().getConnection();
	}
	
	public List<ProdutoVenda> listar(int tipo, int pesquisa, int ordem)
	{
		
		try
		{
			List<ProdutoVenda> lista = new ArrayList<>();
			String sql = montarQuery(tipo, pesquisa, ordem);
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			if(tipo == 0)
			{
				while(rs.next())
				{
					ProdutoVenda pv = new ProdutoVenda();
					Cliente cli = new Cliente();
					
					cli.setNome(rs.getString("cliente"));
					pv.setQuantidade(rs.getInt("compras"));
					pv.getVenda().setComprador(cli);
					
					lista.add(pv);
				}
			}
			else if(tipo == 1)
			{
				while(rs.next())
				{
					ProdutoVenda pv = new ProdutoVenda();
					Venda v = new Venda();
					
					v.setDataVenda(rs.getString("mes"));
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
	
	public String montarQuery(int tipo, int pesquisa, int ordem)
	{
		String sql = "";
		if(tipo == 0)
		{
			if(pesquisa == 0)
			{
				sql = "Select c.NomeCliente cliente, count(*) compras from shmdb.clientes c "
						+ "inner join shmdb.vendas v on c.IdCliente = v.IdCliente "
						+ "group by c.NomeCliente order by count(*) ";
			}
			else if(pesquisa == 1)
			{
				sql = "Select c.NomeCliente cliente, count(*) dividas from shmdb.clientes c "
						+ "inner join shmdb.vendas v on c.IdCliente = v.IdCliente and v.PagoVenda = 0 "
						+ "group by c.NomeCliente order by count(*) ";
			}
		}
		else if(tipo == 1)
		{
			if(pesquisa == 0)
			{
				sql = "Select month(str_to_date(DataVenda, '%d/%m/%Y')) mes, count(IdVenda) vendas "
					+ "from shmdb.vendas group by month(str_to_date(DataVenda, '%d/%m/%Y')) "
					+ "order by count(IdVenda) ";
			}
			else if(pesquisa == 1)
			{
				sql = "Select year(str_to_date(DataVenda, '%d/%m/%Y')) ano, count(IdVenda) vendas "
						+ "from shmdb.vendas group by year(str_to_date(DataVenda, '%d/%m/%Y')) "
						+ "order by count(IdVenda) ";
			}
		}
		else if(tipo == 2)
		{
			if(pesquisa == 0)
			{
				sql += "SELECT p.NomeProduto produto, sum(pv.QuantidadeProdutoVenda) quantidade FROM shmdb.produtos p "
						+ "inner join shmdb.produtovenda pv on p.IdProduto = pv.IdProduto "
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
	
}
