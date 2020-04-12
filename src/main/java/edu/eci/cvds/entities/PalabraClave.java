package edu.eci.cvds.entities;

public class PalabraClave {
	
	private int id;
	private String palabraClave;
	
	public PalabraClave(int id, String palabraClave) {
		this.id= id;
		this.palabraClave= palabraClave;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
