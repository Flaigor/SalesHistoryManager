package br.com.shm.jdbc;

import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;

public class TestaPdf {

	public static void main(String[] args) {		
		PdfFactory pdfFactory = new PdfFactory();
		pdfFactory.getPdf();
	}

}
