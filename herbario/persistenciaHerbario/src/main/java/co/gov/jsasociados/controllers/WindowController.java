package co.gov.jsasociados.controllers;

import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.JOptionPane;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class WindowController {

	@FXML
	private ProgressBar pbCarga;

	@FXML
	private Button btnMeValeVerga;

	@FXML
	private Label lblCargando;

	Timer timer;
	ActionListener al;
	
	@FXML
    void meValeVarge(ActionEvent e) {

		lblCargando.setText("Cargando respuesta.");
		timer();		
		
//		final Service thread = new Service<Integer>() {
//
//			public Task createTask() {
//				return new Task<Integer>() {
//					public Integer call() throws InterruptedException {
//						int i;
//						for (i = 0; i < 1000; i++) {
//
//							updateMessage("Cargando respuesta " + getProgress());
//							updateProgress(i, 1000);
//							Thread.sleep(10);
//						}
//						JOptionPane.showMessageDialog(null, "Pues a mi mas puto");
//						return i;
//					}
//				};
//			}
//
//		};
//
//		pbCarga.progressProperty().bind(thread.progressProperty());
//		lblCargando.textProperty().bind(thread.messageProperty());
//		
//		thread.start();
    }
	
	void timer()
	{		
		
		al = new ActionListener() {
			
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if(pbCarga.getProgress() < 1) {
					pbCarga.setProgress(pbCarga.getProgress()+0.05);				
				}else {
					timer.stop();
					JOptionPane.showMessageDialog(null, "Pues a mi mas topu >:v");
				}				
			}
		};

		timer = new Timer(100, al);
		timer.start();
	}

	public ProgressBar getPbCarga() {
		return pbCarga;
	}

	public void setPbCarga(ProgressBar pbCarga) {
		this.pbCarga = pbCarga;
	}

	public Button getBtnMeValeVerga() {
		return btnMeValeVerga;
	}

	public void setBtnMeValeVerga(Button btnMeValeVerga) {
		this.btnMeValeVerga = btnMeValeVerga;
	}

	public Label getLblCargando() {
		return lblCargando;
	}

	public void setLblCargando(Label lblCargando) {
		this.lblCargando = lblCargando;
	}

}
