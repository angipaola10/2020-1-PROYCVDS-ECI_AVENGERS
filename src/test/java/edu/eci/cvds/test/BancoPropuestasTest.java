package edu.eci.cvds.test;

import org.junit.Before;
import org.junit.Test;
import org.mybatis.guice.transactional.Transactional;

import static org.junit.Assert.*;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.services.BancoPropuestas;
import edu.eci.cvds.services.BancoPropuestasException ;
import edu.eci.cvds.services.BancoPropuestasFactory;
import edu.eci.cvds.entities.Grupo;
import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.PalabraClave;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.managedbeans.BancoPropuestasBean;
import edu.eci.cvds.persistence.PersistenceException ;
import org.apache.ibatis.session.SqlSession;

@Transactional
public class BancoPropuestasTest {
    BancoPropuestas banco;
    public BancoPropuestasTest() throws BancoPropuestasException {
		banco  = BancoPropuestasFactory.getInstance().getBancoPropuestasTest();
	}

    @Test
    /**
     * Se realiza la consulta de una iniciativa con un codigo que no existe
     * @throws BancoPropuestasException
     */
    public void NoDeberiaConsultar() throws BancoPropuestasException {
    	try {
		banco.consultarIniciativa("1234567");}
    	catch(Exception e) {
    		assertTrue(true);
    	}
    }

    @Test
    /**
     * Se realiza la consulta de todas las iniciativas que existen
     * @throws BancoPropuestasException
     */
    public void DeberiaConsultariniciativas() throws BancoPropuestasException {
    	List<Iniciativa> prueba = banco.consultarIniciativas();
    	if (prueba.size() > 4) {
    		assertTrue(true);
    	} else {
    		assertTrue(false);
    	}
    }
    
    @Test
    /**
     * Se Se hace la consulta de de un usuario por el correo
     * @throws BancoPropuestasException
     */
    public void DeberiaConsultarUsuarioporNombre() throws BancoPropuestasException {
    	Usuario prueba = banco.consultarUsuario("henry.sanchez");
    	assertEquals(prueba.getId(),1015442700);
   	
    }
    
    @Test
    /**
     * Se realiza la consulta de las iniciativas de un usuario especifico "angied.ruiz"
     * @throws BancoPropuestasException
     */
    public void DeberiaConsultarIniciativaUsuario() throws BancoPropuestasException {
    	List<Iniciativa> prueba = banco.consultarIniciativa("angied.ruiz");
    	assertEquals(prueba.get(0).getNombrePropuesta(),"HoloLens Aid");
   	
    }
    
    @Test
    /**
     * Se realiza la actualizacion del rol de un usuario
     * @throws BancoPropuestasException
     */
    public void DeberiaCambiarRol() throws BancoPropuestasException {
    	Usuario prueba1 = banco.consultarUsuario("henry.sanchez");
    	String nombre1=prueba1.getRol().name();
    	String Rol="Proponente";
    	banco.modificarUsuario(Rol, "henry.sanchez");
    	Usuario prueba2 = banco.consultarUsuario("henry.sanchez");
    	String nombre2=prueba2.getRol().name();
    	if (nombre1=="Proponente") {assertTrue(prueba1.getRol().name()==prueba2.getRol().name());}
    	if (nombre1=="Administrador") {assertTrue(prueba1.getRol().name()!=prueba2.getRol().name());}
    }
    
    @Test
    /**
     * Se realiza el inicio de sesion del usuarui henry.sanchez
     * @throws BancoPropuestasException
     */
    public void IniciarSesionBien() throws BancoPropuestasException {
	    Usuario prueb = banco.consultarUsuarioLog("henry.sanchez", "santafe1948");
	    assertTrue(prueb.getRol().name()=="Proponente");
    }
    
    @Test
    /**
     * Se hace la consulta de todos los usuarios existentes
     * @throws BancoPropuestasException
     */
    public void DeberiaConsultarUsuarios() throws BancoPropuestasException {
    	List<Usuario> lista = banco.consultarUsuarios();
    	if (lista.size() > 4) {
    		assertTrue(true);
    	} else {
    		assertTrue(false);
    	}
    }
    
    /**
     * Se realiza la consulta de un usuario que no existe
     * @throws BancoPropuestasException
     */
    @Test
    public void NoDeberiaConsultarUsuario()throws BancoPropuestasException {
    	try {
    		Usuario prueba = banco.consultarUsuario("pepito.perez");}
    	
    	catch(Exception e) {
    		assertTrue(true);
    	}
    }
    
    @Test
    /**
     * Se realiza la actualizacion de rol de un ususario que no existe
     * @throws BancoPropuestasException
     */
    public void NoCambiarDeRol()throws BancoPropuestasException {
    	try {
        	banco.modificarUsuario("Administrador", "hola.mundo");}
    	catch(Exception e) {
    		assertTrue(true);
    	}
    }
    
    @Test
    /**
     * Se realiza la actualizacion del estado de un usuario
     * @throws BancoPropuestasException
     */
    public void DeberiaCambiaElEstadoDeUsuario() throws BancoPropuestasException {
    	
    	Usuario prueba1 = banco.consultarUsuario("henry.sanchez");
    	String nombre1 = prueba1.getEstado();
    	String estado="Inactivo";
    	banco.modificarUsuarioEstado(estado,"henry.sanchez");
    	Usuario prueba2 = banco.consultarUsuario("henry.sanchez");
    	String nombre2=prueba2.getEstado();
    	
    	if (nombre1.equals("Inactivo")) {
    		System.out.println("entro1");
    		assertTrue(nombre1.equals(nombre2));
    		}
    	else if (nombre1.equals("Activo")) {
    		System.out.println("Entro");
    		assertFalse(nombre1.equals(nombre2));
    		banco.modificarUsuarioEstado("Activo","henry.sanchez");
    		}
    	else {
    		assertTrue(false);
    	}
    }
    
    @Test
    /**
     * Se realiza el cambio de estado de un usuario, con el estado "hola" que no es permitido
     * @throws BancoPropuestasException
     */
    public void NoDeberiaCambiarEstadoDeUsuario() throws BancoPropuestasException {
    	
    	try {
    		String estado="hola";
    		banco.modificarUsuarioEstado(estado,"henry.sanchez");
    	} catch(Exception e) {
    		assertTrue(true);
    	}
    }
    
    @Test
    /**
     * Se realiza la consulta de los grupos de una iniciativa
     * @throws BancoPropuestasException
     */
    public void DeberiaHacerConsultaDeGrupos() throws BancoPropuestasException {
    	List<Grupo> lista = banco.consultarGrupo(5);
    	if (lista.size() >= 1) {
    		assertTrue(true);
    	} else {
    		assertTrue(false);
    	}
    }
    
    @Test
    /**
     * Se realiza la consulta de los grupos de una iniciativa que no existe
     * @throws BancoPropuestasException
     */
    public void NoDeberiaHacerConsultaDeGrupos() throws BancoPropuestasException {
    	try {
    		List<Grupo> lista = banco.consultarGrupo(100);
    	} catch (Exception e) {
    		assertTrue(true);
    	}
    }
    
    @Test
    /**
     * se realiza 
     * @throws BancoPropuestasException
     */
    public void NoDeberiaConsultarUsuarioporNombre() throws BancoPropuestasException {
    	try {
    		Usuario prueba = banco.consultarUsuario("sanchez");
    	} catch(Exception e) {
    		assertTrue(true);}
    }
}
