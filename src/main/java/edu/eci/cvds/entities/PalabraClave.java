package edu.eci.cvds.entities;

public class PalabraClave {

	private String palabraClave;
	
	public PalabraClave() {}
	
	public PalabraClave(String palabraClave) {
		this.palabraClave= palabraClave;
	}

	public String getPalabraClave() {
		return palabraClave;
	}

	public void setPalabraClave(String palabraClave) {
		this.palabraClave = palabraClave;
	}
	
	public String toString() {
		return palabraClave;
	}
}
