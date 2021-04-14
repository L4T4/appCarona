package br.acme.exception;
import	java.lang.Exception;

public class NomeVazioException	extends	Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NomeVazioException()
	{
		super("Caixa Vazia");
	}
}
