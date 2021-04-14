package br.acme.tests;

import java.util.Date;

import br.acme.exception.CPFInvalidoException;
import br.acme.exception.DataInvalidaException;
import br.acme.exception.EmailInvalidoException;
import br.acme.exception.NomeNullException;
import br.acme.exception.NomeVazioException;
import br.acme.exception.NumeroCelularInvalidoException;
import br.acme.exception.RepositorioException;
import br.acme.exception.SenhaInvalidaException;
import br.acme.exception.SexoInvalidoException;
import br.acme.location.Viagem;
import br.acme.storage.DataBase;
import br.acme.storage.IRepositorio;
import br.acme.storage.RepositorioMotorista;
import br.acme.storage.RepositorioSolicitante;
import br.acme.storage.RepositorioViagem;
import br.acme.users.Gerente;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;

public class TesteDataBase {

	public static void main(String[] args) {
		try{
		Date data = null;
		Solicitante cliente1 = new Solicitante("antonio","999.999.999-99","masculino",99999999,data,"teste3@teste.com","teste111");
		Solicitante cliente2 = new Solicitante("jose","999.999.999-99","masculino",99999999,data,"teste3@teste.com","teste111");
		Motorista motorista1= new Motorista("joao","111.111.111-11","masculino","teste2@teste.com","teste321");
		Motorista motorista2= new Motorista("pedro","111.111.111-11","masculino","teste2@teste.com","teste321");
		Gerente gerente1 = new Gerente("Gerente1","999.999.999-99","masculino","teste1@teste.com","teste123");
		Gerente gerente2 = new Gerente("Gerente2","111.111.111-11","masculino","teste2@teste.com","teste123");
		Gerente teste = null;
		Motorista[] motoristas = null;
		Solicitante[] solicitantes = null;
		IRepositorio<Solicitante> teste1=null;
		IRepositorio<Motorista> teste2=null;
		IRepositorio<Viagem> repositorioViagem=new RepositorioViagem(500);
		IRepositorio<Solicitante> repositorioSolicitante= new RepositorioSolicitante(500);
		IRepositorio<Motorista> repositorioMotorista= new RepositorioMotorista(500);	
		gerente1.cadastrarMotorista(repositorioMotorista, motorista1);
		gerente1.cadastrarMotorista(repositorioMotorista, motorista2);
		repositorioSolicitante.adicionar(cliente1);
		repositorioSolicitante.adicionar(cliente2);
		DataBase.salvarEstadoGerente(gerente1);
		DataBase.salvarEstadoMotorista(repositorioMotorista);
		DataBase.salvarEstadoSolicitante(repositorioSolicitante);
		DataBase.salvarEstadoViagem(repositorioViagem);
		teste=DataBase.lerBaseGerente();
		teste2=DataBase.lerBaseMotoristas();
		teste1=DataBase.lerBaseSolicitantes();
		DataBase.lerBaseViagens();
		DataBase.salvarEstadoGerente(gerente2);
		teste=DataBase.lerBaseGerente();
		System.out.println("\nGERENTE: \n"+teste);
		System.out.println(teste1);
		System.out.println(teste2);
		solicitantes=teste1.buscarTodos();
		motoristas=teste2.buscarTodos();
		System.out.println("MOTORISTAS: ");
		for(int x=0;x<motoristas.length;x++)
			System.out.println("\n"+motoristas[x]);
		System.out.println("SOLICITANTES: ");
		for(int x=0; x< solicitantes.length;x++)
			System.out.println("\n"+solicitantes[x]);
		
		}catch(RepositorioException e)
		{
			e.printStackTrace();
		} catch (NomeNullException e) {
			e.printStackTrace();
		} catch (NomeVazioException e) {
			e.printStackTrace();
		} catch (CPFInvalidoException e) {
			e.printStackTrace();
		} catch (EmailInvalidoException e) {
			e.printStackTrace();
		} catch (SexoInvalidoException e) {
			e.printStackTrace();
		} catch (SenhaInvalidaException e) {
			e.printStackTrace();
		} catch (NumeroCelularInvalidoException e) {
			e.printStackTrace();
		} catch (DataInvalidaException e) {
			e.printStackTrace();
		}
	}

}
