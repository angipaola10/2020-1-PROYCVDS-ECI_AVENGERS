package edu.eci.cvds.services;

import java.sql.Date;
import java.util.List;

import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.Usuario;

public interface BancoPropuestas {

    

    public abstract Usuario consultarUsuario(long docu) throws BancoPropuestasException;

   
    public abstract List<Iniciativa> ConsultarIniciativas() throws BancoPropuestasException;

    public abstract List<Usuario> consultarUsuarios() throws BancoPropuestasException;

    public abstract Iniciativa consultarIniciativa(int id) throws BancoPropuestasException;

   
    
    public abstract void registrarIniciativa(Iniciativa ini) throws BancoPropuestasException;

   

}