package edu.fatec.entidades;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Candidato implements Serializable {
	
	
	
	private static final long serialVersionUID = 1L;
	private long id;
	private String nome;
	private String partido;
	private int numero;
	private int voto;

	public Candidato(){}
	
	
    @Id
    @GeneratedValue
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}


    @Column
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	@Column
	public String getPartido() {
		return partido;
	}

	public void setPartido(String partido) {
		this.partido = partido;
	}
	@Column
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	@Column
	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}
	
	
}
