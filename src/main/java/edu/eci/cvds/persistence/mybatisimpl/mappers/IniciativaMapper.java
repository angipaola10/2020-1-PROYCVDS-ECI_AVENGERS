package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.lang.String;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.PalabraClave;
import edu.eci.cvds.entities.Estado;
import org.apache.ibatis.annotations.Param;

public interface IniciativaMapper {

	public List<Iniciativa> consultarTodasLasPropuestas();
	
	public Iniciativa consultarPropuestaPorUsuario(@Param("correo") String correo);
	
	public void insertarIniciativa(@Param("nombrePropuesta") String nombre, @Param("descripcion") String descripcion, @Param("fechaInicio") Date fechaInicio, @Param("area") String area, @Param("usuario") String usuario, @Param("estadoPropuesta") Estado estadoPropuesta);

}
