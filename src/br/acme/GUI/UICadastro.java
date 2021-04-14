package br.acme.GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.acme.exception.CPFInvalidoException;
import br.acme.exception.DataInvalidaException;
import br.acme.exception.EmailInvalidoException;
import br.acme.exception.NomeNullException;
import br.acme.exception.NomeVazioException;
import br.acme.exception.NumeroCelularInvalidoException;
import br.acme.exception.RepositorioException;
import br.acme.exception.SenhaInvalidaException;
import br.acme.exception.SexoInvalidoException;
import br.acme.storage.DataBase;
import br.acme.storage.IRepositorio;
import br.acme.users.Gerente;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;
import br.acme.users.Usuario;

abstract public class UICadastro {
		
		private static JFrame tela ;
		private static JComboBox<String> selecao;
		private static JTextField caixaNome;
		private static JFormattedTextField caixaCpf;
		private static JFormattedTextField caixaData;
		private static JFormattedTextField caixaFone;
		private static JRadioButton caixaSexo1;
		private static JRadioButton caixaSexo2;
		private static JTextField caixaEmail;
		private static JPasswordField caixaSenha;
		private static MaskFormatter mascaraCpf;
		private static MaskFormatter mascaraData;
		private static MaskFormatter mascaraFone;
		private static IRepositorio<Solicitante> repSol;
		private static IRepositorio<Motorista> repMot; 
		private static Gerente gerente;
		
		
		public static void telaCadastroSolicitante()
		{	//Ler dados Salvos
			repSol=DataBase.lerBaseSolicitantes();
			repMot=DataBase.lerBaseMotoristas();
			gerente=DataBase.lerBaseGerente();
			
			//Actionlistener
			ActionListener confirmarListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{	String senha = new String(caixaSenha.getPassword());
					  		String sexo=null;
					  		String stringData= caixaData.getText();
					  		
					  		if(caixaSexo1.isSelected()==true)
				  				sexo="Masculino";
				  			if(caixaSexo2.isSelected()==true)
				  				sexo="Feminino";
						  	
				  			if(selecao.getSelectedItem()=="Solicitante")
					  		{	try {
					  				int numeroCelular= Integer.parseInt(caixaFone.getText().replaceAll("-", ""));
					  				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
									Date data = formato.parse(stringData);
									Usuario.emailLiberado(caixaEmail.getText(), gerente, repSol, repMot);	// se nao existir outro email, criaremos o objeto
									Solicitante solicitante = new Solicitante(caixaNome.getText(),caixaCpf.getText(),sexo,numeroCelular,data,caixaEmail.getText(),senha);    
					  				repSol.adicionar(solicitante);
									DataBase.salvarEstadoSolicitante(repSol);
									JOptionPane.showMessageDialog(null, "Solicitante cadastrado com sucesso !", "Mensagem",JOptionPane.INFORMATION_MESSAGE);
					  				tela.dispose();
				  				} catch(NumberFormatException e1){
				  					JOptionPane.showMessageDialog(null, "Telefone Celular em Formato invalido", "Mensagem",JOptionPane.INFORMATION_MESSAGE);
				  				} catch (ParseException e1) {
				  					JOptionPane.showMessageDialog(null, "Data em Formato invalido", "Mensagem",JOptionPane.INFORMATION_MESSAGE);
				  				} catch (NumeroCelularInvalidoException e1) {
				  					JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
								} catch (NomeNullException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
								} catch (NomeVazioException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
								} catch (CPFInvalidoException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
								} catch (SexoInvalidoException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
								} catch (EmailInvalidoException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
								} catch (SenhaInvalidaException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
								} catch (RepositorioException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
								} catch (DataInvalidaException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(),"Mensagem",JOptionPane.INFORMATION_MESSAGE);
								}
					  		}
					  	}
				};
			ActionListener cancelarListener = new ActionListener() {
					  public void actionPerformed(ActionEvent e) 
					  		{tela.dispose();}
					};
			ActionListener masculinoListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{caixaSexo2.setSelected(false);}
				};
			ActionListener femininoListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{caixaSexo1.setSelected(false);}
				};
							
			//Mouse Listener		
			MouseListener nomeMouseListener = new MouseListener()
					{	public void mouseClicked(MouseEvent e) 
						{caixaNome.setText("");}
						public void mouseEntered(MouseEvent e) 
						{		}
						public void mouseExited(MouseEvent e)
						{		}
						public void mousePressed(MouseEvent e) 
						{		}
						public void mouseReleased(MouseEvent e) 
						{		}
					};
				
			//FocusListener
			FocusListener cpfFocusListener = new FocusListener()
			{public void focusGained(FocusEvent e) 
				{caixaCpf.setText("");}
			 public void focusLost(FocusEvent e)
			 	{	 				}	
			 };
			FocusListener dataFocusListener = new FocusListener()
			{public void focusGained(FocusEvent e) 
				{caixaData.setText("");}
			 public void focusLost(FocusEvent e)
			 	{	 				}	
			 };
			FocusListener foneFocusListener = new FocusListener()
			{public void focusGained(FocusEvent e) 
				{caixaFone.setText("");}
			 public void focusLost(FocusEvent e)
			 	{	 				}	
			 };
			FocusListener emailFocusListener = new FocusListener()
			{public void focusGained(FocusEvent e) 
				{caixaEmail.setText("");}
			 public void focusLost(FocusEvent e)
			 	{	 				}	
			 };
			FocusListener senhaFocusListener = new FocusListener()
			{public void focusGained(FocusEvent e) 
				{caixaSenha.setText("");}
			 public void focusLost(FocusEvent e)
			 	{	 				}	
			 };
			
			
			// Selecao Usuario
			JLabel escolhaUsuario = new JLabel("Qual o usuario deseja cadastrar:");
			JPanel painelEscolhaUsuario = new JPanel();
			selecao = new JComboBox<String>();
			selecao.addItem("Solicitante");
			selecao.setEditable(false);
			painelEscolhaUsuario.add(escolhaUsuario);
			painelEscolhaUsuario.add(selecao);
			
			//MaskFormatter
			 
			 try {
				 mascaraCpf = new MaskFormatter("###.###.###-##");
				 mascaraData = new MaskFormatter("##/##/####");
				 mascaraFone = new MaskFormatter("####-####");
			} catch (ParseException e1) {
				System.out.println(e1.getMessage());
			}
			 
			//Formulario
			JLabel textoCadastro = new JLabel("CADASTRO DE USUARIO");
			JLabel nome = new JLabel("NOME:");
			caixaNome = new JTextField(30);
			JPanel nomePainel = new JPanel();
			JLabel cpf = new JLabel("CPF:");
			caixaCpf = new JFormattedTextField(mascaraCpf);
			JPanel cpfPainel = new JPanel();
			JLabel data = new JLabel("DATA DE NASCIMENTO:");
			caixaData = new JFormattedTextField(mascaraData);
			JPanel dataPainel = new JPanel();
			JLabel fone = new JLabel("TELEFONE CELULAR:");
			caixaFone = new JFormattedTextField(mascaraFone);
			JPanel fonePainel = new JPanel();
			JLabel sexo = new JLabel("SEXO:");
			caixaSexo1 = new JRadioButton("Masculino");
			caixaSexo2 = new JRadioButton("Feminino");
			JPanel sexoPainel = new JPanel();
			JLabel email = new JLabel("EMAIL:");
			caixaEmail = new JTextField(30);
			JPanel emailPainel = new JPanel();
			JLabel senha = new JLabel("SENHA:");
			caixaSenha = new JPasswordField(30);
			JPanel senhaPainel = new JPanel();
			textoCadastro.setForeground(Color.BLACK);
			textoCadastro.setFont(new Font("Arial", Font.BOLD, 17));
			nomePainel.add(nome);
			nomePainel.add(caixaNome);
			cpfPainel.add(cpf);
			cpfPainel.add(caixaCpf);
			dataPainel.add(data);
			dataPainel.add(caixaData);
			fonePainel.add(fone);
			fonePainel.add(caixaFone);
			sexoPainel.add(sexo);
			sexoPainel.add(caixaSexo1);
			sexoPainel.add(caixaSexo2);
			emailPainel.add(email);
			emailPainel.add(caixaEmail);
			senhaPainel.add(senha);
			senhaPainel.add(caixaSenha);
			
			//Mostrar informacoes de usuario no formulario
			caixaNome.setText("Solicitante");
			caixaCpf.setText("111.111.111-11");
			caixaData.setText("01/01/2016");
			caixaFone.setText("99999999");
			caixaSexo1.setSelected(true);
			caixaEmail.setText("usuario@dominio.com");
			caixaSenha.setText("user");
			
			//Apagar caixas ao selecionar
			caixaNome.addMouseListener(nomeMouseListener);
			caixaCpf.addFocusListener(cpfFocusListener);
			caixaData.addFocusListener(dataFocusListener);
			caixaFone.addFocusListener(foneFocusListener);
			caixaEmail.addFocusListener(emailFocusListener);
			caixaSenha.addFocusListener(senhaFocusListener);
			
			//Selecao do Sexo
			caixaSexo1.addActionListener(masculinoListener);
			caixaSexo2.addActionListener(femininoListener);
			
			//Botao
			JButton confirmar = new JButton("CADASTRAR");
			JButton cancelar = new JButton("CANCELAR");
			JPanel botoes = new JPanel();
			botoes.add(confirmar);
			botoes.add(cancelar);
			confirmar.addActionListener(confirmarListener);
			cancelar.addActionListener(cancelarListener);
			
			
			//Tela
			tela = new JFrame();
			tela.setLayout(new FlowLayout());
			tela.pack();
			tela.setSize(500, 330);
			tela.setLocationRelativeTo(null);
			tela.setResizable(false);
			tela.setVisible(true);
			tela.add(textoCadastro);
			tela.add(painelEscolhaUsuario);
			tela.add(nomePainel);
			tela.add(cpfPainel);
			tela.add(dataPainel);
			tela.add(fonePainel);
			tela.add(sexoPainel);
			tela.add(emailPainel);
			tela.add(senhaPainel);
			tela.add(botoes);
		}
		
		public static void telaCadastroUsuariosGerente()	//cadastrara o motorista e os usuarios 
		{	//Ler dados Salvos
			repSol=DataBase.lerBaseSolicitantes();
			repMot=DataBase.lerBaseMotoristas();
			gerente=DataBase.lerBaseGerente();
		
			//Actionlistener
			ActionListener confirmarListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{	String senha = new String(caixaSenha.getPassword());
					  		String sexo=null;
					  		String stringData= caixaData.getText();
					  		
				  			
					  		if(caixaSexo1.isSelected()==true)
				  				sexo="Masculino";
				  			if(caixaSexo2.isSelected()==true)
				  				sexo="Feminino";
				  			
				  			
				  			if(selecao.getSelectedItem()=="Solicitante")
				  			{	try {
				  				int numeroCelular= Integer.parseInt(caixaFone.getText().replaceAll("-", ""));
				  				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
								Date data = formato.parse(stringData);
								Usuario.emailLiberado(caixaEmail.getText(), gerente, repSol, repMot);   // se nao existir outro email, criaremos o objeto
								Solicitante solicitante = new Solicitante(caixaNome.getText(),caixaCpf.getText(),sexo,numeroCelular,data,caixaEmail.getText(),senha);    
				  				repSol.adicionar(solicitante);
				  				DataBase.salvarEstadoSolicitante(repSol);
								JOptionPane.showMessageDialog(null, "Solicitante cadastrado com sucesso !", "Mensagem",JOptionPane.INFORMATION_MESSAGE);
				  				tela.dispose();
					  			} catch(NumberFormatException e1){
				  					JOptionPane.showMessageDialog(null, "Telefone Celular em Formato invalido", "Mensagem",JOptionPane.INFORMATION_MESSAGE);
				  				} catch (ParseException e1) {
				  					JOptionPane.showMessageDialog(null, "Data em Formato invalido", "Mensagem",JOptionPane.INFORMATION_MESSAGE);
				  				} catch (NumeroCelularInvalidoException e1) {
				  					JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
								} catch (NomeNullException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
								} catch (NomeVazioException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
								} catch (CPFInvalidoException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
								} catch (SexoInvalidoException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
								} catch (EmailInvalidoException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
								} catch (SenhaInvalidaException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
								} catch (RepositorioException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
								} catch (DataInvalidaException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(),"Mensagem",JOptionPane.INFORMATION_MESSAGE);
								}
				  			}
				  			
				  			if(selecao.getSelectedItem()=="Motorista")
				  			{	try {
				  				Usuario.emailLiberado(caixaEmail.getText(), gerente, repSol, repMot);		// se nao existir outro email, criaremos o objeto
				  				Motorista motorista = new Motorista(caixaNome.getText(),caixaCpf.getText(),sexo,caixaEmail.getText(),senha);
				  				repMot.adicionar(motorista);
				  				DataBase.salvarEstadoMotorista(repMot);
				  				JOptionPane.showMessageDialog(null, "Motorista cadastrado com sucesso !", "Mensagem",JOptionPane.INFORMATION_MESSAGE);
				  				tela.dispose();
						  		} catch (NomeNullException e1) {
						  			JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
								} catch (NomeVazioException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
								} catch (SenhaInvalidaException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
								} catch (EmailInvalidoException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
								} catch (SexoInvalidoException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
								} catch (CPFInvalidoException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
								} catch (RepositorioException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
								}
				  			}
				  		}
				};
			ActionListener cancelarListener = new ActionListener() {
					  public void actionPerformed(ActionEvent e) 
					  		{tela.dispose();}
					};
			ActionListener masculinoListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{caixaSexo2.setSelected(false);}
				};
			ActionListener femininoListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{caixaSexo1.setSelected(false);}
				};
				
				
			//Mouse Listener		
			MouseListener nomeMouseListener = new MouseListener()
					{	public void mouseClicked(MouseEvent e) 
						{caixaNome.setText("");}
						public void mouseEntered(MouseEvent e) 
						{		}
						public void mouseExited(MouseEvent e)
						{		}
						public void mousePressed(MouseEvent e) 
						{		}
						public void mouseReleased(MouseEvent e) 
						{		}
					};
				
			//FocusListener
			FocusListener cpfFocusListener = new FocusListener()
			{public void focusGained(FocusEvent e) 
				{caixaCpf.setText("");}
			 public void focusLost(FocusEvent e)
			 	{	 				}	
			 };
			FocusListener dataFocusListener = new FocusListener()
			{public void focusGained(FocusEvent e) 
				{caixaData.setText("");}
			 public void focusLost(FocusEvent e)
			 	{	 				}	
			 };
			FocusListener foneFocusListener = new FocusListener()
			{public void focusGained(FocusEvent e) 
				{caixaFone.setText("");}
			 public void focusLost(FocusEvent e)
			 	{	 				}	
			 };
			FocusListener emailFocusListener = new FocusListener()
			{public void focusGained(FocusEvent e) 
				{caixaEmail.setText("");}
			 public void focusLost(FocusEvent e)
			 	{	 				}	
			 };
			FocusListener senhaFocusListener = new FocusListener()
			{public void focusGained(FocusEvent e) 
				{caixaSenha.setText("");}
			 public void focusLost(FocusEvent e)
			 	{	 				}	
			 };
				
					
			// Selecao Usuario
			JLabel escolhaUsuario = new JLabel("Qual o usuario deseja cadastrar:");
			JPanel painelEscolhaUsuario = new JPanel();
			selecao = new JComboBox<String>();
			selecao.addItem("Solicitante");
			selecao.addItem("Motorista");
			selecao.setEditable(false);
			painelEscolhaUsuario.add(escolhaUsuario);
			painelEscolhaUsuario.add(selecao);

			//MaskFormatter
			 
			 try {
				 mascaraCpf = new MaskFormatter("###.###.###-##");
				 mascaraData = new MaskFormatter("##/##/####");
				 mascaraFone = new MaskFormatter("####-####");
			} catch (ParseException e1) {
				System.out.println(e1.getMessage());
			}
			 
			// Formulario 
			JLabel textoCadastro = new JLabel("CADASTRO DE USUARIO");
			JLabel nome = new JLabel("NOME:");
			caixaNome = new JTextField(30);
			JPanel nomePainel = new JPanel();
			JLabel cpf = new JLabel("CPF:");
			caixaCpf =  new JFormattedTextField(mascaraCpf);
			JPanel cpfPainel = new JPanel();
			JLabel data = new JLabel("DATA DE NASCIMENTO:");
			JLabel somenteSolData = new JLabel(" * ");
			caixaData = new JFormattedTextField(mascaraData);
			JPanel dataPainel = new JPanel();
			JLabel fone = new JLabel("TELEFONE CELULAR:");
			caixaFone = new JFormattedTextField(mascaraFone);
			JLabel somenteSolFone = new JLabel(" * ");
			JPanel fonePainel = new JPanel();
			JLabel sexo = new JLabel("SEXO:");
			caixaSexo1 = new JRadioButton("Masculino");
			caixaSexo2 = new JRadioButton("Feminino");
			JPanel sexoPainel = new JPanel();
			JLabel email = new JLabel("EMAIL:");
			caixaEmail = new JTextField(30);
			JPanel emailPainel = new JPanel();
			JLabel senha = new JLabel("SENHA:");
			caixaSenha = new JPasswordField(30);
			JPanel senhaPainel = new JPanel();
			JLabel somenteSol = new JLabel("* Apenas para Solicitante                                                   ");
			somenteSol.setForeground(Color.RED);
			somenteSol.setFont(new Font("Arial", Font.BOLD, 10));
			somenteSolData.setForeground(Color.RED);
			somenteSolFone.setForeground(Color.RED);
			textoCadastro.setForeground(Color.BLACK);
			textoCadastro.setFont(new Font("Arial", Font.BOLD, 17));
			nomePainel.add(nome);
			nomePainel.add(caixaNome);
			cpfPainel.add(cpf);
			cpfPainel.add(caixaCpf);
			dataPainel.add(data);
			dataPainel.add(caixaData);
			dataPainel.add(somenteSolData);
			fonePainel.add(fone);
			fonePainel.add(caixaFone);
			fonePainel.add(somenteSolFone);
			sexoPainel.add(sexo);
			sexoPainel.add(caixaSexo1);
			sexoPainel.add(caixaSexo2);
			emailPainel.add(email);
			emailPainel.add(caixaEmail);
			senhaPainel.add(senha);
			senhaPainel.add(caixaSenha);
			
			//Mostrar informacoes de usuario no formulario
			caixaNome.setText("Nome");
			caixaCpf.setText("111.111.111-11");
			caixaData.setText("01/01/2016");
			caixaFone.setText("99999999");
			caixaSexo1.setSelected(true);
			caixaEmail.setText("usuario@dominio.com");
			caixaSenha.setText("user");
			
			
			//Apagar caixas ao selecionar
			caixaNome.addMouseListener(nomeMouseListener);
			caixaCpf.addFocusListener(cpfFocusListener);
			caixaData.addFocusListener(dataFocusListener);
			caixaFone.addFocusListener(foneFocusListener);
			caixaEmail.addFocusListener(emailFocusListener);
			caixaSenha.addFocusListener(senhaFocusListener);
			
			//Selecao do Sexo
			caixaSexo1.addActionListener(masculinoListener);
			caixaSexo2.addActionListener(femininoListener);
			
			//Botao
			JButton confirmar = new JButton("CADASTRAR");
			JButton cancelar = new JButton("CANCELAR");
			JPanel botoes = new JPanel();
			botoes.add(confirmar);
			botoes.add(cancelar);
			confirmar.addActionListener(confirmarListener);
			cancelar.addActionListener(cancelarListener);
			
			
			//Tela
			tela = new JFrame();
			tela.setLayout(new FlowLayout());
			tela.pack();
			tela.setSize(500,350);
			tela.setLocationRelativeTo(null);
			tela.setResizable(false);
			tela.setVisible(true);
			tela.add(textoCadastro);
			tela.add(painelEscolhaUsuario);
			tela.add(nomePainel);
			tela.add(cpfPainel);
			tela.add(dataPainel);
			tela.add(fonePainel);
			tela.add(sexoPainel);
			tela.add(emailPainel);
			tela.add(senhaPainel);
			tela.add(somenteSol);
			tela.add(botoes);
			
		}
				
}
