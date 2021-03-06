package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();
	
	
	
	private Integer autorId;
	
	private Integer livroId;
	
	public Integer getLivroId() {
		return livroId;
	}
	
	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}
	
	
	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public Integer getAutorId() {
		return autorId;
	}

	public Livro getLivro() {
		return livro;
	}
	
	public void carregarLivroPelaId(){
		
		this.livro = new DAO<Livro>(Livro.class).buscaPorId(this.livroId);
		
	}
	
	public List<Livro> getLivros(){
		
		return new DAO<Livro>(Livro.class).listaTodos();
		
	}
	
	public List<Autor> getAutores(){
		
		return new DAO<Autor>(Autor.class).listaTodos();
		
	}
	
	public List<Autor> getAutoresDoLivro(){
	
		return this.livro.getAutores();
	}
	
	public void gravarAutor(){
		
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
		System.out.println("Livro escrito por: " +  autor.getNome());
		
	}

	public void gravar() {
		
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			
			//throw new RuntimeException("Livro deve ter pelo menos um Autor.");
			//throw new ValidatorException(new FacesMessage("Livro deve ter pelo menos um Autor."));
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Um Livro deve ter pelo menos um Autor"));
			//return;
		}

		
		
		
		if(this.livro.getId() == null) {
			
			new DAO<Livro>(Livro.class).adiciona(this.livro);
		
		} else {
			
			new DAO<Livro>(Livro.class).atualiza(this.livro);
			
		}
		
		
		
		
		this.livro = new Livro();
	}
	
	public void remover(Livro livro) throws Exception{
		
		System.out.println("Removendo Livro");
		new DAO<Livro>(Livro.class).remove(livro);
	}
	
	public void removerAutorDoLivro(Autor autor){
		
		this.livro.removeAutor(autor);
		
	}
	
	
	
	public void carregar(Livro livro) {
		
		System.out.println("Carregando Livro");
		this.livro = livro;
		
	}
	
	
	public String formAutor() {
		
		System.out.println("Chamando o formul�rio do Autor");
		//Isso faz o redirecionamento ser feito pelo Browser e n�o pelo Servidor
		return "autor?faces-redirect=true";
	}
	
	
	
	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		
		String valor = value.toString();
		if(!valor.startsWith("1")){
			
			throw new ValidatorException(new FacesMessage("O ISBN deve come�ar com o n�mero 1"));
		}
		
	}

}
