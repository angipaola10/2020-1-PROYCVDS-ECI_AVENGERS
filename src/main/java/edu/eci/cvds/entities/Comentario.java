package edu.eci.cvds.entities;

import java.awt.List;

public class Comentario {
	private String idusuario;
	private String comentario;
	private int idiniciativa;
	
	public Comentario() {}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}

	public int getIdiniciativa() {
		return idiniciativa;
	}

	public void setIdiniciativa(int idiniciativa) {
		this.idiniciativa = idiniciativa;
	}




}
