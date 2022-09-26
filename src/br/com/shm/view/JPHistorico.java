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

public class JPHistorico extends JPPadrao {
	
	private JTable tResultadoPrev;
	private JTable tResultadoCli;
	private JTable tResultadoVen;
	private JTable tResultadoProd;
	private JScrollPane scrollResPrev;
	private JScrollPane scrollResCli;
	private JScrollPane scrollResVen;
	private JScrollPane scrollResProd;
	private DefaultTableModel dados;
	private String[] colRespPrev = {"Mês", "Numero de Vendas;"};
	private String[] colRespCli = {"Cliente", "Numero de Compras"};
	private String[] colRespVen = {"Mes/Ano", "Numero de Vendas"};
	private String[] colRespProd = {"Produto", "Quantidade"};
	private String[] stTipo = {"Cliente", "Venda", "Produto", "Previsão"};
	private String[] stClientePesquisa = {"Comprador", "Devedor"};
	private String[] stVendaPesquisa = {"Por Mês", "Por Ano"};
	private String[] stProdutoPesquisa = {"Quantidade vendida"};
	private String[] stOrdem = {"Crescente", "Decrescente"};
	private List<String> stAnos = new ArrayList<String>();
	private LocalDateTime now = LocalDateTime.now();
	
	public JPHistorico( JFPadrao frame, boolean admin )
	{
		montaTelaHistorico( frame, admin );
	}
	
	public void listarAnos()
	{
		HistoricoDAO dao = new HistoricoDAO();
		stAnos = dao.listarAnos();
	}
	
	public void listar(int tipo, int pesquisa, int ordem, String ano)
	{
		List<ProdutoVenda> lista = getLista(tipo, pesquisa, ordem, ano);
		
		if(tipo == 0)
		{
			dados = (DefaultTableModel) tResultadoCli.getModel();
			dados.setNumRows(0);
			for(ProdutoVenda pv: lista)
			{
				dados.addRow(new Object[]{
						pv.getVenda().getComprador().getNome(),
						pv.getQuantidade()
				});
			}
			scrollResPrev.setVisible(false);
			scrollResCli.setVisible(true);
			scrollResVen.setVisible(false);
			scrollResProd.setVisible(false);
		}
		else if(tipo == 1)
		{
			dados = (DefaultTableModel) tResultadoVen.getModel();
			dados.setNumRows(0);
			for(ProdutoVenda pv: lista)
			{
				dados.addRow(new Object[]{
						pv.getVenda().getDataVenda(),
						pv.getQuantidade()
				});
			}
			scrollResPrev.setVisible(false);
			scrollResCli.setVisible(false);
			scrollResVen.setVisible(true);
			scrollResProd.setVisible(false);
		}
		else if(tipo == 2)
		{
			dados = (DefaultTableModel) tResultadoProd.getModel();
			dados.setNumRows(0);
			for(ProdutoVenda pv: lista)
			{
				dados.addRow(new Object[]{
						pv.getProduto().getNome(),
						pv.getQuantidade()
				});
			}
			scrollResPrev.setVisible(false);
			scrollResCli.setVisible(false);
			scrollResVen.setVisible(false);
			scrollResProd.setVisible(true);
		}
	}
	
	public void previsao()
	{
		HistoricoDAO dao = new HistoricoDAO();
		
		List<Integer> laux = getValoresPrevisao();
		
		dados = (DefaultTableModel) tResultadoPrev.getModel();
		dados.setNumRows(0);
		for(int i = 0; i < laux.size(); i++)
		{
			dados.addRow(new Object[]{
					dao.getMeses().get(i),
					laux.get(i)
			});
		}
		scrollResPrev.setVisible(true);
		scrollResCli.setVisible(false);
		scrollResVen.setVisible(false);
		scrollResProd.setVisible(false);
		
		/*
		GraficoFactory gf = new GraficoFactory();
		
		frame.remove(JPGrafico.this);
		frame.setTela(new JPGrafico(frame, admin, gf.GeraGraficoBarra(dao.getMeses(), laux, "Previsão de " + now.getYear(), "Numero de Vendas", "Mês")), false );
		*/
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
		
		JLabel labelPesquisa = new JLabel("Pesquisa: ");
		labelPesquisa.setBounds( 210, 10, 60, 30);
		
		JComboBox<String> cbPesquisa = new JComboBox<String>(stClientePesquisa);
		cbPesquisa.setBounds( 280, 10, 150, 30);
		
		JLabel labelOrden = new JLabel("Ordem: ");
		labelOrden.setBounds( 440, 10, 60, 30);
		
		JComboBox<Object> cbOrdem = new JComboBox<Object>(stOrdem);
		cbOrdem.setBounds( 495, 10, 150, 30);
		
		JLabel labelAnos = new JLabel("Ano: ");
		labelAnos.setBounds( 655, 10, 60, 30);
		
		listarAnos();
		
		JComboBox<Object> cbAnos = new JComboBox<Object>(stAnos.toArray());
		cbAnos.setBounds( 695, 10, 150, 30);
		
		tResultadoPrev  = new JTable(new DefaultTableModel(null, colRespPrev));
		tResultadoCli = new JTable(new DefaultTableModel(null, colRespCli));
		tResultadoVen = new JTable(new DefaultTableModel(null, colRespVen));
		tResultadoProd = new JTable(new DefaultTableModel(null, colRespProd));
		
		scrollResPrev = new JScrollPane(tResultadoPrev); 
		scrollResCli = new JScrollPane(tResultadoCli);
		scrollResVen = new JScrollPane(tResultadoVen);
		scrollResProd = new JScrollPane(tResultadoProd);
		
		tResultadoPrev.setBounds( 10 , 50 , width - 40, height - 140 );
		tResultadoCli.setBounds( 10 , 50 , width - 40, height - 140 );
		tResultadoVen.setBounds( 10 , 50 , width - 40, height - 140 );
		tResultadoProd.setBounds( 10 , 50 , width - 40, height - 140 );
		
		scrollResPrev.setBounds( 10 , 50 , width - 40, height - 140 );
		scrollResCli.setBounds( 10 , 50 , width - 40, height - 140 );
		scrollResVen.setBounds( 10 , 50 , width - 40, height - 140 );
		scrollResProd.setBounds( 10 , 50 , width - 40, height - 140 );
		
		scrollResPrev.setVisible(false);
		scrollResCli.setVisible(true);
		scrollResVen.setVisible(false);
		scrollResProd.setVisible(false);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds( width - 150 , 10 , 120, 30 );
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds( 10 , height - 80 , 120, 30 );
		
		JButton btnPrevisao = new JButton("Prever");
		btnPrevisao.setBounds( 230 , height - 80 , 120, 30 );
		btnPrevisao.setEnabled(false);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds( 450 , height - 80 , 120, 30 );
		btnLimpar.setEnabled(false);
		
		JButton btnGerarPdf = new JButton("Gerar PDF");
		btnGerarPdf.setBounds( width - 280 , height - 80 , 120, 30 );
		btnGerarPdf.setEnabled(false);
		
		JButton btnGrafico = new JButton("Gráfico");
		btnGrafico.setBounds( width - 150 , height - 80 , 120, 30 );
		btnGrafico.setEnabled(false);
		
		add(labelTipo);
		add(cbTipo);
		add(labelPesquisa);
		add(cbPesquisa);
		add(labelOrden);
		add(cbOrdem);
		add(labelAnos);
		add(cbAnos);
		add(scrollResPrev);
		add(scrollResCli);
		add(scrollResVen);
		add(scrollResProd);
		add(btnVoltar);
		add(btnPesquisar);
		add(btnPrevisao);
		add(btnLimpar);
		add(btnGerarPdf);
		add(btnGrafico);
		
		frame.repaint();
		
		btnVoltar.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				frame.remove(JPHistorico.this);
				frame.setTela(new JPMenuPrincipal(frame, admin), false);		
			}
		} );
		
		btnPesquisar.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				listar(cbTipo.getSelectedIndex(), cbPesquisa.getSelectedIndex(), cbOrdem.getSelectedIndex(), cbAnos.getSelectedItem().toString());
				btnGerarPdf.setEnabled(true);
				btnGrafico.setEnabled(true);
				btnLimpar.setEnabled(true);
				btnPrevisao.setEnabled(false);
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
		
		btnLimpar.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				cbTipo.setSelectedIndex(0); 
				cbPesquisa.setSelectedIndex(0); 
				cbOrdem.setSelectedIndex(0);
				dados.setNumRows(0);
				btnGerarPdf.setEnabled(false);
				btnGrafico.setEnabled(false);
				btnLimpar.setEnabled(false);
				btnPrevisao.setEnabled(false);
			}
		} );
		
		btnGerarPdf.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{	
				PdfFactory pdfFactory = new PdfFactory();
				
				if(cbTipo.getSelectedIndex() != 3)
				{
					List<ProdutoVenda> lista = getLista(cbTipo.getSelectedIndex(), cbPesquisa.getSelectedIndex(),
							cbOrdem.getSelectedIndex(), cbAnos.getSelectedItem().toString());
					String[] colunas = getColunas(cbTipo.getSelectedIndex());
					String[] pesquisa = {(String) cbTipo.getSelectedItem(), (String) cbPesquisa.getSelectedItem(), (String) cbOrdem.getSelectedItem()};
					
					pdfFactory.gerarPdfHistorico(lista.toArray(new ProdutoVenda[lista.size()]), colunas, pesquisa);
				}
				else
				{
					HistoricoDAO dao = new HistoricoDAO();
					List<Integer> li = getValoresPrevisao();
					pdfFactory.gerarPdfPrevisao( dao.getMeses(), li, now.getYear());
				}
				
				
				
				
			}
		} );
		
		btnGrafico.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				
				List<String> barras = new ArrayList<String>();
				List<Integer> valores = new ArrayList<Integer>();
				String titulo = "";
				String[] colunas = getColunas(cbTipo.getSelectedIndex());
				String colunaX;
				String colunaY;
				
				if(cbTipo.getSelectedIndex() != 3)
				{
					List<ProdutoVenda> lista = getLista(cbTipo.getSelectedIndex(), cbPesquisa.getSelectedIndex(),
						cbOrdem.getSelectedIndex(), cbAnos.getSelectedItem().toString());
					
					colunaX = colunas[1];
					colunaY = colunas[0];
				
					for(int i = 0; i < lista.size(); i++)
					{
						switch(cbTipo.getSelectedIndex())
						{
							case 0:
								barras.add(lista.get(i).getVenda().getComprador().getNome());
								break;
							case 1:
								barras.add(lista.get(i).getVenda().getDataVenda().toString());
								break;
							case 2:
								barras.add(lista.get(i).getProduto().getNome());
								break;
						}
						valores.add(lista.get(i).getQuantidade());
					}
					titulo = cbTipo.getSelectedItem().toString() + " " + cbPesquisa.getSelectedItem().toString() + " "
						+ cbOrdem.getSelectedItem().toString() + " em " + cbAnos.getSelectedItem().toString();
				}
				else
				{
					HistoricoDAO dao = new HistoricoDAO();
					barras = dao.getMeses();
					valores = getValoresPrevisao();
					titulo = "Previsão de " + now.getYear();
					colunaX = "Meses";
					colunaY = "Numero de Vendas";
				}
				
				
				GraficoFactory gf = new GraficoFactory();
				
				frame.remove(JPHistorico.this);
				frame.setTela(new JPGrafico(frame, admin, gf.GeraGraficoBarra(barras, valores, titulo,  colunaX, colunaY)), false );
			}
		} );
		
		cbTipo.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				cbPesquisa.setEnabled(true);
				cbOrdem.setEnabled(true);
				cbAnos.setEnabled(true);
				btnPesquisar.setEnabled(true);
				btnPrevisao.setEnabled(false);
				if(cbTipo.getSelectedIndex() == 0)
				{
					cbPesquisa.removeAllItems();
					for(String str: stClientePesquisa)
					{
						cbPesquisa.addItem(str);
					}
				}
				else if(cbTipo.getSelectedIndex() == 1)
				{
					cbPesquisa.removeAllItems();
					for(String str: stVendaPesquisa)
					{
						cbPesquisa.addItem(str);
					}
				}
				else if(cbTipo.getSelectedIndex() == 2)
				{
					cbPesquisa.removeAllItems();
					for(String str: stProdutoPesquisa)
					{
						cbPesquisa.addItem(str);
					}
				}
				else if(cbTipo.getSelectedIndex() == 3)
				{
					cbPesquisa.setEnabled(false);
					cbOrdem.setEnabled(false);
					cbAnos.setSelectedIndex(cbAnos.getItemCount() - 1);
					cbAnos.setEnabled(false);
					btnPesquisar.setEnabled(false);
					btnLimpar.setEnabled(false);
					btnPrevisao.setEnabled(true);
				}
				
			}
		} );
		
	}
	
	public List<ProdutoVenda> getLista(int tipo, int pesquisa, int ordem, String ano)
	{
			HistoricoDAO dao = new HistoricoDAO();		
			return dao.listar(tipo, pesquisa, ordem, ano);
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
	
	public String[] getColunas(int tipo)
	{
		switch(tipo)
		{
			case 0:
				return colRespCli;
			case 1:
				return colRespVen;
			case 2:
				return colRespProd;
		}
		return null;
	}
}
