package br.com.shm.jdbc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JOptionPane;

import br.com.shm.dao.ProdutoVendaDAO;
import br.com.shm.model.Cliente;
import br.com.shm.model.Produto;
import br.com.shm.model.ProdutoVenda;
import br.com.shm.model.Venda;

public class PdfFactory {
	
	private LocalDateTime now = LocalDateTime.now();
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd_HHmmss");
	private DateTimeFormatter dtfDoc = DateTimeFormatter.ofPattern("dd/MM/YYYY - HH:mm:ss");
	private String dataHora = dtf.format(now);
	private String dataHoraDoc = dtfDoc.format(now);
	private Font Logo = new Font();
	//private DecimalFormat dfpreco = new DecimalFormat(".##");

	public void getPdf()
	{
		Document document = new Document();
		String caminho = "docs\\pdf\\SalesHistoryManager_";
		try {
			
			caminho += dataHora + ".pdf";
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(caminho));
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

			abrirPdf(caminho);
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
	}
	
	public void gerarPdfCliente(Cliente[] clientes)
	{		
		Document document = new Document();
		String caminho = "docs\\pdf\\clientes\\SalesHistoryManagerClientes_" + dataHora + ".pdf";
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(caminho));
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
			
			abrirPdf(caminho);
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
	}
	
	public void gerarPdfProduto(Produto[] produto)
	{		
		Document document = new Document();
		String caminho = "docs\\pdf\\produtos\\SalesHistoryManagerProdutos_" + dataHora + ".pdf";
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(caminho));
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
			
			abrirPdf(caminho);
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
	}
	
	public void gerarPdfVenda(Venda[] venda)
	{		
		Document document = new Document();
		String caminho = "docs\\pdf\\vendas\\SalesHistoryManagerVendas_" + dataHora + ".pdf";
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(caminho));
			document.open();
			
			Logo.setStyle(Font.BOLD);
			Logo.setSize(24);
			
			document.add(new Paragraph("Sales History Manager", Logo));
			document.add(new Paragraph("Vendas: " + dataHoraDoc));
			
			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(105);
			table.setSpacingBefore(11f);
			table.setSpacingAfter(11f);
			
			float[] colWidth = {2f,2f,2f,2f};
			table.setWidths(colWidth);
			PdfPCell cCliente = new PdfPCell(new Paragraph("Cliente"));
			PdfPCell cData = new PdfPCell(new Paragraph("Data"));
			PdfPCell cDescricao = new PdfPCell(new Paragraph("Descricao"));
			PdfPCell cPago = new PdfPCell(new Paragraph("Pago"));
			
			table.addCell(cCliente);
			table.addCell(cData);
			table.addCell(cDescricao);
			table.addCell(cPago);
			
			for(int i = 0; i < venda.length; i++)
			{
				
				PdfPCell venCliente = new PdfPCell(new Paragraph(venda[i].getComprador().getNome().toString()));
				PdfPCell venData = new PdfPCell(new Paragraph(venda[i].getDataVenda()));
				PdfPCell venDescricao = new PdfPCell(new Paragraph(venda[i].getDescricao()));
				PdfPCell venPago = new PdfPCell(new Paragraph(venda[i].getPago() ? "Sim" : "Não"));
			
				table.addCell(venCliente);
				table.addCell(venData);
				table.addCell(venDescricao);
				table.addCell(venPago);
			}
			
			document.add(table);
			
			List unorderList = new List(List.UNORDERED);
			unorderList.add(new ListItem("Total de Vendas: " + venda.length));
			document.add(unorderList);
			
			ProdutoVendaDAO daoProdVen = new ProdutoVendaDAO();
			java.util.List<ProdutoVenda> listaProdVen = new ArrayList<>();
			for(int i = 0; i < venda.length; i++)
			{
				listaProdVen = daoProdVen.listarProdutoInnerVenda(venda[i].getId());
				document = tabelaProdVend(document, listaProdVen.toArray(new ProdutoVenda[listaProdVen.size()]), venda[i]);
			}

			document.close();
			writer.close();

			abrirPdf(caminho);
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
	}
	
	public Document tabelaProdVend(Document document, ProdutoVenda[] prodVenda, Venda venda)
	{
		try {
			Logo.setStyle(Font.BOLD);
			Logo.setSize(16);
			
			document.add(new Paragraph(venda.getComprador().getNome() + " - " + venda.getDataVenda(), Logo));
			
			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(105);
			table.setSpacingBefore(11f);
			table.setSpacingAfter(11f);
			
			float[] colWidth = {2f,2f,2f,2f};
			table.setWidths(colWidth);
			
			PdfPCell cProduto = new PdfPCell(new Paragraph("Produto"));
			PdfPCell cDescricao = new PdfPCell(new Paragraph("Descricao"));
			PdfPCell cPreco = new PdfPCell(new Paragraph("Preço unitário em Reais"));
			PdfPCell cQuantidade = new PdfPCell(new Paragraph("Quantidade"));
			
			table.addCell(cProduto);
			table.addCell(cDescricao);
			table.addCell(cPreco);
			table.addCell(cQuantidade);
			
			Double valor = 0.0;
			int qtd = 0;
			for(int i = 0; i < prodVenda.length; i++)
			{
				
				PdfPCell prodVenProduto = new PdfPCell(new Paragraph(prodVenda[i].getProduto().getNome()));
				PdfPCell prodVenDescricao = new PdfPCell(new Paragraph(prodVenda[i].getProduto().getDescricao()));
				PdfPCell prodVenPreco = new PdfPCell(new Paragraph(prodVenda[i].getValor().toString()));
				PdfPCell prodVenQuantidade = new PdfPCell(new Paragraph(prodVenda[i].getQuantidade().toString()));
			
				table.addCell(prodVenProduto);
				table.addCell(prodVenDescricao);
				table.addCell(prodVenPreco);
				table.addCell(prodVenQuantidade);
				
				qtd += prodVenda[i].getQuantidade();
				valor += (prodVenda[i].getValor() * prodVenda[i].getQuantidade());
			}
			
			document.add(table);
			
			List unorderList = new List(List.UNORDERED);
			unorderList.add(new ListItem("Numero de Produtos: " + prodVenda.length + " Total de Produtos: " + qtd 
					+ " Valor total dos Produtos: " + valor));
			document.add(unorderList);
			
			return document;
			
		} 
		catch(DocumentException de)
		{
			de.printStackTrace();
		} 
		
		
		return null;
	}
	
	public void gerarPdfHistorico(ProdutoVenda[] pordVen, String[] colunas, String[] pesquisa)
	{		
		Document document = new Document();
		String caminho = "docs\\pdf\\historico\\SalesHistoryManagerHistorico_" + dataHora + ".pdf";
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(caminho));
			document.open();
			
			Logo.setStyle(Font.BOLD);
			Logo.setSize(24);
			
			document.add(new Paragraph("Sales History Manager", Logo));
			document.add(new Paragraph("Historico de " + pesquisa[0] + ": " + dataHoraDoc));
			document.add(new Paragraph("Filtros: " + pesquisa[1] + " e " + pesquisa[2]));

			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(105);
			table.setSpacingBefore(11f);
			table.setSpacingAfter(11f);
			
			float[] colWidth = {2f,2f};
			table.setWidths(colWidth);
			PdfPCell coluna1 = new PdfPCell(new Paragraph(colunas[0]));
			PdfPCell coluna2 = new PdfPCell(new Paragraph(colunas[1]));
			
			table.addCell(coluna1);
			table.addCell(coluna2);
			
			switch(pesquisa[0])
			{
				case "Cliente":
					for(int i = 0; i < pordVen.length; i++)
					{
						PdfPCell histCampo1 = new PdfPCell(new Paragraph(pordVen[i].getVenda().getComprador().getNome()));
						PdfPCell histCampo2 = new PdfPCell(new Paragraph(pordVen[i].getQuantidade().toString()));
						
						table.addCell(histCampo1);
						table.addCell(histCampo2);
					}
					break;
				case "Venda":
					for(int i = 0; i < pordVen.length; i++)
					{
						PdfPCell histCampo1 = new PdfPCell(new Paragraph(pordVen[i].getVenda().getDataVenda()));
						PdfPCell histCampo2 = new PdfPCell(new Paragraph(pordVen[i].getQuantidade().toString()));
						
						table.addCell(histCampo1);
						table.addCell(histCampo2);
					}
					break;
				case "Produto":
					for(int i = 0; i < pordVen.length; i++)
					{
						PdfPCell histCampo1 = new PdfPCell(new Paragraph(pordVen[i].getProduto().getNome()));
						PdfPCell histCampo2 = new PdfPCell(new Paragraph(pordVen[i].getQuantidade().toString()));
						
						table.addCell(histCampo1);
						table.addCell(histCampo2);
					}
					break;
			}
			
			document.add(table);
			
			List unorderList = new List(List.UNORDERED);
			unorderList.add(new ListItem("Total de linhas: " + pordVen.length));
			document.add(unorderList);
			
			document.close();
			writer.close();
			
			abrirPdf(caminho);
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
	}
	
	public void abrirPdf(String caminho)
	{
		caminho = new File("").getAbsolutePath() + "\\" + caminho;
		
		try
		{
			Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler " + caminho);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "PDF não pode ser exibido: " + e);
		}
	}
}
