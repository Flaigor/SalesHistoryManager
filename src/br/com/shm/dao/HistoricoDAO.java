package br.com.shm.dao;

import java.sql.Connection;

import br.com.shm.jdbc.ConnectionFactory;

public class HistoricoDAO {

private Connection con;
	
	public HistoricoDAO(){
		this.con = new ConnectionFactory().getConnection();
	}
	
}
