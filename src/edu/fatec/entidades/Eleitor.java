package edu.fatec.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Eleitor implements Serializable{
	
	
	
	private static final long serialVersionUID = -1L;
	private long id;
	private String nome;
	private String titulo;
	private int confirmacao;

	public Eleitor(){}

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
	public int getConfirmacao() {
		return confirmacao;
	}

	public void setConfirmacao(int confirmacao) {
		this.confirmacao = confirmacao;
	}
    @Column
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
}
