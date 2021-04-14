package br.acme.exception;
import	java.lang.Exception;


public class CPFInvalidoException	extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CPFInvalidoException()
	{
		super("CPF Invalido");
	}
}
