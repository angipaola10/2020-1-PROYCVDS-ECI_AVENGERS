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
	/**
    @Test
    public void NoDeberiaConsultar() {
    	try {
		banco.consultarIniciativa("1234567");}
    	catch(Exception e) {
    		assertTrue(true);
    	}
    }
    
    @Test
    public void DeberiaConsultariniciativas() throws BancoPropuestasException {
    	List<Iniciativa> prueba = banco.consultarIniciativas();
    	assertEquals(prueba.size(),7);
   	
    }
    
    @Test
    public void DeberiaConsultarUsuarioporNombre() throws BancoPropuestasException {
    	Usuario prueba = banco.consultarUsuario("henry.sanchez");
    	assertEquals(prueba.getId(),1015442700);
   	
    }
    
    @Test
    public void DeberiaConsultarIniciativaUsuario() throws BancoPropuestasException {
    	List<Iniciativa> prueba = banco.consultarIniciativa("angied.ruiz");
    	assertEquals(prueba.get(0).getNombrePropuesta(),"HoloLens Aid");
   	
    }
    
    @Test
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
    public void IniciarSesionBien() throws BancoPropuestasException {
    Usuario prueb = banco.consultarUsuarioLog("henry.sanchez", "santafe1948");
    assertTrue(prueb.getRol().name()=="Proponente");
  }
	*/
}
