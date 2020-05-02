package edu.eci.cvds.persistence.mybatisimpl;

import java.sql.Date;
import java.util.*;
import java.lang.String;
import com.google.inject.Inject;

import edu.eci.cvds.entities.Estado;
import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.Likes;
import edu.eci.cvds.entities.PalabraClave;
import edu.eci.cvds.entities.Reporte;
import edu.eci.cvds.persistence.mybatisimpl.mappers.IniciativaMapper;
import edu.eci.cvds.persistence.IniciativaDAO;
import edu.eci.cvds.persistence.PersistenceException;

public class MyBatisIniciativaDAO implements IniciativaDAO {
	
	@Inject
	IniciativaMapper iniciativaMapper;
	
	@Override
	public void modificarPropuesta(String nombrePropuesta, String descripcion, String area, String usuario)throws PersistenceException {
        try{
            iniciativaMapper.modificarPropuesta(nombrePropuesta, descripcion, area, usuario);
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
	public List<Iniciativa> consultarEstadoIniciativas(String estado_Propuesta) throws PersistenceException {
        try{
            return iniciativaMapper.consultarPropuestaPorEstado(estado_Propuesta);
         }
         catch(Exception e){
             System.out.println(e);
             throw new PersistenceException("Error al consultar los estados de las iniciativas:",e);     
         }	
    }
	
	@Override
	public void agregarIniciativa(String nombre, String descripcion, String area, String usuario) throws PersistenceException {
		try{
			System.out.println("registrando iniciativa DAO");
			iniciativaMapper.insertarIniciativa(nombre, descripcion,(Date) null, area, usuario, "En espera de revisión");
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
        		System.out.println(e.getMessage());
             throw new PersistenceException("Error al actualizar el registrar esta iniciativa:",e);   
         }	
    }
	
	@Override
	public List<Reporte> agruparIniciativas() throws PersistenceException {
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
	public List<Estado> consultarEstados() throws PersistenceException {
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
	public Likes consultarLikes(int id) throws PersistenceException {
		Likes likes;
		try{	 
			likes = iniciativaMapper.consultarLikes(id);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al agrupar las propuestas:",e);   
         }
		return likes;
	}

	@Override
	public void comentar(int id, String user, String comentario) throws PersistenceException {
		try{
			iniciativaMapper.comentar(id,user,comentario);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al insrterar el comentario:",e);   
         }
	}	
}