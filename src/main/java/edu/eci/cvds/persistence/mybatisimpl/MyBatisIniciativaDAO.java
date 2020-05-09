package edu.eci.cvds.persistence.mybatisimpl;

import java.sql.Date;
import java.util.*;
import java.lang.String;
import com.google.inject.Inject;

import edu.eci.cvds.entities.Comentario;
import edu.eci.cvds.entities.Grupo;
import edu.eci.cvds.entities.ReporteEstado;
import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.MeGusta;
import edu.eci.cvds.entities.MeInteresa;
import edu.eci.cvds.entities.PalabraClave;
import edu.eci.cvds.entities.ReporteArea;
import edu.eci.cvds.persistence.mybatisimpl.mappers.IniciativaMapper;
import edu.eci.cvds.persistence.IniciativaDAO;
import edu.eci.cvds.persistence.PersistenceException;

public class MyBatisIniciativaDAO implements IniciativaDAO {
	
	@Inject
	IniciativaMapper iniciativaMapper;
	
	@Override
	public void modificarPropuesta(String nombrePropuesta, String descripcion, String area, int id)throws PersistenceException {
        try{
            iniciativaMapper.modificarPropuesta(nombrePropuesta, descripcion, area, id);
         }
         catch(Exception e){
             System.out.println(e);
             throw new PersistenceException("Error al modificar la iniciativa:",e);     
         }	
    }

	@Override
	public List<Iniciativa> consularIniciativas() throws PersistenceException {
        try{
            return iniciativaMapper.consultarTodasLasPropuestas();
         }
         catch(Exception e){
             System.out.println(e);
             throw new PersistenceException("Error al consultar las iniciativas:",e);     
         }	
    }
	
	@Override
	public void agregarIniciativa(String nombre, String descripcion, String area, String usuario) throws PersistenceException {
		try{
			iniciativaMapper.insertarIniciativa(nombre, descripcion,(Date) null, area, usuario, "En espera de revisión");
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
        		System.out.println(e.getMessage());
             throw new PersistenceException("Error al actualizar el registrar esta iniciativa:",e);   
         }	
    }
	
	@Override
	public List<ReporteArea> agruparIniciativas() throws PersistenceException {
		try{
			return iniciativaMapper.agruparPropuestaPorArea();
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al agrupar las propuestas:",e);   
         }	
    }
	
	@Override
	public List<Iniciativa> consultarIniciativa(String correo) throws PersistenceException {
        try{
        	return iniciativaMapper.consultarPropuestaPorUsuario(correo);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al consultar esta iniciativa:",e);  
         }	
    }
	
	@Override
	public Iniciativa consultarIniciativaArea(String area) throws PersistenceException {
        try{
        	return iniciativaMapper.consultarPropuestaPorArea(area);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al consultar esta area:",e);  
         }	
    }
	
	
	@Override
	public void agregarPalabraClave(String palabraClave) throws PersistenceException {
		try{;
			iniciativaMapper.insertarPalabraClave(palabraClave);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al agregar la palabra clave:",e);   
         }
	}

	@Override
	public void agregarPCIniciativa(int idIniciativa, int idPClave) throws PersistenceException {
		try{
			iniciativaMapper.insertarPCIniciativa(idIniciativa, idPClave);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al agregar el id de la palabra clave en la tabla intermedia:",e);   
         }
	}

	@Override
	public List<PalabraClave> consultarPalabrasClave(int id_iniciativa) throws PersistenceException {
		try{
			return iniciativaMapper.consultarPalabrasClaveInciativa(id_iniciativa);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al consultar las plabaras clave de esta iniciativa:",e);   
         }
	}
	
	@Override
	public List<PalabraClave> consultarPalabrasClaves() throws PersistenceException {
		try{
			return iniciativaMapper.consultarTodasLasPalabrasClaves();
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al consultar todas las plabaras clave:",e);   
         }
	}
	
	@Override
    public void actualizarIniciativaEstado(String estado, String nombre) throws PersistenceException {
        try{
             iniciativaMapper.modificarIniciativaEstado(estado, nombre);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
        	 System.out.println(e.getMessage());
             throw new PersistenceException("Error al actualizar el estado de la iniciativa:",e); 
         }	
    }

	@Override
	public Iniciativa consultarIdIniciativa(String nombrePropuesta) throws PersistenceException {
		try {
			return iniciativaMapper.consultarIdIniciativa(nombrePropuesta);
		}catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al actualizar el estado de la iniciativa:",e); 
        }	
			
	}

	@Override
	public List<Iniciativa> consultarIniciativaPalabraClave(String palabra) throws PersistenceException {
		try {
			return iniciativaMapper.consultarIniciativaPalabraClave(palabra);
		}catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al actualizar el estado de la iniciativa:",e); 
        }	
			
	}

	@Override
	public List<ReporteEstado> consultarEstados() throws PersistenceException {
		try{
			return iniciativaMapper.consultarEstados();
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al agrupar las propuestas:",e);   
         }	
	}

	@Override
	public void darLike(int id,String user) throws PersistenceException {
		try{
			iniciativaMapper.darLike(id,user);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al agrupar las propuestas:",e);   
         }
		
	}

	@Override
	public List<MeGusta> consultarLikes(int id) throws PersistenceException {
		List<MeGusta> likes;
		try{	 
			likes = iniciativaMapper.consultarLikes(id);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al consultar likes:",e);   
         }
		return likes;
	}

	@Override
	public void comentar(int id, String user, String comentario) throws PersistenceException {
		try{
			iniciativaMapper.comentar(id,user,comentario);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al insertar el comentario:",e);   
         }
	}

	@Override
	public List<Comentario> consultarComentarios(int id) throws PersistenceException {
		List<Comentario> likes;
		try{	 
			likes = iniciativaMapper.consultarComentarios(id);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al consultar comentarios:",e);   
         }
		return likes;
	}

	@Override
	public List<MeGusta> consultarLikePorIds(int idiniciativa, String idusuario) throws PersistenceException {
		List<MeGusta> likes;
		try{	 
			likes = iniciativaMapper.consultarLikePorIds(idiniciativa, idusuario);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
        	 System.out.println("muere en myBatisIniciativaDAO");
             throw new PersistenceException("Error al consultar likes: ",e);   
         }
		return likes;
	}

	@Override
	public void quitarLike(int idiniciativa, String idusuario) throws PersistenceException {
		try{	 
			iniciativaMapper.quitarLike(idiniciativa, idusuario);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
        	 System.out.println(e.getMessage());
             throw new PersistenceException("Error al quitar like: ",e);   
         }
	}

	@Override
	public List<MeInteresa> consultarInteresPorIds(int idiniciativa, String idusuario) throws PersistenceException {
		List<MeInteresa> interes;
		try{	 
			interes = iniciativaMapper.consultarInteresPorIds(idiniciativa, idusuario);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
        	 System.out.println("muere en myBatisIniciativaDAO");
             throw new PersistenceException("Error al consultar interes: ",e);   
         }
		return interes;
	}

	@Override
	public List<MeInteresa> consultarInteres(int id) throws PersistenceException {
		List<MeInteresa> interes;
		try{	 
			interes = iniciativaMapper.consultarInteres(id);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al consultar interes:",e);   
         }
		return interes;
	}

	@Override
	public void quitarInteres(int idiniciativa, String user) throws PersistenceException {
		try{	 
			iniciativaMapper.quitarInteres(idiniciativa, user);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
        	 System.out.println(e.getMessage());
             throw new PersistenceException("Error al quitar interes: ",e);   
         }
		
	}

	@Override
	public void darInteres(int idiniciativa, String user) throws PersistenceException {
		try{
			iniciativaMapper.darInteres(idiniciativa,user);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al dar interes:",e);   
         }
	}

	@Override
	public void agruparIniciativa(String grupo, int inisagrupar) throws PersistenceException {
		try{
			
				System.out.println(inisagrupar);
			iniciativaMapper.agruparIniciativa(grupo,inisagrupar);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException(e);   
         }
		
	}

	@Override
	public Grupo consultarGrupo(int id) throws PersistenceException {
		Grupo res;
		try{	 
			res = iniciativaMapper.consultarGrupo(id);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al consultar grupo:",e);   
         }
		return res;
	}

	@Override
	public List<Iniciativa> consultarInisAgru(int id , String grupo) throws PersistenceException {
		List<Iniciativa> res;
		try{	 
			res = iniciativaMapper.consultarInisAgru(id,grupo);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al consultar grupo:",e);   
         }
		return res;
	}
	
}