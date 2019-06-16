/**
 /**
 * Sample Skeleton for 'registro_empleado.fxml' Controller Class
 */

package co.gov.jsasociados.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import co.gov.jsasociados.Cuenta;
import co.gov.jsasociados.Empleado;
import co.gov.jsasociados.Recolector;
import co.gov.jsasociados.modelo.AdministradorDelegado;
import co.gov.jsasociados.modelo.PersonaObservable;
import co.gov.jsasociados.util.Utilidades;
import co.gov.jsasocioados.exeption.ElementoNoEncontradoException;
import co.gov.jsasocioados.exeption.ElementoRepetidoException;
import co.gov.jsasocioados.exeption.PersonaNoRegistradaException;
import co.gov.jsasocioados.exeption.TipoClaseException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class RegistroEmpleadoControlador {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtCedula"
	private TextField txtCedula; // Value injected by FXMLLoader

	@FXML // fx:id="txtNombre"
	private TextField txtNombre; // Value injected by FXMLLoader

	@FXML // fx:id="txtApellidos"
	private TextField txtApellidos; // Value injected by FXMLLoader

	@FXML // fx:id="txtCorreo"
	private TextField txtCorreo; // Value injected by FXMLLoader

	@FXML // fx:id="txtTelefono"
	private TextField txtTelefono; // Value injected by FXMLLoader

	@FXML // fx:id="txtDireccion"
	private TextField txtDireccion; // Value injected by FXMLLoader

	@FXML // fx:id="txtUsuario"
	private TextField txtUsuario; // Value injected by FXMLLoader

	@FXML // fx:id="txtContrasenia"
	private PasswordField txtContrasenia; // Value injected by FXMLLoader

	@FXML // fx:id="btnAceptar"
	private Button btnAceptar; // Value injected by FXMLLoader

	@FXML // fx:id="btnEliminar"
	private Button btnEliminar; // Value injected by FXMLLoader

	@FXML // fx:id="lblDinamico"
	private Label lblDinamico; // Value injected by FXMLLoader

	@FXML // fx:id="btnCambioGestion"
	private Button btnCambioGestion; // Value injected by FXMLLoader

	@FXML // fx:id="btnModificar"
	private Button btnModificar; // Value injected by FXMLLoader

	@FXML // fx:id="scrollPane"
	private ScrollPane scrollPane; // Value injected by FXMLLoader

	@FXML // fx:id="table"
	private TableView<PersonaObservable> table; // Value injected by FXMLLoader

	@FXML // fx:id="columnCedula"
	private TableColumn<PersonaObservable, String> columnCedula; // Value injected by FXMLLoader

	@FXML // fx:id="columnNombre"
	private TableColumn<PersonaObservable, String> columnNombre; // Value injected by FXMLLoader

	@FXML // fx:id="columnApellidos"
	private TableColumn<PersonaObservable, String> columnApellidos; // Value injected by FXMLLoader

	@FXML // fx:id="columnTelefono"
	private TableColumn<PersonaObservable, String> columnTelefono; // Value injected by FXMLLoader

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtCedula != null : "fx:id=\"txtCedula\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert txtApellidos != null : "fx:id=\"txtApellidos\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert txtCorreo != null : "fx:id=\"txtCorreo\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert txtTelefono != null : "fx:id=\"txtTelefono\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert txtDireccion != null : "fx:id=\"txtDireccion\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert txtUsuario != null : "fx:id=\"txtUsuario\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert txtContrasenia != null : "fx:id=\"txtContrasenia\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert btnAceptar != null : "fx:id=\"btnAceptar\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert btnEliminar != null : "fx:id=\"btnEliminar\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert lblDinamico != null : "fx:id=\"lblDinamico\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert btnCambioGestion != null : "fx:id=\"btnCambioGestion\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert btnModificar != null : "fx:id=\"btnModificar\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert scrollPane != null : "fx:id=\"scrollPane\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert columnCedula != null : "fx:id=\"columnCedula\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert columnNombre != null : "fx:id=\"columnNombre\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert columnApellidos != null : "fx:id=\"columnApellidos\" was not injected: check your FXML file 'registro_empleado.fxml'.";
		assert columnTelefono != null : "fx:id=\"columnTelefono\" was not injected: check your FXML file 'registro_empleado.fxml'.";

	}
	
	/**
	 * estado de gestion de la aplicacion, Empleado o Recolector 
	 */
	private String gestionando = "Empleado";
	/**
	 * instancia del manejador de escenario
	 */
	private ManejadorEscenarios escenarioInicial;

	/**
	 * conexion con la capade negocio
	 */
	private AdministradorDelegado administradorDelegado;

	/**
	 * permite obtener una instancia del escenario general
	 * 
	 * @param escenarioInicial
	 */
	public void setEscenarioInicial(ManejadorEscenarios escenarioInicial) {
		administradorDelegado = AdministradorDelegado.administradorDelegado;
		this.escenarioInicial = escenarioInicial;
		llenarTabla();
	}

	/**
	 * Metodo para insertar un empleado a la base de datos por medio de un botón
	 * 
	 * @param e1
	 */
	@FXML
	void insertarEmpleado(ActionEvent e1) {
		if (gestionando.equals("Empleado")) {
			if (e1.getSource().equals(btnAceptar)) {
				if (validarCampos()) {
					Empleado empleado = new Empleado();
					empleado.setCedula(txtCedula.getText().trim());
					empleado.setNombre(txtNombre.getText().trim());
					empleado.setApellidos(txtApellidos.getText().trim());
					empleado.setCorreo(txtCorreo.getText().trim());
					empleado.setDireccion(txtDireccion.getText().trim());
					empleado.setTelefono(txtTelefono.getText().trim());

					Cuenta cuenta = new Cuenta();
					cuenta.setUsuario(txtUsuario.getText().trim());
					cuenta.setContrasenia(txtContrasenia.getText().trim());
					cuenta.setPersona(empleado);
					empleado.setCuenta(cuenta);

					try {
						empleado = administradorDelegado.registrarEmpleado(empleado);
						if (empleado != null) {
							Utilidades.mostrarMensaje("Registro con exito",
									"Se ha registrado al empleado " + empleado.getNombre() + " con exito.");
							vaciarCampos();
							llenarTabla();
						}

					} catch (ElementoRepetidoException | PersonaNoRegistradaException e) {
						// TODO Auto-generated catch block
						Utilidades.mostrarMensaje("Error", e.getMessage());
					}
				}
			}
		} else if (gestionando.equals("Recolector")) {
			if (e1.getSource().equals(btnAceptar)) {
				if (validarCampos()) {
					Recolector recolector = new Recolector();
					recolector.setCedula(txtCedula.getText().trim());
					recolector.setNombre(txtNombre.getText().trim());
					recolector.setApellidos(txtApellidos.getText().trim());
					recolector.setCorreo(txtCorreo.getText().trim());
					recolector.setDireccion(txtDireccion.getText().trim());
					recolector.setTelefono(txtTelefono.getText().trim());

					Cuenta cuenta = new Cuenta();
					cuenta.setUsuario(txtUsuario.getText().trim());
					cuenta.setContrasenia(txtContrasenia.getText().trim());
					cuenta.setPersona(recolector);
					recolector.setCuenta(cuenta);

					try {
						recolector = administradorDelegado.insertarRecolector(recolector);
						if (recolector != null) {
							Utilidades.mostrarMensaje("Registro con exito",
									"Se ha registrado al recolector " + recolector.getNombre() + " con exito.");
							vaciarCampos();
							llenarTabla();
						}

					} catch (ElementoRepetidoException | PersonaNoRegistradaException e) {
						// TODO Auto-generated catch block
						Utilidades.mostrarMensaje("Error", e.getMessage());
					}
				}
			}
		}

	}

	/**
	 * metodo listener para modificar los datos de una persona
	 * 
	 * @param event
	 */
	@FXML
	void modificarPersona(ActionEvent event) {
		if (gestionando.equals("Empleado")) {
			if (validarCampos()) {
					Empleado empleado;
					try {
						empleado = administradorDelegado.modificarEmpleado(txtNombre.getText().trim(),
								txtApellidos.getText().trim(), txtTelefono.getText().trim(), txtCorreo.getText().trim(),
								txtDireccion.getText().trim(), txtCedula.getText().trim(), txtUsuario.getText().trim(), txtContrasenia.getText().trim());
						if (empleado != null) {
							Utilidades.mostrarMensaje("Se ha cambiado la infarmacion correctamente",
									"Al empleado con el número de cedula " + empleado.getCedula()
											+ " se le ha cambiado la informacion correctamente.");
							llenarTabla();
							vaciarCampos();
						}
					} catch (PersonaNoRegistradaException | TipoClaseException | ElementoNoEncontradoException
							| ElementoRepetidoException e) {
						// TODO Auto-generated catch block
						Utilidades.mostrarMensaje("Error", e.getMessage());
					}
					catch (Exception e) {
						Utilidades.mostrarMensaje("Error", e.getMessage());
					}
					
					// TODO Auto-generated catch block
						
				
			}
		} else if (gestionando.equals("Recolector")) {
			if (validarCampos()) {
				try {
					Recolector recolector = administradorDelegado.modificarRecolector(txtNombre.getText().trim(),
							txtApellidos.getText().trim(), txtTelefono.getText().trim(), txtCorreo.getText().trim(),
							txtDireccion.getText().trim(), txtCedula.getText().trim(), txtUsuario.getText().trim(), txtContrasenia.getText().trim());
					if (recolector != null) {
						Utilidades.mostrarMensaje("Se ha cambiado la infarmacion correctamente",
								"Al empleado con el número de cedula " + recolector.getCedula()
										+ " se le ha cambiado la informacion correctamente.");
						llenarTabla();
						vaciarCampos();
					}
				} catch (PersonaNoRegistradaException | TipoClaseException | ElementoNoEncontradoException
						| ElementoRepetidoException e) {
					// TODO Auto-generated catch block
						Utilidades.mostrarMensaje("Error", e.getMessage());
				}
				catch (Exception e) {
					Utilidades.mostrarMensaje("Error", e.getMessage());
				}
			}
		}
	}

	/**
	 * metodo listener del btn eliminar
	 * 
	 * @param event
	 */
	@FXML
	void eliminarEmpleado(ActionEvent event) {
		if (gestionando.equals("Empleado")) {
			if (txtCedula.getText().trim().equals("")) {
				Utilidades.mostrarMensaje("Alerta", "Por favor ingrese la cedula del empleado a eliminar");
			}else {
				try {
					if (administradorDelegado.eliminarEmpleado(txtCedula.getText().trim())) {
						Utilidades.mostrarMensaje("Exito", "Se ha eliminado el empleado exitosamente");
						llenarTabla();
						vaciarCampos();
					}
				} catch (PersonaNoRegistradaException | TipoClaseException e) {
					// TODO Auto-generated catch block
					Utilidades.mostrarMensaje(e.getMessage(), e.getMessage());
				}
			}
			
		} else if (gestionando.equals("Recolector")) {
			if (txtCedula.getText().trim().equals("")) {
				Utilidades.mostrarMensaje("Alerta", "Por favor ingrese la cedula del recolector a eliminar");
			}else {
				try {
					if (administradorDelegado.eliminarRecolector(txtCedula.getText().trim())) {
						Utilidades.mostrarMensaje("Exito", "Se ha eliminado al recolector exitosamente");
						llenarTabla();
						vaciarCampos();
					}
				} catch (PersonaNoRegistradaException | TipoClaseException e) {
					// TODO Auto-generated catch block
					Utilidades.mostrarMensaje(e.getMessage(), e.getMessage());
				}
			}
		}
	}

	/**
	 * metodo listener para cambiar de tipo de gestion
	 * 
	 * @param event
	 */
	@FXML
	void cambiarGestion(ActionEvent event) {
		if (gestionando.equals("Empleado")) {
			lblDinamico.setText("Recolector");
			btnCambioGestion.setText("Gestionar empleado");
			gestionando = "Recolector";
			vaciarCampos();
			table.getColumns().clear();
			llenarTabla();
		} else if (gestionando.equals("Recolector")) {
			lblDinamico.setText("Empleado");
			btnCambioGestion.setText("Gestionar recolector");
			gestionando = "Empleado";
			vaciarCampos();
			table.getColumns().clear();
			llenarTabla();
		}
	}

	/**
	 * metodo que limpia todos los campos
	 */
	private void vaciarCampos() {
		// TODO Auto-generated method stub
		txtCedula.clear();
		txtNombre.clear();
		txtApellidos.clear();
		txtTelefono.clear();
		txtCorreo.clear();
		txtDireccion.clear();
		txtUsuario.clear();
		txtContrasenia.clear();
	}

	/**
	 * metodo que permite llenar la tabla con el observable
	 */
	private void llenarTabla() {
		// TODO Auto-generated method stub
		if (gestionando.equals("Empleado")) {
			try {
				ObservableList<PersonaObservable> empleados = administradorDelegado.listarEmpleadosObservables();
				table.setItems(empleados);
				columnCedula.setCellValueFactory(new PropertyValueFactory<PersonaObservable, String>("cedula"));
				columnNombre.setCellValueFactory(new PropertyValueFactory<PersonaObservable, String>("nombre"));
				columnApellidos.setCellValueFactory(new PropertyValueFactory<PersonaObservable, String>("apellido"));
				columnTelefono.setCellValueFactory(new PropertyValueFactory<PersonaObservable, String>("telefono"));
				table.getColumns().setAll(columnCedula, columnNombre, columnApellidos, columnTelefono);
				table.setPrefWidth(450);
				table.setPrefHeight(300);
				table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

				table.getSelectionModel().selectedIndexProperty().addListener(new RowSelectChangeListener());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (gestionando.equals("Recolector")) {
			try {
				ObservableList<PersonaObservable> recolectores = administradorDelegado.listarRecolectoresObservables();
				table.setItems(recolectores);
				columnCedula.setCellValueFactory(new PropertyValueFactory<PersonaObservable, String>("cedula"));
				columnNombre.setCellValueFactory(new PropertyValueFactory<PersonaObservable, String>("nombre"));
				columnApellidos.setCellValueFactory(new PropertyValueFactory<PersonaObservable, String>("apellido"));
				columnTelefono.setCellValueFactory(new PropertyValueFactory<PersonaObservable, String>("telefono"));
				table.getColumns().setAll(columnCedula, columnNombre, columnApellidos, columnTelefono);
				table.setPrefWidth(450);
				table.setPrefHeight(300);
				table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

				table.getSelectionModel().selectedIndexProperty().addListener(new RowSelectChangeListener());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * listener de las filas de la tabla
	 * 
	 * @author Jhonatan Hidalgo
	 *
	 */
	private class RowSelectChangeListener implements ChangeListener {

		@Override
		public void changed(ObservableValue arg0, Object arg1, Object index) {
			if ((int) index != -1) {
				if (gestionando.equals("Empleado")) {
					Empleado empleado;
					try {
						empleado = administradorDelegado
								.buscarEmpleado((table.getSelectionModel().getSelectedItem().getCedula()));
						txtCedula.setText(empleado.getCedula());
						txtNombre.setText(empleado.getNombre());
						txtApellidos.setText(empleado.getApellidos());
						txtTelefono.setText(empleado.getTelefono());
						txtCorreo.setText(empleado.getCorreo());
						txtDireccion.setText(empleado.getDireccion());
						txtUsuario.setText(empleado.getCuenta().getUsuario());
						txtContrasenia.setText(empleado.getCuenta().getContrasenia());
					} catch (PersonaNoRegistradaException | TipoClaseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (gestionando.equals("Recolector")) {
					Recolector recolector;
					try {
						recolector = administradorDelegado
								.buscarRecolector((table.getSelectionModel().getSelectedItem().getCedula()));
						txtCedula.setText(recolector.getCedula());
						txtNombre.setText(recolector.getNombre());
						txtApellidos.setText(recolector.getApellidos());
						txtTelefono.setText(recolector.getTelefono());
						txtCorreo.setText(recolector.getCorreo());
						txtDireccion.setText(recolector.getDireccion());
						txtUsuario.setText(recolector.getCuenta().getUsuario());
						txtContrasenia.setText(recolector.getCuenta().getContrasenia());
					} catch (PersonaNoRegistradaException | TipoClaseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

			// System.out.println(table.getSelectionModel().getSelectedItem().getEmpleado());
		}
	}

	/**
	 * metodo que permite verificar que todos los campos esten completos
	 * 
	 * @return
	 */
	private boolean validarCampos() {
		// TODO Auto-generated method stub
		if (txtCedula.getText().trim().equals("")) {
			Utilidades.mostrarMensaje("Complete el campo", "Por favor ingrese un numero de cedula");
			return false;
		}
		if (txtNombre.getText().trim().equals("")) {
			Utilidades.mostrarMensaje("Complete el campo", "Por favor ingrese un nombre");
			return false;
		}
		if (txtApellidos.getText().trim().equals("")) {
			Utilidades.mostrarMensaje("Complete el campo", "Por favor ingrese los apellidos");
			return false;
		}
		if (txtCorreo.getText().trim().equals("")) {
			Utilidades.mostrarMensaje("Complete el campo", "Por favor ingrese el correo");
			return false;
		}
		if (!Utilidades.validarEmailFuerte(txtCorreo.getText().trim())) {
			Utilidades.mostrarMensaje("Complete el campo", "Por favor ingrese un correo valido");
			return false;
		}
		if (txtDireccion.getText().trim().equals("")) {
			Utilidades.mostrarMensaje("Complete el campo", "Por favor ingrese la direccion");
			return false;
		}
		if (txtTelefono.getText().trim().equals("")) {
			Utilidades.mostrarMensaje("Complete el campo", "Por favor ingrese el numero de telefono");
			return false;
		}
		if (txtUsuario.getText().trim().equals("")) {
			Utilidades.mostrarMensaje("Complete el campo", "Por favor ingrese el nombre de usuario");
			return false;
		}
		if (txtContrasenia.getText().trim().equals("")) {
			Utilidades.mostrarMensaje("Complete el campo", "Por favor ingrese una contraseña");
			return false;
		}
		return true;
	}
}
