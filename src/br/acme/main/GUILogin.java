package br.acme.main;

import br.acme.GUI.*;
import br.acme.exception.CPFInvalidoException;
import br.acme.exception.EmailInvalidoException;
import br.acme.exception.NomeNullException;
import br.acme.exception.NomeVazioException;
import br.acme.exception.RepositorioException;
import br.acme.exception.SenhaInvalidaException;
import br.acme.exception.SexoInvalidoException;
import br.acme.location.Viagem;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import br.acme.storage.DataBase;
import br.acme.storage.IRepositorio;
import br.acme.storage.RepositorioMotorista;
import br.acme.storage.RepositorioSolicitante;
import br.acme.storage.RepositorioViagem;
import br.acme.users.Gerente;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;

abstract public class GUILogin {
	
	private static JFrame janela;
	private static JTextField caixaLogin;
	private static JPasswordField senha;
	private static IRepositorio<Motorista> repMot;
	private static IRepositorio<Solicitante> repSol;
	private static IRepositorio<Viagem> repViagem;
	private static Gerente gerente;
	
	public static void login()
	{	
		
	
	//	ActionListener
		ActionListener sairListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
			  		{System.exit(0);}
			};
		ActionListener loginListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
			  		{	gerente=DataBase.lerBaseGerente();
			  			repMot= DataBase.lerBaseMotoristas();
			  			repSol=DataBase.lerBaseSolicitantes();
			  			repViagem=DataBase.lerBaseViagens();
				  		String senhaUsuario= new String(senha.getPassword());
			  			Solicitante[] solicitantes = null;
			  			Motorista[] motoristas = null;
			  			boolean encontrado=false;
			  			
			  			if(gerente!=null)
				  		{	if(caixaLogin.getText().equals(gerente.getEmail())==true && gerente.confirmarSenha(senhaUsuario)==true)
				  					{	janela.dispose();
				  						GUIGerente.telaGerente(gerente);	
				  						JOptionPane.showMessageDialog(null,gerente.getNome() + ",\nBEM - VINDO A CARONA !" , "MENSAGEM",JOptionPane.INFORMATION_MESSAGE);
				  						encontrado=true;
				  					}
			  			}
			  			else		// caso nao tenha o arquivo do gerente irá resetar o gerente
			  			{
			  				Gerente resetGerente=null;
							try {
								resetGerente = new Gerente("Gerente","111.111.111-11","Masculino","gerente@carona.com","gerente123");
							} catch (NomeNullException e1) {
								System.out.println(e1.getMessage());
							} catch (NomeVazioException e1) {
								System.out.println(e1.getMessage());
							} catch (CPFInvalidoException e1) {
								System.out.println(e1.getMessage());
							} catch (EmailInvalidoException e1) {
								System.out.println(e1.getMessage());
							} catch (SexoInvalidoException e1) {
								System.out.println(e1.getMessage());
							} catch (SenhaInvalidaException e1) {
								System.out.println(e1.getMessage());
							}
			  				DataBase.salvarEstadoGerente(resetGerente);
			  			}
			  			
			  			if(repSol!=null)
				  		{	try {
				  				solicitantes=repSol.buscarTodos();
							} catch (RepositorioException e1) {
								System.out.println(e1.getMessage());
							}
				  			if(solicitantes!=null)
				  			{	for(int x=0;x<solicitantes.length;x++)
				  				{if(caixaLogin.getText().equals(solicitantes[x].getEmail())==true && solicitantes[x].confirmarSenha(senhaUsuario)==true)
				  					{	janela.dispose();
				  						GUISolicitante.telaSolicitante(solicitantes[x]);
				  						JOptionPane.showMessageDialog(null,solicitantes[x].getNome() + ",\nBEM - VINDO A CARONA !" , "MENSAGEM",JOptionPane.INFORMATION_MESSAGE);
				  						encontrado=true;
				  						break;
				  					}
				  				}
				  			}
			  			}
			  			else	// caso nao tenha o arquivo do repositorio irá resetar o repositorio
			  			{	IRepositorio<Solicitante> resetRepSol = new RepositorioSolicitante(500);
			  				DataBase.salvarEstadoSolicitante(resetRepSol);
			  			}
			  			
			  			if(repMot!=null)
			  			{	try {
			  					motoristas=repMot.buscarTodos();
							} catch (RepositorioException e1) {
								System.out.println(e1.getMessage());
							}
				  			
				  			if(motoristas!=null)	
					  			{
					  				for(int x=0;x<motoristas.length;x++)
					  				{if(caixaLogin.getText().equals(motoristas[x].getEmail())==true && motoristas[x].confirmarSenha(senhaUsuario)==true)
					  					{	janela.dispose();
					  						GUIMotorista.telaMotorista(motoristas[x], repMot);
					  						JOptionPane.showMessageDialog(null,motoristas[x].getNome() + ",\nBEM - VINDO A CARONA !" , "MENSAGEM",JOptionPane.INFORMATION_MESSAGE);
					  						encontrado=true;
					  						break;
					  					}
					  				}
					  			}
			  			}
			  			else		// caso nao tenha o arquivo do repositorio irá resetar o repositorio
			  			{
			  				IRepositorio<Motorista> resetRepMot = new RepositorioMotorista(500);
			  				DataBase.salvarEstadoMotorista(resetRepMot);
			  			}
			  			
			  			if(repViagem==null)		// caso nao tenha o arquivo do repositorio irá resetar o repositorio
			  			{
			  				IRepositorio<Viagem> resetRepViagem = new RepositorioViagem(500);
			  				DataBase.salvarEstadoViagem(resetRepViagem);
			  			}
			  			
			  			if(encontrado==false)	// se encontrar algum email , mostrara uma mensagem de erro
			  				JOptionPane.showMessageDialog(null,"Email e/ou Senha incorreta" , "ERRO",JOptionPane.ERROR_MESSAGE);
			  			
			  			if(gerente==null)
			  			{
			  				JOptionPane.showMessageDialog(null,"Gerente Resetado" , "Mensagem",JOptionPane.INFORMATION_MESSAGE);
			  			}
			  		}			  		
			};
		ActionListener ajudaListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
			  		{UIAjuda.telaAjuda();}
			};
		ActionListener sobreListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{UISobre.telaSobre();}
				};
		ActionListener cadastrarListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{UICadastro.telaCadastroSolicitante();}
				};
		
		//Mouse Listener		
		MouseListener loginMouseListener = new MouseListener()
				{	public void mouseClicked(MouseEvent e) {
						caixaLogin.setText("");
					}

					public void mouseEntered(MouseEvent e) {
						
					}

					public void mouseExited(MouseEvent e) {
						
					}

					public void mousePressed(MouseEvent e) {
						
					}

					public void mouseReleased(MouseEvent e) {
						
					}	
				};
		
		//FocusListener
		FocusListener senhaFocusListener = new FocusListener()
		{	public void focusGained(FocusEvent e) {
					senha.setText("");
			}

			public void focusLost(FocusEvent e) {
				
			}	
		};
		
		
			
		/*
		 * 
		 */
		
		//Formulario login
		JPanel painelLogin = new JPanel();
		JPanel painelSenha = new JPanel();
		JLabel tela = new JLabel("Carona");
		JLabel textoLogin = new JLabel("LOGIN: ");
		JLabel textoSenha = new JLabel("SENHA:");
		caixaLogin = new JTextField(20);
		senha = new JPasswordField(20);
		tela.setFont(new Font("Times New Roman", Font.BOLD, 100));
		tela.setForeground(Color.orange);
		caixaLogin.setText("usuario@dominio.com");
		caixaLogin.setEditable(true);
		senha.setText("user");
		senha.setEditable(true);
		painelLogin.add(textoLogin);
		painelLogin.add(caixaLogin);
		painelSenha.add(textoSenha);
		painelSenha.add(senha);
		caixaLogin.addMouseListener(loginMouseListener);
		senha.addFocusListener(senhaFocusListener);
		
		
		//Botao
		JPanel botoes = new JPanel();
		JButton botaoSair = new JButton("SAIR");
		JButton login = new JButton("ENTRAR");
		JButton cadastro = new JButton("Criar Nova Conta");
		botaoSair.addActionListener(sairListener);
		login.addActionListener(loginListener);
		cadastro.addActionListener(cadastrarListener);
		botoes.add(login);
		botoes.add(cadastro);
		botoes.add(botaoSair);
		
		//Menu
		JMenuBar menu = new JMenuBar();
		JMenu arquivo = new JMenu("Arquivo");
		JMenu sobre = new JMenu("Sobre");
		JMenuItem ajuda = new JMenuItem("Ajuda");
		JMenuItem info = new JMenuItem("Info");
		JMenuItem cadastrarUsuario = new JMenuItem("Cadastrar Novo Usuario");
		JMenuItem sair = new JMenuItem("Sair");
		arquivo.add(cadastrarUsuario);
		arquivo.add(sair);
		sobre.add(ajuda);
		sobre.add(info);
		menu.add(arquivo);
		menu.add(sobre);
		ajuda.addActionListener(ajudaListener);
		info.addActionListener(sobreListener);
		cadastrarUsuario.addActionListener(cadastrarListener);
		sair.addActionListener(sairListener);
		
		//Janela
		janela = new JFrame();
		janela.setLayout(new FlowLayout());
		janela.add(menu);
		janela.add(tela);
		janela.getContentPane().add(painelLogin);
		janela.getContentPane().add(painelSenha);
		janela.add(botoes);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.pack();
		janela.setSize(350, 305);
		janela.setLocationRelativeTo(null);
		janela.setResizable(false);
		janela.setVisible(true);
		
		
	}
}
