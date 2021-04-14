package br.acme.tests;


import br.acme.exception.CPFInvalidoException;
import br.acme.exception.EmailInvalidoException;
import br.acme.exception.NomeNullException;
import br.acme.exception.NomeVazioException;
import br.acme.exception.SenhaInvalidaException;
import br.acme.exception.SexoInvalidoException;
import br.acme.users.Motorista;


public class TesteMotorista {

	public static void main(String[] args) {
		try{
			Motorista teste= new Motorista("teste","999.999.999-99","masculino","teste2@teste.com","asasdasd");
		System.out.println("Motorista :" + "\n" + teste.getId() + "\n" + teste.getNome() + "\n" + teste.getCpf() + "\n" + teste.getSexo() + "\n" + teste.getEmail() + "\n" + teste.getSenha() +"\n" );
		teste.setId(001);
		teste.setNome("Motorista");
		teste.setCpf("111.999.999-99");
		teste.setEmail("motorista@teste.com");
		teste.setSenha("123asdf");
		teste.setSexo("masc");
		teste.setDisponivel(true);
		System.out.println("Motorista :" + "\n" + teste.getId() + "\n" + teste.getNome() + "\n" + teste.getCpf() + "\n" + teste.getSexo() + "\n" + teste.getEmail() + "\n" + teste.getSenha() +"\n" + teste.isDisponivel() + "\n"  + "\n" );
		System.out.println("MOTORISTA: \n" + teste.toString());
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
