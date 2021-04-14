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

import javax.swing.*;
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

abstract public class UIAlterarDados {
	
	private static JFrame tela ;
	private static JComboBox<String> selecao;
	private static JTextField caixaId;
	private static JTextField caixaNome;
	private static JFormattedTextField caixaCpfMascara;
	private static JFormattedTextField caixaDataMascara;
	private static JFormattedTextField caixaFoneMascara;
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
	
	public static void telaAlterarDadosSolicitante(final Solicitante solicitante )
	{	//Ler dados Salvos
		repMot=DataBase.lerBaseMotoristas();
		repSol=DataBase.lerBaseSolicitantes();
		gerente=DataBase.lerBaseGerente();
	
	
		//Actionlistener
		ActionListener confirmarListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
			  		{	String senha = new String(caixaSenha.getPassword());
			  			String sexo=null;
			  			String stringData= caixaDataMascara.getText();
			  			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			  			if(caixaSexo1.isSelected()==true)
			  				{sexo="Masculino";}
			  			if(caixaSexo2.isSelected()==true)
			  				{sexo="Feminino";}
		  				if(caixaSexo1.isSelected()== true || caixaSexo2.isSelected()==true)
				  		{	try {// procuraremos a existencia de outro email igual em outros repositorios, exceto o que estamos alterando que ja possui esse unico email cadastrado que queremos alterar  
								int numeroCelular= Integer.parseInt(caixaFoneMascara.getText().replaceAll("-", ""));
								Date data = formato.parse(stringData);
								Usuario.emailLiberado(caixaEmail.getText(), gerente, null, repMot);		// se nao existir outro email igual iremos alterar as informacoes   
								repSol.alterar(solicitante.getId(),caixaNome.getText(),caixaCpfMascara.getText(),sexo,caixaEmail.getText(),senha);
					  			solicitante.setNumeroCelular(numeroCelular);
					  			solicitante.setDataNascimento(data);
					  			DataBase.salvarEstadoSolicitante(repSol);			
					  			JOptionPane.showMessageDialog(null, "Usuario Alterado com Sucesso ! ", "MENSAGEM",JOptionPane.INFORMATION_MESSAGE);
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
		  				else
		  					JOptionPane.showMessageDialog(null, "Caixa Vazia", "Mensagem",JOptionPane.INFORMATION_MESSAGE);
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
			{caixaCpfMascara.setText("");}
		 public void focusLost(FocusEvent e)
		 	{	 				}	
		 };
		FocusListener dataFocusListener = new FocusListener()
		{public void focusGained(FocusEvent e) 
			{caixaDataMascara.setText("");}
		 public void focusLost(FocusEvent e)
		 	{	 				}	
		 };
		FocusListener foneFocusListener = new FocusListener()
		{public void focusGained(FocusEvent e) 
			{caixaFoneMascara.setText("");}
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
		 

		//MaskFormatter
		 
		 try {
			 mascaraCpf = new MaskFormatter("###.###.###-##");
			 mascaraData = new MaskFormatter("##/##/####");
			 mascaraFone= new MaskFormatter("####-####");
		} catch (ParseException e1) {
			System.out.println(e1.getMessage());
		}
		 
		// Caixa de texto 
		JLabel textoAlterarDados = new JLabel("ALTERAR DADOS DO SOLICITANTE");
		JLabel id = new JLabel("ID:");
		caixaId = new JTextField(30);
		JPanel idPainel = new JPanel();
		JLabel nome = new JLabel("NOME:");
		caixaNome = new JTextField(30);
		JPanel nomePainel = new JPanel();
		JLabel cpf = new JLabel("CPF:");
		caixaCpfMascara =  new JFormattedTextField(mascaraCpf);
		JPanel cpfPainel = new JPanel();
		JLabel data = new JLabel("DATA DE NASCIMENTO:");
		caixaDataMascara = new JFormattedTextField(mascaraData);
		JPanel dataPainel = new JPanel();
		JLabel fone = new JLabel("TELEFONE CELULAR:");
		caixaFoneMascara = new JFormattedTextField(mascaraFone);
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
		textoAlterarDados.setForeground(Color.BLACK);
		textoAlterarDados.setFont(new Font("Arial", Font.BOLD, 17));
		idPainel.add(id);
		idPainel.add(caixaId);
		nomePainel.add(nome);
		nomePainel.add(caixaNome);
		cpfPainel.add(cpf);
		cpfPainel.add(caixaCpfMascara);
		dataPainel.add(data);
		dataPainel.add(caixaDataMascara);
		fonePainel.add(fone);
		fonePainel.add(caixaFoneMascara);
		sexoPainel.add(sexo);
		sexoPainel.add(caixaSexo1);
		sexoPainel.add(caixaSexo2);
		emailPainel.add(email);
		emailPainel.add(caixaEmail);
		senhaPainel.add(senha);
		senhaPainel.add(caixaSenha);
		
		
		//Selecao do Sexo
		caixaSexo1.addActionListener(masculinoListener);
		caixaSexo2.addActionListener(femininoListener);
		
		//Botao
		JButton confirmar = new JButton("CONFIRMAR");
		JButton cancelar = new JButton("CANCELAR");
		JPanel botoes = new JPanel();
		botoes.add(confirmar);
		botoes.add(cancelar);
		confirmar.addActionListener(confirmarListener);
		cancelar.addActionListener(cancelarListener);
		
		//Mostrar dados do usuario
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNascimento = solicitante.getDataNascimento();
		caixaId.setText(String.valueOf(solicitante.getId()));
		caixaId.setEditable(false);
		caixaNome.setText(solicitante.getNome());
		caixaCpfMascara.setText(solicitante.getCpf());
		caixaFoneMascara.setText(String.valueOf(solicitante.getNumeroCelular()));
		caixaDataMascara.setText(formato.format(dataNascimento));
		caixaEmail.setText(solicitante.getEmail());
		caixaSenha.setText(solicitante.getSenha());
		if(solicitante.getSexo()=="Masculino")
			{caixaSexo1.setSelected(true);}
		if(solicitante.getSexo()=="Feminino")
			{caixaSexo2.setSelected(true);}
		
		//Apagar caixas ao selecionar
		caixaNome.addMouseListener(nomeMouseListener);
		caixaCpfMascara.addFocusListener(cpfFocusListener);
		caixaDataMascara.addFocusListener(dataFocusListener);
		caixaFoneMascara.addFocusListener(foneFocusListener);
		caixaEmail.addFocusListener(emailFocusListener);
		caixaSenha.addFocusListener(senhaFocusListener);
		
		//Tela
		tela = new JFrame("Alterar dados Solicitante");
		tela.setLayout(new FlowLayout());
		tela.pack();
		tela.setSize(500, 330);
		tela.setLocationRelativeTo(null);
		tela.setResizable(false);
		tela.setVisible(true);
		tela.add(textoAlterarDados);
		tela.add(idPainel);
		tela.add(nomePainel);
		tela.add(cpfPainel);
		tela.add(dataPainel);
		tela.add(fonePainel);
		tela.add(sexoPainel);
		tela.add(emailPainel);
		tela.add(senhaPainel);
		tela.add(botoes);
		
	}
	
	public static void telaAlterarDadosMotorista(final Motorista motorista)
	{	//Ler dados Salvos
		repMot=DataBase.lerBaseMotoristas();
		repSol=DataBase.lerBaseSolicitantes();
		gerente=DataBase.lerBaseGerente();
		
		//Actionlistener
		ActionListener confirmarListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
			  		{	String senha = new String(caixaSenha.getPassword());
			  			String sexo=null;
			  			
			  			if(caixaSexo1.isSelected()==true)
			  				{sexo="Masculino";}
			  			if(caixaSexo2.isSelected()==true)
			  				{sexo="Feminino";}
			  			
			  			if(caixaSexo1.isSelected()== true || caixaSexo2.isSelected()==true)
				  		{	try {// procuraremos a existencia de outro email igual em outros repositorios, exceto o que estamos alterando que ja possui esse unico email cadastrado que queremos alterar  
				  				Usuario.emailLiberado(caixaEmail.getText(), gerente, repSol, null);	// se nao existir outro email igual iremos alterar as informacoes 
				  				repMot.alterar(motorista.getId(),caixaNome.getText(),caixaCpfMascara.getText(),sexo,caixaEmail.getText(),senha);
				  				DataBase.salvarEstadoMotorista(repMot);
				  				JOptionPane.showMessageDialog(null, "Usuario Alterado com Sucesso ! ", "Mensagem",JOptionPane.INFORMATION_MESSAGE);
				  				tela.dispose();
					  		} catch (NomeNullException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
							} catch (NomeVazioException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
							} catch (CPFInvalidoException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
							} catch (EmailInvalidoException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
							} catch (SenhaInvalidaException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
							} catch (SexoInvalidoException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
							} catch (RepositorioException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
							}
				  		}
			  			else
			  				JOptionPane.showMessageDialog(null, "Caixa Vazia", "Mensagem",JOptionPane.INFORMATION_MESSAGE);
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
			{caixaCpfMascara.setText("");}
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
			
		 //MaskFormatter
		 
		 try {
			 mascaraCpf = new MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			System.out.println(e1.getMessage());
		}
			 
		// Caixa de texto 
		JLabel textoAlterarDados = new JLabel("ALTERAR DADOS DO MOTORISTA");
		JLabel id = new JLabel("ID:");
		caixaId = new JTextField(30);
		JPanel idPainel = new JPanel();
		JLabel nome = new JLabel("NOME:");
		caixaNome = new JTextField(30);
		JPanel nomePainel = new JPanel();
		JLabel cpf = new JLabel("CPF:");
		caixaCpfMascara = new JFormattedTextField(mascaraCpf);
		JPanel cpfPainel = new JPanel();
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
		textoAlterarDados.setForeground(Color.BLACK);
		textoAlterarDados.setFont(new Font("Arial", Font.BOLD, 17));
		idPainel.add(id);
		idPainel.add(caixaId);
		nomePainel.add(nome);
		nomePainel.add(caixaNome);
		cpfPainel.add(cpf);
		cpfPainel.add(caixaCpfMascara);
		sexoPainel.add(sexo);
		sexoPainel.add(caixaSexo1);
		sexoPainel.add(caixaSexo2);
		emailPainel.add(email);
		emailPainel.add(caixaEmail);
		senhaPainel.add(senha);
		senhaPainel.add(caixaSenha);
		
		
		//Apagar caixas ao selecionar
		caixaNome.addMouseListener(nomeMouseListener);
		caixaCpfMascara.addFocusListener(cpfFocusListener);
		caixaEmail.addFocusListener(emailFocusListener);
		caixaSenha.addFocusListener(senhaFocusListener);
		
		//Selecao do Sexo
		caixaSexo1.addActionListener(masculinoListener);
		caixaSexo2.addActionListener(femininoListener);
				
		//Botao
		JButton confirmar = new JButton("CONFIRMAR");
		JButton cancelar = new JButton("CANCELAR");
		JPanel botoes = new JPanel();
		botoes.add(confirmar);
		botoes.add(cancelar);
		confirmar.addActionListener(confirmarListener);
		cancelar.addActionListener(cancelarListener);
		
		//Mostrar dados do usuario
		caixaId.setText(String.valueOf(motorista.getId()));
		caixaId.setEditable(false);
		caixaNome.setText(motorista.getNome());
		caixaCpfMascara.setText(motorista.getCpf());
		caixaEmail.setText(motorista.getEmail());
		caixaSenha.setText(motorista.getSenha());
		if(motorista.getSexo()=="Masculino")
			caixaSexo1.setSelected(true);
		if(motorista.getSexo()=="Feminino")
			caixaSexo2.setSelected(true);
		
				
		//Tela
		tela = new JFrame("Alterar dados Motorista");
		tela.setLayout(new FlowLayout());
		tela.pack();
		tela.setSize(500, 300);
		tela.setLocationRelativeTo(null);
		tela.setResizable(false);
		tela.setVisible(true);
		tela.add(textoAlterarDados);
		tela.add(idPainel);
		tela.add(nomePainel);
		tela.add(cpfPainel);
		tela.add(sexoPainel);
		tela.add(emailPainel);
		tela.add(senhaPainel);
		tela.add(botoes);
		
	}
	
	private static void telaAlterarDadosGerente()
	{	//Ler dados Salvos
		repMot=DataBase.lerBaseMotoristas();
		repSol=DataBase.lerBaseSolicitantes();
		gerente=DataBase.lerBaseGerente();
		
		//Actionlistener
				ActionListener confirmarListener = new ActionListener() {
					  public void actionPerformed(ActionEvent e) 
					  		{	String senha = new String(caixaSenha.getPassword());
					  			String sexo=null;
					  			
					  			if(caixaSexo1.isSelected()==true)
					  				{sexo="Masculino";}
					  			if(caixaSexo2.isSelected()==true)
					  				{sexo="Feminino";}
								
					  			if(caixaSexo1.isSelected()== true || caixaSexo2.isSelected()==true)
						  		{	try { // procuraremos a existencia de outro email igual em outros repositorios, exceto o que estamos alterando que ja possui esse unico email cadastrado que queremos alterar  
										Usuario.emailLiberado(caixaEmail.getText(), null, repSol, repMot); // se nao existir outro email igual iremos alterar as informacoes 
										gerente.setNome(caixaNome.getText());
							  			gerente.setCpf(caixaCpfMascara.getText());
							  			gerente.setSexo(sexo);
							  			gerente.setEmail(caixaEmail.getText());
							  			gerente.setSenha(senha);
							  			DataBase.salvarEstadoGerente(gerente);
							  			JOptionPane.showMessageDialog(null, "Usuario Alterado com Sucesso ! ", "MENSAGEM",JOptionPane.INFORMATION_MESSAGE);
								  		tela.dispose();
									} catch (NomeNullException e1) {
										JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
									} catch (NomeVazioException e1) {
										JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
									} catch (CPFInvalidoException e1) {
										JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
									} catch (EmailInvalidoException e1) {
										JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
									} catch (SenhaInvalidaException e1) {
										JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
									} catch (SexoInvalidoException e1) {
										JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem",JOptionPane.INFORMATION_MESSAGE);
									}
					  			}
								else
									JOptionPane.showMessageDialog(null, "Caixa Vazia", "Mensagem",JOptionPane.INFORMATION_MESSAGE);
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
					{caixaCpfMascara.setText("");}
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
					
				//MaskFormatter
				 
				 try {
					 mascaraCpf = new MaskFormatter("###.###.###-##");
				} catch (ParseException e1) {
					System.out.println(e1.getMessage());
				}
				 
				// Caixa de texto 
				JLabel textoAlterarDados = new JLabel("ALTERAR DADOS DO GERENTE");
				JLabel id = new JLabel("ID:");
				caixaId = new JTextField(30);
				JPanel idPainel = new JPanel();
				JLabel nome = new JLabel("NOME:");
				caixaNome = new JTextField(30);
				JPanel nomePainel = new JPanel();
				JLabel cpf = new JLabel("CPF:");
				caixaCpfMascara = new JFormattedTextField(mascaraCpf);
				JPanel cpfPainel = new JPanel();
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
				textoAlterarDados.setForeground(Color.BLACK);
				textoAlterarDados.setFont(new Font("Arial", Font.BOLD, 17));
				idPainel.add(id);
				idPainel.add(caixaId);
				nomePainel.add(nome);
				nomePainel.add(caixaNome);
				cpfPainel.add(cpf);
				cpfPainel.add(caixaCpfMascara);
				sexoPainel.add(sexo);
				sexoPainel.add(caixaSexo1);
				sexoPainel.add(caixaSexo2);
				emailPainel.add(email);
				emailPainel.add(caixaEmail);
				senhaPainel.add(senha);
				senhaPainel.add(caixaSenha);
				
				//Apagar caixas ao selecionar
				caixaNome.addMouseListener(nomeMouseListener);
				caixaCpfMascara.addFocusListener(cpfFocusListener);
				caixaEmail.addFocusListener(emailFocusListener);
				caixaSenha.addFocusListener(senhaFocusListener);
				
				//Selecao do Sexo
				caixaSexo1.addActionListener(masculinoListener);
				caixaSexo2.addActionListener(femininoListener);
				
				//Botao
				JButton confirmar = new JButton("CONFIRMAR");
				JButton cancelar = new JButton("CANCELAR");
				JPanel botoes = new JPanel();
				botoes.add(confirmar);
				botoes.add(cancelar);
				confirmar.addActionListener(confirmarListener);
				cancelar.addActionListener(cancelarListener);
			
				//Mostrar dados do usuario
				caixaId.setText(String.valueOf(gerente.getId()));
				caixaId.setEditable(false);
				caixaNome.setText(gerente.getNome());
				caixaCpfMascara.setText(gerente.getCpf());
				caixaEmail.setText(gerente.getEmail());
				caixaSenha.setText(gerente.getSenha());
				if(gerente.getSexo()=="Masculino")
					{caixaSexo1.setSelected(true);}
				if(gerente.getSexo()=="Feminino")
					{caixaSexo2.setSelected(true);}
								
				//Tela
				tela = new JFrame("Alterar dados Gerente");
				tela.setLayout(new FlowLayout());
				tela.pack();
				tela.setSize(500, 300);
				tela.setLocationRelativeTo(null);
				tela.setResizable(false);
				tela.setVisible(true);
				tela.add(textoAlterarDados);
				tela.add(idPainel);
				tela.add(nomePainel);
				tela.add(cpfPainel);
				tela.add(sexoPainel);
				tela.add(emailPainel);
				tela.add(senhaPainel);
				tela.add(botoes);
				
	}
	
	private static void telaEscolhaIdSolicitante()
	{	//Ler dados Salvos
		repSol=DataBase.lerBaseSolicitantes();
		
		//Actionlistener
		ActionListener confirmarListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
			  		{	if(caixaId.getText().matches("\\d{1,}")==true)		// o id deve ter pelo menos numeros
				  		{
				  			long id = Long.parseLong(caixaId.getText());
			  				try {
			  				Solicitante solicitante = repSol.buscar(id);
							tela.dispose();
							UIAlterarDados.telaAlterarDadosSolicitante(solicitante);
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
				  		{tela.dispose();}
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
		tela = new JFrame("Alterar Solicitante ");
		tela.setLayout(new FlowLayout());
		tela.pack();
		tela.setSize(250, 115);
		tela.setLocationRelativeTo(null);
		tela.setResizable(false);
		tela.setVisible(true);
		tela.add(painel);
		tela.add(botoes);
	}
	
	private static void telaEscolhaIdMotorista()
	{	//Ler dados Salvos
		repMot=DataBase.lerBaseMotoristas();
		
		//Actionlistener
				ActionListener confirmarListener = new ActionListener() {
					  public void actionPerformed(ActionEvent e) 
					  		{	if(caixaId.getText().matches("\\d{1,}")==true)	// o id deve ter pelo menos numeros
						  		{
						  			long id = Long.parseLong(caixaId.getText());
					  				try {
					  				Motorista motorista = repMot.buscar(id);
									tela.dispose();
									UIAlterarDados.telaAlterarDadosMotorista(motorista);
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
						  		{tela.dispose();}
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
				tela = new JFrame("Alterar Motorista ");
				tela.setLayout(new FlowLayout());
				tela.pack();
				tela.setSize(250, 115);
				tela.setLocationRelativeTo(null);
				tela.setResizable(false);
				tela.setVisible(true);
				tela.add(painel);
				tela.add(botoes);
	}
	
	public static void telaCaixaDeEscolhaAlterarDados(final Gerente gerente)//alterara dados do gerente e dos usuarios e motoristas que o gerente tem acesso
	{	
		//Actionlistener
		ActionListener confirmarListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
			  		{	if(selecao.getSelectedItem()=="Gerente")
				  		{	tela.dispose();
				  			UIAlterarDados.telaAlterarDadosGerente();
			  			}
			  			if(selecao.getSelectedItem()=="Motorista")
			  			{	tela.dispose();
			  				UIAlterarDados.telaEscolhaIdMotorista();
			  			}
			  			if(selecao.getSelectedItem()=="Solicitante")
			  			{	tela.dispose();
			  				UIAlterarDados.telaEscolhaIdSolicitante();
			  			}
				  	}
			};
		ActionListener cancelarListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{tela.dispose();}
				};
		// Caixa de texto 
		JLabel texto = new JLabel("Qual o usuario deseja alterar os dados:");
		JPanel painel = new JPanel();
		selecao = new JComboBox<String>();
		selecao.addItem("Gerente");
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
		tela = new JFrame("Alterar dados");
		tela.setLayout(new FlowLayout());
		tela.pack();
		tela.setSize(340, 120);
		tela.setLocationRelativeTo(null);
		tela.setResizable(false);
		tela.setVisible(true);
		tela.add(painel);
		tela.add(botoes);
		
	}
	

		
}
	

