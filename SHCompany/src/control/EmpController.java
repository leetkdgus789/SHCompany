package control;

import java.net.URL;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.EmployeeVO;
import model.SalaryVO;

public class EmpController implements Initializable {
	@FXML
	private DatePicker dpJoindate;
	@FXML
	private TextField txtCode;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtRrnum;
	@FXML
	private TextField txtAddr;
	@FXML
	private TextField txtAddr2;
	@FXML
	private TextField txtEmail;
	@FXML
	private DatePicker dpResigndate;
	@FXML
	private Button btnEmpOk;
	@FXML
	private Button btnEmpEdit;
	@FXML
	private Button btnEmpCancel;
	@FXML
	private Button btnEmpInit;
	@FXML
	private TextField txtEmpSearch;
	@FXML
	private Button btnEmpSearch;
	@FXML
	private Button btnEmpResigndateCancel;
	@FXML
	private Button btnEmpDelete;
	@FXML
	private TableView<EmployeeVO> tvEmp = new TableView<EmployeeVO>();

	ObservableList<EmployeeVO> data = FXCollections.observableArrayList();
	ObservableList<EmployeeVO> selectEmp;

	boolean editDelete = false; // ������ �� ��Ϲ�ư ���� ����
	int selectedIndex; // ���̺��� ������ ��� ���� �ε��� ����

	int no; // ������ ���̺��� ������ ����� ��ȣ ����

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// ��ư �ʱ�ȭ
		btnEmpOk.setDisable(true);
		btnEmpEdit.setDisable(true);
		btnEmpResigndateCancel.setDisable(true);
		dpJoindate.setValue(LocalDate.now());

		// ���̺� ����
		tvEmp.setEditable(true);

		totalList();

		TableColumn colJoindate = new TableColumn("�Ի�����");
		colJoindate.setMinWidth(150);
		colJoindate.setStyle("-fx-alignment : CENTER");
		colJoindate.setCellValueFactory(new PropertyValueFactory<>("joindate"));

		TableColumn colCode = new TableColumn("����ڵ�");
		colCode.setMinWidth(100);
		colCode.setStyle("-fx-alignment : CENTER");
		colCode.setCellValueFactory(new PropertyValueFactory<>("code"));

		TableColumn colName = new TableColumn("�����");
		colName.setMinWidth(100);
		colName.setStyle("-fx-alignment : CENTER");
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn colRrnum = new TableColumn("�ֹι�ȣ");
		colRrnum.setMinWidth(200);
		colRrnum.setStyle("-fx-alignment : CENTER");
		colRrnum.setCellValueFactory(new PropertyValueFactory<>("rrnum"));

		TableColumn colAddr = new TableColumn("�ּ�");
		colAddr.setMinWidth(200);
		colAddr.setStyle("-fx-alignment : CENTER");
		colAddr.setCellValueFactory(new PropertyValueFactory<>("addr"));

		TableColumn colAddr2 = new TableColumn("���ּ�");
		colAddr2.setMinWidth(200);
		colAddr2.setStyle("-fx-alignment : CENTER");
		colAddr2.setCellValueFactory(new PropertyValueFactory<>("addr2"));

		TableColumn colEmail = new TableColumn("�̸���");
		colEmail.setMinWidth(200);
		colEmail.setStyle("-fx-alignment : CENTER");
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

		TableColumn colResigndate = new TableColumn("�������");
		colResigndate.setMinWidth(150);
		colResigndate.setStyle("-fx-alignment : CENTER");
		colResigndate.setCellValueFactory(new PropertyValueFactory<>("resigndate"));

		// ���̺� ������ �߰�
		tvEmp.setItems(data);
		tvEmp.getColumns().addAll(colJoindate, colCode, colName, colRrnum, colAddr, colAddr2, colEmail, colResigndate);

		// Ȯ�� ��ư �̺�Ʈ ����
		btnEmpOk.setOnAction(event -> {
			try {
				data.removeAll(data);
				EmployeeVO evo = null;
				EmpDAO edao = null;

				if (event.getSource().equals(btnEmpOk)) {
					evo = new EmployeeVO(dpJoindate.getValue(), Integer.parseInt(txtCode.getText()), txtName.getText(),
							txtRrnum.getText(), txtAddr.getText(), txtAddr2.getText(), txtEmail.getText());
					edao = new EmpDAO();
					edao.getEmpOk(evo);

					if (edao != null) {
						totalList();

						dpJoindate.setEditable(true);
						txtCode.setEditable(true);
						txtName.setEditable(true);
						txtRrnum.setEditable(true);
						txtAddr.setEditable(true);
						txtAddr2.setEditable(true);
						txtEmail.setEditable(true);
						handlerBtnEmpInitAction(event);
					}
				}
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("��� ���� �Է�");
				alert.setHeaderText("��� ������ ��Ȯ�� �Է��Ͻÿ�.");
				alert.setContentText("�������� �����ϼ���!");
				alert.showAndWait();
			}

		});

		// �̺�Ʈ ������ ���
		btnEmpEdit.setOnAction(event -> handlerBtnEmpEditAction(event));
		btnEmpInit.setOnAction(event -> handlerBtnEmpInitAction(event));
		btnEmpCancel.setOnAction(event -> handlerBtnEmpCancelAction(event));
		txtEmail.setOnKeyPressed(event -> handlerTxtEmailKeyPressed(event));
		btnEmpSearch.setOnAction(event -> handlerBtnEmpSearchAction(event));
		btnEmpResigndateCancel.setOnAction(event -> handlerBtnEmpResigndateCancelAction(event));
		btnEmpDelete.setOnAction(event -> handlerBtnEmpDeleteAction(event));

		// ���̺� �̺�Ʈ ó��
		tvEmp.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				selectEmp = tvEmp.getSelectionModel().getSelectedItems();
				selectedIndex = tvEmp.getSelectionModel().getSelectedIndex();

				try {
					dpJoindate.setValue(selectEmp.get(0).getJoindate());
					txtCode.setText(selectEmp.get(0).getCode() + "");
					txtName.setText(selectEmp.get(0).getName());
					txtRrnum.setText(selectEmp.get(0).getRrnum());
					txtAddr.setText(selectEmp.get(0).getAddr());
					txtAddr2.setText(selectEmp.get(0).getAddr2());
					txtEmail.setText(selectEmp.get(0).getEmail());
					dpResigndate.setValue(selectEmp.get(0).getResigndate());

					txtCode.setEditable(false);
					btnEmpResigndateCancel.setDisable(false);
					btnEmpEdit.setDisable(false);

					// ���� �����ÿ� ��Ϲ�ư ��Ȱ��ȭ ��Ű��
					editDelete = true;
				} catch (Exception e) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("��� ���� ���� ����");
					alert.setHeaderText("��� ������ �Է��Ͻÿ�.");
					alert.setContentText("�������� �����ϼ���!");
					alert.showAndWait();
				}
			}
		});

	}
	
	// ���� ��ư �̺�Ʈ
	public void handlerBtnEmpDeleteAction(ActionEvent event) {
		
		try {
			data.removeAll(data);

			EmployeeVO evo = null;
			EmpDAO edao = null;

			if (event.getSource().equals(btnEmpDelete)) {
				evo = new EmployeeVO(dpJoindate.getValue(),
						Integer.parseInt(txtCode.getText()), txtName.getText(),
						txtRrnum.getText(), txtAddr.getText(),
						txtAddr2.getText(), txtEmail.getText(),
						dpResigndate.getValue());
				edao = new EmpDAO();
				edao.getEmpDelete(evo);
				if (edao != null) {

					
					handlerBtnEmpInitAction(event);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("��� ���� �Է�");
			alert.setHeaderText("��� ������ ����");
			alert.setContentText("��� ������ ��Ȯ�� �Է��Ͻÿ�.");
			alert.showAndWait();
		}
		totalList();

	}

	// ������ ó����ư �޼ҵ�
	public void handlerBtnEmpResigndateCancelAction(ActionEvent event) {
		try {
			data.removeAll(data);
			EmployeeVO evo = null;
			EmpDAO edao = null;

			if (event.getSource().equals(btnEmpResigndateCancel)) {

				evo = new EmployeeVO(dpJoindate.getValue(), Integer.parseInt(txtCode.getText()), txtAddr.getText(),
						txtAddr2.getText(), txtEmail.getText(), dpResigndate.getValue());

				edao = new EmpDAO();
				edao.getResigndateCancel(evo, evo.getCode());
			}
			if (edao != null) {
				totalList();

				dpJoindate.setEditable(true);
				txtCode.setEditable(true);
				txtName.setEditable(true);
				txtRrnum.setEditable(true);
				txtAddr.setEditable(true);
				txtAddr2.setEditable(true);
				txtEmail.setEditable(true);
				dpResigndate.setEditable(true);
				btnEmpOk.setDisable(true);
				handlerBtnEmpInitAction(event);
			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("��� ���� ����");
			alert.setHeaderText("�����Ǵ� ������ ��Ȯ�� �Է��Ͻÿ�.");
			alert.setContentText("�������� �����ϼ���!");
			alert.showAndWait();
		}

	}

	// �˻���ư �޼ҵ�
	public void handlerBtnEmpSearchAction(ActionEvent event) {
		for (EmployeeVO list : data) {
			if ((list.getName()).equals(txtEmpSearch.getText())) {
				tvEmp.getSelectionModel().select(list);
				selectEmp = tvEmp.getSelectionModel().getSelectedItems();
				selectedIndex = tvEmp.getSelectionModel().getSelectedIndex();
				try {
					dpJoindate.setValue(selectEmp.get(0).getJoindate());
					txtCode.setText(selectEmp.get(0).getCode() + "");
					txtName.setText(selectEmp.get(0).getName());
					txtRrnum.setText(selectEmp.get(0).getRrnum());
					txtAddr.setText(selectEmp.get(0).getAddr());
					txtAddr2.setText(selectEmp.get(0).getAddr2());
					txtEmail.setText(selectEmp.get(0).getEmail());
					dpResigndate.setValue(selectEmp.get(0).getResigndate());

					txtName.setEditable(false);
					txtCode.setEditable(false);

					btnEmpEdit.setDisable(false);

					// ���� �����ÿ� ��Ϲ�ư ��Ȱ��ȭ ��Ű��
					editDelete = true;
				} catch (Exception e) {
					System.out.println(e);
				}
			}

		}
	}

	// ��Ϲ�ư Ȱ��ȭ �޼ҵ�
	public void handlerTxtEmailKeyPressed(KeyEvent event) {

		if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB) {

			btnEmpOk.setDisable(false);

		}

	}

	// ��ü����Ʈ �޼ҵ�
	public void totalList() {
		Object[][] totalData;

		EmpDAO eDao = new EmpDAO();
		EmployeeVO eVo = null;

		// ���� ArrayList�� �߱� ������ �̷��� �ؾߵ�
		ArrayList<String> title;
		ArrayList<EmployeeVO> list;

		title = eDao.getColumnName(); // ���̺� Į���� �޼ҵ� �ҷ�����
		int columnCount = title.size(); // ��

		// if (dpResigndate.getValue() != null) {
		list = eDao.getEmpTotal(); // ���̺� ������ �޼ҵ� �ҷ�����
		int rowCount = list.size(); // ��

		totalData = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {
			eVo = list.get(index);
			data.add(eVo);
		}

	}

	// ������ư �޼ҵ�
	public void handlerBtnEmpEditAction(ActionEvent event) {
		try {
			data.removeAll(data);
			EmployeeVO evo = null;
			EmpDAO edao = null;

			if (event.getSource().equals(btnEmpEdit)) {

				evo = new EmployeeVO(dpJoindate.getValue(), Integer.parseInt(txtCode.getText()), txtName.getText(), txtRrnum.getText(),
						txtAddr.getText(), txtAddr2.getText(), txtEmail.getText(), dpResigndate.getValue());

				edao = new EmpDAO();
				if (dpResigndate.getValue() != null) {
					edao.getEmpEdit2(evo, evo.getCode());
				} else {
					edao.getEmpEdit(evo, evo.getCode());
				}
				if (edao != null) {
					totalList();

					dpJoindate.setEditable(true);
					txtCode.setEditable(true);
					txtName.setEditable(true);
					txtRrnum.setEditable(true);
					txtAddr.setEditable(true);
					txtAddr2.setEditable(true);
					txtEmail.setEditable(true);
					dpResigndate.setEditable(true);
					btnEmpOk.setDisable(true);
					handlerBtnEmpInitAction(event); // �ʱ�ȭ ��ư �޼ҵ�
				}
			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("��� ���� ����");
			alert.setHeaderText("�����Ǵ� ������ ��Ȯ�� �Է��Ͻÿ�.");
			alert.setContentText("�������� �����ϼ���!");
			alert.showAndWait();
		}
	}

	// �ݱ��ư �޼ҵ�
	public void handlerBtnEmpCancelAction(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
			Scene scene = new Scene(root);
			Stage SubjectStage = new Stage();

			Stage oldStage = (Stage) btnEmpCancel.getScene().getWindow();

			oldStage.close();
			SubjectStage.setTitle("�������� �޿����� ���α׷�");
			SubjectStage.setScene(scene);
			SubjectStage.show();
			SubjectStage.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �ʱ�ȭ��ư �޼ҵ�
	private void handlerBtnEmpInitAction(ActionEvent event) {

		dpJoindate.setValue(LocalDate.now());
		txtCode.clear();
		txtCode.setEditable(true);
		txtName.clear();
		txtName.setEditable(true);
		txtRrnum.clear();
		txtRrnum.setEditable(true);
		txtAddr.clear();
		txtAddr.setEditable(true);
		txtAddr2.clear();
		txtAddr2.setEditable(true);
		txtEmail.clear();
		txtEmail.setEditable(true);
		editDelete = false;
		btnEmpOk.setDisable(true);
		btnEmpEdit.setDisable(true);
		dpResigndate.setValue(null);
		btnEmpResigndateCancel.setDisable(true);
	}

}
