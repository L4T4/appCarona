package br.acme.users;
import java.io.Serializable;
import java.util.Date;			// classe para o atributo Date dataNascimento do tipo Date

import br.acme.exception.CPFInvalidoException;
import br.acme.exception.DataInvalidaException;
import br.acme.exception.EmailInvalidoException;
import br.acme.exception.NomeNullException;
import br.acme.exception.NomeVazioException;
import br.acme.exception.NumeroCelularInvalidoException;
import br.acme.exception.RepositorioException;
import br.acme.exception.SenhaInvalidaException;
import br.acme.exception.SexoInvalidoException;
import br.acme.location.Lugar;
import br.acme.location.Viagem;
import br.acme.storage.IRepositorio;




public class Solicitante extends Usuario	implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Serializable
	/**
	 * 
	 */
	
	
	private Date dataNascimento;
	private int numeroCelular;
	private Lugar[] lugares=new Lugar[500];	
	private long tempoInicial;					// sera o tempo inicial que comecara a contar a partir que solicitar a carona

	
	
	//construtor
	
	public Solicitante(String nome,String cpf,String sexo,int numeroCelular,Date dataNascimento,String email,String senha) throws NumeroCelularInvalidoException, NomeNullException, NomeVazioException, CPFInvalidoException, SexoInvalidoException, EmailInvalidoException, SenhaInvalidaException, DataInvalidaException 	// so sera definido no construtor os atributos essenciais   
	{	super.setNome(nome);
		super.setCpf(cpf);
		super.setSexo(sexo);
		super.setEmail(email);
		super.setSenha(senha);
		this.setNumeroCelular(numeroCelular);
		this.setDataNascimento(dataNascimento);
		tempoInicial=0;
	}
	
	//gets e sets
	
	public Date getDataNascimento()
	{return dataNascimento;}

	public void setDataNascimento(Date dataNascimento) throws DataInvalidaException 
	{	this.dataNascimento = dataNascimento;}

	public int getNumeroCelular() 
	{return numeroCelular;}

	public void setNumeroCelular(int numeroCelular) throws NumeroCelularInvalidoException 
	{	
		this.validarNumeroCelular(numeroCelular);
		this.numeroCelular = numeroCelular;
		
	}

	public Lugar[] getLugares() 
	{return lugares;}

	public void setLugares(Lugar[] lugares) 
	{this.lugares = lugares;}

		
	//o atributo tempo inicial sera exclusivo da classe, para usar exclusivamente em dois metodos(solicitar e cancelar carona) , nao necessitando de gets e sets
	//o get viagens tera a mesma funcao do metodo historico , por isso nao sera necessario colocar esse get
	//o set viagens nao sera necessario, pois o item dessa array ( item que apontara para o objeto nessa array ) sera criado atraves do metodo solicitar carona 

		
	//metodos
		
	public String toString()
	{
		return super.toString() +"\n" + "NUMERO DE CELULAR: " + this.numeroCelular + "\n" + "DATA DE NASCIMENTO: " + this.dataNascimento  ;
	}
	
	private void validarNumeroCelular(int numeroCelular) throws NumeroCelularInvalidoException
	{	String numero = Integer.toString(numeroCelular);	//transformaremos em uma string para analisarmos o numero
		if(numero.matches("[0-9]{8}")==false)		// veremos se tem entre 8 a 9 digitos de numeros entre 0 e 9
			throw new NumeroCelularInvalidoException();
		
	}	
	
	public Motorista[] motoristasDisponiveis(IRepositorio<Motorista> repositorioMotorista) throws RepositorioException
	{	Motorista[] motDisponivel= null;
		Motorista[] motoristas = repositorioMotorista.buscarTodos();
		int contador=0;	 
		int y=0;
		for(int x =0;x<motoristas.length;x++ )
			{if(motoristas[x].isDisponivel()==true)
				contador++;}
		motDisponivel= new Motorista[contador];
		for(int x =0;x<motoristas.length;x++ )
		{if(motoristas[x].isDisponivel()==true)
			{motDisponivel[y]=motoristas[x];
			 y++;}
			}
		return motDisponivel;
	}
		
	public void solicitarCarona(IRepositorio<Motorista> repositorioMotorista,IRepositorio<Viagem> repositorioViagem,Solicitante solicitante,long id,Lugar origem,Lugar destino,String formaDePagamento,double valorViagem) throws RepositorioException		//solicitara carona ao motorista
	{	tempoInicial = System.currentTimeMillis();		// comecara a contar o tempo em milissegundos
		Motorista motorista=repositorioMotorista.buscar(id);
		Viagem viagem = new Viagem(motorista, solicitante, origem, destino, formaDePagamento,valorViagem);
		repositorioViagem.adicionar(viagem);
		
	}
	
	public String cancelarCarona(long id,IRepositorio<Viagem> repViagem) throws RepositorioException		// cancelara a carona pedida ao motorista
	{	long tempofinal = (System.currentTimeMillis() - tempoInicial)/60000;			// medira o tempo final , pegando o tempo atual menos o tempo inicial em millissegundos, dividimos por 60000 para transforma-lo em minutos  
		repViagem.remover(id);
		if(tempofinal>=2)		// se o tempo de inicio ate o cancelamento for superior a 2 minutos ele sera informado para pagar uma taxa padrao
			return "Voce cancelou a carona apos 2 minutos e deve pagar uma taxa de R$0,50";
		else
			return "Voce cancelou a carona" ;		// caso contrario sera informado que cancelou a viagem e nao pagara nada
	}
	

	public Viagem[] historico(IRepositorio<Viagem> rep) throws RepositorioException					//imprimi na tela o historico de viagens realizadas pelo Solicitante
	{ 	Viagem[] viagens = rep.buscarTodos();
		Viagem[] viagensSol=null;
		Solicitante cliente = null;
		int contadorViagem=0;
		int cont=0;
		if(viagens!=null)
		{
			for(int x=0 ; x<viagens.length ;x++)
			{	cliente=viagens[x].getCliente();
				if(cliente.getId()== super.getId())
					contadorViagem++;
			}
			
			viagensSol= new Viagem[contadorViagem];
			for(int x=0 ; x<viagens.length ;x++)
			{	cliente=viagens[x].getCliente();
				if(cliente.getId()== super.getId())
					{viagensSol[cont]=viagens[x];
					 cont++;
					}
			}
		}
		return viagensSol;
	}

	

}
	