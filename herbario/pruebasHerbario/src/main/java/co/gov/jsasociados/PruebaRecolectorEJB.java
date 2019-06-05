package co.gov.jsasociados;

import java.util.List;

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

import co.gov.jsasociados.ejb.RecolectorEJB;
import co.gov.jsasociados.entidades.Cuenta;
import co.gov.jsasociados.entidades.Familia;
import co.gov.jsasociados.entidades.Genero;
import co.gov.jsasociados.entidades.Persona;
import co.gov.jsasociados.entidades.Planta;
import co.gov.jsasociados.entidades.Recolector;
import co.gov.jsasociados.entidades.Registro;
import co.gov.jsasocioados.exeption.ElementoNoExiste;
import co.gov.jsasocioados.exeption.ElementoRepetidoException;
import co.gov.jsasocioados.exeption.PersonaNoRegistradaException;
import co.gov.jsasocioados.exeption.TipoClaseException;
@RunWith(Arquillian.class)
public class PruebaRecolectorEJB {
	
	/**
	 * instancia para realizar ejecutar las operaciones de negocio para recolector
	 */
	//@EJB
	private RecolectorEJB recolectorEJB;

	/**
	 * general el archivo de depliegue de pruebas
	 * @return genera un archivo de configuracion web
	 */
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(JavaArchive.class).addClass(RecolectorEJB.class).addPackage(Persona.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		
	}

	/**
	 * metodo test para agregar recolector
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void agregarRecolector() {
		Recolector recolector=new Recolector();
		recolector.setApellidos("Pacho");
		recolector.setCorreo("gmail");
		recolector.setDireccion("Avenida");
		recolector.setNombre("asha");
		recolector.setTelefono("0000");
		recolector.setCedula("3334");
		
		Cuenta cuenta= new Cuenta();
		cuenta.setUsuario("nevera");
		cuenta.setContrasenia("nevera");
		cuenta.setPersona(recolector);
		recolector.setCuenta(cuenta);
		
		try {
			recolectorEJB.insertarRecolector(recolector);
		} catch (Exception e) {
			Assert.fail(String.format("Error inesperado %s", e.getMessage()));
		}
	}
	
	/**
	 * metodo test para modificar recolector
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void modificarRecolector() {
		try {
			recolectorEJB.modificarRecolector("Pasho", "Asdro", "212", "gmail", "laviejaconfi", "124");
		} catch (PersonaNoRegistradaException | TipoClaseException e) {
			// TODO Auto-generated catch block
			Assert.fail(String.format("Error inesperado %s", e.getMessage()));
		}
	}
	
	/**
	 * metodo test para agregar una planta
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void insertarPlanta() {

		Familia familia=new Familia();
		familia.setFamilia("abc");
		familia.setIdFamilia("familia2");
		
		Genero genero=new Genero();
		genero.setIdGenero("genero3");
		genero.setFamilia(familia);
		genero.setGenero("gen1");
		
		Planta planta=new Planta();
		planta.setIdPlanta("222");
		planta.setGenero(genero);
				
		try {
			recolectorEJB.insertarPlanta(planta);
		} catch (ElementoRepetidoException | ElementoNoExiste e) {
			// TODO Auto-generated catch block
			Assert.fail(String.format("Error inesperado %s", e.getMessage()));
		}
		
	}
	
	/**
	 * metodo test para buscar una planta
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void buscarPlanta() {
		try {
			Planta aux=recolectorEJB.buscarPlanta("851");
		} catch (ElementoNoExiste e) {
			// TODO Auto-generated catch block
			Assert.fail(String.format("Error inesperado %s", e.getMessage()));
		}
	}
	
	/**
	 * metodo test para listar especie aceptadas
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarEspeciesAceptadas() {
		List<Planta>aux=recolectorEJB.listarEspeciesAceptadas();
	}
	
	/**
	 * metodo test para listar registros de un recolector
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarRegistros() {
		try {
			List<Registro>aux=recolectorEJB.listarRegistros("124");
		} catch (PersonaNoRegistradaException e) {
			// TODO Auto-generated catch block
			Assert.fail(String.format("Error inesperado %s", e.getMessage()));
		}
	}
	
	/**
	 * metodo test para listas plantas aceptadas por familia
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasAceptadasPorFamilia() {
		try {
			List<Planta>aux=recolectorEJB.listarPlantasAceptadasPorFamilia("familia3");
		} catch (ElementoNoExiste e) {
			
			// TODO Auto-generated catch block
			Assert.fail(String.format("Error inesperado %s", e.getMessage()));
		}
	}

	/**
	 * metodo test para listar las plantas aceptadas por genero
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void listarPlantasAceptasPorGenero() {
		try {
			List<Planta>aux=recolectorEJB.listarPlantasAceptasPorGenero("genero3");

		} catch (ElementoNoExiste e) {
			// TODO Auto-generated catch block
			Assert.fail(String.format("Error inesperado %s", e.getMessage()));
		}
	}
}
