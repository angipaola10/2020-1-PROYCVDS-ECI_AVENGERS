package edu.eci.cvds.entities;

public class Likes {
	private int megusta;
	
	public Likes() {}
	
	public Likes(int meGusta) {
		this.megusta=meGusta;
	}

	public int getMeGusta() {
		return megusta;
	}

	public void setMeGusta(int megusta) {
		this.megusta = megusta;
	}

	public String toString() {
		return "Likes [meGusta=" + megusta + "]";
	}

}
