package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.util.List;
import edu.eci.cvds.entities.Usuario;
import org.apache.ibatis.annotations.Param;


public interface UsuarioMapper {
    
    public Usuario consultarUsuario(@Param ("idU") int id);
    
}

