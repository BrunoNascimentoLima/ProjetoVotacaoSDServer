package edu.fatec.rmi;



import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.fatec.dao.CandidatoDAO;
import edu.fatec.dao.DAO;
import edu.fatec.dao.EleitorDAO;
import edu.fatec.dao.FuncionarioDAO;
import edu.fatec.entidades.Candidato;
import edu.fatec.entidades.Eleitor;
import edu.fatec.entidades.Funcionario;

public class ElectionImpl extends UnicastRemoteObject implements Election{

	private static final long serialVersionUID = 1L;
	
	
	public ElectionImpl() throws RemoteException {
		super();
		
	}


	@Override
	public boolean vote(String nomeCandidato, String titulo, long numeroVotos)
			throws RemoteException {
		numeroVotos++;
		Candidato candidato = new Candidato();
		Eleitor eleitor = new Eleitor();
		DAO edao= new EleitorDAO();
		DAO cDAO= new CandidatoDAO();
		try {			
			eleitor=edao.pesquisarPorTitulo(titulo);
			if(eleitor!=null){
				eleitor.setConfirmacao(eleitor.getConfirmacao()+1);
				edao.atualizar(eleitor.getId(), eleitor);
			    candidato=(Candidato)cDAO.pesquisarPorNome(nomeCandidato);
			    candidato.setVoto(candidato.getVoto()+1);
			    cDAO.atualizar(candidato.getId(), candidato);
			}
			 return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public Candidato result(String nomeCandidato) throws RemoteException {
		Candidato candidato=null;
		DAO cdao = new CandidatoDAO();
		try {
			ArrayList<Candidato> candidatos = (ArrayList<Candidato>) cdao.vencedor();
		    candidato = candidatos.get(0);
		    return candidato;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return candidato;
	}


	@Override
	public Eleitor verificar(String titulo) throws RemoteException {
		   Eleitor e;
		   DAO edao= new EleitorDAO();
		   try {
			e=edao.pesquisarPorTitulo(titulo);
		     if(e!=null)
		    	 return e;
		     else
		    	 return null;
		   
		   } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}


	@Override
	public Candidato trazerCandidato(int numeroCandidato)
			throws RemoteException {
		Candidato c;
		DAO cdao = new CandidatoDAO();
		try {
			c=cdao.pesquisarPorNumero(numeroCandidato);
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Candidato trazerCandidatoPorNome(String nomeCandidato)
			throws RemoteException {
		Candidato c;
		DAO cdao = new CandidatoDAO();
		try {
			c=(Candidato)cdao.pesquisarPorNome(nomeCandidato);
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}


	@Override
	public List trazerCandidatos() throws RemoteException {
		DAO cdao= new CandidatoDAO();
		try {
			List<Candidato> candidatos = cdao.pesquisarTodos();
			return candidatos;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}


	@Override
	public Funcionario autenticarFuncionario(String usuario)
			throws RemoteException {
		Funcionario func= new Funcionario();
		DAO fdao = new FuncionarioDAO();
		try {
		func=(Funcionario)fdao.pesquisarPorNome(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return func;
	}


	@Override
	public boolean cadastrarEleitor(Eleitor eleitor) throws RemoteException {
		DAO edao = new EleitorDAO();
		try {
		boolean atualizado=	edao.inserir(eleitor);
			return atualizado;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	
	}


	@Override
	public boolean cadastrarCandidato(Candidato candidato) throws RemoteException {
		DAO cdao = new CandidatoDAO();
		try {
		boolean atualizado=	cdao.inserir(candidato);
			return atualizado;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public List trazerEleitores() throws RemoteException {
		DAO edao= new EleitorDAO();
		try {
			List<Eleitor> eleitores = edao.pesquisarTodos();
			return eleitores;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}



}
