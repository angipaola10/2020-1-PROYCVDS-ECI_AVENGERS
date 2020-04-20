package edu.eci.cvds.persistence;

import java.util.List;
import java.sql.Date;
import java.lang.String;
import edu.eci.cvds.entities.Iniciativa; 


public interface IniciativaDAO {

	public List<Iniciativa> consularIniciativas()  throws PersistenceException;

	public Iniciativa consultarIniciativa(String correo) throws PersistenceException;
	
	public void agregarIniciativa(String nombre, String descripcion, Date fechaInicio, String area, String usuario, String estadoPropuesta) throws PersistenceException;
	
}
