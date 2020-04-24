package edu.eci.cvds.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.PalabraClave;
import edu.eci.cvds.entities.Reporte;
import edu.eci.cvds.entities.Rol;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.persistence.IniciativaDAO;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.UsuarioDAO;
import edu.eci.cvds.services.BancoPropuestas;
import edu.eci.cvds.services.BancoPropuestasException;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

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
    public List<Iniciativa> consultarIniciativa(String correo) throws BancoPropuestasException {
        try {
            return iniciativaDAO.consultarIniciativa(correo);
        } catch (PersistenceException e) {
            throw new BancoPropuestasException("Error al consultar la iniciativa "+correo,e);
        }
    }
    
    @Override
    public Iniciativa consultarIniciativaArea(String area) throws BancoPropuestasException {
        try {
            return iniciativaDAO.consultarIniciativaArea(area);
        } catch (PersistenceException e) {
            throw new BancoPropuestasException("Error al consultar el area "+area,e);
        }
    }


    @Override
    public List<Iniciativa> consultarIniciativas() throws BancoPropuestasException {
        try {
        	System.out.println("entra al bancop");
            return iniciativaDAO.consularIniciativas();
        } catch (PersistenceException e) {
        	System.out.println("hola 22");
            throw new BancoPropuestasException("Error al consultar las iniciativas ",e);
        }
    }
   

	@Override
	public void registrarIniciativa(String nombre, String descripcion, String area, String usuario) throws BancoPropuestasException {
		try {
            iniciativaDAO.agregarIniciativa(nombre, descripcion, area, usuario);
        } catch (PersistenceException e) {
            throw new BancoPropuestasException("Error al registrar la iniciativa ",e);
        }
		
	}
	
	@Override
	public List<Reporte> agruparIniciativas() throws BancoPropuestasException {
		try {
            return iniciativaDAO.agruparIniciativas();
        } catch (PersistenceException e) {
            throw new BancoPropuestasException("Error al agrupar las iniciativas ",e);
        }
		
	}
	
	@Override
	public void registrarUsuario(int id, String tid, String nombre, BigInteger telefono, String correo, String clave, Rol rol, String estado) throws BancoPropuestasException {
		try {
            usuarioDAO.agregarUsuario(id, tid, nombre, telefono, correo, clave, rol, estado);
        } catch (PersistenceException e) {
            throw new BancoPropuestasException("Error al registrar este usuario ",e);
        }
	}
   
   @Override
	public void modificarUsuario(String rol, String correo)  throws BancoPropuestasException {
		try {
            usuarioDAO.actualizarUsuario(rol, correo);
        } catch (PersistenceException e) {
            throw new BancoPropuestasException("Error al actualizar el rol del usuario. ",e);
        }
	}
   
   @Override
	public void modificarUsuarioEstado(String estado, String correo)  throws BancoPropuestasException {
		try {
           usuarioDAO.actualizarUsuarioEstado(estado, correo);
       } catch (PersistenceException e) {
           throw new BancoPropuestasException("Error al actualizar el rol del usuario. ",e);
       }
	}
   
   @Override
	public void modificarIniciativaEstado(String estado, String correo)  throws BancoPropuestasException {
		try {
            iniciativaDAO.actualizarIniciativaEstado(estado, correo);
        } catch (PersistenceException e) {
            throw new BancoPropuestasException("Error al actualizar el estado de la iniciativa ",e);
        }
	}

   @Override
    public void registrarPalabraClave(String palabraClave) throws BancoPropuestasException {
	   try {
		   iniciativaDAO.agregarPalabraClave(palabraClave);
       } catch (PersistenceException e) {
           throw new BancoPropuestasException("Error al registrar la palabra clave ",e);
       }
   }
   @Override
	public void registrarPCIniciativa(int idIniciativa, int idPClave) throws BancoPropuestasException {
	   try {
           iniciativaDAO.agregarPCIniciativa(idIniciativa,idPClave);
       } catch (PersistenceException e) {
           throw new BancoPropuestasException("Error al registrar la tabla intermedia ",e);
       }
   }
   
   @Override
   public Iniciativa consultarIdIniciativa(String nombrePropuesta) throws BancoPropuestasException {
	   try {
		   return iniciativaDAO.consultarIdIniciativa(nombrePropuesta);
	   }catch (PersistenceException e) {
           throw new BancoPropuestasException("Error al consultar id iniciativa ",e);
       }  
   }
   
   @Override
	public List<PalabraClave> consultarPalabraClave(int id_iniciativa) throws BancoPropuestasException {
	   try {
           return iniciativaDAO.consultarPalabrasClave(id_iniciativa);
       } catch (PersistenceException e) {
           throw new BancoPropuestasException("Error al consultar las palabras clave de esta inciativa ",e);
       }
   }

   @Override
	public List<PalabraClave> consultarPalabrasClaves() throws BancoPropuestasException {
	   try {
           return iniciativaDAO.consultarPalabrasClaves();
       } catch (PersistenceException e) {
           throw new BancoPropuestasException("Error al consultar todas las palabras clave ",e);
       }
   }

@Override
public List<Iniciativa> consultarIniciativaPalabraClave(String palabra) throws BancoPropuestasException {
	 try {
         return iniciativaDAO.consultarIniciativaPalabraClave(palabra);
     } catch (PersistenceException e) {
         throw new BancoPropuestasException("Error al consultar iniciativa por palabra ",e);
     }
}
	
   
}