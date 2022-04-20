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
import java.util.ArrayList;
import java.util.Calendar;
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
import br.com.shm.dao.ProdutoVendaDAO;
import br.com.shm.dao.ProdutosDAO;
import br.com.shm.dao.VendasDAO;
import br.com.shm.model.Cliente;
import br.com.shm.model.Produto;
import br.com.shm.model.ProdutoVenda;
import br.com.shm.model.Venda;

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
	private DecimalFormat dfpreco = new DecimalFormat(".##");
	private LocalDateTime now = LocalDateTime.now();
	private Cliente cliente  = null;
	private List<Produto> produtos  = new ArrayList<>();
	int[] linhas;
	
	public JPNovaVenda( JFPadrao frame, boolean admin )
	{
		montaTelaNovaVenda( frame, admin );
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
				"1"
			});
		}
	}
	
	public void montaTelaNovaVenda( JFPadrao frame, boolean admin )
	{
		limpaTela( );
		width = frame.getBounds( ).width;
		height = frame.getBounds( ).height;
		this.setBounds( 0, 0, width, height );
		
		JLabel labelDescricaoVenda = new JLabel("Descrição: ");
		labelDescricaoVenda.setBounds( 10, 10, 70, 30);
		
		JTextArea taDescricaoVenda = new JTextArea(5, 10);
		taDescricaoVenda.setBounds(10, 50, width - 40 , 60);
		taDescricaoVenda.setWrapStyleWord(true);
		
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
		
		JLabel labelResultado = new JLabel("");
		labelResultado.setBounds( 600, height - 80, 300, 30);
		
		JLabel labelValorVenda = new JLabel("Valor: ");
		labelValorVenda.setBounds( width - 190, height - 80, 40, 30);
		
		JTextField tfValorVenda = new JTextField();
		tfValorVenda.setBounds(width - 150, height - 80, 120 , 30);
		
		tfValorVenda.setEditable(false);
		tfValorVenda.setHorizontalAlignment(JTextField.RIGHT);
		tfValorVenda.setText("0");
		
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
		add(labelResultado);
		add(tfValorVenda);
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
				frame.setTela(new JPMenuPrincipal(frame, admin), false);		
			}
		} );
		
		btnCadastrarVenda.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				if(cliente != null && !produtos.isEmpty())
				{
					boolean erro = false;
					boolean dateErro = false;
					Integer qtd = 0;
					Double preco = 0.0;
					for(int i = 0; i < linhas.length; i++)
					{
						qtd = Integer.parseInt(tProdutos.getModel().getValueAt(linhas[i], 2).toString());
						preco = Double.parseDouble(tProdutos.getModel().getValueAt(linhas[i], 1).toString());
						if(qtd <= 0 || preco <= 0.0 )
						{
							erro = true;
							break;
						}
					}

					Integer dia = 0, mes = 0, ano = 0;
					String substr, strdt = tfDataVenda.getText();
					
					strdt = strdt.replace("/", "");
					
					substr = strdt.substring(0, 2);
					dia = Integer.parseInt(substr);
					
					substr = strdt.substring(2, 4);
					mes = Integer.parseInt(substr);
					
					substr = strdt.substring(4, 8);
					ano = Integer.parseInt(substr);
					
					Calendar calVenda = Calendar.getInstance();
					
					calVenda.set(ano, mes - 1, 1, 0, 0, 0);
		
					if(dia > calVenda.getActualMaximum(Calendar.DAY_OF_MONTH) || mes > 12)
					{
						dateErro = true;
					}
					
					if(!erro && !dateErro)
					{
						Venda venda = new Venda(tfDataVenda.getText(), taDescricaoVenda.getText(),
								cliente, chkbxPago.isSelected());
						VendasDAO vendaDao = new VendasDAO();
						vendaDao.cadastrarVenda(venda);
						venda.setId(vendaDao.getMaiorId());
						qtd = 0;
						preco = 0.0;
						for(int i = 0; i < produtos.size(); i++)
						{
							qtd = Integer.parseInt(tProdutos.getModel().getValueAt(linhas[i], 2).toString());
							preco = Double.parseDouble(tProdutos.getModel().getValueAt(linhas[i], 1).toString());
							ProdutoVenda prodVenda = new ProdutoVenda(venda, produtos.get(i), qtd, preco);
							ProdutoVendaDAO prodVendaDao = new ProdutoVendaDAO();
							prodVendaDao.cadastrarProdutoVenda(prodVenda);
						}
						listar();
						cliente = null;
						produtos.clear();
						taDescricaoVenda.setText("");
						tfDataVenda.setText(dtf.format(now));
						chkbxPago.setSelected(false);
						tfValorVenda.setText("0");

					}
					else if(dateErro)
					{
						labelResultado.setText("Data não existe");
					}
					else if(erro)
					{
						labelResultado.setText("Produto com quantidade ou Preço errado");
					}
				}
				else
				{
					labelResultado.setText("Sem cliente ou produto");
				}
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
				tfValorVenda.setText("0");
				cliente = null;
				produtos.clear();
			}
		} );
		
		tClientes.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				ClientesDAO dao = new ClientesDAO();
				List<Cliente> listaCli = dao.listarClientes();
				cliente = listaCli.get(tClientes.getSelectedRow());
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
				int i = tProdutos.getSelectedRow();
				Integer qtd = Integer.parseInt(tProdutos.getModel().getValueAt(i, 2).toString());
				Double preco = Double.parseDouble(tProdutos.getModel().getValueAt(i, 1).toString());
				if( qtd <= 0 || preco <= 0.0)
				{
					tProdutos.getModel().setValueAt("1", i, 2);
					tProdutos.getModel().setValueAt("1.0", i, 1);
				}		
				
			}

			public void mousePressed(MouseEvent e) {
				
			}

			public void mouseReleased(MouseEvent e) {
				linhas = null;
				produtos.clear();
				linhas = tProdutos.getSelectedRows();
				Integer qtd;
				Double valor = 0.0;
				Double preco = 0.0;
				ProdutosDAO dao = new ProdutosDAO();
				List<Produto> listaProd = dao.listarProdutos();
				tfValorVenda.setText("");
				for(int i = 0; i < linhas.length; i++)
				{
					produtos.add(listaProd.get(linhas[i]));
					qtd = Integer.parseInt(tProdutos.getModel().getValueAt(linhas[i], 2).toString());
					preco = Double.parseDouble(tProdutos.getModel().getValueAt(linhas[i], 1).toString());
					if(qtd >= 0 || preco >= 0.0)
					{
						valor += (qtd * preco);
					}
					else
					{
						tProdutos.getModel().setValueAt("1", i, 2);
						tProdutos.getModel().setValueAt("1.0", i, 1);
					}
				}
				tfValorVenda.setText(dfpreco.format(valor));
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
