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
import br.acme.storage.IRepositorio;
import br.acme.storage.RepositorioSolicitante;
import br.acme.users.Solicitante;

public class TesteRepositorioSolicitante {

	public static void main(String[] args){
		try
		{Date data = null;
		Solicitante solicitante = null ;
		Solicitante solicitante1 = null;
		try {
			solicitante1 = new Solicitante("Pedro","999.999.999-99","masculino",99999999,data,"teste3@teste.com","teste111");
		} catch (NumeroCelularInvalidoException e) {
			e.printStackTrace();
		} catch (DataInvalidaException e) {
			e.printStackTrace();
		}
		Solicitante solicitante2 = null;
		try {
			solicitante2 = new Solicitante("joao","999.999.999-99","masculino",99999999,data,"teste3@teste.com","teste111");
		} catch (NumeroCelularInvalidoException e) {
			e.printStackTrace();
		} catch (DataInvalidaException e) {
			e.printStackTrace();
		}
		IRepositorio<Solicitante> repositorioSolicitante= new RepositorioSolicitante(500);
		repositorioSolicitante.adicionar(solicitante1);
		repositorioSolicitante.adicionar(solicitante2);
		repositorioSolicitante.alterar(0, null, null, null, null, null);
		solicitante =repositorioSolicitante.buscar(1);
		System.out.println(solicitante.getNome());
		repositorioSolicitante.remover(1);
		solicitante =repositorioSolicitante.buscar(1);
		repositorioSolicitante.buscarTodos();
		repositorioSolicitante.adicionar(solicitante2);
		}catch(RepositorioException e)
		{	
			e.printStackTrace();
			System.out.println("exception funcionando , Erro: " + e.getMessage());
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
