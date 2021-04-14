package br.acme.users;

import br.acme.location.Viagem;
import br.acme.storage.IRepositorio;
import java.io.Serializable;

import br.acme.exception.CPFInvalidoException;
import br.acme.exception.EmailInvalidoException;
import br.acme.exception.NomeNullException;
import br.acme.exception.NomeVazioException;
import br.acme.exception.RepositorioException;
import br.acme.exception.SenhaInvalidaException;
import br.acme.exception.SexoInvalidoException;


public class Motorista extends Usuario	implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Serializable
	/**
	 * 
	 */
		
	
	private boolean disponivel;
	
	
	//os atributos solicitanteCaronaAtual origemCaronaAtual e destinoCaronaAtual sao utilizados para quando o motorista for inicializar o objeto viagem passar essas informacoes para o objeto  
	// quem passara essas informacoes para o motorista sera o solicitante
	
	
	//construtor
	
	public Motorista(String nome,String cpf,String sexo,String email,String senha) throws NomeNullException, NomeVazioException, SenhaInvalidaException, EmailInvalidoException, SexoInvalidoException, CPFInvalidoException  // os outros atributos serao definidos depois , apos inicializar o construtor      
	{	super.setNome(nome);
		super.setCpf(cpf);
		super.setSexo(sexo);
		super.setEmail(email);
		super.setSenha(senha);
	}
	
	// gets e sets
	
	public boolean isDisponivel() 
	{return disponivel;}

	public void setDisponivel(boolean disponivel) 
	{this.disponivel = disponivel;}
	
	
	//o get viagens tera a mesma funcao do metodo historico , por isso nao sera necessario colocar esse get
	//o set viagens nao sera necessario, pois o item dessa array ( item que apontara para o objeto nessa array ) sera criado quando o metodo responder pedido for respondido positivamente(true)

	// metodos
	
	public String toString()
	{
		return super.toString() +"\n"+ "DISPONIVEL: " + this.disponivel ;
	}
	
	public void responderPedido(IRepositorio<Viagem> repositorioViagem,long id,boolean pedido) throws RepositorioException			// responde o pedido do cliente
	{	if(pedido==false)	// se o motorista responder true, continuara com a viagem que o solicitante criou
			{
			repositorioViagem.remover(id); // caso o motorista nao queira aquela viagem, ira remover a viagem que o solicitante criou
			 }			
	}
	
	public Viagem[] historico(IRepositorio<Viagem> rep)	throws RepositorioException					//retornara o historico de viagens realizadas pelo Motorista
	{	Viagem[] viagens = rep.buscarTodos();
		Viagem[] viagensMot=null;
		Motorista motorista = null;
		int contadorViagem=0;
		int cont=0;
		if(viagens!=null)
		{
			for(int x=0 ; x<viagens.length ;x++)
			{	motorista=viagens[x].getMotorista();
				if(motorista.getId()== super.getId())
					contadorViagem++;
			}
			
			if(contadorViagem!=0) // se tiver alguma viagem no repositorio que seja do motorista(contadorViagem!=0) passaremos a viagem para uma array intermediaria e retornaremos ela
			{
				viagensMot= new Viagem[contadorViagem];
				for(int x=0 ; x<viagens.length ;x++)
				{	motorista=viagens[x].getMotorista();
					if(motorista.getId()== super.getId())
						{viagensMot[cont]=viagens[x];
						 cont++;
						}
				}
			}
		}
		return viagensMot;		// retornaremos a array
	}

}
