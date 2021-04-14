package br.acme.storage;

import java.io.Serializable;

import br.acme.exception.RepositorioException;
import br.acme.location.Viagem;



public class RepositorioViagem	implements IRepositorio<Viagem>,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Serializable
	/**
	 * 
	 */	
	
	private Viagem[] conjViagem; 
	private int quantidade;		// atributos apenas para a propria classe funcionar , nao tendo gets e sets
	private	long colocarID;
	
	
	// construtor 
	
	public RepositorioViagem(int tamanho)
	{
		conjViagem= new Viagem[tamanho];		//criara um array conjViagem de tamanho definido na criacao do objeto RepositorioViagem
		quantidade=0;							// comecara com quantidade 0 de objetos
		colocarID=1;							
	}
	
	
	//gets e sets
	//nao tera o get, pois o get sera o metodo buscarTodos
	//nao tera set, pois essa classe tera a funcao de encapsular a array viagem , sendo a sua funcao substituida pelo metodo adicionar, que adicionara apenas um item ao array  
	
	
	//metodos
	
	
	public Viagem buscar (long id)	throws RepositorioException{			// busca uma viagem pela id e retorna o mesmo no tipo Viagem 	
		if(conjViagem[0]!=null)
		{for(int y = 0 ; y < quantidade ; y++)		//buscara atraves de um loop for,onde incrementara o y que comecara do zero ate o tamanho da array 
			{	if(conjViagem[y].getId() == id )				
					{return conjViagem[y];	}				// se a id for igual a id dada no incremento do loop, vamos retornar o objeto Viagem 
			}
		  throw new RepositorioException("Viagem nao existe no Repositorio");		// caso depois de terminar o loop nao encontre a id, vamos mostrar uma exception
		}
		else
			throw new RepositorioException("RepositorioViagem Vazio");
	
	}
	
	
	public Viagem[] buscarTodos ()	throws RepositorioException{		// retorna a lista em formato de array apenas das Viagens existentes e nao o conjViagem todo,faremos isso para ficar mais pratico  
		if(conjViagem[0]!=null)
			{Viagem[] viagens = new Viagem[quantidade];
			 for(int x=0;x<quantidade;x++)
			 viagens[x]=conjViagem[x];
			 return viagens;
			}
		else
			throw new RepositorioException("RepositorioViagem Vazio");
	}
		
	

	public void adicionar (Viagem viagem) throws RepositorioException{			// metodo que ira adicionar um objeto do tipo viagem a array Viagem
		if(quantidade < this.conjViagem.length)		// se a quantidade de viagens atuais for menor que o tamanho da array toda, significa que ainda tem espaco na array
		{	for(int y=0 ; y < quantidade ; y++)		// faremos um loop ate a quantidade atual de viagens, para saber se existe alguma viagemigual na array
			{if(conjViagem[y].equals(viagem))		// se tiver lancaremos uma exception		
				{	throw new RepositorioException("Viagem ja cadastrada");
				}
			}
			this.conjViagem[quantidade]=viagem;	// entao adicionaremos na posicao da quantidade que eh a ultima posicao vazia disponivel
			quantidade++;		// incrementaremos a quantidade , pois teremos mais uma viagem na array
			viagem.setId(colocarID);		// definiremos o id da nova viagem de acordo com a posicao que ele atribuiu ao ser colocado na array
			System.out.println("Viagem adicionada com sucesso,"+" ID: " + colocarID);		// informaremos o seu novo id
			colocarID++;		// incrementaremos o id
			
		}
		else
			throw new RepositorioException("Espaco insuficiente");	// se nao tiver espaco mais , ira lancar uma exception informando
	}
	
	
	public void remover (long id)	throws RepositorioException	{		//ira remover um objeto da array atraves do id do objeto , faremos o inverso de adicionar
		int y;	//variavel y contador
		this.buscar(id);			// facilitaremos o codigo , usando o metodo busca ja existente nessa classe e caso nao encontre a viagem ou o Repositorio esteja vazio , o proprio buscar vai nos dar uma exception   
		for(y= 0 ; y < quantidade ; y++)				// se o metodo busca for diferente de null( ou seja o id existir) faremos o loop ate o tamanho da array
			{if(conjViagem[y].getId() == id )					// onde encontrara a posicao do objeto na array
			   	{conjViagem[y]=null;									// e atribuiremos aquela posicao como vazia
				 if(y!=quantidade-1)				// se o y nao for a ultima posicao valida, que tem uma viagem na array
					{for(int x = y ; x < quantidade ; x++)	// entao passaremos as viagens sucessoras , para uma posicao a menos
						{if(conjViagem[x]==null)				// deixando de ter o null intercalado e ficando apenas no final
							{	conjViagem[x]=conjViagem[x+1];
								conjViagem[x+1]=null;
							}
			           	}
					}
				 quantidade--;					// passaremos a ter uma quantidade de viagens a menos na array
				 System.out.println("Viagem removida");		// mensagem de operecao bem sucedida
				 break;		// sairemos do laco
			   	}
			}
	}
	
	public void alterar(long id ,String mudancaNome,String mudancaCpf,String mudancaSexo,String mudancaEmail,String mudancaSenha) throws RepositorioException
	{
		
	}
		
	

}
