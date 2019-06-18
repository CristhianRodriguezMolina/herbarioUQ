package co.gov.jsasociados.util;

import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.envEntryType;

import java.io.ByteArrayInputStream;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	@PersistenceContext
	private static EntityManager entityManager;
	 
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

	public static Image convertirBytesAImagen(Long id) throws IOException, SQLException, ClassNotFoundException {
		
		FileOutputStream image;
		Connection con = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet res = null;
		StringBuffer query = null;
		String driverName = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "root";
		byte b[] = null;
		
		Class.forName(driverName);
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/herbario", userName, password);
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select planta.imagen from Planta planta where planta.idPlanta='"+id+"'");
		try {
			if (rs.next()) {
				Blob test = rs.getBlob("image");
				InputStream x = test.getBinaryStream();
				int size = x.available();
				OutputStream out = new FileOutputStream("C:\\anu.gif");
				b = new byte[size];
				x.read(b);
				out.write(b);
			}
		} catch (Exception e) {
			System.out.println("Exception :" + e);
		} 
		
		return new Image(new ByteArrayInputStream(b));
	}
		
	public static void enviarConGMail(String destinatario, String asunto, String cuerpo)  {
	    // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente tambi�n.
	    String remitente = "herbicidaherbariouq.bot";  //Para la direcci�n nomcuenta@gmail.com

	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
	    props.put("mail.smtp.user", remitente);
	    props.put("mail.smtp.clave", "jsasociados");    //La clave de la cuenta
	    props.put("mail.smtp.auth", "true");    //Usar autenticaci�n mediante usuario y clave
	    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
	    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
	    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(remitente,"HerbarioUQ"));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
	        message.setSubject(asunto);
	        message.setText(cuerpo);
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", remitente, "jsasociados");
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException | UnsupportedEncodingException me) {
	        me.printStackTrace();   //Si se produce un error
	    }
	}
}
