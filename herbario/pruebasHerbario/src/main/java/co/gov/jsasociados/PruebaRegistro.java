package co.gov.jsasociados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Clase que permite relizar los test de planta
 * 
 * @author Sergio Osorio
 * @author Cristian Rodriguez
 * @author Jhonatan Hidalgo
 *
 */
@RunWith(Arquillian.class)
public class PruebaRegistro {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * general el archivo de depliegue de pruebas
	 * 
	 * @return genera un archivo de configuracion web
	 */
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(Persona.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

	}
	
	/**
	 * test para obtener datos de registro
	 */
//	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json" })
	public void obtenerDatosRegistroTest() throws ParseException {
		TypedQuery<Object[]> query = entityManager.createNamedQuery(Registro.DATOS_REGISTRO_FECHA, Object[].class);
		query.setParameter("fechaRegistro", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse("1999-03-30 00:00:00.0"));
		List<Object[]> list = query.getResultList();

		Assert.assertEquals("888", list.get(0)[0].toString());
		
		Iterator it = list.iterator();

		while (it.hasNext()) {
			
			System.out.println(list.get(0)[0].toString());
		}
	}
	
	/**
	 * test para obtener datos de registro con DTO
	 */
//	@Test
//	@Transactional(value = TransactionMode.ROLLBACK)
//	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
//			"familia.json", "genero.json", "recolector.json", "planta.json" })
//	public void obtenerDatosRegistroDTOTest() throws ParseException {
//		TypedQuery<DTODatosFechaRegistro> query = entityManager.createNamedQuery(Registro.DATOS_REGISTRO_FECHA_DTO, DTODatosFechaRegistro.class);
//		query.setParameter("fechaRegistro", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse("1999-03-30 00:00:00.0"));
//		List<DTODatosFechaRegistro> list = query.getResultList();
//
//		Assert.assertEquals("888", list.get(0).getIdRegistro());
//		
//		Iterator it = list.iterator();
//
//		while (it.hasNext()) {
//			
//			System.out.println(list.get(0).toString());
//		}
//	}
}
