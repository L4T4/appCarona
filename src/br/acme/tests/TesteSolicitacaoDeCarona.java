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
import br.acme.location.Lugar;
import br.acme.location.Viagem;
import br.acme.storage.IRepositorio;
import br.acme.storage.RepositorioMotorista;
import br.acme.storage.RepositorioSolicitante;
import br.acme.storage.RepositorioViagem;
import br.acme.users.*;


public class TesteSolicitacaoDeCarona {

	public static void main(String[] args){		// nao trataremos o exception para ver se ele esta funcionando
		try{
		Date data = null;
		Lugar origem= new Lugar("rua.teste","teste");
		Lugar destino= new Lugar("rua.teste","teste");
		Lugar origem2= new Lugar("rua.benfica","teste3");
		Lugar destino2= new Lugar("av.caxanga","teste4");
		Solicitante cliente1 = null;
		try {
			cliente1 = new Solicitante("antonio","999.999.999-99","masculino",99999999,data,"teste3@teste.com","teste111");
		} catch (NumeroCelularInvalidoException e) {
			e.printStackTrace();
		} catch (DataInvalidaException e) {
			e.printStackTrace();
		}
		Solicitante cliente2 = null;
		try {
			cliente2 = new Solicitante("jose","999.999.999-99","masculino",99999999,data,"teste3@teste.com","teste111");
		} catch (NumeroCelularInvalidoException e) {
			e.printStackTrace();
		} catch (DataInvalidaException e) {
			e.printStackTrace();
		}
		Motorista motorista1= new Motorista("joao","111.111.111-11","masculino","teste2@teste.com","teste321");
		Motorista motorista2= new Motorista("pedro","111.111.111-11","masculino","teste2@teste.com","teste321");
		Gerente gerente = new Gerente("Gerente","999.999.999-99","masculino","teste1@teste.com","teste123");
		IRepositorio<Motorista> repositorioMotorista= new RepositorioMotorista(500);	
		IRepositorio<Solicitante> repositorioSolicitante= new RepositorioSolicitante(500);
		IRepositorio<Viagem> repositorioViagem=new RepositorioViagem(500);
		repositorioSolicitante.adicionar(cliente1);
		repositorioSolicitante.adicionar(cliente2);
		gerente.cadastrarMotorista(repositorioMotorista, motorista1);
		gerente.cadastrarMotorista(repositorioMotorista, motorista2);
		motorista1.setDisponivel(true);
		cliente1.solicitarCarona(repositorioMotorista, repositorioViagem, cliente1,1,origem,destino,"cartao", 0);
		motorista1.responderPedido(repositorioViagem, 1, true);
		motorista1.historico(repositorioViagem);
		System.out.println("\n\n\nViagens cliente:");
		cliente1.historico(repositorioViagem);
		cliente1.solicitarCarona(repositorioMotorista, repositorioViagem, cliente1,1,origem2,destino2,"dinheiro", 0);
		motorista1.responderPedido(repositorioViagem, 2, true);
		cliente1.historico(repositorioViagem);
		cliente1.solicitarCarona(repositorioMotorista, repositorioViagem, cliente1,1,origem2,destino2,"dinheiro", 0);
		cliente1.cancelarCarona(1, repositorioViagem);
		}catch(RepositorioException e)
		{
			e.printStackTrace();
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
