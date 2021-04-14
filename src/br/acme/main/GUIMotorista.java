package br.acme.main;

import br.acme.GUI.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import br.acme.storage.IRepositorio;
import br.acme.users.Motorista;

abstract public class GUIMotorista {
	
	private static JFrame tela;
	
	public static void telaMotorista(final Motorista motorista,final IRepositorio<Motorista> repMot)       
	{
	//ActionListener
		ActionListener sairListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
			  		{System.exit(0);}
			};
		ActionListener ajudaListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
			  		{UIAjuda.telaAjuda();}
			};
		ActionListener sobreListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{UISobre.telaSobre();}
				};
		ActionListener alterarListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
			  		{UIAlterarDados.telaAlterarDadosMotorista(motorista);}
			};
		ActionListener excluirListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
			  		{UIExcluirConta.telaExcluirContaMotorista(tela,motorista);}
			};
		ActionListener logoutListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
			  		{tela.dispose();
			  		GUILogin.login();}
			};
		ActionListener statusListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
			  		{UIAlterarStatus.telaAlterarStatus(motorista,repMot);}
			};
		ActionListener viagensListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
			  		{UIViagens.telaViagensMotorista(motorista);}
			};
		ActionListener responderPedidoListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
			  		{UIResponderCarona.telaResponderCarona(motorista);}
			};
		
		
		
		
		
	// MENU
		
	JMenuBar menu = new JMenuBar();
	JMenu arquivo = new JMenu("Arquivo");
	JMenu editar = new JMenu("Editar");
	JMenu sobre = new JMenu("Sobre");
	JMenuItem ajuda = new JMenuItem("Ajuda");
	JMenuItem info = new JMenuItem("Info");
	JMenuItem alterarDados = new JMenuItem("Alterar Dados");
	JMenuItem excluir = new JMenuItem("Excluir Conta");
	JMenuItem logout = new JMenuItem("Logout");
	JMenuItem sair = new JMenuItem("Sair");
	JMenuItem alterarStatus = new JMenuItem("Alterar Status");
	JMenuItem listarViagens = new JMenuItem("Listar Viagens");
	JMenuItem responderPedido = new JMenuItem("Responder Pedido de Carona");
	arquivo.add(logout);
	arquivo.add(sair);
	editar.add(alterarDados);
	editar.add(excluir);
	editar.add(alterarStatus);
	editar.add(responderPedido);
	editar.add(listarViagens);
	sobre.add(ajuda);
	sobre.add(info);
	menu.add(arquivo);
	menu.add(editar);
	menu.add(sobre);
	ajuda.addActionListener(ajudaListener);
	info.addActionListener(sobreListener);
	alterarDados.addActionListener(alterarListener);
	excluir.addActionListener(excluirListener);
	logout.addActionListener(logoutListener);
	sair.addActionListener(sairListener);
	alterarStatus.addActionListener(statusListener);
	listarViagens.addActionListener(viagensListener);
	responderPedido.addActionListener(responderPedidoListener);
	
	//TELA
	tela = new JFrame();
	JLabel boasVindas=new JLabel();
	JLabel caronaTexto=new JLabel();
	boasVindas.setText("                       Bem-Vindo, " +motorista.getNome() );
	boasVindas.setForeground(Color.BLUE);
	boasVindas.setFont(new Font("Times New Roman", Font.BOLD, 40));
	caronaTexto.setText("       Carona");
	caronaTexto.setForeground(Color.ORANGE);
	caronaTexto.setFont(new Font("Times New Roman", Font.BOLD, 120));
	tela.setTitle("APP-Carona : Motorista");
	tela.setLayout(new BorderLayout());
	tela.setSize(800, 600);
	tela.setLocationRelativeTo(null);
	tela.setJMenuBar(menu);
	tela.add(boasVindas,BorderLayout.CENTER);
	tela.add(caronaTexto,BorderLayout.SOUTH);
	tela.setVisible(true);
	tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
	
}
