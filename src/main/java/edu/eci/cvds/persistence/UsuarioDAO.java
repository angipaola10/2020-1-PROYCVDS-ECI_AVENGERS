/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.persistence;

import edu.eci.cvds.entities.Estado;
import edu.eci.cvds.entities.Rol;
import edu.eci.cvds.entities.Usuario;
import java.util.*;

import org.apache.ibatis.annotations.Param;

import java.lang.String;
import java.math.BigInteger;

public interface UsuarioDAO {
	
	public List<Usuario> obtenerUsuarios() throws PersistenceException;

    public Usuario obtenerUsuario(String correo) throws PersistenceException;
	
	public Usuario obtenerUsuarioLog(String correo, String clave) throws PersistenceException;
	
	public void actualizarUsuario(String rol, String correo) throws PersistenceException;
	
	public void agregarUsuario(int id, String tid, String nombre, BigInteger  telefono, String correo, String clave, Rol rol, String estado) throws PersistenceException;

	public void actualizarUsuarioEstado( Estado estado, String correo) throws PersistenceException;

}