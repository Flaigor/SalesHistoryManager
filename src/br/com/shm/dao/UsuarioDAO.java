package br.com.shm.dao;

import java.sql.Connection;

import br.com.shm.jdbc.ConnectionFactory;

public class UsuarioDAO {

	private Connection con;
	
	public UsuarioDAO(){
		this.con = new ConnectionFactory().getConnection();
	}
}
