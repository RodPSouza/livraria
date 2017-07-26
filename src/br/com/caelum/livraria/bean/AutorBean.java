package br.com.caelum.livraria.bean;

import javax.faces.bean.ManagedBean;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
public class AutorBean {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public RedirectView gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		new DAO<Autor>(Autor.class).adiciona(this.autor);
		
		this.autor = new Autor();
		//Isso faz o redirecionamento ser feito pelo Browser e não pelo Servidor
		//return "livro?faces-redirect=true";
		
		//Forma mais profissional de fazer a mesma coisa:
		
		return new RedirectView("livro");
		
		
		//Seria possivel também fazer da seguinte forma:
		// return new ForwardView("livro"); pelo Servidor mas só que de uma maneira mais profissional
		
	}
	
	
	
	
}
