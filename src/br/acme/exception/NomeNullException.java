package br.acme.exception;
import	java.lang.Exception;

public class NomeNullException	extends	Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NomeNullException()
	{
		super("String Null Exception");
	}
}
