package br.com.shm.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.shm.dao.UsuarioDAO;
import br.com.shm.model.Usuario;

public class JPLogin extends JPPadrao {

	private Font titulo = new Font("Serif", Font.BOLD, 50);
	private final String ICONE = "img\\shm.png";
	
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
		
		try
		{
			BufferedImage imgLogo = ImageIO.read(new File(ICONE));
			JLabel imgLabel = new JLabel(new ImageIcon(imgLogo));
			imgLabel.setBounds(525, 10, 150, 150);
			
			add(imgLabel);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Falha em carregar a Imagem de Logo, erro: " + e);
		}
		
		JLabel labelLogo = new JLabel("Sales History Manager " + frame.getVersao());
		labelLogo.setBounds(300, 170, 600, 90);
		labelLogo.setFont(titulo);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(545 , 470 , 120, 30);
		
		JLabel labelUser = new JLabel("Usuário: ");
		labelUser.setBounds(400, 300, 100, 30);
		
		JTextField tfUsuario = new JTextField();
		tfUsuario.setBounds(450, 300, 300, 30);
		
		JLabel labelSenha = new JLabel("Senha: ");
		labelSenha.setBounds(400, 370, 100, 30);
		
		JPasswordField tfSenha = new JPasswordField();
		tfSenha.setBounds(450, 370, 300, 30);
		
		JLabel labelResposta = new JLabel();
		labelResposta.setBounds(510, 430, 200, 30);
		labelResposta.setHorizontalAlignment(JTextField.CENTER);
		
		JCheckBox chkbxSenha = new JCheckBox("Monstrar Senha");
		chkbxSenha.setBounds(770, 370, 120, 30);
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
