package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import excepciones.*;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

import org.mybatis.guice.transactional.Transactional;

@Singleton
public class ServiciosAlquilerImpl implements ServiciosAlquiler {

   @Inject
   private ItemDAO itemDAO;
   @Inject
   private ItemRentadoDAO itemRentadoDAO;
   @Inject
   private TipoItemDAO tipoItemDAO;
   @Inject
   private ClienteDAO clienteDAO;

   @Override
   public int valorMultaRetrasoxDia(int itemId) throws ExcepcionServiciosAlquiler{
	   try {
            return itemDAO.valormultaretraso(itemId);
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el item " + itemId, e);
        }
   }

   @Override
   public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler{
		try {
			return clienteDAO.load(docu);
		} catch (PersistenceException e) {
			throw new ExcepcionServiciosAlquiler(e.getMessage());
		}	   
   }

   @Override
   public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
       List<ItemRentado> res = null;
        try {
            if(!(consultarCliente(idcliente)==null)){
                throw new ExcepcionServiciosAlquiler("Este cliente no está registrado: "+ idcliente);
            }
            res = clienteDAO.loadItems(idcliente);
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Ocurrió un error al consultar este cliente " + idcliente, e);
        }
        return res;
   }

   @Override
   public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
       try {
            return clienteDAO.consultarClientes();
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Ocurrió un error al consultar los clientes", e);
        }
   }

   @Override
   public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
       Item res =null;
        try {
            res=itemDAO.load(id);
            } catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el item " + id, e);
        }
        return res;
   }

   @Override
   public List<Item> consultarItemsDisponibles() throws ExcepcionServiciosAlquiler {
      try {
            return itemDAO.consultarItemsDisponibles();
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Ocurrió un error al consultar los items que están disponibles", e);
        }
   }

   @Override
   public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
	   try {
           System.out.println(iditem);
           ItemRentado a=itemRentadoDAO.load(iditem);
           System.out.println(iditem);
           if(a==null || itemRentadoDAO.load(iditem)==null){
               throw new ExcepcionServiciosAlquiler("No hay información de el item rentado: "+ iditem);
           }
           LocalDate fechaMinimaEntrega=a.getFechafinrenta().toLocalDate();
           LocalDate fechaEntrega=fechaDevolucion.toLocalDate();
           long diasRetraso = ChronoUnit.DAYS.between(fechaMinimaEntrega, fechaEntrega);            
           return diasRetraso*a.getItem().getTarifaxDia();
       } catch (PersistenceException e) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el item rentado: "+ iditem, e);
       }
   }

   @Override
   public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
       try {
            return tipoItemDAO.load(id);
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Ocurrió un error al consultar el tipo de item " + id, e);
        }
   }

   @Override
   public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
       try {
            return tipoItemDAO.consultarTiposItem();
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Ocurrió un error al consultar los tipos de item", e);
        }
   }

   @Override
   @Transactional
   public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
	   try {
           Calendar calendar = Calendar.getInstance();
           calendar.setTime(date);
           calendar.add(Calendar.DAY_OF_YEAR, numdias);
           itemRentadoDAO.save(docu,item.getId(), date, new java.sql.Date(calendar.getTime().getTime()));
       } catch (Exception ex) {
           throw new ExcepcionServiciosAlquiler(
                   "Error al registrar el alquiler del producto " + item.toString() + " por el cliente " + docu, ex);
       }
   }

   @Override
   @Transactional
   public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler {
       try {
            clienteDAO.save(c);
            for(ItemRentado a: c.getRentados()){
                itemDAO.save(a.getItem());
            }
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Ocurrió un error al registrar el cliente " + c.toString(), e);
        }
   }
  

   @Override
   public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
	   long res=0;
       try {
           if(itemDAO.load(iditem)==null){
               throw new ExcepcionServiciosAlquiler("El item no está registrado: " + iditem);
           }
           res = itemDAO.consultarCostoAlquiler(iditem, numdias);
       } catch (PersistenceException e) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el item " + iditem, e);
       }
       return res;
   }

   @Override
   @Transactional
   public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
       try {
            itemDAO.actualizarTarifaItem(id, tarifa);
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Ocurrió un error al actualizar la tarifa del item " + id, e);
        }
   }
   
   @Override
   @Transactional
   public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
       try {
            if(tipoItemDAO.load(i.getTipo().getID())==null){
                tipoItemDAO.save(i.getTipo());
            }
            itemDAO.save(i);
            
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Ocurrió un error al registrar el item " + i.toString(), e);
        }
   }

   @Override
   @Transactional
   public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
       try {
            clienteDAO.vetarCliente(docu, estado);
        } catch (PersistenceException e) {
            throw new ExcepcionServiciosAlquiler("Ocurrió un error al registrar el vetar el cliente " + docu, e);
            
        }
   }
}