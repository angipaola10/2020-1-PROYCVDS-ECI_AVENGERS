package edu.eci.cvds.persistence;

import java.util.List;
import java.sql.Date;
import java.lang.String;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.entities.Iniciativa; 
import edu.eci.cvds.entities.PalabraClave;
import edu.eci.cvds.entities.Estado;
import edu.eci.cvds.services.BancoPropuestasException;

public interface IniciativaDAO {

	public List<Iniciativa> ConsularIniciativas() throws BancoPropuestasException;

	Iniciativa ConsultarIniciativa(int id) throws BancoPropuestasException;
	
}
