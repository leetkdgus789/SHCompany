package control;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.EmployeeVO;
import model.SalaryVO;

public class SalController implements Initializable {

	@FXML
	private TextField txtYear;
	@FXML
	private ComboBox<String> cbMonth;
	@FXML
	private Button btnSalList;
	@FXML
	private TableView<EmployeeVO> tvInfo = new TableView<EmployeeVO>();
	@FXML
	private TextField txtSalCode;
	@FXML
	private TextField txtSalName;
	@FXML
	private TextField txtSalRrnum;
	@FXML
	private TextField txtSalJoin;
	@FXML
	private TextField txtSalResign;
	@FXML
	private TextField txtBasepay;
	@FXML
	private TextField txtBonus;
	@FXML
	private TextField txtOverpay;
	@FXML
	private TextField txtSumpay;
	@FXML
	private TextField txtPension;
	@FXML
	private TextField txtHealth;
	@FXML
	private TextField txtEmpment;
	@FXML
	private TextField txtWithtax;
	@FXML
	private TextField txtLocaltax;
	@FXML
	private TextField txtSumtax;
	@FXML
	private TextField txtActualpay;
	@FXML
	private Button btnSalCal;
	@FXML
	private Button btnSalInit;
	@FXML
	private Button btnSalOk;
	@FXML
	private Button btnSalEdit;
	@FXML
	private Button btnSalDelete;
	@FXML
	private Button btnSalBar;
	@FXML
	private Button btnSalTotal;
	@FXML
	private Button btnSalCancel;
	@FXML
	private TextField txtSalSearch;
	@FXML
	private Button btnSalSearch;
	@FXML
	private TableView<SalaryVO> tvSal = new TableView<SalaryVO>();
	@FXML
	private Button btnBarchartCancle;
	@FXML
	private TextField txtTotalYear;
	@FXML
	private TextField cbTotalMonth;

	ObservableList<EmployeeVO> data = FXCollections.observableArrayList();
	ObservableList<EmployeeVO> selectSal;
	ObservableList<SalaryVO> salData = FXCollections.observableArrayList();
	ObservableList<SalaryVO> salTotalData = FXCollections.observableArrayList();
	ObservableList<SalaryVO> data1 = FXCollections.observableArrayList();
	ObservableList<SalaryVO> selectSal2;

	boolean editDelete = false; // 수정할 때 등록버튼 상태 설정
	int selectedIndex; // 테이블에서 선택한 사원 정보 인덱스 저장

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		btnSalCal.setDisable(true);
		btnSalEdit.setDisable(true);
		btnSalInit.setDisable(true);
		btnSalOk.setDisable(true);
		btnSalDelete.setDisable(true);

		txtSumpay.setEditable(false);
		txtPension.setEditable(false);
		txtHealth.setEditable(false);
		txtEmpment.setEditable(false);
		txtWithtax.setEditable(false);
		txtLocaltax.setEditable(false);
		txtSumtax.setEditable(false);
		txtActualpay.setEditable(false);

		cbMonth.setItems(FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
				"11", "12"));

		// 이벤트 등록
		btnSalList.setOnAction(event -> handlerBtnSalListAction(event));
		btnSalCancel.setOnAction(event -> handlerBtnSalCancelAction(event));
		btnSalCal.setOnAction(event -> handlerBtnSalCalAction(event));
		btnSalInit.setOnAction(event -> handlerBtnSalInitAction(event));

		btnSalList.setOnKeyPressed(event -> handlerBtnSalListAction(event));

		tvInfo.setOnMouseClicked(event -> handlerTvInfoClicked(event));
		tvInfo.setOnKeyPressed(event -> handlerTvInfoKeyPressedAction(event));

		btnSalOk.setOnAction(event -> handlerBtnSalOkaction(event));
		btnSalEdit.setOnAction(event -> handlerBtnSalEditAction(event));
		btnSalDelete.setOnAction(event -> handlerBtnSalDeleteAction(event));
		btnSalSearch.setOnAction(event -> handlerBtnSalSearchAction(event));

		btnSalBar.setOnAction(event -> handlerBtnSalBarAction(event));
		btnSalTotal.setOnAction(event -> handlerBtnSalTotalAction(event));
		tvSal.setOnMouseClicked(event -> handlerTvSalClicked(event));

		// 사원리스트 테이블 칼럼 등록
		TableColumn colEmpCode = new TableColumn("사원코드");
		colEmpCode.setMinWidth(100);
		colEmpCode.setStyle("-fx-alignment : CENTER");
		colEmpCode.setCellValueFactory(new PropertyValueFactory<>("code"));

		TableColumn colEmpName = new TableColumn("사원명");
		colEmpName.setMinWidth(100);
		colEmpName.setStyle("-fx-alignment : CENTER");
		colEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn colEmpRrnum = new TableColumn("주민번호");
		colEmpRrnum.setMinWidth(200);
		colEmpRrnum.setStyle("-fx-alignment : CENTER");
		colEmpRrnum.setCellValueFactory(new PropertyValueFactory<>("rrnum"));

		// 테이블에 데이터 추가
		tvInfo.setItems(data);
		tvInfo.getColumns().addAll(colEmpCode, colEmpName, colEmpRrnum);

		// 급여리스트 테이블 칼럼 등록
		TableColumn colNo = new TableColumn("번호");
		colNo.setMinWidth(50);
		colNo.setStyle("-fx-alignment : CENTER");
		colNo.setCellValueFactory(new PropertyValueFactory<>("no"));

		TableColumn colYear = new TableColumn("년");
		colYear.setMinWidth(50);
		colYear.setStyle("-fx-alignment : CENTER");
		colYear.setCellValueFactory(new PropertyValueFactory<>("year"));

		TableColumn colMonth = new TableColumn("월");
		colMonth.setMinWidth(50);
		colMonth.setStyle("-fx-alignment : CENTER");
		colMonth.setCellValueFactory(new PropertyValueFactory<>("month"));

		TableColumn colCode = new TableColumn("코드");
		colCode.setMinWidth(50);
		colCode.setStyle("-fx-alignment : CENTER");
		colCode.setCellValueFactory(new PropertyValueFactory<>("code"));

		TableColumn colName = new TableColumn("사원명");
		colName.setMinWidth(80);
		colName.setStyle("-fx-alignment : CENTER");
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn colBase = new TableColumn("기본급");
		colBase.setMinWidth(110);
		colBase.setStyle("-fx-alignment : CENTER");
		colBase.setCellValueFactory(new PropertyValueFactory<>("basepay"));

		TableColumn colBonus = new TableColumn("상여");
		colBonus.setMinWidth(100);
		colBonus.setStyle("-fx-alignment : CENTER");
		colBonus.setCellValueFactory(new PropertyValueFactory<>("bonus"));

		TableColumn colOver = new TableColumn("연장수당");
		colOver.setMinWidth(100);
		colOver.setStyle("-fx-alignment : CENTER");
		colOver.setCellValueFactory(new PropertyValueFactory<>("overpay"));

		TableColumn colSumpay = new TableColumn("지급액");
		colSumpay.setMinWidth(140);
		colSumpay.setStyle("-fx-alignment : CENTER");
		colSumpay.setCellValueFactory(new PropertyValueFactory<>("sumpay"));

		TableColumn colPension = new TableColumn("국민연금");
		colPension.setMinWidth(90);
		colPension.setStyle("-fx-alignment : CENTER");
		colPension.setCellValueFactory(new PropertyValueFactory<>("pension"));

		TableColumn colHealth = new TableColumn("건강보험");
		colHealth.setMinWidth(90);
		colHealth.setStyle("-fx-alignment : CENTER");
		colHealth.setCellValueFactory(new PropertyValueFactory<>("health"));

		TableColumn colMent = new TableColumn("고용보험");
		colMent.setMinWidth(90);
		colMent.setStyle("-fx-alignment : CENTER");
		colMent.setCellValueFactory(new PropertyValueFactory<>("empment"));

		TableColumn colWith = new TableColumn("소득세");
		colWith.setMinWidth(90);
		colWith.setStyle("-fx-alignment : CENTER");
		colWith.setCellValueFactory(new PropertyValueFactory<>("withtax"));

		TableColumn colLocal = new TableColumn("지방세");
		colLocal.setMinWidth(90);
		colLocal.setStyle("-fx-alignment : CENTER");
		colLocal.setCellValueFactory(new PropertyValueFactory<>("localtax"));

		TableColumn colSumtax = new TableColumn("공제액");
		colSumtax.setMinWidth(100);
		colSumtax.setStyle("-fx-alignment : CENTER");
		colSumtax.setCellValueFactory(new PropertyValueFactory<>("sumtax"));

		TableColumn colActual = new TableColumn("차인지급액");
		colActual.setMinWidth(150);
		colActual.setStyle("-fx-alignment : CENTER");
		colActual.setCellValueFactory(new PropertyValueFactory<>("actualpay"));

		// 테이블에 데이터 추가
		tvSal.setItems(salData);
		tvSal.getColumns().addAll(colNo, colYear, colMonth, colCode, colName, colBase, colBonus, colOver, colSumpay,
				colPension, colHealth, colMent, colWith, colLocal, colSumtax, colActual);

	}

	// 사원불러오기 버튼 ENTER 키 적용
	public void handlerBtnSalListAction(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			data.removeAll(data);
			salData.removeAll(salData);
			try {
				empTotalList();
				SalTotalList();
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("에러");
				alert.setHeaderText("귀속년월 미입력");
				alert.setContentText("귀속년월을 입력하시오.");
				alert.showAndWait();
			}
		}

	}

	// 사원항목 키보드 메소드
	public void handlerTvInfoKeyPressedAction(KeyEvent event) {

	}

	// 초기화 메소드
	public void handlerBtnSalInitAction(ActionEvent event) {
		txtBasepay.clear();
		txtBasepay.setEditable(true);
		txtBonus.clear();
		txtBonus.setEditable(true);
		txtOverpay.clear();
		txtOverpay.setEditable(true);
		txtSumpay.clear();
		txtSumpay.setEditable(true);
		txtPension.clear();
		txtPension.setEditable(true);
		txtHealth.clear();
		txtHealth.setEditable(true);
		txtEmpment.clear();
		txtEmpment.setEditable(true);
		txtWithtax.clear();
		txtWithtax.setEditable(true);
		txtLocaltax.clear();
		txtLocaltax.setEditable(true);
		txtSumtax.clear();
		txtSumtax.setEditable(true);
		txtActualpay.clear();
		txtActualpay.setEditable(true);
		btnSalOk.setDisable(true);

		txtSumpay.setEditable(false);
		txtPension.setEditable(false);
		txtHealth.setEditable(false);
		txtEmpment.setEditable(false);
		txtWithtax.setEditable(false);
		txtLocaltax.setEditable(false);
		txtSumtax.setEditable(false);
		txtActualpay.setEditable(false);
	}

	// 계산버튼 메소드
	public void handlerBtnSalCalAction(ActionEvent event) {
		NumberFormat a = NumberFormat.getInstance();
		a.setGroupingUsed(false);
	
		int pension;
		int health;
		int empment;
		int withtax;
		int localtax;

		// 지급액 계산
		try {
			if (txtBonus.getText().equals("") && txtOverpay.getText().equals("")) {
				txtSumpay.setText(Integer.parseInt(txtBasepay.getText().trim()) + "");
			} else if (txtOverpay.getText().equals("")) {
				txtSumpay.setText(
						(Integer.parseInt(txtBasepay.getText().trim()) + Integer.parseInt(txtBonus.getText().trim()))
								+ "");
			} else if (txtBonus.getText().equals("")) {
				txtSumpay.setText(
						(Integer.parseInt(txtBasepay.getText().trim()) + Integer.parseInt(txtOverpay.getText().trim()))
								+ "");
			} else {
				txtSumpay.setText(
						(Integer.parseInt(txtBasepay.getText().trim()) + Integer.parseInt(txtBonus.getText().trim())
								+ Integer.parseInt(txtOverpay.getText().trim())) + "");
			}

			if (txtSumpay.getText() != null) {
				// 국민연금 계산
				// txtPension.setText(Integer.parseInt(txtSumpay.getText()) * 4.5 / 100 + "");
				pension = Integer.parseInt(a.format(Math.round((Double.parseDouble(txtSumpay.getText()) * 4.5 / 100))));
				txtPension.setText(Math.round(pension) + "");

				// 건강보험 계산
				// txtHealth.setText(Integer.parseInt(txtSumpay.getText()) * 3.12 / 100 + "");
				health = Integer.parseInt(a.format(Math.round((Double.parseDouble(txtSumpay.getText()) * 3.12 / 100))));
				txtHealth.setText(health + "");

				// 고용보험 계산
				// txtEmpment.setText(Integer.parseInt(txtSumpay.getText()) * 0.65 / 100 + "");
				empment = Integer.parseInt(a.format(Math.round((Double.parseDouble(txtSumpay.getText()) * 0.65 / 100))));
				txtEmpment.setText(empment + "");

				// 소득세 계산
				// txtWithtax.setText(10000 + "");
				if (Double.parseDouble(txtSumpay.getText()) < 1060000) {
					withtax = 0;
				} else if (Double.parseDouble(txtSumpay.getText()) >= 1060000
						&& Double.parseDouble(txtSumpay.getText()) < 1500000) {
					withtax = Integer.parseInt(a.format(Math.round((Double.parseDouble(txtSumpay.getText()) * 0.005))));
				} else if (Double.parseDouble(txtSumpay.getText()) >= 1500000
						&& Double.parseDouble(txtSumpay.getText()) < 2200000) {
					withtax = Integer.parseInt(a.format(Math.round((Double.parseDouble(txtSumpay.getText()) * 0.011))));
				} else if (Double.parseDouble(txtSumpay.getText()) >= 2200000
						&& Double.parseDouble(txtSumpay.getText()) < 2600000) {
					withtax = Integer.parseInt(a.format(Math.round((Double.parseDouble(txtSumpay.getText()) * 0.019))));
				} else if (Double.parseDouble(txtSumpay.getText()) >= 2600000
						&& Double.parseDouble(txtSumpay.getText()) < 3000000) {
					withtax = Integer.parseInt(a.format(Math.round((Double.parseDouble(txtSumpay.getText()) * 0.028))));
				} else if (Double.parseDouble(txtSumpay.getText()) >= 3000000
						&& Double.parseDouble(txtSumpay.getText()) < 3400000) {
					withtax = Integer.parseInt(a.format(Math.round((Double.parseDouble(txtSumpay.getText()) * 0.038))));
				} else if (Double.parseDouble(txtSumpay.getText()) >= 3400000
						&& Double.parseDouble(txtSumpay.getText()) < 4000000) {
					withtax = Integer.parseInt(a.format(Math.round((Double.parseDouble(txtSumpay.getText()) * 0.052))));
				} else if (Double.parseDouble(txtSumpay.getText()) >= 4000000
						&& Double.parseDouble(txtSumpay.getText()) < 5000000) {
					withtax = Integer.parseInt(a.format(Math.round((Double.parseDouble(txtSumpay.getText()) * 0.070))));
				} else if (Double.parseDouble(txtSumpay.getText()) >= 5000000
						&& Double.parseDouble(txtSumpay.getText()) < 6000000) {
					withtax = Integer.parseInt(a.format(Math.round((Double.parseDouble(txtSumpay.getText()) * 0.092))));
				} else if (Double.parseDouble(txtSumpay.getText()) >= 6000000
						&& Double.parseDouble(txtSumpay.getText()) < 7000000) {
					withtax = Integer.parseInt(a.format(Math.round((Double.parseDouble(txtSumpay.getText()) * 0.111))));
				} else if (Double.parseDouble(txtSumpay.getText()) >= 7000000
						&& Double.parseDouble(txtSumpay.getText()) < 8000000) {
					withtax = Integer.parseInt(a.format(Math.round((Double.parseDouble(txtSumpay.getText()) * 0.125))));
				} else if (Double.parseDouble(txtSumpay.getText()) >= 8000000
						&& Double.parseDouble(txtSumpay.getText()) < 10000000) {
					withtax = Integer.parseInt(a.format(Math.round((Double.parseDouble(txtSumpay.getText()) * 0.155))));
				} else {
					withtax = Integer.parseInt(a.format(Math.round((Double.parseDouble(txtSumpay.getText()) * 0.35))));
				}

				txtWithtax.setText(withtax + "");

				// 지방소득세 계산
				// txtLocaltax.setText(Integer.parseInt(txtWithtax.getText()) / 10 + "");
				localtax = Integer.parseInt(a.format((withtax / 10)));
				txtLocaltax.setText(localtax + "");

				// 공제액
				txtSumtax.setText(pension + health + empment + withtax + localtax + "");

				// 차인지급액
				txtActualpay
						.setText(Integer.parseInt(txtSumpay.getText()) - Integer.parseInt(txtSumtax.getText()) + "");

				btnSalInit.setDisable(false);
				btnSalOk.setDisable(false);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// 닫기버튼 메소드
	public void handlerBtnSalCancelAction(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
			Scene scene = new Scene(root);
			Stage SubjectStage = new Stage();

			Stage oldStage = (Stage) btnSalCancel.getScene().getWindow();

			oldStage.close();
			SubjectStage.setTitle("상현내과 급여관리 프로그램");
			SubjectStage.setScene(scene);
			SubjectStage.show();
			SubjectStage.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 급여 리스트 검색 버튼 메소드
	public void handlerBtnSalSearchAction(ActionEvent event) {
		for (SalaryVO list : salData) {
			if ((list.getName()).equals(txtSalSearch.getText())) {
				tvSal.getSelectionModel().select(list);
				selectSal2 = tvSal.getSelectionModel().getSelectedItems();
				selectedIndex = tvSal.getSelectionModel().getSelectedIndex();
				try {
					txtSalCode.setText(selectSal2.get(0).getCode() + "");
					txtSalName.setText(selectSal2.get(0).getName());
					txtSalRrnum.setText(selectSal2.get(0).getRrnum());
					txtSalJoin.setText(selectSal2.get(0).getJoindate() + "");
					txtSalResign.setText(selectSal2.get(0).getResigndate() + "");

					txtBasepay.setText(selectSal2.get(0).getBasepay() + "");
					txtBonus.setText(selectSal2.get(0).getBonus() + "");
					txtOverpay.setText(selectSal2.get(0).getOverpay() + "");
					txtSumpay.setText(selectSal2.get(0).getSumpay() + "");
					txtPension.setText(selectSal2.get(0).getPension() + "");
					txtHealth.setText(selectSal2.get(0).getHealth() + "");
					txtEmpment.setText(selectSal2.get(0).getEmpment() + "");
					txtWithtax.setText(selectSal2.get(0).getWithtax() + "");
					txtLocaltax.setText(selectSal2.get(0).getLocaltax() + "");
					txtSumtax.setText(selectSal2.get(0).getSumtax() + "");
					txtActualpay.setText(selectSal2.get(0).getActualpay() + "");

					txtSalCode.setEditable(false);
					txtSalName.setEditable(false);
					txtSalRrnum.setEditable(false);
					txtSalJoin.setEditable(false);
					txtSalResign.setEditable(false);

					txtSumpay.setEditable(false);
					txtPension.setEditable(false);
					txtHealth.setEditable(false);
					txtEmpment.setEditable(false);
					txtWithtax.setEditable(false);
					txtLocaltax.setEditable(false);
					txtSumtax.setEditable(false);
					txtActualpay.setEditable(false);

					tvInfo.setDisable(false);
					btnSalCal.setDisable(false);
					btnSalEdit.setDisable(false);
					btnSalDelete.setDisable(false);

					// 수정 삭제시에 등록버튼 비활성화 시키기
					editDelete = true;
				} catch (Exception e) {
					System.out.println(e);
				}
			}

		}
	}

	// 사원 리스트 버튼 메소드
	public void handlerBtnSalListAction(ActionEvent event) {
		data.removeAll(data);
		salData.removeAll(salData);

		try {
			empTotalList();
			SalTotalList();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("에러");
			alert.setHeaderText("귀속년월 미입력");
			alert.setContentText("귀속년월을 입력하시오.");
			alert.showAndWait();
		}
	}

	// 사원 리스트 불러오기 메소드
	public void empTotalList() {
		Object[][] totalData;

		EmpDAO eDao = new EmpDAO();
		EmployeeVO eVo = null;
		// 전부 ArrayList로 했기 때문에 이렇게 해야됨
		ArrayList<String> title;
		ArrayList<EmployeeVO> list;

		title = eDao.getSalColumn(); // 테이블 칼럼명 메소드 불러오기
		int columnCount = title.size(); // 열

		list = eDao.getSalRow(txtYear.getText().substring(2, 4).toString(),
				cbMonth.getSelectionModel().getSelectedItem().toString());// 테이블 데이터 메소드 불러오기
		int rowCount = list.size(); // 행

		totalData = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {
			eVo = list.get(index);
			data.add(eVo);
		}
		System.out.println(Integer.parseInt(txtYear.getText().substring(2, 4)));
		System.out.println(Integer.parseInt(cbMonth.getSelectionModel().getSelectedItem()));
	}

	// DB등록 버튼 메소드
	public void handlerBtnSalOkaction(ActionEvent event) {
		try {
			salData.removeAll(salData);

			SalaryVO svo = null;
			SalDAO sdao = null;

			if (event.getSource().equals(btnSalOk)) {
				svo = new SalaryVO(Integer.parseInt(txtYear.getText()),
						Integer.parseInt(cbMonth.getSelectionModel().getSelectedItem()),
						Integer.parseInt(txtSalCode.getText()), txtSalName.getText(),
						txtBasepay.getText(), txtBonus.getText(),
						txtOverpay.getText(), txtSumpay.getText(),
						txtPension.getText(), txtHealth.getText(),
						txtEmpment.getText(), txtWithtax.getText(),
						txtLocaltax.getText(), txtSumtax.getText(),
						txtActualpay.getText(), txtSalRrnum.getText(), txtSalJoin.getText(),
						txtSalResign.getText());
				System.out.println(txtSalResign.getText());
				sdao = new SalDAO();

				sdao.getSalOk(svo);

				if (sdao != null) {

					txtSumpay.setEditable(false);
					txtPension.setEditable(false);
					txtHealth.setEditable(false);
					txtEmpment.setEditable(false);
					txtWithtax.setEditable(false);
					txtLocaltax.setEditable(false);
					txtSumtax.setEditable(false);
					txtActualpay.setEditable(false);
					handlerBtnSalInitAction(event);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("에러");
			alert.setHeaderText("사원 정보 입력");
			alert.setContentText("사원 정보를 정확히 입력하시오.");
			alert.showAndWait();
		}
		SalTotalList();
	}

	// DB수정 버튼 메소드
	public void handlerBtnSalEditAction(ActionEvent event) {
		try {
			salData.removeAll(salData);

			SalaryVO svo = null;
			SalDAO sdao = null;

			if (event.getSource().equals(btnSalEdit)) {
				svo = new SalaryVO(Integer.parseInt(txtYear.getText()),
						Integer.parseInt(cbMonth.getSelectionModel().getSelectedItem()),
						Integer.parseInt(txtSalCode.getText()), txtSalName.getText(),
						txtBasepay.getText(), txtBonus.getText(),
						txtOverpay.getText(), txtSumpay.getText(),
						txtPension.getText(), txtHealth.getText(),
						txtEmpment.getText(), txtWithtax.getText(),
						txtLocaltax.getText(), txtSumtax.getText(),
						txtActualpay.getText(), txtSalRrnum.getText(), txtSalJoin.getText(),
						txtSalResign.getText());
				sdao = new SalDAO();
				sdao.getSalEdit(svo);

				if (sdao != null) {

					txtSumpay.setEditable(false);
					txtPension.setEditable(false);
					txtHealth.setEditable(false);
					txtEmpment.setEditable(false);
					txtWithtax.setEditable(false);
					txtLocaltax.setEditable(false);
					txtSumtax.setEditable(false);
					txtActualpay.setEditable(false);
					handlerBtnSalInitAction(event);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("에러");
			alert.setHeaderText("사원 정보");
			alert.setContentText("사원 정보를 정확히 입력하시오.");
			alert.showAndWait();
		}
		SalTotalList();

	}

	// DB 삭제 버튼 메소드
	public void handlerBtnSalDeleteAction(ActionEvent event) {
		try {
			salData.removeAll(salData);

			SalaryVO svo = null;
			SalDAO sdao = null;

			if (event.getSource().equals(btnSalDelete)) {
				svo = new SalaryVO(Integer.parseInt(txtYear.getText()),
						Integer.parseInt(cbMonth.getSelectionModel().getSelectedItem()),
						Integer.parseInt(txtSalCode.getText()), txtSalName.getText(),
						txtBasepay.getText(), txtBonus.getText(),
						txtOverpay.getText(), txtSumpay.getText(),
						txtPension.getText(), txtHealth.getText(),
						txtEmpment.getText(), txtWithtax.getText(),
						txtLocaltax.getText(), txtSumtax.getText(),
						txtActualpay.getText());
				sdao = new SalDAO();
				sdao.getSalDelete(svo);

				if (sdao != null) {

					txtSumpay.setEditable(false);
					txtPension.setEditable(false);
					txtHealth.setEditable(false);
					txtEmpment.setEditable(false);
					txtWithtax.setEditable(false);
					txtLocaltax.setEditable(false);
					txtSumtax.setEditable(false);
					txtActualpay.setEditable(false);
					handlerBtnSalInitAction(event);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("에러");
			alert.setHeaderText("삭제 에러");
			alert.setContentText("다시 확인하시오");
			alert.showAndWait();
		}
		SalTotalList();

	}

	// 급여 리스트 불러오기
	public void SalTotalList() {
		Object[][] salTotalDate;

		SalDAO sdao = new SalDAO();
		SalaryVO svo = null;

		ArrayList<String> title;
		ArrayList<SalaryVO> list;

		title = sdao.getColumnName();
		int columnCount = title.size();

		list = sdao.getSalRow(Integer.parseInt(txtYear.getText()),
				Integer.parseInt(cbMonth.getSelectionModel().getSelectedItem()));

		int rowCount = list.size();

		salTotalDate = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {
			svo = list.get(index);
			salData.add(svo);
		}
	}

	// tvInfo 클릭 이벤트
	public void handlerTvInfoClicked(MouseEvent event) {
		selectSal = tvInfo.getSelectionModel().getSelectedItems();

		try {
			txtSalCode.setText(selectSal.get(0).getCode() + "");
			txtSalName.setText(selectSal.get(0).getName());
			txtSalRrnum.setText(selectSal.get(0).getRrnum());
			txtSalJoin.setText(selectSal.get(0).getJoindate() + "");
			txtSalResign.setText(selectSal.get(0).getResigndate() + "");

			txtBasepay.clear();
			txtBasepay.setEditable(true);
			txtBonus.clear();
			txtBonus.setEditable(true);
			txtOverpay.clear();
			txtOverpay.setEditable(true);
			txtSumpay.clear();
			txtSumpay.setEditable(true);
			txtPension.clear();
			txtPension.setEditable(true);
			txtHealth.clear();
			txtHealth.setEditable(true);
			txtEmpment.clear();
			txtEmpment.setEditable(true);
			txtWithtax.clear();
			txtWithtax.setEditable(true);
			txtLocaltax.clear();
			txtLocaltax.setEditable(true);
			txtSumtax.clear();
			txtSumtax.setEditable(true);
			txtActualpay.clear();
			txtActualpay.setEditable(true);
			btnSalOk.setDisable(true);

			txtSumpay.setEditable(false);
			txtPension.setEditable(false);
			txtHealth.setEditable(false);
			txtEmpment.setEditable(false);
			txtWithtax.setEditable(false);
			txtLocaltax.setEditable(false);
			txtSumtax.setEditable(false);
			txtActualpay.setEditable(false);

			txtSalCode.setEditable(false);
			txtSalName.setEditable(false);
			txtSalRrnum.setEditable(false);
			txtSalJoin.setEditable(false);
			txtSalResign.setEditable(false);

			tvInfo.setDisable(false);
			btnSalCal.setDisable(false);

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("사원 선택 실패");
			alert.setHeaderText("사원을 제대로 선택하시오.");
			alert.setContentText("다음에는 주의하세요!");
			alert.showAndWait();
		}

	}

	// tvSal 클릭 이벤트
	public void handlerTvSalClicked(MouseEvent event) {
		selectSal2 = tvSal.getSelectionModel().getSelectedItems();
		
		try {
			txtSalCode.setText(selectSal2.get(0).getCode() + "");
			txtSalName.setText(selectSal2.get(0).getName());

			txtSalRrnum.setText(selectSal2.get(0).getRrnum());
			txtSalJoin.setText(selectSal2.get(0).getJoindate());
			txtSalResign.setText(selectSal2.get(0).getResigndate());

			txtBasepay.setText(selectSal2.get(0).getBasepay().replace(",", "").trim());
			txtBonus.setText(selectSal2.get(0).getBonus().replace(",", "").trim());
			txtOverpay.setText(selectSal2.get(0).getOverpay().replace(",", "").trim());
			txtSumpay.setText(selectSal2.get(0).getSumpay().replace(",", "").trim());
			txtPension.setText(selectSal2.get(0).getPension().replace(",", "").trim());
			txtHealth.setText(selectSal2.get(0).getHealth().replace(",", "").trim());
			txtEmpment.setText(selectSal2.get(0).getEmpment().replace(",", "").trim());
			txtWithtax.setText(selectSal2.get(0).getWithtax().replace(",", "").trim());
			txtLocaltax.setText(selectSal2.get(0).getLocaltax().replace(",", "").trim());
			txtSumtax.setText(selectSal2.get(0).getSumtax().replace(",", "").trim());
			txtActualpay.setText(selectSal2.get(0).getActualpay().replace(",", "").trim());

			txtSalCode.setEditable(false);
			txtSalName.setEditable(false);
			txtSalRrnum.setEditable(false);
			txtSalJoin.setEditable(false);
			txtSalResign.setEditable(false);

			txtSumpay.setEditable(false);
			txtPension.setEditable(false);
			txtHealth.setEditable(false);
			txtEmpment.setEditable(false);
			txtWithtax.setEditable(false);
			txtLocaltax.setEditable(false);
			txtSumtax.setEditable(false);
			txtActualpay.setEditable(false);

			tvInfo.setDisable(false);
			btnSalCal.setDisable(false);
			btnSalEdit.setDisable(false);
			btnSalDelete.setDisable(false);

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("사원 선택 실패");
			alert.setHeaderText("사원을 제대로 선택하시오.");
			alert.setContentText("다음에는 주의하세요!");
			alert.showAndWait();
		}
	}

	// 바 차트 버튼 메소드
	public void handlerBtnSalBarAction(ActionEvent event) {
		try {
			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btnSalBar.getScene().getWindow());
			dialog.setTitle("막대 그래프");

			Parent parent = FXMLLoader.load(getClass().getResource("/view/barchart.fxml"));

			BarChart barChart = (BarChart) parent.lookup("#barChart");

			XYChart.Series seriesSumpay = new XYChart.Series();
			seriesSumpay.setName("지급액");
			ObservableList sumpayList = FXCollections.observableArrayList();
			for (int i = 0; i < salData.size(); i++) {
				sumpayList.add(new XYChart.Data(salData.get(i).getName(), Integer.parseInt(salData.get(i).getBasepay().replaceAll(",","").trim())));
			}
			seriesSumpay.setData(sumpayList);
			barChart.getData().add(seriesSumpay);

			Button btnClose = (Button) parent.lookup("#btnBarchartCancel");

			btnClose.setOnAction(e -> dialog.close());

			Scene scene = new Scene(parent);
			dialog.setScene(scene);
			dialog.show();
		} catch (IOException e) {

		}
	}

	// 통계 버튼 메소드
	public void handlerBtnSalTotalAction(ActionEvent event) {
		try {
			salTotalData.removeAll(salTotalData);

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(btnSalTotal.getScene().getWindow());
			dialog.setTitle("통계");

			Parent parent = FXMLLoader.load(getClass().getResource("/view/total.fxml"));
			TableView<SalaryVO> tvTotal = (TableView<SalaryVO>) parent.lookup("#tvTotal");

			// 통계 테이블 칼럼 등록
			TableColumn colYear = new TableColumn("년");
			colYear.setMinWidth(50);
			colYear.setStyle("-fx-alignment : CENTER");
			colYear.setCellValueFactory(new PropertyValueFactory<>("year"));

			TableColumn colMonth = new TableColumn("월");
			colMonth.setMinWidth(50);
			colMonth.setStyle("-fx-alignment : CENTER");
			colMonth.setCellValueFactory(new PropertyValueFactory<>("month"));

			TableColumn colCode = new TableColumn("총 원");
			colCode.setMinWidth(50);
			colCode.setStyle("-fx-alignment : CENTER");
			colCode.setCellValueFactory(new PropertyValueFactory<>("code"));

			TableColumn colBase = new TableColumn("총 기본급");
			colBase.setMinWidth(100);
			colBase.setStyle("-fx-alignment : CENTER");
			colBase.setCellValueFactory(new PropertyValueFactory<>("basepay"));

			TableColumn colBonus = new TableColumn("총 상여");
			colBonus.setMinWidth(80);
			colBonus.setStyle("-fx-alignment : CENTER");
			colBonus.setCellValueFactory(new PropertyValueFactory<>("bonus"));

			TableColumn colOver = new TableColumn("총 연장수당");
			colOver.setMinWidth(80);
			colOver.setStyle("-fx-alignment : CENTER");
			colOver.setCellValueFactory(new PropertyValueFactory<>("overpay"));

			TableColumn colSumpay = new TableColumn("총 지급액");
			colSumpay.setMinWidth(100);
			colSumpay.setStyle("-fx-alignment : CENTER");
			colSumpay.setCellValueFactory(new PropertyValueFactory<>("sumpay"));

			TableColumn colPension = new TableColumn("총 국민연금");
			colPension.setMinWidth(80);
			colPension.setStyle("-fx-alignment : CENTER");
			colPension.setCellValueFactory(new PropertyValueFactory<>("pension"));

			TableColumn colHealth = new TableColumn("총 건강보험");
			colHealth.setMinWidth(80);
			colHealth.setStyle("-fx-alignment : CENTER");
			colHealth.setCellValueFactory(new PropertyValueFactory<>("health"));

			TableColumn colMent = new TableColumn("총 고용보험");
			colMent.setMinWidth(80);
			colMent.setStyle("-fx-alignment : CENTER");
			colMent.setCellValueFactory(new PropertyValueFactory<>("empment"));

			TableColumn colWith = new TableColumn("총 소득세");
			colWith.setMinWidth(80);
			colWith.setStyle("-fx-alignment : CENTER");
			colWith.setCellValueFactory(new PropertyValueFactory<>("withtax"));

			TableColumn colLocal = new TableColumn("총 지방세");
			colLocal.setMinWidth(80);
			colLocal.setStyle("-fx-alignment : CENTER");
			colLocal.setCellValueFactory(new PropertyValueFactory<>("localtax"));

			TableColumn colSumtax = new TableColumn("총 공제액");
			colSumtax.setMinWidth(100);
			colSumtax.setStyle("-fx-alignment : CENTER");
			colSumtax.setCellValueFactory(new PropertyValueFactory<>("sumtax"));

			TableColumn colActual = new TableColumn("총 차인지급액");
			colActual.setMinWidth(130);
			colActual.setStyle("-fx-alignment : CENTER");
			colActual.setCellValueFactory(new PropertyValueFactory<>("actualpay"));

			// 테이블에 데이터 추가
			tvTotal.setItems(salTotalData);
			tvTotal.getColumns().addAll(colYear, colMonth, colCode, colBase, colBonus, colOver, colSumpay, colPension,
					colHealth, colMent, colWith, colLocal, colSumtax, colActual);

			Object[][] totalData;

			SalDAO sdao = new SalDAO();
			SalaryVO svo = null;
			// 전부 ArrayList로 했기 때문에 이렇게 해야됨
			ArrayList<String> title;
			ArrayList<SalaryVO> list;

			title = sdao.getTotalColumn(); // 테이블 칼럼명 메소드 불러오기
			int columnCount = title.size(); // 열

			list = sdao.getTotalRow();// 테이블 데이터 메소드 불러오기
			int rowCount = list.size(); // 행

			totalData = new Object[rowCount][columnCount];

			for (int index = 0; index < rowCount; index++) {
				svo = list.get(index);
				salTotalData.add(svo);
			}

			Button btnClose = (Button) parent.lookup("#btnTotalCancel");
			btnClose.setOnAction(e -> dialog.close());

			Scene scene = new Scene(parent);
			dialog.setScene(scene);
			dialog.show();
		} catch (IOException e) {

		}
	}

}