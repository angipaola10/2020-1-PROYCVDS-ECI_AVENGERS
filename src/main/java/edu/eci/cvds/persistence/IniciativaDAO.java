package edu.eci.cvds.persistence;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import java.sql.Date;
import java.lang.String;
import edu.eci.cvds.entities.Comentario;
import edu.eci.cvds.entities.Grupo;
import edu.eci.cvds.entities.ReporteEstado;
import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.MeGusta;
import edu.eci.cvds.entities.MeInteresa;
import edu.eci.cvds.entities.PalabraClave;
import edu.eci.cvds.entities.ReporteArea; 

public interface IniciativaDAO {
	
	public void modificarPropuesta(String nombrePropuesta, String descripcion, String area, int id) throws PersistenceException;

	public List<Iniciativa> consularIniciativas() throws PersistenceException;

	public List<Iniciativa> consultarIniciativa(String correo) throws PersistenceException;
	
	public Iniciativa consultarIniciativaArea(String area) throws PersistenceException;
	
	public void agregarIniciativa(String nombre, String descripcion, String area, String usuario) throws PersistenceException;
	
	public List<ReporteArea>  agruparIniciativas() throws PersistenceException;
	
	public void agregarPalabraClave(String palabraClave) throws PersistenceException;
	
	public void agregarPCIniciativa(int idIniciativa, int idPClave) throws PersistenceException;
	
	public List<PalabraClave> consultarPalabrasClaves() throws PersistenceException;
	
	public void actualizarIniciativaEstado(String estado, String correo) throws PersistenceException;

	public Iniciativa consultarIdIniciativa(String nombrePropuesta) throws PersistenceException;

	public List<PalabraClave> consultarPalabrasClave(int id_iniciativa) throws PersistenceException;

	public List<Iniciativa> consultarIniciativaPalabraClave(String palabra) throws PersistenceException;
	
	public List<ReporteEstado> consultarEstados() throws PersistenceException;

	public void darLike(int id, String user) throws PersistenceException ;

	public List<MeGusta> consultarLikes(int id)  throws PersistenceException;

	public void comentar(int id, String user, String comentario)  throws PersistenceException;

	public List<Comentario> consultarComentarios(int id) throws PersistenceException;

	public List<MeGusta> consultarLikePorIds(int idiniciativa, String idusuario) throws PersistenceException;

	public void quitarLike(int idiniciativa, String idusuario) throws PersistenceException;

	public List<MeInteresa> consultarInteresPorIds(int idiniciativa, String idusuario) throws PersistenceException;

	public List<MeInteresa> consultarInteres(int id) throws PersistenceException;

	public void quitarInteres(int idiniciativa, String user)throws PersistenceException;

	public void darInteres(int idiniciativa, String user)throws PersistenceException;

	public void agruparIniciativa(String grupo, int inisagrupar) throws PersistenceException;

	public List<Grupo> consultarGrupo(int id) throws PersistenceException ;

	public List<Iniciativa> consultarInisAgru(int id, String grupo) throws PersistenceException;

	public Iniciativa consultarIniciativaPorId(int ini) throws PersistenceException;


}
