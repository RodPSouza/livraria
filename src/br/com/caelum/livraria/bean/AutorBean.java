package br.com.caelum.livraria.bean;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();

	// public void setAutor(Autor autor) {
	// this.autor = autor;
	// }

	private Integer autorId;

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void carregarAutorPelaId() {

		// Integer id = this.autor.getId();
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
		/*
		 * if (this.autor == null) { this.autor = new Autor(); }
		 */
	}

	public Autor getAutor() {
		return autor;
	}

	public RedirectView gravar() {

		System.out.println("Gravando autor " + this.autor.getNome());

		if (this.autor.getId() == null) {

			new DAO<Autor>(Autor.class).adiciona(this.autor);

		} else {

			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}

		this.autor = new Autor();
		// Isso faz o redirecionamento ser feito pelo Browser e não pelo
		// Servidor
		// return "livro?faces-redirect=true";

		// Forma mais profissional de fazer a mesma coisa:

		return new RedirectView("livro");

		// Seria possivel também fazer da seguinte forma:
		// return new ForwardView("livro"); pelo Servidor mas só que de uma
		// maneira mais profissional

	}

	public List<Autor> getAutores() {

		return new DAO<Autor>(Autor.class).listaTodos();

	}

	/*
	 * public List<Autor> getAutoresLista(){
	 * 
	 * return this.autor.getTodosAutores(); }
	 */

	public void remove(Autor autor) throws Exception {

		/*this.carrega(autor);

		// setAutor(autor);

		System.out.println("Não morreu");

		if (new DAO<Autor>(Autor.class).verificaAutorPossuiLivro(this.getAutor().toString()) == true) {

			FacesContext.getCurrentInstance().addMessage("autor",
					new FacesMessage("Esse autor possui livros escritos!"));
			System.out.println("Era para aparecer mensagem");

		} else {
*/
			new DAO<Autor>(Autor.class).remove(autor);
		/*	System.out.println("Era para excluir");

		}*/

	}

	public void carrega(Autor autor) {

		this.autor = autor;
		return;
	}

}
