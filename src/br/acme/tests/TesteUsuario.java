package br.acme.tests;
import br.acme.exception.CPFInvalidoException;
import br.acme.exception.EmailInvalidoException;
import br.acme.exception.NomeNullException;
import br.acme.exception.NomeVazioException;
import br.acme.exception.SenhaInvalidaException;
import br.acme.exception.SexoInvalidoException;
import br.acme.users.*;

public class TesteUsuario {

	public static void main(String[] args) {
		try{
		Usuario gerente = new Gerente("Gerente","999.999.999-99","masculino","teste1@teste.com","teste123"); // polimorfismo
		Usuario motorista= new Motorista("pedro","111.111.111-99","masculino","teste2@teste.com","teste321");
		System.out.println(motorista.toString());
		System.out.println(gerente.toString());	
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
