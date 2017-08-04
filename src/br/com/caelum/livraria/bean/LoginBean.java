package br.com.caelum.livraria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario(){
		
		return usuario;
		
		
	}
	
	
	public String efetuaLogin(){
		
		System.out.println("Fazendo login do usu�rio " + this.usuario.getEmail());
		
		boolean existe = new UsuarioDao().existe(usuario);
		
		if(existe){
			
			return "livro?faces-redirect=true";
			
		}
		
		return null;
	}
	
}