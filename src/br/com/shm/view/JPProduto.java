package br.com.shm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.shm.dao.ProdutosDAO;
import br.com.shm.jdbc.PdfFactory;
import br.com.shm.model.Produto;

public class JPProduto extends JPPadrao {
	
	private JTable tProdutos;
	private DefaultTableModel dados;
	private String[] colunas = {"ID","Nome","Descrição","Preço"};
	private List<Produto> produtos = new ArrayList<>();
	
	public JPProduto( JFPadrao frame, boolean admin )
	{
		montaTelaProduto( frame, admin );
	}
	
	public void listar()
	{
		ProdutosDAO dao = new ProdutosDAO();
		List<Produto> lista = dao.listarProdutos();
		dados = (DefaultTableModel) tProdutos.getModel();
		dados.setNumRows(0);
		
		dados.addRow(new Object[]{
				"Novo Produto",
				"",
				"",
				""
			});
		
		for(Produto p: lista)
		{
			dados.addRow(new Object[]{
				p.getId(),
				p.getNome(),
				p.getDescricao(),
				p.getPreco()
			});
		}
	}
	
	public void montaTelaProduto( JFPadrao frame, boolean admin )
	{
		limpaTela( );
		width = frame.getBounds( ).width;
		height = frame.getBounds( ).height;
		this.setBounds( 0, 0, width, height );
		
		JLabel labelIdProd = new JLabel("ID: ");
		labelIdProd.setBounds( 560, 10, 20, 30);
		
		JTextField tfIdProd = new JTextField();
		tfIdProd.setBounds( 590, 10 , 300, 30 );
		
		tfIdProd.setEnabled(false);
		
		JLabel labelNomeProd = new JLabel("Nome: ");
		labelNomeProd.setBounds( 10, 10, 60, 30);
		
		JTextField tfNomeProd = new JTextField();
		tfNomeProd.setBounds( 90, 10 , 300, 30 );
		
		JLabel labelPrecoProd = new JLabel("Preço: ");
		labelPrecoProd.setBounds( 400, 10, 40, 30);
		
		DecimalFormat dfPreco = new DecimalFormat("0.##");
		JFormattedTextField tfPrecoProd = new JFormattedTextField(dfPreco);
		tfPrecoProd.setColumns(7);
		tfPrecoProd.setBounds( 450, 10 , 100, 30 );
		
		JLabel labelDescricaoProd = new JLabel("Descrição: ");
		labelDescricaoProd.setBounds( 10, 50, 70, 30);
		
		JTextField tfDescricaoProd = new JTextField();
		tfDescricaoProd.setBounds( 90, 50 , width - 120, 30 );
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds( width - 150 , 10 , 120, 30 );
		
		JButton btnCadastrarProd = new JButton("Cadastrar");
		btnCadastrarProd.setBounds( 10 , height - 80 , 120, 30 );
		
		JButton btnAttProd = new JButton("Atualizar");
		btnAttProd.setBounds( 230 , height - 80 , 120, 30 );
		
		JButton btnDeletarProd = new JButton("Deletar");
		btnDeletarProd.setBounds( 450 , height - 80 , 120, 30 );
		
		JButton btnGerarPdf = new JButton("Gerar PDF");
		btnGerarPdf.setBounds( width - 280 , height - 80 , 120, 30 );
		
		tProdutos = new JTable(new DefaultTableModel(null, colunas));	
		JScrollPane scrollProdutos = new JScrollPane(tProdutos);
		
		tProdutos.setBounds( 10 , 90 , width - 40, height - 180 );
		scrollProdutos.setBounds( 10 , 90 , width - 40, height - 180 );
		
		JLabel labelResultado = new JLabel("");
		labelResultado.setBounds( 670 , height - 80 , 120, 30 );
		
		add(labelIdProd);
		add(tfIdProd);
		add(labelNomeProd);
		add(tfNomeProd);
		add(labelPrecoProd);
		add(tfPrecoProd);
		add(labelDescricaoProd);
		add(tfDescricaoProd);
		add(btnVoltar);
		add(btnCadastrarProd);
		add(btnAttProd);
		add(btnDeletarProd);
		add(btnGerarPdf);
		add(scrollProdutos);
		add(labelResultado);
		
		listar();
		
		frame.repaint();
		
		btnVoltar.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				frame.remove(JPProduto.this);
				frame.setTela(new JPMenuPrincipal(frame, admin), false);		
			}
		} );
		
		btnCadastrarProd.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				if(!tfNomeProd.getText().isEmpty() && !tfPrecoProd.getText().isEmpty())
				{
					Double preco = Double.parseDouble(tfPrecoProd.getText().replace(",", "."));
					Produto prod = new Produto(tfNomeProd.getText(), tfDescricaoProd.getText(), 
							preco);
					
					ProdutosDAO dao = new ProdutosDAO();
					dao.cadastrarProduto(prod);
					labelResultado.setText("");
					tfIdProd.setText("");
					tfNomeProd.setText("");
					tfDescricaoProd.setText("");
					tfPrecoProd.setText("");
					listar();
				}
				else
				{
					labelResultado.setText("Nome ou preço vazio");
				}
				
			}
		} );
		
		btnAttProd.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				if(!tfNomeProd.getText().isEmpty() && !tfPrecoProd.getText().isEmpty())
				{
					Double preco = Double.parseDouble(tfPrecoProd.getText().replace(",", "."));
					Produto prod = new Produto(Integer.parseInt(tfIdProd.getText()),tfNomeProd.getText(), 
							tfDescricaoProd.getText(), preco);
					ProdutosDAO dao = new ProdutosDAO();
					dao.alterarProduto(prod);
					labelResultado.setText("");
					listar();
				}
				else
				{
					labelResultado.setText("Nome ou preço vazio");
				}
				
			}
		} );
		
		btnDeletarProd.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				ProdutosDAO dao = new ProdutosDAO();
				if(produtos.size() == 1)
				{
					System.out.println("ID: " + produtos.get(0).getId().toString());
					System.out.println("Nome: " + produtos.get(0).getNome());
					dao.excluirProduto(produtos.get(0).getId());
				}
				else
				{
					for(int i = 0; i < produtos.size(); i++)
					{
						dao.excluirProduto(produtos.get(i).getId());
					}
				}
				listar();
				labelResultado.setText("");
				produtos.clear();
			}
		} );
		
		btnGerarPdf.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				if(produtos.isEmpty())
				{
					ProdutosDAO dao = new ProdutosDAO();
					List<Produto> listaProd = dao.listarProdutos();
					for(int i = 0; i < listaProd.size(); i++)
					{
						produtos.add(listaProd.get(i));
					}
				}
				
				PdfFactory pdfFactory = new PdfFactory();
				pdfFactory.gerarPdfProduto(produtos.toArray(new Produto[produtos.size()]));
			}
		} );
		
		tProdutos.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
				
			}

			public void mouseReleased(MouseEvent e) {
				
				produtos.clear();
				
				if(tProdutos.getSelectedRows().length == 1)
				{
					if(tProdutos.getSelectedRow() > 0)
					{
						ProdutosDAO dao = new ProdutosDAO();
						List<Produto> listaProd = dao.listarProdutos();
						Produto prod = new Produto();
						prod = listaProd.get(tProdutos.getSelectedRow() - 1);
					
						tfIdProd.setText(prod.getId().toString());
						tfNomeProd.setText(prod.getNome());
						tfDescricaoProd.setText(prod.getDescricao());
						tfPrecoProd.setText(prod.getPreco().toString().replace(".", ","));
						btnCadastrarProd.setEnabled(false);
						btnAttProd.setEnabled(true);
						btnDeletarProd.setEnabled(true);
						produtos.add(prod);
					}
					else if(tProdutos.getSelectedRow() == 0)
					{
						tfIdProd.setText("");
						tfNomeProd.setText("");
						tfDescricaoProd.setText("");
						tfPrecoProd.setText("");
						btnCadastrarProd.setEnabled(true);
						btnAttProd.setEnabled(false);
						btnDeletarProd.setEnabled(false);
					}
				}
				else
				{	
					List<Integer> linhas = new ArrayList<>();
					for(int i = 0; i < tProdutos.getSelectedRows().length; i++ )
					{
						if(tProdutos.getSelectedRows()[i] != 0)
						{
							linhas.add(tProdutos.getSelectedRows()[i]);
						}
					}
					
					ProdutosDAO dao = new ProdutosDAO();
					List<Produto> listaProd = dao.listarProdutos();
					for(int i = 0; i < linhas.size(); i++)
					{
						produtos.add(listaProd.get(linhas.get(i) - 1));
					}
					
					tfIdProd.setText("");
					tfNomeProd.setText("");
					tfDescricaoProd.setText("");
					tfPrecoProd.setText("");
					btnCadastrarProd.setEnabled(false);
					btnAttProd.setEnabled(false);
					btnDeletarProd.setEnabled(true);
				}
				
				System.out.println("Produtos Selecionados: ");
				for(int i = 0; i < produtos.size(); i++ )
				{
					System.out.println(produtos.get(i).getNome());
				}
				System.out.println("=========================================");
				
				if(produtos.isEmpty())
				{
					labelResultado.setText("Criar novo produto?");
				}
				else if(produtos.size() == 1)
				{
					labelResultado.setText( "1 Produto selecionado");
				}
				else
				{
					labelResultado.setText( produtos.size() + " Produtos selecionados");
				}
				
				
			}

			public void mouseEntered(MouseEvent e) {
				
			}

			public void mouseExited(MouseEvent e) {
				
			}
			
		});
		
		tfNomeProd.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				
			}

			public void keyPressed(KeyEvent e) {
				int contador = tfNomeProd.getText().length();
				if(contador >= 32)
				{
					tfNomeProd.setText(tfNomeProd.getText().substring(0,
							tfNomeProd.getText().length() - 1));
				}
			}

			public void keyReleased(KeyEvent e) {
				int contador = tfNomeProd.getText().length();
				if(contador >= 32)
				{
					tfNomeProd.setText(tfNomeProd.getText().substring(0,
							tfNomeProd.getText().length() - 1));
				}

			}
			
		});
		
		tfDescricaoProd.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				
			}

			public void keyPressed(KeyEvent e) {
				int contador = tfDescricaoProd.getText().length();
				if(contador >= 128)
				{
					tfDescricaoProd.setText(tfDescricaoProd.getText().substring(0,
							tfDescricaoProd.getText().length() - 1));
				}
			}

			public void keyReleased(KeyEvent e) {
				int contador = tfDescricaoProd.getText().length();
				if(contador >= 128)
				{
					tfDescricaoProd.setText(tfDescricaoProd.getText().substring(0,
							tfDescricaoProd.getText().length() - 1));
				}

			}
			
		});
		
	}

}
