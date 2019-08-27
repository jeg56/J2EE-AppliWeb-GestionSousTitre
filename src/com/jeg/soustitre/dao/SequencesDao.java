package com.jeg.soustitre.dao;

import java.util.List;
import java.util.Map;

import com.jeg.soustitre.beans.Sequence;

public interface SequencesDao {
	List<Sequence> getSequence(String numFichier);
	String setUpdateTraduction(String numFichier, Map<Integer, String> messageCible);
	String insertListSequence(Integer idFichier,List<Sequence> listSeq);
}
