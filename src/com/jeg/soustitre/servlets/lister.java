package com.jeg.soustitre.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeg.soustitre.beans.Fichier;
import com.jeg.soustitre.dao.DaoFactory;
import com.jeg.soustitre.dao.FichiersDao;
import com.jeg.soustitre.dao.FichiersDaoImpl;
import com.jeg.soustitre.dao.SequencesDao;

@WebServlet(
		name = "lister", 
		urlPatterns = { "/liste" }, 
		initParams = { 
				@WebInitParam(name = "id_fichier", value = "")
		})
public class lister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FichiersDao fichiersDAO;
	private SequencesDao sequencesDao;


    public void init() throws ServletException {
    	DaoFactory daoFactory = DaoFactory.newInstance();
        this.fichiersDAO = daoFactory.getFichiersDao();
        this.sequencesDao = daoFactory.getSequenceDao();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     	 request.setAttribute("listeFichiers",this.fichiersDAO.lister());
		 this.getServletContext().getRequestDispatcher("/WEB-INF/liste.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setAttribute("listeFichiers",this.fichiersDAO.lister());
		 request.setAttribute("idFichier",request.getParameter("idFichier"));
		 request.setAttribute("listeSequence",this.sequencesDao.getSequence(request.getParameter("idFichier")));
		 
		this.getServletContext().getRequestDispatcher("/WEB-INF/liste.jsp").forward(request,response);
	}


}
