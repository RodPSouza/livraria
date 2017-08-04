package br.com.caelum.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Usuario;

public class UsuarioDao {

    public boolean existe(Usuario usuario){ 
	
	EntityManager em = new JPAUtil().getEntityManager();
	TypedQuery<Usuario> createQuery = em.createQuery(" select u from Usuario u where u.email = :pEmail and u.senha = :pSenha", 
									   Usuario.class);
	
	
	createQuery.setParameter("pEmail", usuario.getEmail());
	createQuery.setParameter("pSenha", usuario.getSenha());
	
	try {
	
		Usuario resultado = createQuery.getSingleResult();
		
	}	catch (NoResultException ex){
			return false;
		
	}
    
	em.close();
	
	return true;
	
	
    }										
	
}
