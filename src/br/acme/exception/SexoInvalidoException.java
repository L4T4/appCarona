package br.acme.exception;
import	java.lang.Exception;

public class SexoInvalidoException extends Exception{
	 /* 
	 */
	private static final long serialVersionUID = 1L;

	public SexoInvalidoException() 
	{
		super("Sexo informado no formato invalido");
	}

}
