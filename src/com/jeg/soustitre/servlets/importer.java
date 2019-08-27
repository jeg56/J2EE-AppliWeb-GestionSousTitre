package com.jeg.soustitre.servlets;

import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.jeg.soustitre.beans.Fichier;
import com.jeg.soustitre.beans.Sequence;
import com.jeg.soustitre.dao.DaoFactory;
import com.jeg.soustitre.dao.FichierTraductionDao;
import com.jeg.soustitre.utils.*;

@WebServlet("/importer")
public class importer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FichierTraductionDao fichierTraductionDao;
	
    public void init() throws ServletException {
      	DaoFactory daoFactory = DaoFactory.newInstance();
      	this.fichierTraductionDao = daoFactory.getFichierTraductionDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/import.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// On récupère le champ du fichier
        Part part = request.getPart("fichier");
            
        // On vérifie qu'on a bien reçu un fichier
        String nomFichier = boiteAOutils.getNomFichier(part);

        // Si on a bien un fichier
        if (nomFichier != null && !nomFichier.isEmpty()) {
         
            // Corrige un bug du fonctionnement d'Internet Explorer
             nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
                    .substring(nomFichier.lastIndexOf('\\') + 1);

            // On écrit définitivement le fichier sur le disque
             boiteAOutils.ecrireFichierTemporaire(part, nomFichier);
             Fichier infosFichier = new Fichier(nomFichier);
             List<Sequence> dataSequence = boiteAOutils.readFile(nomFichier);
           
             if(dataSequence.size()==0) {
            	 request.setAttribute("etatImportation","ECHEC");
             }else {
            	 request.setAttribute("etatImportation",this.fichierTraductionDao.save(infosFichier, dataSequence));
             }  
        }

        request.setAttribute("fichier",nomFichier);
		this.getServletContext().getRequestDispatcher("/WEB-INF/import.jsp").forward(request, response);
	} 
}



