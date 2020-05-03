package edu.eci.cvds.services;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import edu.eci.cvds.entities.Comentario;
import edu.eci.cvds.entities.ReporteEstado;
import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.MeGusta;
import edu.eci.cvds.entities.PalabraClave;
import edu.eci.cvds.entities.ReporteArea;
import edu.eci.cvds.entities.Rol;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.persistence.PersistenceException;

public interface BancoPropuestas {

    public abstract Usuario consultarUsuario(String correo) throws BancoPropuestasException;
	
	public abstract Usuario consultarUsuarioLog(String correo, String clave) throws BancoPropuestasException;
	
    public abstract List<Iniciativa> consultarIniciativas() throws BancoPropuestasException;

    public abstract List<Usuario> consultarUsuarios() throws BancoPropuestasException;

    public abstract List<Iniciativa> consultarIniciativa(String correo) throws BancoPropuestasException;
    
    public abstract Iniciativa consultarIniciativaArea(String area) throws BancoPropuestasException;
	
	public abstract void  modificarUsuario(String rol, String correo) throws BancoPropuestasException;
	
	public abstract void  modificarPropuesta(String nombrePropuesta, String descripcion, String area, String usuario) throws BancoPropuestasException;
	
	public abstract void  modificarUsuarioEstado(String estado, String correo) throws BancoPropuestasException;
    
    public abstract void registrarIniciativa(String nombre, String descripcion, String area, String usuario) throws BancoPropuestasException; 
    
    public abstract List<ReporteArea> agruparIniciativas() throws BancoPropuestasException; 

	public abstract void registrarUsuario(int id, String tid, String nombre, BigInteger telefono, String correo, String clave, Rol rol, String estado) throws BancoPropuestasException; 

	public abstract void registrarPalabraClave(String palabraClave) throws BancoPropuestasException;
	
	public abstract void registrarPCIniciativa(int idIniciativa, int idPClave) throws BancoPropuestasException;
	
	public abstract Iniciativa consultarIdIniciativa(String nombrePropuesta)throws BancoPropuestasException;
	
	public abstract List<PalabraClave> consultarPalabrasClaves() throws BancoPropuestasException;
	
	public abstract void modificarIniciativaEstado(String estado, String nombre) throws BancoPropuestasException;

	public abstract List<PalabraClave> consultarPalabraClave(int id_iniciativa) throws BancoPropuestasException;

	public abstract List<Iniciativa> consultarIniciativaPalabraClave(String palabra)throws BancoPropuestasException;

	public abstract List<ReporteEstado> consultarEstados() throws BancoPropuestasException;

	public abstract void darLike(int id , String user) throws BancoPropuestasException ;

	public abstract List<MeGusta> consultarLikes(int id) throws BancoPropuestasException;

	public abstract void comentar(int id, String user, String comentario) throws BancoPropuestasException;

	public abstract List<Comentario> consultarComentarios(int id) throws BancoPropuestasException;

}