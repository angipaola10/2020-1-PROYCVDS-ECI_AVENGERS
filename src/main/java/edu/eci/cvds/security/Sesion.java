package edu.eci.cvds.security;

import edu.eci.cvds.services.BancoPropuestasException;

public interface Sesion {
    public void login(int id, String clave) throws BancoPropuestasException;
    public boolean estaLogeado();
}
