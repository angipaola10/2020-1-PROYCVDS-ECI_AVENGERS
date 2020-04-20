package edu.eci.cvds.persistence.mybatisimpl;

import com.google.inject.Inject;

import edu.eci.cvds.entities.Rol;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.*;
import edu.eci.cvds.persistence.mybatisimpl.mappers.*;

import java.math.BigInteger;
import java.util.List;

public class MyBatisUsuarioDAO implements UsuarioDAO {
    @Inject
    private UsuarioMapper usuarioMapper;

    @Override
    public Usuario obtenerUsuario (String correo) throws PersistenceException {
        try{
             return usuarioMapper.consultarUsuario(correo);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             
             throw new PersistenceException("Error al consultar este usuario:",e);
             
         }	
    }
	
	 @Override
    public List<Usuario> obtenerUsuarios() throws PersistenceException {
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
    public Usuario obtenerUsuarioLog(String correo, String clave) throws PersistenceException {
        try{
             return usuarioMapper.consultarUsuarioLog(correo,clave);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             
             throw new PersistenceException("Error al consultar las credenciales del usuario:",e);
             
         }	
    }
	
	@Override
    public void actualizarUsuario(int rol, String correo)throws PersistenceException {
        try{
             usuarioMapper.modificarUsuario(rol,correo);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             
             throw new PersistenceException("Error al actualizar el rol del usuario:",e);
             
         }	
    }


	@Override
    public void agregarUsuario(int id, String tid, String nombre, BigInteger telefono, String correo, String clave, Rol rol, String estado) throws PersistenceException {
        try{
             usuarioMapper.insertarUsuario(id, tid, nombre, telefono, correo, clave, rol, estado);
         }
         catch(org.apache.ibatis.exceptions.PersistenceException e){
             
             throw new PersistenceException("Error al actualizar el registrar este usuario:",e);
             
         }	
    }
	
	
}
