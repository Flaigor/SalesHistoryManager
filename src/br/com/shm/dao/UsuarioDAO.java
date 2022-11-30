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
import br.com.shm.model.Usuario;

public class UsuarioDAO {

	private Connection con;
	
	public UsuarioDAO(){
		this.con = new ConnectionFactory().getConnection();
	}
	
	public void cadastrarUsuario(Usuario user) {
		try
		{
			String sql = "Insert Into shmdb.users(NameUser, PasswordUser, AdminUser) "
					+ "Values (?, ?, ?)";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, user.getLogin());
			stmt.setString(2, user.getSenha());
			stmt.setBoolean(3, user.isAdmin());
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");
			
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha no cadastro do Usuario, erro: " + erro);
		}
	}
	
	public void alterarUsuario(Usuario user) {
		try
		{
			String sql = "Update shmdb.users set NameUser = ?, PasswordUser = ?, AdminUser = ? "
					+ "Where IdUser = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, user.getLogin());
			stmt.setString(2, user.getSenha());
			stmt.setBoolean(3, user.isAdmin());
			stmt.setString(4, user.getId().toString());
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Usuario alterado com sucesso!");
			
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha na alteracao do Usuario, erro: " + erro);
		}
	}
	
	public void excluirUsuario(String id) {
		try
		{
			String sql = "Delete From shmdb.users Where IdUser = ?";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			
			stmt.execute();
			stmt.close();
			
			JOptionPane.showMessageDialog(null, "Usuario excluido com sucesso!");
			
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha na exclusao do Usuario, erro: " + erro);
		}
	}
	
	public List<Usuario> listarUsuario() {
		try
		{
			List<Usuario> lista = new ArrayList<>();
			String sql = "SELECT * FROM shmdb.users";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				Usuario user = new Usuario();
				user.setId(rs.getInt("IdUser"));
				user.setLogin(rs.getString("NameUser"));
				user.setSenha(rs.getString("PasswordUser"));
				user.setAdmin(rs.getBoolean("AdminUser"));
				
				lista.add(user);
			}
			
			return lista;
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha em listar os Usuario, erro: " + erro);
			return null;
		}
	}
	
	public boolean validaUsuario(String loign, String senha) {
		try
		{
			String sql = "Select count(*) NumUser From shmdb.users where NameUser = ? and "
					+ "PasswordUser = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, loign);
			stmt.setString(2, senha);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				if(rs.getInt("NumUser") == 1)
				{
					return true;
				}
			}
			
			return false;
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha em validar o Usuario, erro: " + erro);
			return false;
		}
	}
	
	public Usuario getUsuario(String loign, String senha) {
		try
		{
			String sql = "Select * From shmdb.users where NameUser = ? and "
					+ "PasswordUser = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, loign);
			stmt.setString(2, senha);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				Usuario user = new Usuario();
				user.setId(rs.getInt("IdUser"));
				user.setLogin(rs.getString("NameUser"));
				user.setSenha(rs.getString("PasswordUser"));
				user.setAdmin(rs.getBoolean("AdminUser"));
				return user;
			}
			
			return null;
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha em achar o Usuario, erro: " + erro);
			return null;
		}
	}
}
