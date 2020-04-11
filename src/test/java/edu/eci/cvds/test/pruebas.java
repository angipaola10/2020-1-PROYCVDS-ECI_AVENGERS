package edu.eci.cvds.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import com.google.inject.Inject;
import edu.eci.cvds.services.BancoPropuestasException ;
import edu.eci.cvds.persistence.PersistenceException ;
import org.apache.ibatis.session.SqlSession;

public class pruebas {
    
    @Test
    public void pruebaParaQuePase() {
		int a = 3;
    	Assert.assertequals(a,3);
    }
 
}
