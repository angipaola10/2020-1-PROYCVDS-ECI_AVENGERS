package edu.eci.cvds.entities;

public class Estado {

	private int id;
	private String estado;
	
	public Estado(int id, String estado) {
		this.id= id;
		this.estado= estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstad(String estado) {
		this.estado = estado;
	}
	
	public String toString() {
		return "Estado {estado: " + estado + "}";
	}
}