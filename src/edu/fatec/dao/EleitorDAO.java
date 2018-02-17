package edu.fatec.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import edu.fatec.entidades.Candidato;
import edu.fatec.entidades.Eleitor;

public class EleitorDAO implements DAO {
	
	private static EntityManagerFactory emf = null;

	public EleitorDAO(){
		if(emf==null){
			emf=Persistence.createEntityManagerFactory("ELEICAO");
		}
	}

	
	
	@Override
	public boolean inserir(Object obj) throws SQLException {
		 Eleitor eleitor;
		   if(obj instanceof Eleitor){
			   eleitor=(Eleitor)obj;
			   EntityManager em = emf.createEntityManager();
				em.getTransaction().begin();
				em.persist(eleitor);
				em.getTransaction().commit();
				em.close();
				return true;
		   }
		   else
			   return false;
		
	}

	@Override
	public void remover(long id, Object obj) throws SQLException {
		Eleitor eleitor;
		   if(obj instanceof Eleitor){
			   eleitor=(Eleitor)obj;
			   EntityManager em = emf.createEntityManager();
			    eleitor = em.getReference(Eleitor.class, id);
				em.getTransaction().begin();
				em.remove( eleitor );
				em.getTransaction().commit();
				em.close();   
		   }
		
	}

	@Override
	public void atualizar(long id, Object obj) throws SQLException {
		Eleitor eleitor;
		if(obj instanceof Eleitor){
		  eleitor=(Eleitor)obj;
		EntityManager em = emf.createEntityManager();
	    Eleitor eleitorAntigo = em.getReference(Eleitor.class, id);
		em.getTransaction().begin(); 
		eleitorAntigo.setNome(eleitor.getNome()); 
		eleitorAntigo.setTitulo(eleitor.getTitulo());
		eleitorAntigo.setConfirmacao(eleitor.getConfirmacao());
		em.getTransaction().commit();
		em.close();
		}	
	}

	@Override
	public Object pesquisarPorNome(String nome) throws SQLException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Eleitor> qry = em.createQuery(
				"select eleitor from Eleitor eleitor where eleitor.nome like :nome",
				Eleitor.class);
		qry.setParameter("nome", "%" + nome + "%");
		Eleitor resultado = qry.getSingleResult();
		em.close();
		return resultado;
	}

	@Override
	public List pesquisarTodos() throws SQLException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Eleitor> qry = em.createQuery(
				"select eleitor from Eleitor eleitor ",
				Eleitor.class);
		List<Eleitor> resultado = qry.getResultList();
		em.close();
		return resultado;
	}

	@Override
	public List vencedor() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Eleitor pesquisarPorTitulo(String Titulo) throws SQLException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Eleitor> qry = em.createQuery(
				"select eleitor from Eleitor eleitor where eleitor.titulo = :Titulo",
				Eleitor.class);
		qry.setParameter("Titulo",  Titulo );
	try{
		Eleitor resultado = qry.getSingleResult();
		em.close();
		return resultado;
		
	}catch(Exception e){
		em.close();
		e.printStackTrace();
		return null;
	}
	}



	@Override
	public Candidato pesquisarPorNumero(int numero) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
