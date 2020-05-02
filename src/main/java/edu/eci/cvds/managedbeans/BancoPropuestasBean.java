package edu.eci.cvds.managedbeans;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import edu.eci.cvds.entities.Estado;
import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.Likes;
import edu.eci.cvds.entities.PalabraClave;
import edu.eci.cvds.entities.Reporte;
import edu.eci.cvds.entities.Rol;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.services.BancoPropuestas;
import edu.eci.cvds.services.BancoPropuestasException;

@SuppressWarnings("serial")
@ManagedBean(name="BancoPropuestasBean")
@ApplicationScoped
public class BancoPropuestasBean extends BasePageBean {

    private final String[] estadosIniciativas = {"En espera de revisión", "En revisión", "Proyecto", "Solucionado"};
    private final String[] areasIniciativas = {"Matemáticas", "Ciencias Naturales", "Ciencias Sociales", "Artes", "Ciencias de la salud", "Economía", "Administración", "Ingeniería"};
    private final Rol[] roles = Rol.values();
    
    @Inject
    private BancoPropuestas bancoPropuesta;
    private List<PalabraClave> selectedPalabras;
    private Usuario selectedUsuario;
    private Reporte reporte;
	private Iniciativa selectedIniciativa;
	private PalabraClave palabraClave;
	private Estado estado;
	private List <Likes> likes;
	private List <Iniciativa> pcclaveini;

    public List<Usuario> consultarUsuarios(){
        List<Usuario> clientes = null;
        try{
            clientes=bancoPropuesta.consultarUsuarios();
            
        } catch (BancoPropuestasException e) {
            setErrorMessage(e);
        }
        return clientes;
    }
	
	public void registrarUsuario(int id, String tid, String nombre, BigInteger telefono, String correo, String clave, Rol rol, String estado){
        try{
            bancoPropuesta.registrarUsuario(id, tid, nombre, telefono, correo, clave, rol, estado);
        } catch (BancoPropuestasException e) {
            setErrorMessage(e);
        }
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
            bancoPropuesta.modificarUsuario(rol, selectedUsuario.getCorreo());
        } catch (BancoPropuestasException e) {
            setErrorMessage(e);

        }
    }
	
	public void modificarPropuesta(String nombrePropuesta, String descripcion, String area){
        try {
            bancoPropuesta.modificarPropuesta(nombrePropuesta, descripcion, area, selectedIniciativa.getUsuario());
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

	public void setSelectedUsuario(Usuario usuario){
		this.selectedUsuario = usuario;
	}

	public Usuario getSelectedUsuario(){
		return selectedUsuario;
	}
	
    public Rol[] getRoles() {
        return roles;
    }
	
    public void registrarIniciativa(String nombre, String descripcion, String area, String usuario){
        try{
        	
            bancoPropuesta.registrarIniciativa(nombre, descripcion, area,usuario);
            Iniciativa ini = this.consultarIdIniciativa(nombre);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Iniciativa Registrada " + ini.getNombrePropuesta() , ini.getNombrePropuesta()));
   
            for (PalabraClave p: selectedPalabras) {
            	registrarPCIniciativa(ini.getId(), p.getId());
            	System.out.println("registro "+p.getPalabraClave());
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Palabra clave: " + p.getPalabraClave(), p.getPalabraClave()));
            }
            
        } catch (Exception e) {
        	
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error"));
        	setErrorMessage(e);
        }
    }

    public List<Iniciativa> consultarIniciativas(){
        List<Iniciativa> iniciativas = null;
        try{
            iniciativas=bancoPropuesta.consultarIniciativas();
        } catch (BancoPropuestasException e) {
            setErrorMessage(e);
        }
        return iniciativas;
   }
    
    public List<Iniciativa> consultarIniciativasPorEstado(String estado_Propuesta){
            List<Iniciativa> iniciativas = null;
            try{
                iniciativas=bancoPropuesta.consultarIniciativasPorEstado(estado_Propuesta);
            } catch (BancoPropuestasException e) {
                setErrorMessage(e);
            }
            return iniciativas;
       }    
    
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }
    
    public List<Iniciativa> consultarIniciativa(String correo){
        List<Iniciativa> ini=null;
        try {
            ini=bancoPropuesta.consultarIniciativa(correo);
        } catch (BancoPropuestasException e) {
            setErrorMessage(e);
        }
        return ini;
    }
    
    public Iniciativa consultarIdIniciativa(String nombrePropuesta){
        Iniciativa ini=null;
        try {
        	ini = bancoPropuesta.consultarIdIniciativa(nombrePropuesta);
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
	public void modificarIniciativaEstado(String estado){
        try{
            bancoPropuesta.modificarIniciativaEstado(estado, selectedIniciativa.getNombrePropuesta());
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
        return reportes;
    }
    
    public List<Estado> consultarEstados(){
    	List<Estado> estados = null;
        try{
            estados = bancoPropuesta.consultarEstados();
        } catch (BancoPropuestasException e) {
        	setErrorMessage(e);
        }
        return estados;
    }

    public void setSelectedPalabraClave(PalabraClave palabraClave){this.palabraClave = palabraClave;}

    public PalabraClave GetPalabraClave(){
        return palabraClave;
    }
    
    public void setSelectedIniciativa(Iniciativa iniciativa){
    	this.selectedIniciativa = iniciativa;
    }
    
    public Iniciativa getSelectedIniciativa(){
        return selectedIniciativa;
    }
    
    public void setSelectedReporte(Reporte reporte){
    	this.reporte = reporte;
    }

    public Reporte GetReporte(){
        return reporte;
    }
    
    public void setSelectedEstado(Estado estado){
    	this.estado = estado;
    }

    public Estado GetEstado(){
        return estado;
    }
   
    
    private void setErrorMessage(Exception e){
        String message = e.getMessage();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

    public String[] getEstadosIniciativas() {
        return estadosIniciativas;
    }
    
    public String[] getAreasIniciativas() {
        return areasIniciativas;
    }

    public void registrarPalabraClave(String palabraClave) throws BancoPropuestasException {
	   try {
		   bancoPropuesta.registrarPalabraClave(palabraClave);
       } catch (BancoPropuestasException e) {
    	   setErrorMessage(e);
       }
    }
   
	public List<PalabraClave> consultarPalabrasClaves() throws BancoPropuestasException {
		   List<PalabraClave> palabrasClaves = null;
	       try{
	    	   palabrasClaves = bancoPropuesta.consultarPalabrasClaves();
	       } catch (BancoPropuestasException e) {
	    	   setErrorMessage(e);
	       }
	       return palabrasClaves;
	}
	
	public List<String> consultarPalabrasClavesString() {
		List<String> pclavesString = new ArrayList<String>();
		try {
			List<PalabraClave> pclaves = consultarPalabrasClaves();
			if(pclaves != null) {
				for(PalabraClave pc: pclaves) {
					pclavesString.add(pc.getPalabraClave());
				}
			}
		}catch (BancoPropuestasException e) {
			 setErrorMessage(e);
		}
		return pclavesString;
	}
	
	public List<PalabraClave> getSelectedPalabras() {
		return selectedPalabras;
	}

	public void setSelectedPalabras(List<PalabraClave> selectedPalabras) {
		this.selectedPalabras = selectedPalabras;
	}
	
	public void registrarPCIniciativa(int idIniciativa, int idPClave) throws BancoPropuestasException {
		try {
			 bancoPropuesta.registrarPCIniciativa(idIniciativa, idPClave);
	       } catch (BancoPropuestasException e) {
	    	   setErrorMessage(e);
	       }
	}
	
	public void darLike( Iniciativa f, String user) throws BancoPropuestasException {
		try {
			 bancoPropuesta.darLike(f.getId(),user);
	       } catch (BancoPropuestasException e) {
	    	   setErrorMessage(e);
	       }
	}
	
	public void comentar(Iniciativa f, String user, String comentario) throws BancoPropuestasException {
		try {
			System.out.println("---------------------------------------------");
			System.out.println(comentario);
			 bancoPropuesta.comentar(f.getId(),user,comentario);
	       } catch (BancoPropuestasException e) {
	    	   setErrorMessage(e);
	       }
	}
	
	public int consultarLikes( int id) throws BancoPropuestasException {
		 int likes = 0;
		try {
			System.out.println("Likes" + id);
			 likes = bancoPropuesta.consultarLikes(id).getMeGusta();
			 System.out.println(likes);
	       } catch (BancoPropuestasException e) {
	    	   setErrorMessage(e);
	       }
		 
		return likes;
	}
 
	public List<PalabraClave> consultarPalabrasClaveIniciativa() throws BancoPropuestasException {
	   List<PalabraClave> palabrasClaves = new ArrayList<PalabraClave>();
       try{
    	   if(selectedIniciativa != null) {
    		   palabrasClaves = bancoPropuesta.consultarPalabraClave(selectedIniciativa.getId());
    	   }
       } catch (BancoPropuestasException e) {
       	   setErrorMessage(e);
       }
       return palabrasClaves;
	}
	
	public void consultarIniciativaPalabraClave (String palabra) throws IOException{
		List<Iniciativa> iniciativas = null;
		try {
			iniciativas = bancoPropuesta.consultarIniciativaPalabraClave(palabra);
		} catch(BancoPropuestasException e) {
			setErrorMessage(e);
		}
		setPcclaveini(iniciativas);
		 if (ShiroBean.subject.hasRole("Administrador")) {FacesContext.getCurrentInstance().getExternalContext().redirect("../faces/palabraClave.xhtml");}
		 if (ShiroBean.subject.hasRole("Publico")) {FacesContext.getCurrentInstance().getExternalContext().redirect("../faces/palabraClaveUP.xhtml");}
	}
	

	public List<Likes> getLikes() {
		return likes;
	}

	public void setLikes(List<Likes> likes) {
		this.likes = likes;
	}

	public List <Iniciativa> getPcclaveini() {
		return pcclaveini;
	}

	public void setPcclaveini(List <Iniciativa> pcclaveini) {
		this.pcclaveini = pcclaveini;
	}

}