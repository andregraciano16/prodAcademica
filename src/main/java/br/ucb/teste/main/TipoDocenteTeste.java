package br.ucb.teste.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.ucb.entity.Curso;
import br.ucb.entity.Endereco;
import br.ucb.entity.TipoDocente;
import br.ucb.entity.TipoProducao;

public class TipoDocenteTeste {

	public static void main(String[] args) {
	    TipoDocente curso = new TipoDocente();
		curso.setIdTipoDocente(1);
		curso.setTipo("fjdlkasjdkfljasdkj");

		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("prodAc");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(curso);
		em.getTransaction().commit();
		
	}
	
}
