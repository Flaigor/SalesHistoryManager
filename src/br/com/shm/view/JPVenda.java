package br.com.shm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.shm.dao.ProdutoVendaDAO;
import br.com.shm.dao.VendasDAO;
import br.com.shm.model.ProdutoVenda;
import br.com.shm.model.Venda;

public class JPVenda extends JPPadrao {
	
	private MaskFormatter ftmData;
	private JFormattedTextField tfDataVenda;
	private JTable tVendas;
	private JTable tProdutos;
	private DefaultTableModel dadosVen;
	private DefaultTableModel dadosProdVen;
	private String[] colunasVendas = {"Cliente", "Data", "Desci��o", "Pago"};
	private String[] colunasProd = {"Produto", "Descri��o", "Pre�o", "Quantidade"};
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private DateTimeFormatter dtv = DateTimeFormatter.ofPattern("dd/MM/yyyy").withResolverStyle(ResolverStyle.STRICT);
	private DecimalFormat dfpreco = new DecimalFormat(".##");
	private LocalDateTime now = LocalDateTime.now();
	
	public JPVenda( JFPadrao frame )
	{
		montaTelaVenda( frame );
	}
	
	public void listarVendas()
	{
		VendasDAO daoVen = new VendasDAO();
		List<Venda> listaVenda = daoVen.listarVendaJoinCliente();
		dadosVen = (DefaultTableModel) tVendas.getModel();
		dadosVen.setNumRows(0);
		
		for(Venda v: listaVenda)
		{
			dadosVen.addRow(new Object[]{
				v.getComprador().getNome(),
				v.getDataVenda(),
				v.getDescricao(),
				v.getPago() ? "Sim" : "N�o"
			});
		}
		
	}
	
	public Double listarProdVenda(String id)
	{
		Double valor = 0.0;
		ProdutoVendaDAO daoProdVen = new ProdutoVendaDAO();
		List<ProdutoVenda> listaProdVen = daoProdVen.listarProdutoPorVenda(id);
		dadosProdVen = (DefaultTableModel) tProdutos.getModel();
		dadosProdVen.setNumRows(0);
		
		for(ProdutoVenda pv: listaProdVen)
		{
			dadosProdVen.addRow(new Object[]{
				pv.getProduto().getNome(),
				pv.getProduto().getDescricao(),
				pv.getValor(),
				pv.getQuantidade()
			});
			
			valor += (pv.getValor() * pv.getQuantidade());
		}
		
		return valor;
	}
	
	public Double attValor()
	{
		Double valor = 0.0;
		Double preco = 0.0;
		Integer qtd;
		VendasDAO daoVen = new VendasDAO();
		List<Venda> listaVen = daoVen.listarVendaJoinCliente();
		Venda ven = new Venda();
		ven = listaVen.get(tVendas.getSelectedRow());
		
		ProdutoVendaDAO daoProdVen = new ProdutoVendaDAO();
		List<ProdutoVenda> listaProdVen = daoProdVen.listarProdutoPorVenda(ven.getId().toString());
		
		for(int i = 0; i < listaProdVen.size(); i++)
		{
			preco = Double.parseDouble(tProdutos.getModel().getValueAt(i, 2).toString());
			qtd = Integer.parseInt(tProdutos.getModel().getValueAt(i, 3).toString());
			
			valor += (preco * qtd);
		}
	
		return valor;
	}
	
	public void attTabelaVenda()
	{
		listarVendas();
		tVendas.clearSelection();
		tProdutos.removeAll();
		tfDataVenda.setText(dtf.format(now));
	}
	
	public void montaTelaVenda( JFPadrao frame )
	{
		limpaTela( );
		width = frame.getBounds( ).width;
		height = frame.getBounds( ).height;
		this.setBounds( 0, 0, width, height );
		
		JLabel labelNomeCli = new JLabel("Nome: ");
		labelNomeCli.setBounds( 240, 10, 60, 30);
		
		JTextField tfNomeCli = new JTextField();
		tfNomeCli.setBounds( 280, 10 , 300, 30 );
		
		tfNomeCli.setEnabled(false);
		
		JLabel labelDescricaoVenda = new JLabel("Descri��o: ");
		labelDescricaoVenda.setBounds( 10, 10, 70, 30);
		
		JTextArea taDescricaoVenda = new JTextArea(5, 10);
		taDescricaoVenda.setBounds(10, 50, width - 40 , 60);
		taDescricaoVenda.setWrapStyleWord(true);
		
		JLabel labelDataVenda = new JLabel("Data: ");
		labelDataVenda.setBounds( 600, 10, 40, 30);
		
		try {
			ftmData = new MaskFormatter("##/##/####");
			tfDataVenda = new JFormattedTextField(ftmData);

			ftmData.setValidCharacters("0123456789");
			
			tfDataVenda.setColumns(10);
			tfDataVenda.setBounds( 640, 10, 100, 30 );
			
			tfDataVenda.setText(dtf.format(now));
			
			add(tfDataVenda);
				
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		taDescricaoVenda.setBorder(tfDataVenda.getBorder());
		
		JCheckBox chkbxPago = new JCheckBox("Venda Paga?");
		chkbxPago.setBounds( 760, 10, 120, 30);
		chkbxPago.setBackground(color);
		
		JLabel labelResultado = new JLabel("");
		labelResultado.setBounds( 600, height - 80, 300, 30);
		
		JLabel labelValorVenda = new JLabel("Valor: ");
		labelValorVenda.setBounds( width - 190, height - 80, 40, 30);
		
		JTextField tfValorVenda = new JTextField();
		tfValorVenda.setBounds(width - 150, height - 80, 120 , 30);
		
		tfValorVenda.setEditable(false);
		tfValorVenda.setHorizontalAlignment(JTextField.RIGHT);
		tfValorVenda.setText("0");
		
		tVendas = new JTable(new DefaultTableModel(null, colunasVendas));	
		JScrollPane scrollVendas = new JScrollPane(tVendas);
		
		tVendas.setBounds( 10 , 120 , width - 630, height - 210 );
		scrollVendas.setBounds( 10 , 120 , width - 630, height - 210 );
		
		tProdutos = new JTable(new DefaultTableModel(null, colunasProd));	
		JScrollPane scrollProdutos = new JScrollPane(tProdutos);
		
		tProdutos.setBounds( 600 , 120 , width - 630, height - 210 );
		scrollProdutos.setBounds( 600 ,120 , width - 630, height - 210 );
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds( width - 150 , 10 , 120, 30 );
		
		JButton btnAttVenda = new JButton("Atualizar");
		btnAttVenda.setBounds( 10 , height - 80 , 120, 30 );
		
		JButton btnDeletarVenda = new JButton("Deletar");
		btnDeletarVenda.setBounds( 460 , height - 80 , 120, 30 );
		
		add(labelNomeCli);
		add(tfNomeCli);
		add(labelDescricaoVenda);
		add(taDescricaoVenda);
		add(labelDataVenda);
		add(chkbxPago);
		add(labelResultado);
		add(labelValorVenda);
		add(tfValorVenda);
		add(scrollVendas);
		add(scrollProdutos);
		add(btnVoltar);
		add(btnAttVenda);
		add(btnDeletarVenda);
		
		listarVendas();
		
		frame.repaint();
		
		btnVoltar.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				frame.remove(JPVenda.this);
				frame.setTela(new JPMenuPrincipal(frame), false);		
			}
		} );
		
		btnAttVenda.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				VendasDAO daoVen = new VendasDAO();
				List<Venda> listaVen = daoVen.listarVendaJoinCliente();
				Venda ven = new Venda();
				ven = listaVen.get(tVendas.getSelectedRow());
				
				if(ven.getDataVenda().compareTo(tfDataVenda.getText()) != 0 || 
						taDescricaoVenda.getText().compareTo(ven.getDescricao()) != 0 ||
						(chkbxPago.isSelected() != ven.getPago()))
				{
					ven.setDataVenda(tfDataVenda.getText());
					ven.setDescricao(taDescricaoVenda.getText());
					ven.setPago(chkbxPago.isSelected());
					daoVen.alterarVenda(ven);
				}

				ProdutoVendaDAO daoProdVen = new ProdutoVendaDAO();
				List<ProdutoVenda> listaProdVen = daoProdVen.listarProdutoPorVenda(ven.getId().toString());
				ProdutoVenda prodVen = new ProdutoVenda();
				
				Integer qtd;
				Double preco = 0.0;
				for(int i = 0; i < listaProdVen.size(); i++)
				{
					preco = Double.parseDouble(tProdutos.getModel().getValueAt(i, 2).toString());
					qtd = Integer.parseInt(tProdutos.getModel().getValueAt(i, 3).toString());
					if(qtd == 0 && listaProdVen.get(i).getId() != 0)
					{
						prodVen = listaProdVen.get(i);
						daoProdVen.excluirProdutoVenda(prodVen.getId().toString());
					}
					else if ((qtd != listaProdVen.get(i).getQuantidade() || preco.compareTo(listaProdVen.get(i).getValor()) != 0) && 
							listaProdVen.get(i).getId() != 0)
					{
						prodVen = listaProdVen.get(i);
						prodVen.setQuantidade(qtd);
						prodVen.setValor(preco);
						daoProdVen.alterarProdutoVenda(prodVen);
					}
					else if(qtd != 0 && listaProdVen.get(i).getId() == 0)
					{
						prodVen = listaProdVen.get(i);
						prodVen.setQuantidade(qtd);
						prodVen.setValor(preco);
						prodVen.setIdVenda(ven.getId());
						daoProdVen.cadastrarProdutoVenda(prodVen);
					}
				}
				
				attTabelaVenda();
				
				tfNomeCli.setText("");
				taDescricaoVenda.setText("");
				chkbxPago.setSelected(false);
				tfValorVenda.setText(dfpreco.format(0.0));
				
				labelResultado.setText("Venda e seus Produtos foram Atualizados");
			}
		} );
		
		btnDeletarVenda.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				VendasDAO daoVen = new VendasDAO();
				List<Venda> listaVen = daoVen.listarVendaJoinCliente();
				Venda ven = new Venda();
				ven = listaVen.get(tVendas.getSelectedRow());
				
				ProdutoVendaDAO daoProdVen = new ProdutoVendaDAO();
				
				daoProdVen.excluirProdutoVendaPorVenda(ven.getId().toString());
				daoVen.excluirVenda(ven.getId().toString());
				
				attTabelaVenda();
				
				tfNomeCli.setText("");
				taDescricaoVenda.setText("");
				chkbxPago.setSelected(false);
				tfValorVenda.setText(dfpreco.format(0.0));
				
				labelResultado.setText("Venda e seus Produtos foram Deletados");
			}
		} );
		
		tVendas.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				Double valor = 0.0;
				VendasDAO daoVen = new VendasDAO();
				List<Venda> listaVen = daoVen.listarVendaJoinCliente();
				Venda ven = new Venda();
				ven = listaVen.get(tVendas.getSelectedRow());
				
				tfNomeCli.setText(ven.getComprador().getNome());
				taDescricaoVenda.setText(ven.getDescricao());
				tfDataVenda.setText(ven.getDataVenda());
				chkbxPago.setSelected(ven.getPago());
				
				valor += listarProdVenda(ven.getId().toString());
				tfValorVenda.setText(dfpreco.format(valor));
				
			}

			public void mousePressed(MouseEvent e) {
				
			}

			public void mouseReleased(MouseEvent e) {
				
			}

			public void mouseEntered(MouseEvent e) {
				
			}

			public void mouseExited(MouseEvent e) {
				
			}
			
		});
		
		tProdutos.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				tfValorVenda.setText(dfpreco.format(attValor()));
			}

			public void mousePressed(MouseEvent e) {
				tfValorVenda.setText(dfpreco.format(attValor()));
			}

			public void mouseReleased(MouseEvent e) {
				tfValorVenda.setText(dfpreco.format(attValor()));
			}

			public void mouseEntered(MouseEvent e) {
				
			}

			public void mouseExited(MouseEvent e) {
				
			}
			
		});
		
		taDescricaoVenda.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				
			}

			public void keyPressed(KeyEvent e) {
				int contador = taDescricaoVenda.getText().length();
				if(contador >= 512)
				{
					taDescricaoVenda.setText(taDescricaoVenda.getText().substring(0,
							taDescricaoVenda.getText().length() - 1));
				}
			}

			public void keyReleased(KeyEvent e) {
				int contador = taDescricaoVenda.getText().length();
				if(contador >= 512)
				{
					taDescricaoVenda.setText(taDescricaoVenda.getText().substring(0,
							taDescricaoVenda.getText().length() - 1));
				}

			}
			
		});
		
	}

}
