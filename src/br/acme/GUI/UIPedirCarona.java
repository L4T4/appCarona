package br.acme.GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.acme.exception.RepositorioException;
import br.acme.location.Lugar;
import br.acme.location.Viagem;
import br.acme.storage.DataBase;
import br.acme.storage.IRepositorio;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;

abstract public class UIPedirCarona {
	
	private static JFrame janela ;
	private static JTextField caixaID;
	private static JTextField caixaOrigemEndereco;
	private static JTextField caixaOrigemIdentificador;
	private static JTextField caixaDestinoEndereco;
	private static JTextField caixaDestinoIdentificador;
	private static JTextField caixaValorViagem;
	private static JComboBox<String> caixaFormaDePagamento;
	private static IRepositorio<Motorista> repositorioMotorista;
	private static IRepositorio<Viagem> repositorioViagem;
	private static Motorista[] motDisponivel;
	
	public static void telaPedirCarona(final Solicitante solicitante)
	{	//Ler dados Salvos
		repositorioMotorista=DataBase.lerBaseMotoristas();
		repositorioViagem=DataBase.lerBaseViagens();
		
		motDisponivel = null;
		try {
			motDisponivel=solicitante.motoristasDisponiveis(repositorioMotorista);
		} catch (RepositorioException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), "ERRO",JOptionPane.ERROR_MESSAGE);
		}
		
		if(motDisponivel != null)
		{
		
			
		//Actionlistener
				ActionListener confirmarListener = new ActionListener() {
					  public void actionPerformed(ActionEvent e) 
					  		{	if(caixaID.getText().matches("\\d{1,}")==true)		//o id deve ter pelo menos numeros
					  			{
						  			long id = Long.parseLong(caixaID.getText());
						  			boolean idConfere=false;
						  			for(int x=0;x<motDisponivel.length;x++)
						  			{	if(motDisponivel[x].getId()==id)		// veremos se o id que o usuario digitou sera de um motorista disponivel
											{idConfere=true;
											 break;
											}
						  			}
						  			
						  			if(idConfere==true)		//se o id for de um motorista disponivel, entao solicitara a carona para ele
						  			{
							  			Lugar origem =new Lugar(caixaOrigemEndereco.getText(),caixaOrigemIdentificador.getText());
							  			Lugar destino = new Lugar(caixaDestinoEndereco.getText(),caixaDestinoEndereco.getText());
							  			String formaDePagamento = (String)caixaFormaDePagamento.getSelectedItem();
							  			
							  			try {
							  				double valorViagem=Double.parseDouble(caixaValorViagem.getText());
											solicitante.solicitarCarona(repositorioMotorista,repositorioViagem,solicitante, id, origem, destino, formaDePagamento,valorViagem);
											DataBase.salvarEstadoViagem(repositorioViagem);
											JOptionPane.showMessageDialog(null, "Carona requisitada com sucesso.\n Aguardando motorista confirmar Carona... ", "MENSAGEM",JOptionPane.INFORMATION_MESSAGE);
											UICancelarCarona.telaCancelarCaronaAtual(id, solicitante);
										} catch (RepositorioException e1) {
											JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRO",JOptionPane.ERROR_MESSAGE);
										} catch(NumberFormatException e1){
											JOptionPane.showMessageDialog(null, "Digite apenas numeros no valor da viagem", "ERRO",JOptionPane.ERROR_MESSAGE);
										}
								  		finally{janela.dispose();}
						  			
						  			}
						  			else
						  				JOptionPane.showMessageDialog(null, "Digite algum ID de Motorista disponivel", "ERRO",JOptionPane.ERROR_MESSAGE);
					  		}
				  			else
				  				JOptionPane.showMessageDialog(null, "Digite apenas numeros no ID", "ERRO",JOptionPane.ERROR_MESSAGE);
				  		}
					};
				ActionListener cancelarListener = new ActionListener() {
						  public void actionPerformed(ActionEvent e) 
						  		{janela.dispose();}
						};
		
		
		//Organizar motoristas no formulario
		
		Object[][] mot  = new Object[motDisponivel.length][5];
			for(int x=0;x<motDisponivel.length;x++)
			{
				mot[x][0]=motDisponivel[x].getId();
				mot[x][1]=motDisponivel[x].getNome();
				mot[x][2]=motDisponivel[x].getCpf();
				mot[x][3]=motDisponivel[x].getEmail();
				mot[x][4]=motDisponivel[x].getSexo();
			}
		
						
		//Formulario
						
		String[] colunas = new String[]{"ID:","NOME:","CPF:","EMAIL:","SEXO:"};
		JTable tabela = new JTable(mot,colunas);
		JScrollPane rolagem = new JScrollPane(tabela);
		JPanel botao = new JPanel();
		JPanel painel = new JPanel();
		JPanel painelOrigemEndereco = new JPanel();
		JPanel painelOrigemIdentificador = new JPanel();
		JPanel painelDestinoEndereco = new JPanel();
		JPanel painelDestinoIdentificador = new JPanel();
		JPanel painelFormaDePagamento = new JPanel();
		JPanel painelValorViagem = new JPanel();
		JButton confirmar = new JButton("Confirmar");
		JButton cancelar = new JButton("Cancelar");
		JLabel textoId = new JLabel("Digite o ID do motorista para pedir Carona :");
		JLabel textoOrigemEndereco = new JLabel("Origem Endereço:");
		JLabel textoOrigemIdentificador = new JLabel("Origem Indentificador:");
		JLabel textoDestinoEndereco = new JLabel("Destino Endereço:");
		JLabel textoDestinoIdentificador = new JLabel("Destino Indentificador:");
		JLabel textoFormaDePagamento = new JLabel("Forma de Pagamento :");
		JLabel textoValorViagem = new JLabel("Valor sugerido para a viagem :");
		caixaID= new JTextField(20);
		caixaOrigemEndereco= new JTextField(20) ;
		caixaOrigemIdentificador= new JTextField(20);
		caixaDestinoEndereco= new JTextField(20);
		caixaDestinoIdentificador= new JTextField(20);
		caixaValorViagem= new JTextField(20);
		caixaFormaDePagamento= new JComboBox<String>();
		caixaFormaDePagamento.addItem("Dinheiro");
		caixaFormaDePagamento.addItem("Cartao Credito");
		caixaFormaDePagamento.addItem("Cartao Debito");
		caixaFormaDePagamento.setEditable(false);
		painel.add(textoId);
		painel.add(caixaID);
		painelOrigemIdentificador.add(textoOrigemIdentificador);
		painelOrigemIdentificador.add(caixaOrigemIdentificador);
		painelOrigemEndereco.add(textoOrigemEndereco);
		painelOrigemEndereco.add(caixaOrigemEndereco);
		painelDestinoIdentificador.add(textoDestinoIdentificador);
		painelDestinoIdentificador.add(caixaDestinoIdentificador);
		painelDestinoEndereco.add(textoDestinoEndereco);
		painelDestinoEndereco.add(caixaDestinoEndereco);
		painelFormaDePagamento.add(textoFormaDePagamento);
		painelFormaDePagamento.add(caixaFormaDePagamento);
		painelValorViagem.add(textoValorViagem);
		painelValorViagem.add(caixaValorViagem);
		botao.add(confirmar);
		botao.add(cancelar);
		confirmar.addActionListener(confirmarListener);
		cancelar.addActionListener(cancelarListener);
		
		//Janela
		janela = new JFrame();
		janela.setLayout(new FlowLayout());
		janela.setSize(500,770);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		janela.getContentPane().add(rolagem);
		janela.add(painelOrigemIdentificador);
		janela.add(painelOrigemEndereco);
		janela.add(painelDestinoIdentificador);
		janela.add(painelDestinoEndereco);
		janela.add(painelFormaDePagamento);
		janela.add(painelValorViagem);
		janela.add(painel);
		janela.add(botao);
		
		
		}
		
	}
	
	
	
	
}
