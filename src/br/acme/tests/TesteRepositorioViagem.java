package br.acme.tests;

import br.acme.exception.RepositorioException;
import br.acme.location.Lugar;
import br.acme.location.Viagem;
import br.acme.storage.IRepositorio;
import br.acme.storage.RepositorioViagem;
import br.acme.users.Motorista;
import br.acme.users.Solicitante;

public class TesteRepositorioViagem {

	public static void main(String[] args){		
		try{
		Lugar origem = null ;
		Motorista motorista = null;
		Lugar destino = null;
		Solicitante cliente = null;
		Viagem viagem = null;
		Viagem viagem1 = new Viagem(motorista,cliente,origem,destino,null, 0);
		Viagem viagem2 = new Viagem(motorista,cliente,origem,destino,null, 0);
		IRepositorio<Viagem> repositorioViagem=new RepositorioViagem(500);
		viagem1.setCliente(cliente);
		viagem1.setDestino(destino);
		viagem1.setFormaPagamento("debito");
		viagem1.setMotorista(motorista);
		viagem1.setOrigem(origem);
		viagem1.setValorViagem(32.5);
		viagem1.setId(001);
		viagem2.setCliente(cliente);
		viagem2.setDestino(destino);
		viagem2.setFormaPagamento("cartao");
		viagem2.setMotorista(motorista);
		viagem2.setOrigem(origem);
		viagem2.setValorViagem(32.5);
		viagem2.setId(10);
		repositorioViagem.adicionar(viagem1);
		repositorioViagem.adicionar(viagem2);
		viagem = repositorioViagem.buscar(1);
		System.out.println(viagem.getFormaPagamento());
		repositorioViagem.remover(1);
		repositorioViagem.buscar(1);
		repositorioViagem.buscarTodos();
		repositorioViagem.adicionar(viagem2);
		}catch(RepositorioException e)
		{	
			e.printStackTrace();
			System.out.println("exception funcionando , erro : " + e.getMessage());
		}
	}
}
