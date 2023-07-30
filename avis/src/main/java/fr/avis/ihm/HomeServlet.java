package fr.avis.ihm;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import fr.avis.bo.Avis;
import fr.avis.config.EntityManagerFactorySingleton;
import fr.avis.dal.Repository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Créez un EntityManagerFactory en utilisant le nom de l'unité de persistance spécifié dans persistence.xml
        EntityManagerFactory emf = EntityManagerFactorySingleton.getInstance();

        // Créez un nouvel EntityManager à partir de l'EntityManagerFactory
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();        
        Avis c = new Avis();
        c.setContent("bien bien bien");
        em.persist(c);
        em.flush();
        transaction.commit();
		Repository r = new Repository();
		System.out.println(r.findAll());
		EntityManagerFactorySingleton.close();
		response.getWriter().append("Served at: ").append(request.getContextPath());
	} 
}
