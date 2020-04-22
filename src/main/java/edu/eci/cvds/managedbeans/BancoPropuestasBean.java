package edu.eci.cvds.managedbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.PalabraClave;
import edu.eci.cvds.entities.Reporte;
import edu.eci.cvds.entities.Rol;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.persistence.PersistenceException;
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
	
    private Usuario selectedUsuario;
    
    private Reporte reporte;
	private Iniciativa iniciativa;
	
	private PalabraClave palabraClave;

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
        } catch (BancoPropuestasException e) {
            setErrorMessage(e);
        }
        return cliente;
    }
	
	public Usuario consultarUsuarioLog(String correo, String clave){
        Usuario cliente=null;
        try {
            cliente=bancoPropuesta.consultarUsuarioLog(correo, clave);
        } catch (BancoPropuestasException e) {
            setErrorMessage(e);
        }
        return cliente;
    }
	
	public void modificarUsuario(String rol){
        try {
        	System.out.println("modificando perfil usuario "+selectedUsuario.getCorreo()+" "+rol);
            bancoPropuesta.modificarUsuario(rol, selectedUsuario.getCorreo());
        } catch (BancoPropuestasException e) {
            setErrorMessage(e);
        }
    }
	
	public void modificarUsuarioEstado(String estado){
        try{
            bancoPropuesta.modificarUsuarioEstado(estado, selectedUsuario.getCorreo());
        } catch (BancoPropuestasException e) {
            setErrorMessage(e);
        }
    }
		
	public void modificarIniciativaEstado(String estado){
        try{
            bancoPropuesta.modificarIniciativaEstado(estado, iniciativa.getUsuario());
        } catch (BancoPropuestasException e) {
            setErrorMessage(e);
        }
    }
	
    public void registrarIniciativa(String nombre, String descripcion, String area){
        try{
        	System.out.println("registrando iniciativa bean");
            bancoPropuesta.registrarIniciativa(nombre, descripcion, area,"angied.ruiz");
        } catch (BancoPropuestasException e) {
        	setErrorMessage(e);
        }
    }
    
    public List<Reporte> agruparIniciativas(){
    	List<Reporte> reportes = null;
        try{
            reportes = bancoPropuesta.agruparIniciativas();
        } catch (BancoPropuestasException e) {
        	setErrorMessage(e);
        }
        System.out.println(reportes);
        return reportes;
    }
	
	public void registrarUsuario(int id, String tid, String nombre, BigInteger telefono, String correo, String clave, Rol rol, String estado){
        try{
            bancoPropuesta.registrarUsuario(id, tid, nombre, telefono, correo, clave, rol, estado);
        } catch (BancoPropuestasException e) {
            setErrorMessage(e);
        }
    }

    public void setSelectedUsuario(Usuario usuario){
    	this.selectedUsuario = usuario;
    }

    public Usuario getSelectedUsuario(){
        return selectedUsuario;
    }

    public void setSelectedPalabraClave(PalabraClave palabraClave){this.palabraClave = palabraClave;}

    public PalabraClave GetPalabraClave(){
        return palabraClave;
    }
    
    public void setSelectedIniciativa(Iniciativa iniciativa){
    	this.iniciativa = iniciativa;
    }
    
    public Iniciativa getSelectedIniciativa(){
        return iniciativa;
    }
    
    public void setSelectedReporte(Reporte reporte){this.reporte = reporte;}

    public Reporte GetReporte(){
        return reporte;
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
        } catch (BancoPropuestasException e) {
            setErrorMessage(e);
        }
        return ini;
    }
    
    public Iniciativa consultarIniciativaArea(String area){
        Iniciativa ini=null;
        try {
            ini=bancoPropuesta.consultarIniciativaArea(area);
        } catch (BancoPropuestasException e) {
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

    public void setRoles(Rol[] roles1) {}

    public String[] getEstadosIniciativas() {
        return estadosIniciativas;
    }
    
    public void registrarPalabraClave(String palabraClave) throws BancoPropuestasException {
	   try {
		   bancoPropuesta.registrarPalabraClave(palabraClave);
       } catch (BancoPropuestasException e) {
    	   setErrorMessage(e);
       }
   }
	
	public void registrarPCIniciativa() throws BancoPropuestasException {
		try {
			 bancoPropuesta.registrarPCIniciativa();
	       } catch (BancoPropuestasException e) {
	    	   setErrorMessage(e);
	       }
   }
	
	public List<PalabraClave> consultarPalabraClave(String id_iniciativa) throws BancoPropuestasException {
	   List<PalabraClave> palabrasClaves = null;
       try{
    	   palabrasClaves = bancoPropuesta.consultarPalabraClave(id_iniciativa);
       } catch (BancoPropuestasException e) {
       	setErrorMessage(e);
       }
       System.out.println(palabrasClaves);
       return palabrasClaves;
   }
	
	public List<PalabraClave> consultarPalabrasClaves() throws BancoPropuestasException {
	   List<PalabraClave> palabrasClaves = null;
       try{
    	   palabrasClaves = bancoPropuesta.consultarPalabrasClaves();
       } catch (BancoPropuestasException e) {
       	setErrorMessage(e);
       }
       System.out.println(palabrasClaves);
       return palabrasClaves;
   }

    public void setEstadosIniciativas (String[] estadosIni) {}
    
    public void actualizarEstadoIniciativa(String estado) {
    	System.out.println("Actualizando iniciativa "+iniciativa.getNombrePropuesta()+" "+estado);
    }

}