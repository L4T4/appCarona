package br.acme.storage;

import br.acme.exception.CPFInvalidoException;
import br.acme.exception.EmailInvalidoException;
import br.acme.exception.NomeNullException;
import br.acme.exception.NomeVazioException;
import br.acme.exception.RepositorioException;
import br.acme.exception.SenhaInvalidaException;
import br.acme.exception.SexoInvalidoException;


public interface IRepositorio <Obj> {
	public Obj buscar(long id)throws RepositorioException	;
	public Obj[] buscarTodos()throws RepositorioException	;
	public void adicionar(Obj obj)throws RepositorioException;
	public void remover(long id)throws RepositorioException	;
	public void alterar(long id ,String mudancaNome,String mudancaCpf,String mudancaSexo,String mudancaEmail,String mudancaSenha)throws RepositorioException, NomeNullException, NomeVazioException, EmailInvalidoException, CPFInvalidoException, SexoInvalidoException, SenhaInvalidaException ;   

}
