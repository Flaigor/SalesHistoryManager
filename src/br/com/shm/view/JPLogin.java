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
	private final String LOGO = "img\\Shm_150.png";
	
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
			JLabel imgLabel = new JLabel("");
			imgLabel.setBounds(500, 10, 200, 200);
			
			ImageIcon logoApp = new ImageIcon(LOGO);
			
	        logoApp = new ImageIcon( logoApp.getImage( ).getScaledInstance( imgLabel.getWidth( ), imgLabel.getHeight( ),
	        		logoApp.getImage( ).SCALE_DEFAULT ) );
	        
	        imgLabel.setIcon(logoApp);
	        
			add(imgLabel);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Falha em carregar a Imagem de Logo, erro: " + e);
		}
		
		JLabel labelLogo = new JLabel("Sales History Manager " + frame.getVersao());
		labelLogo.setBounds(300, 220, 600, 90);
		labelLogo.setFont(titulo);
		
		JLabel labelUser = new JLabel("Usuario: ");
		labelUser.setBounds(400, 330, 100, 30);
		
		JTextField tfUsuario = new JTextField();
		tfUsuario.setBounds(450, 330, 300, 30);
		
		JLabel labelSenha = new JLabel("Senha: ");
		labelSenha.setBounds(400, 400, 100, 30);
		
		JPasswordField tfSenha = new JPasswordField();
		tfSenha.setBounds(450, 400, 300, 30);
		
		JCheckBox chkbxSenha = new JCheckBox("Monstrar Senha");
		chkbxSenha.setBounds(770, 400, 120, 30);
		chkbxSenha.setBackground(color);
		
		JLabel labelResposta = new JLabel();
		labelResposta.setBounds(510, 440, 200, 30);
		labelResposta.setHorizontalAlignment(JTextField.CENTER);		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(545 , 500 , 120, 30);
		
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
					labelResposta.setText("Usuario ou senha invalidos");
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
