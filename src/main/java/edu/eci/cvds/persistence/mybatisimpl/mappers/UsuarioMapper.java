package edu.eci.cvds.persistence.mybatisimpl.mappers;

import java.util.List;

import edu.eci.cvds.entities.Rol;
import edu.eci.cvds.entities.Usuario;
import org.apache.ibatis.annotations.Param;

public interface UsuarioMapper {
    
    public Usuario consultarUsuario(@Param ("correoU") String correo);
	public List<Usuario> consultarUsuarios();
	public Usuario consultarUsuarioLog(@Param("correoU") String correo,  @Param("claveU") String clave);
	public void modificarUsuario(int rol, String correo);
	public void insertarUsuario(int id, String tid, String nombre, int telefono, String correo, String clave, Rol rol, String estado);
    
}

