package co.gov.jsasociados.util;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

public class Util {

	public static void mostarMensaje(String titulo, String msg) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, msg);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	public static ResourceBundle getResourseBundle() {
		FacesContext facesContext= FacesContext.getCurrentInstance();
		ResourceBundle resourceBundle= facesContext.getApplication().getResourceBundle(facesContext, "msg");
		return resourceBundle;
	}
	
	public static String getPath() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
	}
	
	public static byte[] convertirImagenABytes(String ruta) throws IOException {

		BufferedImage bufferedImage = ImageIO.read(new File(ruta));

		WritableRaster raster = bufferedImage.getRaster();
		DataBufferByte data = (DataBufferByte) raster.getDataBuffer();

		return (data.getData());
	}
}
