package com.jeg.soustitre.servlets;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeg.soustitre.dao.DaoFactory;
import com.jeg.soustitre.dao.FichiersDao;
import com.jeg.soustitre.dao.SequencesDao;

@WebServlet("/enregistrerSequence")
public class enregistrerSequence extends HttpServlet {
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
		this.getServletContext().getRequestDispatcher("/WEB-INF/liste.jsp").forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration enumeration = request.getParameterNames();
		Map<Integer, String> modelMap = new HashMap<>();
		while(enumeration.hasMoreElements()){
		    String parameterName = (String) enumeration.nextElement();
		    if(parameterName.length()>11 && parameterName.substring(0,12).equalsIgnoreCase("messageCible")){
		    	modelMap.put(Integer.parseInt(parameterName.substring(13)),request.getParameter(parameterName));
		    }
		}
		    
		String numFichier = request.getParameter("idFichier");
		request.setAttribute("etatEnregistrement",this.sequencesDao.setUpdateTraduction(numFichier, modelMap));
		
		request.setAttribute("listeFichiers",this.fichiersDAO.lister());	
		request.setAttribute("idFichier",request.getParameter("idFichier"));
		request.setAttribute("listeSequence",this.sequencesDao.getSequence(numFichier));
		 
		 
		this.getServletContext().getRequestDispatcher("/WEB-INF/liste.jsp").forward(request,response);
	}

}
