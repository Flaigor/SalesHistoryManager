package br.com.shm.view;

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
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds( width/2 - 55 , height - 200 , 120, 30 );
		
		JLabel labelUser = new JLabel("Usuário: ");
		labelUser.setBounds( width/2 - 200, height/3, 100, 30);
		
		JTextField tfUsuario = new JTextField();
		tfUsuario.setBounds( width/2 - 150, height/3 , 300, 30 );
		
		JLabel labelSenha = new JLabel("Senha: ");
		labelSenha.setBounds( width/2 - 200, height/2 - 30, 100, 30);
		
		JPasswordField tfSenha = new JPasswordField();
		tfSenha.setBounds( width/2 - 150, height/2 - 30 , 300, 30 );
		
		JLabel labelResposta = new JLabel();
		labelResposta.setBounds( width/2 - 90, height/2 + 30, 200, 30);
		labelResposta.setHorizontalAlignment(JTextField.CENTER);
		
		JCheckBox chkbxSenha = new JCheckBox("Monstrar Senha");
		chkbxSenha.setBounds( width/2 + 170, height/2 - 30, 120, 30);
		chkbxSenha.setBackground(color);
		
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
