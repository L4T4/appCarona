package br.acme.exception;
import	java.lang.Exception;


public class NumeroCelularInvalidoException	extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NumeroCelularInvalidoException()
	{
		super("Numero Celular Invalido");
	}

}
