package br.acme.tests;

import java.util.Date;

import br.acme.exception.CPFInvalidoException;
import br.acme.exception.DataInvalidaException;
import br.acme.exception.EmailInvalidoException;
import br.acme.exception.NomeNullException;
import br.acme.exception.NomeVazioException;
import br.acme.exception.NumeroCelularInvalidoException;
import br.acme.exception.SenhaInvalidaException;
import br.acme.exception.SexoInvalidoException;
import br.acme.location.Lugar;
import br.acme.location.Viagem;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;

public class TesteViagem {

	public static void main(String[] args) {
		try{
		Date data = null;
		Lugar origem= new Lugar("rua.teste","teste");
		Lugar destino= new Lugar("rua.teste","teste");
		Motorista motorista = new Motorista("teste","111.111.111-99","masculino","teste2@teste.com","teste321");
		Solicitante cliente = null;
		try {
			cliente = new Solicitante("teste","999.999.999-99","masculino",99999999,data,"teste3@teste.com","teste111");
		} catch (NumeroCelularInvalidoException e) {
			e.printStackTrace();
		} catch (DataInvalidaException e) {
			e.printStackTrace();
		}
		Viagem teste = new Viagem(motorista,cliente,origem,destino,null, 0);
		teste.setCliente(cliente);
		teste.setDestino(destino);
		teste.setFormaPagamento("debito");
		teste.setId(99999);
		teste.setMotorista(motorista);
		teste.setOrigem(origem);
		teste.setValorViagem(32.5);
		System.out.println(teste.getId() + "\n" + teste.getFormaPagamento() + "\n" + teste.getValorViagem() + "\n" + teste.getCliente() + "\n" + teste.getDestino() + "\n" + teste.getMotorista() + "\n" + teste.getOrigem() );
		System.out.println("\n\n\n"+teste.toString());
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
	}
	}

}
