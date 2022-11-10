package br.com.shm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.shm.dao.ClientesDAO;
import br.com.shm.dao.HistoricoDAO;
import br.com.shm.dao.PrevisaoDAO;
import br.com.shm.dao.ProdutosDAO;
import br.com.shm.jdbc.GraficoFactory;
import br.com.shm.jdbc.PdfFactory;
import br.com.shm.jdbc.PrevisaoFactory;
import br.com.shm.model.Cliente;
import br.com.shm.model.Produto;
import br.com.shm.model.ProdutoVenda;

public class JPPrevisao extends JPPadrao {
	
	private JTable tResultadoPrevMeses;
	private JScrollPane scrollResPrevMeses;
	private JTable tResultadoPrevCli;
	private JScrollPane scrollResPrevCli;
	private JTable tResultadoPrevProd;
	private JScrollPane scrollResPrevProd;
	private DefaultTableModel dadosMes;
	private DefaultTableModel dadosCli;
	private String[] stTipo = {"Cliente", "Venda", "Produto", "Previsão"};
	private String[] colRespPrevMeses = {"Mês", "Numero de Vendas"};
	private String[] colRespPrevCli = {"Cliente", "Numero de Compras"};
	private String[] colRespPrevProd = {"Produto", "Previsão de Venda"};
	private List<String> stAnos = new ArrayList<String>();
	private LocalDateTime now = LocalDateTime.now();
	private List<Integer> matrixPrevisao = new ArrayList<Integer>();
	private List<Integer> somaMeses = new  ArrayList<Integer>();
	private List<Integer> somaClientes = new  ArrayList<Integer>();
	private List<Cliente> clientes = new  ArrayList<Cliente>();
	
	public JPPrevisao( JFPadrao frame, boolean admin )
	{
		montaTelaHistorico(frame, admin);
	}
	
	public void listarAnos()
	{
		stAnos.add("" + now.getYear());
		stAnos.add("" + (now.getYear() + 1));
	}
	
	public void popularTabelaMes()
	{
		HistoricoDAO dao = new HistoricoDAO();
		
		dadosMes = (DefaultTableModel) tResultadoPrevMeses.getModel();
		dadosMes.setNumRows(0);
		for(int i = 0; i < somaMeses.size(); i++)
		{
			dadosMes.addRow(new Object[]{
					dao.getMeses().get(i),
					somaMeses.get(i)
			});
		}
	}
	
	public void popularTabelaCli()
	{	
		dadosCli = (DefaultTableModel) tResultadoPrevCli.getModel();
		dadosCli.setNumRows(0);
		
		for(int i = 0; i < clientes.size(); i++)
		{
			dadosCli.addRow(new Object[]{
					clientes.get(i).getNome(),
					somaClientes.get(i)
			});
		}
	}
	
	public void montaTelaHistorico( JFPadrao frame, boolean admin )
	{
		limpaTela( );
		width = frame.getBounds( ).width;
		height = frame.getBounds( ).height;
		this.setBounds( 0, 0, width, height );
		
		JLabel labelTipo = new JLabel("Tipo: ");
		labelTipo.setBounds( 10, 10, 60, 30);
		
		JComboBox<Object> cbTipo = new JComboBox<Object>(stTipo);
		cbTipo.setBounds( 50, 10, 150, 30);
		cbTipo.setSelectedIndex(3);
		
		JLabel labelAnos = new JLabel("Ano: ");
		labelAnos.setBounds( 655, 10, 60, 30);
		
		listarAnos();
		
		JComboBox<Object> cbAnos = new JComboBox<Object>(stAnos.toArray());
		cbAnos.setBounds( 695, 10, 150, 30);
		
		tResultadoPrevMeses  = new JTable(new DefaultTableModel(null, colRespPrevMeses));
		
		scrollResPrevMeses = new JScrollPane(tResultadoPrevMeses);
		
		tResultadoPrevMeses.setBounds( 10 , 50 , width - 640, height - 140 );
		
		scrollResPrevMeses.setBounds( 10 , 50 , width - 640, height - 140 );
		
		scrollResPrevMeses.setVisible(true);
		
		tResultadoPrevCli  = new JTable(new DefaultTableModel(null, colRespPrevCli));
		
		scrollResPrevCli = new JScrollPane(tResultadoPrevCli);
		
		tResultadoPrevCli.setBounds( width - 620 , 50 , width - 610, height - 375 );
		
		scrollResPrevCli.setBounds( width - 620 , 50 , width - 610, height - 375 );
		
		scrollResPrevCli.setVisible(true);
		
		tResultadoPrevProd  = new JTable(new DefaultTableModel(null, colRespPrevProd));
		
		scrollResPrevProd = new JScrollPane(tResultadoPrevProd);
		
		tResultadoPrevProd.setBounds( width - 620 , height - 315 , width - 610, height - 375 );
		
		scrollResPrevProd.setBounds( width - 620 , height - 315 , width - 610, height - 375 );
		
		scrollResPrevProd.setVisible(true);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds( 10 , height - 80 , 120, 30 );
		btnPesquisar.setEnabled(false);
		
		JButton btnPrevisao = new JButton("Prever");
		btnPrevisao.setBounds( 230 , height - 80 , 120, 30 );
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds( 450 , height - 80 , 120, 30 );
		btnLimpar.setEnabled(false);
		
		JButton btnGerarPdf = new JButton("Gerar PDF");
		btnGerarPdf.setBounds( width - 280 , height - 80 , 120, 30 );
		btnGerarPdf.setEnabled(false);
		
		JButton btnGrafico = new JButton("Gráfico");
		btnGrafico.setBounds( width - 150 , height - 80 , 120, 30 );
		btnGrafico.setEnabled(false);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds( width - 150 , 10 , 120, 30 );
		
		add(labelTipo);
		add(cbTipo);
		add(labelAnos);
		add(cbAnos);
		add(scrollResPrevMeses);
		add(scrollResPrevCli);
		add(scrollResPrevProd);
		add(btnPesquisar);
		add(btnPrevisao);
		add(btnLimpar);
		add(btnGerarPdf);
		add(btnGrafico);
		add(btnVoltar);
		
		frame.repaint();
		
		btnVoltar.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				frame.remove(JPPrevisao.this);
				frame.setTela(new JPMenuPrincipal(frame, admin), false);		
			}
		} );
		
		cbTipo.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				if(cbTipo.getSelectedIndex() != 3)
				{
					frame.remove(JPPrevisao.this);
					frame.setTela(new JPHistorico(frame, admin, cbTipo.getSelectedIndex()), false);
				}
			}
		});
		
		btnLimpar.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				cbTipo.setSelectedIndex(3); 
				dadosMes.setNumRows(0);
				dadosCli.setNumRows(0);
				
				matrixPrevisao.clear();
				somaMeses.clear();
				somaClientes.clear();
				clientes.clear();
				
				btnGerarPdf.setEnabled(false);
				btnGrafico.setEnabled(false);
				btnLimpar.setEnabled(false);;
			}
		} );
		
		btnGerarPdf.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{	
				PdfFactory pdfFactory = new PdfFactory();
				
				if(tResultadoPrevMeses.getSelectedRowCount() > 0)
				{
					List<String> mesesNome = new ArrayList<String>();
					List<Integer> mesesValor = new ArrayList<Integer>();
					
					getMesesSelecionados(mesesNome, mesesValor);
					
					pdfFactory.gerarPdfPrevisaoFull( mesesNome, mesesValor, somaClientes, clientes, now.getYear());
				}
				else
				{
					HistoricoDAO dao = new HistoricoDAO();
					List<String> meses = dao.getMeses();
					
					pdfFactory.gerarPdfPrevisaoFull( meses, somaMeses, somaClientes, clientes, now.getYear());
				}
			}
		} );
		
		btnGrafico.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				
				List<String> barras = new ArrayList<String>();
				String titulo = "";
				String colunaX;
				String colunaY;

				titulo = "Previsão de " + now.getYear();
				colunaX = "Meses";
				colunaY = "Numero de Vendas";
				
				GraficoFactory gf = new GraficoFactory();
				
				frame.remove(JPPrevisao.this);
				
				if(tResultadoPrevMeses.getSelectedRowCount() > 0)
				{
					List<String> barraNome = new ArrayList<String>();
					List<Integer> barraValor = new ArrayList<Integer>();
					
					getMesesSelecionados(barraNome, barraValor);
					
					frame.setTela(new JPGrafico(frame, admin, gf.GeraGraficoBarra(barraNome, barraValor, titulo,  colunaX, colunaY), 3), false );
				}
				else
				{
					HistoricoDAO dao = new HistoricoDAO();
					barras = dao.getMeses();
					
					frame.setTela(new JPGrafico(frame, admin, gf.GeraGraficoBarra(barras, somaMeses, titulo,  colunaX, colunaY), 3), false );
				}
			}
		} );
			
		btnPrevisao.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				PrevisaoFactory pf = new PrevisaoFactory();
				
				matrixPrevisao = pf.populaMatrixVendaMes( now.getYear(), now.getMonthValue());
				popularClientes();
				previsaoInicial();
				popularTabelaMes();
				popularTabelaCli();
				btnLimpar.setEnabled(true);
				btnGerarPdf.setEnabled(true);
				btnGrafico.setEnabled(true);
			}
		} );
		
		tResultadoPrevMeses.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
				
			}

			public void mouseReleased(MouseEvent e) {
				
				if(tResultadoPrevMeses.getSelectedRows().length == 1)
				{
					previsaoMes(tResultadoPrevMeses.getSelectedRow());
					popularTabelaCli();
				}
				else
				{
					previsaoMeses(tResultadoPrevMeses.getSelectedRows());
					popularTabelaCli();
				}
				
			}

			public void mouseEntered(MouseEvent e) {
				
			}

			public void mouseExited(MouseEvent e) {
				
			}
			
		});

	}
	
	public void popularClientes()
	{
		clientes.clear();
		ClientesDAO cliDao = new ClientesDAO();
		clientes = cliDao.listarClientes();
	}
	
	public void getMesesSelecionados(List<String> meses, List<Integer> valores)
	{ 
		HistoricoDAO dao = new HistoricoDAO();
		List<String> mesestotal = dao.getMeses();
		
		for(int i = 0; i < tResultadoPrevMeses.getSelectedRowCount(); i++)
		{
			meses.add(mesestotal.get(tResultadoPrevMeses.getSelectedRows()[i]));
			valores.add(somaMeses.get(tResultadoPrevMeses.getSelectedRows()[i]));
		}
	}
	
	public void previsaoInicial()
	{
		somaMeses.clear();
		somaClientes.clear();
		
		int somaMes;
		int somaCliente;
		
		for(int i = 0; i < clientes.size(); i++)
		{
			somaCliente = 0;
			for(int j = 12 * i;  j < 12 * (i + 1); j++)
			{
				somaCliente += matrixPrevisao.get(j);
			}
			somaClientes.add(somaCliente);
		}
		
		for(int i = 0; i < 12; i ++)
		{
			somaMes = 0;
			for(int j = i; j < (12 * clientes.size()); j += 12)
			{
				somaMes += matrixPrevisao.get(j);
			}
			somaMeses.add(somaMes);
		}
		
	}
	
	public void previsaoMes(int mes)
	{
		somaClientes.clear();
		
		for(int i = mes; i < matrixPrevisao.size(); i += 12)
		{
			somaClientes.add(matrixPrevisao.get(i));
		}
	}
	
	public void previsaoMeses(int[] meses)
	{
		somaClientes.clear();
		
		for(int i = 0; i < clientes.size(); i++ )
		{
			somaClientes.add(0);
		}
		
		for(int i = 0; i < meses.length; i++)
		{
			for(int j = 0; j < clientes.size(); j++)
			{
				somaClientes.set( j, somaClientes.get(j) + matrixPrevisao.get(meses[i] + (12 * j)));
			}
		}
	}
}
