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
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.gov.jsasociados.ejb.AdminEJB;

@RunWith(Arquillian.class)
public class TestAdminEJB {
	/**
	 * instancia para realizar ejecutar las operaciones de negocio para admin
	 */
	@EJB
	private AdminEJB adminEJB;
	

	/**
	 * general el archivo de depliegue de pruebas
	 * 
	 * @return genera un archivo de configuracion web
	 */
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(JavaArchive.class).addClass(AdminEJB.class).addPackage(Persona.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void insertarEmpleadoTest() {
		Empleado empleado=new Empleado();
		empleado.setApellidos("Mariskos");
		empleado.setCedula("123");
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
		}catch(Exception e) {
			Assert.fail(String.format("Error inesperado %s", e.getMessage()));
		}
		
	}
}
