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
import br.acme.users.Solicitante;

public class TesteSolicitante {

	public static void main(String[] args) {
		try{
		Lugar[] lugares = null;
		Date data = null;
		Solicitante teste = null;
		try {
			teste = new Solicitante("teste","999.999.999-99","masculino",99999999,data,"teste3@teste.com","teste111");
		} catch (NumeroCelularInvalidoException e) {
			e.printStackTrace();
		} catch (DataInvalidaException e) {
			e.printStackTrace();
		}
		System.out.println("Solicitante :" + "\n" + teste.getId() + "\n" + teste.getNome() + "\n" + teste.getCpf() + "\n" + teste.getSexo() + "\n" + teste.getEmail() + "\n" + teste.getSenha() + "\n" + teste.getNumeroCelular() + "\n"+ teste.getDataNascimento() );
		teste.setId(001);
		teste.setNome("Solicitante");
		teste.setCpf("111.111.111-11");
		teste.setEmail("solicitante@teste.com");
		teste.setSenha("s1234asd");
		teste.setSexo("masc");
		teste.setDataNascimento(data);
		try {
			teste.setNumeroCelular(11111111);
		} catch (NumeroCelularInvalidoException e) {
			e.printStackTrace();
		}
		teste.setLugares(lugares);
		try {
			teste.cancelarCarona(0, null);
		} catch (RepositorioException e) {
			e.printStackTrace();
		}
		System.out.println("Solicitante:\n"+teste.toString());
		
		}catch (NomeNullException e) {
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
		} catch (DataInvalidaException e1) {
			e1.printStackTrace();
		}
	}
}
