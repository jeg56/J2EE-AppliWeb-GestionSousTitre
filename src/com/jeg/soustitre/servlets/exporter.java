package com.jeg.soustitre.servlets;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jeg.soustitre.beans.Fichier;
import com.jeg.soustitre.beans.Sequence;
import com.jeg.soustitre.dao.DaoFactory;
import com.jeg.soustitre.dao.FichiersDao;
import com.jeg.soustitre.dao.SequencesDao;
import com.jeg.soustitre.exception.configException;

@WebServlet("/exporter")
public class exporter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FichiersDao fichiersDAO;
	private SequencesDao sequencesDao;
	private List<Fichier> listFichier;
	private static final String PROPERTY_FILE = "resources/config.properties";
	private String CHEMIN_FICHIERS_TEMP;
	public static final String SESSION_CLIENTS  = "clients";
	
    public void init() throws ServletException {
      	DaoFactory daoFactory = DaoFactory.newInstance();
        this.fichiersDAO = daoFactory.getFichiersDao();
        this.sequencesDao = daoFactory.getSequenceDao();
	    Properties prop = new Properties();
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    InputStream in = classLoader.getResourceAsStream(PROPERTY_FILE);
	    try {
			prop.load(in);
			this.CHEMIN_FICHIERS_TEMP = prop.getProperty("CHEMIN_FICHIERS_TEMP");
	    } catch (IOException e) {
	    	throw new configException("Impossible de charger le fichier de propriété :  " + PROPERTY_FILE + " - " + e.toString());
	    }
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listeFichiers",this.fichiersDAO.lister());
		HttpSession session = request.getSession();
		session.invalidate();
		this.getServletContext().getRequestDispatcher("/WEB-INF/export.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listFichier=this.fichiersDAO.lister();
		request.setAttribute("listeFichiers",this.fichiersDAO.lister());	
		HttpSession session = request.getSession();
		if(!request.getParameter("idFichier").trim().equalsIgnoreCase("")) {
			for (Fichier fic : listFichier) {
				if(fic.getId()==Integer.parseInt(request.getParameter("idFichier"))) {
					List<Sequence> dataSequence = this.sequencesDao.getSequence(request.getParameter("idFichier"));
					session.setAttribute("dataSequence",dataSequence);
					session.setAttribute("fic",fic.getNom_fichier());
				}
			}
		}

			this.getServletContext().getRequestDispatcher("/WEB-INF/export.jsp").forward(request, response);	
	}
}



