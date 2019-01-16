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

	boolean editDelete = false; // 수정할 때 등록버튼 상태 설정
	int selectedIndex; // 테이블에서 선택한 사원 정보 인덱스 저장

	int no; // 삭제시 테이블에서 선택한 사원의 번호 저장

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// 버튼 초기화
		btnEmpOk.setDisable(true);
		btnEmpEdit.setDisable(true);
		btnEmpResigndateCancel.setDisable(true);
		dpJoindate.setValue(LocalDate.now());

		// 테이블 설정
		tvEmp.setEditable(true);

		totalList();

		TableColumn colJoindate = new TableColumn("입사년월일");
		colJoindate.setMinWidth(150);
		colJoindate.setStyle("-fx-alignment : CENTER");
		colJoindate.setCellValueFactory(new PropertyValueFactory<>("joindate"));

		TableColumn colCode = new TableColumn("사원코드");
		colCode.setMinWidth(100);
		colCode.setStyle("-fx-alignment : CENTER");
		colCode.setCellValueFactory(new PropertyValueFactory<>("code"));

		TableColumn colName = new TableColumn("사원명");
		colName.setMinWidth(100);
		colName.setStyle("-fx-alignment : CENTER");
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn colRrnum = new TableColumn("주민번호");
		colRrnum.setMinWidth(200);
		colRrnum.setStyle("-fx-alignment : CENTER");
		colRrnum.setCellValueFactory(new PropertyValueFactory<>("rrnum"));

		TableColumn colAddr = new TableColumn("주소");
		colAddr.setMinWidth(200);
		colAddr.setStyle("-fx-alignment : CENTER");
		colAddr.setCellValueFactory(new PropertyValueFactory<>("addr"));

		TableColumn colAddr2 = new TableColumn("상세주소");
		colAddr2.setMinWidth(200);
		colAddr2.setStyle("-fx-alignment : CENTER");
		colAddr2.setCellValueFactory(new PropertyValueFactory<>("addr2"));

		TableColumn colEmail = new TableColumn("이메일");
		colEmail.setMinWidth(200);
		colEmail.setStyle("-fx-alignment : CENTER");
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

		TableColumn colResigndate = new TableColumn("퇴사년월일");
		colResigndate.setMinWidth(150);
		colResigndate.setStyle("-fx-alignment : CENTER");
		colResigndate.setCellValueFactory(new PropertyValueFactory<>("resigndate"));

		// 테이블에 데이터 추가
		tvEmp.setItems(data);
		tvEmp.getColumns().addAll(colJoindate, colCode, colName, colRrnum, colAddr, colAddr2, colEmail, colResigndate);

		// 확인 버튼 이벤트 설정
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
				alert.setTitle("사원 정보 입력");
				alert.setHeaderText("사원 정보를 정확히 입력하시오.");
				alert.setContentText("다음에는 주의하세요!");
				alert.showAndWait();
			}

		});

		// 이벤트 리스너 등록
		btnEmpEdit.setOnAction(event -> handlerBtnEmpEditAction(event));
		btnEmpInit.setOnAction(event -> handlerBtnEmpInitAction(event));
		btnEmpCancel.setOnAction(event -> handlerBtnEmpCancelAction(event));
		txtEmail.setOnKeyPressed(event -> handlerTxtEmailKeyPressed(event));
		btnEmpSearch.setOnAction(event -> handlerBtnEmpSearchAction(event));
		btnEmpResigndateCancel.setOnAction(event -> handlerBtnEmpResigndateCancelAction(event));
		btnEmpDelete.setOnAction(event -> handlerBtnEmpDeleteAction(event));

		// 테이블에 이벤트 처리
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

					// 수정 삭제시에 등록버튼 비활성화 시키기
					editDelete = true;
				} catch (Exception e) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("사원 정보 수정 삭제");
					alert.setHeaderText("사원 정보를 입력하시오.");
					alert.setContentText("다음에는 주의하세요!");
					alert.showAndWait();
				}
			}
		});

	}
	
	// 삭제 버튼 이벤트
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
			alert.setTitle("사원 정보 입력");
			alert.setHeaderText("사원 정보를 에러");
			alert.setContentText("사원 정보를 정확히 입력하시오.");
			alert.showAndWait();
		}
		totalList();

	}

	// 퇴사취소 처리버튼 메소드
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
			alert.setTitle("사원 정보 수정");
			alert.setHeaderText("수정되는 정보를 정확히 입력하시오.");
			alert.setContentText("다음에는 주의하세요!");
			alert.showAndWait();
		}

	}

	// 검색버튼 메소드
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

					// 수정 삭제시에 등록버튼 비활성화 시키기
					editDelete = true;
				} catch (Exception e) {
					System.out.println(e);
				}
			}

		}
	}

	// 등록버튼 활성화 메소드
	public void handlerTxtEmailKeyPressed(KeyEvent event) {

		if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB) {

			btnEmpOk.setDisable(false);

		}

	}

	// 전체리스트 메소드
	public void totalList() {
		Object[][] totalData;

		EmpDAO eDao = new EmpDAO();
		EmployeeVO eVo = null;

		// 전부 ArrayList로 했기 때문에 이렇게 해야됨
		ArrayList<String> title;
		ArrayList<EmployeeVO> list;

		title = eDao.getColumnName(); // 테이블 칼럼명 메소드 불러오기
		int columnCount = title.size(); // 열

		// if (dpResigndate.getValue() != null) {
		list = eDao.getEmpTotal(); // 테이블 데이터 메소드 불러오기
		int rowCount = list.size(); // 행

		totalData = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {
			eVo = list.get(index);
			data.add(eVo);
		}

	}

	// 수정버튼 메소드
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
					handlerBtnEmpInitAction(event); // 초기화 버튼 메소드
				}
			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("사원 정보 수정");
			alert.setHeaderText("수정되는 정보를 정확히 입력하시오.");
			alert.setContentText("다음에는 주의하세요!");
			alert.showAndWait();
		}
	}

	// 닫기버튼 메소드
	public void handlerBtnEmpCancelAction(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
			Scene scene = new Scene(root);
			Stage SubjectStage = new Stage();

			Stage oldStage = (Stage) btnEmpCancel.getScene().getWindow();

			oldStage.close();
			SubjectStage.setTitle("상현내과 급여관리 프로그램");
			SubjectStage.setScene(scene);
			SubjectStage.show();
			SubjectStage.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 초기화버튼 메소드
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
