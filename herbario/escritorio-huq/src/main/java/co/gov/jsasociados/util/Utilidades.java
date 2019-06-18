package co.gov.jsasociados.util;

import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.ByteArrayInputStream;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Properties;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Permite manejar las operaciones generales de la capa de presentacion
 * 
 * @author EinerZG
 * @version 1.0
 */
public final class Utilidades {

	/**
	 * permite mostrar un texto informativo en pantalla
	 * 
	 * @param titulo  subtitulo de la alerta
	 * @param mensaje mensaje principal
	 */
	public static void mostrarMensaje(String titulo, String mensaje) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Herbario");
		alert.setContentText(mensaje);
		alert.showAndWait();
	}

	public static boolean mostrarMensajeConfirmacion(String titulo, String msg) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(titulo);
		alert.setContentText(msg);
		alert.showAndWait();
		if (alert.getResult() == ButtonType.OK) {
			return true;
		}
		return false;
	}

	/**
	 * permite hacer un casting de localDate a Date
	 * 
	 * @param localDate que se quiere cambiar
	 * @return una fecha en formato date
	 */
	public static Date pasarADate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * permite hacer un casting de date a localdate
	 * 
	 * @param date que se desea cambiar de formato
	 * @return una fecha en formato local date
	 */
	public static LocalDate pasarALocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static byte[] convertirImagenABytes(String ruta) throws IOException {

		BufferedImage bufferedImage = ImageIO.read(new File(ruta));

		WritableRaster raster = bufferedImage.getRaster();
		DataBufferByte data = (DataBufferByte) raster.getDataBuffer();

		return (data.getData());
	}

	/**
	 * metodo para validar el ingreso de un email valido
	 * 
	 * @param email
	 * @return
	 */
	public static boolean validarEmailFuerte(String email) {

		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);

		return matcher.matches();
	}

	public static Image convertirBytesAImagen(byte[] bytes) throws IOException {
		return new Image(new ByteArrayInputStream(bytes));
	}

	/**
	 * metodo que permite enviar un email por medio de Gmail
	 * 
	 * @param destinatario
	 * @param asunto
	 * @param cuerpo
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	private static void enviarConGMail(String destinatario, String asunto, String cuerpo)
			throws UnsupportedEncodingException, MessagingException {
		// Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el
		// remitente también.
		String remitente = "herbicidaherbariouq.bot"; // Para la dirección nomcuenta@gmail.com

		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
		props.put("mail.smtp.user", remitente);
		props.put("mail.smtp.clave", "jsasociados"); // La clave de la cuenta
		props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
		props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		message.setFrom(new InternetAddress(remitente, "HerbarioUQ"));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
		message.setSubject(asunto);
		message.setText(cuerpo);
		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.gmail.com", remitente, "jsasociados");
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}

	public static boolean restablecerClave(String destinatario, String clave) {
		String asunto = "Recuperación de contraseña de HerbarioUQ";
		String cuerpo = "Esta es la contraseña que se ha asignado por el proceso de restablecer su contraseña de la cuenta asociada a este correo.\nClave establecida: "
				+ clave;
		try {
			enviarConGMail(destinatario, asunto, cuerpo);
			return true;
		} catch (UnsupportedEncodingException | MessagingException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
}
