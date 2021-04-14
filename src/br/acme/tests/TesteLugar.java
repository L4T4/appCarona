package br.acme.tests;

import br.acme.location.Lugar;

public class TesteLugar {

	public static void main(String[] args) {
		Lugar teste= new Lugar("rua.teste","teste");
		System.out.println("Lugar :" + "\n" + teste.getEndereco() + "\n" + teste.getIdentificadorLugar() + "\n");
		teste.setEndereco("rua benfica");
		teste.setIdentificadorLugar("faculdade");
		System.out.println("Lugar :" + "\n" + teste.getEndereco() + "\n" + teste.getIdentificadorLugar() + "\n");
		System.out.println("LUGAR:"+"\n" +teste.toString());
		
	}

}
