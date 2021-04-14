package br.acme.GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.acme.exception.RepositorioException;
import br.acme.location.Viagem;
import br.acme.storage.DataBase;
import br.acme.storage.IRepositorio;
import br.acme.users.Solicitante;

abstract public class UICancelarCarona {
	
	private static JFrame janela ;
	private static JTextField caixaId;
	private static IRepositorio<Viagem> repViagem;
	
	public static void telaCancelarCarona(final Solicitante solicitante)
	{	
		//ActionListener
		
		ActionListener confirmarListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
		  		{try {
		  			repViagem=DataBase.lerBaseViagens();
					String carona=solicitante.cancelarCarona(Long.parseLong(caixaId.getText()), repViagem);					
					DataBase.salvarEstadoViagem(repViagem);
					JOptionPane.showMessageDialog(null, carona, "MENSAGEM",JOptionPane.INFORMATION_MESSAGE);
				} catch (RepositorioException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRO",JOptionPane.ERROR_MESSAGE);
				}catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Digite apenas numeros no Id", "ERRO",JOptionPane.ERROR_MESSAGE);
				}finally{janela.dispose();} }
				
			};
			
			ActionListener cancelarListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
			  		{janela.dispose();}
				};

			//Texto e Botoes
			JLabel texto = new JLabel("Voce deseja excluir a carona ? \n Digite o Id: ");
			JButton confirmar = new JButton("confirmar");
			JButton cancelar = new JButton("cancelar");
			JPanel botao = new JPanel();
			caixaId=new JTextField(10);
			botao.add(confirmar);
			botao.add(cancelar);
			confirmar.addActionListener(confirmarListener);
			cancelar.addActionListener(cancelarListener);
		
			//Tela
			janela = new JFrame("Excluir Carona");
			janela.pack();
			janela.setSize(290, 140);
			janela.setVisible(true);
			janela.setLayout(new FlowLayout());
			janela.add(texto);
			janela.add(caixaId);
			janela.add(botao);	
						
	}
	
	public static void telaCancelarCaronaAtual(final long id,final Solicitante solicitante)
	{	//ActionListener
		ActionListener simListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
		  		{try {
		  			repViagem=DataBase.lerBaseViagens();
					String carona =solicitante.cancelarCarona(id, repViagem);		
					DataBase.salvarEstadoViagem(repViagem);
					JOptionPane.showMessageDialog(null, carona, "MENSAGEM",JOptionPane.INFORMATION_MESSAGE);
				} catch (RepositorioException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRO",JOptionPane.ERROR_MESSAGE);
				}finally{janela.dispose();} }
			};
			
			ActionListener naoListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
			  		{janela.dispose();}
				};

			//Texto e Botoes
			JLabel texto = new JLabel("Voce deseja excluir a carona ?");
			JButton confirmar = new JButton("Sim");
			JButton cancelar = new JButton("Não");
			JPanel botao = new JPanel();
			botao.add(confirmar);
			botao.add(cancelar);
			confirmar.addActionListener(simListener);
			cancelar.addActionListener(naoListener);
			
			//Tela
			janela = new JFrame("Excluir Carona");
			janela.pack();
			janela.setSize(240, 100);
			janela.setLocationRelativeTo(null);
			janela.setResizable(false);
			janela.setVisible(true);
			janela.setLayout(new FlowLayout());
			janela.add(texto);
			janela.add(botao);	
			
	}
	
	
	
}
