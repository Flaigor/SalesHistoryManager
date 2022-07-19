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

public class PdfFactory {
	
	private LocalDateTime now = LocalDateTime.now();
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd_HHmmss");
	private String data = dtf.format(now);

	public Document getPdf()
	{
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("docs\\pdf\\SalesHistoryManager" + data + ".pdf"));
			document.open();
			
			Font f = new Font();
			f.setStyle(Font.BOLD);
			f.setSize(24);
			
			document.add(new Paragraph("Sales History Manager", f));
			
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
		} 
		catch(DocumentException de)
		{
			de.printStackTrace();
		} 
		catch(FileNotFoundException fne)
		{
			fne.printStackTrace();
		} 
		return document;
	}
}
