package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RootController implements Initializable {
	@FXML
	private Button btnEmp;
	@FXML
	private Button btnSalary;
	@FXML
	private Button btnExit;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnEmp.setOnAction(event -> handlerBtnEmpAction(event));
		btnSalary.setOnAction(event -> handlerBtnSalaryAction(event));
		btnExit.setOnAction(event -> handlerBtnExitAction(event));

	}

	public void handlerBtnEmpAction(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/emp.fxml"));
			Scene scene = new Scene(root);
			Stage EmpStage = new Stage();

			Stage oldStage = (Stage) btnEmp.getScene().getWindow();

			oldStage.close();
			EmpStage.setTitle("사원");
			EmpStage.setScene(scene);
			EmpStage.show();
			EmpStage.setResizable(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerBtnSalaryAction(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/salary.fxml"));
			Scene scene = new Scene(root);
			Stage EmpStage = new Stage();

			Stage oldStage = (Stage) btnSalary.getScene().getWindow();

			oldStage.close();
			EmpStage.setTitle("급여");
			EmpStage.setScene(scene);
			EmpStage.show();
			EmpStage.setResizable(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void handlerBtnExitAction(ActionEvent event) {
		Platform.exit();
		
	}

}
