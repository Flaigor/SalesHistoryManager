package br.com.shm.jdbc;

import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;

public class TestaPdf {

	public static void main(String[] args) {		
		try
		{
			PdfFactory pdfFactory = new PdfFactory();
			pdfFactory.getPdf();
			JOptionPane.showMessageDialog(null, "PDF criado com Sucesso!");
		} catch(Exception erro)
		{
			JOptionPane.showMessageDialog(null, "Falha na criação do PDF, erro: " + erro);
		}
	}

}
