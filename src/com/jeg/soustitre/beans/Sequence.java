package com.jeg.soustitre.beans;

public class Sequence  {
	private Integer id;
	private Integer id_fichier;
	private Integer num_sequence;
	private String time_deb;
	private String time_fin;
	private String message_source;
	private String message_cible;
	
	 /*********************************************************************************/
	 /*** Les constructeurs 														***/
	 /*********************************************************************************/
	public Sequence() {
		
	}
	
	public Sequence(Integer id, Integer id_fichier, Integer num_sequence, String time_deb, String time_fin,
			String message_source, String message_cible) {
		this.id = id;
		this.id_fichier = id_fichier;
		this.num_sequence = num_sequence;
		this.time_deb = time_deb;
		this.time_fin = time_fin;
		this.message_source = message_source;
		this.message_cible = message_cible;
	}

	 /*********************************************************************************/
	 /*** Les getters  																***/
	 /*********************************************************************************/
	
	public Integer getId() {
		return id;
	}

	public Integer getId_fichier() {
		return id_fichier;
	}

	public Integer getNum_sequence() {
		return num_sequence;
	}

	public String getTime_deb() {
		return time_deb;
	}

	public String getTime_fin() {
		return time_fin;
	}

	public String getMessage_source() {
		return message_source;
	}

	public String getMessage_cible() {
		return message_cible;
	}

	 /*********************************************************************************/
	 /*** Les getters  																***/
	 /*********************************************************************************/
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setId_fichier(Integer id_fichier) {
		this.id_fichier = id_fichier;
	}

	public void setNum_sequence(Integer num_sequence) {
		this.num_sequence = num_sequence;
	}

	public void setTime_deb(String time_deb) {
		this.time_deb = time_deb;
	}

	public void setTime_fin(String time_fin) {
		this.time_fin = time_fin;
	}

	public void setMessage_source(String message_source) {
		this.message_source = message_source;
	}

	public void setMessage_cible(String message_cible) {
		this.message_cible = message_cible;
	}
	

}
