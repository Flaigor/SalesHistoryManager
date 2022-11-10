package br.com.shm.jdbc;

import java.util.ArrayList;
import java.util.List;

import br.com.shm.dao.ClientesDAO;
import br.com.shm.dao.PrevisaoDAO;
import br.com.shm.model.Cliente;

public class PrevisaoFactory {
	
	private final Double pesoAlvo = 1.2;
	private final Double pesoPrimeiroAno = 1.1;
	private final Double pesoSegundoAno = 1.0;
	private final Double pesoTerceiroAno = 0.9;
	private final Double pesoQuartoAno = 0.8;
	
	private Double mediaAlvo = 0.0;
	private	Double mediaPrimeiroAno = 0.0;
	private	Double mediaSegundoAno = 0.0;
	private	Double mediaTerceiroAno = 0.0;
	private	Double mediaQuartoAno = 0.0;
	private	Double mediaAnos = 0.0;
	
	private Integer resultadoPrevisao = 0;

	public int previsaoMes(List<Integer> listaNum, int numMes)
	{
		double divisorMeses = (numMes == 1 ? 1.0 : (numMes - 1.0));
		
		for(int i = numMes - 1; i < listaNum.size(); i += numMes)
		{
			mediaAlvo += listaNum.get(i);
		}
		
		if(numMes > 1)
		{
			for(int i = numMes * 0; i < (numMes * 1) - 1; i++)
			{
				mediaQuartoAno += listaNum.get(i);
			}

			for(int i = numMes * 1; i < (numMes * 2) - 1; i++)
			{
				mediaTerceiroAno += listaNum.get(i);
			}

			for(int i = numMes * 2; i < (numMes * 3) - 1; i++)
			{
				mediaSegundoAno += listaNum.get(i);
			}

			for(int i = numMes * 3; i < (numMes * 4) - 1; i++)
			{
				mediaPrimeiroAno += listaNum.get(i);
			}
		}
		
		mediaAlvo /= 3.00;
		mediaQuartoAno /= divisorMeses;
		mediaTerceiroAno /= divisorMeses;
		mediaSegundoAno /= divisorMeses;
		mediaPrimeiroAno /= divisorMeses;
		
		mediaAlvo *= pesoAlvo;
		mediaQuartoAno *= pesoQuartoAno;
		mediaTerceiroAno *= pesoTerceiroAno;
		mediaSegundoAno *= pesoSegundoAno;
		mediaPrimeiroAno *= pesoPrimeiroAno;
		
		mediaAnos = mediaQuartoAno + mediaTerceiroAno + mediaSegundoAno + mediaPrimeiroAno;
		
		mediaAnos /= 4;
		
		mediaAlvo += mediaAnos;
		
		mediaAlvo /= 2;
		
		resultadoPrevisao = mediaAlvo.intValue();
	
		return resultadoPrevisao;
	}
	
	
	public void previsaoAno(List<Integer> listaNum, int mesIni)
	{
		List<Integer> pedaco = new ArrayList<Integer>();
		int umQuarto = listaNum.size() / 4;
		
		for(int i = mesIni; i <= umQuarto; i++)
		{
			pedaco = arrumarArray(listaNum, i );
			listaNum.set( (umQuarto * 3) + (i - 1), previsaoMes(pedaco, i));
		}
	}
	
	
	public List<Integer> arrumarArray(List<Integer> listaNum, int numMes )
	{
		List<Integer> novaLista = new ArrayList<Integer>();
		int umQuarto = listaNum.size() / 4;
		
		for(int i = 0; i < listaNum.size(); i += umQuarto)
		{
			for(int j = 0; j < numMes; j++)
			{
				if(j != numMes - 1 || i != (umQuarto * 3))
				{
					novaLista.add(listaNum.get(j + i));
				}
			}
		}																													
		return novaLista;
	}
	
	public List<Integer> populaMatrixVendaMes(int ano, int mes)
	{
		List<Integer> matrixPrevisao = new ArrayList<Integer>();
		
		PrevisaoDAO dao = new PrevisaoDAO();
		
		ClientesDAO cliDao = new ClientesDAO();
		List<Cliente> clientes = new  ArrayList<Cliente>();
		clientes = cliDao.listarClientes();
		
		List<Integer> lp = new ArrayList<Integer>();
		List<Integer> laux = new ArrayList<Integer>();
		
		int contador;
		
		for(int i = 0; i < clientes.size(); i++)
		{
			lp.clear();
			for(int j = 3; j >= 0; j--)
			{
				laux = dao.getListPrevisaoCli(clientes.get(i), ano - j);
				
				for(Integer z : laux)
				{
					lp.add(z);
				}
				laux.clear();
			}
			
			for(int j = lp.size(); j <= 47; j++)
			{
				lp.add(0);
			}
			
			previsaoAno(lp, mes + 1);
						
			for(int j = lp.size() - 12; j < lp.size(); j++)
			{
				laux.add(lp.get(j));
			}
			
			prepararMatrix(matrixPrevisao);
			
			contador = 0;
			for(int j = 12 * i; j < 12 * (i + 1); j++)
			{
				matrixPrevisao.set(j, laux.get(contador));
				contador++;
			}
		}
		
		return matrixPrevisao;
	}
	
	public void prepararMatrix(List<Integer> matrix) 
	{
		ClientesDAO cliDao = new ClientesDAO();
		List<Cliente> clientes = new  ArrayList<Cliente>();
		clientes = cliDao.listarClientes();
		
		for(int i = 0; i < 12 * clientes.size(); i++)
		{
			matrix.add(0);
		}
	}
	
	public void printMatrix(List<Integer> matrix)
	{
		ClientesDAO cliDao = new ClientesDAO();
		List<Cliente> clientes = new  ArrayList<Cliente>();
		clientes = cliDao.listarClientes();
		
		for(int i = 0; i < clientes.size(); i++)
		{
			for(int j = 12 * i;  j < 12 * (i + 1); j++)
			{
				System.out.print(matrix.get(j) + " ");
			}
			System.out.println("");
		}
	}
}
