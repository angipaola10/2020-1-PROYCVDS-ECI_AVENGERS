package edu.eci.cvds.persistence;

import edu.eci.cvds.entities.Rol;
import edu.eci.cvds.entities.Usuario;
import java.util.*;
import java.lang.String;
import java.math.BigInteger;

public interface UsuarioDAO {
	
	
	
	public List<Usuario> obtenerUsuarios() throws PersistenceException;


    public Usuario obtenerUsuario(String correo) throws PersistenceException;
	
	
	public Usuario obtenerUsuarioLog(String correo, String clave) throws PersistenceException;
	
	
	public void actualizarUsuario(String rol, String correo) throws PersistenceException;
	
	
	public void agregarUsuario(int id, String tid, String nombre, BigInteger  telefono, String correo, String clave, Rol rol, String estado) throws PersistenceException;


	public void actualizarUsuarioEstado(String estado, String correo) throws PersistenceException;



}