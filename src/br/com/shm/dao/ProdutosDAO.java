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

public class ProdutosDAO {
	
	private Connection con;
	
	public ProdutosDAO(){
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void cadastrarProduto(Produto prod)
	{
		try
		{
			String sql = "Insert Into SHMDB.Produtos(NomeProduto, DescricaoProduto, PrecoProduto) "
					+ "Values (?, ?, ?)";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, prod.getNome());
			stmt.setString(2, prod.getDescricao());
			stmt.setString(3, prod.getPreco().toString());
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
			
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha no cadastro do Produto, erro: " + erro);
		}
	}
	
	public void alterarProduto(Produto prod)
	{
		try
		{
			String sql = "Update SHMDB.Produtos set NomeProduto = ?, DescricaoProduto = ?, PrecoProduto = ? "
					+ "Where IdProduto = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, prod.getNome());
			stmt.setString(2, prod.getDescricao());
			stmt.setString(3, prod.getPreco().toString());
			stmt.setString(4, prod.getId().toString());
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");
			
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha na alteração do Produto, erro: " + erro);
		}
	}
	
	public void excluirProduto(int id) 
	{
		try
		{
			String sql = "Delete From SHMDB.Produtos Where IdProduto = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
			
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha na exclusão do Produto, erro: " + erro);
		}
	}
	
	public List<Produto> listarProdutos()
	{
		try
		{
			List<Produto> lista = new ArrayList<>();
			String sql = "Select * From SHMDB.Produtos";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				Produto prod = new Produto();
				prod.setId(rs.getInt("IdProduto"));
				prod.setNome(rs.getString("NomeProduto"));
				prod.setDescricao(rs.getString("DescricaoProduto"));
				prod.setPreco(rs.getDouble("PrecoProduto"));
				
				lista.add(prod);
			}
			
			return lista;
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha em listar os Produtos, erro: " + erro);
			return null;
		}
	}

}
