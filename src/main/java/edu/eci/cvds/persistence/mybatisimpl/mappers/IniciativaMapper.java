package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.sql.Date;
import java.util.List;
import java.lang.String;

import edu.eci.cvds.entities.Comentario;
import edu.eci.cvds.entities.ReporteEstado;
import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.MeGusta;
import edu.eci.cvds.entities.MeInteresa;
import edu.eci.cvds.entities.PalabraClave;
import edu.eci.cvds.entities.ReporteArea;

import org.apache.ibatis.annotations.Param;

public interface IniciativaMapper {
	
	public void modificarPropuesta(@Param("nombrePropuesta") String nombrePropuesta, @Param("descripcion") String descripcion, @Param("area") String area, @Param("id") int id);

	public List<Iniciativa> consultarTodasLasPropuestas();
	
	public List<Iniciativa> consultarPropuestaPorUsuario(@Param("correo") String correo);
	
	public Iniciativa consultarPropuestaPorArea(@Param("area") String area);
	
	public List<ReporteArea> agruparPropuestaPorArea();
	
	public void insertarIniciativa(@Param("nombrePropuesta") String nombre, @Param("descripcion") String descripcion, @Param("fechaInicio") Date fechaInicio, @Param("area") String area, @Param("usuario") String usuario, @Param("estadoPropuesta") String estadoPropuesta);

	public void insertarPalabraClave(@Param("palabraClave") String palabraClave);
	
	public void insertarPCIniciativa(@Param("ini_id") int ini_id, @Param("palabras_clave") int palabras_clave);
	
	public Iniciativa consultarIdIniciativa(@Param("nombrePropuesta") String nombrePropuesta);
	
	public List<PalabraClave> consultarPalabrasClaveInciativa(@Param("ini_id") int id_iniciativa);
	
	public List<PalabraClave> consultarTodasLasPalabrasClaves();
	
	public void modificarIniciativaEstado(@Param("estado_Propuesta") String estado, @Param("nombrePropuesta") String nombre);

	public List<Iniciativa> consultarIniciativaPalabraClave(@Param("palabra")String palabra);

	public List<ReporteEstado> consultarEstados();

	public void darLike(@Param("id_ini") int id, @Param("id_usu") String user);

	public List<MeGusta> consultarLikes(@Param("id_inic")int id);
	
	public List<Comentario> consultarComentarios(@Param("id_inic")int id);

	public void comentar(@Param("id_ini") int id,@Param("id_usu") String user, @Param("coment") String comentario);

	public List<MeGusta> consultarLikePorIds(@Param("idiniciativa") int idiniciativa, @Param("idusuario") String idusuario);

	public void quitarLike(@Param("idiniciativa") int idiniciativa,@Param("idusuario") String idusuario);

	public void quitarInteres(@Param("idiniciativa") int idiniciativa,@Param("idusuario") String idusuario);

	public List<MeInteresa> consultarInteres(@Param("id_inic")int id);

	public List<MeInteresa> consultarInteresPorIds(@Param("idiniciativa") int idiniciativa, @Param("idusuario") String idusuario);

	public void darInteres(@Param("id_ini") int id, @Param("id_usu") String user);

	public void agruparIniciativa(@Param("grup_nombre")String grupo, @Param("ini_id")int id);

	public String consultarGrupo(@Param("ini_id")int id);
}
