package com.jeg.soustitre.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeg.soustitre.dao.DaoFactory;

@WebServlet("/accueil")
public class accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public accueil() {
        super();
    	DaoFactory daoFactory = DaoFactory.newInstance();
		daoFactory.toString();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
	}
}
