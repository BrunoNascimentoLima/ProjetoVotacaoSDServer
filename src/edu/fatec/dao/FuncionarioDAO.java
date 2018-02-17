package edu.fatec.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import edu.fatec.entidades.Candidato;
import edu.fatec.entidades.Eleitor;
import edu.fatec.entidades.Funcionario;

public class FuncionarioDAO implements DAO{
	
	private static EntityManagerFactory emf = null;

	public FuncionarioDAO(){
		if(emf==null){
			emf=Persistence.createEntityManagerFactory("ELEICAO");
		}
	}

	@Override
	public boolean inserir(Object obj) throws SQLException {
		return false;
		
	}

	@Override
	public void remover(long id, Object obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizar(long id, Object obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object pesquisarPorNome(String nome) throws SQLException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Funcionario> qry = em.createQuery(
				"select funcionario from Funcionario funcionario where funcionario.usuario = :usuario",
				Funcionario.class);
		qry.setParameter("usuario",   nome );
		try{
		Funcionario resultado = qry.getSingleResult();
		em.close();
		return resultado;		
		}catch(Exception e){
			em.close();
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List pesquisarTodos() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List vencedor() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Eleitor pesquisarPorTitulo(String Titulo) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Candidato pesquisarPorNumero(int numero) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
