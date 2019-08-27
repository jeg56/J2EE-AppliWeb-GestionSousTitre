package com.jeg.soustitre.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.jeg.soustitre.beans.Fichier;
import com.jeg.soustitre.dao.FichiersDao;

public class FichiersDaoImpl implements FichiersDao{
	private DaoFactory daoFactory;

	FichiersDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public List<Fichier> lister() {
		List<Fichier> ListFichiers = new ArrayList<Fichier>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
	
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM fichier;");

			while (resultat.next()) {
				Integer id = Integer.parseInt(resultat.getString("id"));
				String nomFichier = resultat.getString("nom_fichier");
				
				Fichier fichier = new Fichier(id,nomFichier);
			
				ListFichiers.add(fichier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ListFichiers;
	}

	public Integer insertFichiers(Fichier file) {
		Connection connexion = null;
		PreparedStatement prepareStatement = null;
		
		Integer id_file=-1;;
		
		/*** Insertion des données ***/
		try {
			connexion = daoFactory.getConnection();
			prepareStatement = connexion.prepareStatement("insert into fichier (nom_fichier) values (?);",Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setString(1,  file.getNom_fichier());
			prepareStatement.executeUpdate();
			ResultSet resultat = prepareStatement.getGeneratedKeys();
		
			if(resultat.next()){
				 id_file = resultat.getInt(1);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id_file;
	}
}
