package br.acme.location;

import java.io.Serializable;

import br.acme.users.Motorista;
import br.acme.users.Solicitante;

public class Viagem	implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Serializable
	/**
	 * 
	 */
	
	private	long id;
	private	Solicitante cliente;
	private	Motorista motorista;
	private	Lugar origem;
	private	Lugar destino;	
	private	double valorViagem;
	private	String formaPagamento;
	
	//construtor
	
	public Viagem(Motorista motorista,Solicitante cliente,Lugar origem,Lugar destino,String formaPagamento,double valor) // a classe passara apenas os objetos no parametro do construtor para inicializar, o resto sera informado apos o termino da viagem  
	{	
		setMotorista(motorista);				
		setCliente(cliente);
		setOrigem(origem);
		setDestino(destino);
		setFormaPagamento(formaPagamento);
		setValorViagem(valor);
		
	}
	
		//gets e sets
	
	public long getId() 
	{return id;}

	public void setId(long id)
	{this.id = id;}

	public Solicitante getCliente()
	{return cliente;}

	public void setCliente(Solicitante cliente)
	{this.cliente = cliente;}

	public Motorista getMotorista() 
	{return motorista;}

	public void setMotorista(Motorista motorista) 
	{this.motorista = motorista;}

	public Lugar getOrigem() 
	{return origem;}

	public void setOrigem(Lugar origem) 
	{this.origem = origem;}

	public Lugar getDestino() 
	{return destino;}

	public void setDestino(Lugar destino) 
	{this.destino = destino;}

	public double getValorViagem() 
	{return valorViagem;}

	public void setValorViagem(double valorViagem) 
	{this.valorViagem = valorViagem;}

	public String getFormaPagamento()
	{return formaPagamento;}

	public void setFormaPagamento(String formaPagamento)
	{this.formaPagamento = formaPagamento;}
	
	//metodos
	
	public String toString()
	{
		return "VIAGEM:\n"+"ID: " + this.id + "\n\n"+ "SOLICITANTE:\n" + cliente.toString() + "\n\n" +"MOTORISTA:\n" + motorista.toString() +"\n\n" + "ORIGEM:\n" + origem.toString() +"\n\n" + "DESTINO:\n" + destino.toString() +"\n\n" +  "VALOR VIAGEM: " + this.valorViagem +"\n" + "FORMA DE PAGAMENTO: " + this.formaPagamento;
	}
	
}



	