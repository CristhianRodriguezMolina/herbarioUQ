package co.gov.jsasociados.ejb;

import javax.ejb.Remote;

import co.gov.jsasociados.Empleado;
import co.gov.jsasocioados.exeption.ElementoRepetidoException;

/**
 * permite manejar el patron layer para exponer las funciones deseadas
 */
@Remote
public interface AdminEJBRemote {
	
	/**
	 * metodo que permiete agregar un empleado
	 * 
	 * @param empleado
	 * @return
	 * @throws ElementoRepetidoException
	 */
	 Empleado insertarEmpleado(Empleado empleado) throws ElementoRepetidoException;
}
