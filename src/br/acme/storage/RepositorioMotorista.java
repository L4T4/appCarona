package br.acme.storage;

import java.io.Serializable;

import br.acme.exception.CPFInvalidoException;
import br.acme.exception.EmailInvalidoException;
import br.acme.exception.NomeNullException;
import br.acme.exception.NomeVazioException;
import br.acme.exception.RepositorioException;
import br.acme.exception.SenhaInvalidaException;
import br.acme.exception.SexoInvalidoException;
import br.acme.users.Motorista;


public class RepositorioMotorista	implements IRepositorio<Motorista> , Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Serializable
	/**
	 * 
	 */
	
	
	private Motorista[] conjMot;	
	private int quantidade;		// atributos apenas para a propria classe funcionar , nao tendo gets e sets
	private	long colocarID;
	// construtor
	
	public RepositorioMotorista(int tamanho)
	{
		conjMot= new Motorista[tamanho];		//criara um array conjMot de tamanho definido na criacao do objeto RepositorioMotorista
		quantidade=0;							// comecara com quantidade 0 de objetos
		colocarID=1;
	}
	
	//gets e sets
	// nao tera get,pois o get sera o metodo buscarTodos
	//nao tera set, pois essa classe tera a funcao de encapsular a array motorista , sendo a sua funcao substituida pelo metodo adicionar, que adicionara apenas um item ao array  
	


	//metodos
	
		
	public Motorista buscar (long id)	throws RepositorioException{			// busca um motorista pela id e retorna o mesmo no tipo Motorista 	
		if(conjMot[0]!=null)
		{for(int y = 0 ; y < quantidade ; y++)		//buscara atraves de um loop for,onde incrementara o y que comecara do zero ate o tamanho da array 
			{	if(conjMot[y].getId() == id )				
					{return conjMot[y];	}				// se a id for igual a id dada no incremento do loop, vamos retornar o objeto Motorista 
			}
		  throw new RepositorioException("Motorista nao existe no Repositorio");		// caso depois de terminar o loop nao encontre a id, vamos mostrar uma exception
		}
		else
			throw new RepositorioException("RepositorioMotorista Vazio");		// caso o repositorio esteja vazio,mostraremos uma exception
	
	}
	
	
	public Motorista[] buscarTodos ()	throws RepositorioException{		// retorna a lista em formato de array apenas dos Motoristas existentes e nao o conjMot todo,faremos isso para ficar mais pratico
		if(conjMot[0]!=null)
			{Motorista[] motoristas = new Motorista[quantidade];
			 for(int x=0;x<quantidade;x++)
			 motoristas[x]=conjMot[x];
			 return motoristas;
			}
		else
			throw new RepositorioException("RepositorioMotorista Vazio");
	}
	
	
	public void adicionar (Motorista motorista) throws RepositorioException{			// metodo que ira adicionar um objeto do tipo motorista a array Motorista
		if(quantidade < this.conjMot.length)		// se a quantidade de motoristas atuais for menor que o tamanho da array toda, significa que ainda tem espaco na array
		{	for(int y=0 ; y < quantidade ; y++)		// faremos um loop ate a quantidade atual de motoristas, para saber se existe algum motorista igual na array
			{if(conjMot[y].equals(motorista))		// se tiver lancaremos uma exception		
				{	throw new RepositorioException("Motorista ja cadastrado");
				}
			}
			this.conjMot[quantidade]=motorista;	// entao adicionaremos na posicao da quantidade que eh a ultima posicao vazia disponivel
			quantidade++;		// incrementaremos a quantidade , pois teremos mais um motorista na array
			motorista.setId(colocarID);		// definiremos o id do novo motorista de acordo com a posicao que ele atribuiu ao ser colocado na array
			System.out.println("Motorista adicionado com sucesso,"+" NOME: " + motorista.getNome() + " ID: " + colocarID);		// informaremos o seu novo id
			colocarID++;		// incrementaremos o id
		}
		else
			throw new RepositorioException("Espaco insuficiente");	// se nao tiver espaco mais , ira lancar uma exception informando
	}
	
	
	public void remover (long id)	throws RepositorioException	{		//ira remover um objeto da array atraves do id do objeto , faremos o inverso de adicionar
		int y;	//variavel y contador
		this.buscar(id);			// facilitaremos o codigo , usando o metodo busca ja existente nessa classe e caso nao encontre o motorista ou o Repositorio esteja vazio , o proprio buscar vai nos dar uma exception   
		for(y= 0 ; y < quantidade ; y++)				// se o metodo busca for diferente de null( ou seja o id existir) faremos o loop ate o tamanho da array
			{if(conjMot[y].getId() == id )					// onde encontrara a posicao do objeto na array
			   	{conjMot[y]=null;									// e atribuiremos aquela posicao como vazia
				 if(y!=quantidade-1)				// se o y nao for a ultima posicao valida, que tem um motorista, na array
					{for(int x = y ; x < quantidade ; x++)	// entao passaremos os motoristas sucessores , para uma posicao a menos
						{if(conjMot[x]==null)				// deixando de ter o null intercalado e ficando apenas no final
							{	conjMot[x]=conjMot[x+1];
								conjMot[x+1]=null;
							}
			           	}
					}
				 quantidade--;					// passaremos a ter uma quantidade de motorista a menos na array
				 System.out.println("Motorista removido");		// mensagem de operecao bem sucedida
				 break;		// sairemos do laco
			   	}
			}
	}
	
	
	public void alterar (long id ,String mudancaNome,String mudancaCpf,String mudancaSexo,String mudancaEmail,String mudancaSenha)	throws RepositorioException, NomeNullException, NomeVazioException, EmailInvalidoException, CPFInvalidoException, SexoInvalidoException, SenhaInvalidaException      
	{		
		Motorista motorista	=this.buscar(id);			// so nao mudara o id
		motorista.setNome(mudancaNome);		//  muda o nome
		motorista.setCpf(mudancaCpf);			//  muda o cpf
		motorista.setSexo(mudancaSexo);		// muda o sexo
		motorista.setEmail(mudancaEmail);		//  muda o email
		motorista.setSenha(mudancaSenha);		//  muda a senha
		
	    System.out.println("Alteracao realizada com sucesso");		// informamos uma mensagem de sucesso
			
		// caso nao encontre o id ou o repositorio esteja vazio,passaremos a exception do buscar
		
	}

}
