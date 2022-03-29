package br.com.shm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.shm.jdbc.ConnectionFactory;
import br.com.shm.model.Produto;
import br.com.shm.model.ProdutoVenda;

public class ProdutoVendaDAO {

private Connection con;
	
	public ProdutoVendaDAO(){
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void cadastrarProdutoVenda(ProdutoVenda prodVend)
	{
		try
		{
			String sql = "Insert Into SHMDB.ProdutoVenda(IdVenda, IdProduto, QuantidadeProdutoVenda,"
					+ " ValorProdutoVenda) Values (?, ?, ?, ?)";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, prodVend.getIdVenda());
			stmt.setInt(2, prodVend.getIdProduto());
			stmt.setInt(3, prodVend.getQuantidade());
			stmt.setDouble(4, prodVend.getValor());
			
			stmt.execute();
			stmt.close();
			
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha no cadastro do ProdutoVenda, erro: " + erro);
		}
	}
	
	public void alterarProdutoVenda(ProdutoVenda prodVend)
	{
		try
		{
			String sql = "Update SHMDB.ProdutoVenda set QuantidadeProdutoVenda = ?, ValorProdutoVenda = ?"
					+ " Where IdProdutoVenda = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, prodVend.getQuantidade());
			stmt.setDouble(2, prodVend.getValor());
			stmt.setInt(3, prodVend.getId());
			
			stmt.execute();
			stmt.close();
			
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha na alteração do ProdutoVenda, erro: " + erro);
		}
	}
	
	public void excluirProdutoVenda(String id) 
	{
		try
		{
			String sql = "Delete From SHMDB.ProdutoVenda Where IdProdutoVenda = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			
			stmt.execute();
			stmt.close();
			
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha na exclusão do ProdutoVenda, erro: " + erro);
		}
	}
	
	public void excluirProdutoVendaPorVenda(String id) 
	{
		try
		{
			String sql = "Delete From SHMDB.ProdutoVenda Where IdVenda = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			
			stmt.execute();
			stmt.close();
			
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha na exclusão do ProdutoVenda, erro: " + erro);
		}
	}
	
	public void excluirProdutoVendaPorProduto(String id) 
	{
		try
		{
			String sql = "Delete From SHMDB.ProdutoVenda Where IdProduto = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			
			stmt.execute();
			stmt.close();
			
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha na exclusão do ProdutoVenda, erro: " + erro);
		}
	}
	
	public List<ProdutoVenda> listarProdutoVenda()
	{
		try
		{
			List<ProdutoVenda> lista = new ArrayList<>();
			String sql = "Select * From SHMDB.ProdutoVenda";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				ProdutoVenda prodVend = new ProdutoVenda();
				prodVend.setId(rs.getInt("IdProdutoVenda"));
				prodVend.setIdVenda(rs.getInt("IdVenda"));
				prodVend.setIdProduto(rs.getInt("IdProduto"));
				prodVend.setQuantidade(rs.getInt("QuantidadeProdutoVenda"));
				prodVend.setValor(rs.getDouble("ValorProdutoVenda"));
				
				lista.add(prodVend);
			}
			
			return lista;
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha em listar os ProdutosVendas, erro: " + erro);
			return null;
		}
	}
	
	public List<ProdutoVenda> listarProdutoPorVenda(String id)
	{
		try
		{
			List<ProdutoVenda> lista = new ArrayList<>();
			String sql = "SELECT if(prodVen.IdProdutoVenda is null, '0', prodVen.IdProdutoVenda) Id, "
					+ "prod.IdProduto IdProduto ,prod.NomeProduto Nome, prod.DescricaoProduto Descrição, "
					+ "if(prodVen.QuantidadeProdutoVenda is null, 0, prodVen.QuantidadeProdutoVenda) Quantidade, "
					+ "if(prodVen.ValorProdutoVenda is null, prod.PrecoProduto, prodVen.ValorProdutoVenda) Preço "
					+ "FROM shmdb.produtovenda prodVen right JOIN shmdb.produtos prod ON prodVen.IdProduto = prod.IdProduto "
					+ "AND prodVen.IdVenda = ?;";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				ProdutoVenda prodVend = new ProdutoVenda();
				Produto prod = new Produto();
				prodVend.setId(rs.getInt("Id"));
				prodVend.setIdProduto(rs.getInt("IdProduto"));
				prod.setNome(rs.getString("Nome"));
				prod.setDescricao(rs.getString("Descrição"));
				prodVend.setProduto(prod);
				prodVend.setQuantidade(rs.getInt("Quantidade"));
				prodVend.setValor(rs.getDouble("Preço"));
				
				lista.add(prodVend);
			}
			
			return lista;
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha em listar os ProdutosVendas, erro: " + erro);
			return null;
		}
	}
}
