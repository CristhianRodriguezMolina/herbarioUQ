package co.gov.jsasociados.ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.gov.jsasociados.entidades.Administrador;
import co.gov.jsasociados.entidades.Cuenta;

/**
 * Se encarga de verificar la configuracion por defecto
 */
@Singleton
@LocalBean
@Startup
public class ConfiguracionEJB {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ConfiguracionEJB() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * inicializar la informacion basica
	 */
	@PostConstruct
	public void inicializar() {
		// TODO Auto-generated constructor stub
		TypedQuery<Administrador> query = entityManager.createNamedQuery(Administrador.LISTAR_ADMINISTRADORES,
				Administrador.class);
		List<Administrador> administradores = query.getResultList();
		if (administradores.size() == 0) {
			Administrador administrador = new Administrador();

			administrador.setCedula("1004915534");
			administrador.setNombre("Carlos Antonio");
			administrador.setApellidos("Osorio Rodriguez");
			administrador.setCorreo("correo@mail.com");
			administrador.setDireccion("por ahi");

			Cuenta cuenta = new Cuenta();
			cuenta.setUsuario("Admin");
			cuenta.setContrasenia("admin321");

			administrador.setCuenta(cuenta);
			
			entityManager.persist(cuenta);
			entityManager.persist(administrador);
		}
	}

}
