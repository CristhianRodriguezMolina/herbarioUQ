package co.gov.jsasociados.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.gov.jsasociados.Familia;
import co.gov.jsasociados.Recolector;
import co.gov.jsasocioados.exeption.ElementoRepetidoException;
import co.gov.jsasocioados.exeption.FamiliaYaRegistrada;
import co.gov.jsasocioados.exeption.PersonaNoRegistradaException;
import co.gov.jsasocioados.exeption.TipoClaseException;

@Remote
public interface EmpleadoEJBRemote {

	/**
	 * Metodo para insertar una familia a la base de datos
	 * @param familia
	 * @return la familia si se inserto o null de lo contrario
	 */
	public Familia insertarFamilia(Familia familia)throws ElementoRepetidoException, FamiliaYaRegistrada;	

	/**
	 * Metodo para eliminar una familia por id
	 * @param idFamilia
	 * @return True si se elimino correctamente la familia y False de lo contrario
	 * @throws FamiliaYaRegistrada
	 */
    public boolean eliminarFamilia(String idFamilia) throws FamiliaYaRegistrada;
    
    /**
     * metodo para modificar una familia
     * @param nombre
     * @param idFamilia
     * @return la familia modificada o null si no encontro familia
     * @throws FamiliaYaRegistrada
     */
    public Familia modificarFamilia(String nombre, String idFamilia) throws FamiliaYaRegistrada;
    
    /**
     * metodo para listar las familias
     * @return la lista de familias
     * @throws Exception
     */
    public List<Familia> listarFamilias() throws Exception;
    
	 /**
     * Metodo para insertar un recolector a la base de datos
     * @param empleado
     * @return El recolector a insertar si no ha excepcion
     * @throws ElementoRepetidoException
     * @throws PersonaNoRegistradaException
     */
	public Recolector insertarRecolector(Recolector recolector)
			throws ElementoRepetidoException, PersonaNoRegistradaException;

	/**
     * Metodo para eliminar un recolector por cedula
     * @param cedula por la cual se busca al recolector
     * @return True si se elimino correctamente el recolector y False de lo contrario
     * @throws PersonaNoRegistradaException
     * @throws TipoClaseException
     */
    public boolean eliminarRecolector(String cedula) throws PersonaNoRegistradaException, TipoClaseException;
        
    /**
     * metodo para modificar un recolector
     * @param nombre
     * @param apellido
     * @param telefono
     * @param correo
     * @param direccion
     * @param cedula
     * @return El recolector modificado
     * @throws PersonaNoRegistradaException
     * @throws TipoClaseException
     */
    public Recolector modificarRecolector(String nombre, String apellido, String telefono, String correo,
			String direccion, String cedula) throws PersonaNoRegistradaException, TipoClaseException;
    
    /**
     * Metodo para listar a todos los recolectores
     * @return La lista de recolectores 
     * @throws Exception
     */
    public List<Recolector> listarRecolectores() throws Exception;
    
    /**
     * Metodo para buscar un recolector por cedula
     * @param cedula
     * @return El recolector si fue encontrado o null de lo contrario
     * @throws PersonaNoRegistradaException
     * @throws TipoClaseException
     */
    public Recolector buscarRecolector(String cedula) throws PersonaNoRegistradaException, TipoClaseException;
    
    
}
