package br.acme.GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.*;

import br.acme.exception.RepositorioException;
import br.acme.storage.DataBase;
import br.acme.storage.IRepositorio;
import br.acme.users.Gerente;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;

abstract public class UIListarUsuarios {
	
	private static JFrame janela;

	
	public static void telaListarMotoristas(final Gerente gerente )
	{	try {
			//Ler dados Salvos
			IRepositorio<Motorista> repMot = DataBase.lerBaseMotoristas();
			Motorista[] motoristas = gerente.listarMotoristas(repMot);
			
			//ActionListener
			ActionListener confirmarListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{janela.dispose();}
				};
			
			//Organizar viagens no formulario
			Object[][] obj = new Object[motoristas.length][5];
			for(int x=0;x<motoristas.length;x++)
			{
				obj[x][0]=motoristas[x].getId();
				obj[x][1]=motoristas[x].getNome();
				obj[x][2]=motoristas[x].getEmail();
				obj[x][3]=motoristas[x].getCpf();
				obj[x][4]=motoristas[x].getSexo();
				
			}
			//Tabela
			String[] colunas = new String[]{"ID:","NOME:","EMAIL:","CPF:","SEXO:"};    
			JTable tabela = new JTable(obj,colunas);
			JScrollPane rolagem = new JScrollPane(tabela);
			tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tabela.getColumnModel().getColumn(0).setPreferredWidth(60);
			tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
			tabela.getColumnModel().getColumn(2).setPreferredWidth(150);
			tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(4).setPreferredWidth(70);
			
			//Tela
			JButton confirmar = new JButton("OK");
			janela = new JFrame();
			janela.setLayout(new FlowLayout());
			janela.setSize(480,520);
			janela.setResizable(false);
			janela.setLocationRelativeTo(null);
			janela.setVisible(true);
			janela.getContentPane().add(rolagem);
			janela.getContentPane().add(confirmar);
			confirmar.addActionListener(confirmarListener);
					
			} catch (RepositorioException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",JOptionPane.ERROR_MESSAGE);
			}
	}
	
	
	public static void telaListarSolicitantes(final Gerente gerente)
		{	try {
			//Ler dados salvos
			IRepositorio<Solicitante> repSol=DataBase.lerBaseSolicitantes();
			Solicitante[]solicitantes = gerente.listarClientes(repSol);
	
			//ActionListener
			ActionListener confirmarListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{janela.dispose();}
				};
			
			//Organizar viagens no formulario
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Object[][] obj = new Object[solicitantes.length][7];
			for(int x=0;x<solicitantes.length;x++)
			{
				obj[x][0]=solicitantes[x].getId();
				obj[x][1]=solicitantes[x].getNome();
				obj[x][2]=solicitantes[x].getEmail();
				obj[x][3]=solicitantes[x].getNumeroCelular();
				obj[x][4]=formato.format(solicitantes[x].getDataNascimento()).toString();
				obj[x][5]=solicitantes[x].getCpf();
				obj[x][6]=solicitantes[x].getSexo();
				
			}
			
			//Tabela
			String[] colunas = new String[]{"ID:","NOME:","EMAIL:","NUMERO CELULAR:","DATA DE NASCIMENTO:","CPF:","SEXO:"};    
			JTable tabela = new JTable(obj,colunas);
			JScrollPane rolagem = new JScrollPane(tabela);
			tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tabela.getColumnModel().getColumn(0).setPreferredWidth(60);
			tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
			tabela.getColumnModel().getColumn(2).setPreferredWidth(150);
			tabela.getColumnModel().getColumn(3).setPreferredWidth(150);
			tabela.getColumnModel().getColumn(4).setPreferredWidth(150);
			tabela.getColumnModel().getColumn(5).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(6).setPreferredWidth(70);
			
			
			//Tela
			JButton confirmar = new JButton("OK");
			janela = new JFrame();
			janela.setLayout(new FlowLayout());
			janela.setSize(480,520);
			janela.setResizable(false);
			janela.setLocationRelativeTo(null);
			janela.setVisible(true);
			janela.getContentPane().add(rolagem);
			janela.getContentPane().add(confirmar);
			confirmar.addActionListener(confirmarListener);
		
			
			} catch (RepositorioException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",JOptionPane.ERROR_MESSAGE);
			}
		
	}
	
}
