package com.jeg.soustitre.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jeg.soustitre.beans.Sequence;
import com.jeg.soustitre.dao.SequencesDao;

public class SequencesDaoImpl implements SequencesDao{

	private DaoFactory daoFactory;

	SequencesDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public List<Sequence> getSequence(String numFichier) {
		List<Sequence> listeSequence = new ArrayList<Sequence>();
		Connection connexion = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultat = null;
		
		try {
			connexion = daoFactory.getConnection();
			prepareStatement = connexion.prepareStatement("SELECT * FROM traduction where id_fichier = ?");
			prepareStatement.setInt(1,  Integer.parseInt(numFichier));
			resultat = prepareStatement.executeQuery();

			while (resultat.next()) {
				Integer id = Integer.parseInt(resultat.getString("id"));
				Integer idFichier = Integer.parseInt(resultat.getString("id_fichier"));
				Integer numSequence = Integer.parseInt(resultat.getString("num_sequence"));
				String timeDeb = resultat.getString("time_deb");
				String timeFin = resultat.getString("time_fin");
				String messageSource = resultat.getString("message_source");
				String messageCible = resultat.getString("message_cible");				
				
				Sequence seq = new Sequence(id,idFichier,numSequence,timeDeb,timeFin,messageSource,messageCible);
			
				listeSequence.add(seq);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeSequence;		
	}
	
	@Override
	public String setUpdateTraduction(String numFichier, Map<Integer, String> messageCible) {
		Connection connexion = null;
		PreparedStatement prepareStatement = null;
		String etat="SUCCES";
		
		try {
			connexion = daoFactory.getConnection();
			prepareStatement = connexion.prepareStatement("update traduction set message_cible = ? where id_fichier = ? and num_sequence = ? ;");
		    for (Integer mapKey : messageCible.keySet()) { 
				prepareStatement.setString(1,  messageCible.get(mapKey));
				prepareStatement.setInt(2,  Integer.parseInt(numFichier));
				prepareStatement.setInt(3,  mapKey);
				prepareStatement.executeUpdate();
		    }
		} catch (SQLException e) {
			e.printStackTrace();
			etat="ECHEC";
		}
		return etat;
	}

	@Override
	public String insertListSequence(Integer idFichier, List<Sequence> listSeq) {
		Connection connexion = null;
		PreparedStatement prepareStatement = null;
		String etat="SUCCES";
		
		/*** Insertion des données ***/
		try {
			connexion = daoFactory.getConnection();

			for (Sequence seq : listSeq) {
				prepareStatement = connexion.prepareStatement("insert into traduction (id_fichier,num_sequence,time_deb,time_fin,message_source) values (?,?,?,?,?);");
				prepareStatement.setInt(1,idFichier );
				prepareStatement.setInt(2,seq.getNum_sequence());
				prepareStatement.setString(3,seq.getTime_deb());
				prepareStatement.setString(4,seq.getTime_fin());
				prepareStatement.setString(5,seq.getMessage_source());
				prepareStatement.executeUpdate();
			}
		} catch (SQLException e) {
			etat="ECHEC";
			e.printStackTrace();
		}
		return etat;
	}
}
