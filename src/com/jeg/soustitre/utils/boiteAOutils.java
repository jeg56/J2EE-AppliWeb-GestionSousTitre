package com.jeg.soustitre.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.servlet.http.Part;

import com.jeg.soustitre.beans.Fichier;
import com.jeg.soustitre.beans.Sequence;
import com.jeg.soustitre.exception.configException;

public class boiteAOutils {
	private static final String PROPERTY_FILE = "resources/config.properties";

	 public static void ecrireFichierTemporaire( Part part, String nomFichier ) throws IOException {
			String CHEMIN_FICHIERS_TEMP="";
			Integer TAILLE_TAMPON;
		    Properties prop = new Properties();
		    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		    
		    InputStream in = classLoader.getResourceAsStream(PROPERTY_FILE);
		    try {
				prop.load(in);
				CHEMIN_FICHIERS_TEMP = prop.getProperty("CHEMIN_FICHIERS_TEMP");
				TAILLE_TAMPON=Integer.parseInt(prop.getProperty("TAILLE_TAMPON"));
		    } catch (IOException e) {
		    	throw new configException("Impossible de charger le fichier de propriété :  " + PROPERTY_FILE + " - " + e.toString());
		    }
		    
	        BufferedInputStream entree = null;
	        BufferedOutputStream sortie = null;
	        try {
	            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
	            sortie = new BufferedOutputStream(new FileOutputStream(new File(CHEMIN_FICHIERS_TEMP + nomFichier)), TAILLE_TAMPON);

	            byte[] tampon = new byte[TAILLE_TAMPON];
	            int longueur;
	            while ((longueur = entree.read(tampon)) > 0) {
	                sortie.write(tampon, 0, longueur);
	            }
	      
	        
	   } catch (Exception e) {
           e.printStackTrace();
        
       }
	        finally {
	            try {
	                sortie.close();  
	            } catch (IOException ignore) {
	            }
	            try {
	                entree.close();
	            } catch (IOException ignore) {
	            }
	        }
	    }
	 
	 public static String getNomFichier( Part part ) {
	        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
	            if ( contentDisposition.trim().startsWith( "filename" ) ) {
	                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
	            }
	        }
	        return null;
	    } 
	 
	 
	public static void writeFile (List<Sequence> listseq,String pathNomFic) {
        BufferedWriter writer = null;
        try {
            File fic = new File(pathNomFic);

            writer = new BufferedWriter(new FileWriter(fic));
           
			for (Sequence seq : listseq) {
				writer.write(seq.getNum_sequence()+"\n");
				writer.write(seq.getTime_deb()+" --> "+seq.getTime_fin()+"\n");
				writer.write(seq.getMessage_cible() == null?" "+"\n\n":seq.getMessage_cible()+"\n\n");
			}
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	public static List<Sequence> readFile (String nomFichier){
		List<Sequence> listeSequence = new ArrayList<Sequence>(); 
		String CHEMIN_FICHIERS_TEMP="";
	    Properties prop = new Properties();
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    InputStream in = classLoader.getResourceAsStream(PROPERTY_FILE);
	    try {
			prop.load(in);
			CHEMIN_FICHIERS_TEMP = prop.getProperty("CHEMIN_FICHIERS_TEMP");
	    } catch (IOException e) {
	    	throw new configException("Impossible de charger le fichier de propriété :  " + PROPERTY_FILE + " - " + e.toString());
	    }
	    
		//lecture du fichier texte
	    Sequence seq;
	    String ligne = null,ligne_message;
		  try{
			File file = new File(CHEMIN_FICHIERS_TEMP+nomFichier);
			Scanner scan = new Scanner(file,"UTF-8");
		
			while(scan.hasNextLine()){
				seq = new Sequence();
				String message_complet="";
				ligne=scan.nextLine();
				seq.setNum_sequence(Integer.parseInt(ligne.trim()));
				ligne=scan.nextLine();
				seq.setTime_deb(ligne.split("-->")[0].trim());
				seq.setTime_fin(ligne.split("-->")[1].trim());
			    ligne_message="";
			    while (!(ligne_message=scan.nextLine()).trim().equalsIgnoreCase("")){
			    	message_complet+=(message_complet.equalsIgnoreCase(""))?ligne_message:"\n"+ligne_message;
			    	if(!scan.hasNextLine())break;
			    }
			    seq.setMessage_source(message_complet);
			    listeSequence.add(seq);
			 
			}
			  scan.close();
			  file.delete();
		  }  
		  catch (Exception e){
			  System.out.println("Probleme dans l'importation du fichier sequence");
			  System.out.println(ligne);
		   System.out.println(e.toString());
		  }
		
		return listeSequence;
	}
}
