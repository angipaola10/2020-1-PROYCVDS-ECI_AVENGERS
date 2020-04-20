package edu.eci.cvds.services.impl;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.Rol;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.entities.Estado;
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
    public Usuario consultarUsuario(String correo) throws BancoPropuestasException {
        try {
            Usuario usu = usuarioDAO.obtenerUsuario(correo);
            if (usu==null) throw new BancoPropuestasException("El cliente no esta registrado");
            else return usu;

        } catch (PersistenceException e) {
            throw new BancoPropuestasException("Error al consultar el usuario con identificacion "+correo,e);
        }
    }
	
    @Override
    public List<Usuario> consultarUsuarios() throws BancoPropuestasException {
        try {
            System.out.println("hola 11");
            return usuarioDAO.obtenerUsuarios();
        } catch (PersistenceException e) {
        	System.out.println("hola 22");
            throw new BancoPropuestasException("Error al consultar Usuarios",e);
        }
    }
	
	@Override
    public Usuario consultarUsuarioLog(String correo, String clave) throws BancoPropuestasException {
        try {
            Usuario usu =usuarioDAO.obtenerUsuarioLog(correo, clave);
            if (usu==null) throw new BancoPropuestasException("El cliente no esta registrado");
            else return usu;

        } catch (PersistenceException e) {
            throw new BancoPropuestasException("Error al consultar el usuario con las credenciales "+correo+clave,e);
        }
    }

    @Override
    public Iniciativa consultarIniciativa(String correo) throws BancoPropuestasException {
        try {
            return iniciativaDAO.consultarIniciativa(correo);
        } catch (Exception ex) {
            throw new BancoPropuestasException("Error al consultar la iniciativa "+correo,ex);
        }
    }

    @Override
    public List<Iniciativa> consultarIniciativas() throws BancoPropuestasException {
        try {
            return iniciativaDAO.consularIniciativas();
        } catch (Exception ex) {
            throw new BancoPropuestasException("Error al consultar las iniciativas ",ex);
        }
    }
	
	@Override
	public void registrarIniciativa(String nombre, String descripcion, Date fechaInicio, String area, String usuario, Estado estadoPropuesta) throws BancoPropuestasException {
		try {
            iniciativaDAO.agregarIniciativa(nombre, descripcion, fechaInicio, area, usuario, estadoPropuesta);
        } catch (Exception ex) {
            throw new BancoPropuestasException("Error al registrar la iniciativa ",ex);
        }
		
	}
	
	@Override
	public void registrarUsuario(int id, String tid, String nombre, int telefono, String correo, String clave, Rol rol, String estado) throws BancoPropuestasException {
		try {
            usuarioDAO.agregarUsuario(id, tid, nombre, telefono, correo, clave, rol, estado);
        } catch (Exception ex) {
            throw new BancoPropuestasException("Error al registrar este usuario ",ex);
        }
	}
   
   @Override
	public void modificarUsuario(int rol, String correo)  throws BancoPropuestasException {
		try {
            usuarioDAO.actualizarUsuario(rol, correo);
        } catch (Exception ex) {
            throw new BancoPropuestasException("Error al actualizar el rol del usuario. ",ex);
        }
		
	}
  
   
}