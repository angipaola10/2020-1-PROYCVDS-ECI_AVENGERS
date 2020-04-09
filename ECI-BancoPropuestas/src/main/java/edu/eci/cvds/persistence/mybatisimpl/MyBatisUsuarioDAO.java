
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.persistence.mybatisimpl;


import com.google.inject.Inject;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.*;
import edu.eci.cvds.persistence.mybatisimpl.mappers.*;
import java.util.List;


public class MyBatisUsuarioDAO implements UsuarioDAO {
    @Inject
    private UsuarioMapper usuarioMapper;

    @Override
    public Usuario consultarUsuario(int id) throws PersistenceException {
        try{
             return usuarioMapper.consultarUsuario(id);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             
             throw new PersistenceException("Error al consultar este usuario:",e);
             
         }	
    }
	
	 @Override
    public List<Usuario> cargarTodosLosUsuarios() throws PersistenceException {
        try{
             return usuarioMapper.consultarUsuarios();
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             
             throw new PersistenceException("Error al consultar los usuarios:",e);
             
         }	
    }
	
	@Override
    public Usuario obtenerUsuarioLog(int id, String clave) throws PersistenceException {
        try{
             return usuarioMapper.consultarUsuarioLog();
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             
             throw new PersistenceException("Error al consultar las credenciales del usuario:",e);
             
         }	
    }

	
	
}
