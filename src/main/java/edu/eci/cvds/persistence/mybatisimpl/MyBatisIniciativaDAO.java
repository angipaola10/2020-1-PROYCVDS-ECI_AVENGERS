package edu.eci.cvds.persistence.mybatisimpl;

import java.sql.Date;
import java.util.*;
import java.lang.String;
import com.google.inject.Inject;
import edu.eci.cvds.entities.Iniciativa;
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
	public void agregarIniciativa(String nombre, String descripcion, Date fechaInicio, String area, String usuario, String estadoPropuesta) throws PersistenceException {
		try{
			iniciativaMapper.insertarIniciativa(nombre, descripcion, fechaInicio, area, usuario, "En espera de revisión");
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("Error al actualizar el registrar esta iniciativa:",e);   
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
	
}

