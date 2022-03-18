package br.com.shm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.shm.jdbc.ConnectionFactory;
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
					+ "Values (?, TO_DATE('?', 'DD/MM/YYYY'), ?, ?)";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, vend.getId());
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
			String sql = "Update SHMDB.Vendas set DataVenda = TO_DATE('?', 'DD/MM/YYYY'), DescricaoVenda = ?, "
					+ "PagoVenda = ?, Where IdVenda = ?";
			
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
}
