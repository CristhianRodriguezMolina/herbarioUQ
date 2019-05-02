package co.gov.jsasociados.main;

import co.gov.jsasociados.controllers.WindowController;
import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/view/window.fxml"));
		Scene scene = new Scene(root);
				
		primaryStage.setScene(scene);		
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}
}
