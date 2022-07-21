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

public class ClientesDAO {
	
	private Connection con;
	
	public ClientesDAO(){
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void cadastrarCliente(Cliente cli)
	{
		try
		{
			String sql = "Insert Into SHMDB.Clientes(NomeCliente, EnderecoCliente, TelefoneCliente) "
					+ "Values (?, ?, ?)";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cli.getNome());
			stmt.setString(2, cli.getEndereco());
			stmt.setString(3, cli.getTelefone());
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
			
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha no cadastro do Cliente, erro: " + erro);
		}
	}
	
	public void alterarCliente(Cliente cli)
	{
		try
		{
			String sql = "Update SHMDB.Clientes set NomeCliente = ?, EnderecoCliente = ?, TelefoneCliente = ? "
					+ "Where IdCliente = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cli.getNome());
			stmt.setString(2, cli.getEndereco());
			stmt.setString(3, cli.getTelefone());
			stmt.setString(4, cli.getId().toString());
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
			
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha na alteração do Cliente, erro: " + erro);
		}
	}
	
	public void excluirCliente(int id) 
	{
		try
		{
			String sql = "Delete From SHMDB.Clientes Where IdCliente = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
			
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha na exclusão do Cliente, erro: " + erro);
		}
	}
	
	public List<Cliente> listarClientes()
	{
		try
		{
			List<Cliente> lista = new ArrayList<>();
			String sql = "Select * From SHMDB.Clientes";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				Cliente cli = new Cliente();
				cli.setId(rs.getInt("IdCliente"));
				cli.setNome(rs.getString("NomeCliente"));
				cli.setEndereco(rs.getString("EnderecoCliente"));
				cli.setTelefone(rs.getString("TelefoneCliente"));
				
				lista.add(cli);
			}
			
			return lista;
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha em listar os Clientes, erro: " + erro);
			return null;
		}
	}

}
