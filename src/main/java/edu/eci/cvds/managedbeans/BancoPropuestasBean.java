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

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.entities.Comentario;
import edu.eci.cvds.entities.ReporteEstado;
import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.MeGusta;
import edu.eci.cvds.entities.PalabraClave;
import edu.eci.cvds.entities.ReporteArea;
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
    private ReporteArea reporte;
	private Iniciativa selectedIniciativa;
	private PalabraClave palabraClave;
	private ReporteEstado estado;
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
        	System.out.println("modificando estado ini "+selectedIniciativa.getNombrePropuesta()+" "+estado);
            bancoPropuesta.modificarIniciativaEstado(estado, selectedIniciativa.getNombrePropuesta());
        } catch (BancoPropuestasException e) {
        	setErrorMessage(e);
        }
    }
    
    public List<ReporteArea> agruparIniciativas(){
    	List<ReporteArea> reportes = null;
        try{
            reportes = bancoPropuesta.agruparIniciativas();
        } catch (BancoPropuestasException e) {
        	setErrorMessage(e);
        }
        return reportes;
    }
    
    public List<ReporteEstado> consultarEstados(){
    	List<ReporteEstado> estados = null;
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
    
    public void setSelectedReporte(ReporteArea reporte){
    	this.reporte = reporte;
    }

    public ReporteArea GetReporte(){
        return reporte;
    }
    
    public void setSelectedEstado(ReporteEstado estado){
    	this.estado = estado;
    }

    public ReporteEstado GetEstado(){
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
	
	public void darLike(String user) throws BancoPropuestasException {
		try {
			System.out.println("Darlike user "+ user);
			System.out.println("Darlike "+ selectedIniciativa.getId());
			 bancoPropuesta.darLike(selectedIniciativa.getId(),user);
	       } catch (BancoPropuestasException e) {
	    	   setErrorMessage(e);
	       }
	}
	
	public void pruebacomentar(int id, String user, String comentario) {
			System.out.println("---------------------------------------------");
			System.out.println(comentario);
			System.out.println(user);
			System.out.println(id);
	}
	
	public void comentar(String ini, String user, String comentario) throws BancoPropuestasException {
		try {
			System.out.println("---------------------------------------------");
			System.out.println(comentario);
			System.out.println(user);
			System.out.println(ini);
			int iniId = Integer.parseInt(ini);
			bancoPropuesta.comentar(iniId,user,comentario);
	       } catch (BancoPropuestasException e) {
	    	   setErrorMessage(e);
	       }
	}
	
	public int consultarLikes( int id) throws BancoPropuestasException {
		List<MeGusta> likes = null;
		try {
			 likes = bancoPropuesta.consultarLikes(id);
	       } catch (BancoPropuestasException e) {
	    	   setErrorMessage(e);
	       }
		return likes.size();
	}
	
	public int consultarNumLikes( int id) throws BancoPropuestasException {
		List<Comentario> likes = null;
		try {
			 likes = bancoPropuesta.consultarComentarios(id);
	       } catch (BancoPropuestasException e) {
	    	   setErrorMessage(e);
	       }
		return likes.size();
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
	
	public List <Iniciativa> getPcclaveini() {
		return pcclaveini;
	}

	public void setPcclaveini(List <Iniciativa> pcclaveini) {
		this.pcclaveini = pcclaveini;
	}

}