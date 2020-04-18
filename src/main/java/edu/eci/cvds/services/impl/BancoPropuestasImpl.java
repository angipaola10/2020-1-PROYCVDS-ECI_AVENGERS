package edu.eci.cvds.services.impl;


import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.persistence.IniciativaDAO;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.UsuarioDAO;
import edu.eci.cvds.services.BancoPropuestas;
import edu.eci.cvds.services.BancoPropuestasException;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.guice.transactional.Transactional;

@Singleton
public class BancoPropuestasImpl implements BancoPropuestas {

    @Inject
    private IniciativaDAO iniciativaDAO;
    @Inject
    private  UsuarioDAO usuarioDAO;



    @Override
    public Usuario consultarUsuario(long docu) throws BancoPropuestasException {
        try {
            Usuario usu =usuarioDAO.consultarUsuario(docu);
            if (usu==null) throw new BancoPropuestasException("El cliente no esta registrado");
            else return usu;

        } catch (PersistenceException e) {
            throw new BancoPropuestasException("Error al consultar el usuario con identificacion "+docu,e);
        }
    }
    @Override
    public List<Usuario> consultarUsuarios() throws BancoPropuestasException {
        try {
            System.out.println("hola 11");
            return usuarioDAO.ConsultarUsuarios();
        } catch (PersistenceException e) {
        	System.out.println("hola 22");
            throw new BancoPropuestasException("Error al consultar Usuarios",e);
        }
    }

    @Override
    public Iniciativa consultarIniciativa(int id) throws BancoPropuestasException {
        try {
            return iniciativaDAO.ConsultarIniciativa(id);
        } catch (Exception ex) {
            throw new BancoPropuestasException("Error al consultar la iniciativa "+id,ex);
        }
    }

    @Override
    public List<Iniciativa> ConsultarIniciativas() throws BancoPropuestasException {
        try {
            return iniciativaDAO.ConsularIniciativas();
        } catch (Exception ex) {
            throw new BancoPropuestasException("Error al consultar las iniciativas ",ex);
        }
    }
	@Override
	public void registrarIniciativa(Iniciativa ini) throws BancoPropuestasException {
		// TODO Auto-generated method stub
		
	}

   
   
  
   

   
}