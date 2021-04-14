package br.acme.location;

import java.io.Serializable;

public class Lugar	implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Serializable
	/**
	 * 
	 */
	
	private	String identificadorLugar;
	private	String endereco;

	//construtor
	public Lugar(String endereco,String identificadorLugar) 
	{
		this.endereco=endereco;
		this.identificadorLugar=identificadorLugar;
	
	}
	
	//gets e sets
	
	public String getIdentificadorLugar() 
	{return identificadorLugar;}

	public void setIdentificadorLugar(String identificadorLugar)
	{this.identificadorLugar = identificadorLugar;}

	public String getEndereco() 
	{return endereco;}

	public void setEndereco(String endereco) 
	{this.endereco = endereco;}
	
	//metodos
	
	public String toString()
	{
		return "IDENTIFICADOR LUGAR: " + this.identificadorLugar + "\n" + "ENDEREÇO: " + this.endereco;
	}

	
}



