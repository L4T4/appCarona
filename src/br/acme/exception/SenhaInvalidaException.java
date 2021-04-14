package br.acme.exception;
import	java.lang.Exception;


public class SenhaInvalidaException	extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SenhaInvalidaException()
	{
		super("Senha Invalida");
	}
}
