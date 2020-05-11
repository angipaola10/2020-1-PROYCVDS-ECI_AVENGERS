package edu.eci.cvds.entities;

import java.awt.List;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comentario {
	private String idusuario;
	private String comentario;
	private int idiniciativa;
	private Date fecha;
	
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getFechaString() {
		SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        return dt1.format(this.fecha);
	}
}
