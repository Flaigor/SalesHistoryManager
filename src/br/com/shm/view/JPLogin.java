package br.com.shm.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.shm.dao.UsuarioDAO;
import br.com.shm.model.Usuario;

public class JPLogin extends JPPadrao {

	private Font logo = new Font("Serif", Font.BOLD, 50);
	
	public JPLogin( JFPadrao frame )
	{
		montaTelaLogin( frame );
	}
	
	public void montaTelaLogin( JFPadrao frame )
	{
		limpaTela( );
		width = frame.getBounds( ).width;
		height = frame.getBounds( ).height;
		this.setBounds( 0, 0, width, height );
		
		JLabel labelLogo = new JLabel("Sales History Manager " + frame.getVersao());
		labelLogo.setBounds( 300, 50, 600, 90);
		labelLogo.setFont(logo);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds( 545 , 400 , 120, 30 );
		
		JLabel labelUser = new JLabel("Usuário: ");
		labelUser.setBounds( 400, 200, 100, 30);
		
		JTextField tfUsuario = new JTextField();
		tfUsuario.setBounds( 450, 200 , 300, 30 );
		
		JLabel labelSenha = new JLabel("Senha: ");
		labelSenha.setBounds( 400, 270, 100, 30);
		
		JPasswordField tfSenha = new JPasswordField();
		tfSenha.setBounds( 450, 270 , 300, 30 );
		
		JLabel labelResposta = new JLabel();
		labelResposta.setBounds( 510, 330, 200, 30);
		labelResposta.setHorizontalAlignment(JTextField.CENTER);
		
		JCheckBox chkbxSenha = new JCheckBox("Monstrar Senha");
		chkbxSenha.setBounds( 770, 270, 120, 30);
		chkbxSenha.setBackground(color);
		
		add(labelLogo);
		add(btnLogin);
		add(labelUser);
		add(tfUsuario);
		add(labelSenha);
		add(tfSenha);
		add(labelResposta);
		add(chkbxSenha);
		
		frame.repaint();
		
		btnLogin.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				UsuarioDAO dao = new UsuarioDAO();
				if(dao.validaUsuario(tfUsuario.getText(), tfSenha.getText()))
				{
					Usuario user = new Usuario();
					user = dao.getUsuario(tfUsuario.getText(), tfSenha.getText());
					tfUsuario.setText("");
					tfSenha.setText("");
					frame.remove(JPLogin.this);
					frame.setTela(new JPMenuPrincipal(frame, user.isAdmin()), false);		
				}
				else
				{
					labelResposta.setText("Usuário ou senha inválidos");
				}
			}
		} );
		
		chkbxSenha.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				if(chkbxSenha.isSelected())
				{
					tfSenha.setEchoChar('\u0000');
				}
				else
				{
					tfSenha.setEchoChar('*');
				}
			}
		} );	
	}
}
