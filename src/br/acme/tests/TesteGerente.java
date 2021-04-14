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
import br.acme.storage.RepositorioMotorista;
import br.acme.storage.RepositorioSolicitante;
import br.acme.users.Gerente;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;

public class TesteGerente {

	public static void main(String[] args){		// nao trataremos o exception para ver se ele esta funcionando
		try{
		Date data = null ;
		Solicitante solicitante1 = new Solicitante("antonio","999.999.999-99","masculino",99999999,data,"teste3@teste.com","teste111");
		Solicitante solicitante2 = new Solicitante("jose","999.999.999-99","masculino",99999999,data,"teste3@teste.com","teste111");
		Motorista motorista1= new Motorista("joao","111.111.111-11","masculino","teste2@teste.com","teste321");
		Motorista motorista2= new Motorista("pedro","111.111.111-11","masculino","teste2@teste.com","teste321");
		Gerente teste = new Gerente("Gerente","999.999.999-99","masculino","teste1@teste.com","teste123");
		IRepositorio<Motorista> repositorioMotorista= new RepositorioMotorista(500);	
		IRepositorio<Solicitante> repositorioSolicitante= new RepositorioSolicitante(500);
		System.out.println("Gerente :" + "\n" + teste.getId() + "\n" + teste.getNome() + "\n" + teste.getCpf() + "\n" + teste.getSexo() + "\n" + teste.getEmail() + "\n" + teste.getSenha() + "\n" );
		teste.setId(002);
		teste.setNome("Teste");
		teste.setCpf("111.111.111-11");
		teste.setEmail("gerente@teste.com");
		teste.setSenha("t1234asd");
		teste.setSexo("masc");
		teste.cadastrarMotorista(repositorioMotorista, motorista1);
		teste.cadastrarMotorista(repositorioMotorista, motorista2);
		repositorioSolicitante.adicionar(solicitante1);
		repositorioSolicitante.adicionar(solicitante2);
		teste.listarClientes(repositorioSolicitante);
		teste.listarMotoristas(repositorioMotorista);
		teste.removerMotorista(repositorioMotorista, 2);
		System.out.println("Gerente: \n" + teste.toString());
		teste.listarMotoristas(repositorioMotorista);
		}catch(RepositorioException e)
		{
			System.out.println(e.getMessage());
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
		} catch (NumeroCelularInvalidoException e) {
			e.printStackTrace();
		} catch (DataInvalidaException e) {
			e.printStackTrace();
		}
}

}
