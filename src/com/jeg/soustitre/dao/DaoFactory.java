package com.jeg.soustitre.dao;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.jeg.soustitre.exception.configException;
import com.jeg.soustitre.dao.FichiersDao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
	private static final String PROPERTY_FILE = "resources/config.properties";
    private String username;
    private String url;
    private String password;

    
    public DaoFactory(String url, String username, String password){
        this.username = username;
        this.url = url;
        this.password = password;
    }
	 /*********************************************************************************/
	 /*** Les connexions 															***/
	 /*********************************************************************************/
	  public static DaoFactory newInstance(){
	        String username,password,url,driver;
			
	        Properties prop = new Properties();
	        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

	        try {
	         	InputStream in = classLoader.getResourceAsStream(PROPERTY_FILE);
	         	prop.load(in);

	            username = prop.getProperty("username");
	            password = prop.getProperty("password");
	            url = prop.getProperty("url");
	            driver = prop.getProperty("driver");
	          
	 
	        } catch (IOException e) {
	        	throw new configException("Impossible de charger le fichier de propriété :  " + PROPERTY_FILE + " - " + e.toString());
	        }
	        
	        // Load driver from classpath
	        try{
	            Class.forName(driver);
	        }catch(ClassNotFoundException e){
	        	throw new configException("Impossible de charger de charger le driver - " + e.toString());

	        }
	        
	        DaoFactory instance = new DaoFactory(url, username, password);
	        return instance;  
	    }

	@Override
	public String toString() {
		  return String.format("username : "+username +" - " + "password" + password);
	}
	   

    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, username, password);
    }
    

	 /*********************************************************************************/
	 /*** Le DAO		 															***/
	 /*********************************************************************************/
	public FichiersDao getFichiersDao() {
		return new FichiersDaoImpl(this);
	}
	
	public SequencesDao getSequenceDao() {
		return new SequencesDaoImpl(this);
	}
	
	public FichierTraductionDao getFichierTraductionDao() {
		return new FichierTraductionDaoImpl(this);
	}

}
