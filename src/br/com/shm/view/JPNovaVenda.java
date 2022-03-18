package br.com.shm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import br.com.shm.dao.ClientesDAO;
import br.com.shm.dao.ProdutosDAO;
import br.com.shm.model.Cliente;
import br.com.shm.model.Produto;

public class JPNovaVenda extends JPPadrao {
	
	private MaskFormatter ftmData;
	private JFormattedTextField tfDataVenda;
	private JTable tClientes;
	private JTable tProdutos;
	private DefaultTableModel dadosCli;
	private DefaultTableModel dadosProd;
	private String[] colunasCli = {"Nome Cliente"};
	private String[] colunasProd = {"Nome Produto", "Preço", "Quantidade"};
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private LocalDateTime now = LocalDateTime.now();
	private Cliente clientes  = new Cliente();
	private List<Produto> produtos  = new ArrayList<>();
	
	public JPNovaVenda( JFPadrao frame )
	{
		montaTelaCliente( frame );
	}
	
	public void listar()
	{
		ClientesDAO daoCli = new ClientesDAO();
		List<Cliente> listaCli = daoCli.listarClientes();
		dadosCli = (DefaultTableModel) tClientes.getModel();
		dadosCli.setNumRows(0);
		
		for(Cliente c: listaCli)
		{
			dadosCli.addRow(new Object[]{
				c.getNome()
			});
		}
		
		ProdutosDAO daoProd = new ProdutosDAO();
		List<Produto> listaProd = daoProd.listarProdutos();
		dadosProd = (DefaultTableModel) tProdutos.getModel();
		dadosProd.setNumRows(0);
		
		for(Produto p: listaProd)
		{
			dadosProd.addRow(new Object[]{
				p.getNome(),
				p.getPreco(),
				"0"
			});
		}
	}
	
	public void montaTelaCliente( JFPadrao frame )
	{
		limpaTela( );
		width = frame.getBounds( ).width;
		height = frame.getBounds( ).height;
		this.setBounds( 0, 0, width, height );
		
		JLabel labelDescricaoVenda = new JLabel("Descrição: ");
		labelDescricaoVenda.setBounds( 10, 10, 70, 30);
		
		JTextArea taDescricaoVenda = new JTextArea();
		taDescricaoVenda.setBounds(10, 50, width - 40 , 60);
		
		JLabel labelDataVenda = new JLabel("Data: ");
		labelDataVenda.setBounds( 440, 10, 40, 30);
		
		try {
			ftmData = new MaskFormatter("##/##/####");
			tfDataVenda = new JFormattedTextField(ftmData);

			ftmData.setValidCharacters("0123456789");
			
			tfDataVenda.setColumns(10);
			tfDataVenda.setBounds( 480, 10, 100, 30 );
			
			tfDataVenda.setText(dtf.format(now));
			
			add(tfDataVenda);
				
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		taDescricaoVenda.setBorder(tfDataVenda.getBorder());
		
		JCheckBox chkbxPago = new JCheckBox("Venda Paga?");
		chkbxPago.setBounds( 600, 10, 120, 30);
		chkbxPago.setBackground(color);
		
		JTextField tfNomeProd = new JTextField();
		tfNomeProd.setBounds( 90, 10 , 300, 30 );
		
		JLabel labelValorVenda = new JLabel("Valor: ");
		labelValorVenda.setBounds( width - 190, height - 80, 40, 30);
		
		JTextField tfDescricaoVenda = new JTextField();
		tfDescricaoVenda.setBounds(width - 150, height - 80, 120 , 30);
		
		tfDescricaoVenda.setEditable(false);
		tfDescricaoVenda.setHorizontalAlignment(JTextField.RIGHT);
		tfDescricaoVenda.setText("0");
		
		tClientes = new JTable(new DefaultTableModel(null, colunasCli));	
		JScrollPane scrollCliente = new JScrollPane(tClientes);
		
		tClientes.setBounds( 10 , 120 , width - 630, height - 210 );
		scrollCliente.setBounds( 10 , 120 , width - 630, height - 210 );
		
		tProdutos = new JTable(new DefaultTableModel(null, colunasProd));	
		JScrollPane scrollProdutos = new JScrollPane(tProdutos);
		
		tProdutos.setBounds( 600 , 120 , width - 630, height - 210 );
		scrollProdutos.setBounds( 600 ,120 , width - 630, height - 210 );

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds( width - 150 , 10 , 120, 30 );
		
		JButton btnCadastrarVenda = new JButton("Cadastrar");
		btnCadastrarVenda.setBounds( 10 , height - 80 , 120, 30 );
		
		JButton btnCancelarVenda = new JButton("Cancelar");
		btnCancelarVenda.setBounds( 460 , height - 80 , 120, 30 );
		
		add(labelDescricaoVenda);
		add(taDescricaoVenda);
		add(labelDataVenda);
		add(labelValorVenda);
		add(tfDescricaoVenda);
		add(scrollCliente);
		add(scrollProdutos);
		add(chkbxPago);
		add(btnVoltar);
		add(btnCadastrarVenda);
		add(btnCancelarVenda);
		
		listar();
		
		frame.repaint();
		
		btnVoltar.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				frame.remove(JPNovaVenda.this);
				frame.setTela(new JPMenuPrincipal(frame), false);		
			}
		} );
		
		btnCadastrarVenda.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				
			}
		} );
		
		btnCancelarVenda.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				listar();
				taDescricaoVenda.setText("");
				tfDataVenda.setText(dtf.format(now));
				chkbxPago.setSelected(false);
				tfDescricaoVenda.setText("0");
			}
		} );
		
		tClientes.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				
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
