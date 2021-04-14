package br.acme.GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.acme.exception.RepositorioException;
import br.acme.main.GUILogin;
import br.acme.storage.DataBase;
import br.acme.storage.IRepositorio;
import br.acme.users.Gerente;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;

abstract public class UIExcluirConta {

	private static JFrame janela;
	private static JComboBox<String> selecao;
	private static JTextField caixaId;
	private static IRepositorio<Solicitante> repSol;
	private static  IRepositorio<Motorista> repMot;
	
	public static void telaExcluirContaSolicitante(final JFrame telaGeral,final Solicitante sol)
	{	//Ler dados Salvos
		repSol=DataBase.lerBaseSolicitantes();
		
		//ActionListener
		ActionListener confirmarListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
		  		{try {
					repSol.remover(sol.getId());
					DataBase.salvarEstadoSolicitante(repSol);
					telaGeral.dispose();
					GUILogin.login();
					JOptionPane.showMessageDialog(null, "Usuario removido com Sucesso ! ", "MENSAGEM",JOptionPane.INFORMATION_MESSAGE);
				} catch (RepositorioException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRO",JOptionPane.ERROR_MESSAGE);
				}finally{janela.dispose();} }
			};
			
			ActionListener cancelarListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
			  		{janela.dispose();}
				};

			//Texto e Botoes
			JLabel texto = new JLabel("Voce deseja excluir a conta ? ");
			JButton confirmar = new JButton("confirmar");
			JButton cancelar = new JButton("cancelar");
			confirmar.addActionListener(confirmarListener);
			cancelar.addActionListener(cancelarListener);
	
			//Tela
			janela = new JFrame("Excluir Conta");
			janela.pack();
			janela.setSize(220, 100);
			janela.setLocationRelativeTo(null);
			janela.setResizable(false);
			janela.setVisible(true);
			janela.setLayout(new FlowLayout());
			janela.add(texto);
			janela.add(confirmar);
			janela.add(cancelar);		
			
	}
	
	public static void telaExcluirContaMotorista(final JFrame telaGeral,final Motorista mot)
	{	//Ler dados Salvos
		repMot=DataBase.lerBaseMotoristas();
		
		//ActionListener
		ActionListener confirmarListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
		  		{try {
					repMot.remover(mot.getId());
					DataBase.salvarEstadoMotorista(repMot);
					telaGeral.dispose();
					GUILogin.login();
					JOptionPane.showMessageDialog(null, "Usuario removido com Sucesso ! ", "MENSAGEM",JOptionPane.INFORMATION_MESSAGE);
				} catch (RepositorioException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRO",JOptionPane.ERROR_MESSAGE);
				}finally{janela.dispose();} }
			};
			
			ActionListener cancelarListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
			  		{janela.dispose();}
				};

			//Texto e Botoes
			JLabel texto = new JLabel("Voce deseja excluir a conta ? ");
			JButton confirmar = new JButton("confirmar");
			JButton cancelar = new JButton("cancelar");
			confirmar.addActionListener(confirmarListener);
			cancelar.addActionListener(cancelarListener);
		
			//Tela
			janela = new JFrame("Excluir Conta");
			janela.pack();
			janela.setSize(220, 100);
			janela.setLocationRelativeTo(null);
			janela.setResizable(false);
			janela.setVisible(true);
			janela.setLayout(new FlowLayout());
			janela.add(texto);
			janela.add(confirmar);
			janela.add(cancelar);	
			
	}
		
	private static void telaExcluirContaSolicitanteGerente(final Solicitante solicitante)
	{	//Ler dados Salvos
		repSol=DataBase.lerBaseSolicitantes();
		
		//ActionListener
		ActionListener confirmarListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
		  		{try {
					repSol.remover(solicitante.getId());
					DataBase.salvarEstadoSolicitante(repSol);
					JOptionPane.showMessageDialog(null, "Usuario removido com Sucesso ! ", "MENSAGEM",JOptionPane.INFORMATION_MESSAGE);
				} catch (RepositorioException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRO",JOptionPane.ERROR_MESSAGE);
				}finally{janela.dispose();} }
			};
			
			ActionListener cancelarListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
			  		{janela.dispose();}
				};

			//Texto e Botoes
			JLabel texto = new JLabel("Voce deseja excluir a conta ? ");
			JButton confirmar = new JButton("confirmar");
			JButton cancelar = new JButton("cancelar");
			confirmar.addActionListener(confirmarListener);
			cancelar.addActionListener(cancelarListener);

			//Tela
			janela = new JFrame("Excluir Conta");
			janela.pack();
			janela.setSize(220, 100);
			janela.setLocationRelativeTo(null);
			janela.setResizable(false);
			janela.setVisible(true);
			janela.setLayout(new FlowLayout());
			janela.add(texto);
			janela.add(confirmar);
			janela.add(cancelar);	
			
	}
	private static void telaEscolhaIdSolicitante()
	{	//Ler dados Salvos
		repSol=DataBase.lerBaseSolicitantes();
		
		//Actionlistener
		ActionListener confirmarListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
			  		{	if(caixaId.getText().matches("\\d{1,}")==true)
				  		{
				  			long id = Long.parseLong(caixaId.getText());
			  				try {
			  				Solicitante solicitante = repSol.buscar(id);
							janela.dispose();
							UIExcluirConta.telaExcluirContaSolicitanteGerente(solicitante);
			  				} catch (RepositorioException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRO",JOptionPane.ERROR_MESSAGE);
			  				}
			  			}
			  			else
			  				JOptionPane.showMessageDialog(null, "Digite apenas numeros", "ERRO",JOptionPane.ERROR_MESSAGE);
			  			
				  		
				  	}
			};
		ActionListener cancelarListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{janela.dispose();}
				};
				
		// Caixa de texto 
		JLabel id = new JLabel("ID SOLICITANTE :");
		caixaId = new JTextField(10);
		JPanel painel = new JPanel();
		caixaId.setEditable(true);
		painel.add(id);
		painel.add(caixaId);
		
		
		//Botao
		JButton confirmar = new JButton("CONFIRMAR");
		JButton cancelar = new JButton("CANCELAR");
		JPanel botoes = new JPanel();
		botoes.add(confirmar);
		botoes.add(cancelar);
		confirmar.addActionListener(confirmarListener);
		cancelar.addActionListener(cancelarListener);
		
		
		//Tela
		janela = new JFrame("Excluir Solicitante ");
		janela.setLayout(new FlowLayout());
		janela.pack();
		janela.setSize(250, 115);
		janela.setLocationRelativeTo(null);
		janela.setResizable(false);
		janela.setVisible(true);
		janela.add(painel);
		janela.add(botoes);
	}
	
	private static void telaRemoverMotoristaGerente(final long id,final Gerente gerente)
	{	//Ler dados Salvos
		repMot=DataBase.lerBaseMotoristas();
		
		//ActionListener
		ActionListener confirmarListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
		  		{try {
					gerente.removerMotorista(repMot, id);
					DataBase.salvarEstadoMotorista(repMot);
					DataBase.salvarEstadoGerente(gerente);
					JOptionPane.showMessageDialog(null, "Usuario removido com Sucesso ! ", "MENSAGEM",JOptionPane.INFORMATION_MESSAGE);
				} catch (RepositorioException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRO",JOptionPane.ERROR_MESSAGE);
				}finally{janela.dispose();} }
			};
			
			ActionListener cancelarListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
			  		{janela.dispose();}
				};

			//Texto e Botoes
			JLabel texto = new JLabel("Voce deseja excluir a conta ? ");
			JButton confirmar = new JButton("confirmar");
			JButton cancelar = new JButton("cancelar");
			confirmar.addActionListener(confirmarListener);
			cancelar.addActionListener(cancelarListener);
			
			//Tela
			janela = new JFrame("Excluir Conta");
			janela.pack();
			janela.setSize(220, 100);
			janela.setLocationRelativeTo(null);
			janela.setResizable(false);
			janela.setVisible(true);
			janela.setLayout(new FlowLayout());
			janela.add(texto);
			janela.add(confirmar);
			janela.add(cancelar);	
						
	}
	
	private static void telaEscolhaIdMotorista(final Gerente gerente)
	{	//Ler dados Salvos
		repMot=DataBase.lerBaseMotoristas();
		
		//Actionlistener
				ActionListener confirmarListener = new ActionListener() {
					  public void actionPerformed(ActionEvent e) 
					  		{	if(caixaId.getText().matches("\\d{1,}")==true)
						  		{
						  			long id = Long.parseLong(caixaId.getText());
						  			try {
										repMot.buscar(id);
										janela.dispose();
							  			telaRemoverMotoristaGerente(id,gerente);
									} catch (RepositorioException e1) {
										JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRO",JOptionPane.ERROR_MESSAGE);
									}
					  			}
					  			else
					  				JOptionPane.showMessageDialog(null, "Digite apenas numeros", "ERRO",JOptionPane.ERROR_MESSAGE);
					  			
						  		
						  	}
					};
				ActionListener cancelarListener = new ActionListener() {
						  public void actionPerformed(ActionEvent e) 
						  		{janela.dispose();}
						};
				// Caixa de texto 
				JLabel id = new JLabel("ID MOTORISTA :");
				caixaId = new JTextField(10);
				JPanel painel = new JPanel();
				caixaId.setEditable(true);
				painel.add(id);
				painel.add(caixaId);
				
				
				//Botao
				JButton confirmar = new JButton("CONFIRMAR");
				JButton cancelar = new JButton("CANCELAR");
				JPanel botoes = new JPanel();
				botoes.add(confirmar);
				botoes.add(cancelar);
				confirmar.addActionListener(confirmarListener);
				cancelar.addActionListener(cancelarListener);
				
				
				//Tela
				janela = new JFrame("Excluir Motorista ");
				janela.setLayout(new FlowLayout());
				janela.pack();
				janela.setSize(250, 115);
				janela.setLocationRelativeTo(null);
				janela.setResizable(false);
				janela.setVisible(true);
				janela.add(painel);
				janela.add(botoes);
	}
	
	public static void telaRemoverContasGerente(final Gerente gerente)//alterara dados do gerente e dos usuarios e motoristas que o gerente tem acesso
	{	//Ler dados Salvos
		repMot=DataBase.lerBaseMotoristas();
		repSol=DataBase.lerBaseSolicitantes();
		
		//Actionlistener
		ActionListener confirmarListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
			  		{	
			  			if(selecao.getSelectedItem()=="Motorista")
			  			{	janela.dispose();
			  				UIExcluirConta.telaEscolhaIdMotorista(gerente);
			  			}
			  			if(selecao.getSelectedItem()=="Solicitante")
			  			{	janela.dispose();
			  				UIExcluirConta.telaEscolhaIdSolicitante();
			  			}
				  	}
			};
		ActionListener cancelarListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{janela.dispose();}
				};
				
		// Caixa de texto 
		JLabel texto = new JLabel("Qual o usuario deseja excluir:");
		JPanel painel = new JPanel();
		selecao = new JComboBox<String>();
		selecao.addItem("Solicitante");
		selecao.addItem("Motorista");
		selecao.setEditable(false);
		painel.add(texto);
		painel.add(selecao);
		
		//Botao
		JButton confirmar = new JButton("CONFIRMAR");
		JButton cancelar = new JButton("CANCELAR");
		JPanel botoes = new JPanel();
		botoes.add(confirmar);
		botoes.add(cancelar);
		confirmar.addActionListener(confirmarListener);
		cancelar.addActionListener(cancelarListener);
		
		
		//Tela
		janela = new JFrame("Excluir Conta");
		janela.setLayout(new FlowLayout());
		janela.pack();
		janela.setSize(290, 120);
		janela.setLocationRelativeTo(null);
		janela.setResizable(false);
		janela.setVisible(true);
		janela.add(painel);
		janela.add(botoes);
		
	}
}
