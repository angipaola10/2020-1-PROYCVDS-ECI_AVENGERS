package edu.eci.cvds.entities;

import java.text.SimpleDateFormat;
import java.util.*;

public class Iniciativa{
	private String nombre;
	private int id;
	private String descripcion;
	private Date fechaInicio;
	private List<PalabraClave> palabrasClaves;
	private String area;
	private Usuario usuario;
	private String estado;
	
	public Iniciativa(String nombre, int id, String descripcion, Date fechaInicio, List<PalabraClave> palabrasClaves, String area, Usuario usuario, String estado){
		this.nombre = nombre;
		this.id =  id;
		this.descripcion= descripcion;
		this.fechaInicio = fechaInicio;
		this.palabrasClaves = palabrasClaves;
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String toString() {
		return "Iniciativa {nombre: " + nombre + ",id: " + id + ", descripcion: " + descripcion + ", fechaInicio: " + fechaInicio + ", palabrasClaves: " + palabrasClaves + ", area: " + area + ", usuario: " + usuario + "}";
	}
	
}
