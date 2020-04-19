package edu.eci.cvds.services;

import java.sql.Date;
import java.util.List;

import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.entities.Estado;

public interface BancoPropuestas {

    

    public abstract Usuario consultarUsuario(long docu) throws BancoPropuestasException;
   
    public abstract List<Iniciativa> consultarIniciativas() throws BancoPropuestasException;

    public abstract List<Usuario> consultarUsuarios() throws BancoPropuestasException;

    public abstract Iniciativa consultarIniciativa(String correo) throws BancoPropuestasException;
    
    public abstract void registrarIniciativa(String nombre, String descripcion, Date fechaInicio, String area, Usuario usuario, Estado estadoPropuesta) throws BancoPropuestasException;  

}