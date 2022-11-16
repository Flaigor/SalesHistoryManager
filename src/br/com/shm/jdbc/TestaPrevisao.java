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
	//private List<Integer> matrixPrevisao = new ArrayList<Integer>();

	public static void main(String[] args) {
		
		PrevisaoFactory pf = new PrevisaoFactory();
		List<Integer> matrixPrevisao = new ArrayList<Integer>();
		
		matrixPrevisao = pf.populaMatrixProdutoMes( now.getYear(), now.getMonthValue());
		pf.printMatrix(matrixPrevisao);

		
	}
}
