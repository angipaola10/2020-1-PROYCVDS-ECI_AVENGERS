package edu.eci.cvds.persistence.mybatisimpl;

import com.google.inject.Inject;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.*;
import edu.eci.cvds.persistence.mybatisimpl.mappers.*;

public class MyBatisProponenteDAO implements ProponenteDAO{
	
    @Inject
    private ProponenteMapper proponenteMapper;

	@Override
	public void registrar(String descripcion) throws PersistenceException {
        try{
        	proponenteMapper.registrar(descripcion);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            
            throw new PersistenceException("Error al registrar la iniciativa:",e);
            
        }
	}

}
