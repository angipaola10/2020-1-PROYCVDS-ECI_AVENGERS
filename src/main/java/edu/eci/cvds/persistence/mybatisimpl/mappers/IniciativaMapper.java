package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.lang.String;
import org.apache.ibatis.annotations.Param;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.PalabraClave;
import edu.eci.cvds.entities.Estado;

public interface IniciativaMapper {

	public List<Iniciativa> consultarTodasLasPropuestas();
	
	public Iniciativa consultarPropuestaPorUsuario(String correo);
	
	public void insertarIniciativa(String nombre, String descripcion, Date fechaInicio, String area, Usuario usuario, Estado estadoPropuesta);

}
