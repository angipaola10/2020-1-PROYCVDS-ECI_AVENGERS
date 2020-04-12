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


public class MyBatisIniciativaDAO implements IniciativaDAO{
	
	@Inject
	IniciativaMapper iniciativaMapper;

	public void addInitiative(String nombreIniciativa, String description, String area, Date fechaInicio, int idus, String estado) throws BancoPropuestasException{
		try {
			LocalDate creationDate = LocalDate.now();
			
			iniciativaMapper.insertInitiative(nombreIniciativa, description, area, fechaInicio, idus, "En espera");
			List<PalabraClave> kw= iniciativaMapper.listKeywords();
			/*
			 * Se revisará si ya existe la palabra clave, para no tener datos duplicados en la BD.
			 */
			int idKw ;
			for (String k: keywords) {
				idKw= -1;
				for (PalabraClave w: kw) {
					if(w.getPalabraClave().equals(k)) {idKw= w.getId(); break;}}
				if (idKw == -1) {
					iniciativaMapper.insertKeyword(k);
					iniciativaMapper.insertWordInitiative();
				}
				else {
					iniciativaMapper.insertWordInitiativeId(idKw);
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new BancoPropuestasException("Ocurrio un error al insertar");
		}
	}

	@Override
	public void modifyInitiative(String newStatus, String name) throws BancoPropuestasException {
		iniciativaMapper.modifyInitiative(newStatus,name);
		
	}

	@Override
	public List<Iniciativa> loadAll() throws BancoPropuestasException {
		return iniciativaMapper.loadAll();
	}

	@Override
	public List<Estado> listStatus() throws BancoPropuestasException {
		return iniciativaMapper.listStatus();
	}

	@Override
	public List<PalabraClave> listKeywords() throws BancoPropuestasException {
		return iniciativaMapper.listKeywords();
	}
	
}