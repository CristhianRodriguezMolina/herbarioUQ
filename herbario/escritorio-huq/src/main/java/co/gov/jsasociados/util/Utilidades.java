package co.gov.jsasociados.util;

import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javafx.scene.image.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.sun.messaging.jmq.admin.apps.console.AExplorer;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;
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
//        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
//        Iterator readers = ImageIO.getImageReadersByFormatName("jpeg");
//        ImageReader reader = (ImageReader) readers.next();
//        Object source = bis; // File or InputStream
//        ImageInputStream iis = ImageIO.createImageInputStream(source);
//        reader.setInput(iis, true);
//        ImageReadParam param = reader.getDefaultReadParam();
		File temp = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			BufferedImage bim = ImageIO.read(new ByteArrayInputStream(baos.toByteArray()));
			temp = new File("D:/result.jpg");
			ImageIO.write(bim, "jpg", temp);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new Image(new ByteArrayInputStream(bytes));
	}

}
