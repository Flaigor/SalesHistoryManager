package br.com.shm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.shm.jdbc.ConnectionFactory;
import br.com.shm.model.Cliente;
import br.com.shm.model.Venda;

public class VendasDAO {

private Connection con;
	
	public VendasDAO(){
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void cadastrarVenda(Venda vend)
	{
		try
		{
			String sql = "Insert Into SHMDB.Vendas(IdCliente, DataVenda, DescricaoVenda, PagoVenda) "
					+ "Values (?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, vend.getIdcliente());
			stmt.setString(2, vend.getDataVenda());
			stmt.setString(3, vend.getDescricao());
			stmt.setBoolean(4, vend.getPago());
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso!");
			
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha no cadastro da Venda, erro: " + erro);
		}
	}
	
	public void alterarVenda(Venda vend)
	{
		try
		{
			String sql = "Update SHMDB.Vendas set DataVenda = ?, DescricaoVenda = ?, "
					+ "PagoVenda = ? Where IdVenda = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, vend.getDataVenda());
			stmt.setString(2, vend.getDescricao());
			stmt.setBoolean(3, vend.getPago());
			stmt.setString(4, vend.getId().toString());
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Venda alterada com sucesso!");
			
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha na alteração da Venda, erro: " + erro);
		}
	}
	
	public void excluirVenda(String id) 
	{
		try
		{
			String sql = "Delete From SHMDB.Vendas Where IdVenda = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Venda excluída com sucesso!");
			
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha na exclusão da Venda, erro: " + erro);
		}
	}
	
	public void excluirVendaPorCliente(String id) 
	{
		try
		{
			String sql = "Delete From SHMDB.Vendas Where IdCliente = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Venda excluída com sucesso!");
			
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha na exclusão da Venda, erro: " + erro);
		}
	}
	
	public List<Venda> listarVenda()
	{
		try
		{
			List<Venda> lista = new ArrayList<>();
			String sql = "Select * From SHMDB.Vendas";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				Venda vend = new Venda();
				vend.setId(rs.getInt("IdVenda"));
				vend.setDataVenda(rs.getString("DataVenda"));
				vend.setDescricao(rs.getString("DescricaoVenda"));
				vend.setIdcliente(rs.getInt("IdCliente"));
				vend.setPago(rs.getBoolean("PagoVenda"));
				
				lista.add(vend);
			}
			
			return lista;
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha em listar as Vendas, erro: " + erro);
			return null;
		}
	}
	
	public int getMaiorId()
	{
		try
		{
			String sql = "Select max(IdVenda) from shmdb.vendas";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			int idVenda = 0;
			while(rs.next())
			{
				idVenda = rs.getInt("max(IdVenda)");
			}
			
			return idVenda;
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha em listar as Vendas, erro: " + erro);
			return 0;
		}
	}
	
	public List<Venda> listarVendaJoinCliente()
	{
		try
		{
			List<Venda> lista = new ArrayList<>();
			String sql = "SELECT ven.IdVenda, cli.NomeCliente, ven.DataVenda, ven.DesCricaoVenda, ven.PagoVenda "
					+ "FROM shmdb.vendas ven INNER JOIN shmdb.clientes cli ON ven.IdCliente = cli.IdCliente "
					+ "ORDER BY ven.DataVenda;";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				Venda vend = new Venda();
				Cliente cli = new Cliente();
				vend.setId(rs.getInt("ven.IdVenda"));
				cli.setNome(rs.getString("cli.NomeCliente"));
				vend.setComprador(cli);  
				vend.setDataVenda(rs.getString("ven.DataVenda"));
				vend.setDescricao(rs.getString("ven.DesCricaoVenda"));
				vend.setPago(rs.getBoolean("ven.PagoVenda"));
				
				lista.add(vend);
			}
			
			return lista;
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha em listar as Vendas, erro: " + erro);
			return null;
		}
	}
	
	public List<Venda> listarVendaNaoPagas(List<Integer> idVendas)
	{
		try
		{
			List<Venda> lista = new ArrayList<>();
			String sql = "SELECT cli.NomeCliente Cliente, ven.DataVenda, ven.DesCricaoVenda Descricao, "
						+ "sum((prodven.QuantidadeProdutoVenda * prodven.ValorProdutoVenda)) Valor "
						+ "FROM shmdb.vendas ven INNER JOIN shmdb.clientes cli ON ven.IdCliente = cli.IdCliente "
						+ "AND ven.PagoVenda = '0' INNER JOIN shmdb.produtovenda prodven "
						+ "ON ven.IdVenda = prodven.IdVenda AND ven.IdVenda = ? ORDER BY ven.DataVenda;";
			
			for(int i = 0; i < idVendas.size(); i++)
			{
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, idVendas.get(i).toString());
				ResultSet rs = stmt.executeQuery();
				Double valor = 0.0;
				while(rs.next())
				{
					valor = rs.getDouble("Valor");
					if(valor != 0.0)
					{
						Venda vend = new Venda();
						Cliente cli = new Cliente();
						cli.setNome(rs.getString("Cliente"));
						vend.setComprador(cli);  
						vend.setDataVenda(rs.getString("ven.DataVenda"));
						vend.setDescricao(rs.getString("Descricao"));
						vend.setValor(rs.getDouble("Valor"));
						
						lista.add(vend);
					}
				}
			}
			return lista;
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha em listar as Vendas, erro: " + erro);
			return null;
		}
	}
	
	public List<Integer> IdVendasNaoPagas()
	{
		try
		{
			List<Integer> lista = new ArrayList<>();
			String sql = "SELECT IdVenda From shmdb.vendas where PagoVenda = '0'";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				lista.add(rs.getInt("IdVenda"));
			}
			
			return lista;
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha em listar as Vendas, erro: " + erro);
			return null;
		}
	}
}
