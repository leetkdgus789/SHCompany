package application;
	

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws NumberFormatException {
		try {
			Parent view = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
			Scene scene = new Scene(view);

			primaryStage.setTitle("SH Company 급여관리 프로그램");
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
