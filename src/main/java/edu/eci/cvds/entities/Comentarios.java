package edu.eci.cvds.entities;

import java.awt.List;

public class Comentarios {
	private String nombre_comento;
	private int id_iniciativa;
	private int likes_iniciativa;
	private String comentario;
	
	public Comentarios() {}

	public String getNombre_comento() {
		return nombre_comento;
	}

	public void setNombre_comento(String nombre_comento) {
		this.nombre_comento = nombre_comento;
	}

	public int getId_iniciativa() {
		return id_iniciativa;
	}

	public void setId_iniciativa(int id_iniciativa) {
		this.id_iniciativa = id_iniciativa;
	}

	public int getLikes_iniciativa() {
		return likes_iniciativa;
	}

	public void setLikes_iniciativa(int likes_iniciativa) {
		this.likes_iniciativa = likes_iniciativa;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	};
	

}
