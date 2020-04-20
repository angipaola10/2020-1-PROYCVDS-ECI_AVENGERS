package edu.eci.cvds.entities;

import java.text.SimpleDateFormat;
import java.util.*;

public class Iniciativa{
	private String nombre;
	private String descripcion;
	private Date fechaInicio;
	private List<PalabraClave> palabrasClaves;
	private String area;
	private String usuario;
	private Estado estado;
	
	public Iniciativa(String nombre, String descripcion, Date fechaInicio, String area, String usuario, Estado estado){
		this.nombre = nombre;
		this.descripcion= descripcion;
		this.fechaInicio = fechaInicio;
		this.area= area;
		this.usuario = usuario;
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public List<PalabraClave> getPalabrasClaves() {
		return palabrasClaves;
	}

	public void setPalabrasClaves(List<PalabraClave> palabrasClaves) {
		this.palabrasClaves = palabrasClaves;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public String toString() {
		return "Iniciativa {nombre: " + nombre + ", descripcion: " + descripcion + ", fechaInicio: " + fechaInicio + ", palabrasClaves: " + palabrasClaves + ", area: " + area + ", usuario: " + usuario + "}";
	}
	
}
