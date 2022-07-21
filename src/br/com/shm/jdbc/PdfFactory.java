package br.com.shm.jdbc;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.shm.model.Cliente;
import br.com.shm.model.Produto;

public class PdfFactory {
	
	private LocalDateTime now = LocalDateTime.now();
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd_HHmmss");
	private DateTimeFormatter dtfDoc = DateTimeFormatter.ofPattern("dd/MM/YYYY - HH:mm:ss");
	private String dataHora = dtf.format(now);
	private String dataHoraDoc = dtfDoc.format(now);
	private Font Logo = new Font();

	public Document getPdf()
	{
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("docs\\pdf\\SalesHistoryManager_" + dataHora + ".pdf"));
			document.open();
			
			Logo.setStyle(Font.BOLD);
			Logo.setSize(24);
			
			document.add(new Paragraph("Sales History Manager", Logo));
			
			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(105);
			table.setSpacingBefore(11f);
			table.setSpacingAfter(11f);
			
			float[] colWidth = {2f,2f,2f};
			table.setWidths(colWidth);
			PdfPCell c01 = new PdfPCell(new Paragraph("Coluna 01"));
			PdfPCell c02 = new PdfPCell(new Paragraph("Coluna 02"));
			PdfPCell c03 = new PdfPCell(new Paragraph("Coluna 03"));
			PdfPCell c04 = new PdfPCell(new Paragraph("Coluna 04"));
			PdfPCell c05 = new PdfPCell(new Paragraph("Coluna 05"));
			PdfPCell c06 = new PdfPCell(new Paragraph("Coluna 06"));
			
			table.addCell(c01);
			table.addCell(c02);
			table.addCell(c03);
			table.addCell(c04);
			table.addCell(c05);
			table.addCell(c06);
			
			document.add(table);
			
			List orderList = new List(List.ORDERED);
			orderList.add(new ListItem("Item 01"));
			orderList.add(new ListItem("Item 02"));
			orderList.add(new ListItem("Item 03"));
			document.add(orderList);
			
			List unorderList = new List(List.UNORDERED);
			unorderList.add(new ListItem("Item 04"));
			unorderList.add(new ListItem("Item 05"));
			unorderList.add(new ListItem("Item 06"));
			document.add(unorderList);
			
			document.close();
			writer.close();
			JOptionPane.showMessageDialog(null, "PDF criado com Sucesso!");
		} 
		catch(DocumentException de)
		{
			de.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha na criação do PDF, erro: " + de);
		} 
		catch(FileNotFoundException fne)
		{
			fne.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha na criação do PDF, erro: " + fne);
		} 
		return document;
	}
	
	public Document getPdfCliente(Cliente[] clientes)
	{		
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("docs\\pdf\\clientes\\SalesHistoryManagerClientes_" 
					+ dataHora + ".pdf"));
			document.open();
			
			Logo.setStyle(Font.BOLD);
			Logo.setSize(24);
			
			document.add(new Paragraph("Sales History Manager", Logo));
			document.add(new Paragraph("Clientes: " + dataHoraDoc));
			
			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(105);
			table.setSpacingBefore(11f);
			table.setSpacingAfter(11f);
			
			float[] colWidth = {2f,2f,2f};
			table.setWidths(colWidth);
			PdfPCell cNome = new PdfPCell(new Paragraph("Nome"));
			PdfPCell cEndereço = new PdfPCell(new Paragraph("Endereço"));
			PdfPCell cTelefone = new PdfPCell(new Paragraph("Telefone"));
			
			table.addCell(cNome);
			table.addCell(cEndereço);
			table.addCell(cTelefone);
			
			for(int i = 0; i < clientes.length; i++)
			{
				PdfPCell cliNome = new PdfPCell(new Paragraph(clientes[i].getNome()));
				PdfPCell cliEndereco = new PdfPCell(new Paragraph(clientes[i].getEndereco()));
				PdfPCell cliTelefone = new PdfPCell(new Paragraph(clientes[i].getTelefone()));
				
				table.addCell(cliNome);
				table.addCell(cliEndereco);
				table.addCell(cliTelefone);
			}
			
			document.add(table);
			
			List unorderList = new List(List.UNORDERED);
			unorderList.add(new ListItem("Total de Cliente: " + clientes.length));
			document.add(unorderList);
			
			document.close();
			writer.close();
			JOptionPane.showMessageDialog(null, "PDF criado com Sucesso!");
		} 
		catch(DocumentException de)
		{
			de.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha na criação do PDF, erro: " + de);
		} 
		catch(FileNotFoundException fne)
		{
			fne.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha na criação do PDF, erro: " + fne);
		} 
		return document;
	}
	
	public Document gerarPdfProduto(Produto[] produto)
	{		
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("docs\\pdf\\produtos\\SalesHistoryManagerProdutos_" 
					+ dataHora + ".pdf"));
			document.open();
			
			Logo.setStyle(Font.BOLD);
			Logo.setSize(24);
			
			document.add(new Paragraph("Sales History Manager", Logo));
			document.add(new Paragraph("Produtos: " + dataHoraDoc));
			
			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(105);
			table.setSpacingBefore(11f);
			table.setSpacingAfter(11f);
			
			float[] colWidth = {2f,2f,2f};
			table.setWidths(colWidth);
			PdfPCell cNome = new PdfPCell(new Paragraph("Nome"));
			PdfPCell cDescricao = new PdfPCell(new Paragraph("Descrição"));
			PdfPCell cPreco = new PdfPCell(new Paragraph("Preço em Reais"));
			
			table.addCell(cNome);
			table.addCell(cDescricao);
			table.addCell(cPreco);
			
			for(int i = 0; i < produto.length; i++)
			{
				PdfPCell prodNome = new PdfPCell(new Paragraph(produto[i].getNome()));
				PdfPCell prodDescricao = new PdfPCell(new Paragraph(produto[i].getDescricao()));
				PdfPCell prodPreco = new PdfPCell(new Paragraph(produto[i].getPreco().toString()));
				
				table.addCell(prodNome);
				table.addCell(prodDescricao);
				table.addCell(prodPreco);
			}
			
			document.add(table);
			
			List unorderList = new List(List.UNORDERED);
			unorderList.add(new ListItem("Total de Produtos: " + produto.length));
			document.add(unorderList);
			
			document.close();
			writer.close();
			JOptionPane.showMessageDialog(null, "PDF criado com Sucesso!");
		} 
		catch(DocumentException de)
		{
			de.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha na criação do PDF, erro: " + de);
		} 
		catch(FileNotFoundException fne)
		{
			fne.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha na criação do PDF, erro: " + fne);
		} 
		return document;
	}
}
