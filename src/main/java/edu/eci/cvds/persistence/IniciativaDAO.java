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

	public List<Iniciativa> consularIniciativas() throws BancoPropuestasException;

	public Iniciativa consultarIniciativa(String correo) throws BancoPropuestasException;
	
	public void agregarIniciativa(String nombre, String descripcion, Date fechaInicio, String area, Usuario usuario, Estado estadoPropuesta) throws BancoPropuestasException;
	
}
