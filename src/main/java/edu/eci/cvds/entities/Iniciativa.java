package edu.eci.cvds.entities;

import java.util.*;
import java.lang.String;

public class Iniciativa{
	private String nombrePropuesta;
	private String descripcion;
	private Date fechaInicio;
	private List<PalabraClave> palabrasClaves;
	private String area;
	private String usuario;
	private String estado_Propuesta;
	
	public Iniciativa() {}
	
	public Iniciativa(String nombrePropuesta, String descripcion, Date fechaInicio, String area, String usuario, String esestado_Propuestatado){
		this.nombrePropuesta = nombrePropuesta;
		this.descripcion= descripcion;
		this.fechaInicio = fechaInicio;
		this.area= area;
		this.usuario = usuario;
		this.estado_Propuesta = estado_Propuesta;
	}

	public String getNombre() {
		return nombrePropuesta;
	}

	public void setNombre(String nombrePropuesta) {
		this.nombrePropuesta = nombrePropuesta;
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
	
	public String getEstado() {
		return estado_Propuesta;
	}

	public void setEstado(String estado_Propuesta) {
		this.estado_Propuesta = estado_Propuesta;
	}
	
	public String toString() {
		return "Iniciativa {nombre: " + nombrePropuesta + ", descripcion: " + descripcion + ", fechaInicio: " + fechaInicio + ", palabrasClaves: " + palabrasClaves + ", area: " + area + ", usuario: " + usuario + ", estado: " + estado_Propuesta + "}";
	}
	
}
