package com.jeg.soustitre.dao;

import java.util.List;

import com.jeg.soustitre.beans.Fichier;
import com.jeg.soustitre.beans.Sequence;

public interface FichierTraductionDao {
	String save(Fichier file, List<Sequence> seqFichier);
}


