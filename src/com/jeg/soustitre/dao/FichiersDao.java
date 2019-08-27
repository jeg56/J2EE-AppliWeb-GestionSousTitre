package com.jeg.soustitre.dao;

import java.util.List;

import com.jeg.soustitre.beans.Fichier;

public interface FichiersDao {
	List<Fichier> lister();
	Integer insertFichiers(Fichier fic);
}
