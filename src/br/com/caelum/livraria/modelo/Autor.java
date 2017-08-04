package br.com.caelum.livraria.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.caelum.livraria.dao.DAO;

@Entity
public class Autor {

	@Id
	@GeneratedValue
	private Integer id;
	private String nome;
	private String email;
	
	/*
	private List<Autor> autores = new ArrayList<Autor>();
	
	
	public List<Autor> getTodosAutores() {
		return autores;
	}
	*/
	/*
	public void removeAutor(Autor autor) {
		this.autores.remove(autor);
		
	}*/

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
