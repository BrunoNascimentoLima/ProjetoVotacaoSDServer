package edu.fatec.dao;

import java.sql.SQLException;
import java.util.List;

import edu.fatec.entidades.Candidato;
import edu.fatec.entidades.Eleitor;

public interface DAO {
	public boolean inserir( Object obj ) throws SQLException;
	public void remover( long id,Object obj ) throws SQLException;
	public void atualizar(long id, Object obj) throws SQLException ;
	public Object pesquisarPorNome(String nome) throws SQLException;
	public List pesquisarTodos() throws SQLException;
	public List vencedor() throws SQLException;
	public Eleitor pesquisarPorTitulo(String Titulo)throws SQLException;
	public Candidato pesquisarPorNumero(int numero)throws SQLException;
}
