package br.com.shm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import br.com.shm.dao.HistoricoDAO;
import br.com.shm.dao.VendasDAO;
import br.com.shm.model.Cliente;
import br.com.shm.model.Produto;
import br.com.shm.model.ProdutoVenda;
import br.com.shm.model.Venda;

public class JPHistorico extends JPPadrao {
	
	private JTable tResultadoCli;
	private JTable tResultadoVen;
	private JTable tResultadoProd;
	private JScrollPane scrollResCli;
	private JScrollPane scrollResVen;
	private JScrollPane scrollResProd;
	private DefaultTableModel dados;
	private String[] colRespCli = {"Cliente", "Numero de Compras"};
	private String[] colRespVen = {"Mes/Ano", "Numero de Vendas"};
	private String[] colRespProd = {"Produto", "Quantidade"};
	private String[] stTipo = {"Cliente", "Venda", "Produto"};
	private String[] stClientePesquisa = {"Comprador", "Devedor"};
	private String[] stVendaPesquisa = {"Por Mês", "Por Ano"};
	private String[] stProdutoPesquisa = {"Quantidade vendida"};
	private String[] stOrdem = {"Crescente", "Decrescente"};
	
	
	public JPHistorico( JFPadrao frame )
	{
		montaTelaHistorico( frame );
	}
	
	public void listar(int tipo, int pesquisa, int ordem)
	{
		HistoricoDAO dao = new HistoricoDAO();
		List<ProdutoVenda> lista = dao.listar(tipo, pesquisa, ordem);
		
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
			scrollResCli.setVisible(false);
			scrollResVen.setVisible(false);
			scrollResProd.setVisible(true);
		}
	}
	
	public void montaTelaHistorico( JFPadrao frame )
	{
		limpaTela( );
		width = frame.getBounds( ).width;
		height = frame.getBounds( ).height;
		this.setBounds( 0, 0, width, height );
		
		JLabel labelTipo = new JLabel("Tipo: ");
		labelTipo.setBounds( 10, 10, 70, 30);
		
		JComboBox cbTipo = new JComboBox(stTipo);
		cbTipo.setBounds( 80, 10, 150, 30);
		
		JLabel labelPesquisa = new JLabel("Pesquisa: ");
		labelPesquisa.setBounds( 290, 10, 70, 30);
		
		JComboBox cbPesquisa = new JComboBox(stClientePesquisa);
		cbPesquisa.setBounds( 350, 10, 150, 30);
		
		JLabel labelOrden = new JLabel("Ordem: ");
		labelOrden.setBounds( 550, 10, 70, 30);
		
		JComboBox cbOrdem = new JComboBox(stOrdem);
		cbOrdem.setBounds( 620, 10, 150, 30);
		
		tResultadoCli = new JTable(new DefaultTableModel(null, colRespCli));
		tResultadoVen = new JTable(new DefaultTableModel(null, colRespVen));
		tResultadoProd = new JTable(new DefaultTableModel(null, colRespProd));
		
		scrollResCli = new JScrollPane(tResultadoCli);
		scrollResVen = new JScrollPane(tResultadoVen);
		scrollResProd = new JScrollPane(tResultadoProd);
		
		tResultadoCli.setBounds( 10 , 50 , width - 40, height - 140 );
		tResultadoVen.setBounds( 10 , 50 , width - 40, height - 140 );
		tResultadoProd.setBounds( 10 , 50 , width - 40, height - 140 );
		
		scrollResCli.setBounds( 10 , 50 , width - 40, height - 140 );
		scrollResVen.setBounds( 10 , 50 , width - 40, height - 140 );
		scrollResProd.setBounds( 10 , 50 , width - 40, height - 140 );
		
		scrollResCli.setVisible(true);
		scrollResVen.setVisible(false);
		scrollResProd.setVisible(false);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds( width - 150 , 10 , 120, 30 );
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds( 10 , height - 80 , 120, 30 );
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds( 450 , height - 80 , 120, 30 );
		
		add(labelTipo);
		add(cbTipo);
		add(labelPesquisa);
		add(cbPesquisa);
		add(labelOrden);
		add(cbOrdem);
		add(scrollResCli);
		add(scrollResVen);
		add(scrollResProd);
		add(btnVoltar);
		add(btnPesquisar);
		add(btnLimpar);
		
		frame.repaint();
		
		btnVoltar.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				frame.remove(JPHistorico.this);
				frame.setTela(new JPMenuPrincipal(frame), false);		
			}
		} );
		
		btnPesquisar.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				listar(cbTipo.getSelectedIndex(), cbPesquisa.getSelectedIndex(), cbOrdem.getSelectedIndex());	
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
			}
		} );
		
		cbTipo.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
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
				
			}
		} );
		
	}
}
