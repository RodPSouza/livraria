package br.com.caelum.livraria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T> {

	private final Class<T> classe;

	public DAO(Class<T> classe) {
		this.classe = classe;
	}

	public void adiciona(T t) {

		// consegue a entity manager
		EntityManager em = new JPAUtil().getEntityManager();

		// abre transacao
		em.getTransaction().begin();

		// persiste o objeto
		em.persist(t);

		// commita a transacao
		em.getTransaction().commit();

		// fecha a entity manager
		em.close();
	}

	public void remove(T t) throws Exception {
		
		try {

			EntityManager em = new JPAUtil().getEntityManager();
			em.getTransaction().begin();

			em.remove(em.merge(t));

			em.getTransaction().commit();
			em.close();
			
		} catch (Exception e) {

			System.out.println("Classe Remove");
		}
		
		//System.out.println(t);

		
		
	}

	public void atualiza(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.merge(t);

		em.getTransaction().commit();
		em.close();
	}

	public List<T> listaTodos() {
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).getResultList();

		em.close();
		return lista;
	}

	public T buscaPorId(Integer id) {
		EntityManager em = new JPAUtil().getEntityManager();
		T instancia = em.find(classe, id);
		em.close();
		return instancia;
	}

	public boolean verificaAutorPossuiLivro(String autor){
		
		EntityManager em = new JPAUtil().getEntityManager();
		long result = (Long) em.createQuery("Select count(n) from Livro_Autor Where autores_id = " + autor ).getSingleResult();
		em.close();
		
		if(result>0){
		 
			return false;
			
		} else {
			
			return true;
			
		}
		
	}
	
	public int contaTodos() {
		EntityManager em = new JPAUtil().getEntityManager();
		long result = (Long) em.createQuery("select count(n) from livro n")
				.getSingleResult();
		em.close();

		return (int) result;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();

		em.close();
		return lista;
	}

}
