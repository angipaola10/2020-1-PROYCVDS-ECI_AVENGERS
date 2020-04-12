package edu.eci.cvds.persistence.mybatisimpl.mappers;

import org.apache.ibatis.annotations.Param;

public interface ProponenteMapper {

	public void registrar(@Param("descrip") String descripcion);
	
}