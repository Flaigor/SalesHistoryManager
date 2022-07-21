package br.com.shm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.shm.dao.ClientesDAO;
import br.com.shm.dao.ProdutosDAO;
import br.com.shm.jdbc.PdfFactory;
import br.com.shm.model.Cliente;
import br.com.shm.model.Produto;

public class JPCliente extends JPPadrao {
	
	private MaskFormatter ftmTelefone;
	private JFormattedTextField tfTelefoneCli;
	private JTable tClientes;
	private DefaultTableModel dados;
	private String[] colunas = {"ID","Nome","Endereço","Telefone"};
	private List<Cliente> clientes  = new ArrayList<>();
	
	public JPCliente( JFPadrao frame, boolean admin )
	{
		montaTelaCliente( frame, admin );
	}
	
	public void listar()
	{
		ClientesDAO dao = new ClientesDAO();
		List<Cliente> lista = dao.listarClientes();
		dados = (DefaultTableModel) tClientes.getModel();
		dados.setNumRows(0);
		
		dados.addRow(new Object[]{
				"Novo Cliente",
				"",
				"",
				""
			});
		
		for(Cliente c: lista)
		{
			dados.addRow(new Object[]{
				c.getId(),
				c.getNome(),
				c.getEndereco(),
				c.getTelefone()
			});
		}
		
	}
	
	public void montaTelaCliente( JFPadrao frame, boolean admin )
	{
		limpaTela( );
		width = frame.getBounds( ).width;
		height = frame.getBounds( ).height;
		this.setBounds( 0, 0, width, height );
		
		JLabel labelIdCli = new JLabel("ID: ");
		labelIdCli.setBounds( 390, 10, 20, 30);
		
		JTextField tfIdCli = new JTextField();
		tfIdCli.setBounds( 420, 10 , 300, 30 );
		
		tfIdCli.setEnabled(false);
		
		JLabel labelNomeCli = new JLabel("Nome: ");
		labelNomeCli.setBounds( 10, 10, 60, 30);
		
		JTextField tfNomeCli = new JTextField();
		tfNomeCli.setBounds( 80, 10 , 300, 30 );
		
		JLabel labelEderecoCli = new JLabel("Endereço: ");
		labelEderecoCli.setBounds( 200, 50, 60, 30);
		
		JTextField tfEderecoCli = new JTextField();
		tfEderecoCli.setBounds( 270, 50 , width - 300, 30 );
		
		JLabel labelTelefoneCli = new JLabel("Telefone: ");
		labelTelefoneCli.setBounds( 10, 50, 60, 30);
		
		try {
			ftmTelefone = new MaskFormatter("(##)#####-####");
			tfTelefoneCli = new JFormattedTextField(ftmTelefone);

			ftmTelefone.setValidCharacters("0123456789");
			
			tfTelefoneCli.setColumns(14);
			tfTelefoneCli.setBounds( 80, 50, 110, 30 );
			
			add(tfTelefoneCli);
				
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds( width - 150 , 10 , 120, 30 );
		
		JButton btnCadastrarCli = new JButton("Cadastrar");
		btnCadastrarCli.setBounds( 10 , height - 80 , 120, 30 );
		
		JButton btnAttCli = new JButton("Atualizar");
		btnAttCli.setBounds( 230 , height - 80 , 120, 30 );
		
		JButton btnDeletarCli = new JButton("Deletar");
		btnDeletarCli.setBounds( 450 , height - 80 , 120, 30 );
		
		JButton btnGerarPdf = new JButton("Gerar PDF");
		btnGerarPdf.setBounds( width - 280 , height - 80 , 120, 30 );
		
		tClientes = new JTable(new DefaultTableModel(null, colunas));	
		JScrollPane scrollClientes = new JScrollPane(tClientes);
		
		tClientes.setBounds( 10 , 90 , width - 40, height - 180 );
		scrollClientes.setBounds( 10 , 90 , width - 40, height - 180 );
		
		JLabel labelResultado = new JLabel("");
		labelResultado.setBounds( 670 , height - 80 , 220, 30 );
		
		add(labelIdCli);
		add(tfIdCli);
		add(labelNomeCli);
		add(tfNomeCli);
		add(labelEderecoCli);
		add(tfEderecoCli);
		add(labelTelefoneCli);
		add(btnVoltar);
		add(btnCadastrarCli);
		add(btnAttCli);
		add(btnDeletarCli);
		add(btnGerarPdf);
		add(scrollClientes);
		add(labelResultado);
		
		listar();
		
		frame.repaint();
		
		btnVoltar.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				frame.remove(JPCliente.this);
				frame.setTela(new JPMenuPrincipal(frame, admin), false);		
			}
		} );
		
		btnCadastrarCli.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				if(!tfNomeCli.getText().isEmpty())
				{
					Cliente cli = new Cliente(tfNomeCli.getText(), tfEderecoCli.getText(), tfTelefoneCli.getText());
					ClientesDAO dao = new ClientesDAO();
					dao.cadastrarCliente(cli);
					labelResultado.setText("");
					tfIdCli.setText("");
					tfNomeCli.setText("");
					tfEderecoCli.setText("");
					tfTelefoneCli.setText("");
					listar();
				}
				else
				{
					labelResultado.setText("Nome vazio");
				}
				
			}
		} );
		
		btnAttCli.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				if(!tfNomeCli.getText().isEmpty())
				{
					Cliente cli = new Cliente(Integer.parseInt(tfIdCli.getText()),tfNomeCli.getText(), 
							tfEderecoCli.getText(), tfTelefoneCli.getText());
					ClientesDAO dao = new ClientesDAO();
					dao.alterarCliente(cli);
					labelResultado.setText("");
					listar();
				}
				else
				{
					labelResultado.setText("Nome vazio");
				}
				
			}
		} );
		
		btnDeletarCli.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				ClientesDAO dao = new ClientesDAO();
				if(clientes.size() == 1)
				{
					dao.excluirCliente(clientes.get(0).getId());
				}
				else
				{
					for(int i = 0; i < clientes.size(); i++)
					{
						dao.excluirCliente(clientes.get(i).getId());
					}
				}
				listar();
				labelResultado.setText("");
				clientes.clear();
			}
		} );
		
		btnGerarPdf.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				if(clientes.isEmpty())
				{
					ClientesDAO dao = new ClientesDAO();
					List<Cliente> listaCli = dao.listarClientes();
					for(int i = 0; i < listaCli.size(); i++)
					{
						clientes.add(listaCli.get(i));
					}
				}
				
				PdfFactory pdfFactory = new PdfFactory();
				pdfFactory.gerarPdfCliente(clientes.toArray(new Cliente[clientes.size()]));
			}
		} );

		tClientes.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				
				
			}

			public void mousePressed(MouseEvent e) {
				
			}

			public void mouseReleased(MouseEvent e) {
				
				clientes.clear();
				
				if(tClientes.getSelectedRows().length == 1)
				{
					if(tClientes.getSelectedRow() > 0)
					{
						ClientesDAO dao = new ClientesDAO();
						List<Cliente> listaCli = dao.listarClientes();
						Cliente cli = new Cliente();
						cli = listaCli.get(tClientes.getSelectedRow() - 1);
					
						tfIdCli.setText(cli.getId().toString());
						tfNomeCli.setText(cli.getNome());
						tfEderecoCli.setText(cli.getEndereco());
						tfTelefoneCli.setText(cli.getTelefone());
						btnCadastrarCli.setEnabled(false);
						btnAttCli.setEnabled(true);
						btnDeletarCli.setEnabled(true);
						clientes.add(cli);
					}
					else if(tClientes.getSelectedRow() == 0)
					{
						tfIdCli.setText("");
						tfNomeCli.setText("");
						tfEderecoCli.setText("");
						tfTelefoneCli.setText("");
						btnCadastrarCli.setEnabled(true);
						btnAttCli.setEnabled(false);
						btnDeletarCli.setEnabled(false);
					}
				}
				else
				{
					List<Integer> linhas = new ArrayList<>();
					for(int i = 0; i < tClientes.getSelectedRows().length; i++ )
					{
						if(tClientes.getSelectedRows()[i] != 0)
						{
							linhas.add(tClientes.getSelectedRows()[i]);
						}
					}
					
					ClientesDAO dao = new ClientesDAO();
					List<Cliente> listaCli = dao.listarClientes();
					for(int i = 0; i < linhas.size(); i++)
					{
						clientes.add(listaCli.get(linhas.get(i) - 1));
					}
					
					tfIdCli.setText("");
					tfNomeCli.setText("");
					tfEderecoCli.setText("");
					tfTelefoneCli.setText("");
					btnCadastrarCli.setEnabled(false);
					btnAttCli.setEnabled(false);
					btnDeletarCli.setEnabled(true);
				}
				
				if(clientes.isEmpty())
				{
					labelResultado.setText("Cadastrar novo Cliente?");
				}
				else if(clientes.size() == 1)
				{
					labelResultado.setText( "1 Cliente selecionado");
				}
				else
				{
					labelResultado.setText( clientes.size() + " Clientes selecionados");
				}
				
			}

			public void mouseEntered(MouseEvent e) {
				
			}

			public void mouseExited(MouseEvent e) {
				
			}
			
		});
		
		tfNomeCli.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				
			}

			public void keyPressed(KeyEvent e) {
				int contador = tfNomeCli.getText().length();
				if(contador >= 128)
				{
					tfNomeCli.setText(tfNomeCli.getText().substring(0,
							tfNomeCli.getText().length() - 1));
				}
			}

			public void keyReleased(KeyEvent e) {
				int contador = tfNomeCli.getText().length();
				if(contador >= 128)
				{
					tfNomeCli.setText(tfNomeCli.getText().substring(0,
							tfNomeCli.getText().length() - 1));
				}

			}
			
		});
		
		tfEderecoCli.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				
			}

			public void keyPressed(KeyEvent e) {
				int contador = tfEderecoCli.getText().length();
				if(contador >= 256)
				{
					tfEderecoCli.setText(tfEderecoCli.getText().substring(0,
							tfEderecoCli.getText().length() - 1));
				}
			}

			public void keyReleased(KeyEvent e) {
				int contador = tfEderecoCli.getText().length();
				if(contador >= 256)
				{
					tfEderecoCli.setText(tfEderecoCli.getText().substring(0,
							tfEderecoCli.getText().length() - 1));
				}

			}
			
		});
		
	}
		
}
