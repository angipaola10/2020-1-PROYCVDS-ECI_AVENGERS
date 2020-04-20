package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.sql.Date;
import java.util.List;
import java.lang.String;
import edu.eci.cvds.entities.Iniciativa;
import org.apache.ibatis.annotations.Param;

public interface IniciativaMapper {

	public List<Iniciativa> consultarTodasLasPropuestas();
	
	public Iniciativa consultarPropuestaPorUsuario(@Param("correo") String correo);
	
	public void insertarIniciativa(@Param("nombrePropuesta") String nombre, @Param("descripcion") String descripcion, @Param("fechaInicio") Date fechaInicio, @Param("area") String area, @Param("usuario") String usuario, @Param("estadoPropuesta") String estadoPropuesta);

}
