package br.acme.tests;

import br.acme.exception.CPFInvalidoException;
import br.acme.exception.EmailInvalidoException;
import br.acme.exception.NomeNullException;
import br.acme.exception.NomeVazioException;
import br.acme.exception.RepositorioException;
import br.acme.exception.SenhaInvalidaException;
import br.acme.exception.SexoInvalidoException;
import br.acme.storage.IRepositorio;
import br.acme.storage.RepositorioMotorista;
import br.acme.users.Motorista;

public class TesteRepositorioMotorista {

	public static void main(String[] args){		
		try{
		Motorista motorista = null;
		Motorista mot1= new Motorista("Joao","111.111.111-11","masculino","teste2@teste.com","teste321");
		Motorista mot2= new Motorista("Pedro","111.111.111-11","masculino","teste2@teste.com","teste321");
		Motorista mot3= new Motorista("Jose","111.111.111-11","masculino","teste2@teste.com","teste321");
		IRepositorio<Motorista> repositorioMotorista= new RepositorioMotorista(500);	
		repositorioMotorista.adicionar(mot1);
		repositorioMotorista.adicionar(mot2);
		repositorioMotorista.adicionar(mot3);
		motorista = repositorioMotorista.buscar(2);
		System.out.println(motorista.getNome());
		repositorioMotorista.remover(002);
		repositorioMotorista.buscar(002);
		repositorioMotorista.alterar(0, null, null, null, null, null);
		motorista=repositorioMotorista.buscar(001);
		System.out.println(motorista.getNome());
		repositorioMotorista.buscarTodos();
		repositorioMotorista.adicionar(mot1);
		}catch(RepositorioException e)
		{	
			e.printStackTrace();
			System.out.println("Exception funcionando , Erro : " + e.getMessage());
		}
		catch (NomeNullException e) {
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
		}
	}
}
