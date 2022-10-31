package br.com.shm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.shm.dao.HistoricoDAO;
import br.com.shm.jdbc.GraficoFactory;
import br.com.shm.jdbc.PdfFactory;
import br.com.shm.jdbc.PrevisaoFactory;
import br.com.shm.model.ProdutoVenda;

public class JPPrevisao extends JPPadrao {
	
	private JTable tResultadoPrevMeses;
	private JScrollPane scrollResPrevMeses;
	private JTable tResultadoPrevCli;
	private JScrollPane scrollResPrevCli;
	private JTable tResultadoPrevProd;
	private JScrollPane scrollResPrevProd;
	private DefaultTableModel dados;
	private String[] stTipo = {"Cliente", "Venda", "Produto", "Previsão"};
	private String[] colRespPrevMeses = {"Mês", "Numero de Vendas"};
	private String[] colRespPrevCli = {"Cliente", "Numero de Compras"};
	private String[] colRespPrevProd = {"Produto", "Previsão de Venda"};
	private List<String> stAnos = new ArrayList<String>();
	private LocalDateTime now = LocalDateTime.now();
	
	public JPPrevisao( JFPadrao frame, boolean admin )
	{
		montaTelaHistorico(frame, admin);
	}
	
	public void listarAnos()
	{
		stAnos.add("" + now.getYear());
		stAnos.add("" + (now.getYear() + 1));
	}
	
	public void previsao()
	{
		HistoricoDAO dao = new HistoricoDAO();
		
		List<Integer> laux = getValoresPrevisao();
		
		dados = (DefaultTableModel) tResultadoPrevMeses.getModel();
		dados.setNumRows(0);
		for(int i = 0; i < laux.size(); i++)
		{
			dados.addRow(new Object[]{
					dao.getMeses().get(i),
					laux.get(i)
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
				dados.setNumRows(0);
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
					
				HistoricoDAO dao = new HistoricoDAO();
				List<Integer> li = getValoresPrevisao();
				pdfFactory.gerarPdfPrevisao( dao.getMeses(), li, now.getYear());
				
			}
		} );
		
		btnGrafico.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				
				List<String> barras = new ArrayList<String>();
				List<Integer> valores = new ArrayList<Integer>();
				String titulo = "";
				String colunaX;
				String colunaY;
				
				HistoricoDAO dao = new HistoricoDAO();
				barras = dao.getMeses();
				valores = getValoresPrevisao();
				titulo = "Previsão de " + now.getYear();
				colunaX = "Meses";
				colunaY = "Numero de Vendas";
				
				GraficoFactory gf = new GraficoFactory();
				
				frame.remove(JPPrevisao.this);
				frame.setTela(new JPGrafico(frame, admin, gf.GeraGraficoBarra(barras, valores, titulo,  colunaX, colunaY), 3), false );
			}
		} );
			
		btnPrevisao.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				previsao();
				btnLimpar.setEnabled(true);
				btnGerarPdf.setEnabled(true);
				btnGrafico.setEnabled(true);
			}
		} );

	}
	
	public List<Integer> getValoresPrevisao()
	{
		PrevisaoFactory pf = new PrevisaoFactory();
		HistoricoDAO dao = new HistoricoDAO();
		
		List<Integer> lp = new ArrayList<Integer>();
		
		List<Integer> laux = new ArrayList<Integer>();
		
		for(int i = 3; i >= 0; i--)
		{
			laux = dao.getListPrevisao(now.getYear() - i);
			for(Integer j : laux)
			{
				lp.add(j);
			}
			laux.clear();
		}
		
		for(int i = lp.size(); i <= 47; i++)
		{
			lp.add(0);
		}
		
		pf.previsaoAno(lp, now.getMonthValue() + 1);
					
		for(int i = lp.size() - 12; i < lp.size(); i++)
		{
			laux.add(lp.get(i));
		}
		
		return laux;
	}
}
