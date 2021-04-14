package br.acme.storage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import br.acme.location.Viagem;
import br.acme.users.Gerente;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;

public class DataBase {
	
	public static void salvarEstadoMotorista(IRepositorio<Motorista> repMot)
	{	FileOutputStream arqGrav = null;
		ObjectOutputStream objGrav=null;
		try{	//trataremos logo os possiveis erros com try e catch , ao inves de usar throws
		arqGrav = new FileOutputStream("RepositorioMotorista.txt");		// criara o arquivo
		objGrav = new ObjectOutputStream(arqGrav);					
		objGrav.writeObject(repMot);						// gravara o objeto no arquivo
		objGrav.flush();
		arqGrav.flush();
		
		}catch(IOException e)
		{	
			System.out.println("Erro :" + e.getMessage());			// caso de algum erro, mostrara uma mensagem de erro
		}
		finally{			// o finally ira ocorrer caso de erro ou nao , vamos usar para fechar o arquivo o que garante mais seguranca,
			try {			// pois se der erro e o arquivo estiver aberto teremos uma falha de seguranca,pois o arquivo continuara aberto
			if(objGrav!=null)	//caso o objGrav for inicializado com o new, ou seja for diferente de null	
				objGrav.close();	// fechara o objeto
			} catch (IOException e1) {		// pegaremos o erro do close , caso ocorra
				e1.printStackTrace();
			}		
			try {
			if(arqGrav!=null)	//caso o arqGrav for inicializado com o new, ou seja for diferente de null	
				arqGrav.close();	// fechara o arquivo
			} catch (IOException e) {	// pegaremos o erro do close , caso ocorra
				e.printStackTrace();
			}		
		}
		
	}
	
	public static void salvarEstadoSolicitante(IRepositorio<Solicitante> repSol)
	{	FileOutputStream arqGrav = null;
		ObjectOutputStream objGrav=null;
		try{		//trataremos logo os possiveis erros com try e catch , ao inves de usar throws
			arqGrav = new FileOutputStream("RepositorioSolicitante.txt");		// criara o arquivo
			objGrav = new ObjectOutputStream(arqGrav);
			objGrav.writeObject(repSol);				// gravara o objeto no arquivo
			objGrav.flush();
			arqGrav.flush();
			}catch(IOException e)
			{
				System.out.println("Erro :" + e.getMessage());				// caso de algum erro, mostrara uma mensagem de erro
			}
		finally{			// o finally ira ocorrer caso de erro ou nao , vamos usar para fechar o arquivo o que garante mais seguranca,
				try {
				if(objGrav!=null)		// pois se der erro e o arquivo estiver aberto teremos uma falha de seguranca,pois o arquivo continuara aberto
					objGrav.close();	// fechara o objeto
				} catch (IOException e1) {		// pegaremos o erro do close , caso ocorra
					e1.printStackTrace();
				}		
				try {
				if(arqGrav!=null)		
					arqGrav.close();	// fechara o arquivo
				} catch (IOException e) {	// pegaremos o erro do close , caso ocorra
					e.printStackTrace();
				}		
			}
		
	}
	
	public static void salvarEstadoViagem(IRepositorio<Viagem> repViagem)
	{	FileOutputStream arqGrav = null;
		ObjectOutputStream objGrav=null;
		try{	//trataremos logo os possiveis erros com try e catch , ao inves de usar throws
			arqGrav = new FileOutputStream("RepositorioViagem.txt");			// criara o arquivo
			objGrav = new ObjectOutputStream(arqGrav);
			objGrav.writeObject(repViagem);				// gravara o objeto no arquivo
			objGrav.flush();
			arqGrav.flush();
			}catch(IOException e)
			{
				System.out.println("Erro :" + e.getMessage());				// caso de algum erro, mostrara uma mensagem de erro
			}
		finally{			// o finally ira ocorrer caso de erro ou nao , vamos usar para fechar o arquivo o que garante mais seguranca,
				try {
				if(objGrav!=null)		// pois se der erro e o arquivo estiver aberto teremos uma falha de seguranca,pois o arquivo continuara aberto
					objGrav.close();	// fechara o objeto
				} catch (IOException e1) {		// pegaremos o erro do close , caso ocorra
					e1.printStackTrace();
				}		
				try {
				if(arqGrav!=null)		
					arqGrav.close();	// fechara o arquivo
				} catch (IOException e) {	// pegaremos o erro do close , caso ocorra
					e.printStackTrace();
				}		
			}
		
	}
	
	public static void salvarEstadoGerente(Gerente gerente)
	{	FileOutputStream arqGrav = null;
		ObjectOutputStream objGrav=null;
		try{	//trataremos logo os possiveis erros com try e catch , ao inves de usar throws
			arqGrav = new FileOutputStream("Gerente.txt");			// criara o arquivo
			objGrav = new ObjectOutputStream(arqGrav);
			objGrav.writeObject(gerente);					// gravara o objeto no arquivo
			objGrav.flush();
			arqGrav.flush();
			}catch(IOException e)
			{
				System.out.println("Erro :" + e.getMessage());				// caso de algum erro, mostrara uma mensagem de erro
			}
		finally{			// o finally ira ocorrer caso de erro ou nao , vamos usar para fechar o arquivo o que garante mais seguranca,
				try {
				if(objGrav!=null)		// pois se der erro e o arquivo estiver aberto teremos uma falha de seguranca,pois o arquivo continuara aberto
					objGrav.close();	// fechara o objeto
				} catch (IOException e1) {		// pegaremos o erro do close , caso ocorra
					e1.printStackTrace();
				}		
				try {
				if(arqGrav!=null)		
					arqGrav.close();	// fechara o arquivo
				} catch (IOException e) {	// pegaremos o erro do close , caso ocorra
					e.printStackTrace();
				}		
			}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public static IRepositorio<Solicitante> lerBaseSolicitantes()
	{	FileInputStream arqLer=null;
		ObjectInputStream objLer=null;
		IRepositorio<Solicitante> rep=null;				// variavel do tipo IRepositorioSolicitante , tendo a funcao de uma variavel intermediaria
		try{	//trataremos logo os possiveis erros com try e catch , ao inves de usar throws
		arqLer = new FileInputStream("RepositorioSolicitante.txt");			// ler o arquivo
		objLer = new ObjectInputStream(arqLer);						//ler o objeto
		rep=(IRepositorio<Solicitante>)objLer.readObject();								// passara o objeto para uma variavel intermediaria
		
		}catch(IOException e)						// caso de algum erro, mostrara uma mensagem de erro
		{
			System.out.println("Erro :" + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Erro :" + e.getMessage());
		}
		finally{			// o finally ira ocorrer caso de erro ou nao , vamos usar para fechar o arquivo o que garante mais seguranca,
				try {
				if(objLer!=null)		// pois se der erro e o arquivo estiver aberto teremos uma falha de seguranca,pois o arquivo continuara aberto
					objLer.close();	// fechara o objeto
				} catch (IOException e1) {		// pegaremos o erro do close , caso ocorra
					e1.printStackTrace();
				}		
				try {
				if(arqLer!=null)		
					arqLer.close();	// fechara o arquivo
				} catch (IOException e) {	// pegaremos o erro do close , caso ocorra
					e.printStackTrace();
				}		
			}
		return rep;			// retornaremos rep
	
	}
	
	@SuppressWarnings("unchecked")
	public static IRepositorio<Motorista> lerBaseMotoristas()
	{	IRepositorio<Motorista> rep=null;				// variavel do tipo IRepositorioMotorista , tendo a funcao de uma variavel intermediaria
		FileInputStream arqLer=null;
		ObjectInputStream objLer=null;
		try{	//trataremos logo os possiveis erros com try e catch , ao inves de usar throws
			arqLer = new FileInputStream("RepositorioMotorista.txt");		// ler o arquivo
			objLer = new ObjectInputStream(arqLer);						// ler o objeto
			rep=(IRepositorio<Motorista>)objLer.readObject();						// passara o objeto para uma variavel intermediaria

			}catch(IOException e)				// caso de algum erro, mostrara uma mensagem de erro
			{
				System.out.println("Erro :" + e.getMessage());
			} catch (ClassNotFoundException e) {
				System.out.println("Erro :" + e.getMessage());
			}
		finally{			// o finally ira ocorrer caso de erro ou nao , vamos usar para fechar o arquivo o que garante mais seguranca,
				try {
				if(objLer!=null)		// pois se der erro e o arquivo estiver aberto teremos uma falha de seguranca,pois o arquivo continuara aberto
					objLer.close();	// fechara o objeto
				} catch (IOException e1) {		// pegaremos o erro do close , caso ocorra
					e1.printStackTrace();
				}		
				try {
				if(arqLer!=null)		
					arqLer.close();	// fechara o arquivo
				} catch (IOException e) {	// pegaremos o erro do close , caso ocorra
					e.printStackTrace();
				}		
			}
		return rep;			// retornaremos rep
	}
	
	
	@SuppressWarnings("unchecked")
	public static IRepositorio<Viagem> lerBaseViagens()
	{	IRepositorio<Viagem> rep=null;			// variavel do tipo IRepositorioViagem , tendo a funcao de uma variavel intermediaria  
		FileInputStream arqLer = null;
		ObjectInputStream objLer = null;							
		try{	//trataremos logo os possiveis erros com try e catch , ao inves de usar throws 
			arqLer = new FileInputStream("RepositorioViagem.txt");				// ler o arquivo
			objLer = new ObjectInputStream(arqLer);							//ler o objeto
			rep=(IRepositorio<Viagem>)objLer.readObject();							// passara o objeto para uma variavel intermediaria
			
			}catch(IOException e)				// caso de algum erro, mostrara uma mensagem de erro
			{
				System.out.println("Erro :" + e.getMessage());
			} catch (ClassNotFoundException e) {
				System.out.println("Erro :" + e.getMessage());
			}
		finally{			// o finally ira ocorrer caso de erro ou nao , vamos usar para fechar o arquivo o que garante mais seguranca,
				try {
				if(objLer!=null)		// pois se der erro e o arquivo estiver aberto teremos uma falha de seguranca,pois o arquivo continuara aberto
					objLer.close();	// fechara o objeto
				} catch (IOException e1) {		// pegaremos o erro do close , caso ocorra
					e1.printStackTrace();
				}		
				try {
				if(arqLer!=null)		
					arqLer.close();	// fechara o arquivo
				} catch (IOException e) {	// pegaremos o erro do close , caso ocorra
					e.printStackTrace();
				}		
			}
		return rep;		// retornaremos rep
	}
	
	public static Gerente	lerBaseGerente()
	{	Gerente rep=null;							// variavel do tipo Gerente , tendo a funcao de uma variavel intermediaria
		FileInputStream arqLer = null;
		ObjectInputStream objLer = null;
		try{	//trataremos logo os possiveis erros com try e catch , ao inves de usar throws
			arqLer = new FileInputStream("Gerente.txt");			// ler o arquivo
			objLer = new ObjectInputStream(arqLer);				// ler o objeto
			rep=(Gerente)objLer.readObject();							// passara o objeto para uma variavel intermediaria
						
			}catch(IOException e)				// caso de algum erro, mostrara uma mensagem de erro
			{
				System.out.println("Erro :" + e.getMessage());
			} catch (ClassNotFoundException e) {
				System.out.println("Erro :" + e.getMessage());
			}
		finally{			// o finally ira ocorrer caso de erro ou nao , vamos usar para fechar o arquivo o que garante mais seguranca,
				try {
				if(objLer!=null)		// pois se der erro e o arquivo estiver aberto teremos uma falha de seguranca,pois o arquivo continuara aberto
					objLer.close();	// fechara o objeto
				} catch (IOException e1) {		// pegaremos o erro do close , caso ocorra
					e1.printStackTrace();
				}		
				try {
				if(arqLer!=null)		
					arqLer.close();	// fechara o arquivo
				} catch (IOException e) {	// pegaremos o erro do close , caso ocorra
					e.printStackTrace();
				}		
			}
		return rep;		// retornaremos rep
	}
		
}
