package co.gov.jsasociados;

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
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class pruebaComentario {
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
	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json", "comentario.json" })
	public void listarComentarios() {
		TypedQuery<Comentario> query = entityManager.createNamedQuery(Comentario.LISTAR_COMENTARIOS, Comentario.class);

		List<Comentario> comentarios = query.getResultList();
		System.out.println("tamanio de la lista" + comentarios.size());
		Iterator<Comentario> iterator = comentarios.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getComentario());
		}
	}

	/**
	 * lista todos los comentarios de cierta fecha
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json", "comentario.json" })
	public void listarComentariosPorFecha() {
		TypedQuery<Comentario> query = entityManager.createNamedQuery(Comentario.LISTAR_COMENTARIOS_FECHAPUBLICACION,
				Comentario.class);
		try {
			query.setParameter("fechaPublicacion", new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-12"));
			List<Comentario> comentarios = query.getResultList();
			System.out.println("tamanio de la lista" + comentarios.size());
			Iterator<Comentario> iterator = comentarios.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next().getComentario());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * lista los comentarios de una persona por su cedula
	 */
	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "administrador.json", "cuenta.json", "empleado.json",
			"familia.json", "genero.json", "recolector.json", "planta.json", "comentario.json" })
	public void listar() {
		TypedQuery<Comentario> query = entityManager.createNamedQuery(Comentario.LISTAR_COMENTARIOS_PERSONA,
				Comentario.class);
		query.setParameter("cedula", "123");
		List<Comentario> comentarios = query.getResultList();
		System.out.println("tamanio de la lista" + comentarios.size());
		Iterator<Comentario> iterator = comentarios.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getComentario());
		}
	}
}
