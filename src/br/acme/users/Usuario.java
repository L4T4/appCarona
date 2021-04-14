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


abstract public class Usuario	implements Serializable {	// abstract para nao ser instanciada, pois essa classe so servira para polimorfismo, pois nao criaremos o usuario de um modo generico , ja saberemos se ele vai ser gerente,solicitante,ou motorista   
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Serializable
	/**
	 * 
	 */
	
	
	private	long id;
	private	String cpf;
	private	String nome;
	private	String senha;
	private	String email;
	private	String sexo;

	
	
	
	//construtor
	
	public Usuario(){
		
	}
	
	// gets e sets
	
	public long getId() 
	{return id;}
	
	public void setId(long id) 
	{this.id = id;}
	
	public String getCpf() 
	{return cpf;}
	
	public void setCpf(String cpf) throws NomeNullException, NomeVazioException, CPFInvalidoException 
	{	this.validarCPF(cpf);
		this.cpf = cpf;
	}
	
	public String getNome()
	{return nome;}
	
	public void setNome(String nome) throws NomeNullException, NomeVazioException
	{	validarString(nome);
		this.nome = nome;
	}	
	
	public String getSenha()
	{return senha;}
	
	public void setSenha(String senha) throws NomeNullException, NomeVazioException, SenhaInvalidaException
	{	validarSenha(senha);
		this.senha = senha;
	}
	
	public String getEmail() 
	{return email;}
	
	public void setEmail(String email) throws EmailInvalidoException, NomeNullException, NomeVazioException
	{	this.validarEmail(email);
		this.email = email;
	}
	
	public String getSexo()
	{return sexo;}
	
	public void setSexo(String sexo) throws NomeNullException, NomeVazioException, SexoInvalidoException
	{	this.validarSexo(sexo);
		this.sexo = sexo;
	}
	
	
	//Repositorios sem necessidade de sets , pois quem fara essa funcao sera o cadastrarRepositorios
	
	// metodos
	
	public String toString()
	{
		return "ID: " + this.id +"\n"+ "NOME: " + this.nome +"\n"+ "SEXO: " + this.sexo  + "\n" +"CPF: " + this.cpf + "\n" + "EMAIL: " + this.email + "\n" + "SENHA: " + this.senha;
	}
	
	public boolean confirmarSenha(String senha)	// metodo para verificar se a senha informada eh a mesma do usuario
	{
		if(senha.equals(this.senha)==true)
			return true;
		else
			return false;
	}
	
	public void validarString(String string) throws NomeNullException, NomeVazioException
	{
		if(string==null)
			throw new NomeNullException();
		if(string.trim().isEmpty()==true)
			throw new NomeVazioException();
	}
	
	public void validarCPF(String cpf) throws NomeNullException, NomeVazioException, CPFInvalidoException
	{
		this.validarString(cpf);
		if(cpf.matches("[0-9]{11}|^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")==false) // se o cpf for diferente do modelo 11111111111 ou 111.111.111-11 lancara uma exception
			throw new CPFInvalidoException();
		
	}
	
	public void validarSenha(String senha) throws NomeNullException, NomeVazioException, SenhaInvalidaException
	{
		this.validarString(senha);
		if(senha.matches(".{7,}") == false)			// a senha precisa ter pelo menos 7 caracteres para ser valida , caso contrario lancara uma exception
			throw new SenhaInvalidaException();
		
	}
	
	public void validarSexo(String sexo) throws NomeNullException, NomeVazioException, SexoInvalidoException
	{
		this.validarString(sexo);
		if( sexo.matches( "m|f|M|F|masculino|feminino|Masculino|Feminino|MASCULINO|FEMININO|MASC|FEM|masc|fem" ) == false )
			throw new SexoInvalidoException();
	}
	
	public void validarEmail(String email) throws EmailInvalidoException, NomeNullException, NomeVazioException
	{	
		this.validarString(email);
		if(email.matches(".+@.+\\.\\w{2,3}\\.\\w{2,3}|.+@.+\\.\\w{2,3}")==false)	// email informado diferente do formato caracter@caracter.caracter{3 caracteres} ou caracter@caracter.caracter{3 caracteres}.caracter{2 caracteres} levantara uma exception    
			throw new EmailInvalidoException();
	}
	
	public static void emailLiberado(String emailVerifica,Gerente gerente,IRepositorio<Solicitante> repSol,IRepositorio<Motorista> repMot) throws EmailInvalidoException
	{	// verifica se tem outro email igual nos repositorios
		//caso encontre algum email igual, lançara uma exception 
		
		Solicitante[] solicitantes=null;			
		Motorista[] motoristas=null;
		
		if(gerente!=null)	// se gerente existir, for diferente de null, verifica se o email e igual
		{
			if(emailVerifica.equals(gerente.getEmail())==true)
				throw new EmailInvalidoException();
		}
		
		if(repSol!=null)			// se repSol existir, for diferente de null, verifica se o email e igual
		{	
			try {
				solicitantes = repSol.buscarTodos();			// buscara todos os solicitantes
			} catch (RepositorioException e1) {
				System.out.println(e1.getMessage());
			}
			
			if(solicitantes!=null)			// se o repositorio nao estiver vazio, percorrera o repositorio atras de algum email igual
			{
				for(int x=0;x<solicitantes.length;x++)
					{if(emailVerifica.equals(solicitantes[x].getEmail())==true)
						throw new EmailInvalidoException();
					}
			}
		}
		
		if(repMot!=null)	// se repMot existir, for diferente de null, verifica se o email e igual
		{	
			try {
				motoristas = repMot.buscarTodos();		// buscara todos os motoristas
			} catch (RepositorioException e) {
				System.out.println(e.getMessage());
			}
			
			if(motoristas!=null)			// se o repositorio nao estiver vazio, percorrera o repositorio atras de algum email igual
			{
				for(int x=0;x<motoristas.length;x++)
				{if(emailVerifica.equals(motoristas[x].getEmail())==true)
					throw new EmailInvalidoException();
				}
			}
		}
		
	}
	
}
