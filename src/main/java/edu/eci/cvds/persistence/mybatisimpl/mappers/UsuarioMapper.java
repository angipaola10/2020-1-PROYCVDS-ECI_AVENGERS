package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.math.BigInteger;
import java.util.List;

import edu.eci.cvds.entities.Rol;
import edu.eci.cvds.entities.Usuario;
import org.apache.ibatis.annotations.Param;

public interface UsuarioMapper {
    
    public Usuario consultarUsuario(@Param ("correoU") String correo);
	public List<Usuario> consultarUsuarios();
	public Usuario consultarUsuarioLog(@Param("correoU") String correo,  @Param("claveU") String clave);
	public void modificarUsuario(@Param("rol")int rol, @Param("correoU") String correo);
	public void insertarUsuario(@Param("id")int id, @Param("tid") String tid, @Param("nombre") String nombre, @Param("telefono") BigInteger telefono, @Param("correo") String correo, @Param("clave") String clave, @Param("rol") Rol rol, @Param("estado") String estado);
    
}

