package com.jeg.soustitre.beans;

public class fichierTraduction  {
	 private Fichier file ;
	 private Sequence sequence ;
	 /*********************************************************************************/
	 /*** Les constructeurs 														***/
	 /*********************************************************************************/
	public fichierTraduction(){
		file = new Fichier();
		sequence = new Sequence();
	}
	
	 /*********************************************************************************/
	 /*** Les getters  																***/
	 /*********************************************************************************/
	public Fichier getFile() {
		return file;
	}
	public Sequence getSequence() {
		return sequence;
	}
	
	 /*********************************************************************************/
	 /*** Les setters  																***/
	 /*********************************************************************************/
	
	public void setFile(Fichier unObjet) {
		this.file = unObjet;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	public void save() {
		// TODO Auto-generated method stub
		
	}

}
