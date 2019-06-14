package co.gov.jsasociados.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import co.gov.jsasociados.Planta;
import co.gov.jsasociados.Recolector;
import co.gov.jsasociados.Registro;
import co.gov.jsasocioados.exeption.ElementoNoEncontradoException;
import co.gov.jsasocioados.exeption.ElementoNoExiste;
import co.gov.jsasocioados.exeption.ElementoRepetidoException;
import co.gov.jsasocioados.exeption.PersonaNoRegistradaException;
import co.gov.jsasocioados.exeption.TipoClaseException;

/**
 * permite manejar el patron layer para exponer las funciones deseadas
 */
@Remote
public interface RecolectorEJBRemote {
	
	/**
	 * Metodo que permite agregar un recolector
	 * @param recolector- recolector a registrar
	 * @return
	 * @throws ElementoRepetidoException
	 */
	Recolector insertarRecolector(Recolector recolector) throws ElementoRepetidoException;
	
	/**
	 * Metodo que permite modifcar los datos de un recolector
	 * @param nombre-nombre por el cual quiere ser reemplazado
	 * @param apellido-apellido por el cual quiere ser reemplazado
	 * @param telefono-telefono por el cual quiere ser reemplazado
	 * @param correo-correo por el cual quiere ser reemplazado
	 * @param direccion-direccion por el cual quiere ser reemplazado
	 * @param cedula-cedula del recolector, el cual se desea modificar los datos
	 * @return
	 * @throws PersonaNoRegistradaException
	 * @throws TipoClaseException
	 */
	Recolector modificarRecolector(String nombre, String apellido, String telefono, String correo, String direccion,
				String cedula)throws PersonaNoRegistradaException, TipoClaseException;
	
	/**
	 * Metodo que permite agregar una planta
	 * @param planta-planta a registar
	 * @return
	 * @throws ElementoRepetidoException
	 * @throws ElementoNoExiste
	 */
	 Planta insertarPlanta(Planta planta)throws ElementoRepetidoException, ElementoNoExiste;
	 	 
	 /**
	  * Metodo que lista todas las especies aceptadas
	  * @return
	  */
	 List<Planta> listarEspeciesAceptadas();
	 
	 /**
	  * Metodo que lista todos los registros que ha hecho un recolector
	  * @param cedula-cedula del recolector del cual se desea listar los registros
	  * @return
	  * @throws PersonaNoRegistradaException
	  */
	 ArrayList<Registro> listarRegistros(String cedula)throws PersonaNoRegistradaException;
	 
	 /**
	  * Metodo que lista las plantas aceptadas de una familia
	  * @param idFamilia-ID de la familia para filtrar las plantas aceptadas
	  * @return
	  */
	 List<Planta>listarPlantasAceptadasPorFamilia(String idFamilia)throws ElementoNoExiste;
	 
	 /**
	  * Metodo que lista las plantas aceptadas de un genero
	  * @param idGenero-ID del genero para filtrar las plantas aceptadas
	  * @return
	  * @throws ElementoNoExiste
	  */
	 List<Planta>listarPlantasAceptasPorGenero(String idGenero)throws ElementoNoExiste;
	 
	 /**
	  * Metodo para registrar una especie
	  * @param planta
	  * @return
	  * @throws ElementoRepetidoException
	  */
	 Planta registrarEspecie(Planta planta) throws ElementoRepetidoException;
	 
	 /**
	  * metodo para eliminar una especie
	  * @param idPlanta
	  * @return
	  * @throws ElementoNoEncontradoException
	  */
	 boolean elimiarEspecie(Long idPlanta) throws ElementoNoEncontradoException;
	 
	 /**
	  * Metodo para modificar una especie
	  * @param planta
	  * @param idPlanta
	  * @return
	  * @throws ElementoNoEncontradoException
	  */
	 Planta modificarEspecie(String planta, Long idPlanta) throws ElementoNoEncontradoException;
	 
	 /**
	  * metodo para listar las plantas registradas
	  * @return
	  * @throws Exception
	  */
	 List<Planta> listarPlanta() throws Exception;
	 
	 /**
	  * Metodo para buscar una planta
	  * @param nombrePlanta
	  * @return
	  */
	 Planta buscarPlanta(String nombrePlanta);
	 
	 
	 
}
