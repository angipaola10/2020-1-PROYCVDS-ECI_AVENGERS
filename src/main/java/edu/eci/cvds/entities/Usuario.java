package edu.eci.cvds.entities;

import java.io.Serializable;
import java.util.*;

public class Usuario implements Serializable {

	private int id;
	private String tid;
    private String nombre;
    private int  telefono;
    private String correo;
    private String estado;
    private String clave;
    private Rol rol;
    
    public Usuario() {}

    public Usuario(int id, String tid, String nombre, int telefono, String correo, String clave, Rol rol, String estado) {
    	this.id = id;
        this.tid = tid;
    	this.nombre = nombre;
    	this.telefono = telefono;
    	this.correo = correo;
        this.clave = clave;
        this.estado = estado;
        this.rol = rol;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int num) {
        this.id = num;
    }
    
    public String getTid() {
        return tid;
    }

    public void setTid(String docu) {
        this.tid = docu;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int tel) {
        this.telefono = tel;
    }
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String esta) {
        this.estado = esta;
    }
	
	 public String getClave() {
        return clave;
    }

    public void setClave(String cl) {
        this.clave = cl;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario {id: " + id + ", tid: " + tid + " ,nombre: " + nombre + ", telefono: " + telefono + ", correo : " + correo + ", clave: " + clave + ",estado: " + estado + ",rol: " + rol + "}";
    }

}
