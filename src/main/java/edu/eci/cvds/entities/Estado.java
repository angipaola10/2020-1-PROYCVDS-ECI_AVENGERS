package edu.eci.cvds.entities;

public class Estado {
	private String estado;
	
	public Estado(String estado) {
		this.estado= estado;
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