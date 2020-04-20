package edu.eci.cvds.managedbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.Rol;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.entities.Estado;
import edu.eci.cvds.services.BancoPropuestas;
import edu.eci.cvds.services.BancoPropuestasException;
import java.util.List;
import java.math.BigInteger;
import java.sql.Date;

@SuppressWarnings("serial")
@ManagedBean(name="BancoPropuestasBean")
@ApplicationScoped
public class BancoPropuestasBean extends BasePageBean {

    private final String[] estadosIniciativas = {"En espera de revisión", "En revisión", "Proyecto", "Solucionado"};
    private Rol[] roles = Rol.values();
    
    @Inject
    private BancoPropuestas bancoPropuesta;
	
    private Usuario usuario;
    
	private Iniciativa iniciativa;

    public List<Usuario> consultarUsuarios(){
        List<Usuario> clientes = null;
        try{
            clientes=bancoPropuesta.consultarUsuarios();
            System.out.println("hola");
            
        } catch (BancoPropuestasException e) {
            setErrorMessage(e);
            System.out.println("hola2");
        }
        System.out.println(clientes);
        return clientes;
    }
	
    public Usuario consultarUsuario(String correo){
        Usuario cliente=null;
        try {
            cliente=bancoPropuesta.consultarUsuario(correo);
        } catch (Exception e) {
            setErrorMessage(e);
        }
        return cliente;
    }
	
	public Usuario consultarUsuarioLog(String correo, String clave){
        Usuario cliente=null;
        try {
            cliente=bancoPropuesta.consultarUsuarioLog(correo, clave);
        } catch (Exception e) {
            setErrorMessage(e);
        }
        return cliente;
    }
	
	public void modificarUsuario(String rol, String correo, Date fechaInicio){
        try{
            bancoPropuesta.modificarUsuario(rol, correo);
        } catch (Exception e) {
            setErrorMessage(e);
        }
    }
	
	public void modificarUsuarioEstado(Estado estado, String correo, Date fechaInicio){
        try{
            bancoPropuesta.modificarUsuarioEstado(estado, correo);
        } catch (Exception e) {
            setErrorMessage(e);
        }
    }
	
    public void registrarIniciativa(String nombre, String descripcion, Date fechaInicio, String area, String usuario, String estado){
        try{
            bancoPropuesta.registrarIniciativa(nombre, descripcion, fechaInicio, area, usuario, estado);
        } catch (Exception e) {
            setErrorMessage(e);
        }
    }
	
	public void registrarUsuario(int id, String tid, String nombre, BigInteger telefono, String correo, String clave, Rol rol, String estado){
        try{
            bancoPropuesta.registrarUsuario(id, tid, nombre, telefono, correo, clave, rol, estado);
        } catch (Exception e) {
            setErrorMessage(e);
        }
    }

    public void setSelectedUsuario(Usuario usuario){this.usuario = usuario;}

    public Usuario GetUsuario(){
        return usuario;
    }
    
    public void setSelectedIniciativa(Iniciativa iniciativa){this.iniciativa = iniciativa;}

    public Iniciativa GetIniciativa(){
        return iniciativa;
    }
    
    public List<Iniciativa> consultarIniciativas(){
        List<Iniciativa> iniciativas = null;
        try{
            iniciativas=bancoPropuesta.consultarIniciativas();
            System.out.println("hola INI");
        } catch (BancoPropuestasException e) {
            setErrorMessage(e);
            System.out.println("hola ini error");
        }
        System.out.println(iniciativas);
        return iniciativas;
        }
    
    public Iniciativa consultarIniciativa(String correo){
        Iniciativa ini=null;
        try {
            ini=bancoPropuesta.consultarIniciativa(correo);
        } catch (Exception e) {
            setErrorMessage(e);
        }
        return ini;
    }
    
    private void setErrorMessage(Exception e){
        String message = e.getMessage();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

    public Rol[] getRoles() {
        return roles;
    }

    public void setRoles(Rol[] roles1) {

    }

    public String[] getEstadosIniciativas() {
        return estadosIniciativas;
    }

    public void setEstadosIniciativas (String[] estadosIni) {

    }

}