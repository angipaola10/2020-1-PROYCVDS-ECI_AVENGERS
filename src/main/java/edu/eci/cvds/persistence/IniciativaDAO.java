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
	
	
	public void addInitiative(String nombreIniciativa, String description, String area, int idus, String estado) throws BancoPropuestasException;

	public void modifyInitiative(String newStatus, String name) throws BancoPropuestasException;

	public List<Iniciativa> loadAll() throws BancoPropuestasException;

	public List<Estado> listStatus() throws BancoPropuestasException;

	public List<PalabraClave> listKeywords() throws BancoPropuestasException;

}
