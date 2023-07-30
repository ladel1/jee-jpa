package fr.eni.comptes.ihm;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.eni.comptes.bo.Compte;

@WebServlet("/browser")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Créez un EntityManagerFactory en utilisant le nom de l'unité de persistance spécifié dans persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");

        // Créez un nouvel EntityManager à partir de l'EntityManagerFactory
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();        
        Compte c = new Compte();
        c.setClient("Adel");
        c.setSolde(100000000);
        em.persist(c);
        em.flush();
        transaction.commit();
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
