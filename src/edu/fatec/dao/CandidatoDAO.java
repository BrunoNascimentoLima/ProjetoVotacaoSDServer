package edu.fatec.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import edu.fatec.entidades.Candidato;
import edu.fatec.entidades.Eleitor;


public class CandidatoDAO implements DAO{
	
	private static EntityManagerFactory emf = null;

	public CandidatoDAO(){
		if(emf==null){
			emf=Persistence.createEntityManagerFactory("ELEICAO");
		}
	}

	@Override
	public boolean inserir(Object obj) throws SQLException {
	   Candidato candidato;
	   if(obj instanceof Candidato){
		   candidato=(Candidato)obj;
		   EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(candidato);
			em.getTransaction().commit();
			em.close();
			return true;
	   }
	   else
		    return false;
	}

	@Override
	public void remover(long id, Object obj) throws SQLException {
		Candidato candidato;
		   if(obj instanceof Candidato){
			   candidato=(Candidato)obj;
			   EntityManager em = emf.createEntityManager();
			    candidato = em.getReference(Candidato.class, id);
				em.getTransaction().begin();
				em.remove( candidato );
				em.getTransaction().commit();
				em.close();   
		   }
	}

	@Override
	public void atualizar(long id, Object obj) throws SQLException {
		Candidato candidato;
		if(obj instanceof Candidato){
		  candidato=(Candidato)obj;
		EntityManager em = emf.createEntityManager();
	    Candidato candidatoAntigo = em.getReference(Candidato.class, id);
		em.getTransaction().begin(); 
		candidatoAntigo.setNome(candidato.getNome()); 
		candidatoAntigo.setNumero(candidato.getNumero());
		candidatoAntigo.setPartido(candidato.getPartido());
		candidatoAntigo.setVoto(candidato.getVoto());
		em.getTransaction().commit();
		em.close();
		}
	}

	@Override
	public Object pesquisarPorNome(String nome) throws SQLException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Candidato> qry = em.createQuery(
				"select candidato from Candidato candidato where candidato.nome = :nome",
				Candidato.class);
		qry.setParameter("nome", nome );
		try{
			Candidato resultado = qry.getSingleResult();
			em.close();
			return resultado;
			}catch(Exception e){
				em.close();
				e.printStackTrace();
				return null;
			}
	}

	

	@Override
	public List vencedor() throws SQLException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Candidato> qry = em.createQuery(
				"select candidato from Candidato candidato order by voto desc",
				Candidato.class);
		List<Candidato> resultado = qry.getResultList();
		em.close();
		return resultado;
	}

	@Override
	public List pesquisarTodos() throws SQLException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Candidato> qry = em.createQuery(
				"select candidato from Candidato candidato",
				Candidato.class);
		List<Candidato> resultado = qry.getResultList();
		em.close();
		return resultado;
			}

	@Override
	public Eleitor pesquisarPorTitulo(String titulo) throws SQLException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Eleitor> qry = em.createQuery(
				"select eleitor from Eleitor eleitor where eleitor.titulo = :titulo",
				Eleitor.class);
		qry.setParameter("titulo",   titulo );
		Eleitor resultado = qry.getSingleResult();
		em.close();
		return resultado;
	}

	@Override
	public Candidato pesquisarPorNumero(int numero) throws SQLException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Candidato> qry = em.createQuery(
				"select candidato from Candidato candidato where candidato.numero = :numero",
				Candidato.class);
		qry.setParameter("numero",   numero );
		try{
		Candidato resultado = qry.getSingleResult();
		em.close();
		return resultado;
		}catch(Exception e){
			em.close();
			e.printStackTrace();
			return null;
		}
		
		
	}

}
