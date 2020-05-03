package edu.eci.cvds.entities;

public class ReporteEstado {
	
	private String estado_propuesta;
	private int numero;
	
	public ReporteEstado() {}
	
	public ReporteEstado(String estado_propuesta, int numero) {
		this.estado_propuesta = estado_propuesta;
		this.numero = numero;
	}
	
	public String getEstado() {
		return estado_propuesta;
	}
	
	public void setEstado(String estado) {
		this.estado_propuesta = estado;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public String toString() {
		
		return  estado_propuesta + "," + numero ;
	}	
	
}
