package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.sql.Date;
import java.util.List;
import java.lang.String;
import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.Estado;
import edu.eci.cvds.entities.PalabraClave;
import edu.eci.cvds.entities.Reporte;

import org.apache.ibatis.annotations.Param;

public interface IniciativaMapper {

	public List<Iniciativa> consultarTodasLasPropuestas();
	
	public Iniciativa consultarPropuestaPorUsuario(@Param("correo") String correo);
	
	public Iniciativa consultarPropuestaPorArea(@Param("area") String area);
	
	public List<Reporte> agruparPropuestaPorArea();
	
	public void insertarIniciativa(@Param("nombrePropuesta") String nombre, @Param("descripcion") String descripcion, @Param("fechaInicio") Date fechaInicio, @Param("area") String area, @Param("usuario") String usuario, @Param("estadoPropuesta") Estado estadoPropuesta);

	public void insertarPalabraClave(@Param("palabraClave") String palabraClave);
	
	public void insertarPCIniciativa();
	
	public List<PalabraClave> consultarPalabrasClaveInciativa(@Param("id") String id_iniciativa);
	
	public List<PalabraClave> consultarTodasLasPalabrasClaves();
	
	public void modificarIniciativaEstado(@Param("estado") String estado, @Param("correo") String correo);


}
