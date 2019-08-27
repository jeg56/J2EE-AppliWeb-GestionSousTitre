package com.jeg.soustitre.dao;

import java.util.List;

import com.jeg.soustitre.beans.Fichier;
import com.jeg.soustitre.beans.Sequence;

public class FichierTraductionDaoImpl implements FichierTraductionDao {
	private DaoFactory daoFactory;
	private FichiersDao fichiersDao;
	private SequencesDao sequencesDao;
	FichierTraductionDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		
	}
	
	@Override
	public String save(Fichier file, List<Sequence> dataSequence) {
		/*** Insertion des données dans la table fichier ***/
		this.fichiersDao = daoFactory.getFichiersDao();
		Integer idFichier = this.fichiersDao.insertFichiers(file);
		/*** Insertion des données dans la table traduction ***/
		this.sequencesDao = daoFactory.getSequenceDao();

		return this.sequencesDao.insertListSequence(idFichier, dataSequence);
	}
}

