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
import edu.eci.cvds.entities.Grupo;
import edu.eci.cvds.managedbeans.ShiroBean;
import edu.eci.cvds.entities.ReporteEstado;
import edu.eci.cvds.entities.Iniciativa;
import edu.eci.cvds.entities.MeGusta;
import edu.eci.cvds.entities.MeInteresa;
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
	private List <Iniciativa> inisagrupar;
	private List <Iniciativa> iniciativasConsultadas;
	private List <Comentario> comentarios;


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
	
	public void consulinis(String grupo){
        try{
        	if (grupo=="") {
    			FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Agrupar iniciativas", "Debe ingresar el nombre del grupo."));
        	}else {
        		if (inisagrupar.size() == 0) {
	        		FacesContext context = FacesContext.getCurrentInstance();
	        		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Agrupar iniciativas", "Seleccione las iniciativas a agrupar."));
        		}else {
	        		for (int i=0;i<inisagrupar.size();i++) {
	        			bancoPropuesta.agruparIniciativas(grupo,inisagrupar.get(i).getId());
	        		}
	        		FacesContext context = FacesContext.getCurrentInstance();
	        		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Agrupar iniciativas", "Iniciativas agrupadas."));
	        	}
        	}
        } catch (BancoPropuestasException e) {
        	FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Agrupar iniciativas", "No fue posible agrupar las iniciativas."));
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
        	if (selectedUsuario != null) {
        		if(rol != "") {
		            bancoPropuesta.modificarUsuario(rol, selectedUsuario.getCorreo());
		            FacesContext context = FacesContext.getCurrentInstance();
		            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificar usuario", "Rol actualizado."));
        		}else {
        			FacesContext context = FacesContext.getCurrentInstance();
		            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Modificar usuario", "Seleccione un rol"));
        		}
        	}else {
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Modificar usuario", "Seleccione el usuario al que le desea actualizar el rol."));
        	}
        } catch (BancoPropuestasException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Modificar usuario", "No fue posible actualizar el rol."));
        }
    }
	
	public void modificarPropuesta(Iniciativa ini, String nombrePropuesta, String descripcion, String area){
		
		if(ini.getEstado_Propuesta().equals("En revisión")) {
        try {
            bancoPropuesta.modificarPropuesta(nombrePropuesta, descripcion, area, ini.getId());
            this.consultarIniciativas();
        } catch (BancoPropuestasException e) {
            setErrorMessage(e);

        }}
		else { FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El estado debe estar En revisión" , null));}
    }
	
	public void modificarUsuarioEstado(String estado){
        try{
            bancoPropuesta.modificarUsuarioEstado(estado, selectedUsuario.getCorreo());
        } catch (BancoPropuestasException e) {
            setErrorMessage(e);
        }
    }
	
	public List<Grupo> consultarGrupos (int id) {
		List<Grupo> aux = new ArrayList<Grupo>();
		try{
        	aux=bancoPropuesta.consultarGrupo(id);
        } catch (BancoPropuestasException e) {
        }
		return aux;
	}
	
	public String consultarGrupo (int id){
		String res = "";
		List<Grupo> aux = consultarGrupos(id);
		if (aux.size() == 0) {
			res= "No pertenece a ningun grupo.";
		}
        for(int i=0; i< aux.size()-1; i++) {
        	res += aux.get(i).getNombre()+", ";
       	}
        if (aux.size() > 0) {
        	res +=aux.get(aux.size()-1).getNombre()+".";
        }
        return res;
    }
	
	public String consultarInisAgru(int id){
		String res = "";
        try{
        	List<Grupo> grupos = this.consultarGrupos(id);
        	if(grupos.size() == 0) {
        		res="No se encuenta relacionada con ninguna iniciativa.";
        	}
        	for (int g=0; g < grupos.size(); g++) {
        		res+=grupos.get(g).getNombre()+":\n";
        		List <Iniciativa> iniR = bancoPropuesta.consultarInisAgru(id, grupos.get(g).getNombre());
        		for(int i=0; i<iniR.size() - 1; i++) {
        			res +=iniR.get(i).getNombrePropuesta()+", ";
        		}
        		if(iniR.size() > 0) {
        			res+=iniR.get(iniR.size()-1).getNombrePropuesta();
        			if( g < grupos.size()-1) {
        				res+="\n\n";
        			}
        		}
        	}
        } catch (BancoPropuestasException e) {
        }
        return res;
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
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Palabra clave: " + p.getPalabraClave(), p.getPalabraClave()));
            }
            this.consultarIniciativas();
        } catch (Exception e) {
        	
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error"));
        	setErrorMessage(e);
        }
    }

    public void consultarIniciativas(){
        try{
            iniciativasConsultadas = bancoPropuesta.consultarIniciativas();
        } catch (BancoPropuestasException e) {
            setErrorMessage(e);
        }
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
        	if (selectedIniciativa != null) {
        		if (estado != "") {
		            bancoPropuesta.modificarIniciativaEstado(estado, selectedIniciativa.getNombrePropuesta());
		            this.consultarIniciativas();
		            FacesContext context = FacesContext.getCurrentInstance();
		            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificar iniciativa", "Estado actualizado."));
        		}else {
        			FacesContext context = FacesContext.getCurrentInstance();
		            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Modificar iniciativa", "Seleccione un estado."));
        		}
        	}else {
        		FacesContext context = FacesContext.getCurrentInstance();
	            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Modificar iniciativa", "Seleccione la iniciativa a la que le desea modificar el estado."));
        	}
        } catch (BancoPropuestasException e) {
        	FacesContext context = FacesContext.getCurrentInstance();
        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Modificar iniciativa", "No fue posible actualizar el estado."));
        }
    }
    
    public List<ReporteArea> agruparIniciativas(){
    	List<ReporteArea> reportes = null;
        try{
            reportes = bancoPropuesta.agruparIniciativas();
        } catch (BancoPropuestasException e) {
        	
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
    	   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La palabra ya se encuentra registrada", null));
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
			int idiniciativa= selectedIniciativa.getId();
			System.out.println("USU "+ user);
			if (consultarLikePorIds(idiniciativa, user) == 0) {
				bancoPropuesta.darLike(idiniciativa, user);
			}else {
				bancoPropuesta.quitarLike(idiniciativa, user);
			}
	       } catch (BancoPropuestasException e) {
	    	   setErrorMessage(e);
	       }
	}
	
	public void darInteres(String user) throws BancoPropuestasException {
		try {
			int idiniciativa= selectedIniciativa.getId();
			if (consultarInteresPorIds(idiniciativa, user) == 0) {
				bancoPropuesta.darInteres(idiniciativa, user);
			}else {
				bancoPropuesta.quitarInteres(idiniciativa, user);
			}
	       } catch (BancoPropuestasException e) {
	    	   setErrorMessage(e);
	       }
	}
	
	public int consultarLikePorIds (int idiniciativa, String idusuario) throws BancoPropuestasException{
		try {
			return bancoPropuesta.consultarLikePorIds(idiniciativa, idusuario).size();
		}catch(BancoPropuestasException e) {
			setErrorMessage(e);
			return 0;
		}
	}
	
	public int consultarInteresPorIds (int idiniciativa, String idusuario) throws BancoPropuestasException{
		try {
			return bancoPropuesta.consultarInteresPorIds(idiniciativa, idusuario).size();
		}catch(BancoPropuestasException e) {
			setErrorMessage(e);
			return 0;
		}
	}
	
	public void comentar(int ini, String user, String comentario) throws BancoPropuestasException {
		try {
			bancoPropuesta.comentar(ini,user,comentario);
			consultarComentarios(ini);
	       } catch (BancoPropuestasException e) {
	    	   setErrorMessage(e);
	       }
	}
	
	public int consultarNumLikes(int id) throws BancoPropuestasException {
		List<MeGusta> likes = null;
		try {
			 likes = bancoPropuesta.consultarLikes(id);
	       } catch (BancoPropuestasException e) {
	    	   setErrorMessage(e);
	       }
		return likes.size();
	}
	
	public int consultarNumInteres(int id) throws BancoPropuestasException {
		List<MeInteresa> interes = null;
		try {
			 interes = bancoPropuesta.consultarInteres(id);
	       } catch (BancoPropuestasException e) {
	    	   setErrorMessage(e);
	       }
		return interes.size();
	}
	
	public int consultarNumComentarios(int id) throws BancoPropuestasException {
		List<Comentario> comentarios = null;
		try {
			 comentarios = bancoPropuesta.consultarComentarios(id);
	       } catch (BancoPropuestasException e) {
	    	   setErrorMessage(e);
	       }
		return comentarios.size();
	}
	
	public void consultarComentarios(int ini) throws BancoPropuestasException {
		List<Comentario> c = new ArrayList<Comentario>();
		try {
			c = bancoPropuesta.consultarComentarios(ini);
	    } catch (BancoPropuestasException e) {
	    	setErrorMessage(e);
	    }
		
		comentarios = c;
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
	
	public List<PalabraClave> consultarPalabrasClaveIniciativa(int id) throws BancoPropuestasException {
		   List<PalabraClave> palabrasClaves = new ArrayList<PalabraClave>();
	       try{
	    	   palabrasClaves = bancoPropuesta.consultarPalabraClave(id);
	       } catch (BancoPropuestasException e) {
	       	   setErrorMessage(e);
	       }
	       return palabrasClaves;
		}
	
	public void consultarIniciativaPalabraClave (String palabra) throws IOException{
		try {
			iniciativasConsultadas = bancoPropuesta.consultarIniciativaPalabraClave(palabra);
		} catch(BancoPropuestasException e) {
			FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Consultar iniciativas", "No fue posible consultar las iniciativas."));
		}
	}
	
	public boolean isLike (int idIniciativa, String usuario) throws IOException{
		try {
			if (bancoPropuesta.consultarInteresPorIds(idIniciativa, usuario).size() == 1) {
				return true;
			} return false;
		} catch(BancoPropuestasException e) {
			setErrorMessage(e);
			return false;
		}
	}
	
	public List <Iniciativa> getPcclaveini() {
		return pcclaveini;
	}

	public void setPcclaveini(List <Iniciativa> pcclaveini) {
		this.pcclaveini = pcclaveini;
	}

	public List <Iniciativa> getInisagrupar() {
		return inisagrupar;
	}

	public void setInisagrupar(List <Iniciativa> inisagrupar) {
		this.inisagrupar = inisagrupar;
	}

	public List <Iniciativa> getIniciativasConsultadas() {
		return iniciativasConsultadas;
	}

	public void setIniciativasConsultadas(List <Iniciativa> iniciativasConsultadas) {
		this.iniciativasConsultadas = iniciativasConsultadas;
	}

	public List <Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List <Comentario> comentarios) {
		this.comentarios = comentarios;
	}
}