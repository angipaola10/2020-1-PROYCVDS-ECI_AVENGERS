package edu.eci.cvds.entities;

public class Reporte {
	
	private String area;
	private int numIniciativas;
	
	public Reporte() {}
	
	public Reporte(String area, int numIniciativas) {
		this.area = area;
		this.numIniciativas = numIniciativas;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getNumIniciativas() {
		return numIniciativas;
	}

	public void setNumIniciativas(int numIniciativas) {
		this.numIniciativas = numIniciativas;
	}
	
	public String toString() {
		return "Reporte{area: " + area + ", numero de iniciativas: " + numIniciativas + "}";
	}
	
}
