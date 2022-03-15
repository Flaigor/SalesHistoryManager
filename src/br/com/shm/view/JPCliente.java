package br.com.shm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.MaskFormatter;

import br.com.shm.dao.ClientesDAO;
import br.com.shm.model.Cliente;

public class JPCliente extends JPPadrao {
	
	private MaskFormatter ftmTelefone;
	private JFormattedTextField tfTelefoneCli;
	
	public JPCliente( JFPadrao frame )
	{
		montaTelaCliente( frame );
	}
	
	public void montaTelaCliente( JFPadrao frame )
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
		
		JTextPane tpClientes = new JTextPane();
		JScrollPane scrollClientes = new JScrollPane(tpClientes);
		
		tpClientes.setBounds( 10 , 90 , width - 40, height - 180 );
		scrollClientes.setBounds( 10 , 90 , width - 40, height - 180 );
		
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
		add(scrollClientes);
		
		frame.repaint();
		
		btnVoltar.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				frame.remove(JPCliente.this);
				frame.setTela(new JPMenuPrincipal(frame), false);		
			}
		} );
		
		btnCadastrarCli.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				Cliente cli = new Cliente(tfNomeCli.getText(), tfEderecoCli.getText(), tfTelefoneCli.getText());
				ClientesDAO dao = new ClientesDAO();
				dao.cadastrarCliente(cli);
			}
		} );
	}
		
}
