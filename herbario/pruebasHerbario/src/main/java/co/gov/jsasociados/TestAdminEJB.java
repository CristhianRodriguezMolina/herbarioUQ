package co.gov.jsasociados;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.gov.jsasociados.ejb.AdminEJB;
import co.gov.jsasocioados.exeption.PersonaNoRegistradaException;
import co.gov.jsasocioados.exeption.TipoClaseException;

@RunWith(Arquillian.class)
public class TestAdminEJB {
	/**
	 * instancia para realizar ejecutar las operaciones de negocio para admin
	 */
	//@EJB
	private AdminEJB adminEJB;

	/**
	 * general el archivo de depliegue de pruebas is
	 * 
	 * @return genera un archivo de configuracion web
	 */
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(JavaArchive.class).addClass(AdminEJB.class).addPackage(Persona.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

	}

	/**
	 * metodo test para agregar un empleado
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void insertarEmpleadoTest() {
		Empleado empleado = new Empleado();
		empleado.setApellidos("Mariskos");
		empleado.setCedula("12355");
		empleado.setNombre("Mayoneso");
		empleado.setTelefono("31131312");
		empleado.setCorreo("sodmadaodja");

		Cuenta cuenta = new Cuenta();
		cuenta.setContrasenia("123");
		cuenta.setUsuario("123");

		cuenta.setPersona(empleado);
		empleado.setCuenta(cuenta);
		try {
			adminEJB.insertarEmpleado(empleado);
		} catch (Exception e) {
			Assert.fail(String.format("Error inesperado %s", e.getMessage()));
		}

	}

	/**
	 * metodo test para eliminar un empleado
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void eliminarEmpladoTest() {
		try {
			adminEJB.eliminarEmpleado("126");
		} catch (PersonaNoRegistradaException | TipoClaseException e) {
			// TODO Auto-generated catch block
			Assert.fail(String.format("Error inesperado %s", e.getMessage()));
		}
	}

	/**
	 * metodo test para modificar los datos de un empleado
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void modificarEmpleadoTest() {

		try {
			adminEJB.modificarEmpleado("Carlos Antonio", "Agudelo", "310255545", "caa@mail.com", "por ahi", "125");
		} catch (PersonaNoRegistradaException | TipoClaseException e) {
			// TODO Auto-generated catch block
			Assert.fail(String.format("Error inesperado %s", e.getMessage()));
		}
	}

	/**
	 * metodo test para listar los empleados
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarEmpleadoTest() {

		try {
			adminEJB.listarEmpleados();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.fail(String.format("Error inesperado %s", e.getMessage()));
		}
	}

	/**
	 * metodo test para buscar en empleado
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void buscarEmpleadoTest() {

		try {
			adminEJB.buscarEmpleado("128");
		} catch (PersonaNoRegistradaException | TipoClaseException e) {
			// TODO Auto-generated catch block
			Assert.fail(String.format("Error inesperado %s", e.getMessage()));
		}
	}

	// test para recolectores

	/**
	 * metodo test para agregar un recolector
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void insertarRecolecotorTest() {
		Recolector recolector = new Recolector();
		recolector.setApellidos("Mariskos");
		recolector.setCedula("123455");
		recolector.setNombre("Mayoneso");
		recolector.setTelefono("31131312");
		recolector.setCorreo("sodmadaodja");

		Cuenta cuenta = new Cuenta();
		cuenta.setContrasenia("123");
		cuenta.setUsuario("123");

		cuenta.setPersona(recolector);
		recolector.setCuenta(cuenta);
		try {
			adminEJB.insertarRecolector(recolector);
		} catch (Exception e) {
			Assert.fail(String.format("Error inesperado %s", e.getMessage()));
		}

	}

	/**
	 * metodo test para eliminar un recolector
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void eliminarRecolectorTest() {
		try {
			adminEJB.eliminarRecolector("129");
		} catch (PersonaNoRegistradaException | TipoClaseException e) {
			// TODO Auto-generated catch block
			Assert.fail(String.format("Error inesperado %s", e.getMessage()));
		}
	}

	/**
	 * metodo test para modificar los datos de un recolector
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void modificarRecolectorTest() {

		try {
			adminEJB.modificarRecolector("Carlos Antonio", "Agudelo", "310255545", "caa@mail.com", "por ahi", "129");
		} catch (PersonaNoRegistradaException | TipoClaseException e) {
			// TODO Auto-generated catch block
			Assert.fail(String.format("Error inesperado %s", e.getMessage()));
		}
	}

	/**
	 * metodo test para listar los recolectores
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarRecolectoresTest() {

		try {
			adminEJB.listarRecolectores();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.fail(String.format("Error inesperado %s", e.getMessage()));
		}
	}

	/**
	 * metodo test para buscar un recolector
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void buscarRecolectorTest() {

		try {
			adminEJB.buscarRecolector("129");
		} catch (PersonaNoRegistradaException | TipoClaseException e) {
			// TODO Auto-generated catch block
			Assert.fail(String.format("Error inesperado %s", e.getMessage()));
		}
	}
}
