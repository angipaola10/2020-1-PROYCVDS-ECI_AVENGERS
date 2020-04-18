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
    public Usuario consultarUsuario (long docu) throws PersistenceException {
        try{
             return usuarioMapper.consultarUsuario(docu);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             
             throw new PersistenceException("Error al consultar este usuario:",e);
             
         }	
    }
	
	 @Override
    public List<Usuario> ConsultarUsuarios() throws PersistenceException {
        try{
            System.out.println("hola 111");

             return usuarioMapper.consultarUsuarios();
         }
         catch(Exception e){
             System.out.println("hola 222");

             System.out.println(e);
             throw new PersistenceException("Error al consultar los usuarios:",e);
             
         }	
    }
	
	@Override
    public Usuario obtenerUsuarioLog(long id, String clave) throws PersistenceException {
        try{
             return usuarioMapper.consultarUsuarioLog(id,clave);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             
             throw new PersistenceException("Error al consultar las credenciales del usuario:",e);
             
         }	
    }

	
	
}
