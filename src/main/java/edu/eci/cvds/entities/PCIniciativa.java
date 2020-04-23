package edu.eci.cvds.entities;

public class PCIniciativa {
	private int ini_id;
	private int palabras_clave;
	
	public PCIniciativa() {}

	public int getIni_id() {
		return ini_id;
	}

	public void setIni_id(int ini_id) {
		this.ini_id = ini_id;
	}

	public int getPalabras_clave() {
		return palabras_clave;
	}

	public void setPalabras_clave(int palabras_clave) {
		this.palabras_clave = palabras_clave;
	}

	public String toString() {
		return "PCIniciativa {ini_id: " + this.ini_id + ", palabras_clave: " + this.palabras_clave + "}";
	}
}
