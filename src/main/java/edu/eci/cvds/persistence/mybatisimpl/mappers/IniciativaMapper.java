package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.lang.String;
import org.apache.ibatis.annotations.Param;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.PalabraClave;
import edu.eci.cvds.entities.Estado;

public interface IniciativaMapper {
	
	/*public void insertInitiative(@Param("nombreIniciativa")String nombreIniciativa, 
			@Param("description")String description, 
			@Param("area")String area,
			@Param("fechaInicio")LocalDate creationDate, 
			@Param("idus")int idus, 
			@Param("estado")String estado);
	*/
			
	//public void insertKeyword(@Param("keyword") String keyword);
	
	//public void insertWordInitiative();
	
	//public void insertWordInitiativeId(@Param("id") int id);

	//public void modifyInitiative(@Param("newStatus")String newStatus,@Param("name") String name);

	public List<Iniciativa> loadAll();

	//public List<Estado> listStatus();

	//public List<PalabraClave> listKeywords();
	
	//public List<PalabraClave> loadKeywordInitiative(@Param("id")int id);

	//public void modifyAllInitiative(@Param("id")int id,@Param("area") String area,@Param("description") String description,@Param("nombre")String nombre);
	

}