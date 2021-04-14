package br.acme.GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.acme.exception.RepositorioException;
import br.acme.location.Viagem;
import br.acme.storage.DataBase;
import br.acme.storage.IRepositorio;
import br.acme.users.Motorista;

abstract public class UIResponderCarona {

	private static JFrame janela;
	private static IRepositorio<Viagem> repViagem;
	private static Viagem[] viagens;
	
	public static void telaResponderCarona(final Motorista motorista)
	{	//Ler dados Salvos
		repViagem=DataBase.lerBaseViagens();
		
		try {
			viagens=motorista.historico(repViagem);
		} catch (RepositorioException e2) {
			JOptionPane.showMessageDialog(null,e2.getMessage(), "ERRO",JOptionPane.ERROR_MESSAGE);
		}
		
		if(viagens!=null) // se existir alguma viagem para o usuario(viagens!=null), entao mostrara o pedido
		{	
			//ActionListener
			ActionListener aceitarListener = new ActionListener() {
			  public void actionPerformed(ActionEvent e) 
		  		{janela.dispose();}		// se o motorista aceitar o pedido de carona,o sistema nao fara nada, pois o solicitante ja tinha criado o objeto viagem anteriormente
			};
			
			ActionListener rejeitarListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  {		try {
						  motorista.responderPedido(repViagem,viagens[viagens.length-1].getId() ,false);		// recusaremos a carona 
						  DataBase.salvarEstadoViagem(repViagem);
						  janela.dispose();
					  	} catch (RepositorioException e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage(), "ERRO",JOptionPane.ERROR_MESSAGE);
					  	}
				  }
				};

		
			
			//Botoes e texto
			JLabel texto = new JLabel("Aceitar o pedido de Carona de " + viagens[viagens.length-1].getCliente().getNome() + " a R$ "+viagens[viagens.length-1].getValorViagem() 
										+" para "+ viagens[viagens.length-1].getDestino().getEndereco()+ " partindo de "+viagens[viagens.length-1].getOrigem().getEndereco() +" ?" );
			JButton aceitar = new JButton("Aceitar");
			JButton rejeitar = new JButton("Rejeitar");
			JPanel botoes = new JPanel();
			botoes.add(aceitar);
			botoes.add(rejeitar);
			aceitar.addActionListener(aceitarListener);
			rejeitar.addActionListener(rejeitarListener);
			
			//Janela
			janela = new JFrame("Responder Pedido de Carona");
			janela.pack();
			janela.setSize(600, 100);
			janela.setLocationRelativeTo(null);
			janela.setResizable(false);
			janela.setVisible(true);
			janela.setLayout(new FlowLayout());
			janela.add(texto);
			janela.add(botoes);	
		}
		else
		{	// caso nao tenha nenhuma viagem para o usuario, mostrara uma mensagem
			JOptionPane.showMessageDialog(null,"Não existe caronas requisitadas no momento", "Mensagem",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
}
