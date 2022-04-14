package br.com.shm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.shm.jdbc.ConnectionFactory;
import br.com.shm.model.ProdutoVenda;

public class HistoricoDAO {

	private Connection con;
	
	public HistoricoDAO(){
		this.con = new ConnectionFactory().getConnection();
	}
	
	public List<ProdutoVenda> montarSelect(int tipo, int pesquisa, int ordem)
	{
		String sql = "";
		if(tipo == 0)
		{
			if(pesquisa == 0)
			{
				sql = "Select c.NomeCliente cliente, count(*) compras from shmdb.clientes c "
						+ "inner join shmdb.vendas v on c.IdCliente = v.IdCliente "
						+ "group by c.NomeCliente order by count(*)";
			}
			else if(pesquisa == 1)
			{
				sql = "Select c.NomeCliente cliente, count(*) dividas from shmdb.clientes c "
						+ "inner join shmdb.vendas v on c.IdCliente = v.IdCliente and v.PagoVenda = 0 "
						+ "group by c.NomeCliente order by count(*)";
			}
		}
		else if(tipo == 1)
		{
			if(pesquisa == 0)
			{
				sql = "Select month(str_to_date(DataVenda, '%d/%m/%Y')) mes, count(IdVenda) vendas "
					+ "from shmdb.vendas group by month(str_to_date(DataVenda, '%d/%m/%Y'))";
			}
			else if(pesquisa == 1)
			{
				sql = "Select year(str_to_date(DataVenda, '%d/%m/%Y')) ano, count(IdVenda) vendas "
						+ "from shmdb.vendas group by year(str_to_date(DataVenda, '%d/%m/%Y'));";
			}
		}
		else if(tipo == 2)
		{
			if(pesquisa == 0)
			{
				sql += "SELECT p.NomeProduto produto, sum(pv.QuantidadeProdutoVenda) quantidade FROM shmdb.produtos p "
						+ "inner join shmdb.produtovenda pv on p.IdProduto = pv.IdProduto "
						+ "group by p.NomeProduto order by sum(pv.QuantidadeProdutoVenda)";
			}
		}
		
		if(ordem == 0)
		{
			sql += " asc;";
			
		}
		else if(ordem == 1)
		{
			sql += " desc;";
		}
		
		try
		{
			List<ProdutoVenda> pv = new ArrayList<>();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				
			}
			
		} catch(SQLException erro)
		{
			JOptionPane.showMessageDialog(null, "Falha no retorno do histórico, erro: " + erro);
		}
		
		return null;

	}
}
