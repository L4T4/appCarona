package br.acme.GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import br.acme.exception.RepositorioException;
import br.acme.location.Viagem;
import br.acme.storage.DataBase;
import br.acme.storage.IRepositorio;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;

abstract public class UIViagens {
	
	private static JFrame janela ;
	
	public static void telaViagensSolicitante(final Solicitante solicitante) 
	{	
		try {	
				//Ler dados Salvos
				IRepositorio<Viagem> repViagem = DataBase.lerBaseViagens();
				Viagem[] viagens = solicitante.historico(repViagem);
			
			if(viagens!=null)		// se o usuario possuir viagens(viagens != null), entao mostrara elas.
			{
					
				//ActionListener
				ActionListener confirmarListener = new ActionListener() {
					  public void actionPerformed(ActionEvent e) 
					  		{janela.dispose();}
					};
				
				//Organizar viagens no formulario
				Object[][] obj = new Object[viagens.length][16];
				for(int x=0;x<viagens.length;x++)
				{
					obj[x][0]=viagens[x].getId();
					obj[x][1]=viagens[x].getCliente().getId();
					obj[x][2]=viagens[x].getCliente().getNome();
					obj[x][3]=viagens[x].getCliente().getCpf();
					obj[x][4]=viagens[x].getCliente().getEmail();
					obj[x][5]=viagens[x].getCliente().getNumeroCelular();
					obj[x][6]=viagens[x].getCliente().getSexo();
					obj[x][7]=viagens[x].getMotorista().getId();
					obj[x][8]=viagens[x].getMotorista().getNome();
					obj[x][9]=viagens[x].getMotorista().getCpf();
					obj[x][10]=viagens[x].getMotorista().getEmail();
					obj[x][11]=viagens[x].getMotorista().getSexo();
					obj[x][12]=viagens[x].getOrigem().getEndereco();
					obj[x][13]=viagens[x].getDestino().getEndereco();
					obj[x][14]=viagens[x].getFormaPagamento();
					obj[x][15]=viagens[x].getValorViagem();
				}
				
				//Tabela
				String[] colunas = new String[]{"ID VIAGEM:","ID CLIENTE:","NOME CLIENTE:","CPF CLIENTE:","EMAIL CLIENTE:","NUMERO CELULAR CLIENTE:","SEXO CLIENTE:","ID MOTORISTA:","NOME MOTORISTA:","CPF MOTORISTA:","EMAIL MOTORISTA:","SEXO MOTORISTA:","ENDEREÇO ORIGEM:","ENDEREÇO DESTINO:","FORMA DE PAGAMENTO:","VALOR VIAGEM:"};    
				JTable tabela = new JTable(obj,colunas);
				JScrollPane rolagem = new JScrollPane(tabela);
				tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				tabela.getColumnModel().getColumn(0).setPreferredWidth(75);
				tabela.getColumnModel().getColumn(1).setPreferredWidth(80);
				tabela.getColumnModel().getColumn(2).setPreferredWidth(120);
				tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
				tabela.getColumnModel().getColumn(4).setPreferredWidth(160);
				tabela.getColumnModel().getColumn(5).setPreferredWidth(130);
				tabela.getColumnModel().getColumn(6).setPreferredWidth(100);
				tabela.getColumnModel().getColumn(7).setPreferredWidth(100);
				tabela.getColumnModel().getColumn(8).setPreferredWidth(120);
				tabela.getColumnModel().getColumn(9).setPreferredWidth(110);
				tabela.getColumnModel().getColumn(10).setPreferredWidth(160);
				tabela.getColumnModel().getColumn(11).setPreferredWidth(115);
				tabela.getColumnModel().getColumn(12).setPreferredWidth(130);
				tabela.getColumnModel().getColumn(13).setPreferredWidth(135);
				tabela.getColumnModel().getColumn(14).setPreferredWidth(150);
				tabela.getColumnModel().getColumn(15).setPreferredWidth(100);
				
				//Tela
				janela = new JFrame();
				JButton confirmar = new JButton("OK");
				janela.setLayout(new FlowLayout());
				janela.setSize(480,520);
				janela.setLocationRelativeTo(null);
				janela.setResizable(false);
				janela.setVisible(true);
				janela.getContentPane().add(rolagem);
				janela.add(confirmar);
				confirmar.addActionListener(confirmarListener);
			}
			else	// caso o usuario nao possua viagens listadas, mostrara uma mensagem
			{
			JOptionPane.showMessageDialog(null,"Não possui viagens listadas no momento", "Mensagem",JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (RepositorioException e) {	//Trataremos os exceptions com mensagens de erro
		JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void telaViagensMotorista(final Motorista motorista)
	{
		try {
			//Ler dados Salvos
			IRepositorio<Viagem> repViagem = DataBase.lerBaseViagens();
			Viagem[] viagens = motorista.historico(repViagem);
		
		if(viagens!=null)	// se o usuario possuir viagens(viagens != null), entao mostrara elas.
		{
			
			//ActionListener
			ActionListener confirmarListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{janela.dispose();}
				};
				
				
			//Organizar viagens no formulario
	
			Object[][] obj = new Object[viagens.length][16];
			for(int x=0;x<viagens.length;x++)
			{
				obj[x][0]=viagens[x].getId();
				obj[x][1]=viagens[x].getCliente().getId();
				obj[x][2]=viagens[x].getCliente().getNome();
				obj[x][3]=viagens[x].getCliente().getCpf();
				obj[x][4]=viagens[x].getCliente().getEmail();
				obj[x][5]=viagens[x].getCliente().getNumeroCelular();
				obj[x][6]=viagens[x].getCliente().getSexo();
				obj[x][7]=viagens[x].getMotorista().getId();
				obj[x][8]=viagens[x].getMotorista().getNome();
				obj[x][9]=viagens[x].getMotorista().getCpf();
				obj[x][10]=viagens[x].getMotorista().getEmail();
				obj[x][11]=viagens[x].getMotorista().getSexo();
				obj[x][12]=viagens[x].getOrigem().getEndereco();
				obj[x][13]=viagens[x].getDestino().getEndereco();
				obj[x][14]=viagens[x].getFormaPagamento();
				obj[x][15]=viagens[x].getValorViagem();
			}
			
			//Tabela
			String[] colunas = new String[]{"ID VIAGEM:","ID CLIENTE:","NOME CLIENTE:","CPF CLIENTE:","EMAIL CLIENTE:","NUMERO CELULAR CLIENTE:","SEXO CLIENTE:","ID MOTORISTA:","NOME MOTORISTA:","CPF MOTORISTA:","EMAIL MOTORISTA:","SEXO MOTORISTA:","ENDEREÇO ORIGEM:","ENDEREÇO DESTINO:","FORMA DE PAGAMENTO:","VALOR VIAGEM:"};    
			JTable tabela = new JTable(obj,colunas);
			JScrollPane rolagem = new JScrollPane(tabela);
			tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tabela.getColumnModel().getColumn(0).setPreferredWidth(75);
			tabela.getColumnModel().getColumn(1).setPreferredWidth(80);
			tabela.getColumnModel().getColumn(2).setPreferredWidth(120);
			tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(4).setPreferredWidth(160);
			tabela.getColumnModel().getColumn(5).setPreferredWidth(130);
			tabela.getColumnModel().getColumn(6).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(7).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(8).setPreferredWidth(120);
			tabela.getColumnModel().getColumn(9).setPreferredWidth(110);
			tabela.getColumnModel().getColumn(10).setPreferredWidth(160);
			tabela.getColumnModel().getColumn(11).setPreferredWidth(115);
			tabela.getColumnModel().getColumn(12).setPreferredWidth(130);
			tabela.getColumnModel().getColumn(13).setPreferredWidth(135);
			tabela.getColumnModel().getColumn(14).setPreferredWidth(150);
			tabela.getColumnModel().getColumn(15).setPreferredWidth(100);
			
			
			//Tela
			janela = new JFrame();
			JButton confirmar = new JButton("OK");
			janela.setLayout(new FlowLayout());
			janela.setSize(480,520);
			janela.setLocationRelativeTo(null);
			janela.setResizable(false);
			janela.setVisible(true);
			janela.getContentPane().add(rolagem);
			janela.add(confirmar);
			confirmar.addActionListener(confirmarListener);
		}
		else	// caso o usuario nao possua viagens listadas, mostrara uma mensagem
			{
			JOptionPane.showMessageDialog(null,"Não possui viagens listadas no momento", "Mensagem",JOptionPane.INFORMATION_MESSAGE);
			}
		
		} catch (RepositorioException e) {	//Trataremos os exceptions com mensagens de erro
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void telaViagensGerente()
	{	try {
			//Ler dados Salvos
			IRepositorio<Viagem> rep = DataBase.lerBaseViagens();
			Viagem[] viagens=rep.buscarTodos();
			
			//ActionListener
			ActionListener confirmarListener = new ActionListener() {
				  public void actionPerformed(ActionEvent e) 
				  		{janela.dispose();}
				};
			
			//Organizar viagens no formulario
			
			Object[][] obj = new Object[viagens.length][16];
			for(int x=0;x<viagens.length;x++)
			{
				obj[x][0]=viagens[x].getId();
				obj[x][1]=viagens[x].getCliente().getId();
				obj[x][2]=viagens[x].getCliente().getNome();
				obj[x][3]=viagens[x].getCliente().getCpf();
				obj[x][4]=viagens[x].getCliente().getEmail();
				obj[x][5]=viagens[x].getCliente().getNumeroCelular();
				obj[x][6]=viagens[x].getCliente().getSexo();
				obj[x][7]=viagens[x].getMotorista().getId();
				obj[x][8]=viagens[x].getMotorista().getNome();
				obj[x][9]=viagens[x].getMotorista().getCpf();
				obj[x][10]=viagens[x].getMotorista().getEmail();
				obj[x][11]=viagens[x].getMotorista().getSexo();
				obj[x][12]=viagens[x].getOrigem().getEndereco();
				obj[x][13]=viagens[x].getDestino().getEndereco();
				obj[x][14]=viagens[x].getFormaPagamento();
				obj[x][15]=viagens[x].getValorViagem();
			}
			
			//Tabela
			String[] colunas = new String[]{"ID VIAGEM:","ID CLIENTE:","NOME CLIENTE:","CPF CLIENTE:","EMAIL CLIENTE:","NUMERO CELULAR CLIENTE:","SEXO CLIENTE:","ID MOTORISTA:","NOME MOTORISTA:","CPF MOTORISTA:","EMAIL MOTORISTA:","SEXO MOTORISTA:","ENDEREÇO ORIGEM:","ENDEREÇO DESTINO:","FORMA DE PAGAMENTO:","VALOR VIAGEM:"};    
			JTable tabela = new JTable(obj,colunas);
			JScrollPane rolagem = new JScrollPane(tabela);
			tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tabela.getColumnModel().getColumn(0).setPreferredWidth(75);
			tabela.getColumnModel().getColumn(1).setPreferredWidth(80);
			tabela.getColumnModel().getColumn(2).setPreferredWidth(120);
			tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(4).setPreferredWidth(160);
			tabela.getColumnModel().getColumn(5).setPreferredWidth(130);
			tabela.getColumnModel().getColumn(6).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(7).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(8).setPreferredWidth(120);
			tabela.getColumnModel().getColumn(9).setPreferredWidth(110);
			tabela.getColumnModel().getColumn(10).setPreferredWidth(160);
			tabela.getColumnModel().getColumn(11).setPreferredWidth(115);
			tabela.getColumnModel().getColumn(12).setPreferredWidth(130);
			tabela.getColumnModel().getColumn(13).setPreferredWidth(135);
			tabela.getColumnModel().getColumn(14).setPreferredWidth(150);
			tabela.getColumnModel().getColumn(15).setPreferredWidth(100);
			
			//Tela
			janela = new JFrame();
			JButton confirmar = new JButton("OK");
			janela.setLayout(new FlowLayout());
			janela.setSize(480,520);
			janela.setLocationRelativeTo(null);
			janela.setResizable(false);
			janela.setVisible(true);
			janela.getContentPane().add(rolagem);
			janela.add(confirmar);
			confirmar.addActionListener(confirmarListener);
			
		} catch (RepositorioException e) {	//Trataremos os exceptions com mensagens de erro
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
}
