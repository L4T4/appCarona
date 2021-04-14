package br.acme.main;

import br.acme.GUI.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import br.acme.users.Gerente;

abstract public class GUIGerente {
		
		private static JFrame tela;
	
		public static void telaGerente(final Gerente gerente)
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
			ActionListener cadastrarListener = new ActionListener() {
					  public void actionPerformed(ActionEvent e) 
					  		{UICadastro.telaCadastroUsuariosGerente();}
					};
			ActionListener alterarListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{UIAlterarDados.telaCaixaDeEscolhaAlterarDados(gerente);}
				};
			ActionListener logoutListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{tela.dispose();
				  		GUILogin.login();}
				};
			ActionListener removerListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{UIExcluirConta.telaRemoverContasGerente(gerente);}
				};
			ActionListener listarSolicitantesListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{UIListarUsuarios.telaListarSolicitantes(gerente);}
				};
			ActionListener listarMotoristasListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{UIListarUsuarios.telaListarMotoristas(gerente);}
				};
			ActionListener viagensListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{UIViagens.telaViagensGerente();}
				};
				
				
				
				
				
			// MENU
				
			JMenuBar menu = new JMenuBar();
			JMenu arquivo = new JMenu("Arquivo");
			JMenu editar = new JMenu("Editar");
			JMenu sobre = new JMenu("Sobre");
			JMenuItem ajuda = new JMenuItem("Ajuda");
			JMenuItem info = new JMenuItem("Info");
			JMenuItem cadastrarUsuario = new JMenuItem("Cadastrar Novo Usuario");
			JMenuItem alterarDados = new JMenuItem("Alterar Dados");
			JMenuItem logout = new JMenuItem("Logout");
			JMenuItem sair = new JMenuItem("Sair");
			JMenuItem removerUsuario = new JMenuItem("Remover Usuario");
			JMenuItem listarSolicitantes = new JMenuItem("Listar Solicitantes");
			JMenuItem listarMotoristas = new JMenuItem("Listar Motoristas");
			JMenuItem relatorioViagens = new JMenuItem("Relatorio Viagens");
			arquivo.add(cadastrarUsuario);
			arquivo.add(logout);
			arquivo.add(sair);
			editar.add(alterarDados);
			editar.add(removerUsuario);
			editar.add(listarSolicitantes);
			editar.add(listarMotoristas);
			editar.add(relatorioViagens);
			sobre.add(ajuda);
			sobre.add(info);
			menu.add(arquivo);
			menu.add(editar);
			menu.add(sobre);
			sair.addActionListener(sairListener);
			ajuda.addActionListener(ajudaListener);
			info.addActionListener(sobreListener);
			logout.addActionListener(logoutListener);
			listarMotoristas.addActionListener(listarMotoristasListener);
			listarSolicitantes.addActionListener(listarSolicitantesListener);
			removerUsuario.addActionListener(removerListener);
			alterarDados.addActionListener(alterarListener);
			cadastrarUsuario.addActionListener(cadastrarListener);
			relatorioViagens.addActionListener(viagensListener);
			
			
			//TELA
			tela = new JFrame();
			JLabel boasVindas=new JLabel();
			JLabel caronaTexto=new JLabel();
			boasVindas.setText("                       Bem-Vindo, " +gerente.getNome() );
			boasVindas.setForeground(Color.BLUE);
			boasVindas.setFont(new Font("Times New Roman", Font.BOLD, 40));
			caronaTexto.setText("       Carona");
			caronaTexto.setForeground(Color.ORANGE);
			caronaTexto.setFont(new Font("Times New Roman", Font.BOLD, 120));
			tela.setTitle("APP-Carona : Gerente");
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
