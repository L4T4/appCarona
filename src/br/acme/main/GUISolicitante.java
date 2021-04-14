package br.acme.main;

import br.acme.GUI.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import br.acme.users.Solicitante;

abstract public class GUISolicitante {
	
	private static JFrame tela;
	
	public static void telaSolicitante(final Solicitante solicitante)
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
				  		{UIAlterarDados.telaAlterarDadosSolicitante(solicitante);}
				};
			ActionListener viagensListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{UIViagens.telaViagensSolicitante(solicitante);}
				};
			ActionListener caronaListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{UIPedirCarona.telaPedirCarona(solicitante);}
				};
			ActionListener excluirListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{UIExcluirConta.telaExcluirContaSolicitante(tela,solicitante);}
				};
			ActionListener logoutListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{	tela.dispose();
					  		GUILogin.login();}
				};
			ActionListener cancelarCaronaListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{UICancelarCarona.telaCancelarCarona(solicitante);}
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
			JMenuItem pedirCarona = new JMenuItem("Pedir Carona");
			JMenuItem listarViagens = new JMenuItem("Listar Viagens");
			JMenuItem cancelarCarona = new JMenuItem("CancelarCarona");
			arquivo.add(logout);
			arquivo.add(sair);
			editar.add(alterarDados);
			editar.add(excluir);
			editar.add(pedirCarona);
			editar.add(cancelarCarona);
			editar.add(listarViagens);
			sobre.add(ajuda);
			sobre.add(info);
			menu.add(arquivo);
			menu.add(editar);
			menu.add(sobre);
			sair.addActionListener(sairListener);
			logout.addActionListener(logoutListener);
			alterarDados.addActionListener(alterarListener);
			info.addActionListener(sobreListener);
			ajuda.addActionListener(ajudaListener);
			excluir.addActionListener(excluirListener);
			pedirCarona.addActionListener(caronaListener);
			listarViagens.addActionListener(viagensListener);
			cancelarCarona.addActionListener(cancelarCaronaListener);
			
			//TELA
			tela = new JFrame();
			JLabel boasVindas=new JLabel();
			JLabel caronaTexto=new JLabel();
			boasVindas.setText("                       Bem-Vindo, " +solicitante.getNome() );
			boasVindas.setForeground(Color.BLUE);
			boasVindas.setFont(new Font("Times New Roman", Font.BOLD, 40));
			caronaTexto.setText("       Carona");
			caronaTexto.setForeground(Color.ORANGE);
			caronaTexto.setFont(new Font("Times New Roman", Font.BOLD, 120));
			tela.setTitle("APP-Carona : Usuario");
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
