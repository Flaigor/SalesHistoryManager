package br.com.shm.jdbc;

import java.util.ArrayList;
import java.util.List;

public class TestaPrevisao {

	public static void main(String[] args) {
		
		List<Integer> matriz = new ArrayList<Integer>();
		List<Integer> novaMatriz = new ArrayList<Integer>();
		int resultado;
		
		// 4° Ano
		matriz.add(9);		// Janeiro
		matriz.add(20);		// Fevereiro
		matriz.add(7);		// Março
		matriz.add(10);		// Abril
		matriz.add(17);		// Maio
		matriz.add(15);		// Junho
		matriz.add(16);		// Julho
		matriz.add(8);		// Agosto
		matriz.add(10);		// Setembro
		matriz.add(13);		// Outubro
		matriz.add(21);		// Novembro
		matriz.add(23);		// Dezembro
		
		// 3° Ano
		matriz.add(12);		// Janeiro
		matriz.add(18);		// Fevereiro
		matriz.add(10);		// Março
		matriz.add(12);		// Abril
		matriz.add(21);		// Maio
		matriz.add(18);		// Junho
		matriz.add(14);		// Julho
		matriz.add(11);		// Agosto
		matriz.add(15);		// Setembro
		matriz.add(17);		// Outubro
		matriz.add(25);		// Novembro
		matriz.add(30);		// Dezembro
		
		// 2° Ano
		matriz.add(4);		// Janeiro
		matriz.add(5);		// Fevereiro
		matriz.add(20);		// Março
		matriz.add(9);		// Abril
		matriz.add(13);		// Maio
		matriz.add(12);		// Junho
		matriz.add(5);		// Julho
		matriz.add(11);		// Agosto
		matriz.add(12);		// Setembro
		matriz.add(7);		// Outubro
		matriz.add(18);		// Novembro
		matriz.add(21);		// Dezembro
		
		// 1° Ano
		/*
		matriz.add(13); 	// Janeiro
		matriz.add(19); 	// Fevereiro
		matriz.add(12); 	// Março
		matriz.add(16);		// Abril
		matriz.add(13);		// Maio
		matriz.add(16);		// Junho
		matriz.add(17);		// Julho
		matriz.add(12);		// Agosto
		matriz.add(11);		// Setembro
		matriz.add(20);		// Outubro
		matriz.add(28);		// Novembro
		matriz.add(31);		// Dezembro
		*/
		
		matriz.add(0); 		// Janeiro
		matriz.add(0); 		// Fevereiro
		matriz.add(0); 		// Março
		matriz.add(0);		// Abril
		matriz.add(0);		// Maio
		matriz.add(0);		// Junho
		matriz.add(0);		// Julho
		matriz.add(0);		// Agosto
		matriz.add(0);		// Setembro
		matriz.add(0);		// Outubro
		matriz.add(0);		// Novembro
		matriz.add(0);		// Dezembro
		
		//System.out.println(matriz.get(0 + 3 + 4 + 4));
		
		PrevisaoFactory PF = new PrevisaoFactory();
		
		//novaMatriz =  PF.arrumarArray(matriz, 4);
		//System.out.println("Tamanho da Lista: " + novaMatriz.size());
		
		//resultado = PF.previsaoMes(novaMatriz, 4);
		//novaMatriz.add(resultado);
		//System.out.println("Resultado: " + resultado);
		
		//matriz = PF.previsaoAno(matriz, 1);
		
		for(int i = 0; i < matriz.size(); i += 12)
		{
			System.out.println("");
			for(int j = 0; j < 12; j++)
			{
				System.out.print(matriz.get(i + j) + " ");
			}

		}
		
	}

}
