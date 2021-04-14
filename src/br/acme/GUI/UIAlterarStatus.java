package br.acme.GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import br.acme.storage.DataBase;
import br.acme.storage.IRepositorio;
import br.acme.users.Motorista;

abstract public class UIAlterarStatus {
	
	private static JFrame janela;
		
	public static void telaAlterarStatus( final Motorista motorista,final IRepositorio<Motorista> repMot)
	{	
		//Action Listener
		
		ActionListener disponivelListener = new ActionListener() {
		  public void actionPerformed(ActionEvent e) 
	  		{motorista.setDisponivel(true);
	  		DataBase.salvarEstadoMotorista(repMot);
	  		janela.dispose();}
		};
		
		ActionListener indisponivelListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
		  		{motorista.setDisponivel(false);
		  		DataBase.salvarEstadoMotorista(repMot);
		  		janela.dispose();}
			};

		//Caixa de texto e botoes 
		JLabel texto = new JLabel("Aperte o botao de status desejado ");
		JButton disponivel = new JButton("Disponivel");
		JButton indisponivel = new JButton("Indisponivel");
		disponivel.addActionListener(disponivelListener);
		indisponivel.addActionListener(indisponivelListener);

		
		//Tela
		janela = new JFrame("Alterar Status");
		janela.pack();
		janela.setSize(240, 100);
		janela.setLocationRelativeTo(null);
		janela.setResizable(false);
		janela.setVisible(true);
		janela.setLayout(new FlowLayout());
		janela.add(texto);
		janela.add(disponivel);
		janela.add(indisponivel);		
		
		
	}

	
	
}
