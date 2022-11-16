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

public class PrevisaoDAO {
	private Connection con;
	
	public PrevisaoDAO(){
		this.con = new ConnectionFactory().getConnection();
	}
	
	public List<Integer> getListPrevisaoMes(int ano)
	{
		List<Integer> listaPrevisaoMes = new ArrayList<Integer>();
		
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
				listaPrevisaoMes.add(rs.getInt(2));
			}
			
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha no retorno da Lista para previsão, erro: " + erro);
			return null;
		}
		
		return listaPrevisaoMes;
	}
	
	public List<Integer> getListPrevisaoCli(Cliente cli, int ano)
	{
		List<Integer> listaPrevisaoCli = new ArrayList<Integer>();
		
		for(int i = 0; i < 12; i++)
		{
			listaPrevisaoCli.add(0);
		}
		
		try
		{
			String sql = "Select month(str_to_date(v.DataVenda, '%d/%m/%Y')) mes, count(v.IdVenda) vendas "
					+ "from shmdb.vendas v, shmdb.clientes c "
					+ "where substring(v.DataVenda, 7, 4) = '" + ano
					+ "' and c.IdCliente = v.IdCliente and c.IdCliente = '" + cli.getId()
					+ "' group by month(str_to_date(v.DataVenda, '%d/%m/%Y')) "
					+ "order by month(str_to_date(v.DataVenda, '%d/%m/%Y'));";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				listaPrevisaoCli.set(rs.getInt(1) - 1, rs.getInt(2));
			}
			
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha no retorno da Lista para previsão Clientes, erro: " + erro);
			return null;
		}
		
		return listaPrevisaoCli;
	}
	
	public List<Integer> getListPrevisaoProd(Produto prod, int ano)
	{
		List<Integer> listaPrevisaoProd = new ArrayList<Integer>();
		
		for(int i = 0; i < 12; i++)
		{
			listaPrevisaoProd.add(0);
		}
		
		try
		{
			String sql = "select month(str_to_date(v.DataVenda, '%d/%m/%Y')) mes, sum(pv.QuantidadeProdutoVenda) quantidade "
					+ "from shmdb.produtos p, shmdb.produtovenda pv, shmdb.vendas v "
					+ "where pv.IdProduto = p.IdProduto and pv.IdVenda = v.IdVenda "
					+ "and substring(v.DataVenda, 7, 4) = " + ano
					+ " and p.IdProduto = " + prod.getId()
					+ " group by month(str_to_date(v.DataVenda, '%d/%m/%Y')) "
					+ " order by month(str_to_date(v.DataVenda, '%d/%m/%Y'));";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				listaPrevisaoProd.set(rs.getInt(1) - 1, rs.getInt(2));
			}
			
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha no retorno da Lista para previsão Clientes, erro: " + erro);
			return null;
		}
		
		return listaPrevisaoProd;
	}

}
