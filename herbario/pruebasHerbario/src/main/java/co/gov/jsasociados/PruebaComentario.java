package co.gov.jsasociados;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * Clase que permite relizar los test de comentarios
 * 
 * @author Sergio Osorio
 * @author Cristian Rodriguez
 * @author Jhonatan Hidalgo
 *
 */

@RunWith(Arquillian.class)
public class PruebaComentario {
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
	 * lista todos los comentarios hechos
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json", "comentario.json" })
	public void listarComentariosTest() {
		TypedQuery<Comentario> query = entityManager.createNamedQuery(Comentario.LISTAR_COMENTARIOS, Comentario.class);

		List<Comentario> comentarios = query.getResultList();
		Assert.assertEquals(comentarios.size(), 5);
	}

	/**
	 * lista todos los comentarios de cierta fecha
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json", "comentario.json" })
	public void listarComentariosPorFechaTest() {
		TypedQuery<Comentario> query = entityManager.createNamedQuery(Comentario.LISTAR_COMENTARIOS_FECHAPUBLICACION,
				Comentario.class);
		try {
			query.setParameter("fechaPublicacion", new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-12"));
			List<Comentario> comentarios = query.getResultList();
			Assert.assertEquals(comentarios.size(), 2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * lista los comentarios de una persona por su cedula
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json", "comentario.json" })
	public void listarComentariosCedulaTest() {
		TypedQuery<Comentario> query = entityManager.createNamedQuery(Comentario.LISTAR_COMENTARIOS_PERSONA,
				Comentario.class);
		query.setParameter("cedula", "123");
		List<Comentario> comentarios = query.getResultList();
		Assert.assertEquals(comentarios.size(), 2);
	}
}
