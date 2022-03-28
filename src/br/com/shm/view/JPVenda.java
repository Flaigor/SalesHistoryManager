package br.com.shm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import br.com.shm.model.Cliente;
import br.com.shm.model.ProdutoVenda;
import br.com.shm.model.Venda;

public class JPVenda extends JPPadrao {
	
	private MaskFormatter ftmData;
	private JFormattedTextField tfDataVenda;
	private JTable tVendas;
	private JTable tProdutos;
	private DefaultTableModel dadosVen;
	private DefaultTableModel dadosProdVen;
	private String[] colunasVendas = {"Cliente", "Data", "Descição", "Pago"};
	private String[] colunasProd = {"Nome do Produto", "Descrição", "Preço", "Quantidade"};
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private DecimalFormat dfpreco = new DecimalFormat(".##");
	private LocalDateTime now = LocalDateTime.now();
	
	public JPVenda( JFPadrao frame )
	{
		montaTelaMenuPrincipal( frame );
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
				v.getPago() ? "Sim" : "Não"
			});
		}
		
	}
	
	public Double listarProdVenda(int idVenda)
	{
		Double valor = 0.0;
		ProdutoVendaDAO daoProdVen = new ProdutoVendaDAO();
		List<ProdutoVenda> listaProdVen = daoProdVen.listarProdutoPorVenda(idVenda);
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
	
	public void montaTelaMenuPrincipal( JFPadrao frame )
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
		
		JLabel labelDescricaoVenda = new JLabel("Descrição: ");
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
			tfDataVenda.setEnabled(false);
			
			add(tfDataVenda);
				
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		taDescricaoVenda.setBorder(tfDataVenda.getBorder());
		
		JCheckBox chkbxPago = new JCheckBox("Venda Paga?");
		chkbxPago.setBounds( 760, 10, 120, 30);
		chkbxPago.setBackground(color);
		
		JLabel labelResultado = new JLabel("Aqui");
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
				
				valor += listarProdVenda(ven.getId());
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
		
	}

}
