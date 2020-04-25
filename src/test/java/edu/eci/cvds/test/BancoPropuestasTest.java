package edu.eci.cvds.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.services.BancoPropuestas;
import edu.eci.cvds.services.BancoPropuestasException ;
import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.managedbeans.BancoPropuestasBean;
import edu.eci.cvds.persistence.PersistenceException ;
import org.apache.ibatis.session.SqlSession;

public class BancoPropuestasTest {
    BancoPropuestasBean banco;
    @Test
    public void NoDeberiaConsultar() {
    	try {
		banco.consultarIniciativa("1234567");}
    	catch(Exception e) {
    		assertTrue(true);
    	}
    }
    
    @Test
    public void DeberiaConsultarUsuario() throws BancoPropuestasException {

    	List<Iniciativa> prueba = banco.consultarIniciativas();
    	
    	
    	assertEquals(prueba.size(),4);
		
    	
    	
    }
 
}
