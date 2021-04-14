package br.acme.users;

import java.io.Serializable;

import br.acme.exception.CPFInvalidoException;
import br.acme.exception.EmailInvalidoException;
import br.acme.exception.NomeNullException;
import br.acme.exception.NomeVazioException;
import br.acme.exception.RepositorioException;
import br.acme.exception.SenhaInvalidaException;
import br.acme.exception.SexoInvalidoException;
import br.acme.storage.IRepositorio;


public class Gerente extends Usuario	implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Serializable
	/**
	 * 
	 */
		
	private static long indexarID=1;		// atributo estatico para indexar o id do gerente, a cada novo objeto criado esse atributo ira incrementar , sera um atributo da classe 
	
	//construtor
	
	public Gerente(String nome,String cpf,String sexo,String email,String senha) throws NomeNullException, NomeVazioException, CPFInvalidoException, EmailInvalidoException, SexoInvalidoException, SenhaInvalidaException  // sera inicializado junto com o construtor, os atributos essenciais    
	{	super.setId(indexarID);
		super.setNome(nome);
		super.setCpf(cpf);
		super.setSexo(sexo);
		super.setEmail(email);
		super.setSenha(senha);
		indexarID++;
	}
	
	
	
	// metodos
	

	public String toString()
	{
		return super.toString();
	}
	
	public void cadastrarMotorista(IRepositorio<Motorista> repositorioMotorista,Motorista motorista) throws RepositorioException		// o gerente cadastrara o motorista usando o metodo do repositorio motorista
	{	
			if(repositorioMotorista==null)
				System.out.println("Repositorio ainda nao foi criado,crie um novo repositorio");
			else 
				repositorioMotorista.adicionar(motorista);
		
	}
		
	public void removerMotorista(IRepositorio<Motorista> repositorioMotorista,long id) throws RepositorioException
	{
		repositorioMotorista.remover(id);				// o gerente ira remover o motorista usando o metodo do repositorio motorista

	}
	
	public Motorista[] listarMotoristas(IRepositorio<Motorista> repositorioMotorista) throws RepositorioException		// metodo que ira listar todos os motoristas,usando o System.out.print para imprimir na tela
	{	Motorista[] motoristas=repositorioMotorista.buscarTodos();						
		return motoristas;
	}
	
	public Solicitante[] listarClientes(IRepositorio<Solicitante> repositorioSolicitante) throws RepositorioException	// metodo que ira listar todos os solicitantes, usando o System.out.print para imprimir na tela
	{	Solicitante[] solicitantes =repositorioSolicitante.buscarTodos();
		return solicitantes;
	}
	
}

	