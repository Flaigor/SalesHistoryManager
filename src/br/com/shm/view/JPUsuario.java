package br.com.shm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.shm.dao.ClientesDAO;
import br.com.shm.dao.UsuarioDAO;
import br.com.shm.model.Cliente;
import br.com.shm.model.Usuario;

public class JPUsuario extends JPPadrao {
	
	private JTable tUsuarios;
	private DefaultTableModel dados;
	private String[] colunas = {"Id", "Login","Senha","Admin"};
	private List<Usuario> usuarios  = new ArrayList<>();
	
	public JPUsuario( JFPadrao frame, boolean admin )
	{
		montaTelaCliente( frame, admin );
	}
	
	public void listar()
	{
		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> lista = dao.listarUsuario();
		dados = (DefaultTableModel) tUsuarios.getModel();
		dados.setNumRows(0);
		
		dados.addRow(new Object[]{
				"Novo Usuário",
				"",
				"",
				""
			});
		
		for(Usuario u: lista)
		{
			dados.addRow(new Object[]{
				u.getId(),
				u.getLogin(),
				u.getSenha(),
				u.isAdmin() ? "Sim" : "Não"
			});
		}
	}
	
	public void montaTelaCliente( JFPadrao frame, boolean admin )
	{
		limpaTela( );
		width = frame.getBounds( ).width;
		height = frame.getBounds( ).height;
		this.setBounds( 0, 0, width, height );
		
		JLabel labelLogin = new JLabel("Login: ");
		labelLogin.setBounds( 10, 10, 50, 30);
		
		JTextField tfLogin = new JTextField();
		tfLogin.setBounds( 60, 10 , 500, 30 );
		
		JLabel labelIdUser = new JLabel("Id: ");
		labelIdUser.setBounds( 575, 10, 50, 30);
		
		JTextField tfIdUser = new JTextField();
		tfIdUser.setBounds( 600, 10 , 50, 30 );
		tfIdUser.setEnabled(false);
		
		JLabel labelSenha = new JLabel("Senha: ");
		labelSenha.setBounds( 10, 50, 50, 30);
		
		JTextField tfSenha = new JTextField();
		tfSenha.setBounds( 60, 50 , 500, 30 );
		
		JCheckBox chkbxAdm = new JCheckBox("Admin");
		chkbxAdm.setBounds( 570, 50, 120, 30);
		chkbxAdm.setBackground(color);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds( width - 150 , 10 , 120, 30 );
		
		JButton btnCadastrarUser = new JButton("Cadastrar");
		btnCadastrarUser.setBounds( 10 , height - 80 , 120, 30 );
		
		JButton btnAttUser = new JButton("Atualizar");
		btnAttUser.setBounds( 230 , height - 80 , 120, 30 );
		
		JButton btnDeletarUser = new JButton("Deletar");
		btnDeletarUser.setBounds( 450 , height - 80 , 120, 30 );
		
		tUsuarios = new JTable(new DefaultTableModel(null, colunas));	
		JScrollPane scrollUsuarios = new JScrollPane(tUsuarios);
		
		tUsuarios.setBounds( 10 , 90 , width - 40, height - 180 );
		scrollUsuarios.setBounds( 10 , 90 , width - 40, height - 180 );
		
		JLabel labelResultado = new JLabel("");
		labelResultado.setBounds( 670 , height - 80 , 120, 30 );
		
		add(labelLogin);
		add(tfLogin);
		add(labelIdUser);
		add(tfIdUser);
		add(labelSenha);
		add(tfSenha);
		add(chkbxAdm);
		add(btnVoltar);
		add(btnCadastrarUser);
		add(btnAttUser);
		add(btnDeletarUser);
		add(scrollUsuarios);
		add(labelResultado);
		
		listar();
		
		frame.repaint();
		
		btnVoltar.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				frame.remove(JPUsuario.this);
				frame.setTela(new JPMenuPrincipal(frame, admin), false);		
			}
		} );
		
		btnCadastrarUser.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				if(!tfLogin.getText().isEmpty() || !tfSenha.getText().isEmpty())
				{
					Usuario user = new Usuario(tfLogin.getText(), tfSenha.getText(), chkbxAdm.isSelected());
					UsuarioDAO dao = new UsuarioDAO();
					dao.cadastrarUsuario(user);
					labelResultado.setText("");
					tfLogin.setText("");
					tfSenha.setText("");
					chkbxAdm.setSelected(false);
					listar();
				}
				else
				{
					labelResultado.setText("Sem Login ou senha");
				}
				
			}
		} );
		
		btnAttUser.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				if(!tfLogin.getText().isEmpty() || !tfSenha.getText().isEmpty())
				{
					Usuario user = new Usuario(Integer.parseInt(tfIdUser.getText()), 
							tfLogin.getText(), tfSenha.getText(), chkbxAdm.isSelected());
					UsuarioDAO dao = new UsuarioDAO();
					dao.alterarUsuario(user);
					labelResultado.setText("");
					listar();
				}
				else
				{
					labelResultado.setText("Sem Login ou senha");
				}
				
			}
		} );
		
		btnDeletarUser.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				UsuarioDAO dao = new UsuarioDAO();
				if(usuarios.size() == 0)
				{
					dao.excluirUsuario(tfIdUser.getText());
				}
				else
				{
					for(int i = 0; i < usuarios.size(); i++)
					{
						dao.excluirUsuario(usuarios.get(i).getId().toString());
					}
				}
				listar();
				labelResultado.setText("");
				usuarios.clear();
			}
		} );
		
		tUsuarios.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				if(tUsuarios.getSelectedRow() > 0)
				{
					UsuarioDAO dao = new UsuarioDAO();
					List<Usuario> listaUser = dao.listarUsuario();
					Usuario user = new Usuario();
					user = listaUser.get(tUsuarios.getSelectedRow() - 1);
				
					tfIdUser.setText(user.getId().toString());
					tfLogin.setText(user.getLogin());
					tfSenha.setText(user.getSenha());
					chkbxAdm.setSelected(user.isAdmin());
					btnCadastrarUser.setEnabled(false);
					btnAttUser.setEnabled(true);
					btnDeletarUser.setEnabled(true);
				}
				else if(tUsuarios.getSelectedRow() == 0)
				{
					tfIdUser.setText("");
					tfLogin.setText("");
					tfSenha.setText("");
					chkbxAdm.setSelected(false);
					btnCadastrarUser.setEnabled(true);
					btnAttUser.setEnabled(false);
					btnDeletarUser.setEnabled(false);
				}
				usuarios.clear();
			}

			public void mousePressed(MouseEvent e) {
				
			}

			public void mouseReleased(MouseEvent e) {
				int[] linhas = tUsuarios.getSelectedRows();
				if(linhas[0] > 0)
				{
					UsuarioDAO dao = new UsuarioDAO();
					List<Usuario> listaUser = dao.listarUsuario();
					for(int i = 0; i < linhas.length; i++)
					{
						usuarios.add(listaUser.get(linhas[i] - 1));
					}
				}
				else if(linhas[0] == 0)
				{
					UsuarioDAO dao = new UsuarioDAO();
					List<Usuario> listaUser = dao.listarUsuario();
					for(int i = 1; i < linhas.length; i++)
					{
						usuarios.add(listaUser.get(linhas[i] - 1)); 
					}
				}
				btnCadastrarUser.setEnabled(false);
				btnAttUser.setEnabled(false);
				btnDeletarUser.setEnabled(true);
			}

			public void mouseEntered(MouseEvent e) {
				
			}

			public void mouseExited(MouseEvent e) {
				
			}
			
		});
		
		tfLogin.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				
			}

			public void keyPressed(KeyEvent e) {
				int contador = tfLogin.getText().length();
				if(contador >= 32)
				{
					tfLogin.setText(tfLogin.getText().substring(0,
							tfLogin.getText().length() - 1));
				}
			}

			public void keyReleased(KeyEvent e) {
				int contador = tfLogin.getText().length();
				if(contador >= 32)
				{
					tfLogin.setText(tfLogin.getText().substring(0,
							tfLogin.getText().length() - 1));
				}

			}
			
		});
		
		tfSenha.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				
			}

			public void keyPressed(KeyEvent e) {
				int contador = tfSenha.getText().length();
				if(contador >= 32)
				{
					tfSenha.setText(tfSenha.getText().substring(0,
							tfSenha.getText().length() - 1));
				}
			}

			public void keyReleased(KeyEvent e) {
				int contador = tfSenha.getText().length();
				if(contador >= 32)
				{
					tfSenha.setText(tfSenha.getText().substring(0,
							tfSenha.getText().length() - 1));
				}

			}
			
		});
	
	}

}
