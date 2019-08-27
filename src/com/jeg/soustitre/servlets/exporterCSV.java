package com.jeg.soustitre.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.jeg.soustitre.beans.Fichier;
import com.jeg.soustitre.beans.Sequence;
import com.jeg.soustitre.beans.fichierTraduction;
import com.jeg.soustitre.dao.DaoFactory;
import com.jeg.soustitre.dao.FichierTraductionDao;
import com.jeg.soustitre.dao.FichiersDao;
import com.jeg.soustitre.dao.SequencesDao;
import com.jeg.soustitre.exception.configException;
import com.jeg.soustitre.utils.*;
/**
 * Servlet implementation class importer
 */
@WebServlet("/exporterCSV")
public class exporterCSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String PROPERTY_FILE = "resources/config.properties";
	private String CHEMIN_FICHIERS_TEMP;
	public static final String SESSION_CLIENTS  = "clients";
	
    public void init() throws ServletException {
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
		HttpSession session = request.getSession();
		List<Sequence> dataSequence =  (List<Sequence>) session.getAttribute("dataSequence");
		String fic =(String) session.getAttribute("fic");
		
		boiteAOutils.writeFile(dataSequence,CHEMIN_FICHIERS_TEMP+fic);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ fic.trim() + "\"");
 
		FileInputStream fileInputStream = new FileInputStream(CHEMIN_FICHIERS_TEMP+fic.trim());
 
		int i;
		while ((i = fileInputStream.read()) != -1) {
			out.write(i);
		}
		fileInputStream.close();
		out.close();
	}
}



