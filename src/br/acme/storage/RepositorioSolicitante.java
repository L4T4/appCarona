package br.acme.storage;

import java.io.Serializable;

import br.acme.exception.CPFInvalidoException;
import br.acme.exception.EmailInvalidoException;
import br.acme.exception.NomeNullException;
import br.acme.exception.NomeVazioException;
import br.acme.exception.RepositorioException;
import br.acme.exception.SenhaInvalidaException;
import br.acme.exception.SexoInvalidoException;
import br.acme.users.Solicitante;

public class RepositorioSolicitante	implements IRepositorio<Solicitante>,Serializable{
			
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		//Serializable
		/**
		 * 
		 */
	
		
		private Solicitante [] conjSol ; 
		private int quantidade;		// atributos apenas para a propria classe funcionar , nao tendo gets e sets
		private	long colocarID;
		
		//construtor
		
		public RepositorioSolicitante(int tamanho)
		{
			conjSol= new Solicitante[tamanho];			//criara um array conjSol de tamanho definido na criacao do objeto RepositorioSolicitante
			quantidade=0;								// comecara com quantidade 0 de objetos
			colocarID=1;
		}
		
		
		//gets e sets
		//nao tera o get, pois o get sera o metodo buscarTodos
		//nao tera set, pois essa classe tera a funcao de encapsular a array solicitante, sendo a sua funcao substituida pelo metodo adicionar, que adicionara apenas um item ao array  

		
		//metodos 
		
				
		public Solicitante buscar (long id)	throws RepositorioException{			// busca um solicitante pela id e retorna o mesmo no tipo Solicitante 	
			if(conjSol[0]!=null)
			{for(int y = 0 ; y < quantidade ; y++)		//buscara atraves de um loop for,onde incrementara o y que comecara do zero ate o tamanho da array 
				{	if(conjSol[y].getId() == id )				
						{return conjSol[y];	}				// se a id for igual a id dada no incremento do loop, vamos retornar o objeto Solicitante 
				}
			  throw new RepositorioException("Solicitante nao existe no Repositorio");		// caso depois de terminar o loop nao encontre a id, vamos mostrar uma exception
			}
			else
				throw new RepositorioException("RepositorioSolicitante Vazio");
		
		}
		
		
		public Solicitante[] buscarTodos ()	throws RepositorioException{		// retorna a lista em formato de array apenas dos Solicitantes existentes e nao o conjSol todo,faremos isso para ficar mais pratico   
			if(conjSol[0]!=null)
				{Solicitante[] solicitantes = new Solicitante[quantidade];
				 for(int x=0;x<quantidade;x++)
				 solicitantes[x]=conjSol[x];
				 return solicitantes;
				}
			else
				throw new RepositorioException("RepositorioSolicitante Vazio");
		}
		
		

		public void adicionar (Solicitante solicitante) throws RepositorioException{			// metodo que ira adicionar um objeto do tipo solicitante a array Solicitante
			if(quantidade < this.conjSol.length)		// se a quantidade de solicitantes atuais for menor que o tamanho da array toda, significa que ainda tem espaco na array
			{	for(int y=0 ; y < quantidade ; y++)		// faremos um loop ate a quantidade atual de solicitantes, para saber se existe algum solicitante igual na array
				{if(conjSol[y].equals(solicitante))		// se tiver lancaremos uma exception		
					{	throw new RepositorioException("Solicitante ja cadastrado");
					}
				}
				this.conjSol[quantidade]=solicitante;	// entao adicionaremos na posicao da quantidade que eh a ultima posicao vazia disponivel
				quantidade++;		// incrementaremos a quantidade , pois teremos mais um solicitante na array
				solicitante.setId(colocarID);		// definiremos o id do novo solicitante de acordo com a posicao que ele atribuiu ao ser colocado na array
				System.out.println("Solicitante adicionado com sucesso,"+" NOME: " + solicitante.getNome() + " ID: " + colocarID);		// informaremos o seu novo id
				colocarID++;		// incrementaremos o id
			}
			else
				throw new RepositorioException("Espaco insuficiente");	// se nao tiver espaco mais , ira lancar uma exception informando
		}
		
		public void remover (long id)	throws RepositorioException	{		//ira remover um objeto da array atraves do id do objeto , faremos o inverso de adicionar
			int y;	//variavel y contador
			this.buscar(id);			// facilitaremos o codigo , usando o metodo busca ja existente nessa classe e caso nao encontre o solicitante ou o Repositorio esteja vazio , o proprio buscar vai nos dar uma exception   
			for(y= 0 ; y < quantidade ; y++)				// se o metodo busca for diferente de null( ou seja o id existir) faremos o loop ate o tamanho da array
				{if(conjSol[y].getId() == id )					// onde encontrara a posicao do objeto na array
				   	{conjSol[y]=null;									// e atribuiremos aquela posicao como vazia
					 if(y!=quantidade-1)				// se o y nao for a ultima posicao valida, que tem um solicitante, na array
						{for(int x = y ; x < quantidade ; x++)	// entao passaremos os solicitantes sucessores , para uma posicao a menos
							{if(conjSol[x]==null)				// deixando de ter o null intercalado e ficando apenas no final
								{	conjSol[x]=conjSol[x+1];
									conjSol[x+1]=null;
								}
				           	}
						}
					 quantidade--;					// passaremos a ter uma quantidade de solicitante a menos na array
					 System.out.println("Solicitante removido");		// mensagem de operecao bem sucedida
					 break;		// sairemos do laco
				   	}
				}
		}
		
		
		
		public void alterar (long id ,String mudancaNome,String mudancaCpf,String mudancaSexo,String mudancaEmail,String mudancaSenha)	throws RepositorioException, NomeNullException, NomeVazioException, CPFInvalidoException, SexoInvalidoException, EmailInvalidoException, SenhaInvalidaException            
		{		
			Solicitante solicitante	=this.buscar(id); //so nao mudara o id
			solicitante.setNome(mudancaNome);		// muda o nome
			solicitante.setCpf(mudancaCpf);			//  muda o cpf
			solicitante.setSexo(mudancaSexo);		// muda o sexo
			solicitante.setEmail(mudancaEmail);		//  muda o email
			solicitante.setSenha(mudancaSenha);		// muda a senha
			
			System.out.println("Alteracao realizada com sucesso");		// informamos uma mensagem de sucesso
			
			//caso nao encontre o id passaremos uma exception atraves do metodo buscar
					
		}
		
}
