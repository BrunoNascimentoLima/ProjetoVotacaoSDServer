package edu.fatec.rmi;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;


import edu.fatec.dao.CandidatoDAO;
import edu.fatec.dao.EleitorDAO;
import edu.fatec.entidades.Candidato;
import edu.fatec.entidades.Eleitor;


public class TestaServer {

	
	
	public static void main(String[] args) throws  MalformedURLException {
		try{
		   
			Eleitor e = new Eleitor();
			e.setNome("Bruno Nascimento Lima");
			e.setTitulo("1111-1111-1111");
		  EleitorDAO edao= new EleitorDAO();
		//	edao.inserir(e);
			
			//System.setSecurityManager (new RMISecurityManager());
		    Election eleicao = new ElectionImpl();
			LocateRegistry.createRegistry(1990);
		    Naming.rebind("//localhost:1990/eleicao",eleicao);
		     System.out.println("Servidor RMI " + 
		        " registrado e pronto para receber requisições.");
		     
		     
		}
		catch (Exception ex){
			System.out.println("Houve um erro: " + ex.getMessage());
		}

	
	}
	
}

		

