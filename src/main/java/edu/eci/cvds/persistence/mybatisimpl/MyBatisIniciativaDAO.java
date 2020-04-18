package edu.eci.cvds.persistence.mybatisimpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.lang.String;
import com.google.inject.Inject;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.PalabraClave;
import edu.eci.cvds.entities.Estado;
import edu.eci.cvds.persistence.mybatisimpl.mappers.IniciativaMapper;
import edu.eci.cvds.persistence.IniciativaDAO;
import edu.eci.cvds.services.BancoPropuestasException;


public class MyBatisIniciativaDAO implements IniciativaDAO {
	
	@Inject
	IniciativaMapper iniciativaMapper;

	
	
	

	@Override
	public List<Iniciativa> ConsularIniciativas() throws BancoPropuestasException {
		return iniciativaMapper.loadAll();
	} 

	@Override
	public Iniciativa ConsultarIniciativa(int id) throws BancoPropuestasException {
		// TODO Auto-generated method stub
		return null;
	}

	
		
}

	
