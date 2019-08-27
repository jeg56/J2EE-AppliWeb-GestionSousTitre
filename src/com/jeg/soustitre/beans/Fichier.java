package com.jeg.soustitre.beans;


public class Fichier {
	private Integer id;
	private String nom_fichier;
	
	 /*********************************************************************************/
	 /*** Les constructeurs 														***/
	 /*********************************************************************************/
	 public Fichier() {
	}
	 
	 public Fichier(String nom_fichier) {
		this.nom_fichier = nom_fichier;
	}
	 
	 public Fichier(Integer id, String nom_fichier) {
		this.id = id;
		this.nom_fichier = nom_fichier;
	}

	 /*********************************************************************************/
	 /*** Les getters  																***/
	 /*********************************************************************************/
	public Integer getId() {
		return id;
	}

	public String getNom_fichier() {
		return nom_fichier;
	}

	
	 /*********************************************************************************/
	 /*** Les setters  																***/
	 /*********************************************************************************/

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNom_fichier(String nom_fichier) {
		this.nom_fichier = nom_fichier;
	}

}
