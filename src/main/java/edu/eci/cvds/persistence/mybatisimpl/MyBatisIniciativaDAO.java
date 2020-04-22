package edu.eci.cvds.persistence.mybatisimpl;

import java.sql.Date;
import java.util.*;
import java.lang.String;
import com.google.inject.Inject;
import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.PalabraClave;
import edu.eci.cvds.entities.Reporte;
import edu.eci.cvds.persistence.mybatisimpl.mappers.IniciativaMapper;
import edu.eci.cvds.persistence.IniciativaDAO;
import edu.eci.cvds.persistence.PersistenceException;

public class MyBatisIniciativaDAO implements IniciativaDAO {
	
	@Inject
	IniciativaMapper iniciativaMapper;

	@Override
	public List<Iniciativa> consularIniciativas() throws PersistenceException {
        try{
            System.out.println("hola DAO  ini");
            return iniciativaMapper.consultarTodasLasPropuestas();
         }
         catch(Exception e){
             System.out.println("hola Dao ini");
             System.out.println(e);
             throw new PersistenceException("Error al consultar las iniciativas:",e);     
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
	public Iniciativa consultarIniciativa(String correo) throws PersistenceException {
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
		try{
			System.out.println("Registrando p clave "+palabraClave+" DAO");
			iniciativaMapper.insertarPalabraClave(palabraClave);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al agregar la palabra clave:",e);   
         }
	}
	
	/*
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
	public List<PalabraClave> consultarPalabrasClave(String id_iniciativa) throws PersistenceException {
		try{
			return iniciativaMapper.consultarPalabrasClaveInciativa(id_iniciativa);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al consultar las plabaras clave de esta iniciativa:",e);   
         }
	}
	*/
	
	@Override
	public List<PalabraClave> consultarPalabrasClaves() throws PersistenceException {
		try{
			System.out.println("consultando palabras clave DAO");
			return iniciativaMapper.consultarTodasLasPalabrasClaves();
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
        	 System.out.println("consultando palabras clave DAO excepcion");
        	 System.out.println(e.getMessage());
             throw new PersistenceException("Error al consultar todas las plabaras clave:",e);   
         }
	}
	
	@Override
    public void actualizarIniciativaEstado(String estado, String nombre) throws PersistenceException {
        try{
        	System.out.println("Modificando estado iniciativa "+nombre+" "+estado+" DAO");
             iniciativaMapper.modificarIniciativaEstado(estado, nombre);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
        	 System.out.println("Modificando estado iniciativa "+nombre+" "+estado+" DAO excepcion");
        	 System.out.println(e.getMessage());
             throw new PersistenceException("Error al actualizar el estado de la iniciativa:",e); 
         }	
    }
	
	
}

