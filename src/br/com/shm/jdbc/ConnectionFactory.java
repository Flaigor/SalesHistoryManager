package br.com.shm.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	public Connection getConnection()
	{
		try
		{
			return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shmdb", "admin", "123");
		} catch(Exception erro)
		{
			throw new RuntimeException(erro);
		}
	}
	
}
