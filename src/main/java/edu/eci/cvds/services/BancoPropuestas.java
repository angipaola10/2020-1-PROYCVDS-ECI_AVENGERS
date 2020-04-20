package edu.eci.cvds.services;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.Rol;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.entities.Estado;

public interface BancoPropuestas {

    public abstract Usuario consultarUsuario(String correo) throws BancoPropuestasException;
	
	public abstract Usuario consultarUsuarioLog(String correo, String clave) throws BancoPropuestasException;
	
    public abstract List<Iniciativa> consultarIniciativas() throws BancoPropuestasException;

    public abstract List<Usuario> consultarUsuarios() throws BancoPropuestasException;

    public abstract Iniciativa consultarIniciativa(String correo) throws BancoPropuestasException;
    
    public abstract Iniciativa consultarIniciativaArea(String area) throws BancoPropuestasException;
	
	public abstract void  modificarUsuario(String rol, String correo) throws BancoPropuestasException;
	
	public abstract void  modificarUsuarioEstado(Estado estado, String correo) throws BancoPropuestasException;
    
    public abstract void registrarIniciativa(String nombre, String descripcion, Date fechaInicio, String area, String usuario, String estadoPropuesta) throws BancoPropuestasException; 
    
    public abstract void agruparIniciativas() throws BancoPropuestasException; 

	public abstract void registrarUsuario(int id, String tid, String nombre, BigInteger telefono, String correo, String clave, Rol rol, String estado) throws BancoPropuestasException; 

}