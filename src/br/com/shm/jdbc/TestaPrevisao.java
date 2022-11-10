package br.com.shm.jdbc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.shm.dao.ClientesDAO;
import br.com.shm.dao.PrevisaoDAO;
import br.com.shm.model.Cliente;

public class TestaPrevisao {
	
	private static LocalDateTime now = LocalDateTime.now();
	private static List<Integer> somaMeses = new ArrayList<Integer>();

	public static void main(String[] args) {
		
		for(int i = 0; i < 12; i++)
		{
			somaMeses.add(0);
		}
		
		System.out.println("");
		System.out.println(somaMeses);
		
		List<Cliente> clientes = new  ArrayList<Cliente>();
		
		ClientesDAO cliDao = new ClientesDAO();
		
		clientes = cliDao.listarClientes();
		
		int soma;
		int somaTotal = 0;
		
		for(Cliente cli : clientes)
		{
			soma = getSoma(cli);
			somaTotal += soma;
			System.out.println("Soma: " + soma);
		}
		
		System.out.println("");
		System.out.println(somaTotal);
		System.out.println("");
		System.out.println(somaMeses);
		
	}
	
	public static int getSoma(Cliente cliente)
	{
		PrevisaoFactory PF = new PrevisaoFactory();
		PrevisaoDAO preDao = new PrevisaoDAO();
		
		List<Integer> lp = new ArrayList<Integer>();
		List<Integer> laux = new ArrayList<Integer>();
		int soma = 0;
		int ano = 0;
		
		System.out.println(cliente.getNome());
		for(int i = 3; i >= 0; i--)
		{
			ano = (now.getYear()) - i;
			laux = preDao.getListPrevisaoCli(cliente , ano);
			
			for(Integer j : laux)
			{
				lp.add(j);
			}
			System.out.println("");
			System.out.print(laux + " " + ano);
			
			laux.clear();
		}
		
		for(int i = lp.size(); i <= 47; i++)
		{
			lp.add(0);
		}
		
		PF.previsaoAno(lp, now.getMonthValue() + 1);
		
		System.out.println("");
		for(int i = lp.size() - 12; i < lp.size(); i++)
		{
			System.out.print(lp.get(i) + " ");
			somaMeses.set(i - 36, somaMeses.get(i - 36) + lp.get(i));
			laux.add(lp.get(i));
			soma += lp.get(i);
		}
		
		return soma;
	}
}
